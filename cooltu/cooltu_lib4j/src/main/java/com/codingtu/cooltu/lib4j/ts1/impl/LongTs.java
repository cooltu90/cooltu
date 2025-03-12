package com.codingtu.cooltu.lib4j.ts1.impl;

import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.function.ToLong;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts1.NumTs;

import java.util.List;

public class LongTs extends NumTs<Long, LongTs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public LongTs() {
    }

    public LongTs(List<Long> list) {
        super(list);
    }
    ///////////////////////////////////////////////////////
    //
    // 获取symbol
    //
    ///////////////////////////////////////////////////////

    @Override
    protected String obtainSymbol(Long aLong) {
        return aLong + "";
    }

    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////

    public LongTs add_long(long... longs) {
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
    public LongTs createThis_long(long... longs) {
        LongTs longTs = new LongTs();
        longTs.add_long(longs);
        return longTs;
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
