package cooltu.processor;

import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

import cooltu.processor.lib.IdTool;

@AutoService(Processor.class)
public class AppProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        IdTool.trees = Trees.instance(processingEnv);
        IdTool.rScanner = new IdTool.RScanner();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }
}