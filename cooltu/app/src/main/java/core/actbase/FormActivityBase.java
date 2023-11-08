package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormActivityBase extends com.codingtu.cooltu.lib4a.ui.act.CoreActivity implements View.OnClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
    protected android.widget.TextView nameTv;
    protected android.widget.Button bt;
    protected android.widget.EditText nameEt1;
    protected android.widget.EditText nameEt2;
    protected android.widget.EditText nameEt3;
    protected com.codingtu.cooltu.bean.Forms formmmm;
    protected boolean initFormBean;
    public BindHandler bindHandler;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_form);

        nameTv = findViewById(com.codingtu.cooltu.R.id.nameTv);
        bt = findViewById(com.codingtu.cooltu.R.id.bt);
        nameEt1 = findViewById(com.codingtu.cooltu.R.id.nameEt1);
        nameEt2 = findViewById(com.codingtu.cooltu.R.id.nameEt2);
        nameEt3 = findViewById(com.codingtu.cooltu.R.id.nameEt3);

        bt.setOnClickListener(this);






        if (formmmm == null) {
            formmmm = new com.codingtu.cooltu.bean.Forms();
            initFormBean = true;
        }
        bindHandler = new BindHandler(formmmm);
        nameEt1.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(bindHandler, com.codingtu.cooltu.processor.annotation.form.FormType.EDIT_TEXT, 0));
        nameEt2.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(bindHandler, com.codingtu.cooltu.processor.annotation.form.FormType.EDIT_TEXT, 1));
        nameEt3.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(bindHandler, com.codingtu.cooltu.processor.annotation.form.FormType.EDIT_TEXT, 2));
        nameTv.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(bindHandler, com.codingtu.cooltu.processor.annotation.form.FormType.TEXT_VIEW, 0));

        onCreateComplete();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.bt:
                btClick(
                );
                break;

        }
    }

    protected void btClick() {}

    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

        }
    }

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {
        super.back(requestCode, permissions, grantResults);

    }

    public static class BindHandler extends android.os.Handler {
        private com.codingtu.cooltu.bean.Forms formmmm;

        public BindHandler(com.codingtu.cooltu.bean.Forms formmmm) {
            this.formmmm = formmmm;
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            if (msg.what == com.codingtu.cooltu.processor.annotation.form.FormType.EDIT_TEXT) {
                switch (msg.arg1) {
                    case 0:
                        formmmm.name1 = (java.lang.String) msg.obj;
                        break;
                    case 1:
                        formmmm.name2 = (java.lang.String) msg.obj;
                        break;
                    case 2:
                        formmmm.name3 = (java.lang.String) msg.obj;
                        break;
                }
            }
            if (msg.what == com.codingtu.cooltu.processor.annotation.form.FormType.TEXT_VIEW) {
                switch (msg.arg1) {
                    case 0:
                        formmmm.name4 = (java.lang.String) msg.obj;
                        break;
                }
            }
        }
    }

}

