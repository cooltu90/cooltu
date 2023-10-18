package com.codingtu.cooltu.ui.base;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.tools.To;

import core.actbase.base.BaseWelcomeActivityBase;
import core.actres.base.BaseWelcomeActivityRes;

@To(BaseWelcomeActivityRes.class)
@ActBase(layout = R.layout.activity_welcome,base = CoreWelcomeActivity.class)
public class BaseWelcomeActivity extends BaseWelcomeActivityBase {

    @ClickView({R.id.tv1, R.id.tv3, R.id.tv})
    public void tv3Click() {
        Logs.i("ss");
    }

}
