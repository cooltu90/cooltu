package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormNewActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.EditText nameEt;
    protected android.widget.EditText cityEt;
    protected android.widget.EditText areaEt;
    protected android.widget.EditText provinceEt;
    protected com.codingtu.cooltu.bean.FormObj2 form;
    protected boolean initFormBean;
    public BindHandler bindHandler;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_form_new);

        nameEt = findViewById(com.codingtu.cooltu.R.id.nameEt);
        cityEt = findViewById(com.codingtu.cooltu.R.id.cityEt);
        areaEt = findViewById(com.codingtu.cooltu.R.id.areaEt);
        provinceEt = findViewById(com.codingtu.cooltu.R.id.provinceEt);






        initFormView();

        onCreateComplete();

    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();



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








    private void initFormView() {
        beforInitFormView();
        if (form == null) {
            form = new com.codingtu.cooltu.bean.FormObj2();
            initFormBean = true;
        }
        bindHandler = new BindHandler(form);
        if (!initFormBean) {
        }
    }
    protected void beforInitFormView() {}

    public static class BindHandler extends android.os.Handler {
        private com.codingtu.cooltu.bean.FormObj2 form;
        public BindHandler(com.codingtu.cooltu.bean.FormObj2 form) {
            this.form = form;
        }
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
            }
        }
    }


}

