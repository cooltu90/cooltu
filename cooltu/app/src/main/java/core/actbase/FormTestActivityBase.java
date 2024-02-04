package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormTestActivityBase extends com.codingtu.cooltu.ui.FormTestBaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
    protected android.widget.TextView testBt;
    protected com.codingtu.cooltu.bean.FormsObj forms;
    protected boolean initFormBean;
    protected BindHandler bindHandler;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_form_test);

        testBt = findViewById(com.codingtu.cooltu.R.id.testBt);
        ymdEt = findViewById(com.codingtu.cooltu.R.id.ymdEt);
        nameEt = findViewById(com.codingtu.cooltu.R.id.nameEt);
        cityEt = findViewById(com.codingtu.cooltu.R.id.cityEt);
        areaEt = findViewById(com.codingtu.cooltu.R.id.areaEt);
        provinceEt = findViewById(com.codingtu.cooltu.R.id.provinceEt);
        countEt = findViewById(com.codingtu.cooltu.R.id.countEt);


        initFormView();
        if (forms == null) {
            forms = new com.codingtu.cooltu.bean.FormsObj();
            initFormBean = true;
        }
        bindHandler = new BindHandler(this, forms);

        new com.codingtu.cooltu.lib4a.form.advice.DefaultEditTextAdvice().addHandler(bindHandler).addViews(nameEt).start();
        new com.codingtu.cooltu.lib4a.form.advice.DefaultEditTextAdvice().addHandler(bindHandler).addViews(provinceEt).start();
        new com.codingtu.cooltu.lib4a.form.advice.DefaultEditTextAdvice().addHandler(bindHandler).addViews(cityEt).start();
        new com.codingtu.cooltu.lib4a.form.advice.DefaultEditTextAdvice().addHandler(bindHandler).addViews(areaEt).start();
        new com.codingtu.cooltu.lib4a.form.advice.DefaultEditTextAdvice().addHandler(bindHandler).addViews(ymdEt).start();
        if (!initFormBean) {
        }


        onCreateComplete();

    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();

        testBt.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.testBt:
                testBtClick(
                );
                break;

        }
    }

    protected void testBtClick() {
    }


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


    protected void initFormView() {
    }


}

