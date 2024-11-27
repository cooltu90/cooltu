package com.codingtu.cooltu.ud;


import com.codingtu.cooltu.processor.annotation.msthread.MainThread;
import com.codingtu.cooltu.processor.annotation.msthread.MsThread;
import com.codingtu.cooltu.processor.annotation.msthread.SubThread;

import core.msthread.TesUdBaseForMsThread;

@MsThread
public class TesUd extends TesUdBaseForMsThread {

    @SubThread(isStart = true)
    public void subStart() {



    }

    @MainThread
    public void toast(String msg) {

    }

}
