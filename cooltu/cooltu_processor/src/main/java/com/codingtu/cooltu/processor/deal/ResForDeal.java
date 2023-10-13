package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.BuilderMap;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import javax.lang.model.element.TypeElement;

public class ResForDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        ResFor resFor = te.getAnnotation(ResFor.class);
        String actClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return resFor.value();
            }
        });
        JavaInfo actBaseJavaInfo = CurrentPath.actBaseJavaInfo(actClass);
        ActBaseBuilder builder = BuilderMap.find(BuilderType.actBase, actBaseJavaInfo.fullName);
        
    }
}
