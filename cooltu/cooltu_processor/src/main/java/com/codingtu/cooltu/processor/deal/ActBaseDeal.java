package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.res.ActBase;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.bean.ActBaseInfo;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

import java.util.List;

import javax.lang.model.element.TypeElement;

@To(ActBaseBuilder.class)
public class ActBaseDeal extends TypeBaseDeal {

    @Override
    protected void dealTypeElement(TypeElement te) {
        ActBase actBase = te.getAnnotation(ActBase.class);

        ActBaseInfo actBaseInfo = new ActBaseInfo();

        actBaseInfo.baseClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return actBase.base();
            }
        });

        if (ClassTool.isVoid(actBaseInfo.baseClass)) {
            actBaseInfo.baseClass = FullName.BASE_ACT;
        }

        if (actBase.layout() > 0) {
            actBaseInfo.layout = IdTools.elementToId(te, ActBase.class, actBase.layout());
            actBaseInfo.viewInfo = LayoutTools.read(actBaseInfo.layout.rName);
        }

        JavaInfo actBaseJavaInfo = CurrentPath.actBaseJavaInfo(ElementTools.getType(te));

        ActBaseBuilder actBaseBuilder = new ActBaseBuilder(actBaseJavaInfo);
        actBaseBuilder.addInfos(actBaseInfo);

    }
}
