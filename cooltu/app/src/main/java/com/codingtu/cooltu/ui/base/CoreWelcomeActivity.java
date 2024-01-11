package com.codingtu.cooltu.ui.base;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;

import core.actbase.base.CoreWelcomeActivityBase;
import core.actres.base.CoreWelcomeActivityRes;

@To(CoreWelcomeActivityRes.class)
@ActBase
public class CoreWelcomeActivity extends CoreWelcomeActivityBase {

    @ClickView(R.id.tv6)
    public void tv6Click() {
        
    }

}
