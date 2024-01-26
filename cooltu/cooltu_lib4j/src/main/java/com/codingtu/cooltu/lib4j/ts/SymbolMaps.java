package com.codingtu.cooltu.lib4j.ts;

import com.codingtu.cooltu.lib4j.data.symbol.Symbol;

public class SymbolMaps<SYMBOL extends Symbol> extends Maps<String, SYMBOL> {

    public SymbolMaps<SYMBOL> put(SYMBOL symbol) {
        put(symbol.obtainSymbol(), symbol);
        return this;
    }

    public SymbolMaps<SYMBOL> delete(SYMBOL symbol) {
        map.remove(symbol.obtainSymbol());
        return this;
    }

}
