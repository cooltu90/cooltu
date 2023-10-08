package com.codingtu.cooltu.processor;

import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.file.list.FileLister;
import com.codingtu.cooltu.lib4j.file.list.ListFile;
import com.codingtu.cooltu.lib4j.file.write.FileWriter;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ts.impl.SetTs;
import com.codingtu.cooltu.processor.bean.SubBuilder;
import com.codingtu.cooltu.processor.constant.Tags;
import com.codingtu.cooltu.processor.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.lib.App;
import com.codingtu.cooltu.processor.lib.BuilderMap;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.PathTools;
import com.codingtu.cooltu.processor.lib.tools.TagTools;
import com.codingtu.cooltu.processor.lib.tools.TempTools;
import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class AppProcessor extends AbstractProcessor {

    private Set<String> supportTypes = new HashSet<>();
    private Class[] types;
    private List<String> builderBaseTempLines;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        IdTools.trees = Trees.instance(processingEnv);
        IdTools.rScanner = new IdTools.RScanner();
        App.init(processingEnv);
        types = SupportTypes.types();
        dealBase();
        dealBuilderBase();
        dealSupportTypes();
    }

    private void dealBase() {
        builderBaseTempLines = TempTools.getTempLines(new File(PathTools.getProcessorJavaInfo(this).path));
    }

    private void dealBuilderBase() {
        String coreProcessorJavaDir = PathTools.getProcessorJavaDir();
        FileLister.dir(PathTools.getBuilderImplDir()).list(new ListFile() {
            @Override
            public void list(File file) {
                String path = file.getAbsolutePath().substring(coreProcessorJavaDir.length());
                path = StringTool.cutSuffix(path, FileType.d_JAVA);
                String classFullName = ConvertTool.pathToPkg(path);
                createBuilderBase(file, classFullName);
            }
        });
    }

    private void createBuilderBase(File file, String classFullName) {
        SubBuilder subBuilder = new SubBuilder();
        subBuilder.initTempLines(copyBuilderBaseTempLines());

        JavaInfo builderJavaInfo = PathTools.getProcessorJavaInfo(classFullName);
        JavaInfo builderBaseJavaInfo = PathTools.getProcessorJavaInfo(Pkg.PROCESSOR_BUILDER_BASE, builderJavaInfo.name + Suffix.PROCESS_BUILDER_BASE);
        subBuilder.map.get("pkg").append(Pkg.PROCESSOR_BUILDER_BASE);
        subBuilder.map.get("name").append(builderBaseJavaInfo.name);
        subBuilder.map.get("base").append(FullName.PROCESS_CORE_BUILDER);
        subBuilder.map.get("JavaInfo").append(FullName.JAVA_INFO);
        StringBuilder filedsSb = subBuilder.map.get("fileds");
        StringBuilder getStringBuilderSb = subBuilder.map.get("getStringBuilder");
        StringBuilder tempLinesSb = subBuilder.map.get("tempLines");
        StringBuilder dealLinesInParentSb = subBuilder.map.get("dealLinesInParent");

        List<String> tempLines = TempTools.getTempLines(file);
        int count = CountTool.count(tempLines);

        List<String> temp = new ArrayList<>();
        if (count > 0) {
            Map<String, String> filedsMap = new HashMap<>();

            for (int i = 0; i < count; i++) {
                String line = tempLines.get(i);
                if (line.startsWith(Tags.SUB_START)) {
                    String[] parts = line.split("\\[");
                    String type = parts[2];
                    String tag = parts[3];
                    if (CountTool.count(temp) == 0 && "if".equals(type)) {
                        TagTools.addLnTag(filedsSb, "    protected boolean is[ObtainMethod];", ConvertTool.toClassType(tag));
                        TagTools.addLnTag(filedsSb, "    private StringBuilder [obtainMethod]Sb;", tag);
                    }
                    temp.add(type);
                } else if (line.endsWith(Tags.SUB_END)) {
                    temp.remove(CountTool.count(temp) - 1);
                } else {
                    List<String> tags = TagTools.getTags(Tags.DOUBLE_START, Tags.DOUBEL_END, line);
                    Ts.ls(tags, new BaseTs.EachTs<String>() {
                        @Override
                        public boolean each(int position, String tag) {
                            if (filedsMap.get(tag) == null) {
                                filedsMap.put(tag, tag);
                                TagTools.addLnTag(filedsSb, "    protected StringBuilder [pkg];", tag);
                                TagTools.addLnTag(getStringBuilderSb, "        [pkg] = map.get(\"[pkg]\");", tag, tag);
                            }
                            return false;
                        }
                    });

                    List<String> mParamTags = TagTools.getTags(Tags.MPARAM_START, Tags.MPARAM_END, line);
                    Ts.ls(mParamTags, new BaseTs.EachTs<String>() {
                        @Override
                        public boolean each(int position, String tag) {
                            if (filedsMap.get(tag) == null) {
                                filedsMap.put(tag, tag);
                                TagTools.addLnTag(filedsSb, "    private StringBuilder [initParams]Sb;", tag);
                                TagTools.addLnTag(filedsSb, "    protected [Params] [initParams];", FullName.PROCESSOR_PARAMS, tag);

                                TagTools.addLnTag(getStringBuilderSb, "        [pkg]Sb = map.get(\"[pkg]Sb\");", tag, tag);
                                TagTools.addLnTag(getStringBuilderSb, "        [initParams] = new [Params]();", tag, FullName.PROCESSOR_PARAMS);

                                TagTools.addLnTag(dealLinesInParentSb, "        [initParams]Sb.append([initParams].getMethodParams());", tag, tag);
                            }
                            return false;
                        }
                    });

                    line = TagTools.dealLine(line, Tags.MPARAM_START, Tags.MPARAM_END, new TagTools.TagValue() {
                        @Override
                        public String tagValue(int i, String tag) {
                            return Tags.DOUBLE_START + tag + "Sb" + Tags.DOUBEL_END;
                        }
                    });
                    TagTools.addLnTag(tempLinesSb, "        lines.add(\"[line]\");", replaceLine(line));
                }

            }
        }
        FileWriter.to(builderBaseJavaInfo.path).cover().write(subBuilder.getLines());

    }

    private List<String> copyBuilderBaseTempLines() {
        ArrayList<String> newLines = new ArrayList<>();
        Ts.ls(builderBaseTempLines, new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int position, String s) {
                newLines.add(s);
                return false;
            }
        });
        return newLines;
    }

    private String replaceLine(String line) {
        return line.replace("\"", "\\\"");
    }

    public void dealSupportTypes() {
        Ts.ts(types).ls(2, new BaseTs.EachTs<Class>() {
            @Override
            public boolean each(int position, Class annoClass) {
                supportTypes.add(annoClass.getCanonicalName());
                return false;
            }
        });
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return supportTypes;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        Ts.ts(types).ls(2, new BaseTs.EachTs<Class>() {
            @Override
            public boolean each(int position, Class annoClass) {
                Class dealClass = types[position + 1];
                Set<Element> es = roundEnv.getElementsAnnotatedWith(annoClass);
                Ts.ts(es).ls(new SetTs.SetEach<Element>() {
                    @Override
                    public boolean each(Element element) {
                        try {
                            BaseDeal deal = (BaseDeal) dealClass.getConstructor().newInstance();
                            deal.dealElement(element);
                        } catch (Exception e) {
                        }
                        return false;
                    }
                });
                return false;
            }
        });

        BuilderMap.create();

        return true;
    }
}
/* model_temp_start
package [[pkg]];
import java.util.ArrayList;
import java.util.List;

public abstract class [[name]] extends [[base]] {
[[fileds]]
    public [[name]]([[JavaInfo]] info) {
        super(info);
[[getStringBuilder]]
    }

    @Override
    protected void dealLinesInParent() {
[[dealLinesInParent]]
    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
[[tempLines]]
        return lines;
    }
}
model_temp_end */
