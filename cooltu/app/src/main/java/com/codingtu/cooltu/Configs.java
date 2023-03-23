package com.codingtu.cooltu;

import cooltu.lib4a.CoreConfigs;

public class Configs extends CoreConfigs {
    @Override
    public boolean isLog() {
        return true;
    }

    @Override
    public String getDefaultLogTag() {
        return "cooltu_test";
    }
}
