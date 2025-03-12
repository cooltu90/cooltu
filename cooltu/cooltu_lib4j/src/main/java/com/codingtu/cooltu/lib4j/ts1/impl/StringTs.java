package com.codingtu.cooltu.lib4j.ts1.impl;

import com.codingtu.cooltu.lib4j.ts1.CoreTs;
import com.codingtu.cooltu.lib4j.ts1.Ts;

import java.util.List;

public class StringTs extends CoreTs<String, StringTs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public StringTs() {
    }

    public StringTs(List<String> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取symbol
    //
    ///////////////////////////////////////////////////////

    @Override
    protected String obtainSymbol(String s) {
        return s;
    }

    ///////////////////////////////////////////////////////
    //
    // get方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * get
     **************************************************/
    @Deprecated
    @Override
    public String getBySymbol(String symbol) {
        return super.getBySymbol(symbol);
    }

    @Deprecated
    @Override
    public String get(String s) {
        return super.get(s);
    }

    /**************************************************
     * getAll
     **************************************************/
    @Deprecated
    @Override
    public StringTs getAllBySymbol(String symbol) {
        return super.getAllBySymbol(symbol);
    }

    @Deprecated
    @Override
    public StringTs getAll(String s) {
        return super.getAll(s);
    }

    ///////////////////////////////////////////////////////
    //
    // has方法
    //
    ///////////////////////////////////////////////////////
    @Deprecated
    @Override
    public boolean hasBySymbol(String symbol) {
        return super.hasBySymbol(symbol);
    }

    @Override
    public boolean has(String s) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (this.ts.get(i).equals(s)) {
                return true;
            }
        }
        return false;
    }

    ///////////////////////////////////////////////////////
    //
    // index方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * index
     **************************************************/
    @Deprecated
    @Override
    public int indexBySymbol(String symbol) {
        return index(symbol);
    }

    @Override
    public int index(String s) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (this.ts.get(i).equals(s)) {
                return i;
            }
        }
        return -1;
    }

    /**************************************************
     * allIndex
     **************************************************/
    @Deprecated
    @Override
    public IntegerTs allIndexBySymbol(String symbol) {
        return allIndex(symbol);
    }

    @Override
    public IntegerTs allIndex(String s) {
        int count = count();
        IntegerTs integerTs = new IntegerTs();
        for (int i = 0; i < count; i++) {
            if (this.ts.get(i).equals(s)) {
                integerTs.add(i);
            }
        }
        return integerTs;
    }

    ///////////////////////////////////////////////////////
    //
    // replace方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * replace
     **************************************************/
    @Deprecated
    @Override
    public StringTs replaceBySymbol(String symbol, String target) {
        return replace(symbol, target);
    }

    @Override
    public StringTs replace(String src, String target) {
        int firstIndex = index(src);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        }
        return this;
    }

    @Deprecated
    @Override
    public StringTs replace(String target) {
        return super.replace(target);
    }

    @Deprecated
    @Override
    public StringTs replace(String... targets) {
        return super.replace(targets);
    }

    @Deprecated
    @Override
    public StringTs replace(List<String> targets) {
        return super.replace(targets);
    }

    @Deprecated
    @Override
    public StringTs replace(StringTs targets) {
        return super.replace(targets);
    }

    /**************************************************
     * replaceAll
     **************************************************/
    @Deprecated
    @Override
    public StringTs replaceAllBySymbol(String symbol, String target) {
        return super.replaceAllBySymbol(symbol, target);
    }

    @Deprecated
    @Override
    public StringTs replaceAll(String target) {
        return super.replaceAll(target);
    }

    @Deprecated
    @Override
    public StringTs replaceAll(String... targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public StringTs replaceAll(List<String> targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public StringTs replaceAll(StringTs targets) {
        return super.replaceAll(targets);
    }

    /**************************************************
     * replaceOrAdd
     **************************************************/
    @Deprecated
    @Override
    public StringTs replaceOrAddBySymbol(String symbol, String target) {
        return super.replaceOrAddBySymbol(symbol, target);
    }

    @Deprecated
    @Override
    public StringTs replaceOrAdd(String target) {
        return super.replaceOrAdd(target);
    }

    @Deprecated
    @Override
    public StringTs replaceOrAdd(String... targets) {
        return super.replaceOrAdd(targets);
    }

    @Deprecated
    @Override
    public StringTs replaceOrAdd(List<String> targets) {
        return super.replaceOrAdd(targets);
    }

    @Deprecated
    @Override
    public StringTs replaceOrAdd(StringTs targets) {
        return super.replaceOrAdd(targets);
    }

    /**************************************************
     * replaceAllOrAdd
     **************************************************/

    @Deprecated
    @Override
    public StringTs replaceAllOrAddBySymbol(String symbol, String target) {
        return super.replaceAllOrAddBySymbol(symbol, target);
    }

    @Deprecated
    @Override
    public StringTs replaceAllOrAdd(String target) {
        return super.replaceAllOrAdd(target);
    }

    @Deprecated
    @Override
    public StringTs replaceAllOrAdd(String... targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public StringTs replaceAllOrAdd(List<String> targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public StringTs replaceAllOrAdd(StringTs targets) {
        return super.replaceAllOrAdd(targets);
    }

    ///////////////////////////////////////////////////////
    //
    // delete方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * delete
     **************************************************/
    @Deprecated
    @Override
    public StringTs deleteBySymbol(String symbol) {
        return super.deleteBySymbol(symbol);
    }

    @Deprecated
    @Override
    public StringTs deleteBySymbol(String... symbols) {
        return super.deleteBySymbol(symbols);
    }

    @Deprecated
    @Override
    public StringTs deleteBySymbol(List<String> symbols) {
        return super.deleteBySymbol(symbols);
    }

    @Deprecated
    @Override
    public StringTs deleteBySymbol(StringTs symbolStringTs) {
        return super.deleteBySymbol(symbolStringTs);
    }

    @Override
    public StringTs delete(String target) {
        return super.delete(target);
    }

    @Override
    public StringTs delete(String... targets) {
        return super.delete(targets);
    }

    @Override
    public StringTs delete(List<String> targets) {
        return super.delete(targets);
    }

    @Override
    public StringTs delete(StringTs targets) {
        return super.delete(targets);
    }

    /**************************************************
     * deleteAll
     **************************************************/
    @Deprecated
    @Override
    public StringTs deleteAllBySymbol(String symbol) {
        return super.deleteAllBySymbol(symbol);
    }

    @Deprecated
    @Override
    public StringTs deleteAllBySymbol(String... symbols) {
        return super.deleteAllBySymbol(symbols);
    }

    @Deprecated
    @Override
    public StringTs deleteAllBySymbol(List<String> symbols) {
        return super.deleteAllBySymbol(symbols);
    }

    @Deprecated
    @Override
    public StringTs deleteAllBySymbol(StringTs symbolStringTs) {
        return super.deleteAllBySymbol(symbolStringTs);
    }

    @Override
    public StringTs deleteAll(String target) {
        return super.deleteAll(target);
    }

    @Override
    public StringTs deleteAll(String... targets) {
        return super.deleteAll(targets);
    }

    @Override
    public StringTs deleteAll(List<String> targets) {
        return super.deleteAll(targets);
    }

    @Override
    public StringTs deleteAll(StringTs targets) {
        return super.deleteAll(targets);
    }

    ///////////////////////////////////////////////////////
    //
    // near方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * nearIndex下一个优先
     **************************************************/
    @Deprecated
    @Override
    public Ts.NearIndex nearIndexWhenNextPriorityBySymbol(String symbol) {
        return super.nearIndexWhenNextPriorityBySymbol(symbol);
    }

    /**************************************************
     * nearData下一个优先
     **************************************************/
    @Deprecated
    @Override
    public String nearDataWhenNextPriorityBySymbol(String symbol) {
        return super.nearDataWhenNextPriorityBySymbol(symbol);
    }

    /**************************************************
     * nearIndex上一个优先
     **************************************************/
    @Deprecated
    @Override
    public Ts.NearIndex nearIndexWhenPrePriorityBySymbol(String symbol) {
        return super.nearIndexWhenPrePriorityBySymbol(symbol);
    }

    /**************************************************
     * nearData下一个优先
     **************************************************/
    @Deprecated
    @Override
    public String nearDataWhenPrePriorityBySymbol(String symbol) {
        return super.nearDataWhenPrePriorityBySymbol(symbol);
    }
}
