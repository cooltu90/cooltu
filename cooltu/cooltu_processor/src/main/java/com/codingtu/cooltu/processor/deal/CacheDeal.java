package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.processor.builder.impl.CacheBuilder;
import com.codingtu.cooltu.processor.deal.base.FieldBaseDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;

import javax.lang.model.element.VariableElement;

public class CacheDeal extends FieldBaseDeal {
    @Override
    protected void dealFieldElement(VariableElement ve) {
        CacheBuilder.BUILDER.add(ve);
    }
}
