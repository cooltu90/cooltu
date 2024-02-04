package com.codingtu.cooltu.formbind.binder;

import android.os.Handler;

import com.codingtu.cooltu.formbind.binder.Binder;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public abstract class CoreBinder<THIS extends Binder> implements Binder<THIS> {
    @Override
    public void destroy() {
        destoryOthers();
    }

    protected abstract void destoryOthers();

}
