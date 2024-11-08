package com.codingtu.cooltu.lib4a.msthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

public abstract class CoreMSThread {

    private Handler mainHandler;
    private Handler subHandler;

    public void start() {
        createMainHandler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                createSubHandler();
            }
        }).start();
    }

    private void createMainHandler() {
        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                handleMessageInMain(msg);
            }
        };
    }

    private void createSubHandler() {
        Looper.prepare();
        subHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                handleMessageInThread(msg);
            }
        };
        sendMessage(subHandler, subThreadStartType());
        Looper.loop();
    }

    protected void stop() {
        Looper.myLooper().quitSafely();
    }

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

    protected void sendMainMessage(int what, Object... objects) {
        sendMessage(mainHandler, what, objects);
    }

    protected void sendSubMessage(int what, Object... objects) {
        sendMessage(subHandler, what, objects);
    }

    protected boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    protected boolean isSubThread() {
        return !isMainThread();
    }

    protected abstract int subThreadStartType();


    protected abstract void handleMessageInThread(Message msg);

    protected abstract void handleMessageInMain(Message msg);
}
