package core.actbase.base;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class CoreWelcomeActivityBase extends com.codingtu.cooltu.lib4a.act.CoreActivity implements View.OnClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
    protected android.widget.RelativeLayout tv3;
    protected java.lang.String fromAct;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);









    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        }
    }


    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {

    }

}

