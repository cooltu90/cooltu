package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.res.ColorRes;
import com.codingtu.cooltu.processor.annotation.res.Dimen;
import com.codingtu.cooltu.processor.annotation.res.Dp;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.Adapter;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.processor.bean.ActBaseInfo;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActStartBuilder;
import com.codingtu.cooltu.processor.builder.impl.Code4RequestBuilder;
import com.codingtu.cooltu.processor.builder.impl.PassBuilder;
import com.codingtu.cooltu.processor.deal.base.ResForBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import java.util.List;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class ResForDeal extends ResForBaseDeal {

    @Override
    protected void dealTypeElement(TypeElement te) {
        super.dealTypeElement(te);
        if (!noStart && !hasStartGroup) {
            Code4RequestBuilder.BUILDER.add(uiClass);
            ActStartBuilder.BUILDER.add(uiClass);
        }
    }

    protected void dealField(String fullName, VariableElement ve, KV<String, String> kv) {
        super.dealField(fullName, ve, kv);
        ActBaseInfo actBaseInfo = CurrentPath.actBaseBuilder(fullName).getActBaseInfo();
        if (!noStart) {
            StartGroup startGroup = ve.getAnnotation(StartGroup.class);
            if (startGroup != null) {
                hasStartGroup = true;
                if (CountTool.isNull(actBaseInfo.starts)) {
                    actBaseInfo.starts.add(new KV<>(FullName.STRING, Constant.FROM_ACT));
                }
                actBaseInfo.starts.add(kv);
                PassBuilder.BUILDER.add(kv);
                Code4RequestBuilder.BUILDER.add(fullName);
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
        return new BaseTools.GetThis<UiBaseBuilder>() {
            @Override
            public UiBaseBuilder getThis(String thisClass) {
                return CurrentPath.actBaseBuilder(thisClass).getUiBaseBuilder();
            }

            @Override
            public List<String> getChilds(String thisClass) {
                return ActBaseDeal.map.get(thisClass);
            }
        };
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
