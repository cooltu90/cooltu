package com.codingtu.cooltu.processor;

import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.lib4j.file.list.FileLister;
import com.codingtu.cooltu.lib4j.file.list.ListFile;
import com.codingtu.cooltu.lib4j.file.read.FileReader;
import com.codingtu.cooltu.lib4j.file.write.FileWriter;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ts.impl.SetTs;
import com.codingtu.cooltu.processor.builder.SupportTypes;
import com.codingtu.cooltu.processor.builder.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.lib.App;
import com.codingtu.cooltu.processor.lib.BuilderMap;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.lib.tools.TagTools;
import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        IdTools.trees = Trees.instance(processingEnv);
        IdTools.rScanner = new IdTools.RScanner();
        App.init(processingEnv);
        types = SupportTypes.types();
        buildTemp();
        dealSupportTypes();
    }

    private void buildTemp() {
        FileLister.dir(NameTools.getBuilderBaseDir()).listOnce(new ListFile() {
            @Override
            public void list(File file) {
                if (!file.isDirectory()) {
                    dealFile(file);
                }
            }
        });
    }

    private void dealFile(File file) {
        List<String> oldLines = FileReader.from(file).readLine();
        int[] indexes = {-1, -1};
        boolean isEnd = false;
        int count = CountTool.count(oldLines);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String s = oldLines.get(i);
                if (s.contains("class " + StringTool.cutSuffix(file.getName(), FileType.dJAVA))) {
                    indexes[0] = i;
                }

                if (!isEnd && s.startsWith("}")) {
                    isEnd = true;
                    indexes[1] = i;
                }
            }
        }

        List<String> tags = TagTools.getTags(oldLines);

        List<String> newLines = new ArrayList<>();
        newLines.addAll(oldLines.subList(0, indexes[0] + 1));

        Ts.ls(tags, new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int position, String s) {
                newLines.add("    public StringBuilder " + s + ";");
                return false;
            }
        });

        newLines.add("    @Override");
        newLines.add("    protected void createStringBuilders() {");

        Ts.ls(tags, new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int position, String s) {
                newLines.add("        " + s + " = map.get(\"" + s + "\");");
                return false;
            }
        });

        newLines.add("    }");

        newLines.addAll(oldLines.subList(indexes[1], oldLines.size()));

        FileWriter.to(file).cover().write(newLines);

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
