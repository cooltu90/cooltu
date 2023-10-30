package core.tools;

import android.content.Intent;

public class ActBackIntent {
    public static Intent welcome(com.codingtu.cooltu.bean.User user, java.util.List<com.codingtu.cooltu.bean.User> users, java.lang.String name) {
        Intent intent = new Intent();
        intent.putExtra(core.tools.Pass.USER, com.codingtu.cooltu.lib4j.json.JsonTool.toJson(user));
        intent.putExtra(core.tools.Pass.USERS, com.codingtu.cooltu.lib4j.json.JsonTool.toJson(users));
        intent.putExtra(core.tools.Pass.NAME, name);
        return intent;
    }

}
