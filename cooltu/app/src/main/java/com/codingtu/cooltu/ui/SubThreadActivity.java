package com.codingtu.cooltu.ui;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.processor.annotation.msthread.MainThread;
import com.codingtu.cooltu.processor.annotation.msthread.MsThread;
import com.codingtu.cooltu.processor.annotation.msthread.SubThread;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actbase.SubThreadActivityBase;
import core.actres.SubThreadActivityRes;

@MsThread
@To(SubThreadActivityRes.class)
@ActBase(layout = R.layout.activity_sub_thread)
public class SubThreadActivity extends SubThreadActivityBase {

    @SubThread(isStart = true, value = 0)
    public void dealDataStart() {

    }

    @SubThread(isStart = true, value = 1)
    public void dealDataStart0() {

    }

    @SubThread(0)
    public void method0() {

    }

    @SubThread(1)
    public void method1() {

    }

    @MainThread
    public void dealToast(String str) {
        toast("xxx");
    }

    @MainThread
    public void dealToast(String str, int age) {

    }

}

