package core.actbase;

import android.view.View;

public abstract class WelcomeActivityBase extends com.codingtu.cooltu.ui.base.BaseWelcomeActivity implements View.OnClickListener {




    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        tv3.setOnClickListener(this);
        tv3RlRlTv.setOnClickListener(this);
        tv4RlRlTv.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

    }
}

