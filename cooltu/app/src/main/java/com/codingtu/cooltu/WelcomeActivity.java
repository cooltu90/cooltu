package com.codingtu.cooltu;

import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.tools.ToRes;

import core.actbase.WelcomeActivityBase;
import core.actres.WelcomeActivityRes;

@To(WelcomeActivityRes.class)
@ToRes(R.layout.activity_main)
public class WelcomeActivity extends WelcomeActivityBase {
}
