package com.codingtu.cooltu.formbind.binder;

import android.view.View;
import android.widget.EditText;

import com.codingtu.cooltu.lib4a.formbind.binder.Binder;

public class AddressBinder implements Binder<AddressBinder> {

    private EditText provinceEt;
    private EditText cityEt;
    private EditText areaEt;

    @Override
    public AddressBinder addViews(View... views) {
        this.provinceEt = (EditText) views[0];
        this.cityEt = (EditText) views[1];
        this.areaEt = (EditText) views[2];
        return this;
    }

    @Override
    public Object value() {
        return getText(this.provinceEt) + "-" + getText(this.cityEt) + "-" + getText(this.areaEt);
    }

    private String getText(EditText et) {
        return et.getText().toString();
    }


    @Override
    public void destroy() {
        provinceEt = null;
        cityEt = null;
        areaEt = null;
    }
}
