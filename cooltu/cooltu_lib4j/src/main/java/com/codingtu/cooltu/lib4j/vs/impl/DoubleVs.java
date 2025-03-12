package com.codingtu.cooltu.lib4j.vs.impl;

import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.function.ToDouble;
import com.codingtu.cooltu.lib4j.function.ToFloat;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.vs.NumVs;

import java.util.List;

public class DoubleVs extends NumVs<Double, DoubleVs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public DoubleVs() {
    }

    public DoubleVs(List<Double> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取valueSymbol
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

    public DoubleVs add_double(double... doubles) {
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
    public DoubleVs createThis_double(double... doubles) {
        DoubleVs doubleVs = new DoubleVs();
        doubleVs.add_double(doubles);
        return doubleVs;
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
