package com.codingtu.cooltu.ui;

import android.os.Bundle;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.net.GetObjMock;
import com.codingtu.cooltu.processor.annotation.net.NetBack;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.tools.ToRes;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.ui.base.BaseWelcomeActivity;

import java.util.List;

import core.actbase.WelcomeActivityBase;
import core.actres.WelcomeActivityRes;
import core.net.back.GetObjBack;
import core.net.params.GetObjParams;

@To(WelcomeActivityRes.class)
@ToRes(R.layout.activity_welcome)
@ActBase(base = BaseWelcomeActivity.class)
public class WelcomeActivity extends WelcomeActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @NetBack
    public void getObjBack(GetObjBack back, GetObjParams params, User user, List objs) {

    }

    @ActBack(WelcomeActivity.class)
    public void welcomeActivityBack(User user,List<User> users,String name) {

    }

}
