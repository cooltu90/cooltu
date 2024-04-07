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



        formHandler = new com.codingtu.cooltu.lib4a.form.FormHandler(this, this);


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








    /**************************************************
     *
     * form
     *
     **************************************************/
    protected void linkTextView(String methodName, android.widget.TextView tv, Object... views) {
        com.codingtu.cooltu.lib4a.form.FormTool.linkTextView(this, formHandler, methodName, tv, views);
    }
    protected com.codingtu.cooltu.lib4a.view.combine.RadioGroup obtainRadioGroup(android.view.ViewGroup viewGroup) {
        return com.codingtu.cooltu.lib4a.form.FormTool.obtainRadioGroup(this, viewGroup);
    }
    @Override
    public void handleMessage(android.os.Message msg, java.util.Map<String, Object[]> links) {
        Object[] objs;
        switch (msg.what) {
            case com.codingtu.cooltu.R.id.nicknameEt:
                objs = links.get("handleName");
                handleName(msg, (android.widget.EditText) objs[0], (android.widget.EditText) objs[1]);
                break;
            case com.codingtu.cooltu.R.id.nameEt:
                objs = links.get("handleName");
                handleName(msg, (android.widget.EditText) objs[0], (android.widget.EditText) objs[1]);
                objs = links.get("handleAge");
                handleAge(msg, (android.widget.EditText) objs[0], (android.widget.EditText) objs[1]);
                break;
        }
    }
    protected void handleName(android.os.Message msg, android.widget.EditText nameEt, android.widget.EditText nicknameEt) {
    }
    protected void handleAge(android.os.Message msg, android.widget.EditText nameEt, android.widget.EditText nicknameEt) {
    }


}

