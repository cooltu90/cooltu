package com.codingtu.cooltu.lib4j.ts;

import com.codingtu.cooltu.lib4j.data.symbol.Symbol;

import java.util.List;
import java.util.Objects;

public class SymbolTs<SYMBOL extends Symbol> extends CoreTs<SYMBOL, SymbolTs<SYMBOL>> {

    public SymbolTs() {
    }

    public SymbolTs(List<SYMBOL> list) {
        super(list);
    }

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

    /**************************************************
     *
     * replace
     *
     **************************************************/

    public SymbolTs<SYMBOL> replace(SYMBOL symbol) {
        replace(symbol, getSymbolIsThisOne(symbol));
        return this;
    }

    public SymbolTs<SYMBOL> replaceTs(List<SYMBOL> srcs) {
        return replaceTs(Ts.symbols(srcs));
    }

    public SymbolTs<SYMBOL> replaceTs(SYMBOL... srcs) {
        return replaceTs(Ts.symbols(srcs));
    }

    public SymbolTs<SYMBOL> replaceTs(SymbolTs<SYMBOL> srcTs) {
        if (srcTs != null && !srcTs.isNull()) {
            srcTs.ls(new Ts.EachTs<SYMBOL>() {
                @Override
                public boolean each(int position, SYMBOL src) {
                    replace(src);
                    return false;
                }
            });
        }
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

    public SymbolTs<SYMBOL> replaceOrAddTs(List<SYMBOL> srcs) {
        return replaceOrAddTs(Ts.symbols(srcs));
    }

    public SymbolTs<SYMBOL> replaceOrAddTs(SYMBOL... srcs) {
        return replaceOrAddTs(Ts.symbols(srcs));
    }

    public SymbolTs<SYMBOL> replaceOrAddTs(SymbolTs<SYMBOL> srcTs) {
        if (srcTs != null && !srcTs.isNull()) {
            srcTs.ls(new Ts.EachTs<SYMBOL>() {
                @Override
                public boolean each(int position, SYMBOL src) {
                    replaceOrAdd(src);
                    return false;
                }
            });
        }
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

    public SymbolTs<SYMBOL> replaceAllTs(SYMBOL... srcs) {
        return replaceAllTs(Ts.symbols(srcs));
    }

    public SymbolTs<SYMBOL> replaceAllTs(List<SYMBOL> srcs) {
        return replaceAllTs(Ts.symbols(srcs));
    }

    public SymbolTs<SYMBOL> replaceAllTs(SymbolTs<SYMBOL> srcTs) {
        if (srcTs != null && !srcTs.isNull()) {
            srcTs.ls(new Ts.EachTs<SYMBOL>() {
                @Override
                public boolean each(int position, SYMBOL src) {
                    replaceAll(src);
                    return false;
                }
            });
        }
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


    public SymbolTs<SYMBOL> replaceAllOrAddTs(SYMBOL... srcs) {
        return replaceAllOrAddTs(Ts.symbols(srcs));
    }

    public SymbolTs<SYMBOL> replaceAllOrAddTs(List<SYMBOL> srcs) {
        return replaceAllOrAddTs(Ts.symbols(srcs));
    }

    public SymbolTs<SYMBOL> replaceAllOrAddTs(SymbolTs<SYMBOL> srcTs) {
        if (srcTs != null && !srcTs.isNull()) {
            srcTs.ls(new Ts.EachTs<SYMBOL>() {
                @Override
                public boolean each(int position, SYMBOL src) {
                    replaceAllOrAdd(src);
                    return false;
                }
            });
        }
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

    /**************************************************
     *
     * delete
     *
     **************************************************/
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

    /**************************************************
     *
     * nearby
     *
     **************************************************/
    public Ts.NearByIndex obtainNearByIndexWhenNextPriority(String symbol) {
        return obtainNearByIndexWhenNextPriority(getSymbolIsThisOne(symbol));
    }

    public SYMBOL obtainNearByDataWhenNextPriority(String symbol) {
        return obtainNearByDataWhenNextPriority(getSymbolIsThisOne(symbol));
    }

    public Ts.NearByIndex obtainNearByIndexWhenNextPriority(SYMBOL symbol) {
        return obtainNearByIndexWhenNextPriority(getSymbolIsThisOne(symbol));
    }

    public SYMBOL obtainNearByDataWhenNextPriority(SYMBOL symbol) {
        return obtainNearByDataWhenNextPriority(getSymbolIsThisOne(symbol));
    }

    public Ts.NearByIndex obtainNearByIndexWhenPrePriority(String symbol) {
        return obtainNearByIndexWhenPrePriority(getSymbolIsThisOne(symbol));
    }

    public SYMBOL obtainNearByDataWhenPrePriority(String symbol) {
        return obtainNearByDataWhenPrePriority(getSymbolIsThisOne(symbol));
    }

    public Ts.NearByIndex obtainNearByIndexWhenPrePriority(SYMBOL symbol) {
        return obtainNearByIndexWhenPrePriority(getSymbolIsThisOne(symbol));
    }

    public SYMBOL obtainNearByDataWhenPrePriority(SYMBOL symbol) {
        return obtainNearByDataWhenPrePriority(getSymbolIsThisOne(symbol));
    }
}
