package com.codingtu.cooltu.processor.deal.base;

import com.codingtu.cooltu.processor.builder.core.CoreBuilder;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

public abstract class FieldBaseDeal extends BaseDeal {

    @Override
    public void dealElement(Element element) {
        dealFieldElement((VariableElement) element);
    }

    protected abstract void dealFieldElement(VariableElement ve);
}
