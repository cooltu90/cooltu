package com.codingtu.cooltu.lib4j.ts;

import com.codingtu.cooltu.lib4j.data.symbol.Symbol;

import java.util.List;

public class BaseTs<T> extends CoreTs<T, BaseTs<T>> {

    public BaseTs() {
    }

    public BaseTs(List<T> list) {
        super(list);
    }

    public SymbolTs toSymbol() {
        SymbolTs symbols = Ts.symbols();
        symbols.add(this.ts);
        return symbols;
    }
}
