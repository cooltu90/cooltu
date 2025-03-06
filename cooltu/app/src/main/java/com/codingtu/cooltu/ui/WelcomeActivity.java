package com.codingtu.cooltu.ui;

import android.os.Bundle;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.form.TestCallBack;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4j.function.ToDouble;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.vs.IntegerVs;
import com.codingtu.cooltu.lib4j.vs.StringVs;
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
        MaxMin<TestValue1> maxMin = vs.maxMin(new ToDouble<TestValue1>() {
            @Override
            public double toDouble(TestValue1 testValue1) {
                return Double.parseDouble(testValue1.id);
            }
        });

        Logs.i("tolong============================");
        Logs.i(maxMin.min.id);
        Logs.i(maxMin.max.id);

        //
        IntegerVs integerVs = new IntegerVs();
        integerVs.add(10).add(11).add(13).add(13).add(13).add(15).add(13);
        integerVs.deleteFirst(13);
        MaxMin<Integer> maxMin1 = integerVs.maxMin();
        Logs.i("==================");
        Logs.i(maxMin1.min);
        Logs.i(maxMin1.max);


        //
        StringVs stringVs = new StringVs();
        stringVs.add("L-0001").add("zhangsan").add("L-0001")
                .add("zhangsan").add("llll").add("zhangsan").add("L-0001").add("L-0001");

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
