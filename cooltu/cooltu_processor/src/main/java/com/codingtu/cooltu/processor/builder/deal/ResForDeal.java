package com.codingtu.cooltu.processor.builder.deal;

import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.res.Layout;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.builder.bean.ActBaseBean;
import com.codingtu.cooltu.processor.builder.builder.ActBaseBuilder;
import com.codingtu.cooltu.processor.builder.deal.base.TypeElementDeal;
import com.codingtu.cooltu.processor.builder.temp.ActBaseTemp;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import javax.lang.model.element.TypeElement;

@To({ActBaseBuilder.class, ActBaseBean.class, ActBaseTemp.class})
public class ResForDeal extends TypeElementDeal {

    @Override
    public void deal(TypeElement te) {
        ActBaseBean actBaseBean = new ActBaseBean();

        //WelcomeActivityRes
        ResFor resFor = te.getAnnotation(ResFor.class);

        //com.codingtu.cooltu.WelcomeActivity
        actBaseBean.actFullName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return resFor.value();
            }
        });

        //com.codingtu.cooltu.R.layout.activity_welcome
        actBaseBean.layout = IdTools.elementToId(te, Layout.class, te.getAnnotation(Layout.class).value());


        ActBaseBuilder actBaseModel = new ActBaseBuilder();
        actBaseModel.actBaseBean = actBaseBean;



    }
}
