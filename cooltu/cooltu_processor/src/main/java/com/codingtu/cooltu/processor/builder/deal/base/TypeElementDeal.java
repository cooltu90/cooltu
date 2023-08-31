package com.codingtu.cooltu.processor.builder.deal.base;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public abstract class TypeElementDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        deal((TypeElement) element);
    }

    public abstract void deal(TypeElement element);
}
