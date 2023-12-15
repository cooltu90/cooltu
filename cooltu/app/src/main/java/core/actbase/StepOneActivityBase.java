package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class StepOneActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
    protected androidx.recyclerview.widget.RecyclerView rv;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_step_one);

        rv = findViewById(com.codingtu.cooltu.R.id.rv);











        onCreateComplete();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        }
    }


    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == android.app.Activity.RESULT_OK) {

        }
    }

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {
        super.back(requestCode, permissions, grantResults);

    }




    private com.codingtu.cooltu.lib4a.view.dialogview.ToastDialog toastDialog;

    protected com.codingtu.cooltu.lib4a.view.dialogview.ToastDialog getToastDialog() {
        if (toastDialog == null)
            toastDialog = new com.codingtu.cooltu.lib4a.view.dialogview.ToastDialog(getAct())
                    .setLayout(com.codingtu.cooltu.R.layout.dialog_toast)
                    .build();
        return toastDialog;
    }
    protected void toastShow(String msg) {
        com.codingtu.cooltu.lib4a.view.dialogview.ToastDialog td = getToastDialog();
        td.setContent(msg);
        if (!td.isShow()) {
            td.show();
        }
    }
    protected void toastShow(long time, String msg, com.codingtu.cooltu.lib4a.view.layerview.listener.OnHiddenFinished onHiddenFinished) {
        toastShow(msg);
        com.codingtu.cooltu.lib4a.tools.HandlerTool.getMainHandler().postDelayed(new java.lang.Runnable() {
            @Override
            public void run() {
                getToastDialog().hidden(onHiddenFinished);
            }
        }, time);
    }

    protected void toastShow(long time, String msg) {
        toastShow(time, msg, null);
    }

    protected void toastHidden(long time, String msg, com.codingtu.cooltu.lib4a.view.layerview.listener.OnHiddenFinished onHiddenFinished) {
        getToastDialog().setContent(msg);
        com.codingtu.cooltu.lib4a.tools.HandlerTool.getMainHandler().postDelayed(new java.lang.Runnable() {
            @Override
            public void run() {
                getToastDialog().hidden(onHiddenFinished);
            }
        }, time);
    }

    protected void toastHidden(long time, String msg) {
        toastHidden(time, msg, null);
    }

    private com.codingtu.cooltu.lib4a.view.dialogview.NoticeDialog noticeDialog;

    protected void noticeShow(String msg) {
        if (noticeDialog == null)
            noticeDialog = new com.codingtu.cooltu.lib4a.view.dialogview.NoticeDialog(getAct())
                    .setLayout(com.codingtu.cooltu.R.layout.dialog_notice)
                    .build();
        noticeDialog.setContent(msg);
        noticeDialog.show();
    }




}

