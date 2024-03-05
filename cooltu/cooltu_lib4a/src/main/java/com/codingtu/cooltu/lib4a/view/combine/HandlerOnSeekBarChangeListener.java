package com.codingtu.cooltu.lib4a.view.combine;

import android.os.Handler;
import android.os.Message;
import android.widget.SeekBar;

import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public class HandlerOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener, OnDestroy {
    private Handler handler;
    private final int viewId;

    public HandlerOnSeekBarChangeListener(Destroys destroys, Handler handler, int viewId) {
        destroys.add(this);
        this.handler = handler;
        this.viewId = viewId;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Message message = Message.obtain();
        message.what = viewId;
        message.obj = progress;
        handler.sendMessage(message);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void destroy() {
        handler = null;
    }
}
