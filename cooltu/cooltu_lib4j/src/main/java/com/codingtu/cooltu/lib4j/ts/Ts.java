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
    public static ObjTs<Boolean> bools(boolean... booleans) {
        ObjTs<Boolean> ts = new ObjTs<>();
        for (int i = 0; i < booleans.length; i++) {
            ts.ts.add(booleans[i]);
        }
        return ts;
    }

    public static ObjTs<Byte> bytes(byte... bytes) {
        ObjTs<Byte> ts = new ObjTs<>();
        for (int i = 0; i < bytes.length; i++) {
            ts.ts.add(bytes[i]);
        }
        return ts;
    }

    public static ObjTs<Character> chars(char... bytes) {
        ObjTs<Character> ts = new ObjTs<>();
        for (int i = 0; i < bytes.length; i++) {
            ts.ts.add(bytes[i]);
        }
        return ts;
    }

    public static ObjTs<Double> doubles(double... doubles) {
        ObjTs<Double> ts = new ObjTs<>();
        for (int i = 0; i < doubles.length; i++) {
            ts.ts.add(doubles[i]);
        }
        return ts;
    }

    public static ObjTs<Float> floats(float... floats) {
        ObjTs<Float> ts = new ObjTs<>();
        for (int i = 0; i < floats.length; i++) {
            ts.ts.add(floats[i]);
        }
        return ts;
    }

    public static ObjTs<Integer> ints(int... ints) {
        ObjTs<Integer> ts = new ObjTs<>();
        for (int i = 0; i < ints.length; i++) {
            ts.ts.add(ints[i]);
        }
        return ts;
    }

    public static ObjTs<Long> longs(long... longs) {
        ObjTs<Long> ts = new ObjTs<>();
        for (int i = 0; i < longs.length; i++) {
            ts.ts.add(longs[i]);
        }
        return ts;
    }

    public static ObjTs<Short> shorts(short... shorts) {
        ObjTs<Short> ts = new ObjTs<>();
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

    public static <T> ObjTs<T> ts(T... srcTs) {
        ObjTs<T> ts = new ObjTs<>();
        ts.add(srcTs);
        return ts;
    }

    public static <T> ObjTs<T> ts(List<T> list) {
        return new ObjTs<>(list);
    }

    public static <T> ObjTs<T> ts(Class<T> clazz) {
        return new ObjTs<>(new ArrayList<>());
    }

    public static <T> ObjTs<T> ts(Set<T> set) {
        ObjTs<T> ts = new ObjTs<>();
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
    public static <T> ObjTs<T> ls(List<T> list, EachTs<T> eachTs) {
        return ts(list).ls(eachTs);
    }

    public static <T> ObjTs<T> ls(T[] ts, EachTs<T> eachTs) {
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


}
