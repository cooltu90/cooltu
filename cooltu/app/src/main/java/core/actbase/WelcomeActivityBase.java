package core.actbase;

import android.view.View;

import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class WelcomeActivityBase extends com.codingtu.cooltu.ui.base.BaseWelcomeActivity implements View.OnClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
    protected int textColor;
    protected int tvColor;
    protected int dp;
    protected int dp1;
    protected java.lang.String name;
    protected int age;
    protected boolean isTest;


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
                public void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs) {
                    super.accept(code, result, params, objs);
                    getObjBack(user);
                }
            }.accept(code, result, params, objs);
        }
    }

    public void getObjBack(User user) {
    }
}

