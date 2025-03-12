package com.codingtu.cooltu.lib4j.ts1;

import java.util.List;

public class BaseTs<T> extends CoreTs<T, BaseTs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public BaseTs() {
    }

    public BaseTs(List<T> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取Symbol
    //
    ///////////////////////////////////////////////////////
    @Override
    protected String obtainSymbol(T t) {
        return t + "";
    }
}
