package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.function.ToDouble;
import com.codingtu.cooltu.lib4j.function.ToInt;
import com.codingtu.cooltu.lib4j.function.ToLong;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.tools.CountTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IntegerVs extends CoreVs<Integer, IntegerVs> {
    @Override
    protected String valueSymbol(Integer integer) {
        return String.valueOf(integer);
    }

    /**************************************************
     * add
     **************************************************/
    public IntegerVs addInts(int... ts) {
        int count = CountTool.count(ts);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                this.ts.add(ts[i]);
            }
        }
        return this;
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
            if (this.ts.get(i) == integer) {
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
            if (this.ts.get(i) == integer) {
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
        List<Integer> indexs = new ArrayList<>();
        int count = count();
        for (int i = 0; i < count; i++) {
            Integer integer1 = this.ts.get(i);
            if (integer1 == integer) {
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
    public IntegerVs replaceFirst(Integer... targets) {
        return super.replaceFirst(targets);
    }

    @Deprecated
    @Override
    public IntegerVs replaceFirst(List<Integer> targets) {
        return super.replaceFirst(targets);
    }

    @Deprecated
    @Override
    public IntegerVs replaceFirst(IntegerVs integerVs) {
        return super.replaceFirst(integerVs);
    }

    @Override
    public IntegerVs replaceFirst(Integer symbol, Integer target) {
        int firstIndex = firstIndex(symbol);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        }
        return this;
    }

    @Deprecated
    @Override
    public IntegerVs replaceFirstByValueSymbol(String valueSymbol, Integer target) {
        return super.replaceFirstByValueSymbol(valueSymbol, target);
    }

    @Deprecated
    @Override
    public IntegerVs replaceAll(Integer... targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public IntegerVs replaceAll(List<Integer> targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public IntegerVs replaceAll(IntegerVs integerVs) {
        return super.replaceAll(integerVs);
    }

    @Deprecated
    @Override
    public IntegerVs replaceAllByValueSymbol(String valueSymbol, Integer target) {
        return super.replaceAllByValueSymbol(valueSymbol, target);
    }

    @Override
    public IntegerVs replaceAll(Integer symbolT, Integer target) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (this.ts.get(i) == symbolT) {
                replaceByIndex(i, target);
            }
        }
        return this;
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
    public IntegerVs replaceFirstOrAdd(Integer... targets) {
        return super.replaceFirstOrAdd(targets);
    }

    @Deprecated
    @Override
    public IntegerVs replaceFirstOrAdd(List<Integer> targets) {
        return super.replaceFirstOrAdd(targets);
    }

    @Deprecated
    @Override
    public IntegerVs replaceFirstOrAdd(IntegerVs integerVs) {
        return super.replaceFirstOrAdd(integerVs);
    }

    @Override
    public IntegerVs replaceFirstOrAdd(Integer symbolT, Integer target) {
        int firstIndex = firstIndex(symbolT);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        } else if (target != null) {
            ts.add(target);
        }
        return this;
    }

    @Deprecated
    @Override
    public IntegerVs replaceAllOrAdd(Integer... targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public IntegerVs replaceAllOrAdd(List<Integer> targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public IntegerVs replaceAllOrAdd(IntegerVs integerVs) {
        return super.replaceAllOrAdd(integerVs);
    }

    @Deprecated
    @Override
    public IntegerVs replaceAllOrAddByValueSymbol(String valueSymbol, Integer target) {
        return super.replaceAllOrAddByValueSymbol(valueSymbol, target);
    }

    @Override
    public IntegerVs replaceAllOrAdd(Integer symboleT, Integer target) {
        int count = count();
        boolean isReplace = false;
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                if (this.ts.get(i) == symboleT) {
                    replaceByIndex(i, target);
                    isReplace = true;
                }
            }
        }
        if (!isReplace && target != null) {
            this.ts.add(target);
        }
        return this;
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
    public IntegerVs deleteFirst(Integer... targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                int firstIndex = firstIndex(targets[i]);
                if (firstIndex >= 0) {
                    this.ts.remove(firstIndex);
                }
            }
        }
        return this;
    }

    @Override
    public IntegerVs deleteFirst(List<Integer> targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                int firstIndex = firstIndex(targets.get(i));
                if (firstIndex >= 0) {
                    this.ts.remove(firstIndex);
                }
            }
        }
        return this;
    }

    @Override
    public IntegerVs deleteFirst(IntegerVs integerVs) {
        int count = integerVs.count();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                int firstIndex = firstIndex(integerVs.ts.get(i));
                if (firstIndex >= 0) {
                    this.ts.remove(firstIndex);
                }
            }
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
            t = this.ts.get(i);
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
    public Integer obtainNeighborDataWhenNextPriorityByVauleSymbol(String valueSymbol) {
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
    public Integer obtainNeighborDataWhenPrePriorityByValueSymbol(String valueSymbol) {
        return super.obtainNeighborDataWhenPrePriorityByValueSymbol(valueSymbol);
    }

    /**************************************************
     *
     **************************************************/
    public int[] toInts() {
        int count = count();
        int[] ints = new int[count];
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                ints[i] = this.ts.get(i);
            }
        }
        return ints;
    }

    /**************************************************
     *
     **************************************************/
    @Deprecated
    @Override
    public Map<String, Integer> toMap() {
        return super.toMap();
    }
}
