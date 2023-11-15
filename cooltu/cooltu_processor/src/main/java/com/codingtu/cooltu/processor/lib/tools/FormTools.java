package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.annotation.form.FormCheck;
import com.codingtu.cooltu.processor.annotation.form.FormParse;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.lib.param.Params;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.VariableElement;

public class FormTools {

    public static String getViewName(String viewName, VariableElement ve, Class annoClass, int resId) {
        if (StringTool.isBlank(viewName)) {
            IdTools.Id id = IdTools.elementToId(ve, annoClass, resId);
            viewName = id.rName;
        }
        return viewName;
    }

    public static int getIndex(Map<Integer, Integer> indexMap, int formType) {
        Integer index = indexMap.get(formType);
        if (index == null) {
            index = 0;
        }
        indexMap.put(formType, index + 1);
        return index;
    }

    public static int getTypeIndex(ActBaseBuilder builder, Map<Integer, Integer> typeIndexMap, String type, int formType) {
        Integer index = typeIndexMap.get(formType);
        if (index == null) {
            typeIndexMap.put(formType, index = builder.handlerCount());
            builder.handler(index, FullName.FORM_TYPE, type);
        }
        return index;
    }

    public static String getFormParse(VariableElement ve) {
        FormParse formParse = ve.getAnnotation(FormParse.class);
        if (formParse != null) {
            return ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return formParse.value();
                }
            });
        }
        return null;
    }

    public static void addCheck(ActBaseBuilder builder, String beanName, VariableElement ve, String field) {
        FormCheck formCheck = ve.getAnnotation(FormCheck.class);
        if (formCheck != null) {
            String checkClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return formCheck.checkClass();
                }
            });

            int checkIndex = builder.checkCount();
            builder.check(checkIndex);

            if (ClassTool.isNotVoid(checkClass)) {
                builder.checkWithDealIf(checkIndex, checkClass, beanName, field, formCheck.prompt());
            } else {
                builder.checkStringIf(checkIndex, FullName.STRING_TOOL, beanName, field, formCheck.prompt());
            }
        }
    }
}
