package com.codingtu.cooltu.lib4a.form;

import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public abstract class CoreFormLink implements FormLink, OnDestroy {

    public CoreFormLink(Destroys destroys) {
        destroys.add(this);
    }
}
