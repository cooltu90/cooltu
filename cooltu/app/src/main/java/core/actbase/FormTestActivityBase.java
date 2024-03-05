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







        onCreateComplete();

    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();



    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {

        }
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










}

