package com.codingtu.cooltu.processor.builder.subdeal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.form.EchoType;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.annotation.form.view.BindSeekBar;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.FormTools;
import com.codingtu.cooltu.processor.lib.tools.TagTools;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.VariableElement;

public class BindSeekBarDeal {

    public static void deal(ActBaseBuilder builder, String beanName, Map<Integer, Integer> indexMap, Map<Integer, Integer> typeIndexMap,
                            Map<Integer, String> viewMap, Map<Integer, BindMultiDeal.ViewIndex> viewIndexMap,
                            VariableElement ve, BindSeekBar bindSeekBar) {
        String type = "SEEK_BAR";
        int typeInt = FormType.SEEK_BAR;
        String viewName = FormTools.getViewName(bindSeekBar.name(), ve, BindSeekBar.class, bindSeekBar.value());
        viewMap.put(bindSeekBar.value(), viewName);
        int index = FormTools.getIndex(indexMap, typeInt);
        int typeIndex = FormTools.getTypeIndex(builder, typeIndexMap, type, typeInt);

        builder.seekBarBind(builder.seekBarBindCount(), viewName, FullName.HANDLER_ON_SEEK_BAR_CHANGE_LISTENER, FullName.FORM_TYPE, type, index + "");

        String parseClass = FormTools.getFormParse(ve);
        String field = ElementTools.simpleName(ve);

        int handleIndex = builder.handlerItemCount(typeIndex);
        builder.handlerItem(typeIndex, handleIndex, index + "");
        viewIndexMap.put(bindSeekBar.value(), new BindMultiDeal.ViewIndex(typeIndex, handleIndex));

        int echoType = FormTools.getEchoType(ve);
        String checkClass = FormTools.getCheckClass(ve);
        if (echoType == EchoType.CHECK) {
            if (ClassTool.isNotVoid(checkClass)) {
                echos(builder, "            if (new [checkClass]().check([bean], [bean].[field]))",
                        checkClass, beanName, beanName, field);
            } else {
                echos(builder, "            if ([StringTool].isNotBlank([bean].[field]))",
                        FullName.STRING_TOOL, beanName, field);
            }
        }

        if (ClassTool.isNotVoid(parseClass)) {
            if (echoType != EchoType.NOT_ECHO) {
                String line = "            [viewName].setProgress(new [parse]().toView([bean].[field]));";
                if (echoType == EchoType.CHECK) {
                    line = "    " + line;
                }
                echos(builder, line, viewName, parseClass, beanName, field);
            }
            builder.handlerItemParseIf(typeIndex, handleIndex, beanName, field, parseClass);
        } else {
            if (echoType != EchoType.NOT_ECHO) {
                String line = "            [viewName].setProgress([bean].[field]);";
                if (echoType == EchoType.CHECK) {
                    line = "    " + line;
                }
                echos(builder, line, viewName, beanName, field);
            }
            builder.handlerItemIntIf(typeIndex, handleIndex, beanName, field);
        }

        FormTools.addCheck(builder, beanName, ve, field, FormType.SEEK_BAR, viewName);
    }

    private static void echos(ActBaseBuilder builder, String line, Object... tags) {
        builder.echos(builder.echosCount(), TagTools.dealLine(line, tags));
    }

}
