package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.annotation.ui.FragmentBase;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import javax.lang.model.element.TypeElement;

public class FragmentBaseDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        String uiFullName = ElementTools.getType(te);
        JavaInfo baseJavaInfo = CurrentPath.actBase(uiFullName);



        FragmentBase fragmentBase = te.getAnnotation(FragmentBase.class);
        Class base = fragmentBase.base();
        int layout = fragmentBase.layout();


    }
}
