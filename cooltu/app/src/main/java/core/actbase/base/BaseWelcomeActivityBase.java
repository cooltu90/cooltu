package core.actbase.base;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class BaseWelcomeActivityBase extends com.codingtu.cooltu.ui.base.CoreWelcomeActivity implements View.OnClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
    protected android.widget.RelativeLayout tv3Rl;
    protected android.widget.RelativeLayout tv4;
    protected android.widget.TextView tv6;
    protected android.widget.RelativeLayout tv4Rl;
    protected android.widget.TextView tv3RlRlTv;
    protected android.widget.LinearLayout rootLl;
    protected android.widget.TextView tv1;
    protected android.widget.TextView tv4RlRlTv;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_welcome);

        tv3 = findViewById(com.codingtu.cooltu.R.id.tv3);
        tv3Rl = tv3.findViewById(com.codingtu.cooltu.R.id.rl);
        tv4 = findViewById(com.codingtu.cooltu.R.id.tv4);
        tv6 = findViewById(com.codingtu.cooltu.R.id.tv6);
        tv4Rl = tv4.findViewById(com.codingtu.cooltu.R.id.rl);
        tv3RlRlTv = tv3Rl.findViewById(com.codingtu.cooltu.R.id.tv);
        rootLl = findViewById(com.codingtu.cooltu.R.id.rootLl);
        tv1 = findViewById(com.codingtu.cooltu.R.id.tv1);
        tv4RlRlTv = tv4Rl.findViewById(com.codingtu.cooltu.R.id.tv);

        tv1.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv3RlRlTv.setOnClickListener(this);
        tv4RlRlTv.setOnClickListener(this);











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
        if (requestCode == core.tools.Permissions.CODE_CHECK_IN_BASE_WELCOME_ACTIVITY) {
            check(com.codingtu.cooltu.lib4a.permission.PermissionTool.allow(grantResults));
        }

    }
    protected void check(boolean isAllow) {}





}

