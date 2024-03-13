package com.codingtu.cooltu.ui;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bind.DataBindConfig;
import com.codingtu.cooltu.processor.annotation.bind.Bind;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actbase.BindActivityBase;
import core.actres.BindActivityRes;

@To(BindActivityRes.class)
@Bind(DataBindConfig.class)
@ActBase(layout = R.layout.activity_bind)
public class BindActivity extends BindActivityBase {

}

