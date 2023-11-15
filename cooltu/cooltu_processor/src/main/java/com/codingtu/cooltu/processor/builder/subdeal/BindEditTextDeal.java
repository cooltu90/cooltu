package com.codingtu.cooltu.processor.builder.subdeal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.annotation.form.FormCheck;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.annotation.form.view.BindEditText;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.FormTools;

import java.util.HashMap;

import javax.lang.model.element.VariableElement;

public class BindEditTextDeal {

    public static void deal(ActBaseBuilder builder, String beanName, HashMap<Integer, Integer> indexMap, VariableElement ve, BindEditText bindEditText) {
        String viewName = FormTools.getViewName(bindEditText.name(), ve, BindEditText.class, bindEditText.value());
        int index = FormTools.getIndex(indexMap, FormType.EDIT_TEXT);
        String type = "EDIT_TEXT";

        builder.editTextInit(index, viewName, FullName.HANDLER_TEXT_WATCHER, FullName.FORM_TYPE, type, "" + index);
        builder.handlerEditTextIf(FullName.FORM_TYPE, type);

        String parseClass = FormTools.getFormParse(ve);
        String field = ElementTools.simpleName(ve);
        if (ClassTool.isNotVoid(parseClass)) {
            if (bindEditText.echo())
                builder.etEchoWithParse(builder.etEchoWithParseCount(), FullName.VIEW_TOOL, viewName, parseClass, beanName, field);
            builder.handlerParseEtItem(builder.handlerParseEtItemCount(), index + "", beanName, field, parseClass);
        } else {
            if (bindEditText.echo())
                builder.etEcho(builder.etEchoCount(), FullName.VIEW_TOOL, viewName, beanName, field);
            builder.handlerEditTextItem(builder.handlerEditTextItemCount(), index + "", beanName, field);
        }

        FormTools.addCheck(builder, beanName, ve, field);
    }
}
