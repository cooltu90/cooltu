package com.codingtu.cooltu.lib4j.ts;

import com.codingtu.cooltu.lib4j.data.symbol.Symbol;

import java.util.Objects;

public class SymbolTs<SYMBOL extends Symbol> extends BaseTs<SYMBOL, SymbolTs> {

    private String obtainSymbol(SYMBOL t) {
        return ((Symbol) t).obtainSymbol();
    }

    private Ts.IsThisOne<SYMBOL> getSymbolIsThisOne(String... symbols) {
        return new Ts.IsThisOne<SYMBOL>() {
            @Override
            public boolean isThisOne(int position, SYMBOL t) {
                for (int i = 0; i < symbols.length; i++) {
                    if (Objects.equals(symbols[i], obtainSymbol(t))) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    private Ts.IsThisOne<SYMBOL> getSymbolIsThisOne(SYMBOL... symbols) {
        return new Ts.IsThisOne<SYMBOL>() {
            @Override
            public boolean isThisOne(int position, SYMBOL t) {
                for (int i = 0; i < symbols.length; i++) {
                    if (Objects.equals(obtainSymbol(symbols[i]), obtainSymbol(t))) {
                        return true;
                    }
                }
                return false;
            }
        };
    }


    public SYMBOL get(String symbol) {
        return get(getSymbolIsThisOne(symbol));
    }

    public SYMBOL get(SYMBOL symbol) {
        return get(getSymbolIsThisOne(symbol));
    }


    //通过标记字符判定
    public boolean has(String symbol) {
        return get(symbol) != null;
    }

    public boolean has(SYMBOL symbol) {
        return has(obtainSymbol(symbol));
    }


    public int index(String symbol) {
        return index(getSymbolIsThisOne(symbol));
    }

    public int index(SYMBOL symbol) {
        return index(getSymbolIsThisOne(symbol));
    }

    public SymbolTs<SYMBOL> replace(SYMBOL symbol) {
        replace(symbol, getSymbolIsThisOne(symbol));
        return this;
    }

    public SymbolTs<SYMBOL> replace(String symbol, SYMBOL target) {
        replace(target, getSymbolIsThisOne(symbol));
        return this;
    }

    public SymbolTs<SYMBOL> replace(SYMBOL symbol, SYMBOL target) {
        replace(target, getSymbolIsThisOne(symbol));
        return this;
    }


    public SymbolTs<SYMBOL> replaceOrAdd(SYMBOL symbol) {
        replaceOrAdd(symbol, getSymbolIsThisOne(symbol));
        return this;
    }

    public SymbolTs<SYMBOL> replaceOrAdd(String symbol, SYMBOL target) {
        replaceOrAdd(target, getSymbolIsThisOne(symbol));
        return this;
    }

    public SymbolTs<SYMBOL> replaceOrAddT(SYMBOL symbol, SYMBOL target) {
        replaceOrAdd(target, getSymbolIsThisOne(symbol));
        return this;
    }

    public SymbolTs<SYMBOL> replaceAll(SYMBOL symbol) {
        replaceAll(symbol, getSymbolIsThisOne(symbol));
        return this;
    }

    public SymbolTs<SYMBOL> replaceAll(String symbol, SYMBOL target) {
        replaceAll(target, getSymbolIsThisOne(symbol));
        return this;
    }

    public SymbolTs<SYMBOL> replaceAll(SYMBOL symbol, SYMBOL target) {
        replaceAll(target, getSymbolIsThisOne(symbol));
        return this;
    }

    public SymbolTs<SYMBOL> replaceAllOrAdd(SYMBOL target) {
        replaceAllOrAdd(target, getSymbolIsThisOne(target));
        return this;
    }

    public SymbolTs<SYMBOL> replaceAllOrAdd(String symbol, SYMBOL target) {
        replaceAllOrAdd(target, getSymbolIsThisOne(symbol));
        return this;
    }

    public SymbolTs<SYMBOL> replaceAllOrAddT(SYMBOL symbol, SYMBOL target) {
        replaceAllOrAdd(target, getSymbolIsThisOne(symbol));
        return this;
    }

    public SymbolTs<SYMBOL> deleteOnce(String symbol) {
        deleteOnce(getSymbolIsThisOne(symbol));
        return this;
    }

    public SymbolTs<SYMBOL> deleteOnceT(SYMBOL symbol) {
        deleteOnce(getSymbolIsThisOne(symbol));
        return this;
    }

    public SymbolTs<SYMBOL> delete(String... symbols) {
        delete(getSymbolIsThisOne(symbols));
        return this;
    }

    public SymbolTs<SYMBOL> delete(SYMBOL... symbol) {
        delete(getSymbolIsThisOne(symbol));
        return this;
    }

}
