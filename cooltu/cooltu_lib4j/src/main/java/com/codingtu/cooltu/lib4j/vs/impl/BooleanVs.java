package com.codingtu.cooltu.lib4j.vs.impl;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.vs.CoreVs;
import com.codingtu.cooltu.lib4j.vs.Vs;

import java.util.List;

public class BooleanVs extends CoreVs<Boolean, BooleanVs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public BooleanVs() {
    }

    public BooleanVs(List<Boolean> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取valueSymbol
    //
    ///////////////////////////////////////////////////////
    @Override
    protected String valueSymbol(Boolean aBoolean) {
        return aBoolean + "";
    }

    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////

    public BooleanVs add_boolean(boolean... booleans) {
        int count = CountTool.count(booleans);
        for (int i = 0; i < count; i++) {
            this.ts.add(booleans[i]);
        }
        return this;
    }

    ///////////////////////////////////////////////////////
    //
    // createThis
    //
    ///////////////////////////////////////////////////////
    public BooleanVs createThis_boolean(boolean... booleans) {
        BooleanVs booleanVs = new BooleanVs();
        booleanVs.add_boolean(booleans);
        return booleanVs;
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
    public Boolean getFirst(Vs.IsThisOne<Boolean> isThisOne) {
        return super.getFirst(isThisOne);
    }

    @Deprecated
    @Override
    public Boolean getFirstByValueSymbol(String valueSymbol) {
        return super.getFirstByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public Boolean getFirst(Boolean aBoolean) {
        return super.getFirst(aBoolean);
    }

    /**************************************************
     * getAll
     **************************************************/
    @Deprecated
    @Override
    public BooleanVs getAll(Vs.IsThisOne<Boolean> isThisOne) {
        return super.getAll(isThisOne);
    }

    @Deprecated
    @Override
    public BooleanVs getAllByValueSymbol(String valueSymbol) {
        return super.getAllByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public BooleanVs getAll(Boolean aBoolean) {
        return super.getAll(aBoolean);
    }

    ///////////////////////////////////////////////////////
    //
    // has方法
    //
    ///////////////////////////////////////////////////////

    @Deprecated
    @Override
    public boolean hasByValueSymbol(String valueSymbol) {
        return super.hasByValueSymbol(valueSymbol);
    }

    @Override
    public boolean has(Boolean aBoolean) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (ts.get(i) == aBoolean) {
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
        return super.firstIndexByValueSymbol(valueSymbol);
    }

    @Override
    public int firstIndex(Boolean aBoolean) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (aBoolean == this.ts.get(i)) {
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
        return super.allIndexByValueSymbol(valueSymbol);
    }

    @Override
    public IntegerVs allIndex(Boolean aBoolean) {
        int count = count();
        IntegerVs integerVs = new IntegerVs();
        for (int i = 0; i < count; i++) {
            if (this.ts.get(i) == aBoolean) {
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
    public BooleanVs replaceFirstByValueSymbol(String valueSymbol, Boolean target) {
        return super.replaceFirstByValueSymbol(valueSymbol, target);
    }

    @Deprecated
    @Override
    public BooleanVs replaceFirst(Boolean symbolT, Boolean target) {
        return super.replaceFirst(symbolT, target);
    }

    @Deprecated
    @Override
    public BooleanVs replaceFirst(Boolean target) {
        return super.replaceFirst(target);
    }

    @Deprecated
    @Override
    public BooleanVs replaceFirst(Boolean... targets) {
        return super.replaceFirst(targets);
    }

    @Deprecated
    @Override
    public BooleanVs replaceFirst(List<Boolean> targets) {
        return super.replaceFirst(targets);
    }

    @Deprecated
    @Override
    public BooleanVs replaceFirst(BooleanVs targetVs) {
        return super.replaceFirst(targetVs);
    }


    /**************************************************
     * replaceAll
     **************************************************/
    @Deprecated
    @Override
    public BooleanVs replaceAllByValueSymbol(String valueSymbol, Boolean target) {
        return super.replaceAllByValueSymbol(valueSymbol, target);
    }

    @Deprecated
    @Override
    public BooleanVs replaceAll(Boolean symbolT, Boolean target) {
        return super.replaceAll(symbolT, target);
    }

    @Deprecated
    @Override
    public BooleanVs replaceAll(Boolean target) {
        return super.replaceAll(target);
    }

    @Deprecated
    @Override
    public BooleanVs replaceAll(Boolean... targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public BooleanVs replaceAll(List<Boolean> targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public BooleanVs replaceAll(BooleanVs targetVs) {
        return super.replaceAll(targetVs);
    }

    /**************************************************
     * replaceFirstOrAdd
     **************************************************/

    @Deprecated
    @Override
    public BooleanVs replaceFirstOrAddByValueSymbol(String valueSymbol, Boolean target) {
        return super.replaceFirstOrAddByValueSymbol(valueSymbol, target);
    }

    @Deprecated
    @Override
    public BooleanVs replaceFirstOrAdd(Boolean symbolT, Boolean target) {
        return super.replaceFirstOrAdd(symbolT, target);
    }

    @Deprecated
    @Override
    public BooleanVs replaceFirstOrAdd(Boolean target) {
        return super.replaceFirstOrAdd(target);
    }

    @Deprecated
    @Override
    public BooleanVs replaceFirstOrAdd(Boolean... targets) {
        return super.replaceFirstOrAdd(targets);
    }

    @Deprecated
    @Override
    public BooleanVs replaceFirstOrAdd(List<Boolean> targets) {
        return super.replaceFirstOrAdd(targets);
    }

    @Deprecated
    @Override
    public BooleanVs replaceFirstOrAdd(BooleanVs targetVs) {
        return super.replaceFirstOrAdd(targetVs);
    }

    /**************************************************
     * replaceAllOrAdd
     **************************************************/
    @Deprecated
    @Override
    public BooleanVs replaceAllOrAddByValueSymbol(String valueSymbol, Boolean target) {
        return super.replaceAllOrAddByValueSymbol(valueSymbol, target);
    }

    @Deprecated
    @Override
    public BooleanVs replaceAllOrAdd(Boolean symboleT, Boolean target) {
        return super.replaceAllOrAdd(symboleT, target);
    }

    @Deprecated
    @Override
    public BooleanVs replaceAllOrAdd(Boolean target) {
        return super.replaceAllOrAdd(target);
    }

    @Deprecated
    @Override
    public BooleanVs replaceAllOrAdd(Boolean... targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public BooleanVs replaceAllOrAdd(List<Boolean> targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public BooleanVs replaceAllOrAdd(BooleanVs targetVs) {
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
    public BooleanVs deleteFirstByValueSymbol(String valueSymbol) {
        return super.deleteFirstByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public BooleanVs deleteFirstByValueSymbol(String... valueSymbols) {
        return super.deleteFirstByValueSymbol(valueSymbols);
    }

    @Deprecated
    @Override
    public BooleanVs deleteFirstByValueSymbol(List<String> valueSymbols) {
        return super.deleteFirstByValueSymbol(valueSymbols);
    }

    @Deprecated
    @Override
    public BooleanVs deleteFirstByValueSymbol(StringVs valueSymbolVs) {
        return super.deleteFirstByValueSymbol(valueSymbolVs);
    }

    @Deprecated
    @Override
    public BooleanVs deleteFirst(Boolean target) {
        int firstIndex = firstIndex(target);
        if (firstIndex >= 0) {
            this.ts.remove(firstIndex);
        }
        return this;
    }

    @Deprecated
    @Override
    public BooleanVs deleteFirst(Boolean... targets) {
        return super.deleteFirst(targets);
    }

    @Deprecated
    @Override
    public BooleanVs deleteFirst(List<Boolean> targets) {
        return super.deleteFirst(targets);
    }

    @Deprecated
    @Override
    public BooleanVs deleteFirst(BooleanVs targetVs) {
        return super.deleteFirst(targetVs);
    }

    /**************************************************
     * deleteAll
     **************************************************/
    @Deprecated
    @Override
    public BooleanVs deleteAllByValueSymbol(String valueSymbol) {
        return super.deleteAllByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public BooleanVs deleteAllByValueSymbol(String... valueSymbols) {
        return super.deleteAllByValueSymbol(valueSymbols);
    }

    @Deprecated
    @Override
    public BooleanVs deleteAllByValueSymbol(List<String> valueSymbols) {
        return super.deleteAllByValueSymbol(valueSymbols);
    }

    @Deprecated
    @Override
    public BooleanVs deleteAllByValueSymbol(StringVs valueSymbolVs) {
        return super.deleteAllByValueSymbol(valueSymbolVs);
    }

    @Deprecated
    @Override
    public BooleanVs deleteAll(Boolean target) {
        return super.deleteAll(target);
    }

    @Deprecated
    @Override
    public BooleanVs deleteAll(Boolean... targets) {
        return super.deleteAll(targets);
    }

    @Deprecated
    @Override
    public BooleanVs deleteAll(List<Boolean> targets) {
        return super.deleteAll(targets);
    }

    @Deprecated
    @Override
    public BooleanVs deleteAll(BooleanVs targetVs) {
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
    public Vs.NeighborIndex obtainNeighborIndexWhenNextPriority(Vs.IsThisOne<Boolean> isThisOne) {
        return super.obtainNeighborIndexWhenNextPriority(isThisOne);
    }


    @Deprecated
    @Override
    public Vs.NeighborIndex obtainNeighborIndexWhenNextPriorityByValueSymbol(String valueSymbol) {
        return super.obtainNeighborIndexWhenNextPriorityByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public Vs.NeighborIndex obtainNeighborIndexWhenNextPriority(Boolean aBoolean) {
        return super.obtainNeighborIndexWhenNextPriority(aBoolean);
    }


    /**************************************************
     * NeighborData下一个优先
     **************************************************/
    @Deprecated
    @Override
    public Boolean obtainNeighborDataWhenNextPriority(Vs.IsThisOne<Boolean> isThisOne) {
        return super.obtainNeighborDataWhenNextPriority(isThisOne);
    }

    @Deprecated
    @Override
    public Boolean obtainNeighborDataWhenNextPriorityByVauleSymbol(String valueSymbol) {
        return super.obtainNeighborDataWhenNextPriorityByVauleSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public Boolean obtainNeighborDataWhenNextPriority(Boolean aBoolean) {
        return super.obtainNeighborDataWhenNextPriority(aBoolean);
    }


    /**************************************************
     * NeighborIndex上一个优先
     **************************************************/
    @Deprecated
    @Override
    public Vs.NeighborIndex obtainNeighborIndexWhenPrePriority(Vs.IsThisOne<Boolean> isThisOne) {
        return super.obtainNeighborIndexWhenPrePriority(isThisOne);
    }

    @Deprecated
    @Override
    public Vs.NeighborIndex obtainNeighborIndexWhenPrePriorityByValueSymbol(String valueSymbol) {
        return super.obtainNeighborIndexWhenPrePriorityByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public Vs.NeighborIndex obtainNeighborIndexWhenPrePriority(Boolean aBoolean) {
        return super.obtainNeighborIndexWhenPrePriority(aBoolean);
    }

    /**************************************************
     * NeighborData下一个优先
     **************************************************/
    @Deprecated
    @Override
    public Boolean obtainNeighborDataWhenPrePriority(Vs.IsThisOne<Boolean> isThisOne) {
        return super.obtainNeighborDataWhenPrePriority(isThisOne);
    }

    @Deprecated
    @Override
    public Boolean obtainNeighborDataWhenPrePriorityByValueSymbol(String valueSymbol) {
        return super.obtainNeighborDataWhenPrePriorityByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public Boolean obtainNeighborDataWhenPrePriority(Boolean aBoolean) {
        return super.obtainNeighborDataWhenPrePriority(aBoolean);
    }
}
