package com.codingtu.cooltu.ui;

import android.os.Bundle;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.LongClickView;

import core.actbase.FormActivityBase;
import core.actres.FormActivityRes;

@To(FormActivityRes.class)
@ActBase(layout = R.layout.activity_form)
public class FormActivity extends FormActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @ClickView(value = R.id.bt, check = true)
    public void btClick() {

    }

    @LongClickView(R.id.bt)
    public boolean btLongClick() {
        Logs.i("bt long click");
        return true;
    }

}

