package com.codingtu.cooltu.processor.builder.deal.base;

import javax.lang.model.element.Element;

public abstract class BaseDeal {

    public void dealElement(Element element) {
        deal(element);
    }

    public abstract void deal(Element element);
}
