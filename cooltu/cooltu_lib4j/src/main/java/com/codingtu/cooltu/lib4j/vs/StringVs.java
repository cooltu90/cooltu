package com.codingtu.cooltu.lib4j.vs;

import java.util.ArrayList;
import java.util.List;

public class StringVs extends CoreVs<String, StringVs> {

    public StringVs() {
    }

    public StringVs(List<String> list) {
        super(list);
    }

    @Override
    protected String valueSymbol(String s) {
        return s;
    }

    /**************************************************
     * get
     **************************************************/
    @Deprecated
    @Override
    public String getFirstByValueSymbol(String valueId) {
        return super.getFirstByValueSymbol(valueId);
    }

    @Deprecated
    @Override
    public String getFirst(String s) {
        return super.getFirst(s);
    }

    @Deprecated
    @Override
    public String getFirst(Vs.IsThisOne<String> isThisOne) {
        return super.getFirst(isThisOne);
    }

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

    @Deprecated
    @Override
    public StringVs getAll(Vs.IsThisOne<String> isThisOne) {
        return super.getAll(isThisOne);
    }

    /**************************************************
     * has
     **************************************************/
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

    /**************************************************
     * index
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


    @Deprecated
    @Override
    public IntegerVs allIndexByValueSymbol(String valueSymbol) {
        return allIndex(valueSymbol);
    }

    @Override
    public IntegerVs allIndex(String s) {
        int count = count();
        List<Integer> indexs = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (this.ts.get(i).equals(s)) {
                indexs.add(i);
            }
        }
        return Vs.ints(indexs);
    }

    /**************************************************
     * replace
     **************************************************/

    @Deprecated
    @Override
    public StringVs replaceFirstByValueSymbol(String valueSymbol, String target) {
        return super.replaceFirstByValueSymbol(valueSymbol, target);
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
    public StringVs replaceFirst(StringVs stringVs) {
        return super.replaceFirst(stringVs);
    }

    @Deprecated
    @Override
    public StringVs replaceAllByValueSymbol(String valueSymbol, String target) {
        return super.replaceAllByValueSymbol(valueSymbol, target);
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
     * replaceOrAdd
     **************************************************/
    @Deprecated
    @Override
    public StringVs replaceFirstOrAddByValueSymbol(String valueSymbol, String target) {
        return super.replaceFirstOrAddByValueSymbol(valueSymbol, target);
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
    public StringVs replaceFirstOrAdd(StringVs stringVs) {
        return super.replaceFirstOrAdd(stringVs);
    }

    @Deprecated
    @Override
    public StringVs replaceAllOrAddByValueSymbol(String valueSymbol, String target) {
        return super.replaceAllOrAddByValueSymbol(valueSymbol, target);
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
    public StringVs replaceAllOrAdd(StringVs stringVs) {
        return super.replaceAllOrAdd(stringVs);
    }

    /**************************************************
     * delete
     **************************************************/
    @Deprecated
    @Override
    public StringVs deleteFirstByValueSymbol(String valueSymbol) {
        return super.deleteFirstByValueSymbol(valueSymbol);
    }


    @Deprecated
    @Override
    public StringVs deleteAllByValueSymbol(String valueSymbol) {
        return super.deleteAllByValueSymbol(valueSymbol);
    }

    /**************************************************
     * neighbor
     **************************************************/
    //下一个优先
    @Deprecated
    @Override
    public Vs.NeighborIndex obtainNeighborIndexWhenNextPriorityByValueSymbol(String valueSymbol) {
        return super.obtainNeighborIndexWhenNextPriorityByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public String obtainNeighborDataWhenNextPriorityByVauleSymbol(String valueSymbol) {
        return super.obtainNeighborDataWhenNextPriorityByVauleSymbol(valueSymbol);
    }

    //上一个优先
    @Deprecated
    @Override
    public Vs.NeighborIndex obtainNeighborIndexWhenPrePriorityByValueSymbol(String valueSymbol) {
        return super.obtainNeighborIndexWhenPrePriorityByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public String obtainNeighborDataWhenPrePriorityByValueSymbol(String valueSymbol) {
        return super.obtainNeighborDataWhenPrePriorityByValueSymbol(valueSymbol);
    }

}
