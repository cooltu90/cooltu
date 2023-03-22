package cooltu.processor.configs;

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
import javax.tools.Diagnostic;

import cooltu.constant.Pkg;
import cooltu.constant.Suffix;
import cooltu.lib4j.config.LibApp;
import cooltu.lib4j.config.LibConfigs;
import cooltu.lib4j.ls.Ts;
import cooltu.lib4j.ls.each.Each;
import cooltu.lib4j.tools.ClassTool;
import cooltu.processor.SupportTypes;
import cooltu.processor.deal.base.BaseDeal;
import cooltu.processor.model.base.ModelMap;
import cooltu.processor.tools.IdTool;
import cooltu.processor.tools.Logs;

@AutoService(Processor.class)
public class AppProcessor extends AbstractProcessor {

    private Set<String> supportTypes = new HashSet<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        LibApp.APP = new LibApp() {
            @Override
            protected LibConfigs createConfigs() {
                return new ProcessorConfigs(processingEnv.getMessager());
            }
        };
        IdTool.trees = Trees.instance(processingEnv);
        IdTool.rScanner = new IdTool.RScanner();
        dealSupportTypes();
    }

    public void dealSupportTypes() {
        Ts.ls(SupportTypes.types(), new Each<Class>() {
            @Override
            public boolean each(int position, Class aClass) {
                supportTypes.add(aClass.getCanonicalName());
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
        Ts.ls(SupportTypes.types(), new Each<Class>() {
            @Override
            public boolean each(int position, Class clazz) {
                String dealFullName = Pkg.DEAL + "." + clazz.getSimpleName() + Suffix.DEAL;
                final Class dealClass = ClassTool.getClass(dealFullName);
                if (dealClass != null) {
                    Set<Element> es = roundEnv.getElementsAnnotatedWith(clazz);
                    Ts.ls(es, new Each<Element>() {
                        @Override
                        public boolean each(int position, Element element) {
                            try {
                                BaseDeal deal = (BaseDeal) dealClass.newInstance();
                                deal.dealElement(element);
                            } catch (Exception e) {
                            }
                            return false;
                        }
                    });
                }
                return false;
            }
        });

        ModelMap.create();
        return true;
    }
}