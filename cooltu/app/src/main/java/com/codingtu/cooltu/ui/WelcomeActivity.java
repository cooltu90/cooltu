package com.codingtu.cooltu.ui;

import android.os.Bundle;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.form.TestCallBack;
import com.codingtu.cooltu.lib4j.vs.ValueSymbolVs;
import com.codingtu.cooltu.lib4j.vs.impl.DoubleVs;
import com.codingtu.cooltu.lib4j.vs.impl.IntegerVs;
import com.codingtu.cooltu.lib4j.vs.impl.StringVs;
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
        vs.add(new TestValue1("7791", "lisi1"));
        vs.add(new TestValue1("7793", "lisi1"));
        vs.add(new TestValue1("7792", "lisi2"));
        vs.add(new TestValue1("7790", "lisi1"));
        vs.add(new TestValue1("7794", "lisi1"));

        ValueSymbolVs<TestValue> baseVs = Vs.vs();
        baseVs.add(new TestValue("0", "lisi1"));
        baseVs.add(new TestValue("1", "lisi2"));
        baseVs.add(new TestValue("2", "lisi3"));
        baseVs.add(new TestValue("1", "lisi2"));
        baseVs.add(new TestValue("3", "lisi4"));
        baseVs.add(new TestValue("2", "lisi3"));

        //
        IntegerVs integerVs = new IntegerVs();
        integerVs.add(10).add(13).add(13).add(15).add(13).add(22).add(25);

        //
        StringVs stringVs = new StringVs();
        stringVs.add("L-0001").add("zhangsan").add("L-0001").add("zhangsan").add("llll").add("zhangsan").add("L-0001").add("L-0001");

        DoubleVs doubleVs = new DoubleVs();
        doubleVs.add(1.2d).add(1.5d).add(2.3d);
        doubleVs.replaceFirst(1.2d, 5.6d);

        //test
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
