package com.codingtu.cooltu.lib4a.formbind.binder;

import android.view.View;

import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public interface Binder<THIS extends Binder> extends OnDestroy {

    THIS addViews(View... views);

    Object value();
}
