package com.codingtu.cooltu.ui;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.CoreTs;
import com.codingtu.cooltu.lib4j.ts.StringTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import core.actbase.StepOneActivityBase;
import core.actres.StepOneActivityRes;

@To(StepOneActivityRes.class)
@ActBase(layout = R.layout.activity_step_one)
public class StepOneActivity extends StepOneActivityBase {


    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
        catAdapter.updateItems(Ts.ts("1", "2", "3", "sdfsd"));
    }

    @Override
    protected void dogAdapterLoadMore(int page) {


    }
}

