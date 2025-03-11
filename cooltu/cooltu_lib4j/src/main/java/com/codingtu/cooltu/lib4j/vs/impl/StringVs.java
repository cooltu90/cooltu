package com.codingtu.cooltu.lib4j.vs.impl;

import com.codingtu.cooltu.lib4j.vs.CoreVs;
import com.codingtu.cooltu.lib4j.vs.Vs;

import java.util.List;

public class StringVs extends CoreVs<String, StringVs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public StringVs() {
    }

    public StringVs(List<String> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取valueSymbol
    //
    ///////////////////////////////////////////////////////

    @Override
    protected String valueSymbol(String s) {
        return s;
    }

    ///////////////////////////////////////////////////////
    //
    // get方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * getFirst
     **************************************************/
    @Deprecated
    @Override
    public String getFirstByValueSymbol(String valueSymbol) {
        return super.getFirstByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public String getFirst(String s) {
        return super.getFirst(s);
    }

    /**************************************************
     * getAll
     **************************************************/
    @Deprecated
    @Override
    public StringVs getAllByValueSymbol(String valueSymbol) {
        return super.getAllByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public StringVs getAll(String s) {
        return super.getAll(s);
    }

    ///////////////////////////////////////////////////////
    //
    // has方法
    //
    ///////////////////////////////////////////////////////
    @Deprecated
    @Override
    public boolean hasByValueSymbol(String valueId) {
        return super.hasByValueSymbol(valueId);
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
     * firstIndex
     **************************************************/
    @Deprecated
    @Override
    public int firstIndexByValueSymbol(String valueSymbol) {
        return firstIndex(valueSymbol);
    }

    @Override
    public int firstIndex(String s) {
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
    public IntegerVs allIndexByValueSymbol(String valueSymbol) {
        return allIndex(valueSymbol);
    }

    @Override
    public IntegerVs allIndex(String s) {
        int count = count();
        IntegerVs integerVs = new IntegerVs();
        for (int i = 0; i < count; i++) {
            if (this.ts.get(i).equals(s)) {
                integerVs.add(i);
            }
        }
        return integerVs;
    }

    ///////////////////////////////////////////////////////
    //
    // replace方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * replaceFirst
     **************************************************/
    @Deprecated
    @Override
    public StringVs replaceFirstByValueSymbol(String valueSymbol, String target) {
        return replaceFirst(valueSymbol, target);
    }

    @Override
    public StringVs replaceFirst(String src, String target) {
        int firstIndex = firstIndex(src);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        }
        return this;
    }

    @Deprecated
    @Override
    public StringVs replaceFirst(String target) {
        return super.replaceFirst(target);
    }

    @Deprecated
    @Override
    public StringVs replaceFirst(String... targets) {
        return super.replaceFirst(targets);
    }

    @Deprecated
    @Override
    public StringVs replaceFirst(List<String> targets) {
        return super.replaceFirst(targets);
    }

    @Deprecated
    @Override
    public StringVs replaceFirst(StringVs targetVs) {
        return super.replaceFirst(targetVs);
    }

    /**************************************************
     * replaceAll
     **************************************************/
    @Deprecated
    @Override
    public StringVs replaceAllByValueSymbol(String valueSymbol, String target) {
        return super.replaceAllByValueSymbol(valueSymbol, target);
    }

    @Deprecated
    @Override
    public StringVs replaceAll(String target) {
        return super.replaceAll(target);
    }

    @Deprecated
    @Override
    public StringVs replaceAll(String... targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public StringVs replaceAll(List<String> targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public StringVs replaceAll(StringVs stringVs) {
        return super.replaceAll(stringVs);
    }

    /**************************************************
     * replaceFirstOrAdd
     **************************************************/
    @Deprecated
    @Override
    public StringVs replaceFirstOrAddByValueSymbol(String valueSymbol, String target) {
        return super.replaceFirstOrAddByValueSymbol(valueSymbol, target);
    }

    @Deprecated
    @Override
    public StringVs replaceFirstOrAdd(String target) {
        return super.replaceFirstOrAdd(target);
    }

    @Deprecated
    @Override
    public StringVs replaceFirstOrAdd(String... targets) {
        return super.replaceFirstOrAdd(targets);
    }

    @Deprecated
    @Override
    public StringVs replaceFirstOrAdd(List<String> targets) {
        return super.replaceFirstOrAdd(targets);
    }

    @Deprecated
    @Override
    public StringVs replaceFirstOrAdd(StringVs targetVs) {
        return super.replaceFirstOrAdd(targetVs);
    }

    /**************************************************
     * replaceAllOrAdd
     **************************************************/

    @Deprecated
    @Override
    public StringVs replaceAllOrAddByValueSymbol(String valueSymbol, String target) {
        return super.replaceAllOrAddByValueSymbol(valueSymbol, target);
    }

    @Deprecated
    @Override
    public StringVs replaceAllOrAdd(String target) {
        return super.replaceAllOrAdd(target);
    }

    @Deprecated
    @Override
    public StringVs replaceAllOrAdd(String... targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public StringVs replaceAllOrAdd(List<String> targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public StringVs replaceAllOrAdd(StringVs targetVs) {
        return super.replaceAllOrAdd(targetVs);
    }

    ///////////////////////////////////////////////////////
    //
    // delete方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * deleteFirst
     **************************************************/
    @Deprecated
    @Override
    public StringVs deleteFirstByValueSymbol(String valueSymbol) {
        return super.deleteFirstByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public StringVs deleteFirstByValueSymbol(String... valueSymbols) {
        return super.deleteFirstByValueSymbol(valueSymbols);
    }

    @Deprecated
    @Override
    public StringVs deleteFirstByValueSymbol(List<String> valueSymbols) {
        return super.deleteFirstByValueSymbol(valueSymbols);
    }

    @Deprecated
    @Override
    public StringVs deleteFirstByValueSymbol(StringVs valueSymbolVs) {
        return super.deleteFirstByValueSymbol(valueSymbolVs);
    }

    @Override
    public StringVs deleteFirst(String target) {
        return super.deleteFirst(target);
    }

    @Override
    public StringVs deleteFirst(String... targets) {
        return super.deleteFirst(targets);
    }

    @Override
    public StringVs deleteFirst(List<String> targets) {
        return super.deleteFirst(targets);
    }

    @Override
    public StringVs deleteFirst(StringVs targetVs) {
        return super.deleteFirst(targetVs);
    }

    /**************************************************
     * deleteAll
     **************************************************/
    @Deprecated
    @Override
    public StringVs deleteAllByValueSymbol(String valueSymbol) {
        return super.deleteAllByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public StringVs deleteAllByValueSymbol(String... valueSymbols) {
        return super.deleteAllByValueSymbol(valueSymbols);
    }

    @Deprecated
    @Override
    public StringVs deleteAllByValueSymbol(List<String> valueSymbols) {
        return super.deleteAllByValueSymbol(valueSymbols);
    }

    @Deprecated
    @Override
    public StringVs deleteAllByValueSymbol(StringVs valueSymbolVs) {
        return super.deleteAllByValueSymbol(valueSymbolVs);
    }

    @Override
    public StringVs deleteAll(String target) {
        return super.deleteAll(target);
    }

    @Override
    public StringVs deleteAll(String... targets) {
        return super.deleteAll(targets);
    }

    @Override
    public StringVs deleteAll(List<String> targets) {
        return super.deleteAll(targets);
    }

    @Override
    public StringVs deleteAll(StringVs targetVs) {
        return super.deleteAll(targetVs);
    }

    ///////////////////////////////////////////////////////
    //
    // neighbor方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * NeighborIndex下一个优先
     **************************************************/
    @Deprecated
    @Override
    public Vs.NeighborIndex obtainNeighborIndexWhenNextPriorityByValueSymbol(String valueSymbol) {
        return super.obtainNeighborIndexWhenNextPriorityByValueSymbol(valueSymbol);
    }

    /**************************************************
     * NeighborData下一个优先
     **************************************************/
    @Deprecated
    @Override
    public String obtainNeighborDataWhenNextPriorityByVauleSymbol(String valueSymbol) {
        return super.obtainNeighborDataWhenNextPriorityByVauleSymbol(valueSymbol);
    }

    /**************************************************
     * NeighborIndex上一个优先
     **************************************************/
    @Deprecated
    @Override
    public Vs.NeighborIndex obtainNeighborIndexWhenPrePriorityByValueSymbol(String valueSymbol) {
        return super.obtainNeighborIndexWhenPrePriorityByValueSymbol(valueSymbol);
    }

    /**************************************************
     * NeighborData下一个优先
     **************************************************/
    @Deprecated
    @Override
    public String obtainNeighborDataWhenPrePriorityByValueSymbol(String valueSymbol) {
        return super.obtainNeighborDataWhenPrePriorityByValueSymbol(valueSymbol);
    }


}
