package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.FragmentBase;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.FragmentBaseBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

import javax.lang.model.element.TypeElement;

public class FragmentBaseDeal extends TypeBaseDeal {
    public static ListValueMap<String, String> map = new ListValueMap<>();

    @Override
    protected void dealTypeElement(TypeElement te) {
        FragmentBase baseAnno = te.getAnnotation(FragmentBase.class);
        String uiFullName = ElementTools.getType(te);
        JavaInfo baseJavaInfo = CurrentPath.fragBase(uiFullName);
        FragmentBaseBuilder baseBuilder = new FragmentBaseBuilder(baseJavaInfo);
        UiBaseBuilder uiBaseBuilder = baseBuilder.getUiBaseBuilder();
        uiBaseBuilder.uiFullName = uiFullName;
        uiBaseBuilder.baseClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return baseAnno.base();
            }
        });

        if (ClassTool.isVoid(uiBaseBuilder.baseClass)) {
            uiBaseBuilder.baseClass = FullName.BASE_FRAGMENT;
        } else {
            map.get(uiBaseBuilder.baseClass).add(uiBaseBuilder.uiFullName);
        }

        if (baseAnno.layout() > 0) {
            uiBaseBuilder.layout = IdTools.elementToId(te, FragmentBase.class, baseAnno.layout());
            uiBaseBuilder.viewInfos = LayoutTools.convert(uiBaseBuilder.layout.rName);
        }


    }
}
