package com.codingtu.cooltu.lib4j.vs.impl;

import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.function.ToLong;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.vs.NumVs;

import java.util.List;

public class LongVs extends NumVs<Long, LongVs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public LongVs() {
    }

    public LongVs(List<Long> list) {
        super(list);
    }
    ///////////////////////////////////////////////////////
    //
    // 获取valueSymbol
    //
    ///////////////////////////////////////////////////////

    @Override
    protected String valueSymbol(Long aLong) {
        return aLong + "";
    }

    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////

    public LongVs add_long(long... longs) {
        int count = CountTool.count(longs);
        for (int i = 0; i < count; i++) {
            this.ts.add(longs[i]);
        }
        return this;
    }

    ///////////////////////////////////////////////////////
    //
    // createThis
    //
    ///////////////////////////////////////////////////////
    public LongVs createThis_long(long... longs) {
        LongVs longVs = new LongVs();
        longVs.add_long(longs);
        return longVs;
    }

    ///////////////////////////////////////////////////////
    //
    // maxMin
    //
    ///////////////////////////////////////////////////////
    public MaxMin<Long> maxMin() {
        return super.maxMin(new ToLong<Long>() {
            @Override
            public long toLong(Long aLong) {
                return aLong;
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    // toArray
    //
    ///////////////////////////////////////////////////////

    public long[] to_longs() {
        int count = count();
        long[] arrs = new long[count];
        for (int i = 0; i < count; i++) {
            arrs[i] = this.ts.get(i);
        }
        return arrs;
    }
}
