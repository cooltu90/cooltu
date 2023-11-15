package com.codingtu.cooltu.processor.builder.subdeal;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.annotation.form.view.BindRadioGroup;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.FormTools;

import java.util.HashMap;

import javax.lang.model.element.VariableElement;

public class BindRadioGroupDeal {

    public static void deal(ActBaseBuilder builder, String beanName, HashMap<Integer, Integer> indexMap, VariableElement ve, BindRadioGroup bindRadioGroup) {
        String viewName = FormTools.getViewName(bindRadioGroup.name(), ve, BindRadioGroup.class, bindRadioGroup.value());
        int index = FormTools.getIndex(indexMap, FormType.RADIO_GROUP);
        String type = "RADIO_GROUP";

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

        builder.rgBind(builder.rgBindCount(), viewName, FullName.HANDLER_ON_SELECT_CHANGE, FullName.FORM_TYPE, type, index + "");

        String parseClass = FormTools.getFormParse(ve);
        String field = ElementTools.simpleName(ve);
        if (ClassTool.isNotVoid(parseClass)) {
            if (bindRadioGroup.echo()) {
                builder.rgEchoWithParse(builder.rgEchoWithParseCount(), viewName, parseClass, beanName, field);
            }
            builder.handlerParseRgItem(builder.handlerParseRgItemCount(), index + "", beanName, field, parseClass);
        } else {
            String param = Params.getParam(bindRadioGroup.strItems(), new BaseTs.Convert<String, String>() {
                @Override
                public String convert(int index, String s) {
                    return "\"" + s + "\"";
                }
            });

            if (bindRadioGroup.echo()) {
                builder.rgEcho(builder.rgEchoCount(), viewName, FullName.DEFAULT_RADIO_GROUP_TO_STRING, param, beanName, field);
            }
            builder.handlerRgItem(builder.handlerRgItemCount(), index + "", beanName, field, FullName.DEFAULT_RADIO_GROUP_TO_STRING, param);
        }
        builder.handlerRgIf(FullName.FORM_TYPE, type);

        FormTools.addCheck(builder, beanName, ve, field);

    }
}
