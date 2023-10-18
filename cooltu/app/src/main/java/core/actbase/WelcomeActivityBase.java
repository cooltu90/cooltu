package core.actbase;

import android.view.View;

public abstract class WelcomeActivityBase extends com.codingtu.cooltu.ui.base.BaseWelcomeActivity implements View.OnClickListener {
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


}

