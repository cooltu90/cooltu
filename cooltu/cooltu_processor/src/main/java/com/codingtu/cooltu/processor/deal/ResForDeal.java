package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.annotation.res.InBase;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.BuilderMap;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

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
        Ts.ls(te.getEnclosedElements(), (position, element) -> {
            if (element instanceof VariableElement) {
                VariableElement ve = (VariableElement) element;
                dealField(actClass, ve);
            }
            return false;
        });


    }

    private void dealField(String fullName,VariableElement ve) {
        InBase inBase = ve.getAnnotation(InBase.class);
        if (inBase != null) {
            KV<String, String> kv = ElementTools.getFiledKv(ve);
            BaseTools.getActBaseBuilderWithChilds(fullName, new BaseTs.EachTs<ActBaseBuilder>() {
                @Override
                public boolean each(int position, ActBaseBuilder actBaseBuilder) {
                    if (position == 0) {
                        actBaseBuilder.addInBase(kv);
                    }else{
                        actBaseBuilder.removeInBase(kv);
                    }
                    return false;
                }
            });
        }
    }
}
