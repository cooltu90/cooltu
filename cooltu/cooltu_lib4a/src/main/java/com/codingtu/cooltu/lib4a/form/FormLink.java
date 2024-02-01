package com.codingtu.cooltu.lib4a.form;

import android.view.View;

public interface FormLink<FORM_BEAN> {

    FormLink setViews(View... views);

    FormLink setBean(FORM_BEAN bean);

    void link(int id);

    void echo();
}
