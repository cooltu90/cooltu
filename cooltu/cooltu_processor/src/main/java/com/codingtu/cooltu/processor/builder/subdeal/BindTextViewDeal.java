package com.codingtu.cooltu.processor.builder.subdeal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.annotation.form.FormCheck;
import com.codingtu.cooltu.processor.annotation.form.FormParse;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.annotation.form.view.BindEditText;
import com.codingtu.cooltu.processor.annotation.form.view.BindTextView;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.FormTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.VariableElement;

public class BindTextViewDeal {

    public static void deal(ActBaseBuilder builder, String beanName, Map<Integer, Integer> indexMap, Map<Integer, Integer> typeIndexMap,
                            Map<Integer, String> viewMap,
                            VariableElement ve, BindTextView bindTextView) {
        String type = "TEXT_VIEW";
        int typeInt = FormType.TEXT_VIEW;
        String viewName = FormTools.getViewName(bindTextView.name(), ve, BindTextView.class, bindTextView.value());
        viewMap.put(bindTextView.value(), viewName);
        int index = FormTools.getIndex(indexMap, typeInt);
        int typeIndex = FormTools.getTypeIndex(builder, typeIndexMap, type, typeInt);

        builder.textViewInit(index, viewName, FullName.HANDLER_TEXT_WATCHER, FullName.FORM_TYPE, type, "" + index);

        String parseClass = FormTools.getFormParse(ve);
        String field = ElementTools.simpleName(ve);

        if (ClassTool.isNotVoid(parseClass)) {
            if (bindTextView.echo()) {
                builder.tvEchoWithParse(builder.tvEchoWithParseCount(), FullName.VIEW_TOOL, viewName, parseClass, beanName, field);
            }
            builder.handlerParseItem(typeIndex, builder.handlerParseItemCount(typeIndex), index + "", beanName, field, parseClass);
        } else {
            if (bindTextView.echo()) {
                builder.tvEcho(builder.tvEchoCount(), FullName.VIEW_TOOL, viewName, beanName, field);
            }
            int handleIndex = builder.handlerItemCount(typeIndex);
            builder.handlerItem(typeIndex, handleIndex, index + "");
            builder.handlerItemStringIf(typeIndex, handleIndex, beanName, field);
        }

        FormTools.addCheck(builder, beanName, ve, field, FormType.TEXT_VIEW, viewName);
    }


}
