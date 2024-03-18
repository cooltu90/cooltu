package com.codingtu.cooltu.ui;

import android.os.Bundle;
import android.view.View;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.Score;
import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.form.TestCallBack;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.path.PathBeanFile;
import com.codingtu.cooltu.lib4a.thread.OnceThread;
import com.codingtu.cooltu.lib4a.tools.HandlerTool;
import com.codingtu.cooltu.lib4a.view.dialogview.ToastDialog;
import com.codingtu.cooltu.lib4a.view.layer.event.OnShowFinishedCallBack;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.tools.ToRes;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.Init;
import com.codingtu.cooltu.ui.base.BaseWelcomeActivity;
import com.codingtu.cooltu.ui.view.DownloadDialog;

import core.actbase.WelcomeActivityBase;
import core.actres.WelcomeActivityRes;
import core.path.DocumentPath;
import core.path.DocumentStudentsStudentPath;
import core.tools.ActStart;

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
        showMenuDialog(null);
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
