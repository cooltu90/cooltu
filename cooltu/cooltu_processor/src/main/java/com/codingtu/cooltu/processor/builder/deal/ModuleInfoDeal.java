package com.codingtu.cooltu.processor.builder.deal;

import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.builder.deal.base.BaseDeal;

import javax.lang.model.element.Element;

public class ModuleInfoDeal extends BaseDeal {

    @Override
    public void deal(Element element) {
        ModuleInfo info = element.getAnnotation(ModuleInfo.class);
        Module.CURRENT = info.module();
    }
}
