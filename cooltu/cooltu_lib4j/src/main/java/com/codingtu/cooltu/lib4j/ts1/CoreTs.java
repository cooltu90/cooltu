package com.codingtu.cooltu.lib4j.ts1;

import com.codingtu.cooltu.lib4j.function.ToDouble;
import com.codingtu.cooltu.lib4j.function.ToFloat;
import com.codingtu.cooltu.lib4j.function.ToInt;
import com.codingtu.cooltu.lib4j.function.ToLong;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.json.JsonTool;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.OtherTool;
import com.codingtu.cooltu.lib4j.data.symbol1.Symbol;
import com.codingtu.cooltu.lib4j.ts1.impl.IntegerTs;
import com.codingtu.cooltu.lib4j.ts1.impl.StringTs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**************************************************
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  add方法                ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #add(Object)}
 * {@link #add(Object[])}
 * {@link #add(List)}
 * {@link #add(CoreTs)}
 * {@link #addn(int, Object)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  get方法                ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【通过索引值获取】
 * {@link #getByIndex(int)}
 *
 * 【get】
 * {@link #get(Ts.IsThisOne)}
 * {@link #getBySymbol(String)}
 * {@link #get(Object)}
 *
 * 【getAll】
 * {@link #getAll(Ts.IsThisOne)}
 * {@link #getAllBySymbol(String)}
 * {@link #getAll(Object)}
 *
 * 【getLast】
 * {@link #getLast()}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  has方法                ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #has(Ts.IsThisOne)}
 * {@link #hasBySymbol(String)}
 * {@link #has(Object)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  index方法              ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【index】
 * {@link #index(Ts.IsThisOne)}
 * {@link #indexBySymbol(String)}
 * {@link #index(Object)}
 *
 * 【allIndex】
 * {@link #allIndex(Ts.IsThisOne)}
 * {@link #allIndexBySymbol(String)}
 * {@link #allIndex(Object)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  replace方法            ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【replaceByIndex】
 * {@link #replaceByIndex(int, Object)}
 *
 * 【replace】
 * {@link #replace(Object, Ts.IsThisOne)}
 * {@link #replaceBySymbol(String, Object)}
 * {@link #replace(Object, Object)}
 * {@link #replace(Object)}
 * {@link #replace(Object[])}
 * {@link #replace(List)}
 * {@link #replace(CoreTs)}
 *
 * 【replaceAll】
 * {@link #replaceAll(Object, Ts.IsThisOne)}
 * {@link #replaceAllBySymbol(String, Object)}
 * {@link #replaceAll(Object, Object)}
 * {@link #replaceAll(Object)}
 * {@link #replaceAll(Object[])}
 * {@link #replaceAll(List)}
 * {@link #replaceAll(CoreTs)}
 *
 * 【replaceOrAdd】
 * {@link #replaceOrAdd(Object, Ts.IsThisOne)}
 * {@link #replaceOrAddBySymbol(String, Object)}
 * {@link #replaceOrAdd(Object, Object)}
 * {@link #replaceOrAdd(Object)}
 * {@link #replaceOrAdd(Object[])}
 * {@link #replaceOrAdd(List)}
 * {@link #replaceOrAdd(CoreTs)}
 *
 * 【replaceAllOrAdd】
 * {@link #replaceAllOrAdd(Object, com.codingtu.cooltu.lib4j.ts.Ts.IsThisOne)}
 * {@link #replaceAllOrAddBySymbol(String, Object)}
 * {@link #replaceAllOrAdd(Object, Object)}
 * {@link #replaceAllOrAdd(Object)}
 * {@link #replaceAllOrAdd(Object[])}
 * {@link #replaceAllOrAdd(List)}
 * {@link #replaceAllOrAdd(CoreTs)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  delete方法             ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【deleteByIndex】
 * {@link #deleteByIndex(int)}
 *
 * 【delete】
 * {@link #delete(Ts.IsThisOne)}
 * {@link #deleteBySymbol(String)}
 * {@link #deleteBySymbol(String...)}
 * {@link #deleteBySymbol(List)}
 * {@link #deleteBySymbol(StringTs)}
 * {@link #delete(Object)}
 * {@link #delete(Object[])}
 * {@link #delete(List)}
 * {@link #delete(CoreTs)}
 *
 * 【deleteAll】
 * {@link #deleteAll(Ts.IsThisOne)}
 * {@link #deleteAllBySymbol(String)}
 * {@link #deleteAllBySymbol(String...)}
 * {@link #deleteAllBySymbol(List)}
 * {@link #deleteAllBySymbol(StringTs)}
 * {@link #deleteAll(Object)}
 * {@link #deleteAll(Object[])}
 * {@link #deleteAll(List)}
 * {@link #deleteAll(CoreTs)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  conver方法             ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #convert(Ts.Convert)}
 * {@link #convert(Class, Ts.Convert)}
 * {@link #convertList(Ts.Convert)}
 * {@link #convertList(Class, Ts.Convert)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  maxMin方法             ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #maxMin(Ts.NowMax)}
 * {@link #maxMin(ToInt)}
 * {@link #maxMin(ToLong)}
 * {@link #maxMin(ToDouble)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  near方法               ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【基础方法】
 * {@link #nearIndexByIndex(int, boolean)}
 * {@link #nearIndex(Ts.IsThisOne, boolean)}
 * {@link #nearIndexBySymbol(String, boolean)}
 * {@link #nearIndex(Object, boolean)}
 * {@link #nearData(Ts.NearIndex)}
 *
 * 【NearIndex下一个优先】
 * {@link #nearIndexWhenNextPriority(Ts.IsThisOne)}
 * {@link #nearIndexWhenNextPriorityBySymbol(String)}
 * {@link #nearIndexWhenNextPriority(Object)}
 *
 * 【NearData下一个优先】
 * {@link #nearDataWhenNextPriority(Ts.IsThisOne)}
 * {@link #nearDataWhenNextPriorityBySymbol(String)}
 * {@link #nearDataWhenNextPriority(Object)}
 *
 * 【NearIndex上一个优先】
 * {@link #nearIndexWhenPrePriority(Ts.IsThisOne)}
 * {@link #nearIndexWhenPrePriorityBySymbol(String)}
 * {@link #nearIndexWhenPrePriority(Object)}
 *
 * 【NearData上一个优先】
 * {@link #nearDataWhenPrePriority(Ts.IsThisOne)}
 * {@link #nearDataWhenPrePriorityBySymbol(String)}
 * {@link #nearDataWhenPrePriority(Object)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  toList方法             ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #toList()}
 * {@link #toArray()}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  findFinal方法          ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #findFinal(Ts.IsNow)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  toMap方法              ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #toMap(Ts.ToMap)}
 * {@link #toMap()}
 *
 **************************************************/
public abstract class CoreTs<T, THIS extends CoreTs> {

    ///////////////////////////////////////////////////////
    //
    // 数据
    //
    ///////////////////////////////////////////////////////

    protected List<T> ts;

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public CoreTs() {
        this.ts = new ArrayList<>();
    }

    public CoreTs(List<T> list) {
        if (list == null) this.ts = new ArrayList<>();
        else this.ts = list;
    }

    ///////////////////////////////////////////////////////
    //
    // 抽象方法，获取Symbol
    //
    ///////////////////////////////////////////////////////

    protected abstract String obtainSymbol(T t);

    ///////////////////////////////////////////////////////
    //
    // 计数
    //
    ///////////////////////////////////////////////////////
    public int count() {
        return CountTool.count(ts);
    }

    public int count(Ts.Counter<T> counter) {
        if (counter == null) return 0;

        int total = 0;
        int count = count();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                T t = this.ts.get(i);
                total = counter.counter(total, i, t);
            }
        }
        return total;
    }

    public boolean isNull() {
        return count() <= 0;
    }

    ///////////////////////////////////////////////////////
    //
    // 遍历
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * 正向遍历
     **************************************************/
    public THIS ls(int step, Ts.EachTs<T> eachTs) {
        if (eachTs == null || step <= 0) return (THIS) this;

        int count = count();
        for (int i = 0; i < count; i += step) {
            if (eachTs.each(i, this.ts.get(i))) {
                return (THIS) this;
            }
        }
        return (THIS) this;
    }

    public THIS ls(Ts.EachTs<T> eachTs) {
        return ls(1, eachTs);
    }

    /**************************************************
     * 反向遍历
     **************************************************/
    public THIS rls(int step, Ts.EachTs<T> eachTs) {
        if (eachTs == null || step <= 0) return (THIS) this;

        int count = count();
        for (int i = count - 1; i >= 0; i -= step) {
            if (eachTs.each(i, this.ts.get(i))) {
                return (THIS) this;
            }
        }
        return (THIS) this;
    }

    public THIS rls(Ts.EachTs<T> eachTs) {
        return rls(1, eachTs);
    }

    ///////////////////////////////////////////////////////
    //
    // 打印
    //
    ///////////////////////////////////////////////////////
    public THIS log() {
        ls(new Ts.EachTs<T>() {
            @Override
            public boolean each(int position, T t) {
                LibLogs.i(JsonTool.toJson(t));
                return false;
            }
        });
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // 排序
    //
    ///////////////////////////////////////////////////////
    public THIS sort(Comparator<T> comparator) {
        if (count() > 0) {
            Collections.sort(ts, comparator);
        }
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // 清除
    //
    ///////////////////////////////////////////////////////
    public THIS clear() {
        this.ts.clear();
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////
    public THIS add(T t) {
        this.ts.add(t);
        return (THIS) this;
    }

    public THIS add(T... ts) {
        int count = CountTool.count(ts);
        for (int i = 0; i < count; i++) {
            this.ts.add(ts[i]);
        }
        return (THIS) this;
    }

    public THIS add(List<T> ts) {
        if (!CountTool.isNull(ts)) {
            this.ts.addAll(ts);
        }
        return (THIS) this;
    }

    public THIS add(THIS ts) {
        if (!CountTool.isNull(ts)) {
            this.ts.addAll(ts.ts);
        }
        return (THIS) this;
    }

    public THIS addn(int n, T t) {
        for (int i = 0; i < n; i++) {
            this.ts.add(t);
        }
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // createThis
    //
    ///////////////////////////////////////////////////////
    public THIS createThis() {
        try {
            return (THIS) this.getClass().getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public THIS createThis(T... ts) {
        THIS aThis = createThis();
        aThis.add(ts);
        return aThis;
    }

    public THIS createThis(List<T> ts) {
        try {
            return (THIS) this.getClass().getConstructor(List.class).newInstance(ts);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    ///////////////////////////////////////////////////////
    //
    // get方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * 通过索引值获取
     **************************************************/
    public T getByIndex(int index) {
        if (index < count() && index >= 0) {
            return ts.get(index);
        }
        return null;
    }

    /**************************************************
     * get
     **************************************************/
    public T get(Ts.IsThisOne<T> isThisOne) {
        if (isThisOne != null) {
            int count = count();
            T t = null;
            for (int i = 0; i < count; i++) {
                t = this.ts.get(i);
                if (isThisOne.isThisOne(i, t)) {
                    return t;
                }
            }
        }
        return null;
    }

    public T getBySymbol(String symbol) {
        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (symbol.equals(obtainSymbol(t))) {
                return t;
            }
        }
        return null;
    }

    public T get(T t) {
        return getBySymbol(obtainSymbol(t));
    }

    /**************************************************
     * getAll
     **************************************************/
    public THIS getAll(Ts.IsThisOne<T> isThisOne) {
        THIS ThisObj = createThis();
        if (isThisOne != null) {
            int count = count();
            T t = null;
            for (int i = 0; i < count; i++) {
                t = this.ts.get(i);
                if (isThisOne.isThisOne(i, t)) {
                    ThisObj.add(t);
                }
            }
        }
        return ThisObj;
    }

    public THIS getAllBySymbol(String symbol) {
        THIS ThisObj = createThis();
        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (symbol.equals(obtainSymbol(t))) {
                ThisObj.add(t);
            }
        }
        return ThisObj;
    }

    public THIS getAll(T t) {
        return getAllBySymbol(obtainSymbol(t));
    }

    /**************************************************
     * getLast
     **************************************************/
    public T getLast() {
        if (count() <= 0) {
            return null;
        }
        return this.ts.get(count() - 1);
    }

    ///////////////////////////////////////////////////////
    //
    // has方法
    //
    ///////////////////////////////////////////////////////
    public boolean has(Ts.IsThisOne<T> isThisOne) {
        return get(isThisOne) != null;
    }

    public boolean hasBySymbol(String symbol) {
        return getBySymbol(symbol) != null;
    }

    public boolean has(T t) {
        return hasBySymbol(obtainSymbol(t));
    }

    ///////////////////////////////////////////////////////
    //
    // index方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * index
     **************************************************/
    public int index(Ts.IsThisOne<T> isThisOne) {
        if (isThisOne != null) {
            int count = count();
            for (int i = 0; i < count; i++) {
                if (isThisOne.isThisOne(i, this.ts.get(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int indexBySymbol(String symbol) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (symbol.equals(obtainSymbol(this.ts.get(i)))) {
                return i;
            }
        }
        return -1;
    }

    public int index(T t) {
        return indexBySymbol(obtainSymbol(t));
    }

    /**************************************************
     * allIndex
     **************************************************/
    public IntegerTs allIndex(Ts.IsThisOne<T> isThisOne) {
        IntegerTs integerTs = new IntegerTs();
        if (isThisOne != null) {
            int count = count();
            for (int i = 0; i < count; i++) {
                if (isThisOne.isThisOne(i, this.ts.get(i))) {
                    integerTs.add(i);
                }
            }
        }
        return integerTs;
    }

    public IntegerTs allIndexBySymbol(String symbol) {
        IntegerTs integerTs = new IntegerTs();
        int count = count();
        for (int i = 0; i < count; i++) {
            if (obtainSymbol(this.ts.get(i)).equals(symbol)) {
                integerTs.add(i);
            }
        }
        return integerTs;
    }

    public IntegerTs allIndex(T t) {
        return allIndexBySymbol(obtainSymbol(t));
    }

    ///////////////////////////////////////////////////////
    //
    // replace方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * replaceByIndex
     **************************************************/
    public THIS replaceByIndex(int index, T t) {
        this.ts.set(index, t);
        return (THIS) this;
    }

    /**************************************************
     * replace
     **************************************************/
    public THIS replace(T target, Ts.IsThisOne<T> isThisOne) {
        if (isThisOne != null) {
            int firstIndex = index(isThisOne);
            if (firstIndex >= 0) {
                replaceByIndex(firstIndex, target);
            }
        }
        return (THIS) this;
    }

    public THIS replaceBySymbol(String symbol, T target) {
        int firstIndex = indexBySymbol(symbol);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        }
        return (THIS) this;
    }

    public THIS replace(T symbolT, T target) {
        if (symbolT != null && target != null) {
            replaceBySymbol(obtainSymbol(symbolT), target);
        }
        return (THIS) this;
    }

    public THIS replace(T target) {
        return replace(target, target);
    }

    public THIS replace(T... targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target;
            for (int i = 0; i < count; i++) {
                target = targets[i];
                if (target != null) {
                    replaceBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replace(List<T> targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target;
            for (int i = 0; i < count; i++) {
                target = targets.get(i);
                if (target != null) {
                    replaceBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replace(THIS targets) {
        int count = targets.count();
        if (count > 0) {
            T target;
            for (int i = 0; i < count; i++) {
                target = (T) targets.ts.get(i);
                if (target != null) {
                    replaceBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    /**************************************************
     * replaceAll
     **************************************************/
    public THIS replaceAll(T target, Ts.IsThisOne<T> isThisOne) {
        if (isThisOne != null) {
            int count = count();
            for (int i = 0; i < count; i++) {
                if (isThisOne.isThisOne(i, this.ts.get(i))) {
                    replaceByIndex(i, target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceAllBySymbol(String symbol, T target) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (obtainSymbol(this.ts.get(i)).equals(symbol)) {
                replaceByIndex(i, target);
            }
        }
        return (THIS) this;
    }

    public THIS replaceAll(T symbolT, T target) {
        if (symbolT != null && target != null) {
            replaceAllBySymbol(obtainSymbol(symbolT), target);
        }
        return (THIS) this;
    }

    public THIS replaceAll(T target) {
        return replaceAll(target, target);
    }

    public THIS replaceAll(T... targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = targets[i];
                if (target != null) {
                    replaceAllBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceAll(List<T> targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = targets.get(i);
                if (target != null) {
                    replaceAllBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceAll(THIS targets) {
        int count = targets.count();
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = (T) targets.ts.get(i);
                if (target != null) {
                    replaceAllBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    /**************************************************
     * replaceOrAdd
     **************************************************/
    public THIS replaceOrAdd(T target, Ts.IsThisOne<T> isThisOne) {
        if (isThisOne != null) {
            int firstIndex = index(isThisOne);
            if (firstIndex >= 0) {
                replaceByIndex(firstIndex, target);
            } else if (target != null) {
                ts.add(target);
            }
        }
        return (THIS) this;
    }

    public THIS replaceOrAddBySymbol(String symbol, T target) {
        int firstIndex = indexBySymbol(symbol);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        } else if (target != null) {
            ts.add(target);
        }
        return (THIS) this;
    }

    public THIS replaceOrAdd(T symbolT, T target) {
        if (symbolT != null && target != null) {
            replaceOrAddBySymbol(obtainSymbol(symbolT), target);
        }
        return (THIS) this;
    }

    public THIS replaceOrAdd(T target) {
        return replaceOrAdd(target, target);
    }

    public THIS replaceOrAdd(T... targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = targets[i];
                if (target != null) {
                    replaceOrAddBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceOrAdd(List<T> targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = targets.get(i);
                if (target != null) {
                    replaceOrAddBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceOrAdd(THIS targets) {
        int count = targets.count();
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = (T) targets.ts.get(i);
                if (target != null) {
                    replaceOrAddBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    /**************************************************
     * replaceAllOrAdd
     **************************************************/
    public THIS replaceAllOrAdd(T target, com.codingtu.cooltu.lib4j.ts.Ts.IsThisOne<T> isThisOne) {
        if (isThisOne != null) {
            int count = count();
            boolean isReplace = false;
            for (int i = 0; i < count; i++) {
                if (isThisOne.isThisOne(i, this.ts.get(i))) {
                    replaceByIndex(i, target);
                    isReplace = true;
                }
            }
            if (!isReplace && target != null) {
                this.ts.add(target);
            }
        }
        return (THIS) this;
    }

    public THIS replaceAllOrAddBySymbol(String symbol, T target) {
        int count = count();
        boolean isReplace = false;
        for (int i = 0; i < count; i++) {
            if (obtainSymbol(this.ts.get(i)).equals(symbol)) {
                replaceByIndex(i, target);
                isReplace = true;
            }
        }
        if (!isReplace && target != null) {
            this.ts.add(target);
        }
        return (THIS) this;
    }

    public THIS replaceAllOrAdd(T symboleT, T target) {
        if (symboleT != null && target != null) {
            replaceAllOrAddBySymbol(obtainSymbol(symboleT), target);
        }
        return (THIS) this;
    }

    public THIS replaceAllOrAdd(T target) {
        return replaceAllOrAdd(target, target);
    }

    public THIS replaceAllOrAdd(T... targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = targets[i];
                if (target != null) {
                    replaceAllOrAddBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceAllOrAdd(List<T> targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = targets.get(i);
                if (target != null) {
                    replaceAllOrAddBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceAllOrAdd(THIS targets) {
        int count = targets.count();
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = (T) targets.ts.get(i);
                if (target != null) {
                    replaceAllOrAddBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // delete方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * deleteByIndex
     **************************************************/
    public THIS deleteByIndex(int position) {
        this.ts.remove(position);
        return (THIS) this;
    }

    /**************************************************
     * delete
     **************************************************/
    //删除第一个
    public THIS delete(Ts.IsThisOne<T> isThisOne) {
        if (isThisOne != null) {
            int firstIndex = index(isThisOne);
            if (firstIndex >= 0) {
                this.ts.remove(firstIndex);
            }
        }
        return (THIS) this;
    }

    public THIS deleteBySymbol(String symbol) {
        int firstIndex = indexBySymbol(symbol);
        if (firstIndex >= 0) {
            this.ts.remove(firstIndex);
        }
        return (THIS) this;
    }

    public THIS deleteBySymbol(String... symbols) {
        int count = CountTool.count(symbols);
        for (int i = 0; i < count; i++) {
            int firstIndex = indexBySymbol(symbols[i]);
            if (firstIndex >= 0) {
                this.ts.remove(firstIndex);
            }
        }
        return (THIS) this;
    }

    public THIS deleteBySymbol(List<String> symbols) {
        int count = CountTool.count(symbols);
        for (int i = 0; i < count; i++) {
            int firstIndex = indexBySymbol(symbols.get(i));
            if (firstIndex >= 0) {
                this.ts.remove(firstIndex);
            }
        }
        return (THIS) this;
    }

    public THIS deleteBySymbol(StringTs symbolStringTs) {
        int count = CountTool.count(symbolStringTs);
        for (int i = 0; i < count; i++) {
            int firstIndex = indexBySymbol(symbolStringTs.ts.get(i));
            if (firstIndex >= 0) {
                this.ts.remove(firstIndex);
            }
        }
        return (THIS) this;
    }


    public THIS delete(T target) {
        if (target != null) {
            deleteBySymbol(obtainSymbol(target));
        }
        return (THIS) this;
    }

    protected THIS delete(Ts.EachGetter<T> getter) {
        if (getter != null) {
            int count = getter.count();
            if (count > 0) {
                T target = null;
                for (int i = 0; i < count; i++) {
                    target = getter.get(i);
                    if (target != null) {
                        deleteBySymbol(obtainSymbol(target));
                    }
                }
            }
        }
        return (THIS) this;
    }

    public THIS delete(T... targets) {
        return delete(new Ts.EachGetter<T>() {
            @Override
            public int count() {
                return CountTool.count(targets);
            }

            @Override
            public T get(int position) {
                return targets[position];
            }
        });
    }

    public THIS delete(List<T> targets) {
        return delete(new Ts.EachGetter<T>() {
            @Override
            public int count() {
                return CountTool.count(targets);
            }

            @Override
            public T get(int position) {
                return targets.get(position);
            }
        });
    }

    public THIS delete(THIS targets) {
        return delete(new Ts.EachGetter<T>() {
            @Override
            public int count() {
                return CountTool.count(targets);
            }

            @Override
            public T get(int position) {
                return (T) targets.ts.get(position);
            }
        });
    }

    /**************************************************
     * deleteAll
     **************************************************/
    public THIS deleteAll(Ts.IsThisOne<T> isThisOne) {
        if (isThisOne != null) {
            List<T> newTs = new ArrayList<>();
            int count = count();
            T t;
            for (int i = 0; i < count; i++) {
                t = this.ts.get(i);
                if (!isThisOne.isThisOne(i, t)) {
                    newTs.add(t);
                }
            }
            this.ts.clear();
            this.ts.addAll(newTs);
        }
        return (THIS) this;
    }

    public THIS deleteAllBySymbol(String symbol) {
        List<T> newTs = new ArrayList<>();
        int count = count();
        T t;
        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (!obtainSymbol(t).equals(symbol)) {
                newTs.add(t);
            }
        }
        this.ts.clear();
        this.ts.addAll(newTs);
        return (THIS) this;
    }

    private THIS deleteAllBySymbol(Ts.EachGetter<String> getter) {
        if (getter != null) {
            int valueSymbolCount = getter.count();
            int tCount = count();
            if (valueSymbolCount > 0 && tCount > 0) {
                List<T> newTs = new ArrayList<>();

                T t;
                String tValueSymbol;
                boolean isSame;
                for (int i = 0; i < tCount; i++) {
                    t = this.ts.get(i);
                    tValueSymbol = obtainSymbol(t);

                    isSame = false;
                    for (int j = 0; j < valueSymbolCount; j++) {
                        if (tValueSymbol.equals(getter.get(j))) {
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

    public THIS deleteAllBySymbol(String... symbols) {
        return deleteAllBySymbol(new Ts.EachGetter<String>() {
            @Override
            public int count() {
                return CountTool.count(symbols);
            }

            @Override
            public String get(int position) {
                return symbols[position];
            }
        });
    }

    public THIS deleteAllBySymbol(List<String> symbols) {
        return deleteAllBySymbol(new Ts.EachGetter<String>() {
            @Override
            public int count() {
                return CountTool.count(symbols);
            }

            @Override
            public String get(int position) {
                return symbols.get(position);
            }
        });
    }

    public THIS deleteAllBySymbol(StringTs symbolStringTs) {
        return deleteAllBySymbol(new Ts.EachGetter<String>() {
            @Override
            public int count() {
                return CountTool.count(symbolStringTs);
            }

            @Override
            public String get(int position) {
                return symbolStringTs.ts.get(position);
            }
        });
    }

    //删除所有
    public THIS deleteAll(T target) {
        if (target != null) {
            deleteAllBySymbol(obtainSymbol(target));
        }
        return (THIS) this;
    }

    protected THIS deleteAll(Ts.EachGetter<T> getter) {
        if (getter != null) {
            int valueSymbolCount = getter.count();
            int tCount = count();
            if (valueSymbolCount > 0 && tCount > 0) {
                List<T> newTs = new ArrayList<>();

                T t;
                String tValueSymbol;
                boolean isSame;
                for (int i = 0; i < tCount; i++) {
                    t = this.ts.get(i);
                    tValueSymbol = obtainSymbol(t);

                    isSame = false;
                    for (int j = 0; j < valueSymbolCount; j++) {
                        if (tValueSymbol.equals(obtainSymbol(getter.get(j)))) {
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


    public THIS deleteAll(T... targets) {
        return deleteAll(new Ts.EachGetter<T>() {
            @Override
            public int count() {
                return CountTool.count(targets);
            }

            @Override
            public T get(int position) {
                return targets[position];
            }
        });
    }

    public THIS deleteAll(List<T> targets) {
        return deleteAll(new Ts.EachGetter<T>() {
            @Override
            public int count() {
                return CountTool.count(targets);
            }

            @Override
            public T get(int position) {
                return targets.get(position);
            }
        });
    }

    public THIS deleteAll(THIS targets) {
        return deleteAll(new Ts.EachGetter<T>() {
            @Override
            public int count() {
                return CountTool.count(targets);
            }

            @Override
            public T get(int position) {
                return (T) targets.ts.get(position);
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    // 转换
    //
    ///////////////////////////////////////////////////////

    public <TARGET extends Symbol> SymbolTs<TARGET> convert(Ts.Convert<T, TARGET> convert) {
        SymbolTs symbolTs = new SymbolTs();
        if (convert != null) {
            int count = count();
            TARGET target;
            for (int i = 0; i < count; i++) {
                target = convert.convert(i, this.ts.get(i));
                if (target != null) {
                    symbolTs.add(target);
                }
            }
        }
        return symbolTs;
    }

    public <TARGET, TS extends CoreTs> TS convert(Class<TS> tsClass, Ts.Convert<T, TARGET> convert) {
        TS ts;
        try {
            ts = tsClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int count = count();
        TARGET target;
        for (int i = 0; i < count; i++) {
            target = convert.convert(i, this.ts.get(i));
            if (target != null) {
                ts.add(target);
            }
        }
        return ts;
    }

    public <TARGET extends Symbol> SymbolTs<TARGET> convertList(Ts.Convert<T, List<TARGET>> convert) {
        SymbolTs<TARGET> ts = new SymbolTs<>();
        if (convert != null) {
            int count = count();
            List<TARGET> list;
            for (int i = 0; i < count; i++) {
                list = convert.convert(i, this.ts.get(i));
                if (!CountTool.isNull(list)) {
                    ts.add(list);
                }
            }
        }
        return ts;
    }

    public <TS extends CoreTs, TARGET> TS convertList(Class<TS> tsClass, Ts.Convert<T, List<TARGET>> convert) {
        TS ts;
        try {
            ts = tsClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (convert != null) {
            int count = count();
            List<TARGET> list;
            for (int i = 0; i < count; i++) {
                list = convert.convert(i, this.ts.get(i));
                if (!CountTool.isNull(list)) {
                    ts.add(list);
                }
            }
        }
        return ts;
    }

    ///////////////////////////////////////////////////////
    //
    // 获取最大最小值
    //
    ///////////////////////////////////////////////////////

    public MaxMin<T> maxMin(Ts.NowMax<T> nowMax) {
        if (nowMax == null) return null;

        int count = count();
        MaxMin<T> maxMin = null;
        for (int i = 0; i < count; i++) {
            T now = this.ts.get(i);
            if (i == 0) {
                maxMin = new MaxMin<>();
                maxMin.max = now;
                maxMin.min = now;
            } else {
                maxMin.max = nowMax.isNowMax(maxMin.max, now) ? now : maxMin.max;
                maxMin.min = nowMax.isNowMax(maxMin.min, now) ? maxMin.min : now;
            }
        }

        return maxMin;
    }

    public MaxMin<T> maxMin(ToInt<T> toInt) {
        return maxMin(new Ts.NowMax<T>() {
            @Override
            public boolean isNowMax(T last, T now) {
                return toInt.toInt(now) > toInt.toInt(last);
            }
        });
    }

    public MaxMin<T> maxMin(ToLong<T> toLong) {
        return maxMin(new Ts.NowMax<T>() {
            @Override
            public boolean isNowMax(T last, T now) {
                return toLong.toLong(now) > toLong.toLong(last);
            }
        });
    }

    public MaxMin<T> maxMin(ToDouble<T> toDouble) {
        return maxMin(new Ts.NowMax<T>() {
            @Override
            public boolean isNowMax(T last, T now) {
                return toDouble.toDouble(now) > toDouble.toDouble(last);
            }
        });
    }

    public MaxMin<T> maxMin(ToFloat<T> toFloat) {
        return maxMin(new Ts.NowMax<T>() {
            @Override
            public boolean isNowMax(T last, T now) {
                return toFloat.toFloat(now) > toFloat.toFloat(last);
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    // near方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * 基础方法
     **************************************************/
    protected Ts.NearIndex nearIndexByIndex(int index, boolean isNext) {
        int count = count();
        if (count == 1) {
            return null;
        }
        if (index < 0) {
            return null;
        }

        int step = isNext ? 1 : -1;

        Ts.NearIndex nearIndex = new Ts.NearIndex();
        nearIndex.currentIndex = index;
        if (nearIndex.currentIndex == (isNext ? (count - 1) : 0)) {
            nearIndex.neighborIndex = nearIndex.currentIndex - step;
        } else {
            nearIndex.neighborIndex = nearIndex.currentIndex + step;
        }
        return nearIndex;
    }

    protected Ts.NearIndex nearIndex(Ts.IsThisOne<T> isThisOne, boolean isNext) {
        return nearIndexByIndex(index(isThisOne), isNext);
    }

    protected Ts.NearIndex nearIndexBySymbol(String symbol, boolean isNext) {
        return nearIndexByIndex(indexBySymbol(symbol), isNext);
    }

    protected Ts.NearIndex nearIndex(T t, boolean isNext) {
        return nearIndexByIndex(index(t), isNext);
    }

    protected T nearData(Ts.NearIndex nearIndex) {
        if (nearIndex == null) return null;
        return getByIndex(nearIndex.neighborIndex);
    }

    /**************************************************
     * NearIndex下一个优先
     **************************************************/
    public Ts.NearIndex nearIndexWhenNextPriority(Ts.IsThisOne<T> isThisOne) {
        return nearIndex(isThisOne, true);
    }

    public Ts.NearIndex nearIndexWhenNextPriorityBySymbol(String symbol) {
        return nearIndexBySymbol(symbol, true);
    }

    public Ts.NearIndex nearIndexWhenNextPriority(T t) {
        return nearIndex(t, true);
    }

    /**************************************************
     * NearData下一个优先
     **************************************************/
    public T nearDataWhenNextPriority(Ts.IsThisOne<T> isThisOne) {
        return nearData(nearIndexWhenNextPriority(isThisOne));
    }

    public T nearDataWhenNextPriorityBySymbol(String symbol) {
        return nearData(nearIndexWhenNextPriorityBySymbol(symbol));
    }

    public T nearDataWhenNextPriority(T t) {
        return nearData(nearIndexWhenNextPriority(t));
    }

    /**************************************************
     * NearIndex上一个优先
     **************************************************/
    public Ts.NearIndex nearIndexWhenPrePriority(Ts.IsThisOne<T> isThisOne) {
        return nearIndex(isThisOne, false);
    }

    public Ts.NearIndex nearIndexWhenPrePriorityBySymbol(String symbol) {
        return nearIndexBySymbol(symbol, false);
    }

    public Ts.NearIndex nearIndexWhenPrePriority(T t) {
        return nearIndex(t, false);
    }

    /**************************************************
     * NearData下一个优先
     **************************************************/
    public T nearDataWhenPrePriority(Ts.IsThisOne<T> isThisOne) {
        return nearData(nearIndexWhenPrePriority(isThisOne));
    }

    public T nearDataWhenPrePriorityBySymbol(String symbol) {
        return nearData(nearIndexWhenPrePriorityBySymbol(symbol));
    }

    public T nearDataWhenPrePriority(T t) {
        return nearData(nearIndexWhenPrePriority(t));
    }

    ///////////////////////////////////////////////////////
    //
    // toList
    //
    ///////////////////////////////////////////////////////
    public List<T> toList() {
        return this.ts;
    }

    public T[] toArray() {
        int count = count();
        T[] newArray = (T[]) java.lang.reflect.Array.newInstance(OtherTool.getFanxing(this, 0), count);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                newArray[i] = this.ts.get(i);
            }
        }
        return newArray;
    }

    ///////////////////////////////////////////////////////
    //
    // findFinal
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * 查找最后一个符合条件的元素
     **************************************************/
    public T findFinal(Ts.IsNow<T> isNow) {
        if (isNow == null) return null;

        int count = count();
        T last = null;
        for (int i = 0; i < count; i++) {
            T now = this.ts.get(i);
            last = last == null ? now : (isNow.isNow(last, now) ? now : last);
        }
        return last;
    }

    ///////////////////////////////////////////////////////
    //
    // ToMap
    //
    ///////////////////////////////////////////////////////
    public <K, V> Map<K, V> toMap(Ts.ToMap<K, V, T> toMap) {
        Map<K, V> map = new HashMap<>();
        int count = count();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                T t = this.ts.get(i);
                toMap.deal(map, i, t);
            }
        }
        return map;
    }

    public Map<String, T> toMap() {
        return toMap(new Ts.ToMap<String, T, T>() {
            @Override
            public void deal(Map<String, T> map, int i, T t) {
                map.put(obtainSymbol(t), t);
            }
        });
    }

    public Map<T, T> toTMap() {
        return toMap(new Ts.ToMap<T, T, T>() {
            @Override
            public void deal(Map<T, T> map, int i, T t) {
                map.put(t, t);
            }
        });
    }

}
