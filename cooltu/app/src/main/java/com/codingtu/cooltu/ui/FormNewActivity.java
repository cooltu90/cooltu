package com.codingtu.cooltu.ui;

import android.os.Bundle;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.FormConfigs;
import com.codingtu.cooltu.processor.annotation.form1.Form;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actbase.FormNewActivityBase;
import core.actres.FormNewActivityRes;

@To(FormNewActivityRes.class)
@Form(FormConfigs.class)
@ActBase(layout = R.layout.activity_form_new)
public class FormNewActivity extends FormNewActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

