package com.codingtu.cooltu.lib4a.form;

import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public abstract class CoreFormLink<FORM_BEAN> implements FormLink<FORM_BEAN>, OnDestroy {

    public CoreFormLink(Destroys destroys) {
        destroys.add(this);
    }
}
