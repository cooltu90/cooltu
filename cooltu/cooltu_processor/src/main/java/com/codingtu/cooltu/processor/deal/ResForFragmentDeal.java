package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.res.ResForFragment;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.deal.base.ResForBaseDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;

import java.util.List;

import javax.lang.model.element.TypeElement;

public class ResForFragmentDeal extends ResForBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        super.dealTypeElement(te);
    }

    @Override
    protected BaseTools.GetThis<UiBaseBuilder> getUiBaseBuilderGetter() {
        return new BaseTools.GetThis<UiBaseBuilder>() {
            @Override
            public UiBaseBuilder getThis(String thisClass) {
                return CurrentPath.fragBaseBuilder(thisClass).getUiBaseBuilder();
            }

            @Override
            public List<String> getChilds(String thisClass) {
                return FragmentBaseDeal.map.get(thisClass);
            }
        };
    }

    @Override
    protected String getUiClass(TypeElement te) {
        return ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return te.getAnnotation(ResForFragment.class).value();
            }
        });
    }
}
