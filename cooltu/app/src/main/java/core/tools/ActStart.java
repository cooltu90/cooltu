package core.tools;

import android.app.Activity;
import android.content.Intent;

public class ActStart {
    public static final void formActivity(Activity act, com.codingtu.cooltu.bean.Forms forms) {
        Intent intent = new Intent(act, com.codingtu.cooltu.ui.FormActivity.class);
        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
        intent.putExtra(Pass.FORMS, com.codingtu.cooltu.lib4j.json.JsonTool.toJson(forms));
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.FORM_ACTIVITY);
    }
    public static final void stepOneActivity(Activity act) {
        Intent intent = new Intent(act, com.codingtu.cooltu.ui.StepOneActivity.class);
        intent.putExtra(Pass.FROM_ACT, act.getClass().getCanonicalName());
        com.codingtu.cooltu.lib4a.tools.ActTool.startActivityForResult(act, intent, Code4Request.STEP_ONE_ACTIVITY);
    }
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
