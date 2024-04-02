package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.create.CreateFormConfig;
import com.codingtu.cooltu.processor.builder.impl.CreateFormConfigBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.CreateFormConfigBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.io.File;

import javax.lang.model.element.TypeElement;

public class CreateFormConfigDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        CreateFormConfig createFormConfig = te.getAnnotation(CreateFormConfig.class);
        String name = createFormConfig.name();
        String packages = createFormConfig.packages();

        String beanClassFullName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return createFormConfig.bean();
            }
        });

        JavaInfo formConfigJavaInfo = CurrentPath.formConfig(packages, name);
        if (new File(formConfigJavaInfo.path).exists())
            return;
        JavaInfo formConfigBaseJavaInfo = CurrentPath.formConfigBase(name);
        new CreateFormConfigBaseBuilder(formConfigBaseJavaInfo);
        new CreateFormConfigBuilder(formConfigJavaInfo, formConfigBaseJavaInfo,beanClassFullName);

    }
}
