package com.codingtu.cooltu.processor.builder.subdeal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.annotation.form.view.BindSeekBar;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.FormTools;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.VariableElement;

public class BindSeekBarDeal {

    public static void deal(ActBaseBuilder builder, String beanName, HashMap<Integer, Integer> indexMap, Map<Integer, Integer> typeIndexMap,
                            VariableElement ve, BindSeekBar bindSeekBar) {
        String type = "SEEK_BAR";
        int typeInt = FormType.SEEK_BAR;
        String viewName = FormTools.getViewName(bindSeekBar.name(), ve, BindSeekBar.class, bindSeekBar.value());
        int index = FormTools.getIndex(indexMap, typeInt);
        int typeIndex = FormTools.getTypeIndex(builder, typeIndexMap, type, typeInt);

        builder.seekBarBind(builder.seekBarBindCount(), viewName, FullName.HANDLER_ON_SEEK_BAR_CHANGE_LISTENER, FullName.FORM_TYPE, type, index + "");

        String parseClass = FormTools.getFormParse(ve);
        String field = ElementTools.simpleName(ve);
        if (ClassTool.isNotVoid(parseClass)) {
            if (bindSeekBar.echo()) {
                builder.seekBarEchoWithParse(builder.seekBarEchoWithParseCount(), viewName, parseClass, beanName, field);
            }
            builder.handlerParseItem(typeIndex, builder.handlerParseItemCount(typeIndex), index + "", beanName, field, parseClass);
        } else {
            if (bindSeekBar.echo()) {
                builder.seekBarEcho(builder.seekBarEchoCount(), viewName, beanName, field);
            }
            int handleIndex = builder.handlerItemCount(typeIndex);
            builder.handlerItem(typeIndex, handleIndex, index + "");
            builder.handlerItemIntIf(typeIndex, handleIndex, beanName, field);
        }

        FormTools.addCheck(builder, beanName, ve, field);
    }

}
