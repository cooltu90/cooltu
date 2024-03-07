package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class StepOneActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected com.codingtu.cooltu.bean.User user;
    protected com.codingtu.cooltu.ui.view.TestView testView;
    protected androidx.recyclerview.widget.RecyclerView rv;
    protected androidx.recyclerview.widget.RecyclerView rv1;
    protected com.codingtu.cooltu.ui.adapter.CatAdapter catAdapter;
    protected com.codingtu.cooltu.ui.adapter.DogAdapter dogAdapter;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_step_one);

        rv = findViewById(com.codingtu.cooltu.R.id.rv);
        rv1 = findViewById(com.codingtu.cooltu.R.id.rv1);






        onCreateComplete();

    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
        // catAdapter
        catAdapter = new com.codingtu.cooltu.ui.adapter.CatAdapter();
        catAdapter.setVH(core.vh.CatVH.class);
        catAdapter.setClick(this);
        rv.setAdapter(catAdapter);
        new com.codingtu.cooltu.lib4a.ui.recyclerview.DefaultConfig().config(getAct(), rv);
        // dogAdapter
        dogAdapter = new com.codingtu.cooltu.ui.adapter.DogAdapter() {
            @Override
            protected void loadMore(int page) {
                dogAdapterLoadMore(page);
            }
        };
        dogAdapter.setVH(core.vh.DogVH.class);
        dogAdapter.setClick(this);
        rv1.setAdapter(dogAdapter);
        new com.codingtu.cooltu.lib4a.ui.recyclerview.DefaultConfig().config(getAct(), rv1);




    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        }
    }



    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {

        }

        return false;

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

    protected abstract void dogAdapterLoadMore(int page);

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

    private com.codingtu.cooltu.lib4a.view.dialogview.EditDialog ed;

    protected void showEd(String text, com.codingtu.cooltu.bean.User user) {
        if (ed == null)
            ed = new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog.Builder(getAct())
                    .setTitle("xxx")
                    .setHint("xxx")
                    .setInputType(2)
                    .setLayout(com.codingtu.cooltu.R.layout.dialog_edit)
                    .setTextWatcher(getEdTextWatcher())
                    .stopAnimation()
                    .setYes(new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog.Yes() {
                        @Override
                        public boolean yes(String text, Object obj) {
                            return edYes(text, (com.codingtu.cooltu.bean.User)obj);
                        }
                    })
                    .build();
        ed.setEditText(text);
        ed.setObject(user);
        ed.show();
    }


    protected boolean edYes(String text, com.codingtu.cooltu.bean.User user) {
        return false;
    }
    protected com.codingtu.cooltu.lib4a.view.dialogview.EditDialog.EdTextWatcher getEdTextWatcher() {
        return null;
    }

    private com.codingtu.cooltu.lib4a.view.dialogview.Dialog dialog;
    protected void showDialog(com.codingtu.cooltu.bean.User user) {
        if (dialog == null) {
            dialog = new com.codingtu.cooltu.lib4a.view.dialogview.Dialog(getAct())
                    .setTitle("xxx")
                    .setContent("xxx")
                    .setLeftBtText("取消")
                    .setRighBtText("确定")
                    .setLayout(com.codingtu.cooltu.R.layout.dialog)
                    .setOnBtClick(new com.codingtu.cooltu.lib4a.view.dialogview.Dialog.OnBtClick() {
                        @Override
                        public void onLeftClick(Object obj) {
                            dialogLeft((com.codingtu.cooltu.bean.User)obj);
                        }

                        @Override
                        public void onRightClick(Object obj) {
                            dialogRight((com.codingtu.cooltu.bean.User)obj);
                        }
                    })
                    .build();
        }
        dialog.setObject(user);
        dialog.show();
    }
    protected void showDialog(String content, com.codingtu.cooltu.bean.User user) {
        if (dialog == null) {
            dialog = new com.codingtu.cooltu.lib4a.view.dialogview.Dialog(getAct())
                    .setTitle("xxx")
                    .setContent(content)
                    .setLeftBtText("取消")
                    .setRighBtText("确定")
                    .setLayout(com.codingtu.cooltu.R.layout.dialog)
                    .setOnBtClick(new com.codingtu.cooltu.lib4a.view.dialogview.Dialog.OnBtClick() {
                        @Override
                        public void onLeftClick(Object obj) {
                            dialogLeft((com.codingtu.cooltu.bean.User)obj);
                        }

                        @Override
                        public void onRightClick(Object obj) {
                            dialogRight((com.codingtu.cooltu.bean.User)obj);
                        }
                    })
                    .build();
        } else {
            dialog.updateContent(content);
        }
        dialog.setObject(user);
        dialog.show();
    }
    protected void dialogLeft(com.codingtu.cooltu.bean.User user) { }
    protected void dialogRight(com.codingtu.cooltu.bean.User user) { }

    protected com.codingtu.cooltu.bean.User user() {
        if (user == null) {
            user = new com.codingtu.cooltu.bean.User();
            user(user);
        }
        return user;
    }

    protected void user(com.codingtu.cooltu.bean.User user) {}
    protected com.codingtu.cooltu.ui.view.TestView testView() {
        if (testView == null) {
            testView = new com.codingtu.cooltu.ui.view.TestView();
            com.codingtu.cooltu.lib4a.tools.DestoryTool.onDestory(getAct(), testView);
            testView(testView);
        }
        return testView;
    }

    protected void testView(com.codingtu.cooltu.ui.view.TestView testView) {}




}

