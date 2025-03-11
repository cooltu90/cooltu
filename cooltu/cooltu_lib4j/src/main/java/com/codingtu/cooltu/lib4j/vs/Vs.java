package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.data.bean.CoreBean;
import com.codingtu.cooltu.lib4j.data.symbol.ValueSymbol;
import com.codingtu.cooltu.lib4j.vs.impl.BooleanVs;
import com.codingtu.cooltu.lib4j.vs.impl.IntegerVs;
import com.codingtu.cooltu.lib4j.vs.impl.StringVs;

import java.util.List;
import java.util.Map;

public class Vs {

    /**************************************************
     *
     **************************************************/
    public static interface EachTs<T> {
        boolean each(int position, T t);
    }

    public static interface EachGetter<T> {
        int count();

        T get(int position);
    }

    public static interface IsThisOne<T> {
        boolean isThisOne(int position, T t);
    }

    public static interface Convert<S, T> {
        T convert(int index, S s);
    }

    public static interface ConvertList<S, T> {
        void convert(List<T> list, int index, S s);
    }

    public static interface NowMax<T> {
        boolean isNowMax(T last, T now);
    }

    public static interface IsNow<T> {
        boolean isNow(T last, T now);
    }

    public static interface Counter<T> {
        public int counter(int lastCount, int index, T t);
    }

    public static interface ToMap<K, V, T> {
        void deal(Map<K, V> map, int i, T t);
    }

    /**************************************************
     *
     **************************************************/
    public static class NeighborIndex extends CoreBean {
        public int currentIndex;
        public int neighborIndex;

        public boolean isNextOne() {
            return neighborIndex > currentIndex;
        }
    }

    /**************************************************
     * basevs
     **************************************************/
    public static <T extends ValueSymbol> ValueSymbolVs<T> vs(T... srcVs) {
        ValueSymbolVs<T> vs = new ValueSymbolVs<>();
        vs.add(srcVs);
        return vs;
    }

    public static <T extends ValueSymbol> ValueSymbolVs<T> vs(List<T> srcVs) {
        ValueSymbolVs<T> vs = new ValueSymbolVs<>();
        vs.add(srcVs);
        return vs;
    }

    public static <T extends ValueSymbol> ValueSymbolVs<T> vs(ValueSymbolVs<T> srcVs) {
        ValueSymbolVs<T> vs = new ValueSymbolVs<>();
        vs.add(srcVs);
        return vs;
    }

    /**************************************************
     * ints
     **************************************************/
    public static IntegerVs ints(int... srcVs) {
        IntegerVs integerVs = new IntegerVs();
        integerVs.add_int(srcVs);
        return integerVs;
    }

    public static IntegerVs integers(Integer... srcVs) {
        IntegerVs integerVs = new IntegerVs();
        integerVs.add(srcVs);
        return integerVs;
    }

    public static IntegerVs ints(List<Integer> srcVs) {
        IntegerVs integerVs = new IntegerVs();
        integerVs.add(srcVs);
        return integerVs;
    }

    public static IntegerVs ints(IntegerVs srcVs) {
        IntegerVs integerVs = new IntegerVs();
        integerVs.add(srcVs);
        return integerVs;
    }

    /**************************************************
     * Strings
     **************************************************/

    public static StringVs strs(String... srcVs) {
        StringVs stringVs = new StringVs();
        stringVs.add(srcVs);
        return stringVs;
    }

    public static StringVs strs(List<String> srcVs) {
        StringVs stringVs = new StringVs();
        stringVs.add(srcVs);
        return stringVs;
    }

    public static StringVs strs(StringVs srcVs) {
        StringVs stringVs = new StringVs();
        stringVs.add(srcVs);
        return stringVs;
    }

    /**************************************************
     * booleans
     **************************************************/
    public static BooleanVs bools(boolean... srcVs) {
        BooleanVs stringVs = new BooleanVs();
        stringVs.add_boolean(srcVs);
        return stringVs;
    }

}
