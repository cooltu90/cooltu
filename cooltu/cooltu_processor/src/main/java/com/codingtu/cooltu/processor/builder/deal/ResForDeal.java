package com.codingtu.cooltu.processor.builder.deal;

import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.res.Layout;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.builder.builder.ActBaseBuilder;
import com.codingtu.cooltu.processor.builder.builderbase.ActBaseBuilderBase;
import com.codingtu.cooltu.processor.builder.deal.base.TypeElementDeal;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import javax.lang.model.element.TypeElement;

@To({ActBaseBuilder.class, ActBaseBuilderBase.class})
public class ResForDeal extends TypeElementDeal {

    @Override
    public void deal(TypeElement te) {
        ActBaseBuilder actBaseModel = new ActBaseBuilder();

        ResFor resFor = te.getAnnotation(ResFor.class);

        actBaseModel.setActFullName(ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return resFor.value();
            }
        }));

        actBaseModel.layout = IdTools.elementToId(te, Layout.class, te.getAnnotation(Layout.class).value());


    }
}
