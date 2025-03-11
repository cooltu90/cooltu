package com.codingtu.cooltu.lib4j.vs.impl;

import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.function.ToDouble;
import com.codingtu.cooltu.lib4j.function.ToFloat;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.vs.NumVs;

import java.util.List;

public class FloatVs extends NumVs<Float, FloatVs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public FloatVs() {
    }

    public FloatVs(List<Float> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取valueSymbol
    //
    ///////////////////////////////////////////////////////
    @Override
    protected String valueSymbol(Float aFloat) {
        return aFloat + "";
    }

    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////

    public FloatVs add_float(float... floats) {
        int count = CountTool.count(floats);
        for (int i = 0; i < count; i++) {
            this.ts.add(floats[i]);
        }
        return this;
    }

    ///////////////////////////////////////////////////////
    //
    // createThis
    //
    ///////////////////////////////////////////////////////
    public FloatVs createThis_float(float... floats) {
        FloatVs floatVs = new FloatVs();
        floatVs.add_float(floats);
        return floatVs;
    }

    ///////////////////////////////////////////////////////
    //
    // maxMin
    //
    ///////////////////////////////////////////////////////
    public MaxMin<Float> maxMin() {
        return super.maxMin(new ToFloat<Float>() {
            @Override
            public double toFloat(Float aFloat) {
                return aFloat;
            }
        });
    }
}
