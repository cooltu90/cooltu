package com.codingtu.cooltu.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.form.TestCallBack;
import com.codingtu.cooltu.lib4a.function.OnFinishInUiThread;
import com.codingtu.cooltu.lib4a.function.OnProgressInUiThread;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.thread.OnceThread;
import com.codingtu.cooltu.lib4a.tools.SDCardTool;
import com.codingtu.cooltu.lib4a.view.dialogview.Dialog;
import com.codingtu.cooltu.lib4j.data.progress.Progress;
import com.codingtu.cooltu.lib4j.file.copy.FileCopy;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.Maps;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.tools.ToRes;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.ui.base.BaseWelcomeActivity;

import java.io.File;
import java.util.HashMap;

import core.actbase.WelcomeActivityBase;
import core.actres.WelcomeActivityRes;
import core.tools.ActStart;
import core.tools.Code4Request;

@To(WelcomeActivityRes.class)
@ToRes(R.layout.activity_welcome)
@ActBase(base = BaseWelcomeActivity.class)
public class WelcomeActivity extends WelcomeActivityBase {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, 1);
    }


    @ClickView(R.id.showBt)
    public void showBtClick() {
        //ActStart.stepOneActivity(getAct());
        OnceThread.sub(new Runnable() {
            @Override
            public void run() {
                String path0 = SDCardTool.getSDCard() + "/LdarData/建档/默认空间/csqy_测试企业/7/新乡化纤8.14下_pack00.zip";
                String path1 = SDCardTool.getSDCard() + "/LdarData/建档/默认空间/csqy_测试企业/7/新乡化纤8.14下_pack01.zip";

                File file = new File(path0);
                Logs.i(file.exists());

                FileCopy.src(path0).progress(new OnProgressInUiThread() {
                    @Override
                    public void progress(long totalLen, long currentLen) {
                        Logs.i("totalLen:" + totalLen + " currentLen:" + currentLen);
                    }
                }).finish(new OnFinishInUiThread() {
                    @Override
                    public void finish(Object o) {

                    }
                }).force().to(path1);
            }
        }).main(new OnceThread.MainRunnable() {
            @Override
            public void run(Throwable throwable) {

            }
        }).start();
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

}
