package com.codingtu.cooltu.ui;

import android.os.Bundle;
import android.view.KeyEvent;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.form.TestCallBack;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.uicore.WhenBackKeyDown;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.StringTs;
import com.codingtu.cooltu.lib4j.ts.SymbolTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.pack.BoolValue;
import com.codingtu.cooltu.lib4j.ts.pack.IntValue;
import com.codingtu.cooltu.lib4j.ts.pack.TValue;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.tools.ToRes;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.ui.base.BaseWelcomeActivity;

import java.util.ArrayList;
import java.util.List;

import core.actbase.WelcomeActivityBase;
import core.actres.WelcomeActivityRes;
import core.tools.ActStart;

@To(WelcomeActivityRes.class)
@ToRes(R.layout.activity_welcome)
@ActBase(base = BaseWelcomeActivity.class)
public class WelcomeActivity extends WelcomeActivityBase {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBase().addWhenKeyDown(new WhenBackKeyDown() {
            @Override
            public boolean onBack(KeyEvent event) {
                return false;
            }
        });

        SymbolTs<User> ts = Ts.symbols();
        ts.add(new User("lisi1", 12));
        ts.add(new User("lisi2", 23));
        ts.add(new User("lisi3", 22));
        ts.add(new User("lisi4", 45));
        ts.add(new User("lisi1", 12));
        ts.add(new User("lisi5", 56));

        List<User> users = new ArrayList<>();
        users.add(new User("lisi1", 33));
        users.add(new User("lisi3", 452));
        users.add(new User("lisi6", 452));

        ts.replaceAll(new User("lisi1", 222), new User("lisi6", 2323));

        ts.log();

        ActStart.stepOneActivity(getAct());


    }


    @ClickView(R.id.showBt)
    public void showBtClick() {
        showMenuDialog();
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
}
