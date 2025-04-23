package com.codingtu.cooltu.processor.lib;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.config.LibApp;
import com.codingtu.cooltu.lib4j.config.LibConfigs;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.file.delete.FileDeleter;
import com.codingtu.cooltu.lib4j.file.read.FileReader;
import com.codingtu.cooltu.lib4j.file.read.ReadLine;
import com.codingtu.cooltu.lib4j.file.write.FileWriter;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.io.File;
import java.util.ArrayList;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;

public class App extends LibApp {

    private Messager messager;
    private ProcessingEnvironment processingEnv;
    public String deleteActName;
    public String deleteActPackages;

    public App(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        this.messager = processingEnv.getMessager();
    }

    public static void init(ProcessingEnvironment processingEnv) {
        LibApp.APP = new App(processingEnv);
    }

    @Override
    protected LibConfigs createConfigs() {
        return new LibConfigs() {
            @Override
            public boolean isLog() {
                return true;
            }

            @Override
            public boolean isLogHttpConnect() {
                return false;
            }

            @Override
            public boolean isLogJsonException() {
                return false;
            }

            @Override
            public String getDefaultLogTag() {
                return "";
            }

            @Override
            public void baseLog(int level, String tag, String msg) {
                messager.printMessage(Diagnostic.Kind.NOTE, msg);
            }
        };
    }

    public void deleteAct() {
        if (StringTool.isBlank(deleteActName) || StringTool.isBlank(deleteActPackages)) {
            return;
        }

        JavaInfo actJavaInfo = CurrentPath.act(deleteActPackages, deleteActName);

        //删除AndroidManifest.xml
        String manifestPath = CurrentPath.manifest();
        File manifestFile = new File(manifestPath);
        BaseTs<String> newManifestLineTs = Ts.ts();
        Ts.ts(FileReader.from(manifestFile).readLine()).ls(new Ts.EachTs<String>() {

            private boolean isAct;
            private boolean isDelete;
            private ArrayList<String> actLines = new ArrayList<>();

            @Override
            public boolean each(int position, String line) {
                String trimLine = line.trim();

                if (trimLine.startsWith("<activity")) {
                    isAct = true;
                }

                if (trimLine.contains(actJavaInfo.name)) {
                    isDelete = true;
                }

                if (isAct) {
                    actLines.add(line);
                    if (trimLine.endsWith("/>")) {
                        if (isDelete) {
                            isDelete = false;
                        } else {
                            newManifestLineTs.add(actLines);
                        }
                        isAct = false;
                        actLines.clear();
                    }
                } else {
                    newManifestLineTs.add(line);
                }

                return false;
            }
        });
        FileWriter.to(manifestFile).cover().write(newManifestLineTs);


        //删除ActStart
        JavaInfo actStartJavaInfo = CurrentPath.javaInfo(FullName.ACT_START);
        String actMethodName = ConvertTool.toMethodType(actJavaInfo.name);
        BaseTs<String> actStartLineTs = Ts.ts();
        FileReader.from(actStartJavaInfo.path).readLine(new ReadLine<String>() {
            private boolean isStart;

            @Override
            public void readLine(String line) {
                String trimLine = line.trim();
                if (trimLine.startsWith("public static final void " + actMethodName)) {
                    isStart = true;
                } else if (isStart) {
                    if (trimLine.startsWith("}")) {
                        isStart = false;
                    }
                } else {
                    actStartLineTs.add(line);
                }
            }
        });
        FileWriter.to(actStartJavaInfo.path).cover().write(actStartLineTs);

        //删除layout
        File layoutFile = new File(CurrentPath.layout("activity_" + deleteActName));
        FileDeleter.file(layoutFile).delete();
        //ActRes
        JavaInfo actResJavaInfo = CurrentPath.actRes(deleteActPackages, deleteActName);
        FileDeleter.file(actResJavaInfo.path).delete();
        //ActBase
        JavaInfo actBaseJavaInfo = CurrentPath.actBase(actJavaInfo.fullName);
        FileDeleter.file(actBaseJavaInfo.path).delete();
        //Act
        FileDeleter.file(actJavaInfo.path).delete();
    }
}
