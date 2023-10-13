package com.codingtu.cooltu.ui.base;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.processor.annotation.res.ActBase;
import com.codingtu.cooltu.processor.annotation.tools.To;

import core.actbase.base.BaseWelcomeActivityBase;
import core.actres.base.BaseWelcomeActivityRes;

@To(BaseWelcomeActivityRes.class)
@ActBase(layout = R.layout.activity_welcome,base = CoreWelcomeActivity.class)
public class BaseWelcomeActivity extends BaseWelcomeActivityBase {
}
