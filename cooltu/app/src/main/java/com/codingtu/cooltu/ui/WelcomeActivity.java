package com.codingtu.cooltu.ui;

import android.os.Bundle;
import android.view.View;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.tools.ToRes;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.ui.base.BaseWelcomeActivity;

import core.actbase.WelcomeActivityBase;
import core.actres.WelcomeActivityRes;
import core.path.DocumentPath;
import core.path.DocumentStudentsStudentPath;

@To(WelcomeActivityRes.class)
@ToRes(R.layout.activity_welcome)
@ActBase(base = BaseWelcomeActivity.class)
public class WelcomeActivity extends WelcomeActivityBase {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActStart.addPhotoActivity(getAct());
        DocumentPath docPath = DocumentPath.obtain("一年级", "二班");

        DocumentStudentsStudentPath xiaoming =
                DocumentPath.obtain("一年级", "二班")
                        .students
                        .student("小明");
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
