package com.codingtu.cooltu.formbind;

import com.codingtu.cooltu.lib4a.form.FormParse;

public class CountParse implements FormParse<Integer, String> {
    @Override
    public String toView(Integer integer) {
        return integer + "";
    }

    @Override
    public Integer toBean(Object obj) {
        return Integer.parseInt((String) obj);
    }
}
