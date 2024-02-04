package com.codingtu.cooltu.formbind;

import com.codingtu.cooltu.bean.FormsObj;
import com.codingtu.cooltu.lib4a.form.FormCheck;
import com.codingtu.cooltu.lib4j.tools.StringTool;

public class NameCheck implements FormCheck<FormsObj, String> {
    @Override
    public boolean check(FormsObj forms, String s) {
        return StringTool.isNotBlank(s);
    }
}
