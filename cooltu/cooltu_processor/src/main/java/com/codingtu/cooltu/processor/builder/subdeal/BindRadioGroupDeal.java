package com.codingtu.cooltu.processor.builder.subdeal;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.form.EchoType;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.annotation.form.view.BindRadioGroup;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.FormTools;
import com.codingtu.cooltu.processor.lib.tools.TagTools;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.VariableElement;

public class BindRadioGroupDeal {

    public static void deal(ActBaseBuilder builder, String beanName, Map<Integer, Integer> indexMap, Map<Integer, Integer> typeIndexMap,
                            Map<Integer, String> viewMap, Map<Integer, BindMultiDeal.ViewIndex> viewIndexMap,
                            VariableElement ve, BindRadioGroup bindRadioGroup) {
        String type = "RADIO_GROUP";
        int typeInt = FormType.RADIO_GROUP;
        String viewName = FormTools.getViewName(bindRadioGroup.name(), ve, BindRadioGroup.class, bindRadioGroup.value());
        viewMap.put(bindRadioGroup.value(), viewName);
        int index = FormTools.getIndex(indexMap, FormType.RADIO_GROUP);
        int typeIndex = FormTools.getTypeIndex(builder, typeIndexMap, type, typeInt);


        String onSetItemClassFullName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return bindRadioGroup.onSetItem();
            }
        });
        String onSetItemName = bindRadioGroup.onSetItemName();
        if (StringTool.isBlank(onSetItemName)) {
            onSetItemName = ConvertTool.toMethodType(CurrentPath.javaInfo(onSetItemClassFullName).name);
        }
        int rgIndex = builder.rgInitCount();
        boolean isAddField = builder.addField(Constant.SIGN_PROTECTED, onSetItemClassFullName, onSetItemName);
        if (isAddField) {
            builder.rgOnSetItemInitIf(rgIndex, onSetItemName, onSetItemClassFullName);
        }
        builder.addField(Constant.SIGN_PROTECTED, FullName.RADIO_GROUP, viewName + "Rg");
        builder.rgInit(rgIndex, viewName, FullName.RADIO_GROUP, onSetItemName, Pkg.LIB4A);
        int defualtItem = bindRadioGroup.defualtItem();
        if (defualtItem >= 0) {
            builder.rgDefaultItemIf(rgIndex, viewName, defualtItem + "");
        }

        builder.rgBind(builder.rgBindCount(), viewName, FullName.HANDLER_ON_SELECT_CHANGE, FullName.FORM_TYPE, type, index + "");

        String parseClass = FormTools.getFormParse(ve);
        String field = ElementTools.simpleName(ve);

        int handleIndex = builder.handlerItemCount(typeIndex);
        builder.handlerItem(typeIndex, handleIndex, index + "");
        viewIndexMap.put(bindRadioGroup.value(), new BindMultiDeal.ViewIndex(typeIndex, handleIndex));

        int echoType = FormTools.getEchoType(ve);
        String checkClass = FormTools.getCheckClass(ve);
        if (echoType == EchoType.CHECK) {
            if (ClassTool.isNotVoid(checkClass)) {
                echos(builder, "            if (new [checkClass]().check([bean], [bean].[field]))",
                        checkClass, beanName, beanName, field);
            } else {
                echos(builder, "            if (new [defaultRadioGroupFormCheckFullName]().check([bean], [viewName]Rg.getSelected())) {",
                        FullName.DEFAULT_RADIO_GROUP_FORM_CHECK, beanName, viewName);
            }
        }


        if (ClassTool.isNotVoid(parseClass)) {
            if (echoType != EchoType.NOT_ECHO) {
                String line = "            [viewName]Rg.setSelected(new [parse]().toView([bean].[field]));";
                if (echoType == EchoType.CHECK) {
                    line = "    " + line;
                }
                echos(builder, line, viewName, parseClass, beanName, field);
            }

            builder.handlerItemParseIf(typeIndex, handleIndex, beanName, field, parseClass);
        } else {
            String param = Params.getParam(bindRadioGroup.strItems(), new BaseTs.Convert<String, String>() {
                @Override
                public String convert(int index, String s) {
                    return "\"" + s + "\"";
                }
            });
            if (echoType != EchoType.NOT_ECHO) {
                String line = "            [viewName]Rg.setSelected(new [defaultRadioGroupToStringFullName]([items]).toView([bean].[field]));";
                if (echoType == EchoType.CHECK) {
                    line = "    " + line;
                }
                echos(builder, line, viewName, FullName.DEFAULT_RADIO_GROUP_TO_STRING, param, beanName, field);
            }
            builder.handlerItemRgIf(typeIndex, handleIndex, beanName, field, FullName.DEFAULT_RADIO_GROUP_TO_STRING, param);
        }

        FormTools.addCheck(builder, beanName, ve, field, FormType.RADIO_GROUP, viewName);

    }

    private static void echos(ActBaseBuilder builder, String line, Object... tags) {
        builder.echos(builder.echosCount(), TagTools.dealLine(line, tags));
    }
}
