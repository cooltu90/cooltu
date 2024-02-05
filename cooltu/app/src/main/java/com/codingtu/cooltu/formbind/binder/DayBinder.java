package com.codingtu.cooltu.formbind.binder;

import android.view.View;
import android.widget.EditText;

public class DayBinder extends CoreBinder<DayBinder> {

    private EditText et;

    @Override
    public DayBinder addViews(View... views) {
        et = (EditText) views[0];
        return this;
    }

    @Override
    public Object value() {
        String[] split = et.getText().toString().split("-");
        return Integer.parseInt(split[2]);
    }

    @Override
    protected void destoryOthers() {
        et = null;
    }
}
