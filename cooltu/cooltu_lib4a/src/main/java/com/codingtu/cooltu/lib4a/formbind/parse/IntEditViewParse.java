package com.codingtu.cooltu.lib4a.formbind.parse;

public class IntEditViewParse implements Parse<Integer, String> {
    @Override
    public String toView(Integer integer) {
        return integer + "";
    }

    @Override
    public Integer toBean(Object obj) {
        return Integer.parseInt((String) obj);
    }
}
