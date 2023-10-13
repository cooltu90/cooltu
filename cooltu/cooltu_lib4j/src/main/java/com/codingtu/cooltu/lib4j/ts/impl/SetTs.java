package com.codingtu.cooltu.lib4j.ts.impl;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.impl.basic.TListTs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class SetTs<T> {

    private Set<T> ts;

    public SetTs(Set<T> ts) {
        this.ts = ts;
    }

    public interface SetEach<T> {
        boolean each(T t);
    }

    public void ls(BaseTs.EachTs<T> eachTs) {
        if (eachTs == null)
            return;
        if (CountTool.count(ts) > 0) {
            int index = 0;
            Iterator<? extends T> iterator = ts.iterator();
            while (iterator.hasNext()) {
                if (eachTs.each(index, iterator.next())) {
                    return;
                }
                index++;
            }
        }
    }

    public void ls(SetEach<T> each) {
        ls(new BaseTs.EachTs<T>() {
            @Override
            public boolean each(int position, T t) {
                return each.each(t);
            }
        });
    }

    public TListTs<T> toList() {
        TListTs<T> ls = new TListTs<T>(new ArrayList<T>());
        ls(new BaseTs.EachTs<T>() {
            @Override
            public boolean each(int position, T t) {
                ls.add(t);
                return false;
            }
        });
        return ls;
    }

}
