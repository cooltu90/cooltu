package core.actbase.base;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class CoreWelcomeActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.RelativeLayout tv3;
    protected boolean isTest;
    protected android.widget.TextView tv6;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);












    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
        tv6.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.tv6:
                tv6Click(
                );
                break;

        }
    }

    protected void tv6Click() {}


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

