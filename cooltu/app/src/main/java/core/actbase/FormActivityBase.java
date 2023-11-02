package core.actbase;

import android.view.View;

import com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher;
import com.codingtu.cooltu.processor.annotation.form.FormType;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormActivityBase extends com.codingtu.cooltu.lib4a.ui.act.CoreActivity implements View.OnClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
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

        nameEt1 = findViewById(com.codingtu.cooltu.R.id.nameEt1);
        nameEt2 = findViewById(com.codingtu.cooltu.R.id.nameEt2);
        nameEt3 = findViewById(com.codingtu.cooltu.R.id.nameEt3);

        onCreateComplete();

    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();

        if (formmmm == null) {
            formmmm = new com.codingtu.cooltu.bean.Forms();
            initFormBean = true;
        }
        bindHandler = new BindHandler(formmmm);

        nameEt1.addTextChangedListener(new HandlerTextWatcher(bindHandler, FormType.EDIT_TEXT, 0));
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
        private com.codingtu.cooltu.bean.Forms formmmm;

        public BindHandler(com.codingtu.cooltu.bean.Forms formmmm) {
            this.formmmm = formmmm;
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
        }
    }

}

