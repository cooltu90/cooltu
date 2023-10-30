package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class WelcomeActivityBase extends com.codingtu.cooltu.ui.base.BaseWelcomeActivity implements View.OnClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
    protected int textColor;
    protected int tvColor;
    protected int dp;
    protected int dp1;
    protected java.lang.String fromAct;
    protected java.lang.String name;
    protected int age;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        textColor = android.graphics.Color.parseColor("#ffffff");

        tvColor = com.codingtu.cooltu.lib4a.tools.ResourceTool.getColor(com.codingtu.cooltu.R.color.black);

        dp = com.codingtu.cooltu.lib4a.tools.MobileTool.dpToPx(12.5f);

        dp1 = com.codingtu.cooltu.lib4a.tools.ResourceTool.getDimen(com.codingtu.cooltu.R.dimen.xxx);

        fromAct = core.tools.Pass.fromAct(getIntent());
        name = core.tools.Pass.name(getIntent());
        age = core.tools.Pass.age(getIntent());
        isTest = core.tools.Pass.isTest(getIntent());


    }
    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {

        }
    }


    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {
        if ("getObjBack".equals(code)) {
            new core.net.back.GetObjBack() {
                @Override
                public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {
                    super.accept(code, result, params, objs);
                    getObjBack(this, (core.net.params.GetObjParams)params, user, objs);
                }
            }.accept(code, result, params, objs);
        }

    }
    protected void getObjBack(core.net.back.GetObjBack back, core.net.params.GetObjParams params, com.codingtu.cooltu.bean.User user, java.util.List objs) {}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == core.tools.Code4Request.WELCOME_ACTIVITY) {
                welcomeActivityBack(core.tools.Pass.user(data), core.tools.Pass.users(data));
            }

        }
    }
    protected void welcomeActivityBack(com.codingtu.cooltu.bean.User user, java.util.List<com.codingtu.cooltu.bean.User> users) {}

}

