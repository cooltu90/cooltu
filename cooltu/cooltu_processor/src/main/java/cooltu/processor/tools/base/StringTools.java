package cooltu.processor.tools.base;

import java.io.File;

import cooltu.lib4j.tools.StringTool;

public class StringTools extends StringTool {

    /**************************************************
     *
     * 包名转路径，以\开始
     *
     **************************************************/
    public static String pkgToPath(String pkg) {
        if (pkg == null) return null;
        int length = pkg.length();
        if (length <= 0) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(File.separator);
        for (int i = 0; i < length; i++) {
            char c = pkg.charAt(i);
            if (c == '.') {
                sb.append(File.separator);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String cutSuffix(String name, String suffix) {
        return name.substring(0, name.length() - suffix.length());
    }

    public static String cutSuffix(Class aClass, String suffix) {
        return cutSuffix(aClass.getSimpleName(), suffix);
    }

}
