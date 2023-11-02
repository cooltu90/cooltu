package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.processor.annotation.form.FormBean;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.HashMap;

import javax.lang.model.element.TypeElement;

public class FormBeanDeal extends TypeBaseDeal {

    public static final HashMap<String, String> MAP = new HashMap<>();

    @Override
    protected void dealTypeElement(TypeElement te) {
        FormBean formBean = te.getAnnotation(FormBean.class);
        MAP.put(ElementTools.getType(te), formBean.value());
    }
}
