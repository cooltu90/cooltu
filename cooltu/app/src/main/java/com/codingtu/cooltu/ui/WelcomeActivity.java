package com.codingtu.cooltu.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.form.TestCallBack;
import com.codingtu.cooltu.lib4a.function.OnErrorInUiThread;
import com.codingtu.cooltu.lib4a.function.OnFinishInUiThread;
import com.codingtu.cooltu.lib4a.function.OnProgressInUiThread;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.thread.OnceThread;
import com.codingtu.cooltu.lib4a.tools.SDCardTool;
import com.codingtu.cooltu.lib4a.tools.ToastTool;
import com.codingtu.cooltu.lib4a.tools.Upload;
import com.codingtu.cooltu.lib4a.view.dialogview.Dialog;
import com.codingtu.cooltu.lib4a.view.layer.event.OnHiddenFinishedCallBack;
import com.codingtu.cooltu.lib4a.view.layer.event.OnShowFinishedCallBack;
import com.codingtu.cooltu.lib4j.data.progress.Progress;
import com.codingtu.cooltu.lib4j.file.copy.FileCopy;
import com.codingtu.cooltu.lib4j.file.delete.FileDeleter;
import com.codingtu.cooltu.lib4j.function.OnError;
import com.codingtu.cooltu.lib4j.function.OnFinish;
import com.codingtu.cooltu.lib4j.function.OnProgress;
import com.codingtu.cooltu.lib4j.path.BasePath;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.Maps;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.tools.ToRes;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.tools.Me;
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
    }


    @ClickView(R.id.showBt)
    public void showBtClick() {
        getToastDialog()
                .setContent("正在处理数据")
                .show()
                .whenShowFinishedStartThread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Handler subHandler = new Handler(Looper.myLooper()) {
                            @Override
                            public void handleMessage(@NonNull Message msg) {
                                super.handleMessage(msg);
                                if (msg.what == 0) {
                                    Looper.myLooper().quit();
                                }
                            }
                        };
                        Message msg = Message.obtain(subHandler);
                        msg.what = 0;
                        subHandler.sendMessage(msg);
                        Looper.loop();

                    }
                })
                .onMainThread(new OnceThread.MainRunnable() {
                    @Override
                    public void run(Throwable throwable) {
                        getToastDialog().hidden()
                                .hiddenTime(1000)
                                .start();
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
