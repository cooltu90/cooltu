package com.codingtu.cooltu.ui;

import android.widget.EditText;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.Forms;
import com.codingtu.cooltu.processor.annotation.form.Form;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actbase.FormActivityBase;
import core.actres.FormActivityRes;

@To(FormActivityRes.class)
@Form(Forms.class)
@ActBase(layout = R.layout.activity_form)
public class FormActivity extends FormActivityBase {



}

