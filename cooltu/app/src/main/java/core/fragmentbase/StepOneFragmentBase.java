package core.fragmentbase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class StepOneFragmentBase extends com.codingtu.cooltu.ui.BaseStepFragment implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.TextView tv1;
    protected int textColor;
    protected int tvColor;
    protected int dp;
    protected int dp1;
    protected com.codingtu.cooltu.ui.adapter.DogAdapter dogAdapter;
    protected androidx.recyclerview.widget.RecyclerView rv1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = com.codingtu.cooltu.lib4a.tools.InflateTool.inflate(inflater, com.codingtu.cooltu.R.layout.fragment_step_one, container);
        tv2 = view.findViewById(com.codingtu.cooltu.R.id.tv2);
        rv = view.findViewById(com.codingtu.cooltu.R.id.rv);
        tv1 = view.findViewById(com.codingtu.cooltu.R.id.tv1);
        textColor = android.graphics.Color.parseColor("#ffffff");
        tvColor = com.codingtu.cooltu.lib4a.tools.ResourceTool.getColor(com.codingtu.cooltu.R.color.black);
        dp = com.codingtu.cooltu.lib4a.tools.MobileTool.dpToPx(12.5f);
        dp1 = com.codingtu.cooltu.lib4a.tools.ResourceTool.getDimen(com.codingtu.cooltu.R.dimen.xxx);

        onCreateComplete();
        return view;
    }


    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
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

        tv1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.tv1:
                tv1Click(
                        (com.codingtu.cooltu.bean.User) v.getTag(com.codingtu.cooltu.lib4a.R.id.tag_0)
                );
                break;

        }
    }
    protected void tv1Click(com.codingtu.cooltu.bean.User user) {}


    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {

        }
        return super.onLongClick(v);



    }



    protected abstract void dogAdapterLoadMore(int page);

    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {
        super.accept(code, result, params, objs);


        if ("getObjBack".equals(code)) {
            new core.net.back.GetObjBack() {
                @Override
                public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {
                    super.accept(code, result, params, objs);
                    getObjBack(user);
                }
            }.accept(code, result, params, objs);
        }

    }
    protected void getObjBack(com.codingtu.cooltu.bean.User user) {}

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == android.app.Activity.RESULT_OK) {

        }
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
    protected void toastShow(long time, String msg, com.codingtu.cooltu.lib4a.view.layer.event.OnHiddenFinishedCallBack onHiddenFinished) {
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

    protected void toastHidden(long time, String msg, com.codingtu.cooltu.lib4a.view.layer.event.OnHiddenFinishedCallBack onHiddenFinished) {
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
    private com.codingtu.cooltu.lib4a.view.dialogview.Dialog dialog1;
    protected void showDialog1() {
        if (dialog1 == null) {
            dialog1 = new com.codingtu.cooltu.lib4a.view.dialogview.Dialog(getAct())
                    .setTitle("xxx")
                    .setContent("32343234")
                    .setLeftBtText("取消")
                    .setRighBtText("确定")
                    .setLayout(com.codingtu.cooltu.R.layout.dialog)
                    .setOnBtClick(new com.codingtu.cooltu.lib4a.view.dialogview.Dialog.OnBtClick() {
                        @Override
                        public void onLeftClick(Object obj) {
                            dialog1Left();
                        }

                        @Override
                        public void onRightClick(Object obj) {
                            dialog1Right();
                        }
                    })
                    .build();
        }
        dialog1.setObject(null);
        dialog1.show();
    }
    protected void showDialog1(String content) {
        if (dialog1 == null) {
            dialog1 = new com.codingtu.cooltu.lib4a.view.dialogview.Dialog(getAct())
                    .setTitle("xxx")
                    .setContent(content)
                    .setLeftBtText("取消")
                    .setRighBtText("确定")
                    .setLayout(com.codingtu.cooltu.R.layout.dialog)
                    .setOnBtClick(new com.codingtu.cooltu.lib4a.view.dialogview.Dialog.OnBtClick() {
                        @Override
                        public void onLeftClick(Object obj) {
                            dialog1Left();
                        }

                        @Override
                        public void onRightClick(Object obj) {
                            dialog1Right();
                        }
                    })
                    .build();
        } else {
            dialog1.updateContent(content);
        }
        dialog1.setObject(null);
        dialog1.show();
    }
    protected void dialog1Left() { }
    protected void dialog1Right() { }




}
