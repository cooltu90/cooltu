package core.actbase;

import android.view.View;

import com.codingtu.cooltu.bean.Info;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormNewActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
    protected android.widget.TextView saveBt1;
    protected android.widget.EditText nicknameEt;
    protected android.widget.TextView saveBt2;
    protected android.widget.EditText nameEt;
    protected android.widget.EditText cityEt;
    protected android.widget.EditText areaEt;
    protected android.widget.EditText provinceEt;
    protected com.codingtu.cooltu.bind.InfoBindConfig infoBindConfig;
    protected Info info;
    protected boolean initInfo;
    protected com.codingtu.cooltu.bind.FormBindConfig formBindConfig;
    protected boolean initForm;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_form_new);

        saveBt1 = findViewById(com.codingtu.cooltu.R.id.saveBt1);
        nicknameEt = findViewById(com.codingtu.cooltu.R.id.nicknameEt);
        saveBt2 = findViewById(com.codingtu.cooltu.R.id.saveBt2);
        nameEt = findViewById(com.codingtu.cooltu.R.id.nameEt);
        cityEt = findViewById(com.codingtu.cooltu.R.id.cityEt);
        areaEt = findViewById(com.codingtu.cooltu.R.id.areaEt);
        provinceEt = findViewById(com.codingtu.cooltu.R.id.provinceEt);


        initBindView();

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


    protected void initBindView() {
        beforeInitBindView();

    }

    protected void beforeInitBindView() {
    }


}

