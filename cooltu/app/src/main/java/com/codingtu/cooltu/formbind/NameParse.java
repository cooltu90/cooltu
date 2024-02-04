package com.codingtu.cooltu.formbind;

import com.codingtu.cooltu.lib4a.form.FormParse;

public class NameParse implements FormParse<String, String> {
    @Override
    public String toView(String s) {
        return s;
    }

    @Override
    public String toBean(Object obj) {
        return (String) obj;
    }
}
