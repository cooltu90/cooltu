package com.codingtu.cooltu.lib4j.ts;

import com.codingtu.cooltu.lib4j.data.symbol.Symbol;
import com.codingtu.cooltu.lib4j.tools.CountTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Ts {


    /**************************************************
     *
     * 创建方法
     *
     **************************************************/
    public static BaseTs<Boolean> bools(boolean... booleans) {
        BaseTs<Boolean> ts = new BaseTs<>();
        for (int i = 0; i < booleans.length; i++) {
            ts.ts.add(booleans[i]);
        }
        return ts;
    }

    public static BaseTs<Byte> bytes(byte... bytes) {
        BaseTs<Byte> ts = new BaseTs<>();
        for (int i = 0; i < bytes.length; i++) {
            ts.ts.add(bytes[i]);
        }
        return ts;
    }

    public static BaseTs<Character> chars(char... bytes) {
        BaseTs<Character> ts = new BaseTs<>();
        for (int i = 0; i < bytes.length; i++) {
            ts.ts.add(bytes[i]);
        }
        return ts;
    }

    public static BaseTs<Double> doubles(double... doubles) {
        BaseTs<Double> ts = new BaseTs<>();
        for (int i = 0; i < doubles.length; i++) {
            ts.ts.add(doubles[i]);
        }
        return ts;
    }

    public static BaseTs<Float> floats(float... floats) {
        BaseTs<Float> ts = new BaseTs<>();
        for (int i = 0; i < floats.length; i++) {
            ts.ts.add(floats[i]);
        }
        return ts;
    }

    public static BaseTs<Integer> ints(int... ints) {
        BaseTs<Integer> ts = new BaseTs<>();
        for (int i = 0; i < ints.length; i++) {
            ts.ts.add(ints[i]);
        }
        return ts;
    }

    public static BaseTs<Long> longs(long... longs) {
        BaseTs<Long> ts = new BaseTs<>();
        for (int i = 0; i < longs.length; i++) {
            ts.ts.add(longs[i]);
        }
        return ts;
    }

    public static BaseTs<Short> shorts(short... shorts) {
        BaseTs<Short> ts = new BaseTs<>();
        for (int i = 0; i < shorts.length; i++) {
            ts.ts.add(shorts[i]);
        }
        return ts;
    }

    public static StringTs strs(String... strs) {
        StringTs ts = new StringTs();
        ts.add(strs);
        return ts;
    }

    public static StringTs strs(List<String> strs) {
        StringTs ts = new StringTs();
        ts.add(strs);
        return ts;
    }

    public static <SYMBOL extends Symbol> SymbolTs<SYMBOL> symbols(SYMBOL... symbols) {
        SymbolTs<SYMBOL> ts = new SymbolTs<>();
        ts.add(symbols);
        return ts;
    }

    public static <SYMBOL extends Symbol> SymbolTs<SYMBOL> symbols(List<SYMBOL> symbols) {
        SymbolTs<SYMBOL> ts = new SymbolTs<>();
        ts.add(symbols);
        return ts;
    }

    public static <T> BaseTs<T> ts(T... srcTs) {
        BaseTs<T> ts = new BaseTs<>();
        ts.add(srcTs);
        return ts;
    }

    public static <T> BaseTs<T> ts(List<T> list) {
        return new BaseTs<>(list);
    }

    public static <T> BaseTs<T> ts(Class<T> clazz) {
        return new BaseTs<>(new ArrayList<>());
    }

    public static <T> BaseTs<T> ts(Set<T> set) {
        BaseTs<T> ts = new BaseTs<>();
        int count = CountTool.count(set);
        if (count >= 0) {
            ts.ts.addAll(set);
        }
        return ts;
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    public static <T> BaseTs<T> ls(List<T> list, EachTs<T> eachTs) {
        return ts(list).ls(eachTs);
    }

    public static <T> BaseTs<T> ls(T[] ts, EachTs<T> eachTs) {
        return ts(ts).ls(eachTs);
    }


    /**************************************************
     *
     *
     *
     **************************************************/
    public interface EachTs<T> {
        boolean each(int position, T t);
    }

    public interface Convert<S, T> {
        T convert(int index, S s);
    }

    public interface IsThisOne<T> {
        boolean isThisOne(int position, T t);
    }

    public interface NowMax<T> {
        boolean isNowMax(T last, T now);
    }

    public interface IsNow<T> {
        boolean isNow(T last, T now);
    }

    public interface MapEach<K, V> {
        public boolean each(K k, V v);
    }


}
