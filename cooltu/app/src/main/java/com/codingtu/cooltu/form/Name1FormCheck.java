package com.codingtu.cooltu.form;

import com.codingtu.cooltu.bean.Forms;
import com.codingtu.cooltu.lib4a.form.FormCheck;

public class Name1FormCheck implements FormCheck<Forms, String> {
    @Override
    public boolean check(Forms forms, String s) {
        return false;
    }
}
