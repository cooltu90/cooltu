package com.codingtu.cooltu.formbind.binder;

import android.view.View;
import android.widget.EditText;

import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public interface Binder<THIS extends Binder> extends OnDestroy {

    THIS addViews(View... views);

    Object value();
}
