package com.codingtu.cooltu.lib4a.form.advice;

import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class DefaultEditTextAdvice extends CoreAdvice<DefaultEditTextAdvice> implements TextWatcher {

    private EditText et;

    @Override
    protected void destoryOthers() {
        et = null;
    }

    @Override
    public DefaultEditTextAdvice addViews(View... views) {
        et = (EditText) views[0];
        return this;
    }

    @Override
    public void start() {
        et.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Message msg = Message.obtain();
        msg.what = et.getId();
        msg.obj = s.toString();
        this.handler.sendMessage(msg);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
