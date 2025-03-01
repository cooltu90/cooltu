package com.codingtu.cooltu.ui;

import android.os.Bundle;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.form.TestCallBack;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4j.vs.BaseVs;
import com.codingtu.cooltu.lib4j.vs.IntegerVs;
import com.codingtu.cooltu.lib4j.vs.StringVs;
import com.codingtu.cooltu.lib4j.vs.Vs;
import com.codingtu.cooltu.lib4j.vs.test.TestValue;
import com.codingtu.cooltu.lib4j.vs.test.TestValue1;
import com.codingtu.cooltu.lib4j.vs.test.TestValue1Vs;
import com.codingtu.cooltu.processor.annotation.net.NetBack;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.tools.ToRes;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.ui.base.BaseWelcomeActivity;

import core.actbase.WelcomeActivityBase;
import core.actres.WelcomeActivityRes;

@To(WelcomeActivityRes.class)
@ToRes(R.layout.activity_welcome)
@ActBase(base = BaseWelcomeActivity.class)
public class WelcomeActivity extends WelcomeActivityBase {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @ClickView(R.id.showBt)
    public void showBtClick() {
        //
        TestValue1Vs vs = new TestValue1Vs();
        vs.add(new TestValue1("7790", "lisi1"));
        vs.add(new TestValue1("7791", "lisi1"));
        vs.add(new TestValue1("7792", "lisi2"));
        vs.add(new TestValue1("7793", "lisi1"));
        vs.add(new TestValue1("7794", "lisi1"));

        //
        IntegerVs integerVs = new IntegerVs();
        integerVs.add(10).add(11).add(13);


        //
        StringVs stringVs = new StringVs();
        stringVs.add("L-0001").add("zhangsan").add("llll").add("L-0001");
    }


    @Override
    protected boolean editDialogYes(String text) {
        return true;
    }

    @ClickView(value = R.id.reportTv, inAct = false)
    public void reportTvClick() {
        toast("clickReport");
    }

    @Override
    protected TestCallBack testCallBackInit() {
        return new TestCallBack() {
            @Override
            public void callback() {

            }
        };
    }

    @NetBack
    public void addObj1Back(String json) {

    }

}
