package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.data.symbol.ValueSymbol;

import java.util.List;

public class BaseVs<T extends ValueSymbol> extends CoreVs<T, BaseVs> {

    public BaseVs() {
    }

    public BaseVs(List<T> list) {
        super(list);
    }

    @Override
    protected String valueSymbol(T t) {
        return t.valueSymbol();
    }
}
