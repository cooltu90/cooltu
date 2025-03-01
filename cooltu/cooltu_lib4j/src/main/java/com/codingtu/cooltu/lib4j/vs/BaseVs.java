package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.vs.value.ValueSymbol;

public class BaseVs<T extends ValueSymbol> extends CoreVs<T, BaseVs> {
    @Override
    protected String valueSymbol(T t) {
        return t.valueSymbol();
    }
}
