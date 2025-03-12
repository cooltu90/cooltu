package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.function.ToDouble;
import com.codingtu.cooltu.lib4j.function.ToFloat;
import com.codingtu.cooltu.lib4j.function.ToInt;
import com.codingtu.cooltu.lib4j.function.ToLong;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.json.JsonTool;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.OtherTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.data.symbol.ValueSymbol;
import com.codingtu.cooltu.lib4j.vs.impl.IntegerVs;
import com.codingtu.cooltu.lib4j.vs.impl.StringVs;

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
 * {@link #add(CoreVs)}
 * {@link #addn(int, Object)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  get方法                ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【通过索引值获取】
 * {@link #getByIndex(int)}
 *
 * 【getFirst】
 * {@link #getFirst(Vs.IsThisOne)}
 * {@link #getFirstByValueSymbol(String)}
 * {@link #getFirst(Object)}
 *
 * 【getAll】
 * {@link #getAll(Vs.IsThisOne)}
 * {@link #getAllByValueSymbol(String)}
 * {@link #getAll(Object)}
 *
 * 【getLast】
 * {@link #getLast()}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  has方法                ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #has(Vs.IsThisOne)}
 * {@link #hasByValueSymbol(String)}
 * {@link #has(Object)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  index方法              ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【firstIndex】
 * {@link #firstIndex(Vs.IsThisOne)}
 * {@link #firstIndexByValueSymbol(String)}
 * {@link #firstIndex(Object)}
 *
 * 【allIndex】
 * {@link #allIndex(Vs.IsThisOne)}
 * {@link #allIndexByValueSymbol(String)}
 * {@link #allIndex(Object)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  replace方法            ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【replaceByIndex】
 * {@link #replaceByIndex(int, Object)}
 *
 * 【replaceFirst】
 * {@link #replaceFirst(Object, Vs.IsThisOne)}
 * {@link #replaceFirstByValueSymbol(String, Object)}
 * {@link #replaceFirst(Object, Object)}
 * {@link #replaceFirst(Object)}
 * {@link #replaceFirst(Object[])}
 * {@link #replaceFirst(List)}
 * {@link #replaceFirst(CoreVs)}
 *
 * 【replaceAll】
 * {@link #replaceAll(Object, Vs.IsThisOne)}
 * {@link #replaceAllByValueSymbol(String, Object)}
 * {@link #replaceAll(Object, Object)}
 * {@link #replaceAll(Object)}
 * {@link #replaceAll(Object[])}
 * {@link #replaceAll(List)}
 * {@link #replaceAll(CoreVs)}
 *
 * 【replaceFirstOrAdd】
 * {@link #replaceFirstOrAdd(Object, Vs.IsThisOne)}
 * {@link #replaceFirstOrAddByValueSymbol(String, Object)}
 * {@link #replaceFirstOrAdd(Object, Object)}
 * {@link #replaceFirstOrAdd(Object)}
 * {@link #replaceFirstOrAdd(Object[])}
 * {@link #replaceFirstOrAdd(List)}
 * {@link #replaceFirstOrAdd(CoreVs)}
 *
 * 【replaceAllOrAdd】
 * {@link #replaceAllOrAdd(Object, Ts.IsThisOne)}
 * {@link #replaceAllOrAddByValueSymbol(String, Object)}
 * {@link #replaceAllOrAdd(Object, Object)}
 * {@link #replaceAllOrAdd(Object)}
 * {@link #replaceAllOrAdd(Object[])}
 * {@link #replaceAllOrAdd(List)}
 * {@link #replaceAllOrAdd(CoreVs)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  delete方法             ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【deleteByIndex】
 * {@link #deleteByIndex(int)}
 *
 * 【deleteFirst】
 * {@link #deleteFirst(Vs.IsThisOne)}
 * {@link #deleteFirstByValueSymbol(String)}
 * {@link #deleteFirstByValueSymbol(String...)}
 * {@link #deleteFirstByValueSymbol(List)}
 * {@link #deleteFirstByValueSymbol(StringVs)}
 * {@link #deleteFirst(Object)}
 * {@link #deleteFirst(Object[])}
 * {@link #deleteFirst(List)}
 * {@link #deleteFirst(CoreVs)}
 *
 * 【deleteAll】
 * {@link #deleteAll(Vs.IsThisOne)}
 * {@link #deleteAllByValueSymbol(String)}
 * {@link #deleteAllByValueSymbol(String...)}
 * {@link #deleteAllByValueSymbol(List)}
 * {@link #deleteAllByValueSymbol(StringVs)}
 * {@link #deleteAll(Object)}
 * {@link #deleteAll(Object[])}
 * {@link #deleteAll(List)}
 * {@link #deleteAll(CoreVs)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  conver方法             ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #convert(Vs.Convert)}
 * {@link #convert(Class, Vs.Convert)}
 * {@link #convertList(Vs.Convert)}
 * {@link #convertList(Class, Vs.Convert)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  maxMin方法             ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #maxMin(Vs.NowMax)}
 * {@link #maxMin(ToInt)}
 * {@link #maxMin(ToLong)}
 * {@link #maxMin(ToDouble)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  neighbor方法           ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【基础方法】
 * {@link #obtainNeighborIndexByIndex(int, boolean)}
 * {@link #obtainNeighborIndex(Vs.IsThisOne, boolean)}
 * {@link #obtainNeighborIndexByValueSymbol(String, boolean)}
 * {@link #obtainNeighborIndex(Object, boolean)}
 * {@link #obtainNeighborData(Vs.NeighborIndex)}
 *
 * 【NeighborIndex下一个优先】
 * {@link #obtainNeighborIndexWhenNextPriority(Vs.IsThisOne)}
 * {@link #obtainNeighborIndexWhenNextPriorityByValueSymbol(String)}
 * {@link #obtainNeighborIndexWhenNextPriority(Object)}
 *
 * 【NeighborData下一个优先】
 * {@link #obtainNeighborDataWhenNextPriority(Vs.IsThisOne)}
 * {@link #obtainNeighborDataWhenNextPriorityByVauleSymbol(String)}
 * {@link #obtainNeighborDataWhenNextPriority(Object)}
 *
 * 【NeighborIndex上一个优先】
 * {@link #obtainNeighborIndexWhenPrePriority(Vs.IsThisOne)}
 * {@link #obtainNeighborIndexWhenPrePriorityByValueSymbol(String)}
 * {@link #obtainNeighborIndexWhenPrePriority(Object)}
 *
 * 【NeighborData上一个优先】
 * {@link #obtainNeighborDataWhenPrePriority(Vs.IsThisOne)}
 * {@link #obtainNeighborDataWhenPrePriorityByValueSymbol(String)}
 * {@link #obtainNeighborDataWhenPrePriority(Object)}
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
 * {@link #findFinal(Vs.IsNow)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  toMap方法              ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #toMap(Vs.ToMap)}
 * {@link #toMap()}
 *
 **************************************************/
public abstract class CoreVs<T, THIS extends CoreVs> {

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
    public CoreVs() {
        this.ts = new ArrayList<>();
    }

    public CoreVs(List<T> list) {
        if (list == null) this.ts = new ArrayList<>();
        else this.ts = list;
    }

    ///////////////////////////////////////////////////////
    //
    // 抽象方法，获取valueSymbol
    //
    ///////////////////////////////////////////////////////

    protected abstract String valueSymbol(T t);

    ///////////////////////////////////////////////////////
    //
    // 计数
    //
    ///////////////////////////////////////////////////////
    public int count() {
        return CountTool.count(ts);
    }

    public int count(Vs.Counter<T> counter) {
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
    public THIS ls(int step, Vs.EachTs<T> eachTs) {
        if (eachTs == null || step <= 0) return (THIS) this;

        int count = count();
        for (int i = 0; i < count; i += step) {
            if (eachTs.each(i, this.ts.get(i))) {
                return (THIS) this;
            }
        }
        return (THIS) this;
    }

    public THIS ls(Vs.EachTs<T> eachTs) {
        return ls(1, eachTs);
    }

    /**************************************************
     * 反向遍历
     **************************************************/
    public THIS rls(int step, Vs.EachTs<T> eachTs) {
        if (eachTs == null || step <= 0) return (THIS) this;

        int count = count();
        for (int i = count - 1; i >= 0; i -= step) {
            if (eachTs.each(i, this.ts.get(i))) {
                return (THIS) this;
            }
        }
        return (THIS) this;
    }

    public THIS rls(Vs.EachTs<T> eachTs) {
        return rls(1, eachTs);
    }

    ///////////////////////////////////////////////////////
    //
    // 打印
    //
    ///////////////////////////////////////////////////////
    public THIS log() {
        ls(new Vs.EachTs<T>() {
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
     * getFirst
     **************************************************/
    public T getFirst(Vs.IsThisOne<T> isThisOne) {
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

    public T getFirstByValueSymbol(String valueSymbol) {
        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (valueSymbol.equals(valueSymbol(t))) {
                return t;
            }
        }
        return null;
    }

    public T getFirst(T t) {
        return getFirstByValueSymbol(valueSymbol(t));
    }

    /**************************************************
     * getAll
     **************************************************/
    public THIS getAll(Vs.IsThisOne<T> isThisOne) {
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

    public THIS getAllByValueSymbol(String valueSymbol) {
        THIS ThisObj = createThis();
        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (valueSymbol.equals(valueSymbol(t))) {
                ThisObj.add(t);
            }
        }
        return ThisObj;
    }

    public THIS getAll(T t) {
        return getAllByValueSymbol(valueSymbol(t));
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
    public boolean has(Vs.IsThisOne<T> isThisOne) {
        return getFirst(isThisOne) != null;
    }

    public boolean hasByValueSymbol(String valueSymbol) {
        return getFirstByValueSymbol(valueSymbol) != null;
    }

    public boolean has(T t) {
        return hasByValueSymbol(valueSymbol(t));
    }

    ///////////////////////////////////////////////////////
    //
    // index方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * firstIndex
     **************************************************/
    public int firstIndex(Vs.IsThisOne<T> isThisOne) {
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

    public int firstIndexByValueSymbol(String valueSymbol) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (valueSymbol.equals(valueSymbol(this.ts.get(i)))) {
                return i;
            }
        }
        return -1;
    }

    public int firstIndex(T t) {
        return firstIndexByValueSymbol(valueSymbol(t));
    }

    /**************************************************
     * allIndex
     **************************************************/
    public IntegerVs allIndex(Vs.IsThisOne<T> isThisOne) {
        IntegerVs integerVs = new IntegerVs();
        if (isThisOne != null) {
            int count = count();
            for (int i = 0; i < count; i++) {
                if (isThisOne.isThisOne(i, this.ts.get(i))) {
                    integerVs.add(i);
                }
            }
        }
        return integerVs;
    }

    public IntegerVs allIndexByValueSymbol(String valueSymbol) {
        IntegerVs integerVs = new IntegerVs();
        int count = count();
        for (int i = 0; i < count; i++) {
            if (valueSymbol(this.ts.get(i)).equals(valueSymbol)) {
                integerVs.add(i);
            }
        }
        return integerVs;
    }

    public IntegerVs allIndex(T t) {
        return allIndexByValueSymbol(valueSymbol(t));
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
     * replaceFirst
     **************************************************/
    public THIS replaceFirst(T target, Vs.IsThisOne<T> isThisOne) {
        if (isThisOne != null) {
            int firstIndex = firstIndex(isThisOne);
            if (firstIndex >= 0) {
                replaceByIndex(firstIndex, target);
            }
        }
        return (THIS) this;
    }

    public THIS replaceFirstByValueSymbol(String valueSymbol, T target) {
        int firstIndex = firstIndexByValueSymbol(valueSymbol);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        }
        return (THIS) this;
    }

    public THIS replaceFirst(T symbolT, T target) {
        if (symbolT != null && target != null) {
            replaceFirstByValueSymbol(valueSymbol(symbolT), target);
        }
        return (THIS) this;
    }

    public THIS replaceFirst(T target) {
        return replaceFirst(target, target);
    }

    public THIS replaceFirst(T... targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target;
            for (int i = 0; i < count; i++) {
                target = targets[i];
                if (target != null) {
                    replaceFirstByValueSymbol(valueSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceFirst(List<T> targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target;
            for (int i = 0; i < count; i++) {
                target = targets.get(i);
                if (target != null) {
                    replaceFirstByValueSymbol(valueSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceFirst(THIS targetVs) {
        int count = targetVs.count();
        if (count > 0) {
            T target;
            for (int i = 0; i < count; i++) {
                target = (T) targetVs.ts.get(i);
                if (target != null) {
                    replaceFirstByValueSymbol(valueSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    /**************************************************
     * replaceAll
     **************************************************/
    public THIS replaceAll(T target, Vs.IsThisOne<T> isThisOne) {
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

    public THIS replaceAllByValueSymbol(String valueSymbol, T target) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (valueSymbol(this.ts.get(i)).equals(valueSymbol)) {
                replaceByIndex(i, target);
            }
        }
        return (THIS) this;
    }

    public THIS replaceAll(T symbolT, T target) {
        if (symbolT != null && target != null) {
            replaceAllByValueSymbol(valueSymbol(symbolT), target);
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
                    replaceAllByValueSymbol(valueSymbol(target), target);
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
                    replaceAllByValueSymbol(valueSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceAll(THIS targetVs) {
        int count = targetVs.count();
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = (T) targetVs.ts.get(i);
                if (target != null) {
                    replaceAllByValueSymbol(valueSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    /**************************************************
     * replaceFirstOrAdd
     **************************************************/
    public THIS replaceFirstOrAdd(T target, Vs.IsThisOne<T> isThisOne) {
        if (isThisOne != null) {
            int firstIndex = firstIndex(isThisOne);
            if (firstIndex >= 0) {
                replaceByIndex(firstIndex, target);
            } else if (target != null) {
                ts.add(target);
            }
        }
        return (THIS) this;
    }

    public THIS replaceFirstOrAddByValueSymbol(String valueSymbol, T target) {
        int firstIndex = firstIndexByValueSymbol(valueSymbol);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        } else if (target != null) {
            ts.add(target);
        }
        return (THIS) this;
    }

    public THIS replaceFirstOrAdd(T symbolT, T target) {
        if (symbolT != null && target != null) {
            replaceFirstOrAddByValueSymbol(valueSymbol(symbolT), target);
        }
        return (THIS) this;
    }

    public THIS replaceFirstOrAdd(T target) {
        return replaceFirstOrAdd(target, target);
    }

    public THIS replaceFirstOrAdd(T... targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = targets[i];
                if (target != null) {
                    replaceFirstOrAddByValueSymbol(valueSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceFirstOrAdd(List<T> targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = targets.get(i);
                if (target != null) {
                    replaceFirstOrAddByValueSymbol(valueSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceFirstOrAdd(THIS targetVs) {
        int count = targetVs.count();
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = (T) targetVs.ts.get(i);
                if (target != null) {
                    replaceFirstOrAddByValueSymbol(valueSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    /**************************************************
     * replaceAllOrAdd
     **************************************************/
    public THIS replaceAllOrAdd(T target, Ts.IsThisOne<T> isThisOne) {
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

    public THIS replaceAllOrAddByValueSymbol(String valueSymbol, T target) {
        int count = count();
        boolean isReplace = false;
        for (int i = 0; i < count; i++) {
            if (valueSymbol(this.ts.get(i)).equals(valueSymbol)) {
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
            replaceAllOrAddByValueSymbol(valueSymbol(symboleT), target);
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
                    replaceAllOrAddByValueSymbol(valueSymbol(target), target);
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
                    replaceAllOrAddByValueSymbol(valueSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceAllOrAdd(THIS targetVs) {
        int count = targetVs.count();
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = (T) targetVs.ts.get(i);
                if (target != null) {
                    replaceAllOrAddByValueSymbol(valueSymbol(target), target);
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
     * deleteFirst
     **************************************************/
    //删除第一个
    public THIS deleteFirst(Vs.IsThisOne<T> isThisOne) {
        if (isThisOne != null) {
            int firstIndex = firstIndex(isThisOne);
            if (firstIndex >= 0) {
                this.ts.remove(firstIndex);
            }
        }
        return (THIS) this;
    }

    public THIS deleteFirstByValueSymbol(String valueSymbol) {
        int firstIndex = firstIndexByValueSymbol(valueSymbol);
        if (firstIndex >= 0) {
            this.ts.remove(firstIndex);
        }
        return (THIS) this;
    }

    public THIS deleteFirstByValueSymbol(String... valueSymbols) {
        int count = CountTool.count(valueSymbols);
        for (int i = 0; i < count; i++) {
            int firstIndex = firstIndexByValueSymbol(valueSymbols[i]);
            if (firstIndex >= 0) {
                this.ts.remove(firstIndex);
            }
        }
        return (THIS) this;
    }

    public THIS deleteFirstByValueSymbol(List<String> valueSymbols) {
        int count = CountTool.count(valueSymbols);
        for (int i = 0; i < count; i++) {
            int firstIndex = firstIndexByValueSymbol(valueSymbols.get(i));
            if (firstIndex >= 0) {
                this.ts.remove(firstIndex);
            }
        }
        return (THIS) this;
    }

    public THIS deleteFirstByValueSymbol(StringVs valueSymbolVs) {
        int count = CountTool.count(valueSymbolVs);
        for (int i = 0; i < count; i++) {
            int firstIndex = firstIndexByValueSymbol(valueSymbolVs.ts.get(i));
            if (firstIndex >= 0) {
                this.ts.remove(firstIndex);
            }
        }
        return (THIS) this;
    }


    public THIS deleteFirst(T target) {
        if (target != null) {
            deleteFirstByValueSymbol(valueSymbol(target));
        }
        return (THIS) this;
    }

    protected THIS deleteFirst(Vs.EachGetter<T> getter) {
        if (getter != null) {
            int count = getter.count();
            if (count > 0) {
                T target = null;
                for (int i = 0; i < count; i++) {
                    target = getter.get(i);
                    if (target != null) {
                        deleteFirstByValueSymbol(valueSymbol(target));
                    }
                }
            }
        }
        return (THIS) this;
    }

    public THIS deleteFirst(T... targets) {
        return deleteFirst(new Vs.EachGetter<T>() {
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

    public THIS deleteFirst(List<T> targets) {
        return deleteFirst(new Vs.EachGetter<T>() {
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

    public THIS deleteFirst(THIS targetVs) {
        return deleteFirst(new Vs.EachGetter<T>() {
            @Override
            public int count() {
                return CountTool.count(targetVs);
            }

            @Override
            public T get(int position) {
                return (T) targetVs.ts.get(position);
            }
        });
    }

    /**************************************************
     * deleteAll
     **************************************************/
    public THIS deleteAll(Vs.IsThisOne<T> isThisOne) {
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

    public THIS deleteAllByValueSymbol(String valueSymbol) {
        List<T> newTs = new ArrayList<>();
        int count = count();
        T t;
        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (!valueSymbol(t).equals(valueSymbol)) {
                newTs.add(t);
            }
        }
        this.ts.clear();
        this.ts.addAll(newTs);
        return (THIS) this;
    }

    private THIS deleteAllByValueSymbol(Vs.EachGetter<String> getter) {
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
                    tValueSymbol = valueSymbol(t);

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

    public THIS deleteAllByValueSymbol(String... valueSymbols) {
        return deleteAllByValueSymbol(new Vs.EachGetter<String>() {
            @Override
            public int count() {
                return CountTool.count(valueSymbols);
            }

            @Override
            public String get(int position) {
                return valueSymbols[position];
            }
        });
    }

    public THIS deleteAllByValueSymbol(List<String> valueSymbols) {
        return deleteAllByValueSymbol(new Vs.EachGetter<String>() {
            @Override
            public int count() {
                return CountTool.count(valueSymbols);
            }

            @Override
            public String get(int position) {
                return valueSymbols.get(position);
            }
        });
    }

    public THIS deleteAllByValueSymbol(StringVs valueSymbolVs) {
        return deleteAllByValueSymbol(new Vs.EachGetter<String>() {
            @Override
            public int count() {
                return CountTool.count(valueSymbolVs);
            }

            @Override
            public String get(int position) {
                return valueSymbolVs.ts.get(position);
            }
        });
    }

    //删除所有
    public THIS deleteAll(T target) {
        if (target != null) {
            deleteAllByValueSymbol(valueSymbol(target));
        }
        return (THIS) this;
    }

    protected THIS deleteAll(Vs.EachGetter<T> getter) {
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
                    tValueSymbol = valueSymbol(t);

                    isSame = false;
                    for (int j = 0; j < valueSymbolCount; j++) {
                        if (tValueSymbol.equals(valueSymbol(getter.get(j)))) {
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
        return deleteAll(new Vs.EachGetter<T>() {
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
        return deleteAll(new Vs.EachGetter<T>() {
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

    public THIS deleteAll(THIS targetVs) {
        return deleteAll(new Vs.EachGetter<T>() {
            @Override
            public int count() {
                return CountTool.count(targetVs);
            }

            @Override
            public T get(int position) {
                return (T) targetVs.ts.get(position);
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    // 转换
    //
    ///////////////////////////////////////////////////////

    public <TARGET extends ValueSymbol> ValueSymbolVs<TARGET> convert(Vs.Convert<T, TARGET> convert) {
        ValueSymbolVs baseVs = new ValueSymbolVs();
        if (convert != null) {
            int count = count();
            TARGET target;
            for (int i = 0; i < count; i++) {
                target = convert.convert(i, this.ts.get(i));
                if (target != null) {
                    baseVs.add(target);
                }
            }
        }
        return baseVs;
    }

    public <TARGET, VS extends CoreVs> VS convert(Class<VS> vsClass, Vs.Convert<T, TARGET> convert) {
        VS vs;
        try {
            vs = (VS) vsClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int count = count();
        TARGET target;
        for (int i = 0; i < count; i++) {
            target = convert.convert(i, this.ts.get(i));
            if (target != null) {
                vs.add(target);
            }
        }
        return vs;
    }

    public <TARGET extends ValueSymbol> ValueSymbolVs<TARGET> convertList(Vs.Convert<T, List<TARGET>> convert) {
        ValueSymbolVs<TARGET> vs = new ValueSymbolVs<>();
        if (convert != null) {
            int count = count();
            List<TARGET> list;
            for (int i = 0; i < count; i++) {
                list = convert.convert(i, this.ts.get(i));
                if (!CountTool.isNull(list)) {
                    vs.add(list);
                }
            }
        }
        return vs;
    }

    public <VS extends CoreVs, TARGET> VS convertList(Class<VS> vsClass, Vs.Convert<T, List<TARGET>> convert) {
        VS vs;
        try {
            vs = (VS) vsClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (convert != null) {
            int count = count();
            List<TARGET> list;
            for (int i = 0; i < count; i++) {
                list = convert.convert(i, this.ts.get(i));
                if (!CountTool.isNull(list)) {
                    vs.add(list);
                }
            }
        }
        return vs;
    }

    ///////////////////////////////////////////////////////
    //
    // 获取最大最小值
    //
    ///////////////////////////////////////////////////////

    public MaxMin<T> maxMin(Vs.NowMax<T> nowMax) {
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
        return maxMin(new Vs.NowMax<T>() {
            @Override
            public boolean isNowMax(T last, T now) {
                return toInt.toInt(now) > toInt.toInt(last);
            }
        });
    }

    public MaxMin<T> maxMin(ToLong<T> toLong) {
        return maxMin(new Vs.NowMax<T>() {
            @Override
            public boolean isNowMax(T last, T now) {
                return toLong.toLong(now) > toLong.toLong(last);
            }
        });
    }

    public MaxMin<T> maxMin(ToDouble<T> toDouble) {
        return maxMin(new Vs.NowMax<T>() {
            @Override
            public boolean isNowMax(T last, T now) {
                return toDouble.toDouble(now) > toDouble.toDouble(last);
            }
        });
    }

    public MaxMin<T> maxMin(ToFloat<T> toFloat) {
        return maxMin(new Vs.NowMax<T>() {
            @Override
            public boolean isNowMax(T last, T now) {
                return toFloat.toFloat(now) > toFloat.toFloat(last);
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    // neighbor方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * 基础方法
     **************************************************/
    protected Vs.NeighborIndex obtainNeighborIndexByIndex(int index, boolean isNext) {
        int count = count();
        if (count == 1) {
            return null;
        }
        if (index < 0) {
            return null;
        }

        int step = isNext ? 1 : -1;

        Vs.NeighborIndex neighborIndex = new Vs.NeighborIndex();
        neighborIndex.currentIndex = index;
        if (neighborIndex.currentIndex == (isNext ? (count - 1) : 0)) {
            neighborIndex.neighborIndex = neighborIndex.currentIndex - step;
        } else {
            neighborIndex.neighborIndex = neighborIndex.currentIndex + step;
        }
        return neighborIndex;
    }

    protected Vs.NeighborIndex obtainNeighborIndex(Vs.IsThisOne<T> isThisOne, boolean isNext) {
        return obtainNeighborIndexByIndex(firstIndex(isThisOne), isNext);
    }

    protected Vs.NeighborIndex obtainNeighborIndexByValueSymbol(String valueSymbol, boolean isNext) {
        return obtainNeighborIndexByIndex(firstIndexByValueSymbol(valueSymbol), isNext);
    }

    protected Vs.NeighborIndex obtainNeighborIndex(T t, boolean isNext) {
        return obtainNeighborIndexByIndex(firstIndex(t), isNext);
    }

    protected T obtainNeighborData(Vs.NeighborIndex neighborIndex) {
        if (neighborIndex == null) return null;
        return getByIndex(neighborIndex.neighborIndex);
    }

    /**************************************************
     * NeighborIndex下一个优先
     **************************************************/
    public Vs.NeighborIndex obtainNeighborIndexWhenNextPriority(Vs.IsThisOne<T> isThisOne) {
        return obtainNeighborIndex(isThisOne, true);
    }

    public Vs.NeighborIndex obtainNeighborIndexWhenNextPriorityByValueSymbol(String valueSymbol) {
        return obtainNeighborIndexByValueSymbol(valueSymbol, true);
    }

    public Vs.NeighborIndex obtainNeighborIndexWhenNextPriority(T t) {
        return obtainNeighborIndex(t, true);
    }

    /**************************************************
     * NeighborData下一个优先
     **************************************************/
    public T obtainNeighborDataWhenNextPriority(Vs.IsThisOne<T> isThisOne) {
        return obtainNeighborData(obtainNeighborIndexWhenNextPriority(isThisOne));
    }

    public T obtainNeighborDataWhenNextPriorityByVauleSymbol(String valueSymbol) {
        return obtainNeighborData(obtainNeighborIndexWhenNextPriorityByValueSymbol(valueSymbol));
    }

    public T obtainNeighborDataWhenNextPriority(T t) {
        return obtainNeighborData(obtainNeighborIndexWhenNextPriority(t));
    }

    /**************************************************
     * NeighborIndex上一个优先
     **************************************************/
    public Vs.NeighborIndex obtainNeighborIndexWhenPrePriority(Vs.IsThisOne<T> isThisOne) {
        return obtainNeighborIndex(isThisOne, false);
    }

    public Vs.NeighborIndex obtainNeighborIndexWhenPrePriorityByValueSymbol(String valueSymbol) {
        return obtainNeighborIndexByValueSymbol(valueSymbol, false);
    }

    public Vs.NeighborIndex obtainNeighborIndexWhenPrePriority(T t) {
        return obtainNeighborIndex(t, false);
    }

    /**************************************************
     * NeighborData下一个优先
     **************************************************/
    public T obtainNeighborDataWhenPrePriority(Vs.IsThisOne<T> isThisOne) {
        return obtainNeighborData(obtainNeighborIndexWhenPrePriority(isThisOne));
    }

    public T obtainNeighborDataWhenPrePriorityByValueSymbol(String valueSymbol) {
        return obtainNeighborData(obtainNeighborIndexWhenPrePriorityByValueSymbol(valueSymbol));
    }

    public T obtainNeighborDataWhenPrePriority(T t) {
        return obtainNeighborData(obtainNeighborIndexWhenPrePriority(t));
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
    public T findFinal(Vs.IsNow<T> isNow) {
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
    public <K, V> Map<K, V> toMap(Vs.ToMap<K, V, T> toMap) {
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
        return toMap(new Vs.ToMap<String, T, T>() {
            @Override
            public void deal(Map<String, T> map, int i, T t) {
                map.put(valueSymbol(t), t);
            }
        });
    }

    public Map<T, T> toTMap() {
        return toMap(new Vs.ToMap<T, T, T>() {
            @Override
            public void deal(Map<T, T> map, int i, T t) {
                map.put(t, t);
            }
        });
    }

}
