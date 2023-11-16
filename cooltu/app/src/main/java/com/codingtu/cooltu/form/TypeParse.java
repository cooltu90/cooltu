package com.codingtu.cooltu.form;

import com.codingtu.cooltu.lib4a.form.FormParse;

public class TypeParse implements FormParse<String, Integer> {
    @Override
    public Integer toView(String s) {
        return 0;
    }

    @Override
    public String toBean(Object obj) {
        return null;
    }
}
