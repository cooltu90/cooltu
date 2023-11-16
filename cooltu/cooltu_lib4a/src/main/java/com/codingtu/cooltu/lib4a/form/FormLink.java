package com.codingtu.cooltu.lib4a.form;

import android.view.View;

public interface FormLink {

    FormLink setViews(View... views);

    FormLink setBean(Object bean);

    void link();

    void echo();
}
