package com.codingtu.cooltu.formbind.advice;

import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.ui.FormTestBaseActivity;

public interface Advice<THIS extends Advice> extends OnDestroy {

    THIS addHandler(Handler handler);

    THIS addViews(View... views);

    void start();
}
