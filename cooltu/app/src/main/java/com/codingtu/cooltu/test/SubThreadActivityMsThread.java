package com.codingtu.cooltu.test;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4a.msthread.CoreMultiMsThread;

public class SubThreadActivityMsThread extends CoreMultiMsThread {

    private Handler mainHandler;
    private Handler subHandler0;
    private Handler subHandler1;

    public void start() {
        createMainHandler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                createSubHandler0();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                createSubHandler1();
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

    private void createSubHandler0() {
        Looper.prepare();
        subHandler0 = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                handleMessageInThread0(msg);
            }
        };
        sendMessage(subHandler0, subThread0StartType());
        Looper.loop();
    }

    private void createSubHandler1() {
        Looper.prepare();
        subHandler1 = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                handleMessageInThread1(msg);
            }
        };
        sendMessage(subHandler1, subThread1StartType());
        Looper.loop();
    }

    ///////////////////////////////////////////////////////
    //
    //
    //
    ///////////////////////////////////////////////////////
    private SubThreadActivityMsThreadInterface dealer;

    public static SubThreadActivityMsThread obtain() {
        return new SubThreadActivityMsThread();
    }

    public SubThreadActivityMsThread dealer(SubThreadActivityMsThreadInterface dealer) {
        this.dealer = dealer;
        return this;
    }

    private int type(SubThreadActivityMsThreadType type) {
        return type.ordinal();
    }

    protected boolean isSubThread0() {
        return Thread.currentThread() == subHandler0.getLooper().getThread();
    }


    /**************************************************
     *
     **************************************************/
    private void handleMessageInMain(Message msg) {
        if (msg.what == type(SubThreadActivityMsThreadType.DEAL_TOAST)) {
            dealer.dealToast((java.lang.String) msg.obj);
            return;
        }
        if (msg.what == type(SubThreadActivityMsThreadType.DEAL_TOAST1)) {
            Object[] objects = (Object[]) msg.obj;
            dealer.dealToast1((java.lang.String) objects[0], (int) objects[1]);
            return;
        }
    }

    public boolean sendMessageForDealToast(java.lang.String str) {
        if (isMainThread()) {
            sendMessage(mainHandler, type(SubThreadActivityMsThreadType.DEAL_TOAST), str);
            return true;
        }
        return false;
    }

    /**************************************************
     *
     **************************************************/


    private int subThread0StartType() {
        return type(SubThreadActivityMsThreadType.DEAL_DATA_START);
    }

    private void handleMessageInThread0(Message msg) {
        if (msg.what == type(SubThreadActivityMsThreadType.DEAL_DATA_START1)) {
            dealer.dealDataStart1((int) msg.obj);
            return;
        }
    }

    /**************************************************
     *
     **************************************************/
    protected boolean isSubThread1() {
        return Thread.currentThread() == subHandler1.getLooper().getThread();
    }

    private int subThread1StartType() {
        return 0;
    }

    private void handleMessageInThread1(Message msg) {

    }


}

