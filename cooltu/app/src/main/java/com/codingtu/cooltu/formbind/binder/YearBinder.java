package com.codingtu.cooltu.formbind.binder;

import android.view.View;
import android.widget.EditText;

public class YearBinder extends CoreBinder<YearBinder> {
    private EditText et;


    @Override
    public YearBinder addViews(View... views) {
        et = (EditText) views[0];
        return this;
    }

    @Override
    public Object value() {
        String[] split = et.getText().toString().split("-");
        return Integer.parseInt(split[0]);
    }

    @Override
    protected void destoryOthers() {
        et = null;
    }
}
