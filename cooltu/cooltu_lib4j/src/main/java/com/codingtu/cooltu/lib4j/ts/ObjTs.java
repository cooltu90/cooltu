package com.codingtu.cooltu.lib4j.ts;

import java.util.List;

public class ObjTs<T> extends BaseTs<T, ObjTs> {

    public ObjTs() {
    }

    public ObjTs(List<T> list) {
        super(list);
    }
}
