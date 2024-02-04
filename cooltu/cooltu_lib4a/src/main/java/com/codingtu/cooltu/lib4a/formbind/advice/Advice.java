package com.codingtu.cooltu.lib4a.formbind.advice;

import android.os.Handler;
import android.view.View;

import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public interface Advice<THIS extends Advice> extends OnDestroy {

    THIS addHandler(Handler handler);

    THIS addViews(View... views);

    void start();
}
