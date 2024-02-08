package com.codingtu.cooltu.lib4a.form.push;

import android.os.Message;
import android.view.View;
import android.widget.SeekBar;

public class DefaultSeekBarPush extends CorePush<DefaultSeekBarPush> implements SeekBar.OnSeekBarChangeListener {

    @Override
    protected void destroyOthers() {

    }

    @Override
    public DefaultSeekBarPush addView(View view) {
        SeekBar seekBar = (SeekBar) view;
        seekBar.setOnSeekBarChangeListener(this);
        return this;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Message message = Message.obtain();
        message.what = seekBar.getId();
        message.obj = progress;
        handler.sendMessage(message);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
