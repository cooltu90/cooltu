package com.codingtu.cooltu.processor.builder.core;

import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;
import com.codingtu.cooltu.lib4j.data.symbol.Symbol;

import java.util.List;

import javax.lang.model.element.Element;

public abstract class CoreBuilder implements Symbol {

    protected StringBuilderValueMap<String> map = new StringBuilderValueMap();

    @Override
    public String obtainSymbol() {
        return getClass().getCanonicalName();
    }


    public void create() {
    }

    protected abstract List<String> getTempLines();

}
