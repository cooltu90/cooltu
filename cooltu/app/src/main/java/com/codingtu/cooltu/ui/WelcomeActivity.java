package com.codingtu.cooltu.ui;

import android.os.Bundle;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.form.TestCallBack;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4j.vs.BaseVs;
import com.codingtu.cooltu.lib4j.vs.IntegerVs;
import com.codingtu.cooltu.lib4j.vs.StringVs;
import com.codingtu.cooltu.lib4j.vs.Vs;
import com.codingtu.cooltu.lib4j.vs.test.TestValue;
import com.codingtu.cooltu.lib4j.vs.test.TestValue1;
import com.codingtu.cooltu.lib4j.vs.test.TestValue1Vs;
import com.codingtu.cooltu.lib4j.vs.value.ValueSymbol;
import com.codingtu.cooltu.processor.annotation.net.NetBack;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.tools.ToRes;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.ui.base.BaseWelcomeActivity;
import com.codingtu.cooltu.ui.view.TestView;

import java.util.ArrayList;
import java.util.List;

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
        BaseVs<TestValue> testValueBaseVs = vs.convertList(new Vs.Convert<TestValue1, List<TestValue>>() {
            @Override
            public List<TestValue> convert(int index, TestValue1 testValue1) {
                List<TestValue> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    TestValue testValue = new TestValue();
                    testValue.id = testValue1.id + "lll" + i;
                    list.add(testValue);
                }
                return list;
            }
        }).ls(new Vs.EachTs<TestValue>() {
            @Override
            public boolean each(int position, TestValue testValue) {
                //Logs.i("testValue:" + testValue.id);
                return false;
            }
        });

        TestValue1Vs testValue1Vs = vs.convertList(TestValue1Vs.class, new Vs.Convert<TestValue1, List<TestValue1>>() {
            @Override
            public List<TestValue1> convert(int index, TestValue1 testValue1) {
                ArrayList<TestValue1> testValue1s = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    TestValue1 testValue11 = new TestValue1(testValue1.id + "id" + i, testValue1.name + "name" + i);
                    testValue1s.add(testValue11);
                }
                return testValue1s;
            }
        }).ls(new Vs.EachTs<TestValue1>() {
            @Override
            public boolean each(int position, TestValue1 testValue1) {
                Logs.i("testValue1 id" + testValue1.id + " name:" + testValue1.name);
                return false;
            }
        });


        //
        IntegerVs integerVs = new IntegerVs();
        integerVs.add(10).add(11).add(13).add(13).add(13).add(15).add(13);
        integerVs.deleteFirst(13);
//        BaseVs<TestValue> convert1 = integerVs.convert(new Vs.Convert<Integer, TestValue>() {
//            @Override
//            public TestValue convert(int index, Integer integer) {
//                return null;
//            }
//        });

//        IntegerVs convert2 = integerVs.convert(IntegerVs.class, new Vs.Convert<Integer, Integer>() {
//            @Override
//            public Integer convert(int index, Integer integer) {
//                return null;
//            }
//        });

        //integerVs.log();


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
