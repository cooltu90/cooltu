package com.codingtu.cooltu.lib4j.ts1.impl;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts1.CoreTs;
import com.codingtu.cooltu.lib4j.ts1.Ts;

import java.util.List;

public class BooleanTs extends CoreTs<Boolean, BooleanTs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public BooleanTs() {
    }

    public BooleanTs(List<Boolean> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取symbol
    //
    ///////////////////////////////////////////////////////
    @Override
    protected String obtainSymbol(Boolean aBoolean) {
        return aBoolean + "";
    }

    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////

    public BooleanTs add_boolean(boolean... booleans) {
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
    public BooleanTs createThis_boolean(boolean... booleans) {
        BooleanTs booleanTs = new BooleanTs();
        booleanTs.add_boolean(booleans);
        return booleanTs;
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
    public Boolean get(Ts.IsThisOne<Boolean> isThisOne) {
        return super.get(isThisOne);
    }

    @Deprecated
    @Override
    public Boolean getBySymbol(String symbol) {
        return super.getBySymbol(symbol);
    }

    @Deprecated
    @Override
    public Boolean get(Boolean aBoolean) {
        return super.get(aBoolean);
    }

    /**************************************************
     * getAll
     **************************************************/
    @Deprecated
    @Override
    public BooleanTs getAll(Ts.IsThisOne<Boolean> isThisOne) {
        return super.getAll(isThisOne);
    }

    @Deprecated
    @Override
    public BooleanTs getAllBySymbol(String symbol) {
        return super.getAllBySymbol(symbol);
    }

    @Deprecated
    @Override
    public BooleanTs getAll(Boolean aBoolean) {
        return super.getAll(aBoolean);
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
     * index
     **************************************************/
    @Deprecated
    @Override
    public int indexBySymbol(String symbol) {
        return super.indexBySymbol(symbol);
    }

    @Override
    public int index(Boolean aBoolean) {
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
    public IntegerTs allIndexBySymbol(String symbol) {
        return super.allIndexBySymbol(symbol);
    }

    @Override
    public IntegerTs allIndex(Boolean aBoolean) {
        int count = count();
        IntegerTs integerTs = new IntegerTs();
        for (int i = 0; i < count; i++) {
            if (this.ts.get(i) == aBoolean) {
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
    public BooleanTs replaceBySymbol(String symbol, Boolean target) {
        return super.replaceBySymbol(symbol, target);
    }

    @Deprecated
    @Override
    public BooleanTs replace(Boolean symbolT, Boolean target) {
        return super.replace(symbolT, target);
    }

    @Deprecated
    @Override
    public BooleanTs replace(Boolean target) {
        return super.replace(target);
    }

    @Deprecated
    @Override
    public BooleanTs replace(Boolean... targets) {
        return super.replace(targets);
    }

    @Deprecated
    @Override
    public BooleanTs replace(List<Boolean> targets) {
        return super.replace(targets);
    }

    @Deprecated
    @Override
    public BooleanTs replace(BooleanTs targets) {
        return super.replace(targets);
    }


    /**************************************************
     * replaceAll
     **************************************************/
    @Deprecated
    @Override
    public BooleanTs replaceAllBySymbol(String symbol, Boolean target) {
        return super.replaceAllBySymbol(symbol, target);
    }

    @Deprecated
    @Override
    public BooleanTs replaceAll(Boolean symbolT, Boolean target) {
        return super.replaceAll(symbolT, target);
    }

    @Deprecated
    @Override
    public BooleanTs replaceAll(Boolean target) {
        return super.replaceAll(target);
    }

    @Deprecated
    @Override
    public BooleanTs replaceAll(Boolean... targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public BooleanTs replaceAll(List<Boolean> targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public BooleanTs replaceAll(BooleanTs targets) {
        return super.replaceAll(targets);
    }

    /**************************************************
     * replaceOrAdd
     **************************************************/

    @Deprecated
    @Override
    public BooleanTs replaceOrAddBySymbol(String symbol, Boolean target) {
        return super.replaceOrAddBySymbol(symbol, target);
    }

    @Deprecated
    @Override
    public BooleanTs replaceOrAdd(Boolean symbolT, Boolean target) {
        return super.replaceOrAdd(symbolT, target);
    }

    @Deprecated
    @Override
    public BooleanTs replaceOrAdd(Boolean target) {
        return super.replaceOrAdd(target);
    }

    @Deprecated
    @Override
    public BooleanTs replaceOrAdd(Boolean... targets) {
        return super.replaceOrAdd(targets);
    }

    @Deprecated
    @Override
    public BooleanTs replaceOrAdd(List<Boolean> targets) {
        return super.replaceOrAdd(targets);
    }

    @Deprecated
    @Override
    public BooleanTs replaceOrAdd(BooleanTs targets) {
        return super.replaceOrAdd(targets);
    }

    /**************************************************
     * replaceAllOrAdd
     **************************************************/
    @Deprecated
    @Override
    public BooleanTs replaceAllOrAddBySymbol(String symbol, Boolean target) {
        return super.replaceAllOrAddBySymbol(symbol, target);
    }

    @Deprecated
    @Override
    public BooleanTs replaceAllOrAdd(Boolean symboleT, Boolean target) {
        return super.replaceAllOrAdd(symboleT, target);
    }

    @Deprecated
    @Override
    public BooleanTs replaceAllOrAdd(Boolean target) {
        return super.replaceAllOrAdd(target);
    }

    @Deprecated
    @Override
    public BooleanTs replaceAllOrAdd(Boolean... targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public BooleanTs replaceAllOrAdd(List<Boolean> targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public BooleanTs replaceAllOrAdd(BooleanTs targets) {
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
    public BooleanTs deleteBySymbol(String symbol) {
        return super.deleteBySymbol(symbol);
    }

    @Deprecated
    @Override
    public BooleanTs deleteBySymbol(String... symbols) {
        return super.deleteBySymbol(symbols);
    }

    @Deprecated
    @Override
    public BooleanTs deleteBySymbol(List<String> symbols) {
        return super.deleteBySymbol(symbols);
    }

    @Deprecated
    @Override
    public BooleanTs deleteBySymbol(StringTs symbolStringTs) {
        return super.deleteBySymbol(symbolStringTs);
    }

    @Deprecated
    @Override
    public BooleanTs delete(Boolean target) {
        int firstIndex = index(target);
        if (firstIndex >= 0) {
            this.ts.remove(firstIndex);
        }
        return this;
    }

    @Deprecated
    @Override
    public BooleanTs delete(Boolean... targets) {
        return super.delete(targets);
    }

    @Deprecated
    @Override
    public BooleanTs delete(List<Boolean> targets) {
        return super.delete(targets);
    }

    @Deprecated
    @Override
    public BooleanTs delete(BooleanTs targets) {
        return super.delete(targets);
    }

    /**************************************************
     * deleteAll
     **************************************************/
    @Deprecated
    @Override
    public BooleanTs deleteAllBySymbol(String symbol) {
        return super.deleteAllBySymbol(symbol);
    }

    @Deprecated
    @Override
    public BooleanTs deleteAllBySymbol(String... symbols) {
        return super.deleteAllBySymbol(symbols);
    }

    @Deprecated
    @Override
    public BooleanTs deleteAllBySymbol(List<String> symbols) {
        return super.deleteAllBySymbol(symbols);
    }

    @Deprecated
    @Override
    public BooleanTs deleteAllBySymbol(StringTs symbolStringTs) {
        return super.deleteAllBySymbol(symbolStringTs);
    }

    @Deprecated
    @Override
    public BooleanTs deleteAll(Boolean target) {
        return super.deleteAll(target);
    }

    @Deprecated
    @Override
    public BooleanTs deleteAll(Boolean... targets) {
        return super.deleteAll(targets);
    }

    @Deprecated
    @Override
    public BooleanTs deleteAll(List<Boolean> targets) {
        return super.deleteAll(targets);
    }

    @Deprecated
    @Override
    public BooleanTs deleteAll(BooleanTs targets) {
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
    public Ts.NearIndex nearIndexWhenNextPriority(Ts.IsThisOne<Boolean> isThisOne) {
        return super.nearIndexWhenNextPriority(isThisOne);
    }


    @Deprecated
    @Override
    public Ts.NearIndex nearIndexWhenNextPriorityBySymbol(String symbol) {
        return super.nearIndexWhenNextPriorityBySymbol(symbol);
    }

    @Deprecated
    @Override
    public Ts.NearIndex nearIndexWhenNextPriority(Boolean aBoolean) {
        return super.nearIndexWhenNextPriority(aBoolean);
    }


    /**************************************************
     * nearData下一个优先
     **************************************************/
    @Deprecated
    @Override
    public Boolean nearDataWhenNextPriority(Ts.IsThisOne<Boolean> isThisOne) {
        return super.nearDataWhenNextPriority(isThisOne);
    }

    @Deprecated
    @Override
    public Boolean nearDataWhenNextPriorityBySymbol(String symbol) {
        return super.nearDataWhenNextPriorityBySymbol(symbol);
    }

    @Deprecated
    @Override
    public Boolean nearDataWhenNextPriority(Boolean aBoolean) {
        return super.nearDataWhenNextPriority(aBoolean);
    }


    /**************************************************
     * nearIndex上一个优先
     **************************************************/
    @Deprecated
    @Override
    public Ts.NearIndex nearIndexWhenPrePriority(Ts.IsThisOne<Boolean> isThisOne) {
        return super.nearIndexWhenPrePriority(isThisOne);
    }

    @Deprecated
    @Override
    public Ts.NearIndex nearIndexWhenPrePriorityBySymbol(String symbol) {
        return super.nearIndexWhenPrePriorityBySymbol(symbol);
    }

    @Deprecated
    @Override
    public Ts.NearIndex nearIndexWhenPrePriority(Boolean aBoolean) {
        return super.nearIndexWhenPrePriority(aBoolean);
    }

    /**************************************************
     * nearData下一个优先
     **************************************************/
    @Deprecated
    @Override
    public Boolean nearDataWhenPrePriority(Ts.IsThisOne<Boolean> isThisOne) {
        return super.nearDataWhenPrePriority(isThisOne);
    }

    @Deprecated
    @Override
    public Boolean nearDataWhenPrePriorityBySymbol(String symbol) {
        return super.nearDataWhenPrePriorityBySymbol(symbol);
    }

    @Deprecated
    @Override
    public Boolean nearDataWhenPrePriority(Boolean aBoolean) {
        return super.nearDataWhenPrePriority(aBoolean);
    }

    ///////////////////////////////////////////////////////
    //
    // toArray
    //
    ///////////////////////////////////////////////////////
    public boolean[] to_booleans() {
        int count = count();
        boolean[] arrs = new boolean[count];
        for (int i = 0; i < count; i++) {
            arrs[i] = this.ts.get(i);
        }
        return arrs;
    }
}
