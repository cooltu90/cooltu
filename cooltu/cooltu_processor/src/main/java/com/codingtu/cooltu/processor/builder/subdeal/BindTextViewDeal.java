package com.codingtu.cooltu.processor.builder.subdeal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.annotation.form.EchoType;
import com.codingtu.cooltu.processor.annotation.form.FormCheck;
import com.codingtu.cooltu.processor.annotation.form.FormParse;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.annotation.form.view.BindEditText;
import com.codingtu.cooltu.processor.annotation.form.view.BindTextView;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.FormTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.TagTools;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.VariableElement;

public class BindTextViewDeal {

    public static void deal(ActBaseBuilder builder, String beanName, Map<Integer, Integer> indexMap, Map<Integer, Integer> typeIndexMap,
                            Map<Integer, String> viewMap, Map<Integer, BindMultiDeal.ViewIndex> viewIndexMap,
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

        int handleIndex = builder.handlerItemCount(typeIndex);
        builder.handlerItem(typeIndex, handleIndex, index + "");
        viewIndexMap.put(bindTextView.value(), new BindMultiDeal.ViewIndex(typeIndex, handleIndex));

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
                String line = "            [viewToolFullName].setText([view], new [parse]().toView([bean].[field]));";
                if (echoType == EchoType.CHECK) {
                    line = "    " + line;
                }
                echos(builder, line, FullName.VIEW_TOOL, viewName, parseClass, beanName, field);
            }
            builder.handlerItemParseIf(typeIndex, handleIndex, beanName, field, parseClass);
        } else {
            if (echoType != EchoType.NOT_ECHO) {
                String line = "            [viewToolFullName].setText([view], [bean].[field]);";
                if (echoType == EchoType.CHECK) {
                    line = "    " + line;
                }
                echos(builder, line, FullName.VIEW_TOOL, viewName, beanName, field);
            }
            builder.handlerItemStringIf(typeIndex, handleIndex, beanName, field);
        }

        FormTools.addCheck(builder, beanName, ve, field, FormType.TEXT_VIEW, viewName);
    }

    private static void echos(ActBaseBuilder builder, String line, Object... tags) {
        builder.echos(builder.echosCount(), TagTools.dealLine(line, tags));
    }


}
