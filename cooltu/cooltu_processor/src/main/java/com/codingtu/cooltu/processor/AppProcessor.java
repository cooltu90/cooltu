package com.codingtu.cooltu.processor;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ts.impl.SetTs;
import com.codingtu.cooltu.processor.builder.SupportTypes;
import com.codingtu.cooltu.processor.builder.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.lib.App;
import com.codingtu.cooltu.processor.lib.BuilderMap;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;

import java.util.HashSet;
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
        dealSupportTypes();
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
