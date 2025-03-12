package com.codingtu.cooltu.lib4j.ts1.impl;

import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.function.ToFloat;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts1.NumTs;

import java.util.List;

public class FloatTs extends NumTs<Float, FloatTs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public FloatTs() {
    }

    public FloatTs(List<Float> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取symbol
    //
    ///////////////////////////////////////////////////////
    @Override
    protected String obtainSymbol(Float aFloat) {
        return aFloat + "";
    }

    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////

    public FloatTs add_float(float... floats) {
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
    public FloatTs createThis_float(float... floats) {
        FloatTs floatTs = new FloatTs();
        floatTs.add_float(floats);
        return floatTs;
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

    ///////////////////////////////////////////////////////
    //
    // toArray
    //
    ///////////////////////////////////////////////////////
    public float[] to_floats() {
        int count = count();
        float[] arrs = new float[count];
        for (int i = 0; i < count; i++) {
            arrs[i] = this.ts.get(i);
        }
        return arrs;
    }
}
