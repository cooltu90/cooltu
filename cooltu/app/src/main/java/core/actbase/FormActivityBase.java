package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.EditText addressEt;
    protected android.widget.LinearLayout radios1;
    protected android.widget.EditText areaEt;
    protected android.widget.SeekBar timeSb;
    protected android.widget.Button bt;
    protected android.widget.TextView nameTv;
    protected android.widget.EditText nameEt1;
    protected android.widget.LinearLayout radios;
    protected android.widget.EditText cityEt;
    protected android.widget.EditText provinceEt;
    protected android.widget.EditText nameEt2;
    protected android.widget.EditText nameEt3;
    protected android.widget.SeekBar timeSb1;
    protected java.lang.String fromAct;
    protected com.codingtu.cooltu.bean.Forms forms;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_form);

        addressEt = findViewById(com.codingtu.cooltu.R.id.addressEt);
        radios1 = findViewById(com.codingtu.cooltu.R.id.radios1);
        areaEt = findViewById(com.codingtu.cooltu.R.id.areaEt);
        timeSb = findViewById(com.codingtu.cooltu.R.id.timeSb);
        bt = findViewById(com.codingtu.cooltu.R.id.bt);
        nameTv = findViewById(com.codingtu.cooltu.R.id.nameTv);
        nameEt1 = findViewById(com.codingtu.cooltu.R.id.nameEt1);
        radios = findViewById(com.codingtu.cooltu.R.id.radios);
        cityEt = findViewById(com.codingtu.cooltu.R.id.cityEt);
        provinceEt = findViewById(com.codingtu.cooltu.R.id.provinceEt);
        nameEt2 = findViewById(com.codingtu.cooltu.R.id.nameEt2);
        nameEt3 = findViewById(com.codingtu.cooltu.R.id.nameEt3);
        timeSb1 = findViewById(com.codingtu.cooltu.R.id.timeSb1);





        fromAct = core.tools.Pass.fromAct(getIntent());
        forms = core.tools.Pass.forms(getIntent());


        onCreateComplete();

    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();

        bt.setOnClickListener(this);

        bt.setOnLongClickListener(this);

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
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.bt:
                return btLongClick(
                );

        }

        return false;

    }

    protected boolean btLongClick() {return false;}

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

