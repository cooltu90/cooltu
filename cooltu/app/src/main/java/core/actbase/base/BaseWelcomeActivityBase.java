package core.actbase.base;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class BaseWelcomeActivityBase extends com.codingtu.cooltu.ui.base.CoreWelcomeActivity implements View.OnClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_welcome);









    }
    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.tv1:
            case com.codingtu.cooltu.R.id.tv3:
            case com.codingtu.cooltu.R.id.tv:
                tv3Click(
                );
                break;

        }
    }

    protected void tv3Click() {}

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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

