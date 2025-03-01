package com.codingtu.cooltu.lib4j.vs.test;

import com.codingtu.cooltu.lib4j.vs.value.ValueSymbol;

public class TestValue implements ValueSymbol {
    public String id;
    @Override
    public String valueSymbol() {
        return id;
    }
}
