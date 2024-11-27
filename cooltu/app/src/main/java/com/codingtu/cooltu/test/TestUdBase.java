package com.codingtu.cooltu.test;

import core.msthread.TesUdMsThread;
import core.msthread.TesUdMsThreadInterface;

public class TestUdBase implements TesUdMsThreadInterface {

    protected core.msthread.TesUdMsThread tesUdMsThread;

    public TestUdBase() {
        tesUdMsThread = TesUdMsThread.obtain().dealer(this);
        tesUdMsThread.start();
    }

    @Override
    public void subStart() {

    }

    protected boolean sendMessageForSubStart() {
        return tesUdMsThread.sendMessageForSubStart();
    }

    @Override
    public void toast(String msg) {

    }

    protected boolean sendMessageForToast(String msg) {
        return tesUdMsThread.sendMessageForToast(msg);
    }
}
