package com.codingtu.cooltu.processor.deal.base;

import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.res.ColorStr;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.ui.NoStart;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public abstract class ResForBaseDeal extends TypeBaseDeal {
    protected boolean noStart;
    protected boolean hasStartGroup;
    protected String uiClass;

    @Override
    protected void dealTypeElement(TypeElement te) {
        uiClass = getUiClass(te);
        noStart = te.getAnnotation(NoStart.class) != null;
        Ts.ls(te.getEnclosedElements(), (position, element) -> {
            if (element instanceof VariableElement) {
                VariableElement ve = (VariableElement) element;
                dealField(uiClass, ve);
            }
            return false;
        });
    }

    protected void dealField(String fullName, VariableElement ve) {
        KV<String, String> kv = ElementTools.getFieldKv(ve);
        BaseTools.GetThis<UiBaseBuilder> uiBaseBuilderGetter = getUiBaseBuilderGetter();
        InBase inBase = ve.getAnnotation(InBase.class);
        if (inBase != null) {
            BaseTools.getThisWithChilds(fullName, new BaseTs.EachTs<UiBaseBuilder>() {
                @Override
                public boolean each(int position, UiBaseBuilder uiBaseBuilder) {
                    if (position == 0) {
                        uiBaseBuilder.addInBase(kv);
                    } else {
                        uiBaseBuilder.removeInBase(kv);
                    }
                    return false;
                }
            }, uiBaseBuilderGetter);
        }
        UiBaseBuilder uiBaseBuilder = uiBaseBuilderGetter.getThis(fullName);
        ColorStr ColorStr = ve.getAnnotation(ColorStr.class);
        if (ColorStr != null) {
            uiBaseBuilder.colorStrs.add(new KV<>(kv.v, ColorStr.value()));
        }
    }

    protected abstract BaseTools.GetThis<UiBaseBuilder> getUiBaseBuilderGetter();

    protected abstract String getUiClass(TypeElement te);
}
