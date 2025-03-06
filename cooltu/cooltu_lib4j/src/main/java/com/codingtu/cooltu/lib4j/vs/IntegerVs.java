package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.function.ToDouble;
import com.codingtu.cooltu.lib4j.function.ToInt;
import com.codingtu.cooltu.lib4j.function.ToLong;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;

import java.util.ArrayList;
import java.util.List;

public class IntegerVs extends CoreVs<Integer, IntegerVs> {
    @Override
    protected String valueSymbol(Integer integer) {
        return String.valueOf(integer);
    }

    /**************************************************
     * get
     **************************************************/
    @Deprecated
    @Override
    public Integer getFirstByValueSymbol(String valueId) {
        return super.getFirstByValueSymbol(valueId);
    }

    @Deprecated
    @Override
    public Integer getFirst(Integer integer) {
        return super.getFirst(integer);
    }

    @Deprecated
    @Override
    public Integer getFirst(Vs.IsThisOne<Integer> isThisOne) {
        return super.getFirst(isThisOne);
    }

    @Deprecated
    @Override
    public IntegerVs getAll(Integer integer) {
        return super.getAll(integer);
    }

    @Deprecated
    @Override
    public IntegerVs getAllByValueSymbol(String valueSymbol) {
        return super.getAllByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public IntegerVs getAll(Vs.IsThisOne<Integer> isThisOne) {
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
    public boolean has(Integer integer) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (getByIndex(i) == integer) {
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
        return super.firstIndexByValueSymbol(valueSymbol);
    }

    @Override
    public int firstIndex(Integer integer) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (getByIndex(i) == integer) {
                return i;
            }
        }
        return -1;
    }

    @Deprecated
    @Override
    public IntegerVs allIndexByValueSymbol(String valueSymbol) {
        return super.allIndexByValueSymbol(valueSymbol);
    }

    @Override
    public IntegerVs allIndex(Integer integer) {
        IntegerVs integerVs = new IntegerVs();
        int count = count();
        for (int i = 0; i < count; i++) {
            Integer integer1 = getByIndex(i);
            if (integer1 == integer) {
                integerVs.add(i);
            }
        }
        return integerVs;
    }

    /**************************************************
     * replace
     **************************************************/
    @Deprecated
    @Override
    public IntegerVs replaceFirst(Integer target) {
        return super.replaceFirst(target);
    }

    @Deprecated
    @Override
    public IntegerVs replaceFirstByValueSymbol(String valueSymbol, Integer target) {
        return super.replaceFirstByValueSymbol(valueSymbol, target);
    }

    @Deprecated
    @Override
    public IntegerVs replaceAll(Integer target) {
        return super.replaceAll(target);
    }

    @Deprecated
    @Override
    public IntegerVs replaceAllByValueSymbol(String valueSymbol, Integer target) {
        return super.replaceAllByValueSymbol(valueSymbol, target);
    }


    /**************************************************
     * replaceOrAdd
     **************************************************/

    @Deprecated
    @Override
    public IntegerVs replaceFirstOrAddByValueSymbol(String valueSymbol, Integer target) {
        return super.replaceFirstOrAddByValueSymbol(valueSymbol, target);
    }

    @Deprecated
    @Override
    public IntegerVs replaceFirstOrAdd(Integer target) {
        return super.replaceFirstOrAdd(target);
    }

    @Deprecated
    @Override
    public IntegerVs replaceAllOrAdd(Integer target) {
        return super.replaceAllOrAdd(target);
    }

    @Deprecated
    @Override
    public IntegerVs replaceAllOrAddByValueSymbol(String valueSymbol, Integer target) {
        return super.replaceAllOrAddByValueSymbol(valueSymbol, target);
    }

    /**************************************************
     * delete
     **************************************************/
    @Deprecated
    @Override
    public IntegerVs deleteFirstByValueSymbol(String valueSymbol) {
        return super.deleteFirstByValueSymbol(valueSymbol);
    }

    @Override
    public IntegerVs deleteFirst(Integer target) {
        int firstIndex = firstIndex(target);
        if (firstIndex >= 0) {
            this.ts.remove(firstIndex);
        }
        return this;
    }

    @Deprecated
    @Override
    public IntegerVs deleteAllByValueSymbol(String valueSymbol) {
        return super.deleteAllByValueSymbol(valueSymbol);
    }

    @Override
    public IntegerVs deleteAll(Integer target) {
        List<Integer> newTs = new ArrayList<>();
        int count = count();
        Integer t = null;
        for (int i = 0; i < count; i++) {
            t = getByIndex(i);
            if (t != target) {
                newTs.add(t);
            }
        }
        this.ts.clear();
        this.ts.addAll(newTs);
        return this;
    }

    /**************************************************
     * maxMin
     **************************************************/
    @Deprecated
    @Override
    public MaxMin<Integer> maxMin(ToInt<Integer> toInt) {
        return super.maxMin(toInt);
    }

    @Deprecated
    @Override
    public MaxMin<Integer> maxMin(ToLong<Integer> toLong) {
        return super.maxMin(toLong);
    }

    @Deprecated
    @Override
    public MaxMin<Integer> maxMin(ToDouble<Integer> toDouble) {
        return super.maxMin(toDouble);
    }

    @Deprecated
    @Override
    public MaxMin<Integer> maxMin(Vs.NowMax<Integer> nowMax) {
        return super.maxMin(nowMax);
    }

    public MaxMin<Integer> maxMin() {
        return super.maxMin(new ToInt<Integer>() {
            @Override
            public int toInt(Integer integer) {
                return integer;
            }
        });
    }
}
