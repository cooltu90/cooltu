package com.codingtu.cooltu.lib4a.msthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class CoreMultiMsThread {

    protected void sendMessage(Handler handler, int what, Object... objects) {
        Message msg = Message.obtain(handler);
        msg.what = what;
        if (objects != null) {
            if (objects.length == 1) {
                msg.obj = objects[0];
            } else {
                msg.obj = objects;
            }
        }
        handler.sendMessage(msg);
    }

    protected boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

}
