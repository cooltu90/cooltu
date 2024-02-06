package com.codingtu.cooltu.lib4a.formbind.push;

import android.os.Handler;

public abstract class CorePush<THIS extends Push> implements Push<THIS> {
    protected Handler handler;

    @Override
    public THIS bindHandler(Handler handler) {
        this.handler = handler;
        return (THIS) this;
    }

    @Override
    public void destroy() {
        this.handler = null;
        destroyOthers();
    }

    protected abstract void destroyOthers();
}
