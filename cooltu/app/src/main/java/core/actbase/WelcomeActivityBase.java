package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class WelcomeActivityBase extends com.codingtu.cooltu.ui.base.BaseWelcomeActivity implements View.OnClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
    protected java.lang.String fromAct;
    protected java.lang.String name;
    protected int age;
    protected boolean isTest;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        backBt.setOnClickListener(this);





        fromAct = core.tools.Pass.fromAct(getIntent());
        name = core.tools.Pass.name(getIntent());
        age = core.tools.Pass.age(getIntent());
        isTest = core.tools.Pass.isTest(getIntent());





        onCreateComplete();

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.backBt:
                if (!isLogin(getAct())) {
                    return;
                }
                backBtClick(
                        v,
                        (com.codingtu.cooltu.bean.User) v.getTag(com.codingtu.cooltu.lib4a.R.id.tag_0)
                );
                break;

        }
    }

    protected void backBtClick(android.view.View v, com.codingtu.cooltu.bean.User user) {}

    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {
        super.accept(code, result, params, objs);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == android.app.Activity.RESULT_OK) {
            if (requestCode == core.tools.Code4Request.STEP_ONE_ACTIVITY) {
                stepOneActivityBack(core.tools.Pass.user(data), core.tools.Pass.xxx(data));
            }

        }
    }
    protected void stepOneActivityBack(com.codingtu.cooltu.bean.User user, java.lang.String xxx) {}

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {
        super.back(requestCode, permissions, grantResults);

    }









}

