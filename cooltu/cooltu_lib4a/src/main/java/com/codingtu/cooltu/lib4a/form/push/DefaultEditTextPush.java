package com.codingtu.cooltu.lib4a.form.push;

import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class DefaultEditTextPush extends CorePush<DefaultEditTextPush> implements TextWatcher {
    EditText et;

    @Override
    public DefaultEditTextPush addView(View view) {
        et = (EditText) view;
        et.addTextChangedListener(this);
        return this;
    }

    @Override
    protected void destroyOthers() {
        et = null;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Message msg = Message.obtain();
        msg.what = et.getId();
        msg.obj = s.toString();
        handler.sendMessage(msg);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
