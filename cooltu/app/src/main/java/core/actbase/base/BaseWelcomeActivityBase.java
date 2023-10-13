package core.actbase.base;

import android.view.View;

public abstract class BaseWelcomeActivityBase extends com.codingtu.cooltu.ui.base.CoreWelcomeActivity implements View.OnClickListener {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_welcome);
    }


    @Override
    public void onClick(View v) {

    }
}

