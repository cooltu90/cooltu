package com.codingtu.cooltu.lib4a.function.zip;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4j.zip.UnZip;

public abstract class OnUnZipFinishInUiThread implements UnZip.OnFinish {

    private final Handler handler;

    public OnUnZipFinishInUiThread() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                long totalLen = (long) msg.obj;
                finish(totalLen);
            }
        };
    }

    public abstract void finish(long totalLen);

    @Override
    public void onFinish(long totalLen) {
        Message msg = Message.obtain();
        msg.obj = totalLen;
        handler.sendMessage(msg);
    }

}