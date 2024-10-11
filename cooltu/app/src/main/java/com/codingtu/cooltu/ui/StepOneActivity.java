package com.codingtu.cooltu.ui;

import android.widget.TextView;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.lib4a.view.dialogview.ToastDialog;
import com.codingtu.cooltu.lib4a.view.layer.event.OnHiddenFinishedCallBack;
import com.codingtu.cooltu.lib4a.view.layer.event.OnShowFinishedCallBack;
import com.codingtu.cooltu.lib4j.data.value.ByteArrayValue;
import com.codingtu.cooltu.lib4j.data.value.ByteValue;
import com.codingtu.cooltu.lib4j.data.value.FloatValue;
import com.codingtu.cooltu.lib4j.data.value.IntArrayValue;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.CoreTs;
import com.codingtu.cooltu.lib4j.ts.StringTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import java.util.Comparator;
import java.util.List;

import core.actbase.StepOneActivityBase;
import core.actres.StepOneActivityRes;

@To(StepOneActivityRes.class)
@ActBase(layout = R.layout.activity_step_one)
public class StepOneActivity extends StepOneActivityBase {


    @Override
    public void onCreateComplete() {
        super.onCreateComplete();

        ToastDialog toastDialog = getToastDialog();
        toastDialog.setContent("xxxx")
                .show(new OnShowFinishedCallBack() {
                    @Override
                    public void onShowFinished() {
                        toastDialog.setContent("nihao").hiddenTime(1000l)
                                .onHiddenFinishedCallBack(new OnHiddenFinishedCallBack() {
                                    @Override
                                    public void onHiddenFinished() {
                                        toast("xxx");
                                    }
                                }).hidden();
                    }
                });
    }

    @Override
    protected void dogAdapterLoadMore(int page) {
    }

}
