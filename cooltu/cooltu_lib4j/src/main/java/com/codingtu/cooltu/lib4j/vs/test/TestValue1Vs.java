package com.codingtu.cooltu.lib4j.vs.test;

import com.codingtu.cooltu.lib4j.vs.CoreVs;

public class TestValue1Vs extends CoreVs<TestValue1, TestValue1Vs> {
    @Override
    protected String valueSymbol(TestValue1 testValue1) {
        return testValue1.id;
    }
}
