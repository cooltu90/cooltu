package com.codingtu.cooltu.lib4j.ts1;

import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.function.ToDouble;
import com.codingtu.cooltu.lib4j.function.ToFloat;
import com.codingtu.cooltu.lib4j.function.ToInt;
import com.codingtu.cooltu.lib4j.function.ToLong;
import com.codingtu.cooltu.lib4j.ts1.impl.IntegerTs;
import com.codingtu.cooltu.lib4j.ts1.impl.StringTs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NumTs<T, THIS extends CoreTs> extends CoreTs<T, THIS> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public NumTs() {
    }

    public NumTs(List<T> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取Symbol
    //
    ///////////////////////////////////////////////////////
    @Override
    protected String obtainSymbol(T t) {
        return t + "";
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
    public T getBySymbol(String symbol) {
        return super.getBySymbol(symbol);
    }

    @Deprecated
    @Override
    public T get(T t) {
        return super.get(t);
    }

    /**************************************************
     * getAll
     **************************************************/
    @Deprecated
    @Override
    public THIS getAllBySymbol(String symbol) {
        return super.getAllBySymbol(symbol);
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
    public boolean hasBySymbol(String symbol) {
        return super.hasBySymbol(symbol);
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
     * first
     **************************************************/
    @Deprecated
    @Override
    public int indexBySymbol(String symbol) {
        return super.indexBySymbol(symbol);
    }

    @Override
    public int index(T t) {
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
    public IntegerTs allIndexBySymbol(String symbol) {
        return super.allIndexBySymbol(symbol);
    }

    @Override
    public IntegerTs allIndex(T t) {
        int count = count();
        IntegerTs integerTs = new IntegerTs();
        for (int i = 0; i < count; i++) {
            if (this.ts.get(i) == t) {
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
    public THIS replaceBySymbol(String symbol, T target) {
        return super.replaceBySymbol(symbol, target);
    }

    @Override
    public THIS replace(T symbolT, T target) {
        int firstIndex = index(symbolT);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        }
        return (THIS) this;
    }

    @Deprecated
    @Override
    public THIS replace(T target) {
        return super.replace(target);
    }

    @Deprecated
    @Override
    public THIS replace(T... targets) {
        return super.replace(targets);
    }

    @Deprecated
    @Override
    public THIS replace(List<T> targets) {
        return super.replace(targets);
    }

    @Deprecated
    @Override
    public THIS replace(THIS targets) {
        return super.replace(targets);
    }

    /**************************************************
     * replaceAll
     **************************************************/
    @Deprecated
    @Override
    public THIS replaceAllBySymbol(String symbol, T target) {
        return super.replaceAllBySymbol(symbol, target);
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
    public THIS replaceAll(THIS targets) {
        return super.replaceAll(targets);
    }

    /**************************************************
     * replaceOrAdd
     **************************************************/
    @Deprecated
    @Override
    public THIS replaceOrAddBySymbol(String symbol, T target) {
        return super.replaceOrAddBySymbol(symbol, target);
    }

    @Override
    public THIS replaceOrAdd(T symbolT, T target) {
        int firstIndex = index(symbolT);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        } else if (target != null) {
            ts.add(target);
        }
        return (THIS) this;
    }

    @Deprecated
    @Override
    public THIS replaceOrAdd(T target) {
        return super.replaceOrAdd(target);
    }

    @Deprecated
    @Override
    public THIS replaceOrAdd(T... targets) {
        return super.replaceOrAdd(targets);
    }

    @Deprecated
    @Override
    public THIS replaceOrAdd(List<T> targets) {
        return super.replaceOrAdd(targets);
    }

    @Deprecated
    @Override
    public THIS replaceOrAdd(THIS targets) {
        return super.replaceOrAdd(targets);
    }

    /**************************************************
     * replaceAllOrAdd
     **************************************************/
    @Deprecated
    @Override
    public THIS replaceAllOrAddBySymbol(String symbol, T target) {
        return super.replaceAllOrAddBySymbol(symbol, target);
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
    public THIS replaceAllOrAdd(THIS targets) {
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
    public THIS deleteBySymbol(String symbol) {
        return super.deleteBySymbol(symbol);
    }

    @Deprecated
    @Override
    public THIS deleteBySymbol(String... symbols) {
        return super.deleteBySymbol(symbols);
    }

    @Deprecated
    @Override
    public THIS deleteBySymbol(List<String> symbols) {
        return super.deleteBySymbol(symbols);
    }

    @Deprecated
    @Override
    public THIS deleteBySymbol(StringTs symbolStringTs) {
        return super.deleteBySymbol(symbolStringTs);
    }

    @Override
    public THIS delete(T target) {
        int firstIndex = index(target);
        if (firstIndex >= 0) {
            this.ts.remove(firstIndex);
        }
        return (THIS) this;
    }

    @Override
    protected THIS delete(Ts.EachGetter<T> getter) {
        if (getter != null) {
            int count = getter.count();
            if (count > 0) {
                T target = null;
                for (int i = 0; i < count; i++) {
                    target = getter.get(i);
                    if (target != null) {
                        delete(target);
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
    public THIS deleteAllBySymbol(String symbol) {
        return super.deleteAllBySymbol(symbol);
    }

    @Deprecated
    @Override
    public THIS deleteAllBySymbol(String... symbols) {
        return super.deleteAllBySymbol(symbols);
    }

    @Deprecated
    @Override
    public THIS deleteAllBySymbol(List<String> symbols) {
        return super.deleteAllBySymbol(symbols);
    }

    @Deprecated
    @Override
    public THIS deleteAllBySymbol(StringTs symbolStringTs) {
        return super.deleteAllBySymbol(symbolStringTs);
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
    protected THIS deleteAll(Ts.EachGetter<T> getter) {
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
    public MaxMin<T> maxMin(Ts.NowMax<T> nowMax) {
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
    // near方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * NearIndex下一个优先
     **************************************************/

    @Deprecated
    @Override
    public Ts.NearIndex nearIndexWhenNextPriorityBySymbol(String symbol) {
        return super.nearIndexWhenNextPriorityBySymbol(symbol);
    }

    /**************************************************
     * NearData下一个优先
     **************************************************/
    @Deprecated
    @Override
    public T nearDataWhenNextPriorityBySymbol(String symbol) {
        return super.nearDataWhenNextPriorityBySymbol(symbol);
    }

    /**************************************************
     * NearIndex上一个优先
     **************************************************/
    @Deprecated
    @Override
    public Ts.NearIndex nearIndexWhenPrePriorityBySymbol(String symbol) {
        return super.nearIndexWhenPrePriorityBySymbol(symbol);
    }

    /**************************************************
     * NearData下一个优先
     **************************************************/
    @Deprecated
    @Override
    public T nearDataWhenPrePriorityBySymbol(String symbol) {
        return super.nearDataWhenPrePriorityBySymbol(symbol);
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
