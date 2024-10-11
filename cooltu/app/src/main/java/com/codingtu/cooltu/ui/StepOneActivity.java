package com.codingtu.cooltu.ui;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.view.layer.event.OnHiddenFinishedCallBack;
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

        getToastDialog()
                .setContent("hi")
                .hiddenTime(1000l)
                .onHiddenFinished(new OnHiddenFinishedCallBack() {
                    @Override
                    public void onHiddenFinished() {
                        toast("hiddenFinish");
                    }
                })
                .hidden();
    }

    @Override
    protected void dogAdapterLoadMore(int page) {
    }

}
