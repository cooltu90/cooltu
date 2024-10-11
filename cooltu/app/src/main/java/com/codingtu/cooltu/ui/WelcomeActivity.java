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
import com.codingtu.cooltu.lib4a.view.layer.event.OnHiddenFinishedCallBack;
import com.codingtu.cooltu.lib4a.view.layer.event.OnShowFinishedCallBack;
import com.codingtu.cooltu.lib4j.data.progress.Progress;
import com.codingtu.cooltu.lib4j.file.copy.FileCopy;
import com.codingtu.cooltu.lib4j.file.delete.FileDeleter;
import com.codingtu.cooltu.lib4j.function.OnError;
import com.codingtu.cooltu.lib4j.function.OnFinish;
import com.codingtu.cooltu.lib4j.function.OnProgress;
import com.codingtu.cooltu.lib4j.tools.StringTool;
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
import core.net.Net;
import core.tools.ActStart;
import core.tools.Code4Request;

@To(WelcomeActivityRes.class)
@ToRes(R.layout.activity_welcome)
@ActBase(base = BaseWelcomeActivity.class)
public class WelcomeActivity extends WelcomeActivityBase {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
//        intent.setData(Uri.parse("package:" + getPackageName()));
//        startActivityForResult(intent, 1);

        //0.032048253164765
        //0.03533525348935628

        String s = StringTool.formatDouble(0.03533525348935628, 2, false);
        Logs.i(s);
    }


    @ClickView(R.id.showBt)
    public void showBtClick() {
        getToastDialog()
                .setContent("正在处理数据")
                .show()
                .whenShowFinishedStartThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //throw new RuntimeException("我错完了");
                    }
                })
                .hiddenWhenThreadFinished()
                .setContent("处理玩了")
                .hiddenTime(1000)
                .onHiddenFinished(new OnHiddenFinishedCallBack() {
                    @Override
                    public void onHiddenFinished() {
                        toast("c处理完了");
                    }
                })
                .start();

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
