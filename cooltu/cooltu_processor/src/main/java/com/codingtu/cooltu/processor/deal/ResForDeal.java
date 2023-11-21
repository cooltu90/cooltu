package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.res.ColorRes;
import com.codingtu.cooltu.processor.annotation.res.ColorStr;
import com.codingtu.cooltu.processor.annotation.res.Dimen;
import com.codingtu.cooltu.processor.annotation.res.Dp;
import com.codingtu.cooltu.processor.annotation.ui.Adapter;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.NoStart;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.processor.bean.ActBaseInfo;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActStartBuilder;
import com.codingtu.cooltu.processor.builder.impl.Code4RequestBuilder;
import com.codingtu.cooltu.processor.builder.impl.PassBuilder;
import com.codingtu.cooltu.processor.deal.base.ResForBaseDeal;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
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

    protected void dealField(String fullName, VariableElement ve) {
        super.dealField(fullName, ve);

        KV<String, String> kv = ElementTools.getFieldKv(ve);

        ActBaseInfo actBaseInfo = CurrentPath.actBaseBuilder(fullName).getActBaseInfo();

        ColorRes colorRes = ve.getAnnotation(ColorRes.class);
        if (colorRes != null) {
            IdTools.Id id = IdTools.elementToId(ve, ColorRes.class, colorRes.value());
            actBaseInfo.colorReses.add(new KV<>(kv.v, id));
        }

        Dp dp = ve.getAnnotation(Dp.class);
        if (dp != null) {
            actBaseInfo.dps.add(new KV<>(kv.v, dp.value()));
        }

        Dimen dimen = ve.getAnnotation(Dimen.class);
        if (dimen != null) {
            IdTools.Id id = IdTools.elementToId(ve, Dimen.class, dimen.value());
            actBaseInfo.dimens.add(new KV<>(kv.v, id));
        }

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

        Adapter adapter = ve.getAnnotation(Adapter.class);
        if (adapter != null) {
            actBaseInfo.adapters.add(ve);
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
