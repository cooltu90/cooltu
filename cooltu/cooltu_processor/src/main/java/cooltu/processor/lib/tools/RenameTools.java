package cooltu.processor.lib.tools;

import cooltu.constant.Constant;
import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.tools.StringTool;

public class RenameTools {

    public static interface RenameLs {
        public String ls(String oldFullName, String newFullName);
    }

    public static String lsActs(String defaultStr, RenameLs ls) {
        for (int i = 0; i < CountTool.count(Constant.RENAME_ACTS); i += 2) {
            String str = ls.ls(Constant.RENAME_ACTS.get(i), Constant.RENAME_ACTS.get(i + 1));
            if (StringTool.isNotBlank(str)) {
                return str;
            }
        }
        return defaultStr;
    }

    public static String actFullNameToStaticName(String actFullName) {
        ////ONE_ACTIVITY
        return NameTools.toStaticType(NameTools.getJavaInfoByName(actFullName).name);
    }

    public static String actFullNameToMethodName(String actFullName) {
        ////ONE_ACTIVITY
        return NameTools.toMethodType(NameTools.getJavaInfoByName(actFullName).name);
    }

    public static JavaInfo actFullNameToActBaseInfo(String fullName) {
        return NameTools.getActBaseInfo(NameTools.getActivityTypeBaseName(fullName));
    }

    public static String actFullNameToLayout(String fullName) {
        String baseName = NameTools.getActivityTypeBaseName(fullName);
        return NameTools.getActivityLayoutName(NameTools.toStaticType(baseName).toLowerCase());
    }


}
