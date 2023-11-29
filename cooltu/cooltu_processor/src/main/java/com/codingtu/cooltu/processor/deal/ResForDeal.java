package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActStartBuilder;
import com.codingtu.cooltu.processor.builder.impl.Code4RequestBuilder;
import com.codingtu.cooltu.processor.builder.impl.PassBuilder;
import com.codingtu.cooltu.processor.deal.base.ResForBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class ResForDeal extends ResForBaseDeal {

    @Override
    protected void dealTypeElement(TypeElement te) {
        super.dealTypeElement(te);
        if (!noStart && !hasStartGroup) {
            Code4RequestBuilder.BUILDER.addAct(uiClass);
            ActStartBuilder.BUILDER.add(uiClass);
        }
    }

    protected void dealField(String fullName, VariableElement ve, KV<String, String> kv,
                             BaseTools.GetThis<UiBaseBuilder> uiBaseBuilderGetter,
                             UiBaseBuilder uiBaseBuilder) {
        super.dealField(fullName, ve, kv, uiBaseBuilderGetter, uiBaseBuilder);
        ActBaseBuilder builder = CurrentPath.actBaseBuilder(fullName);
        if (!noStart) {
            StartGroup startGroup = ve.getAnnotation(StartGroup.class);
            if (startGroup != null) {
                hasStartGroup = true;
                if (CountTool.isNull(builder.starts)) {
                    builder.starts.add(new KV<>(FullName.STRING, Constant.FROM_ACT));
                }
                builder.starts.add(kv);
                PassBuilder.BUILDER.add(kv);
                Code4RequestBuilder.BUILDER.addAct(fullName);
                if (CountTool.isNull(startGroup.value())) {
                    ActStartBuilder.BUILDER.add(fullName, 0, kv);
                } else {
                    Ts.ts(startGroup.value()).ls(new BaseTs.EachTs<Integer>() {
                        @Override
                        public boolean each(int position, Integer integer) {
                            ActStartBuilder.BUILDER.add(fullName, integer, kv);
                            return false;
                        }
                    });
                }
            }
        }
    }

    @Override
    protected BaseTools.GetThis<UiBaseBuilder> getUiBaseBuilderGetter() {
        return BaseTools.getActBaseChildGetter();
    }

    @Override
    protected String getUiClass(TypeElement te) {
        return ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return te.getAnnotation(ResFor.class).value();
            }
        });
    }
}
