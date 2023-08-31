package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

public class ElementTools {

    public static String getType(Element e) {
        return e.asType().toString();
    }

    public static String getParentType(Element e) {
        return getType(e.getEnclosingElement());
    }

    public static String simpleName(Element e) {
        return e.getSimpleName().toString();
    }

    public static String staticSimpleName(Element e) {
        return ConvertTool.toStaticType(simpleName(e));
    }

    public static KV<String, String> getFiledKv(VariableElement ve) {
        return new KV<>(getType(ve), simpleName(ve));
    }

}
