package com.codingtu.cooltu.lib4j.ts;

import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.data.symbol.Symbol;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.tools.CountTool;

import java.util.*;

public class CoreTs<T, THIS> {
    protected List<T> ts;

    /**************************************************
     *
     * 构造函数
     *
     **************************************************/
    public CoreTs() {
        this.ts = new ArrayList<>();
    }

    public CoreTs(List<T> list) {
        if (list == null)
            this.ts = new ArrayList<>();
        else
            this.ts = list;
    }


    /**************************************************
     *
     * 获取ts的属性
     *
     **************************************************/

    public int count() {
        return CountTool.count(ts);
    }

    public boolean isNull() {
        return count() <= 0;
    }

    public THIS set(int index, T t) {
        this.ts.set(index, t);
        return (THIS) this;
    }

    public T getLast() {
        if (count() <= 0) {
            return null;
        }
        return get(count() - 1);
    }

    /**************************************************
     *
     * 打印
     *
     **************************************************/
    public THIS log() {
        ls(new Ts.EachTs<T>() {
            @Override
            public boolean each(int position, T t) {
                LibLogs.i(t);
                return false;
            }
        });
        return (THIS) this;
    }

    /**************************************************
     *
     * 遍历
     *
     **************************************************/


    //正向遍历
    public THIS ls(int step, Ts.EachTs<T> eachTs) {
        if (eachTs == null || step <= 0)
            return (THIS) this;

        int count = count();
        for (int i = 0; i < count; i += step) {
            if (eachTs.each(i, get(i))) {
                return (THIS) this;
            }
        }
        return (THIS) this;
    }

    public THIS ls(Ts.EachTs<T> eachTs) {
        return ls(1, eachTs);
    }

    //反向遍历
    public THIS rls(int step, Ts.EachTs<T> eachTs) {
        if (eachTs == null || step <= 0)
            return (THIS) this;

        int count = count();
        for (int i = count - 1; i >= 0; i -= step) {
            if (eachTs.each(i, get(i))) {
                return (THIS) this;
            }
        }
        return (THIS) this;
    }

    public THIS rls(Ts.EachTs<T> eachTs) {
        return rls(1, eachTs);
    }

    /**************************************************
     *
     * get方法，获取元素
     *
     **************************************************/
    //通过索引值获取
    public T get(int index) {
        if (index < count() && index >= 0) {
            return ts.get(index);
        }
        return null;
    }

    //通过判定是否为当前元素获取
    public T get(Ts.IsThisOne<T> isThisOne) {
        if (isThisOne == null)
            return null;

        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = get(i);
            if (isThisOne.isThisOne(i, t)) {
                return t;
            }
        }
        return null;
    }

    /**************************************************
     *
     * has
     *
     **************************************************/
    //通过判定接口来判断是否包含
    public boolean has(Ts.IsThisOne<T> isThisOne) {
        return get(isThisOne) != null;
    }

    public boolean contains(T t) {
        return this.ts.contains(t);
    }


    /**************************************************
     *
     * 获取索引
     *
     **************************************************/

    public int index(Ts.IsThisOne<T> isThisOne) {
        if (isThisOne == null)
            return -1;
        int count = count();
        T t = null;
        for (int i = 0; i < count; i++) {
            t = get(i);
            if (isThisOne.isThisOne(i, t)) {
                return i;
            }
        }
        return -1;
    }

    /**************************************************
     *
     * 添加
     *
     **************************************************/
    public THIS add(T... t) {
        for (int i = 0; i < CountTool.count(t); i++) {
            this.ts.add(t[i]);
        }
        return (THIS) this;
    }

    public THIS addn(int n, T t) {
        for (int i = 0; i < n; i++) {
            this.ts.add(t);
        }
        return (THIS) this;
    }

    public THIS add(List<T> ts) {
        if (!CountTool.isNull(ts)) {
            this.ts.addAll(ts);
        }
        return (THIS) this;
    }

    public THIS add(CoreTs ts) {
        if (!CountTool.isNull(ts)) {
            this.ts.addAll(ts.toList());
        }
        return (THIS) this;
    }

    /**************************************************
     *
     * 替换
     * 第一个符合条件的项被替换为提供的对象
     *
     **************************************************/
    public THIS replace(T target, Ts.IsThisOne<T> isThisOne) {
        if (target == null || isThisOne == null)
            return (THIS) this;

        int index = index(isThisOne);
        if (index >= 0) {
            set(index, target);
        }
        return (THIS) this;
    }

    /**************************************************
     *
     * replaceAll：所有符合条件的项被替换为提供的对象
     *
     **************************************************/
    public THIS replaceAll(T target, Ts.IsThisOne<T> isThisOne) {
        if (target == null || isThisOne == null)
            return (THIS) this;

        int count = count();
        for (int i = 0; i < count; i++) {
            if (isThisOne.isThisOne(i, get(i))) {
                set(i, target);
            }
        }
        return (THIS) this;
    }

    /**************************************************
     *
     * replaceOrAdd
     * 第一个符合条件的项被替换为提供的对象
     * 如果没有符合条件的项则直接添加到列表里
     *
     **************************************************/

    public THIS replaceOrAdd(T target, Ts.IsThisOne<T> isThisOne) {
        if (target == null || isThisOne == null)
            return (THIS) this;

        int index = index(isThisOne);
        if (index >= 0) {
            set(index, target);
        } else {
            ts.add(target);
        }
        return (THIS) this;
    }


    /**************************************************
     *
     * replaceAllOrAdd：
     * 所有符合条件的项被替换为提供的对象
     * 如果没有符合条件的项则直接添加到列表里
     *
     **************************************************/

    public THIS replaceAllOrAdd(T target, Ts.IsThisOne<T> isThisOne) {
        if (target == null || isThisOne == null)
            return (THIS) this;

        int count = count();
        boolean isReplace = false;
        for (int i = 0; i < count; i++) {
            if (isThisOne.isThisOne(i, get(i))) {
                set(i, target);
                isReplace = true;
            }
        }
        if (!isReplace) {
            this.ts.add(target);
        }
        return (THIS) this;
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    public THIS delete(int position) {
        this.ts.remove(position);
        return (THIS) this;
    }



    /**************************************************
     *
     * 删除：删除第一个符合条件的项
     *
     **************************************************/
    public THIS deleteOnce(Ts.IsThisOne<T> isThisOne) {
        if (isThisOne == null)
            return (THIS) this;
        int index = index(isThisOne);
        if (index >= 0) {
            ts.remove(index);
        }
        return (THIS) this;
    }
    /**************************************************
     *
     * 删除全部：删除所有符合条件的项
     *
     **************************************************/
    public THIS delete(Ts.IsThisOne<T> isThisOne) {
        if (isThisOne == null)
            return (THIS) this;
        List<T> ts = convert(new Ts.Convert<T, T>() {
            @Override
            public T convert(int index, T t) {
                return isThisOne.isThisOne(index, t) ? null : t;
            }
        }).toList();
        this.ts.clear();
        this.ts.addAll(ts);
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // 分割
    //
    ///////////////////////////////////////////////////////




    /**************************************************
     *
     * 转换
     *
     **************************************************/

    public <S> BaseTs<S> convert(Ts.Convert<T, S> convert) {
        if (convert == null)
            return null;

        ArrayList<S> list = new ArrayList<>();
        int count = count();
        for (int i = 0; i < count; i++) {
            S s = convert.convert(i, get(i));
            if (s != null) {
                list.add(s);
            }
        }

        return new BaseTs<>(list);
    }

    public <S> BaseTs<S> convertList(Ts.Convert<T, List<S>> convert) {
        if (convert == null) {
            return null;
        }

        ArrayList<S> list = new ArrayList<>();
        int count = count();
        for (int i = 0; i < count; i++) {
            List<S> ss = convert.convert(i, get(i));
            if (!CountTool.isNull(ss)) {
                list.addAll(ss);
            }
        }
        return new BaseTs<>(list);
    }

    /**************************************************
     *
     * 获取最大最小值
     *
     **************************************************/


    public MaxMin<T> maxMin(Ts.NowMax<T> nowMax) {
        if (nowMax == null)
            return null;

        int count = count();
        MaxMin<T> maxMin = null;
        for (int i = 0; i < count; i++) {
            T now = get(i);
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

    /**************************************************
     *
     * 查找最后一个符合条件的元素
     *
     **************************************************/

    public T findFinal(Ts.IsNow<T> isNow) {
        if (isNow == null)
            return null;

        int count = count();
        T last = null;
        for (int i = 0; i < count; i++) {
            T now = get(i);
            last = last == null ? now : (isNow.isNow(last, now) ? now : last);
        }
        return last;
    }

    /**************************************************
     *
     * 全部替换为默认元素
     *
     **************************************************/
    public THIS fill(T defaultT) {
        int count = count();
        for (int i = 0; i < count; i++) {
            set(i, defaultT);
        }
        return (THIS) this;
    }

    /**************************************************
     *
     * 排序
     *
     **************************************************/
    public THIS sort(Comparator<T> comparator) {
        if (count() > 0) {
            Collections.sort(ts, comparator);
        }
        return (THIS) this;
    }

    /**************************************************
     *
     * 转换列表
     *
     **************************************************/
    public List<T> toList() {
        return this.ts;
    }


    public T[] toArray() {
        int count = count();
        if (count <= 0) {
            return null;
        }
        T[] newArray = (T[]) java.lang.reflect.Array.newInstance
                (get(0).getClass(), count);
        ls(new Ts.EachTs<T>() {
            @Override
            public boolean each(int position, T t) {
                newArray[position] = t;
                return false;
            }
        });
        return newArray;
    }

    public boolean[] toBoolean() {
        int count = count();
        if (count > 0) {
            T t = get(0);
            if (t instanceof Boolean) {
                boolean[] booleans = new boolean[count];
                for (int i = 0; i < count; i++) {
                    t = ts.get(i);
                    if (t == null) {
                        booleans[i] = false;
                    } else {
                        booleans[i] = (boolean) t;
                    }
                }
                return booleans;
            }
        }
        return null;
    }

    public byte[] toBytes() {
        int count = count();
        if (count > 0) {
            T t = get(0);
            if (t instanceof Byte) {
                byte[] bytes = new byte[count];
                for (int i = 0; i < count; i++) {
                    t = ts.get(i);
                    if (t == null) {
                        bytes[i] = 0;
                    } else {
                        bytes[i] = (byte) t;
                    }
                }
                return bytes;
            }
        }
        return null;
    }

    public char[] toChars() {
        int count = count();
        if (count > 0) {
            T t = get(0);
            if (t instanceof Character) {
                char[] chars = new char[count];
                for (int i = 0; i < count; i++) {
                    t = ts.get(i);
                    if (t == null) {
                        chars[i] = 0;
                    } else {
                        chars[i] = (char) t;
                    }
                }
                return chars;
            }
        }
        return null;
    }

    public short[] toShorts() {
        int count = count();
        if (count > 0) {
            T t = get(0);
            if (t instanceof Short) {
                short[] shorts = new short[count];
                for (int i = 0; i < count; i++) {
                    t = ts.get(i);
                    if (t == null) {
                        shorts[i] = 0;
                    } else {
                        shorts[i] = (short) t;
                    }
                }
                return shorts;
            }
        }
        return null;
    }


    public double[] toDoubles() {
        int count = count();
        if (count > 0) {
            T t = get(0);
            if (t instanceof Double) {
                double[] doubles = new double[count];
                for (int i = 0; i < count; i++) {
                    t = ts.get(i);
                    if (t == null) {
                        doubles[i] = 0d;
                    } else {
                        doubles[i] = (double) t;
                    }
                }
                return doubles;
            }
        }
        return null;
    }

    public long[] toLongs() {
        int count = count();
        if (count > 0) {
            T t = get(0);
            if (t instanceof Long) {
                long[] longs = new long[count];
                for (int i = 0; i < count; i++) {
                    t = ts.get(i);
                    if (t == null) {
                        longs[i] = 0l;
                    } else {
                        longs[i] = (long) t;
                    }
                }
                return longs;
            }
        }
        return null;
    }

    public float[] toFloats() {
        int count = count();
        if (count > 0) {
            T t = get(0);
            if (t instanceof Float) {
                float[] floats = new float[count];
                for (int i = 0; i < count; i++) {
                    t = ts.get(i);
                    if (t == null) {
                        floats[i] = 0f;
                    } else {
                        floats[i] = (float) t;
                    }
                }
                return floats;
            }
        }
        return null;
    }

    public int[] toInts() {
        int count = count();
        if (count > 0) {
            T t = get(0);
            if (t instanceof Integer) {
                int[] ints = new int[count];
                for (int i = 0; i < count; i++) {
                    t = ts.get(i);
                    if (t == null) {
                        ints[i] = 0;
                    } else {
                        ints[i] = (int) t;
                    }
                }
                return ints;
            }
        }
        return null;
    }

    public Set<T> toSet() {
        Set<T> set = new HashSet<>();
        if (count() > 0) {
            set.addAll(ts);
        }
        return set;
    }


    public THIS clear() {
        this.ts.clear();
        return (THIS) this;
    }

    public int count(Counter<T> counter) {
        if (counter == null)
            return 0;

        int total = 0;
        int count = count();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                T t = ts.get(i);
                total = counter.counter(total, i, t);
            }
        }
        return total;
    }

    public THIS removeSameItem() {
        ArrayList<T> ts1 = new ArrayList<>();
        int count = count();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                T t = ts.get(i);
                if (!ts1.contains(t)) {
                    ts1.add(t);
                }
            }
        }
        count = CountTool.count(ts1);
        this.ts.clear();
        for (int i = 0; i < count; i++) {
            this.ts.add(ts1.get(i));
        }
        return (THIS) this;
    }

    public static interface Counter<T> {
        public int counter(int lastCount, int index, T t);
    }

    public SymbolTs toSymbol() {
        SymbolTs symbols = Ts.symbols();
        symbols.add(this.ts);
        return symbols;
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public interface GroupSortGetter<T> {
        String getGroup(int level, T t);

        int getLevels();

        int compare(T o1, T o2);

    }

    public THIS groupSort(GroupSortGetter<T> getter) {
        ListValueMap<String, String> totalMap = new ListValueMap<>();
        Map<String, T> tMap = new HashMap<>();

        Collections.sort(ts, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return getter.compare(o1, o2);
            }
        });

        ls(new Ts.EachTs<T>() {
            @Override
            public boolean each(int i, T t) {
                tMap.put(getter.getGroup(getter.getLevels() - 1, t), t);

                String[] gs = new String[getter.getLevels()];
                for (int j = 0; j < gs.length; j++) {
                    gs[j] = getter.getGroup(j, t);
                }

                List<String> list = totalMap.get(getRootGroupKey());

                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < gs.length; j++) {
                    if (j < gs.length - 1) {
                        sb.append(gs[j]);
                        List<String> subList = totalMap.get(sb.toString());
                        if (CountTool.isNull(subList)) {
                            list.add(gs[j]);
                        }
                        list = subList;
                    } else {
                        list.add(gs[j]);
                    }

                }
                return false;
            }
        });

        List<T> as = new ArrayList<>();
        groupSort(as, getter.getLevels(), 0, totalMap, tMap, getRootGroupKey());
        ts = as;
        return (THIS) this;
    }

    private void groupSort(List<T> container, int levels, int level, ListValueMap<String, String> categorgMap, Map<String, T> tMap, String key) {
        Ts.ts(categorgMap.get(key)).ls(new Ts.EachTs<String>() {
            @Override
            public boolean each(int i, String s) {
                if (level < levels - 1) {
                    groupSort(container, levels, level + 1, categorgMap, tMap, (getRootGroupKey().equals(key) ? "" : key) + s);
                } else {
                    container.add(tMap.get(s));
                }
                return false;
            }
        });
    }

    private String getRootGroupKey() {
        return "root";
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    public static interface ToMap<K, V, T> {

        void deal(Map<K, V> map, int i, T t);
    }

    public <K, V> Map<K, V> toMap(ToMap<K, V, T> toMap) {
        Map<K, V> map = new HashMap<>();
        int count = CountTool.count(ts);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                T t = ts.get(i);
                toMap.deal(map, i, t);
            }
        }
        return map;
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    //两个基础方法
    private Ts.NearByIndex obtainNearByIndex(int index, boolean isNext) {
        int count = count();
        if (count == 1) {
            return null;
        }
        if (index < 0) {
            return null;
        }

        int step = isNext ? 1 : -1;

        Ts.NearByIndex nearByIndex = new Ts.NearByIndex();
        nearByIndex.currentIndex = index;
        if (nearByIndex.currentIndex == (isNext ? (count - 1) : 0)) {
            nearByIndex.nearByIndex = nearByIndex.currentIndex - step;
        } else {
            nearByIndex.nearByIndex = nearByIndex.currentIndex + step;
        }
        return nearByIndex;
    }

    private Ts.NearByIndex obtainNearByIndex(Ts.IsThisOne<T> isThisOne, boolean isNext) {
        return obtainNearByIndex(index(isThisOne), isNext);
    }

    //下一个优先
    public Ts.NearByIndex obtainNearByIndexWhenNextPriority(Ts.IsThisOne<T> isThisOne) {
        return obtainNearByIndex(isThisOne, true);
    }

    public T obtainNearByDataWhenNextPriority(Ts.IsThisOne<T> isThisOne) {
        Ts.NearByIndex nearByIndex = obtainNearByIndexWhenNextPriority(isThisOne);
        if (nearByIndex == null)
            return null;
        return get(nearByIndex.nearByIndex);
    }

    //上一个优先
    public Ts.NearByIndex obtainNearByIndexWhenPrePriority(Ts.IsThisOne<T> isThisOne) {
        return obtainNearByIndex(isThisOne, false);
    }

    public T obtainNearByDataWhenPrePriority(Ts.IsThisOne<T> isThisOne) {
        Ts.NearByIndex nearByIndex = obtainNearByIndexWhenPrePriority(isThisOne);
        if (nearByIndex == null)
            return null;
        return get(nearByIndex.nearByIndex);
    }

}
