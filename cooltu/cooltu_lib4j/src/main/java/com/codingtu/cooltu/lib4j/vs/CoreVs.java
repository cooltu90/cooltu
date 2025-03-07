package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.function.ToDouble;
import com.codingtu.cooltu.lib4j.function.ToInt;
import com.codingtu.cooltu.lib4j.function.ToLong;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.OtherTool;
import com.codingtu.cooltu.lib4j.ts.CoreTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.data.symbol.ValueSymbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CoreVs<T, THIS extends CoreVs> {

    protected List<T> ts;

    /**************************************************
     * 构造函数
     **************************************************/
    public CoreVs() {
        this.ts = new ArrayList<>();
    }

    public CoreVs(List<T> list) {
        if (list == null) this.ts = new ArrayList<>();
        else this.ts = list;
    }

    /**************************************************
     *
     **************************************************/
    protected abstract String valueSymbol(T t);

    /**************************************************
     * 获取ts的属性
     **************************************************/
    public int count() {
        return CountTool.count(ts);
    }

    public boolean isNull() {
        return count() <= 0;
    }


    /**************************************************
     * get
     **************************************************/
    //通过索引值获取
    public T getByIndex(int index) {
        if (index < count() && index >= 0) {
            return ts.get(index);
        }
        return null;
    }

    public T getFirstByValueSymbol(String valueSymbol) {
        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (valueSymbol(t).equals(valueSymbol)) {
                return t;
            }
        }
        return null;
    }

    public T getFirst(T t) {
        return getFirstByValueSymbol(valueSymbol(t));
    }

    public T getFirst(Vs.IsThisOne<T> isThisOne) {
        if (isThisOne == null) {
            return null;
        }

        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (isThisOne.isThisOne(i, t)) {
                return t;
            }
        }
        return null;
    }

    public THIS getAllByValueSymbol(String valueSymbol) {
        int count = count();
        T t = null;

        THIS ThisObj;

        try {
            ThisObj = (THIS) this.getClass().getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (valueSymbol(t).equals(valueSymbol)) {
                ThisObj.add(t);
            }
        }
        return ThisObj;
    }

    public THIS getAll(T t) {
        return getAllByValueSymbol(valueSymbol(t));
    }

    public THIS getAll(Vs.IsThisOne<T> isThisOne) {
        if (isThisOne == null) {
            try {
                return (THIS) this.getClass().getConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        int count = count();
        T t = null;

        THIS ThisObj;

        try {
            ThisObj = (THIS) this.getClass().getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (isThisOne.isThisOne(i, t)) {
                ThisObj.add(t);
            }
        }
        return ThisObj;
    }

    public T getLast() {
        if (count() <= 0) {
            return null;
        }
        return this.ts.get(count() - 1);
    }

    /**************************************************
     * has
     **************************************************/
    public boolean hasByValueSymbol(String valueSymbol) {
        return getFirstByValueSymbol(valueSymbol) != null;
    }

    public boolean has(T t) {
        return hasByValueSymbol(valueSymbol(t));
    }

    public boolean has(Vs.IsThisOne<T> isThisOne) {
        if (isThisOne == null) return false;

        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (isThisOne.isThisOne(i, t)) {
                return true;
            }
        }
        return false;
    }

    /**************************************************
     * index
     **************************************************/
    public int firstIndexByValueSymbol(String valueSymbol) {
        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (valueSymbol(t).equals(valueSymbol)) {
                return i;
            }
        }
        return -1;
    }

    public int firstIndex(T t) {
        return firstIndexByValueSymbol(valueSymbol(t));
    }

    public int firstIndex(Vs.IsThisOne<T> isThisOne) {
        if (isThisOne == null) return -1;

        int count = count();
        for (int i = 0; i < count; i++) {
            if (isThisOne.isThisOne(i, this.ts.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public IntegerVs allIndexByValueSymbol(String valueSymbol) {
        List<Integer> indexs = new ArrayList<>();
        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (valueSymbol(t).equals(valueSymbol)) {
                indexs.add(i);
            }
        }
        return Vs.ints(indexs);
    }

    public IntegerVs allIndex(T t) {
        return allIndexByValueSymbol(valueSymbol(t));
    }

    public IntegerVs allIndex(Vs.IsThisOne<T> isThisOne) {
        if (isThisOne == null) return new IntegerVs();

        List<Integer> indexs = new ArrayList<>();
        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = this.ts.get(i);
            if (isThisOne.isThisOne(i, t)) {
                indexs.add(i);
            }
        }
        return Vs.ints(indexs);
    }

    /**************************************************
     * 遍历
     **************************************************/
    //正向遍历
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


    //反向遍历
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

    /**************************************************
     *
     * 打印
     *
     **************************************************/
    public THIS log() {
        ls(new Vs.EachTs<T>() {
            @Override
            public boolean each(int position, T t) {
                LibLogs.i(t);
                return false;
            }
        });
        return (THIS) this;
    }

    /**************************************************
     * add
     **************************************************/
    public THIS add(T... t) {
        for (int i = 0; i < CountTool.count(t); i++) {
            this.ts.add(t[i]);
        }
        return (THIS) this;
    }

    public THIS add(List<T> ts) {
        if (!CountTool.isNull(ts)) {
            this.ts.addAll(ts);
        }
        return (THIS) this;
    }

    public THIS add(CoreVs ts) {
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

    /**************************************************
     * replace
     **************************************************/
    public THIS replaceByIndex(int index, T t) {
        this.ts.set(index, t);
        return (THIS) this;
    }

    //替换第一个
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

    public THIS replaceFirst(T... targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target = null;
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
            T target = null;
            for (int i = 0; i < count; i++) {
                target = targets.get(i);
                if (target != null) {
                    replaceFirstByValueSymbol(valueSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceFirst(THIS aThis) {
        int count = aThis.count();
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = (T) aThis.ts.get(i);
                if (target != null) {
                    replaceFirstByValueSymbol(valueSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }


    //替换所有
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

    public THIS replaceAll(THIS aThis) {
        int count = aThis.count();
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = (T) aThis.ts.get(i);
                if (target != null) {
                    replaceAllByValueSymbol(valueSymbol(target), target);
                }
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


    //替换第一个或者添加
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

    public THIS replaceFirstOrAdd(THIS aThis) {
        int count = aThis.count();
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = (T) aThis.ts.get(i);
                if (target != null) {
                    replaceFirstOrAddByValueSymbol(valueSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceFirstOrAdd(T symbolT, T target) {
        if (symbolT != null && target != null) {
            replaceFirstOrAddByValueSymbol(valueSymbol(symbolT), target);
        }
        return (THIS) this;
    }

    //替换所有或者添加
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

    public THIS replaceAllOrAdd(THIS aThis) {
        int count = aThis.count();
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = (T) aThis.ts.get(i);
                if (target != null) {
                    replaceAllOrAddByValueSymbol(valueSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceAllOrAdd(T symboleT, T target) {
        if (symboleT != null && target != null) {
            replaceAllOrAddByValueSymbol(valueSymbol(symboleT), target);
        }
        return (THIS) this;
    }

    /**************************************************
     * delete
     **************************************************/
    public THIS deleteByIndex(int position) {
        this.ts.remove(position);
        return (THIS) this;
    }

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

    public THIS deleteFirst(T target) {
        if (target != null) {
            deleteFirstByValueSymbol(valueSymbol(target));
        }
        return (THIS) this;
    }

    public THIS deleteFirst(T... targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = targets[i];
                if (target != null)
                    deleteFirstByValueSymbol(valueSymbol(target));
            }
        }
        return (THIS) this;
    }

    public THIS deleteFirst(List<T> targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = targets.get(i);
                if (target != null)
                    deleteFirstByValueSymbol(valueSymbol(target));
            }
        }
        return (THIS) this;
    }

    public THIS deleteFirst(THIS aThis) {
        int count = aThis.count();
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = (T) aThis.ts.get(i);
                if (target != null)
                    deleteFirstByValueSymbol(valueSymbol(target));
            }
        }
        return (THIS) this;
    }

    //删除所有
    public THIS deleteAll(Vs.IsThisOne<T> isThisOne) {
        if (isThisOne != null) {
            List<T> newTs = new ArrayList<>();
            int count = count();
            T t = null;
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
        T t = null;
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

    public THIS deleteAll(T target) {
        if (target != null) {
            deleteAllByValueSymbol(valueSymbol(target));
        }
        return (THIS) this;
    }

    public THIS deleteAll(T... targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = targets[i];
                if (target != null)
                    deleteAllByValueSymbol(valueSymbol(target));
            }
        }
        return (THIS) this;
    }

    public THIS deleteAll(List<T> targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = targets.get(i);
                if (target != null)
                    deleteAllByValueSymbol(valueSymbol(target));
            }
        }
        return (THIS) this;
    }

    public THIS deleteAll(THIS aThis) {
        int count = aThis.count();
        if (count > 0) {
            T target = null;
            for (int i = 0; i < count; i++) {
                target = (T) aThis.ts.get(i);
                if (target != null)
                    deleteAllByValueSymbol(valueSymbol(target));
            }
        }
        return (THIS) this;
    }

    /**************************************************
     * 转换
     **************************************************/
    public <TARGET extends ValueSymbol> BaseVs<TARGET> convert(Vs.Convert<T, TARGET> convert) {
        BaseVs baseVs = new BaseVs();
        if (convert != null) {
            int count = count();
            TARGET target = null;
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
        TARGET target = null;
        for (int i = 0; i < count; i++) {
            target = convert.convert(i, this.ts.get(i));
            if (target != null) {
                vs.add(target);
            }
        }
        return vs;
    }

    public <TARGET extends ValueSymbol> BaseVs<TARGET> convertList(Vs.Convert<T, List<TARGET>> convert) {
        BaseVs<TARGET> vs = new BaseVs<>();
        if (convert != null) {
            int count = count();
            List<TARGET> list = null;
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
            List<TARGET> list = null;
            for (int i = 0; i < count; i++) {
                list = convert.convert(i, this.ts.get(i));
                if (!CountTool.isNull(list)) {
                    vs.add(list);
                }
            }
        }
        return vs;
    }

    /**************************************************
     * 获取最大最小值
     **************************************************/
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

    /**************************************************
     * 排序
     **************************************************/
    public THIS sort(Comparator<T> comparator) {
        if (count() > 0) {
            Collections.sort(ts, comparator);
        }
        return (THIS) this;
    }

    /**************************************************
     * 清除
     **************************************************/
    public THIS clear() {
        this.ts.clear();
        return (THIS) this;
    }

    /**************************************************
     * nearby neighbor
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

    //下一个优先
    public Vs.NeighborIndex obtainNeighborIndexWhenNextPriority(Vs.IsThisOne<T> isThisOne) {
        return obtainNeighborIndex(isThisOne, true);
    }

    public Vs.NeighborIndex obtainNeighborIndexWhenNextPriorityByValueSymbol(String valueSymbol) {
        return obtainNeighborIndexByValueSymbol(valueSymbol, true);
    }

    public Vs.NeighborIndex obtainNeighborIndexWhenNextPriority(T t) {
        return obtainNeighborIndex(t, true);
    }

    public T obtainNeighborDataWhenNextPriority(Vs.IsThisOne<T> isThisOne) {
        return obtainNeighborData(obtainNeighborIndexWhenNextPriority(isThisOne));
    }

    public T obtainNeighborDataWhenNextPriorityByVauleSymbol(String valueSymbol) {
        return obtainNeighborData(obtainNeighborIndexWhenNextPriorityByValueSymbol(valueSymbol));
    }

    public T obtainNeighborDataWhenNextPriority(T t) {
        return obtainNeighborData(obtainNeighborIndexWhenNextPriority(t));
    }

    //上一个优先
    public Vs.NeighborIndex obtainNeighborIndexWhenPrePriority(Vs.IsThisOne<T> isThisOne) {
        return obtainNeighborIndex(isThisOne, false);
    }

    public Vs.NeighborIndex obtainNeighborIndexWhenPrePriorityByValueSymbol(String valueSymbol) {
        return obtainNeighborIndexByValueSymbol(valueSymbol, false);
    }

    public Vs.NeighborIndex obtainNeighborIndexWhenPrePriority(T t) {
        return obtainNeighborIndex(t, false);
    }

    public T obtainNeighborDataWhenPrePriority(Vs.IsThisOne<T> isThisOne) {
        return obtainNeighborData(obtainNeighborIndexWhenPrePriority(isThisOne));
    }

    public T obtainNeighborDataWhenPrePriorityByValueSymbol(String valueSymbol) {
        return obtainNeighborData(obtainNeighborIndexWhenPrePriorityByValueSymbol(valueSymbol));
    }

    public T obtainNeighborDataWhenPrePriority(T t) {
        return obtainNeighborData(obtainNeighborIndexWhenPrePriority(t));
    }

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

    /**************************************************
     *
     **************************************************/
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

    /**************************************************
     *
     **************************************************/
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

    /**************************************************
     *
     **************************************************/
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

}
