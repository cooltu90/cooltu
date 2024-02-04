package com.codingtu.cooltu.lib4a.form.advice;

import android.os.Handler;

public abstract class CoreAdvice<THIS extends Advice> implements Advice<THIS> {

    protected Handler handler;


    @Override
    public THIS addHandler(Handler handler) {
        this.handler = handler;
        return (THIS) this;
    }

    @Override
    public void destroy() {
        this.handler = null;
        destoryOthers();
    }

    protected abstract void destoryOthers();
}
