package core.tools;

import android.content.Intent;

public class ActBackIntent {
    public static Intent stepOne(com.codingtu.cooltu.bean.User user, java.lang.String xxx) {
        Intent intent = new Intent();
        intent.putExtra(core.tools.Pass.USER, com.codingtu.cooltu.lib4j.json.JsonTool.toJson(user));
        intent.putExtra(core.tools.Pass.XXX, xxx);
        return intent;
    }

}
