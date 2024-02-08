package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.HashMap;

import javax.lang.model.element.TypeElement;

public class FormBeanDeal extends TypeBaseDeal {

    public static final HashMap<String, TypeElement> MAP = new HashMap<>();

    @Override
    protected void dealTypeElement(TypeElement te) {
        MAP.put(ElementTools.getType(te), te);
    }
}
