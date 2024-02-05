package com.codingtu.cooltu.formbind.binder;

import android.view.View;
import android.widget.EditText;

public class EditTextBinder extends CoreBinder<EditTextBinder> {

    private EditText et;

    @Override
    protected void destoryOthers() {
        et = null;
    }

    @Override
    public EditTextBinder addViews(View... views) {
        this.et = (EditText) views[0];
        return this;
    }

    @Override
    public Object value() {
        return this.et.getText().toString();
    }
}
