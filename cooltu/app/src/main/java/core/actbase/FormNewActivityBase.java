package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormNewActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.EditText age1Et;
    protected android.widget.LinearLayout numLl;
    protected android.widget.TextView saveBt1;
    protected android.widget.TextView saveBt2;
    protected android.widget.EditText nameEt;
    protected android.widget.EditText areaEt;
    protected android.widget.EditText ageEt;
    protected android.widget.TextView idTv;
    protected android.widget.SeekBar heightSb;
    protected android.widget.EditText id1Et;
    protected android.widget.SeekBar timeSb;
    protected android.widget.EditText nicknameEt;
    protected android.widget.LinearLayout num1Ll;
    protected android.widget.LinearLayout num2Ll;
    protected android.widget.EditText cityEt;
    protected android.widget.TextView passwordTv;
    protected android.widget.EditText provinceEt;
    protected android.widget.EditText idEt;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_form_new);

        age1Et = findViewById(com.codingtu.cooltu.R.id.age1Et);
        numLl = findViewById(com.codingtu.cooltu.R.id.numLl);
        saveBt1 = findViewById(com.codingtu.cooltu.R.id.saveBt1);
        saveBt2 = findViewById(com.codingtu.cooltu.R.id.saveBt2);
        nameEt = findViewById(com.codingtu.cooltu.R.id.nameEt);
        areaEt = findViewById(com.codingtu.cooltu.R.id.areaEt);
        ageEt = findViewById(com.codingtu.cooltu.R.id.ageEt);
        idTv = findViewById(com.codingtu.cooltu.R.id.idTv);
        heightSb = findViewById(com.codingtu.cooltu.R.id.heightSb);
        id1Et = findViewById(com.codingtu.cooltu.R.id.id1Et);
        timeSb = findViewById(com.codingtu.cooltu.R.id.timeSb);
        nicknameEt = findViewById(com.codingtu.cooltu.R.id.nicknameEt);
        num1Ll = findViewById(com.codingtu.cooltu.R.id.num1Ll);
        num2Ll = findViewById(com.codingtu.cooltu.R.id.num2Ll);
        cityEt = findViewById(com.codingtu.cooltu.R.id.cityEt);
        passwordTv = findViewById(com.codingtu.cooltu.R.id.passwordTv);
        provinceEt = findViewById(com.codingtu.cooltu.R.id.provinceEt);
        idEt = findViewById(com.codingtu.cooltu.R.id.idEt);






        onCreateComplete();

    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();

        saveBt1.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.saveBt1:
                saveBt1Click(
                );
                break;

        }
    }

    protected void saveBt1Click() {}


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










}

