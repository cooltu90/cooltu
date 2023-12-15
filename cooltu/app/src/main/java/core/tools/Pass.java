package core.tools;

import android.content.Intent;

public class Pass {
    public static final String FROM_ACT = "fromAct";
    public static final String USER = "user";
    public static final String XXX = "xxx";
    public static final String XX = "xx";
    public static final String FORMS = "forms";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String IS_TEST = "isTest";

    public static final String fromAct(Intent data) {
        return data.getStringExtra(FROM_ACT);
    }
    public static final com.codingtu.cooltu.bean.User user(Intent data) {
        return com.codingtu.cooltu.lib4j.json.JsonTool.toBean(com.codingtu.cooltu.bean.User.class, data.getStringExtra(USER));
    }
    public static final String xxx(Intent data) {
        return data.getStringExtra(XXX);
    }
    public static final String xx(Intent data) {
        return data.getStringExtra(XX);
    }
    public static final com.codingtu.cooltu.bean.Forms forms(Intent data) {
        return com.codingtu.cooltu.lib4j.json.JsonTool.toBean(com.codingtu.cooltu.bean.Forms.class, data.getStringExtra(FORMS));
    }
    public static final String name(Intent data) {
        return data.getStringExtra(NAME);
    }
    public static final int age(Intent data) {
        return data.getIntExtra(AGE, -1);
    }
    public static final boolean isTest(Intent data) {
        return data.getBooleanExtra(IS_TEST, false);
    }


}

