package com.codingtu.cooltu.lib4a.form.push;

import android.os.Handler;

import com.codingtu.cooltu.lib4j.destory.Destroys;

public abstract class CorePush<THIS extends Push> implements Push<THIS> {
    protected Handler handler;
    protected Destroys destroys;

    @Override
    public THIS bindHandler(Handler handler) {
        this.handler = handler;
        return (THIS) this;
    }

    @Override
    public THIS destory(Destroys destroys) {
        this.destroys = destroys;
        destroys.add(this);
        return (THIS) this;
    }

    @Override
    public void destroy() {
        this.handler = null;
        this.destroys = null;
        destroyOthers();
    }

    protected abstract void destroyOthers();
}
