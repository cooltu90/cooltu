package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.data.symbol.ValueSymbol;

import java.util.List;

public class ValueSymbolVs<T extends ValueSymbol> extends CoreVs<T, ValueSymbolVs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public ValueSymbolVs() {
    }

    public ValueSymbolVs(List<T> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取valueSymbol
    //
    ///////////////////////////////////////////////////////
    @Override
    protected String valueSymbol(T t) {
        return t.valueSymbol();
    }
}
