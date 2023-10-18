package core.tools;

import android.app.Activity;
import android.content.Intent;

public class ActStart {
    public static final void welcomeActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu.ui.WelcomeActivity.class);
        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.WELCOME_ACTIVITY);
    }
    public static final void welcomeActivity(Activity act, java.lang.String name, int age, boolean isTest) {
        Intent intent = new Intent(act, com.codingtu.cooltu.ui.WelcomeActivity.class);
        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
        intent.putExtra(Pass.NAME, name);
        intent.putExtra(Pass.AGE, age);
        intent.putExtra(Pass.IS_TEST, isTest);
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.WELCOME_ACTIVITY);
    }
    public static final void welcomeActivity(Activity act, java.lang.String name) {
        Intent intent = new Intent(act, com.codingtu.cooltu.ui.WelcomeActivity.class);
        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
        intent.putExtra(Pass.NAME, name);
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.WELCOME_ACTIVITY);
    }

}
