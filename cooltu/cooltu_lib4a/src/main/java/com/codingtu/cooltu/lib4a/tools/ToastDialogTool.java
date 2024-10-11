package com.codingtu.cooltu.lib4a.tools;

import com.codingtu.cooltu.lib4a.view.dialogview.ToastDialog;
import com.codingtu.cooltu.lib4a.view.layer.event.OnHiddenFinishedCallBack;

public class ToastDialogTool {

    public static void toastHidden(ToastDialog toastDialog, long time, String content, OnHiddenFinishedCallBack onHiddenFinishedCallBack) {
        toastDialog.setContent(content);
        com.codingtu.cooltu.lib4a.tools.HandlerTool.getMainHandler().postDelayed(new java.lang.Runnable() {
            @Override
            public void run() {
                toastDialog.hidden(onHiddenFinishedCallBack);
            }
        }, time);
    }

}
