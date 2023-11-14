package com.codingtu.cooltu.form;

import com.codingtu.cooltu.lib4a.form.FormParse;

public class Name1Parse implements FormParse<String, String> {
    @Override
    public String toView(String s) {
        return s.toLowerCase();
    }

    @Override
    public String toBean(Object obj) {
        String str = (String) obj;
        return str.toUpperCase();
    }
}
