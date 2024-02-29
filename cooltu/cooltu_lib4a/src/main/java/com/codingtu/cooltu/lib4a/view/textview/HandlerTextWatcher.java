package com.codingtu.cooltu.lib4a.view.textview;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;

import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public class HandlerTextWatcher implements TextWatcher, OnDestroy {

    private Handler handler;
    private int id;

    public HandlerTextWatcher(Destroys destroys, Handler handler, int id) {
        destroys.add(this);
        this.handler = handler;
        this.id = id;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Message msg = Message.obtain();
        msg.what = id;
        msg.obj = s.toString();
        handler.sendMessage(msg);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void destroy() {
        handler = null;
    }
}
