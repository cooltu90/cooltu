package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.data.bean.CoreBean;
import com.codingtu.cooltu.lib4j.data.symbol1.Symbol;
import com.codingtu.cooltu.lib4j.vs.impl.BooleanVs;
import com.codingtu.cooltu.lib4j.vs.impl.DoubleVs;
import com.codingtu.cooltu.lib4j.vs.impl.FloatVs;
import com.codingtu.cooltu.lib4j.vs.impl.IntegerVs;
import com.codingtu.cooltu.lib4j.vs.impl.LongVs;
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

    public interface MapEach<K, V> {
        public boolean each(K k, V v);
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
     * ValueSymbolVs
     **************************************************/
    public static <T extends Symbol> SymbolVs<T> vs(T... srcVs) {
        SymbolVs<T> vs = new SymbolVs<>();
        vs.add(srcVs);
        return vs;
    }

    public static <T extends Symbol> SymbolVs<T> vs(List<T> srcVs) {
        return new SymbolVs<>(srcVs);
    }

    public static <T extends Symbol> SymbolVs<T> vs(SymbolVs<T> srcVs) {
        SymbolVs<T> vs = new SymbolVs<>();
        vs.add(srcVs);
        return vs;
    }

    /**************************************************
     * StringVs
     **************************************************/
    public static StringVs strs(String... srcVs) {
        StringVs vs = new StringVs();
        vs.add(srcVs);
        return vs;
    }

    public static StringVs strs(List<String> srcVs) {
        return new StringVs(srcVs);
    }

    public static StringVs strs(StringVs srcVs) {
        StringVs vs = new StringVs();
        vs.add(srcVs);
        return vs;
    }

    /**************************************************
     * BooleanVs
     **************************************************/
    public static BooleanVs booleans(Boolean... srcVs) {
        BooleanVs vs = new BooleanVs();
        vs.add(srcVs);
        return vs;
    }

    public static BooleanVs booleans(boolean... srcVs) {
        BooleanVs vs = new BooleanVs();
        vs.add_boolean(srcVs);
        return vs;
    }

    public static BooleanVs booleans(List<Boolean> srcVs) {
        return new BooleanVs(srcVs);
    }

    public static BooleanVs booleans(BooleanVs srcVs) {
        BooleanVs vs = new BooleanVs();
        vs.add(srcVs);
        return vs;
    }

    /**************************************************
     * DoubleVs
     **************************************************/
    public static DoubleVs doubles(Double... srcVs) {
        DoubleVs vs = new DoubleVs();
        vs.add(srcVs);
        return vs;
    }

    public static DoubleVs doubles(double... srcVs) {
        DoubleVs vs = new DoubleVs();
        vs.add_double(srcVs);
        return vs;
    }

    public static DoubleVs doubles(List<Double> srcVs) {
        return new DoubleVs(srcVs);
    }

    public static DoubleVs doubles(DoubleVs srcVs) {
        DoubleVs vs = new DoubleVs();
        vs.add(srcVs);
        return vs;
    }

    /**************************************************
     * FloatVs
     **************************************************/
    public static FloatVs floats(Float... srcVs) {
        FloatVs vs = new FloatVs();
        vs.add(srcVs);
        return vs;
    }

    public static FloatVs floats(float... srcVs) {
        FloatVs vs = new FloatVs();
        vs.add_float(srcVs);
        return vs;
    }

    public static FloatVs floats(List<Float> srcVs) {
        return new FloatVs(srcVs);
    }

    public static FloatVs floats(FloatVs srcVs) {
        FloatVs vs = new FloatVs();
        vs.add(srcVs);
        return vs;
    }

    /**************************************************
     * IntegerVs
     **************************************************/
    public static IntegerVs ints(Integer... srcVs) {
        IntegerVs vs = new IntegerVs();
        vs.add(srcVs);
        return vs;
    }

    public static IntegerVs ints(int... srcVs) {
        IntegerVs vs = new IntegerVs();
        vs.add_int(srcVs);
        return vs;
    }

    public static IntegerVs ints(List<Integer> srcVs) {
        return new IntegerVs(srcVs);
    }

    public static IntegerVs ints(IntegerVs srcVs) {
        IntegerVs vs = new IntegerVs();
        vs.add(srcVs);
        return vs;
    }

    /**************************************************
     * LongVs
     **************************************************/
    public static LongVs longs(Long... srcVs) {
        LongVs vs = new LongVs();
        vs.add(srcVs);
        return vs;
    }

    public static LongVs longs(long... srcVs) {
        LongVs vs = new LongVs();
        vs.add_long(srcVs);
        return vs;
    }

    public static LongVs longs(List<Long> srcVs) {
        return new LongVs(srcVs);
    }

    public static LongVs longs(LongVs srcVs) {
        LongVs vs = new LongVs();
        vs.add(srcVs);
        return vs;
    }

}
