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

import javax.lang.model.element.VariableElement;

public class BindTextViewDeal {

    public static void deal(ActBaseBuilder builder, String beanName, HashMap<Integer, Integer> indexMap, VariableElement ve, BindTextView bindTextView) {
        String viewName = FormTools.getViewName(bindTextView.name(), ve, BindTextView.class, bindTextView.value());
        int index = FormTools.getIndex(indexMap, FormType.TEXT_VIEW);
        String type = "TEXT_VIEW";

        builder.textViewInit(index, viewName, FullName.HANDLER_TEXT_WATCHER, FullName.FORM_TYPE, type, "" + index);
        builder.handlerTextViewIf(FullName.FORM_TYPE, type);

        String parseClass = null;
        FormParse formParse = ve.getAnnotation(FormParse.class);
        if (formParse != null) {
            parseClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return formParse.value();
                }
            });
        }

        String field = ElementTools.simpleName(ve);
        if (ClassTool.isNotVoid(parseClass)) {
            if (bindTextView.echo())
                builder.tvEchoWithParse(builder.tvEchoWithParseCount(), FullName.VIEW_TOOL, viewName, parseClass, beanName, field);
            builder.handlerParseTvItem(builder.handlerParseTvItemCount(), index + "", beanName, field, parseClass);
        } else {
            if (bindTextView.echo())
                builder.tvEcho(builder.tvEchoCount(), FullName.VIEW_TOOL, viewName, beanName, field);
            builder.handlerTextViewItem(builder.handlerTextViewItemCount(), index + "", beanName, field);
        }

        FormTools.addCheck(builder, beanName, ve, field);
    }


}
