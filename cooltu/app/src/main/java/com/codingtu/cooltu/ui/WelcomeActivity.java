package com.codingtu.cooltu.ui;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.Forms;
import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.lib4a.tools.MeTool;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.tools.ToRes;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.Permission;
import com.codingtu.cooltu.tools.Me;
import com.codingtu.cooltu.ui.base.BaseWelcomeActivity;

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
//        ActStart.formTestActivity(getAct());
        ActStart.addPhotoActivity(getAct());
    }

    @ActBack(StepOneActivity.class)
    public void stepOneActivityBack(User user, String xxx) {
        User user1 = new User();
        backBt.setTag(com.codingtu.cooltu.lib4a.R.id.tag_0, user1);
    }

    @ClickView(value = R.id.backBt, checkLogin = true)
    public void backBtClick(View v, User user) {

    }

}
