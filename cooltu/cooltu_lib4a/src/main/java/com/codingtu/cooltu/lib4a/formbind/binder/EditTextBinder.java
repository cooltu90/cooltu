package com.codingtu.cooltu.lib4a.formbind.binder;

import android.view.View;
import android.widget.EditText;

public class EditTextBinder implements Binder<EditTextBinder> {

    private EditText et;


    @Override
    public EditTextBinder addViews(View... views) {
        this.et = (EditText) views[0];
        return this;
    }

    @Override
    public Object value() {
        return this.et.getText().toString();
    }

    @Override
    public void destroy() {
        et = null;
    }
}
