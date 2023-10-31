package com.codingtu.cooltu.ui;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actbase.StepOneActivityBase;
import core.actres.StepOneActivityRes;

@To(StepOneActivityRes.class)
@ActBase(layout = R.layout.activity_step_one)
public class StepOneActivity extends StepOneActivityBase {
}

