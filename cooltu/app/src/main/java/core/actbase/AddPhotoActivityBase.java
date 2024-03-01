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
    protected com.codingtu.cooltu.bean.Photo photo;
    protected boolean initFormBean;
    public BindHandler bindHandler;


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






        initFormView();
        if (photo == null) {
            photo = new com.codingtu.cooltu.bean.Photo();
            initFormBean = true;
        }
        bindHandler = new BindHandler(photo);
        new com.codingtu.cooltu.lib4a.form.push.DefaultEditTextPush().destory(this).bindHandler(bindHandler).addView(schoolEt);
        new com.codingtu.cooltu.lib4a.form.push.DefaultEditTextPush().destory(this).bindHandler(bindHandler).addView(labelEt);
        new com.codingtu.cooltu.lib4a.form.push.DefaultEditTextPush().destory(this).bindHandler(bindHandler).addView(name1Et);
        new com.codingtu.cooltu.lib4a.form.push.DefaultEditTextPush().destory(this).bindHandler(bindHandler).addView(name2Et);
        new com.codingtu.cooltu.lib4a.form.push.DefaultEditTextPush().destory(this).bindHandler(bindHandler).addView(classEt);
        com.codingtu.cooltu.form.TypeOnSetItem typeOnSetItem = new com.codingtu.cooltu.form.TypeOnSetItem();
        new com.codingtu.cooltu.lib4a.form.push.DefaultRadioGroupPush()
                .destory(this).bindHandler(bindHandler)
                .onSetItem(typeOnSetItem).selected(-1).addView(classLl);
        new com.codingtu.cooltu.lib4a.form.push.DefaultRadioGroupPush()
                .destory(this).bindHandler(bindHandler)
                .onSetItem(typeOnSetItem).selected(-1).addView(numberLl);
        new com.codingtu.cooltu.lib4a.form.push.DefaultEditTextPush().destory(this).bindHandler(bindHandler).addView(otherEt);
        new com.codingtu.cooltu.lib4a.form.push.DefaultSeekBarPush().destory(this).bindHandler(bindHandler).addView(timeSb);
        bindHandler.link(com.codingtu.cooltu.R.id.numberLl, otherEt);
        bindHandler.link(com.codingtu.cooltu.R.id.classLl, classEt);
        bindHandler.link(com.codingtu.cooltu.R.id.otherEt, getRadioGroup(numberLl), otherBt);
        bindHandler.link(com.codingtu.cooltu.R.id.name1Et, name2Et);
        if (!initFormBean) {
            com.codingtu.cooltu.lib4a.tools.ViewTool.setText(labelEt, photo.label);
            com.codingtu.cooltu.lib4a.tools.ViewTool.setText(name1Et, photo.name1);
            com.codingtu.cooltu.lib4a.tools.ViewTool.setText(name2Et, photo.name2);
            com.codingtu.cooltu.lib4a.tools.ViewTool.setText(classEt, photo.className);
            getRadioGroup(classLl).setSelected(photo.classType);
            getRadioGroup(numberLl).setSelected(photo.numbers);
            com.codingtu.cooltu.lib4a.tools.ViewTool.setText(otherEt, photo.others);
            timeSb.setProgress(photo.time);
        }


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



    protected void initFormView() {}

    private boolean checkPhoto() {
        if (!photo.checkLabel(photo.school)) {
            toast("请输入学校");
            return false;
        }
        if (!photo.checkLabel(photo.label)) {
            toast("请输入标签");
            return false;
        }
        if (!photo.checkLabel(photo.name1)) {
            toast("请输入name1");
            return false;
        }
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isBlank(photo.name2)) {
            toast("请输入name2");
            return false;
        }
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isBlank(photo.className)) {
            toast("请输入班级名");
            return false;
        }
        if (photo.classType < 0) {
            toast("请选择班级类型");
            return false;
        }
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isBlank(photo.others)) {
            toast("请输入数字");
            return false;
        }
        return true;
    }
    public static class BindHandler extends android.os.Handler {

        private com.codingtu.cooltu.bean.Photo photo;
        private com.codingtu.cooltu.lib4j.data.map.ListValueMap<Integer, Object> linkMap = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

        public BindHandler(com.codingtu.cooltu.bean.Photo photo) {
            this.photo = photo;
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            List views = linkMap.get(msg.what);
            switch (msg.what) {
                case com.codingtu.cooltu.R.id.schoolEt:
                    photo.school = (java.lang.String) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.labelEt:
                    photo.label = (java.lang.String) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.name1Et:
                    photo.name1 = (java.lang.String) msg.obj;
                    photo.dealName1((android.widget.EditText) views.get(0));
                    break;
                case com.codingtu.cooltu.R.id.name2Et:
                    photo.name2 = (java.lang.String) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.classEt:
                    photo.className = (java.lang.String) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.classLl:
                    photo.classType = (int) msg.obj;
                    photo.dealClass((android.widget.EditText) views.get(0));
                    break;
                case com.codingtu.cooltu.R.id.numberLl:
                    photo.numbers = (int) msg.obj;
                    photo.dealNumbers((android.widget.EditText) views.get(0));
                    break;
                case com.codingtu.cooltu.R.id.otherEt:
                    photo.others = (java.lang.String) msg.obj;
                    photo.dealOthers((com.codingtu.cooltu.lib4a.view.combine.RadioGroup) views.get(0), (android.widget.TextView) views.get(1));
                    break;
                case com.codingtu.cooltu.R.id.timeSb:
                    photo.time = (int) msg.obj;
                    break;
            }
        }
        public void link(int handleId, Object... linkViews) {
            linkMap.get(handleId).addAll(com.codingtu.cooltu.lib4j.ts.Ts.ts(linkViews).toList());
        }
    }
    private com.codingtu.cooltu.lib4a.view.combine.RadioGroup getRadioGroup(android.view.ViewGroup viewGroup) {
        return ((com.codingtu.cooltu.lib4a.view.combine.RadioGroup) viewGroup.getTag(com.codingtu.cooltu.lib4a.R.id.tag_0));
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

