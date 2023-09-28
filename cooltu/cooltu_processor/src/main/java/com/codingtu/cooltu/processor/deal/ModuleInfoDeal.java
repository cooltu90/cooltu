package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;

import javax.lang.model.element.TypeElement;

public class ModuleInfoDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        ModuleInfo moduleInfo = te.getAnnotation(ModuleInfo.class);
        Module.CURRENT = moduleInfo.module();
    }
}
