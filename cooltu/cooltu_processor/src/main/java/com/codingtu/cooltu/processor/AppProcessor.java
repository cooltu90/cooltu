package com.codingtu.cooltu.processor;

import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.file.list.FileLister;
import com.codingtu.cooltu.lib4j.file.list.ListFile;
import com.codingtu.cooltu.lib4j.file.read.FileReader;
import com.codingtu.cooltu.lib4j.file.write.FileWriter;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ts.impl.SetTs;
import com.codingtu.cooltu.processor.builder.SupportTypes;
import com.codingtu.cooltu.processor.builder.core.CoreBuilder;
import com.codingtu.cooltu.processor.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.lib.App;
import com.codingtu.cooltu.processor.lib.BuilderMap;
import com.codingtu.cooltu.processor.lib.annotation.BuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.lib.tools.TagTools;
import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;

import java.io.File;
import java.lang.annotation.Annotation;
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

import jdk.tools.jaotc.collect.classname.ClassNameSource;

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
        dealBuilderBase();
        dealSupportTypes();
    }

    private void dealBuilderBase() {
        String coreProcessorJavaDir = NameTools.getCoreProcessorJavaDir();
        FileLister.dir(NameTools.getBuilderImplDir()).list(new ListFile() {
            @Override
            public void list(File file) {
                String path = file.getAbsolutePath().substring(coreProcessorJavaDir.length());
                path = StringTool.cutSuffix(path, FileType.d_JAVA);
                String classFullName = ConvertTool.pathToPkg(path);
                try {
                    Class builderClass = Class.forName(classFullName);
                    BuilderBase builderBase = (BuilderBase) builderClass.getAnnotation(BuilderBase.class);
                    createBuilderBase(file, builderClass, builderBase);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void createBuilderBase(File file, Class builderClass, BuilderBase builderBase) {
        List<String> lines = FileReader.from(file).readLine();
        int start = 0;
        int end = 0;
        for (int i = 0; i < CountTool.count(lines); i++) {
            String line = lines.get(i);
            if ("/* model_temp_start".equals(line.trim())) {
                start = i + 1;
            } else if ("model_temp_end */".equals(line.trim())) {
                end = i;
            }
        }

        if (start == end) {
            return;
        }

        lines = lines.subList(start, end);


        StringBuilder sb = new StringBuilder();
        TagTools.addLnTag(sb, "package [pkg];", Pkg.PROCESSOR_BUILDER_BASE);
        TagTools.addLnTag(sb, "import java.util.ArrayList;");
        TagTools.addLnTag(sb, "import java.util.List;");
        TagTools.addLnTag(sb, "public abstract class [name][Base] extends [base] {", builderClass.getSimpleName(), Suffix.PROCESS_BUILDER_BASE, builderBase.base().getCanonicalName());

        TagTools.addLnTag(sb, "    @Override");
        TagTools.addLnTag(sb, "    protected List<String> getTempLines() {");
        TagTools.addLnTag(sb, "        List<String> lines = new ArrayList<>();");
        for (int i = 0; i < CountTool.count(lines); i++) {
            TagTools.addLnTag(sb, "        lines.add(\"[line]\");", lines.get(i).replace("\"", "\\\""));
        }
        TagTools.addLnTag(sb, "        return lines;");
        TagTools.addLnTag(sb, "    }");
        TagTools.addLnTag(sb, "}");

        String builderBasePath = NameTools.getBuilderBase(builderClass.getSimpleName());
        FileWriter.to(builderBasePath).cover().write(sb);
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
