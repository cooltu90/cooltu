package com.codingtu.cooltu.lib4a.function.zip;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4j.zip.Zip;

import java.io.File;

public abstract class OnZipFinishInUiThread implements Zip.OnFinish {

    private final Handler handler;

    public OnZipFinishInUiThread() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                File file = (File) msg.obj;
                finish(file);
            }
        };
    }

    public abstract void finish(File file);

    @Override
    public final void onFinish(File file) {
        Message msg = Message.obtain();
        msg.obj = file;
        handler.sendMessage(msg);
    }

}
