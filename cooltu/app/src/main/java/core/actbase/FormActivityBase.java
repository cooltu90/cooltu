package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormActivityBase extends com.codingtu.cooltu.lib4a.ui.act.CoreActivity implements View.OnClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
    protected android.widget.EditText nameEt;
    protected com.codingtu.cooltu.bean.Forms forms;
    protected boolean initFormBean;
    public BindHandler bindHandler;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_form);

        nameEt = findViewById(com.codingtu.cooltu.R.id.nameEt);







        if (forms == null) {
            forms = new com.codingtu.cooltu.bean.Forms();
            initFormBean = true;
        }
        bindHandler = new BindHandler(forms);

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
        private com.codingtu.cooltu.bean.Forms forms;

        public BindHandler(com.codingtu.cooltu.bean.Forms forms) {
            this.forms = forms;
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
        }
    }

}

