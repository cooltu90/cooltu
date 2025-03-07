package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.msthread.SubThread;

import java.util.ArrayList;
import java.util.List;

public class BooleanVs extends CoreVs<Boolean, BooleanVs> {
    @Override
    protected String valueSymbol(Boolean aBoolean) {
        return aBoolean + "";
    }

    /**************************************************
     * add
     **************************************************/
    public BooleanVs addBools(boolean... srcVs) {
        int count = CountTool.count(srcVs);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                this.ts.add(srcVs[i]);
            }
        }
        return this;
    }

    /**************************************************
     *
     **************************************************/
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

    /**************************************************
     *
     **************************************************/
    @Deprecated
    @Override
    public boolean hasByValueSymbol(String valueSymbol) {
        return super.hasByValueSymbol(valueSymbol);
    }

    /**************************************************
     *
     **************************************************/
    @Deprecated
    @Override
    public int firstIndexByValueSymbol(String valueSymbol) {
        return super.firstIndexByValueSymbol(valueSymbol);
    }


    @Deprecated
    @Override
    public IntegerVs allIndexByValueSymbol(String valueSymbol) {
        return super.allIndexByValueSymbol(valueSymbol);
    }

    /**************************************************
     *
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
    public BooleanVs replaceFirst(BooleanVs booleanVs) {
        return super.replaceFirst(booleanVs);
    }

    @Deprecated
    @Override
    public BooleanVs replaceAllByValueSymbol(String valueSymbol, Boolean target) {
        return super.replaceAllByValueSymbol(valueSymbol, target);
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
    public BooleanVs replaceAll(BooleanVs booleanVs) {
        return super.replaceAll(booleanVs);
    }

    @Deprecated
    @Override
    public BooleanVs replaceAll(Boolean symbolT, Boolean target) {
        return super.replaceAll(symbolT, target);
    }

    @Deprecated
    @Override
    public BooleanVs replaceFirstOrAddByValueSymbol(String valueSymbol, Boolean target) {
        return super.replaceFirstOrAddByValueSymbol(valueSymbol, target);
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
    public BooleanVs replaceFirstOrAdd(BooleanVs booleanVs) {
        return super.replaceFirstOrAdd(booleanVs);
    }

    @SubThread
    @Override
    public BooleanVs replaceFirstOrAdd(Boolean symbolT, Boolean target) {
        return super.replaceFirstOrAdd(symbolT, target);
    }

    @Deprecated
    @Override
    public BooleanVs replaceAllOrAddByValueSymbol(String valueSymbol, Boolean target) {
        return super.replaceAllOrAddByValueSymbol(valueSymbol, target);
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
    public BooleanVs replaceAllOrAdd(BooleanVs booleanVs) {
        return super.replaceAllOrAdd(booleanVs);
    }

    @Deprecated
    @Override
    public BooleanVs replaceAllOrAdd(Boolean symboleT, Boolean target) {
        return super.replaceAllOrAdd(symboleT, target);
    }

    /**************************************************
     *
     **************************************************/
    @Deprecated
    @Override
    public BooleanVs deleteFirstByValueSymbol(String valueSymbol) {
        return super.deleteFirstByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public BooleanVs deleteFirst(Boolean target) {
        return super.deleteFirst(target);
    }

    @Deprecated
    @Override
    public BooleanVs deleteAllByValueSymbol(String valueSymbol) {
        return super.deleteAllByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public BooleanVs deleteAll(Boolean target) {
        return super.deleteAll(target);
    }

}
