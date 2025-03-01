package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;

import java.util.ArrayList;
import java.util.List;

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
            t = getByIndex(i);
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
            t = getByIndex(i);
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
            t = getByIndex(i);
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
            t = getByIndex(i);
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
        return getByIndex(count() - 1);
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
            t = getByIndex(i);
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
            t = getByIndex(i);
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
            if (isThisOne.isThisOne(i, getByIndex(i))) {
                return i;
            }
        }
        return -1;
    }

    public IntegerVs allIndexByValueSymbol(String valueSymbol) {
        IntegerVs integerVs = new IntegerVs();
        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = getByIndex(i);
            if (valueSymbol(t).equals(valueSymbol)) {
                integerVs.add(i);
            }
        }
        return integerVs;
    }

    public IntegerVs allIndex(T t) {
        return allIndexByValueSymbol(valueSymbol(t));
    }

    public IntegerVs allIndex(Vs.IsThisOne<T> isThisOne) {
        if (isThisOne == null) return new IntegerVs();

        IntegerVs integerVs = new IntegerVs();
        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = getByIndex(i);
            if (isThisOne.isThisOne(i, t)) {
                integerVs.add(i);
            }
        }
        return integerVs;
    }

    /**************************************************
     * 遍历
     **************************************************/
    //正向遍历
    public THIS ls(int step, Vs.EachTs<T> eachTs) {
        if (eachTs == null || step <= 0) return (THIS) this;

        int count = count();
        for (int i = 0; i < count; i += step) {
            if (eachTs.each(i, getByIndex(i))) {
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
            if (eachTs.each(i, getByIndex(i))) {
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

    public THIS replaceFirst(T target) {
        if (target != null) {
            replaceFirstByValueSymbol(valueSymbol(target), target);
        }
        return (THIS) this;
    }

    //替换所有
    public THIS replaceAll(T target, Vs.IsThisOne<T> isThisOne) {
        if (isThisOne != null) {
            int count = count();
            for (int i = 0; i < count; i++) {
                if (isThisOne.isThisOne(i, getByIndex(i))) {
                    replaceByIndex(i, target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceAllByValueSymbol(String valueSymbol, T target) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (valueSymbol(getByIndex(i)).equals(valueSymbol)) {
                replaceByIndex(i, target);
            }
        }
        return (THIS) this;
    }

    public THIS replaceAll(T target) {
        if (target != null) {
            replaceAllByValueSymbol(valueSymbol(target), target);
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

    public THIS repalceFirstOrAdd(T target) {
        if (target != null) {
            replaceFirstOrAddByValueSymbol(valueSymbol(target), target);
        }
        return (THIS) this;
    }

    //替换所有或者添加
    public THIS replaceAllOrAdd(T target, Ts.IsThisOne<T> isThisOne) {
        if (isThisOne != null) {
            int count = count();
            boolean isReplace = false;
            for (int i = 0; i < count; i++) {
                if (isThisOne.isThisOne(i, getByIndex(i))) {
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
            if (valueSymbol(getByIndex(i)).equals(valueSymbol)) {
                replaceByIndex(i, target);
                isReplace = true;
            }
        }
        if (!isReplace && target != null) {
            this.ts.add(target);
        }

        return (THIS) this;
    }

    public THIS repalceAllOrAdd(T target) {
        if (target != null) {
            replaceAllOrAddByValueSymbol(valueSymbol(target), target);
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

    public THIS deleteFirst(Vs.IsThisOne<T> isThisOne) {
        if (isThisOne == null) return (THIS) this;
        int index = firstIndex(isThisOne);
        if (index >= 0) {
            ts.remove(index);
        }
        return (THIS) this;
    }

    public THIS deleteAll(Vs.IsThisOne<T> isThisOne) {
        if (isThisOne == null) return (THIS) this;

        List<T> newTs = new ArrayList<>();

        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = getByIndex(i);
            if (!isThisOne.isThisOne(i, t)) {
                newTs.add(t);
            }
        }
        this.ts.clear();
        this.ts.addAll(newTs);
        return (THIS) this;
    }


}
