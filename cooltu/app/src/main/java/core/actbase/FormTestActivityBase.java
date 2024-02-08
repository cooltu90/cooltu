package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormTestActivityBase extends com.codingtu.cooltu.ui.FormTestBaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.SeekBar timeSb;
    protected android.widget.EditText nameEt;
    protected android.widget.TextView submitBt;
    protected android.widget.EditText cityEt;
    protected android.widget.EditText areaEt;
    protected android.widget.EditText ageEt;
    protected android.widget.EditText provinceEt;
    protected android.widget.LinearLayout classLl;
    protected com.codingtu.cooltu.bean.FormObj forms;
    protected boolean initFormBean;
    public BindHandler bindHandler;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_form_test);

        timeSb = findViewById(com.codingtu.cooltu.R.id.timeSb);
        nameEt = findViewById(com.codingtu.cooltu.R.id.nameEt);
        submitBt = findViewById(com.codingtu.cooltu.R.id.submitBt);
        cityEt = findViewById(com.codingtu.cooltu.R.id.cityEt);
        areaEt = findViewById(com.codingtu.cooltu.R.id.areaEt);
        ageEt = findViewById(com.codingtu.cooltu.R.id.ageEt);
        provinceEt = findViewById(com.codingtu.cooltu.R.id.provinceEt);
        classLl = findViewById(com.codingtu.cooltu.R.id.classLl);






        initFormView();
        if (forms == null) {
            forms = new com.codingtu.cooltu.bean.FormObj();
            initFormBean = true;
        }
        bindHandler = new BindHandler(forms);
        new com.codingtu.cooltu.lib4a.formbind.push.DefaultEditTextPush().destory(this).bindHandler(bindHandler).addView(nameEt);
        new com.codingtu.cooltu.lib4a.formbind.push.DefaultEditTextPush().destory(this).bindHandler(bindHandler).addView(ageEt);
        new com.codingtu.cooltu.lib4a.formbind.push.DefaultEditTextPush().destory(this).bindHandler(bindHandler).addView(provinceEt);
        new com.codingtu.cooltu.lib4a.formbind.push.DefaultEditTextPush().destory(this).bindHandler(bindHandler).addView(cityEt);
        new com.codingtu.cooltu.lib4a.formbind.push.DefaultEditTextPush().destory(this).bindHandler(bindHandler).addView(areaEt);
        com.codingtu.cooltu.form.TypeOnSetItem typeOnSetItem = new com.codingtu.cooltu.form.TypeOnSetItem();
        new com.codingtu.cooltu.lib4a.formbind.push.DefaultRadioGroupPush()
                .destory(this).bindHandler(bindHandler)
                .onSetItem(typeOnSetItem).selected(null).addView(classLl);
        new com.codingtu.cooltu.lib4a.formbind.push.DefaultSeekBarPush().destory(this).bindHandler(bindHandler).addView(timeSb);
        if (!initFormBean) {
            forms.nameEcho(nameEt,forms.name);
            forms.nameEcho(ageEt,forms.age);
            com.codingtu.cooltu.lib4a.tools.ViewTool.setText(provinceEt, forms.province);
            com.codingtu.cooltu.lib4a.tools.ViewTool.setText(cityEt, forms.city);
            com.codingtu.cooltu.lib4a.tools.ViewTool.setText(areaEt, forms.area);
            getRadioGroup(classLl).setSelected(forms.classIndex);
            timeSb.setProgress(forms.seekBar);
        }


        onCreateComplete();

    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();

        submitBt.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.submitBt:
                submitBtClick(
                );
                break;

        }
    }

    protected void submitBtClick() {}


    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {

        }
        return super.onLongClick(v);


    }


    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {
        super.accept(code, result, params, objs);



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







    protected void initFormView() {}

    private boolean checkFormObj() {
        return true;
    }
    public static class BindHandler extends android.os.Handler {

        private com.codingtu.cooltu.bean.FormObj forms;
        private com.codingtu.cooltu.lib4j.data.map.ListValueMap<Integer, View> linkMap = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

        public BindHandler(com.codingtu.cooltu.bean.FormObj forms) {
            this.forms = forms;
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            List<View> views = linkMap.get(msg.what);
            switch (msg.what) {
                case com.codingtu.cooltu.R.id.nameEt:
                    forms.name = (java.lang.String) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.ageEt:
                    forms.age = (java.lang.String) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.provinceEt:
                    forms.province = (java.lang.String) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.cityEt:
                    forms.city = (java.lang.String) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.areaEt:
                    forms.area = (java.lang.String) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.classLl:
                    forms.classIndex = (int) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.timeSb:
                    forms.seekBar = (int) msg.obj;
                    break;
            }
        }
        public void link(int handleId, View... linkViews) {
            linkMap.get(handleId).addAll(com.codingtu.cooltu.lib4j.ts.Ts.ts(linkViews).toList());
        }
    }
    private com.codingtu.cooltu.lib4a.view.combine.RadioGroup getRadioGroup(android.view.ViewGroup viewGroup) {
        return ((com.codingtu.cooltu.lib4a.view.combine.RadioGroup) viewGroup.getTag(com.codingtu.cooltu.lib4a.R.id.tag_0));
    }




}

