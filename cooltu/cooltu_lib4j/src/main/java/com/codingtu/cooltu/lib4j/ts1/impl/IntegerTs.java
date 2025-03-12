package com.codingtu.cooltu.lib4j.ts1.impl;

import com.codingtu.cooltu.lib4j.function.ToInt;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts1.NumTs;

import java.util.List;

public class IntegerTs extends NumTs<Integer, IntegerTs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public IntegerTs() {
    }

    public IntegerTs(List<Integer> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取symbol
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

    public IntegerTs add_int(int... ints) {
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
    public IntegerTs createThis_int(int... ints) {
        IntegerTs integerTs = new IntegerTs();
        integerTs.add_int(ints);
        return integerTs;
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
