package com.codingtu.cooltu.lib4j.ts1.impl;

import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.function.ToDouble;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts1.NumTs;

import java.util.List;

public class DoubleTs extends NumTs<Double, DoubleTs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public DoubleTs() {
    }

    public DoubleTs(List<Double> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取symbol
    //
    ///////////////////////////////////////////////////////
    @Override
    protected String obtainSymbol(Double aDouble) {
        return aDouble + "";
    }

    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////

    public DoubleTs add_double(double... doubles) {
        int count = CountTool.count(doubles);
        for (int i = 0; i < count; i++) {
            this.ts.add(doubles[i]);
        }
        return this;
    }


    ///////////////////////////////////////////////////////
    //
    // createThis
    //
    ///////////////////////////////////////////////////////
    public DoubleTs createThis_double(double... doubles) {
        DoubleTs doubleTs = new DoubleTs();
        doubleTs.add_double(doubles);
        return doubleTs;
    }

    ///////////////////////////////////////////////////////
    //
    // maxMin
    //
    ///////////////////////////////////////////////////////
    public MaxMin<Double> maxMin() {
        return super.maxMin(new ToDouble<Double>() {
            @Override
            public double toDouble(Double aDouble) {
                return aDouble;
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    // toArray
    //
    ///////////////////////////////////////////////////////
    public double[] to_doubles() {
        int count = count();
        double[] arrs = new double[count];
        for (int i = 0; i < count; i++) {
            arrs[i] = this.ts.get(i);
        }
        return arrs;
    }
}
