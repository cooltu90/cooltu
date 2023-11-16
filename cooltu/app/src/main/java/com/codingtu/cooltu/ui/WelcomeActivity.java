package com.codingtu.cooltu.ui;

import android.Manifest;
import android.os.Bundle;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.Forms;
import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.tools.ToRes;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.Permission;
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
        Forms forms = new Forms();
        forms.name1 = "SSSSS";
        forms.address="山东-淄博-周村";
        ActStart.formActivity(getAct(), forms);
    }

    @ActBack(StepOneActivity.class)
    public void stepOneActivityBack(User user, String xxx) {

    }

}
