package com.codingtu.cooltu.ui;

import android.os.Bundle;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.form.TestCallBack;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
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

import java.util.List;
import java.util.Map;
import java.util.Set;

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

        TestValue1Vs vs1 = new TestValue1Vs();
        vs1.add(new TestValue1("7791", "mei1"));
        vs1.add(new TestValue1("7793", "mei1"));

        vs.replaceFirst(vs1);
        vs.ls(new Vs.EachTs<TestValue1>() {
            @Override
            public boolean each(int position, TestValue1 testValue1) {
                Logs.i(testValue1.toJson());
                return false;
            }
        });

        BaseVs<TestValue> baseVs = Vs.vs();
        baseVs.add(new TestValue("0", "lisi1"));
        baseVs.add(new TestValue("1", "lisi2"));
        baseVs.add(new TestValue("2", "lisi3"));
        baseVs.add(new TestValue("3", "lisi4"));
        baseVs.ls(new Vs.EachTs<TestValue>() {
            @Override
            public boolean each(int position, TestValue testValue) {
                Logs.i(testValue.toJson());
                return false;
            }
        });


        //
        IntegerVs integerVs = new IntegerVs();
        integerVs.add(10).add(13).add(13).add(15).add(13).add(22).add(25);

        IntegerVs ints = Vs.ints(0, 1, 2, 3, 4);
        ints.log();

        //
        StringVs stringVs = new StringVs();
        stringVs.add("L-0001").add("zhangsan").add("L-0001").add("zhangsan").add("llll").add("zhangsan").add("L-0001").add("L-0001");
        stringVs.replaceAllOrAdd("L-0001", "L-0005");
        stringVs.ls(new Vs.EachTs<String>() {
            @Override
            public boolean each(int position, String s) {
                Logs.i("s:" + s);
                return false;
            }
        });

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
