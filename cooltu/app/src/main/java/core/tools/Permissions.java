package core.tools;

import android.app.Activity;

public class Permissions {
    public static final int CODE_CHECK_IN_WELCOME_ACTIVITY = 0;

    public static void checkInWelcomeActivity(Activity act) {
        com.codingtu.cooltu.lib4a.permission.PermissionTool.check(act, CODE_CHECK_IN_WELCOME_ACTIVITY
                , "android.permission.WRITE_EXTERNAL_STORAGE"
                , "android.permission.CAMERA"
        );
    }

}
