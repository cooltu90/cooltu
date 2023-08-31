package core.actbase;

import android.os.Bundle;
import android.view.View;

public abstract class WelcomeActivityBase extends com.codingtu.cooltu.lib4a.act.CoreActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_welcome);
    }

    @Override
    public void onClick(View v) {

    }
}
