package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.data.symbol1.Symbol;

import java.util.List;

public class SymbolVs<T extends Symbol> extends CoreVs<T, SymbolVs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public SymbolVs() {
    }

    public SymbolVs(List<T> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取valueSymbol
    //
    ///////////////////////////////////////////////////////
    @Override
    protected String obtainSymbol(T t) {
        return t.obtainSymbol();
    }
}
