package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI,com.codingtu.cooltu.lib4a.form.FormHandleCallBack{
    protected android.widget.LinearLayout numLl;
    protected android.widget.EditText nicknameEt;
    protected android.widget.TextView saveBt;
    protected android.widget.EditText nameEt;
    protected com.codingtu.cooltu.form.DataFormConfig dataFormConfig;
    protected com.codingtu.cooltu.lib4a.form.FormHandler formHandler;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_form);

        numLl = findViewById(com.codingtu.cooltu.R.id.numLl);
        nicknameEt = findViewById(com.codingtu.cooltu.R.id.nicknameEt);
        saveBt = findViewById(com.codingtu.cooltu.R.id.saveBt);
        nameEt = findViewById(com.codingtu.cooltu.R.id.nameEt);






        onCreateComplete();

    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();

        saveBt.setOnClickListener(this);


        initFormView();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.saveBt:
                saveBtClick(
                );
                break;

        }
    }

    protected void saveBtClick() {}


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








    protected void initFormView() {
        formHandler = new com.codingtu.cooltu.lib4a.form.FormHandler(this, this);
        dataFormConfig = new com.codingtu.cooltu.form.DataFormConfig();
        nameEt.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(this, formHandler, nameEt));
        formHandler.link(nameEt.getId(), "handleName", nameEt, nicknameEt);
        formHandler.link(nameEt.getId(), "handleName1", nameEt, nicknameEt);
        numLl.setTag(com.codingtu.cooltu.lib4a.R.id.tag_0,
                com.codingtu.cooltu.lib4a.view.combine.RadioGroup.obtain(this)
                        .setBts(dataFormConfig.getNumViews(numLl))
                        .setOnSetItem(new com.codingtu.cooltu.form.TypeOnSetItem()));
        com.codingtu.cooltu.lib4a.tools.ViewTool.getRadioGroup(numLl).addOnSelectChange(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSelectChange(this, formHandler, numLl.getId()));
        formHandler.link(numLl.getId(), "handleNum", numLl);

    }
    @Override
    public void handleMessage(android.os.Message msg, java.util.Map<String, Object[]> links) {
        Object[] objs;
        switch (msg.what) {
            case com.codingtu.cooltu.R.id.nameEt:
                objs = links.get("handleName");
                dataFormConfig.handleName(msg, (android.widget.EditText) objs[0], (android.widget.EditText) objs[1]);
                objs = links.get("handleName1");
                dataFormConfig.handleName1(msg, (android.widget.EditText) objs[0], (android.widget.EditText) objs[1]);
                break;
            case com.codingtu.cooltu.R.id.numLl:
                objs = links.get("handleNum");
                dataFormConfig.handleNum(msg, (android.widget.LinearLayout) objs[0]);
                break;

        }
    }
    protected com.codingtu.cooltu.bean.FormDatas.FormData obtainFormData(com.codingtu.cooltu.bean.FormDatas.FormData formData) {
        if (formData == null) {
            formData = new com.codingtu.cooltu.bean.FormDatas.FormData();
        }
        formData = dataFormConfig.checkName(formData, formData.name, nameEt);
        formData.nickname = nicknameEt.getText().toString();
        formData = dataFormConfig.checkNum(formData, formData.num, numLl);
        return formData;
    }

    protected void echo(com.codingtu.cooltu.bean.FormDatas.FormData formData) {
        dataFormConfig.echoName(formData, formData.name, nameEt, nicknameEt);
        dataFormConfig.echoNum(formData, formData.num, numLl);
    }



}

