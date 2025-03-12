package com.codingtu.cooltu.lib4j.ts1;

import com.codingtu.cooltu.lib4j.data.symbol1.Symbol;

import java.util.List;

public class SymbolTs<T extends Symbol> extends CoreTs<T, SymbolTs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public SymbolTs() {
    }

    public SymbolTs(List<T> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取Symbol
    //
    ///////////////////////////////////////////////////////
    @Override
    protected String obtainSymbol(T t) {
        return t.obtainSymbol();
    }
}
