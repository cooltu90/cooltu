package com.codingtu.cooltu.lib4a.view.combine;

import android.os.Handler;
import android.os.Message;

import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public class HandlerOnSelectChange implements RadioGroup.OnSelectChange, OnDestroy {

    private Handler handler;
    private int viewId;

    public HandlerOnSelectChange(Destroys destroys,Handler handler, int viewId) {
        destroys.add(this);
        this.handler = handler;
        this.viewId = viewId;
    }

    @Override
    public void onChange(int selected) {
        Message message = Message.obtain();
        message.what = viewId;
        message.obj = selected;
        handler.sendMessage(message);
    }

    @Override
    public void destroy() {
        handler = null;
    }
}
