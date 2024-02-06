package com.codingtu.cooltu.lib4a.formbind.echo;

import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public interface Echo<THIS extends Echo> extends OnDestroy {

    default THIS destory(Destroys destroys) {
        destroys.add(this);
        return (THIS) this;
    }

}
