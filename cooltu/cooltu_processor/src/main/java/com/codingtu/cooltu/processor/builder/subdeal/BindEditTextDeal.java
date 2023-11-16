package com.codingtu.cooltu.processor.builder.subdeal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.annotation.form.view.BindEditText;
import com.codingtu.cooltu.processor.annotation.form.view.BindMulti;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.FormTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import java.util.Map;

import javax.lang.model.element.VariableElement;

public class BindEditTextDeal {

    public static void deal(ActBaseBuilder builder, String beanName, Map<Integer, Integer> indexMap, Map<Integer, Integer> typeIndexMap,
                            Map<Integer, String> viewMap, Map<Integer, BindMultiDeal.ViewIndex> viewIndexMap,
                            VariableElement ve, BindEditText bindEditText) {
        String type = "EDIT_TEXT";
        int typeInt = FormType.EDIT_TEXT;
        String viewName = FormTools.getViewName(bindEditText.name(), ve, BindEditText.class, bindEditText.value());
        viewMap.put(bindEditText.value(), viewName);
        int index = FormTools.getIndex(indexMap, typeInt);
        int typeIndex = FormTools.getTypeIndex(builder, typeIndexMap, type, typeInt);

        builder.editTextInit(index, viewName, FullName.HANDLER_TEXT_WATCHER, FullName.FORM_TYPE, type, "" + index);

        String parseClass = FormTools.getFormParse(ve);
        String field = ElementTools.simpleName(ve);

        int handleIndex = builder.handlerItemCount(typeIndex);
        builder.handlerItem(typeIndex, handleIndex, index + "");
        viewIndexMap.put(bindEditText.value(), new BindMultiDeal.ViewIndex(typeIndex, handleIndex));

        if (ClassTool.isNotVoid(parseClass)) {
            if (bindEditText.echo()) {
                builder.etEchoWithParse(builder.etEchoWithParseCount(), FullName.VIEW_TOOL, viewName, parseClass, beanName, field);
            }
            builder.handlerItemParseIf(typeIndex, handleIndex, beanName, field, parseClass);
        } else {
            if (bindEditText.echo()) {
                builder.etEcho(builder.etEchoCount(), FullName.VIEW_TOOL, viewName, beanName, field);
            }
            builder.handlerItemStringIf(typeIndex, handleIndex, beanName, field);
        }

        FormTools.addCheck(builder, beanName, ve, field, FormType.EDIT_TEXT, viewName);

    }
}
