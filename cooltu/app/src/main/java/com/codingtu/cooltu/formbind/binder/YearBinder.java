package com.codingtu.cooltu.formbind.binder;

import android.view.View;
import android.widget.EditText;

import com.codingtu.cooltu.lib4a.formbind.binder.Binder;

public class YearBinder implements Binder<YearBinder> {
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
    public void destroy() {
        et = null;
    }
}
