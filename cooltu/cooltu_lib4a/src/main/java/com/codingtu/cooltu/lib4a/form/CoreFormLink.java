package com.codingtu.cooltu.lib4a.form;

import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public abstract class CoreFormLink<FORM_BEAN> implements FormLink<FORM_BEAN>, OnDestroy {

    protected FORM_BEAN bean;

    public CoreFormLink(Destroys destroys) {
        destroys.add(this);
    }


    @Override
    public FormLink setBean(FORM_BEAN bean) {
        this.bean = bean;
        return this;
    }

    @Override
    public void destroy() {
        this.bean = null;
    }
}
