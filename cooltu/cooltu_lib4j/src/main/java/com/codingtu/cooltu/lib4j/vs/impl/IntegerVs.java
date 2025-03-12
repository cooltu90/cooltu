package com.codingtu.cooltu.lib4j.vs.impl;

import com.codingtu.cooltu.lib4j.function.ToDouble;
import com.codingtu.cooltu.lib4j.function.ToInt;
import com.codingtu.cooltu.lib4j.function.ToLong;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.vs.NumVs;
import com.codingtu.cooltu.lib4j.vs.Vs;

import java.util.List;
import java.util.Map;

public class IntegerVs extends NumVs<Integer, IntegerVs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public IntegerVs() {
    }

    public IntegerVs(List<Integer> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取valueSymbol
    //
    ///////////////////////////////////////////////////////
    @Override
    protected String obtainSymbol(Integer integer) {
        return String.valueOf(integer);
    }

    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////

    public IntegerVs add_int(int... ints) {
        int count = CountTool.count(ints);
        for (int i = 0; i < count; i++) {
            this.ts.add(ints[i]);
        }
        return this;
    }

    ///////////////////////////////////////////////////////
    //
    // createThis
    //
    ///////////////////////////////////////////////////////
    public IntegerVs createThis_int(int... ints) {
        IntegerVs integerVs = new IntegerVs();
        integerVs.add_int(ints);
        return integerVs;
    }

    ///////////////////////////////////////////////////////
    //
    // maxMin
    //
    ///////////////////////////////////////////////////////
    public MaxMin<Integer> maxMin() {
        return super.maxMin(new ToInt<Integer>() {
            @Override
            public int toInt(Integer integer) {
                return integer;
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    // toArray
    //
    ///////////////////////////////////////////////////////

    public int[] to_ints() {
        int count = count();
        int[] arrs = new int[count];
        for (int i = 0; i < count; i++) {
            arrs[i] = this.ts.get(i);
        }
        return arrs;
    }
}
