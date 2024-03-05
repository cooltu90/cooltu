package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class AddPhotoActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.EditText otherEt;
    protected android.widget.SeekBar timeSb;
    protected android.widget.EditText name2Et;
    protected android.widget.TextView submitBt;
    protected android.widget.EditText classEt;
    protected android.widget.LinearLayout numberLl;
    protected android.widget.EditText name1Et;
    protected android.widget.EditText labelEt;
    protected android.widget.LinearLayout classLl;
    protected android.widget.EditText schoolEt;
    protected android.widget.TextView otherBt;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_add_photo);

        otherEt = findViewById(com.codingtu.cooltu.R.id.otherEt);
        timeSb = findViewById(com.codingtu.cooltu.R.id.timeSb);
        name2Et = findViewById(com.codingtu.cooltu.R.id.name2Et);
        submitBt = findViewById(com.codingtu.cooltu.R.id.submitBt);
        classEt = findViewById(com.codingtu.cooltu.R.id.classEt);
        numberLl = findViewById(com.codingtu.cooltu.R.id.numberLl);
        name1Et = findViewById(com.codingtu.cooltu.R.id.name1Et);
        labelEt = findViewById(com.codingtu.cooltu.R.id.labelEt);
        classLl = findViewById(com.codingtu.cooltu.R.id.classLl);
        schoolEt = findViewById(com.codingtu.cooltu.R.id.schoolEt);
        otherBt = findViewById(com.codingtu.cooltu.R.id.otherBt);







        onCreateComplete();

    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.reportTv:
                reportTvClick(
                        (java.lang.String) v.getTag(com.codingtu.cooltu.lib4a.R.id.tag_0)
                );
                break;
            case com.codingtu.cooltu.R.id.deleteItemBt:
                deleteItemBtClick(
                        (java.lang.String) v.getTag(com.codingtu.cooltu.lib4a.R.id.tag_0)
                );
                break;
            case com.codingtu.cooltu.R.id.deleteItemBt1:
                deleteItemBt1Click(
                );
                break;

        }
    }

    protected void reportTvClick(java.lang.String str) {}
    protected void deleteItemBtClick(java.lang.String str) {}
    protected void deleteItemBt1Click() {}


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




    private com.codingtu.cooltu.lib4a.view.dialogview.EditDialog dialog;

    protected void showDialog(String text) {
        if (dialog == null)
            dialog = new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog.Builder(getAct())
                    .setTitle("xxx")
                    .setHint("xxx")
                    .setInputType(1)
                    .setLayout(com.codingtu.cooltu.R.layout.dialog_edit)
                    .setYes(new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog.Yes() {
                        @Override
                        public boolean yes(String text, Object obj) {
                            return dialogYes(text);
                        }
                    })
                    .build();
        dialog.setEditText(text);
        dialog.setObject(null);
        dialog.show();
    }


    protected boolean dialogYes(String text) {
        return false;
    }




    /**************************************************
     *
     *  menuDialog
     *
     **************************************************/
    protected com.codingtu.cooltu.lib4a.view.dialogview.MenuDialog menuDialog;

    protected void showMenuDialog(java.lang.String obj) {
        if (menuDialog == null) {
            menuDialog = new com.codingtu.cooltu.lib4a.view.dialogview.MenuDialog(getAct())
                    .setLayout(com.codingtu.cooltu.R.layout.dialog_menu)
                    .setItemLayout(com.codingtu.cooltu.R.layout.dialog_menu_item)
                    .setItem(com.codingtu.cooltu.R.id.reportTv, "导出工单")
                    .setItem(com.codingtu.cooltu.R.id.deleteItemBt, "删除")
                    .setItem(com.codingtu.cooltu.R.id.deleteItemBt1, "删除1")
                    .setOnClickListener(this)
                    .setShowItem(new com.codingtu.cooltu.lib4a.view.dialogview.MenuDialog.ShowItem() {
                        @Override
                        public boolean showItem(int viewId, Object obj) {
                            switch (viewId) {
                                case com.codingtu.cooltu.R.id.reportTv:
                                    return showReportTv((java.lang.String) obj);
                                case com.codingtu.cooltu.R.id.deleteItemBt:
                                    return showDeleteItemBt((java.lang.String) obj);
                                case com.codingtu.cooltu.R.id.deleteItemBt1:
                                    return showDeleteItemBt1((java.lang.String) obj);
                            }
                            return false;
                        }
                    })
                    .setTitle("操作1")
                    .build();
        }
        menuDialog.setObj(obj);
        menuDialog.show();
    }
    protected boolean showReportTv(java.lang.String str) {
        return true;
    }
    protected boolean showDeleteItemBt(java.lang.String str) {
        return true;
    }
    protected boolean showDeleteItemBt1(java.lang.String str) {
        return true;
    }


}

