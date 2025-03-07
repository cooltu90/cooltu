package com.codingtu.cooltu.lib4j.vs.test;

import com.codingtu.cooltu.lib4j.data.bean.CoreBean;
import com.codingtu.cooltu.lib4j.data.symbol.ValueSymbol;

public class TestValue extends CoreBean implements ValueSymbol {
    public String id;
    public String name;

    public TestValue() {
    }

    public TestValue(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String valueSymbol() {
        return id;
    }
}
