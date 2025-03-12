package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.function.ToDouble;
import com.codingtu.cooltu.lib4j.function.ToFloat;
import com.codingtu.cooltu.lib4j.function.ToInt;
import com.codingtu.cooltu.lib4j.function.ToLong;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.vs.impl.IntegerVs;
import com.codingtu.cooltu.lib4j.vs.impl.StringVs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NumVs<T, THIS extends CoreVs> extends CoreVs<T, THIS> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public NumVs() {
    }

    public NumVs(List<T> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取valueSymbol
    //
    ///////////////////////////////////////////////////////
    @Override
    protected String valueSymbol(T t) {
        return t + "";
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
    public T getFirstByValueSymbol(String valueSymbol) {
        return super.getFirstByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public T getFirst(T t) {
        return super.getFirst(t);
    }

    /**************************************************
     * getAll
     **************************************************/
    @Deprecated
    @Override
    public THIS getAllByValueSymbol(String valueSymbol) {
        return super.getAllByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public THIS getAll(T t) {
        return super.getAll(t);
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
    public boolean has(T t) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (this.ts.get(i) == t) {
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
    public int firstIndex(T t) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (t == this.ts.get(i)) {
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
    public IntegerVs allIndex(T t) {
        int count = count();
        IntegerVs integerVs = new IntegerVs();
        for (int i = 0; i < count; i++) {
            if (this.ts.get(i) == t) {
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
    public THIS replaceFirstByValueSymbol(String valueSymbol, T target) {
        return super.replaceFirstByValueSymbol(valueSymbol, target);
    }

    @Override
    public THIS replaceFirst(T symbolT, T target) {
        int firstIndex = firstIndex(symbolT);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        }
        return (THIS) this;
    }

    @Deprecated
    @Override
    public THIS replaceFirst(T target) {
        return super.replaceFirst(target);
    }

    @Deprecated
    @Override
    public THIS replaceFirst(T... targets) {
        return super.replaceFirst(targets);
    }

    @Deprecated
    @Override
    public THIS replaceFirst(List<T> targets) {
        return super.replaceFirst(targets);
    }

    @Deprecated
    @Override
    public THIS replaceFirst(THIS targetVs) {
        return super.replaceFirst(targetVs);
    }

    /**************************************************
     * replaceAll
     **************************************************/
    @Deprecated
    @Override
    public THIS replaceAllByValueSymbol(String valueSymbol, T target) {
        return super.replaceAllByValueSymbol(valueSymbol, target);
    }

    @Override
    public THIS replaceAll(T symbolT, T target) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (this.ts.get(i) == symbolT) {
                replaceByIndex(i, target);
            }
        }
        return (THIS) this;
    }

    @Deprecated
    @Override
    public THIS replaceAll(T target) {
        return super.replaceAll(target);
    }

    @Deprecated
    @Override
    public THIS replaceAll(T... targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public THIS replaceAll(List<T> targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public THIS replaceAll(THIS targetVs) {
        return super.replaceAll(targetVs);
    }

    /**************************************************
     * replaceFirstOrAdd
     **************************************************/
    @Deprecated
    @Override
    public THIS replaceFirstOrAddByValueSymbol(String valueSymbol, T target) {
        return super.replaceFirstOrAddByValueSymbol(valueSymbol, target);
    }

    @Override
    public THIS replaceFirstOrAdd(T symbolT, T target) {
        int firstIndex = firstIndex(symbolT);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        } else if (target != null) {
            ts.add(target);
        }
        return (THIS) this;
    }

    @Deprecated
    @Override
    public THIS replaceFirstOrAdd(T target) {
        return super.replaceFirstOrAdd(target);
    }

    @Deprecated
    @Override
    public THIS replaceFirstOrAdd(T... targets) {
        return super.replaceFirstOrAdd(targets);
    }

    @Deprecated
    @Override
    public THIS replaceFirstOrAdd(List<T> targets) {
        return super.replaceFirstOrAdd(targets);
    }

    @Deprecated
    @Override
    public THIS replaceFirstOrAdd(THIS targetVs) {
        return super.replaceFirstOrAdd(targetVs);
    }

    /**************************************************
     * replaceAllOrAdd
     **************************************************/
    @Deprecated
    @Override
    public THIS replaceAllOrAddByValueSymbol(String valueSymbol, T target) {
        return super.replaceAllOrAddByValueSymbol(valueSymbol, target);
    }

    @Override
    public THIS replaceAllOrAdd(T symboleT, T target) {
        int count = count();
        boolean isReplace = false;
        for (int i = 0; i < count; i++) {
            if (this.ts.get(i) == symboleT) {
                replaceByIndex(i, target);
                isReplace = true;
            }
        }
        if (!isReplace && target != null) {
            this.ts.add(target);
        }
        return (THIS) this;
    }

    @Deprecated
    @Override
    public THIS replaceAllOrAdd(T target) {
        return super.replaceAllOrAdd(target);
    }

    @Deprecated
    @Override
    public THIS replaceAllOrAdd(T... targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public THIS replaceAllOrAdd(List<T> targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public THIS replaceAllOrAdd(THIS targetVs) {
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
    public THIS deleteFirstByValueSymbol(String valueSymbol) {
        return super.deleteFirstByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public THIS deleteFirstByValueSymbol(String... valueSymbols) {
        return super.deleteFirstByValueSymbol(valueSymbols);
    }

    @Deprecated
    @Override
    public THIS deleteFirstByValueSymbol(List<String> valueSymbols) {
        return super.deleteFirstByValueSymbol(valueSymbols);
    }

    @Deprecated
    @Override
    public THIS deleteFirstByValueSymbol(StringVs valueSymbolVs) {
        return super.deleteFirstByValueSymbol(valueSymbolVs);
    }

    @Override
    public THIS deleteFirst(T target) {
        int firstIndex = firstIndex(target);
        if (firstIndex >= 0) {
            this.ts.remove(firstIndex);
        }
        return (THIS) this;
    }

    @Override
    protected THIS deleteFirst(Vs.EachGetter<T> getter) {
        if (getter != null) {
            int count = getter.count();
            if (count > 0) {
                T target = null;
                for (int i = 0; i < count; i++) {
                    target = getter.get(i);
                    if (target != null) {
                        deleteFirst(target);
                    }
                }
            }
        }
        return (THIS) this;
    }

    /**************************************************
     * deleteAll
     **************************************************/
    @Deprecated
    @Override
    public THIS deleteAllByValueSymbol(String valueSymbol) {
        return super.deleteAllByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public THIS deleteAllByValueSymbol(String... valueSymbols) {
        return super.deleteAllByValueSymbol(valueSymbols);
    }

    @Deprecated
    @Override
    public THIS deleteAllByValueSymbol(List<String> valueSymbols) {
        return super.deleteAllByValueSymbol(valueSymbols);
    }

    @Deprecated
    @Override
    public THIS deleteAllByValueSymbol(StringVs valueSymbolVs) {
        return super.deleteAllByValueSymbol(valueSymbolVs);
    }

    @Override
    public THIS deleteAll(T target) {
        List<T> newTs = new ArrayList<>();
        int count = count();
        T t;
        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (t != target) {
                newTs.add(t);
            }
        }
        this.ts.clear();
        this.ts.addAll(newTs);
        return (THIS) this;
    }

    @Override
    protected THIS deleteAll(Vs.EachGetter<T> getter) {
        if (getter != null) {
            int valueSymbolCount = getter.count();
            int tCount = count();
            if (valueSymbolCount > 0 && tCount > 0) {
                List<T> newTs = new ArrayList<>();

                T t;
                boolean isSame;
                for (int i = 0; i < tCount; i++) {
                    t = this.ts.get(i);

                    isSame = false;
                    for (int j = 0; j < valueSymbolCount; j++) {
                        if (t == getter.get(j)) {
                            isSame = true;
                            break;
                        }
                    }
                    if (!isSame) {
                        newTs.add(t);
                    }
                }
                this.ts.clear();
                this.ts.addAll(newTs);
            }
        }
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // maxMin
    //
    ///////////////////////////////////////////////////////

    @Deprecated
    @Override
    public MaxMin<T> maxMin(Vs.NowMax<T> nowMax) {
        return super.maxMin(nowMax);
    }

    @Deprecated
    @Override
    public MaxMin<T> maxMin(ToInt<T> toInt) {
        return super.maxMin(toInt);
    }

    @Deprecated
    @Override
    public MaxMin<T> maxMin(ToLong<T> toLong) {
        return super.maxMin(toLong);
    }

    @Deprecated
    @Override
    public MaxMin<T> maxMin(ToDouble<T> toDouble) {
        return super.maxMin(toDouble);
    }

    @Deprecated
    @Override
    public MaxMin<T> maxMin(ToFloat<T> toFloat) {
        return super.maxMin(toFloat);
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
    public T obtainNeighborDataWhenNextPriorityByVauleSymbol(String valueSymbol) {
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
    public T obtainNeighborDataWhenPrePriorityByValueSymbol(String valueSymbol) {
        return super.obtainNeighborDataWhenPrePriorityByValueSymbol(valueSymbol);
    }

    ///////////////////////////////////////////////////////
    //
    // ToMap
    //
    ///////////////////////////////////////////////////////
    @Deprecated
    @Override
    public Map<String, T> toMap() {
        return super.toMap();
    }
}
