package com.codingtu.cooltu.ui;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.FormObj;
import com.codingtu.cooltu.processor.annotation.formbind.FormBind;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actbase.FormTestActivityBase;
import core.actres.FormTestActivityRes;

@To(FormTestActivityRes.class)
@FormBind(FormObj.class)
@ActBase(layout = R.layout.activity_form_test, base = FormTestBaseActivity.class)
public class FormTestActivity extends FormTestActivityBase {
}

