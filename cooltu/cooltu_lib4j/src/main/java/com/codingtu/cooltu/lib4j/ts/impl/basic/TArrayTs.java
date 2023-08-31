package com.codingtu.cooltu.lib4j.ts.impl.basic;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

import java.util.ArrayList;

public class TArrayTs<T> extends BaseTs<T> {

    private T[] ts;

    public TArrayTs(T... ts) {
        this.ts = ts;
    }

    @Override
    public T get(int position) {
        return ts[position];
    }

    @Override
    public int count() {
        return CountTool.count(ts);
    }

    @Override
    public void set(int index, T target) {
        ts[index] = target;
    }

    public T[] get() {
        return ts;
    }

    public TListTs<T> toList() {
        ArrayList<T> list = new ArrayList<>();
        int count = count();
        for (int i = 0; i < count; i++) {
            list.add(get(i));
        }
        return Ts.ts(list);
    }

    public void clear() {
        int count = count();
        for (int i = 0; i < count; i++) {
            ts[i] = null;
        }
    }


}
