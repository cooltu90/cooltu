package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.lib.param.Params;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
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

    public static KV<String, String> getFieldKv(VariableElement ve) {
        return new KV<>(getType(ve), simpleName(ve));
    }

    public static Params getMethodParamKvs(ExecutableElement ee) {
        return Params.obtain(Ts.ts(ee.getParameters()).convert((index, ve) -> {
            return getFieldKv(ve);
        }).toList());
    }

    public static List<VariableElement> getVariableElements(ExecutableElement ee) {
        return Ts.ts(ee.getParameters()).convert((index, element) -> {
            return (VariableElement) element;
        }).toList();
    }
}
