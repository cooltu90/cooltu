package com.codingtu.cooltu.lib4j.ts1;

import com.codingtu.cooltu.lib4j.data.bean.CoreBean;
import com.codingtu.cooltu.lib4j.data.symbol1.Symbol;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts1.impl.BooleanTs;
import com.codingtu.cooltu.lib4j.ts1.impl.DoubleTs;
import com.codingtu.cooltu.lib4j.ts1.impl.FloatTs;
import com.codingtu.cooltu.lib4j.ts1.impl.IntegerTs;
import com.codingtu.cooltu.lib4j.ts1.impl.LongTs;
import com.codingtu.cooltu.lib4j.ts1.impl.StringTs;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ts {

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
    public static class NearIndex extends CoreBean {
        public int currentIndex;
        public int neighborIndex;

        public boolean isNextOne() {
            return neighborIndex > currentIndex;
        }
    }

    /**************************************************
     * BaseTs
     **************************************************/
    public static <T> BaseTs<T> baseTs(T... srcTs) {
        BaseTs<T> ts = new BaseTs<>();
        ts.add(srcTs);
        return ts;
    }

    public static <T> BaseTs<T> baseTs(List<T> srcTs) {
        return new BaseTs(srcTs);
    }

    public static <T> BaseTs<T> baseTs(Set<T> srcTs) {
        BaseTs<T> ts = new BaseTs<>();
        if (!CountTool.isNull(srcTs)) {
            ts.ts.addAll(srcTs);
        }
        return ts;
    }

    public static <T> BaseTs<T> baseTs(BaseTs<T> srcTs) {
        BaseTs<T> ts = new BaseTs<>();
        ts.add(srcTs);
        return ts;
    }


    /**************************************************
     * SymbolTs
     **************************************************/
    public static <T extends Symbol> SymbolTs<T> ts(T... srcTs) {
        SymbolTs<T> ts = new SymbolTs<>();
        ts.add(srcTs);
        return ts;
    }

    public static <T extends Symbol> SymbolTs<T> ts(List<T> srcTs) {
        return new SymbolTs<>(srcTs);
    }

    public static <T extends Symbol> SymbolTs<T> ts(Set<T> srcTs) {
        SymbolTs<T> ts = new SymbolTs<>();
        if (!CountTool.isNull(srcTs)) {
            ts.ts.addAll(srcTs);
        }
        return ts;
    }

    public static <T extends Symbol> SymbolTs<T> ts(SymbolTs<T> srcTs) {
        SymbolTs<T> ts = new SymbolTs<>();
        ts.add(srcTs);
        return ts;
    }

    /**************************************************
     * StringTs
     **************************************************/
    public static StringTs strs(String... srcTs) {
        StringTs ts = new StringTs();
        ts.add(srcTs);
        return ts;
    }

    public static StringTs strs(List<String> srcTs) {
        return new StringTs(srcTs);
    }


    public static StringTs strs(Set<String> srcTs) {
        StringTs ts = new StringTs();
        if (!CountTool.isNull(srcTs)) {
            ts.ts.addAll(srcTs);
        }
        return ts;
    }

    public static StringTs strs(StringTs srcTs) {
        StringTs ts = new StringTs();
        ts.add(srcTs);
        return ts;
    }

    /**************************************************
     * BooleanTs
     **************************************************/
    public static BooleanTs booleans(Boolean... srcTs) {
        BooleanTs ts = new BooleanTs();
        ts.add(srcTs);
        return ts;
    }

    public static BooleanTs booleans(boolean... srcTs) {
        BooleanTs ts = new BooleanTs();
        ts.add_boolean(srcTs);
        return ts;
    }

    public static BooleanTs booleans(List<Boolean> srcTs) {
        return new BooleanTs(srcTs);
    }

    public static BooleanTs booleans(Set<Boolean> srcTs) {
        BooleanTs ts = new BooleanTs();
        if (!CountTool.isNull(srcTs)) {
            ts.ts.addAll(srcTs);
        }
        return ts;
    }

    public static BooleanTs booleans(BooleanTs srcTs) {
        BooleanTs ts = new BooleanTs();
        ts.add(srcTs);
        return ts;
    }

    /**************************************************
     * DoubleTs
     **************************************************/
    public static DoubleTs doubles(Double... srcTs) {
        DoubleTs ts = new DoubleTs();
        ts.add(srcTs);
        return ts;
    }

    public static DoubleTs doubles(double... srcTs) {
        DoubleTs ts = new DoubleTs();
        ts.add_double(srcTs);
        return ts;
    }

    public static DoubleTs doubles(List<Double> srcTs) {
        return new DoubleTs(srcTs);
    }

    public static DoubleTs doubles(Set<Double> srcTs) {
        DoubleTs ts = new DoubleTs();
        if (!CountTool.isNull(srcTs)) {
            ts.ts.addAll(srcTs);
        }
        return ts;
    }

    public static DoubleTs doubles(DoubleTs srcTs) {
        DoubleTs ts = new DoubleTs();
        ts.add(srcTs);
        return ts;
    }

    /**************************************************
     * FloatTs
     **************************************************/
    public static FloatTs floats(Float... srcTs) {
        FloatTs ts = new FloatTs();
        ts.add(srcTs);
        return ts;
    }

    public static FloatTs floats(float... srcTs) {
        FloatTs ts = new FloatTs();
        ts.add_float(srcTs);
        return ts;
    }

    public static FloatTs floats(List<Float> srcTs) {
        return new FloatTs(srcTs);
    }

    public static FloatTs floats(Set<Float> srcTs) {
        FloatTs ts = new FloatTs();
        if (!CountTool.isNull(srcTs)) {
            ts.ts.addAll(srcTs);
        }
        return ts;
    }

    public static FloatTs floats(FloatTs srcTs) {
        FloatTs ts = new FloatTs();
        ts.add(srcTs);
        return ts;
    }

    /**************************************************
     * IntegerTs
     **************************************************/
    public static IntegerTs ints(Integer... srcTs) {
        IntegerTs ts = new IntegerTs();
        ts.add(srcTs);
        return ts;
    }

    public static IntegerTs ints(int... srcTs) {
        IntegerTs ts = new IntegerTs();
        ts.add_int(srcTs);
        return ts;
    }

    public static IntegerTs ints(List<Integer> srcTs) {
        return new IntegerTs(srcTs);
    }

    public static IntegerTs ints(Set<Integer> srcTs) {
        IntegerTs ts = new IntegerTs();
        if (!CountTool.isNull(srcTs)) {
            ts.ts.addAll(srcTs);
        }
        return ts;
    }

    public static IntegerTs ints(IntegerTs srcTs) {
        IntegerTs ts = new IntegerTs();
        ts.add(srcTs);
        return ts;
    }

    /**************************************************
     * LongTs
     **************************************************/
    public static LongTs longs(Long... srcTs) {
        LongTs ts = new LongTs();
        ts.add(srcTs);
        return ts;
    }

    public static LongTs longs(long... srcTs) {
        LongTs ts = new LongTs();
        ts.add_long(srcTs);
        return ts;
    }

    public static LongTs longs(List<Long> srcTs) {
        return new LongTs(srcTs);
    }

    public static LongTs longs(Set<Long> srcTs) {
        LongTs ts = new LongTs();
        if (!CountTool.isNull(srcTs)) {
            ts.ts.addAll(srcTs);
        }
        return ts;
    }

    public static LongTs longs(LongTs srcTs) {
        LongTs ts = new LongTs();
        ts.add(srcTs);
        return ts;
    }

}
