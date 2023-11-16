package com.codingtu.cooltu.lib4a.form;

import com.codingtu.cooltu.lib4a.uicore.Destroys;
import com.codingtu.cooltu.lib4a.uicore.OnDestroy;

public abstract class CoreFormLink implements FormLink, OnDestroy {

    public CoreFormLink(Destroys destroys) {
        destroys.add(this);
    }
}
