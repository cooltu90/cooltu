package cooltu.processor.lib.tools;

import java.io.File;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import cooltu.constant.Constant;
import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.file.FileTool;
import cooltu.lib4j.tools.StringTool;
import cooltu.processor.worker.model.base.BaseModel;

public class NameTools {

    /**************************************************
     *
     * 字母大小写的操作
     *
     **************************************************/
    public static boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static boolean isLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isLowerLine(char c) {
        return c == '_';
    }

    public static char toUpper(char c) {
        return (char) (c - 32);
    }

    public static char toLower(char c) {
        return (char) (c + 32);
    }

    /***************************************
     *
     * 转换成静态变量的命名方式
     * 比如 myName 转换后为 MY_NAME
     *
     ***************************************/
    public static String toLayoutType(String str) {
        return toStaticType(str).toLowerCase();
    }

    /***************************************
     *
     * 转换成静态变量的命名方式
     * 比如 myName 转换后为 MY_NAME
     *
     ***************************************/
    public static String toStaticType(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (isLower(c)) {
                sb.append(toUpper(c));
            } else if (isUpper(c) && i != 0) {
                sb.append("_" + c);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**************************************************
     *
     * 转换成类的命名方式
     *
     **************************************************/
    public static String toClassType(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && isLower(c)) {
                c = toUpper(c);
            } else if (isLowerLine(c)) {
                if (i == str.length() - 1) {
                    break;
                } else {
                    i++;
                    c = str.charAt(i);
                    if (isLower(c)) {
                        c = toUpper(c);
                    }
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**************************************************
     *
     * 转换成函数的命名方式
     *
     **************************************************/
    public static String toMethodType(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && isUpper(c)) {
                c = toLower(c);
            } else if (isLowerLine(c)) {
                if (i == str.length() - 1) {
                    break;
                } else {
                    i++;
                    c = str.charAt(i);
                    if (isLower(c)) {
                        c = toUpper(c);
                    }
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**********************************************
     *
     * 包名转路径，以\开始
     *
     **********************************************/
    public static String pkgToPath(String pkg) {
        if (pkg == null) return null;
        int length = pkg.length();
        if (length <= 0) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.SEPARATOR);
        for (int i = 0; i < length; i++) {
            char c = pkg.charAt(i);
            if (c == '.') {
                sb.append(Constant.SEPARATOR);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**********************************************
     *
     * 路径转包名
     *
     **********************************************/
    public static String pathToPkg(String path) {
        if (path == null) return null;
        int len = path.length();
        if (len <= 0) return null;
        char separatorChar = File.separatorChar;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = path.charAt(i);
            if (c == separatorChar || c == '/') {
                c = '.';
            }
            if (i == 0 || i == len - 1) {
                if (c == '.') {
                    continue;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**********************************************
     *
     * 获取java类的类名信息
     *
     **********************************************/
    //通过文件路径和java路径获取
    public static JavaInfo getJavaInfo(String filePath, String javaPath) {
        JavaInfo info = new JavaInfo();
        int lastIndexOf = filePath.lastIndexOf(File.separator) + 1;
        info.name = filePath.substring(lastIndexOf, filePath.length() - ".java".length());
        String subPath = StringTool.getSub(filePath, null, javaPath, info.name);
        info.pkg = pathToPkg(subPath);
        if (StringTool.isNotBlank(info.pkg)) {
            info.fullName = info.pkg + "." + info.name;
        } else {
            info.fullName = info.name;
        }
        return info;
    }

    //通过包名和类名获取
    public static JavaInfo getJavaInfoByName(String packages, String typeName) {
        JavaInfo info = new JavaInfo();
        info.name = typeName;
        info.fullName = packages + "." + info.name;
        info.pkg = packages;
        String javaPath = getJavaDir();
        String pkgPath = pkgToPath(packages);
        info.path = javaPath + pkgPath + Constant.SEPARATOR + info.name + Constant.FILE_TYPE_JAVA;
        return info;
    }

    //根据全类名获取
    public static JavaInfo getJavaInfoByName(String fullName) {
        int lastIndexOf = fullName.lastIndexOf(".");
        return getJavaInfoByName(fullName.substring(0, lastIndexOf), fullName.substring(lastIndexOf + 1));
    }

    //根据包名+基础名+后缀获取,基础名（test，user_name,UserName等类型，除去后缀的名字）
    public static JavaInfo getJavaInfoByName(String packages, String allBaseName, String suffix) {
        return getJavaInfoByName(packages, NameTools.toClassType(allBaseName) + suffix);
    }

    public static String getJavaSimpleName(String fullName) {
        int lastIndexOf = fullName.lastIndexOf(".");
        return fullName.substring(lastIndexOf + 1);
    }

    /**********************************************
     *
     * 是否为activity
     *
     **********************************************/
    public static boolean isActivity(String typeName) {
        return typeName.endsWith(Constant.SUFFIX_ACTIVITY);
    }

    public static boolean isActivity(ExecutableElement element) {
        return isActivity(simpleName(element.getEnclosingElement()));
    }

    public static boolean isActivity(TypeElement element) {
        return isActivity(simpleName(element));
    }

    public static boolean isActivity(Element element) {
        if (element instanceof TypeElement) {
            return isActivity((TypeElement) element);
        }
        if (element instanceof ExecutableElement) {
            return isActivity((ExecutableElement) element);
        }
        return false;
    }

    public static boolean isFragment(String typeName) {
        return typeName.endsWith(Constant.SUFFIX_FRAGMENT);
    }

    public static boolean isFragment(ExecutableElement element) {
        return isFragment(simpleName(element.getEnclosingElement()));
    }

    public static boolean isFragment(TypeElement element) {
        return isFragment(simpleName(element));
    }

    public static boolean isFragment(Element element) {
        if (element instanceof TypeElement) {
            return isFragment((TypeElement) element);
        }
        if (element instanceof ExecutableElement) {
            return isFragment((ExecutableElement) element);
        }
        return false;
    }

    /**************************************************
     *
     * 获取去掉后缀的类名
     *
     **************************************************/
    public static String getTypeNameCutSuffix(String typeName, String suffix) {
        return typeName.substring(0, typeName.length() - suffix.length());
    }

    /**********************************************
     *
     * 获取各种dir
     *
     **********************************************/


    /**************************************************
     *
     * 获取Model的类基础名
     *
     **************************************************/
    public static String getModelTypeBaseName(Class modelClass) {
        return getTypeNameCutSuffix(modelClass.getSimpleName(), Constant.SUFFIX_MODEL);
    }

    public static JavaInfo adapterToVH(JavaInfo adapterInfo) {
        return getVHInfo(getAdapterTypeBaseName(adapterInfo.name));
    }

    //获取 VH 的 JavaInfo
    public static JavaInfo getVHInfo(String allBaseName) {
        return getJavaInfoByName(Constant.PKG_VH, allBaseName, Constant.SUFFIX_VH);
    }


    public static String getModelPath(BaseModel baseModel) {
        String canonicalName = baseModel.getClass().getCanonicalName();
        String s = pkgToPath(canonicalName);
        return FileTool.getProjectDir()
                + Constant.SEPARATOR
                + Constant.MODULE_NAME_CORE_PROCESSOR
                + Constant.PATH_SRC_MAIN_JAVA
                + s
                + Constant.FILE_TYPE_JAVA;
    }

    public static String getModelPath() {
        String s = pkgToPath(Constant.PKG_MODEL);
        return FileTool.getProjectDir()
                + Constant.SEPARATOR
                + Constant.MODULE_NAME_CORE_PROCESSOR
                + Constant.PATH_SRC_MAIN_JAVA
                + s;
    }

    public static String getModelInterPath(String typeName) {
        String s = pkgToPath(Constant.PKG_MODEL_INTERFACE);
        return FileTool.getProjectDir()
                + Constant.SEPARATOR
                + Constant.MODULE_NAME_CORE_PROCESSOR_MODEL_INTERFACE
                + Constant.PATH_SRC_MAIN_JAVA
                + s
                + Constant.SEPARATOR
                + typeName
                + Constant.FILE_TYPE_JAVA;
    }

    /**************************************************
     *
     * 分割线，上面的是不变的
     *
     **************************************************/

    /**************************************************
     *
     * 获取具体的JavaInfo
     *
     **************************************************/
    //获取 Activity 的 JavaInfo
    public static JavaInfo getActInfo(String pkg, String allBaseName) {
        return getJavaInfoByName(pkg, allBaseName, Constant.SUFFIX_ACTIVITY);
    }

    //获取 Fragment 的 JavaInfo
    public static JavaInfo getFragmentInfo(String pkg, String allBaseName) {
        return getJavaInfoByName(pkg, allBaseName, Constant.SUFFIX_FRAGMENT);
    }

    //获取 ActBase 的 JavaInfo
    public static JavaInfo getActBaseInfo(String allBaseName) {
        return getJavaInfoByName(Constant.PKG_ACT_BASE, allBaseName, Constant.SUFFIX_ACTIVITY_BASE);
    }

    public static JavaInfo getActBaseInfoByActFullName(String fullName) {
        return getActBaseInfo(NameTools.getActivityTypeBaseName(fullName));
    }

    //获取 FragmentBase 的 JavaInfo
    public static JavaInfo getFragmentBaseInfo(String allBaseName) {
        return getJavaInfoByName(Constant.PKG_FRAGMENT_BASE, allBaseName, Constant.SUFFIX_FRAGMENT_BASE);
    }

    public static JavaInfo getFragmentBaseInfoByFragmentFullName(String fullName) {
        return getFragmentBaseInfo(NameTools.getFragmentTypeBaseName(fullName));
    }

    //获取 ApiService 的 JavaInfo
    public static JavaInfo getApiServiceInfo(String allBaseName) {
        return getJavaInfoByName(Constant.PKG_API_SERVICE, allBaseName, Constant.SUFFIX_API_SERVICE);
    }

    //获取 SendParams 的 JavaInfo
    public static JavaInfo getSendParamsInfo(String allBaseName) {
        return getJavaInfoByName(Constant.PKG_SEND_PARAMS, allBaseName, Constant.SUFFIX_SEND_PARAMS);
    }

    //获取 NetBack 的 JavaInfo
    public static JavaInfo getNetBackInfo(String allBaseName) {
        return getJavaInfoByName(Constant.PKG_NET_BACK, allBaseName, Constant.SUFFIX_NET_BACK);
    }

    //获取 Adapter 的 JavaInfo
    public static JavaInfo getAdapterInfo(String pkg, String allBaseName) {
        return getJavaInfoByName(pkg, allBaseName, Constant.SUFFIX_ADAPTER);
    }

    //获取 core.tools 包下类的 JavaInfo
    public static JavaInfo getCoreToolsInfo(String typeName) {
        return getJavaInfoByName(Constant.PKG_CORE_TOOLS, typeName);
    }

    /**********************************************
     *
     * 获取各种dir
     *
     **********************************************/
    //获取当前模块中的java路径
    public static String getJavaDir() {
        return getJavaDir(Constant.CURRENT_MODULE);
    }

    //通过模块名获取java路径
    public static String getJavaDir(String module) {
        return FileTool.getProjectDir() + Constant.SEPARATOR + module + Constant.PATH_SRC_MAIN_JAVA;
    }

    //获取当前模块中的布局文件路径
    public static String getLayoutDir() {
        return getLayoutDir(Constant.CURRENT_MODULE);
    }

    //通过模块名获取布局文件路径
    public static String getLayoutDir(String module) {
        return FileTool.getProjectDir() + Constant.SEPARATOR + module + Constant.PATH_SRC_MAIN_RES_LAYOUT;
    }

    //获取当前模块中的清单文件路径
    public static String getManifestPath() {
        return getManifestPath(Constant.CURRENT_MODULE);
    }

    //通过模块名获取清单文件路径
    public static String getManifestPath(String module) {
        return FileTool.getProjectDir() + Constant.SEPARATOR + module + Constant.PATH_SRC_MAIN + Constant.SEPARATOR + Constant.FILE_NAME_ANDROID_MANIFEST;
    }

    //获取当前模块中的布局文件路径
    public static String getLayoutPath(String layoutName) {
        return getLayoutPath(Constant.CURRENT_MODULE, layoutName);
    }

    //通过模块名获取布局文件路径
    public static String getLayoutPath(String module, String layoutName) {
        return NameTools.getLayoutDir(module) + Constant.SEPARATOR + layoutName + Constant.FILE_TYPE_XML;
    }

    //获取当前模块中的activity布局文件
    public static String getActivityLayoutPath(String layoutBaseName) {
        return getActivityLayoutPath(Constant.CURRENT_MODULE, layoutBaseName);
    }

    //通过模块名获取activity布局文件
    public static String getActivityLayoutPath(String module, String layoutBaseName) {
        return getLayoutPath(module, Constant.LAYOUT_PREFIX_ACTIVITY + "_" + layoutBaseName);
    }

    //获取当前模块中的item布局文件
    public static String getItemLayoutPath(String layoutBaseName) {
        return getItemLayoutPath(Constant.CURRENT_MODULE, layoutBaseName);
    }

    //通过模块名获取item布局文件
    public static String getItemLayoutPath(String module, String layoutBaseName) {
        return getLayoutPath(module, Constant.LAYOUT_PREFIX_ITEM + "_" + layoutBaseName);
    }

    //获取当前模块中的fragment布局文件
    public static String getFragmentLayoutPath(String layoutBaseName) {
        return getFragmentLayoutPath(Constant.CURRENT_MODULE, layoutBaseName);
    }

    //通过模块名获取afragment布局文件
    public static String getFragmentLayoutPath(String module, String layoutBaseName) {
        return getLayoutPath(module, Constant.LAYOUT_PREFIX_FRAGMENT + "_" + layoutBaseName);
    }

    /**********************************************
     *
     * 获取element的简单名字
     *
     **********************************************/
    public static String simpleName(Element element) {
        return element.getSimpleName().toString();
    }

    /**********************************************
     *
     * 获取Activity的基础的类名
     *
     **********************************************/
    public static String getActivityTypeBaseName(TypeElement element) {
        return getTypeNameCutSuffix(simpleName(element), Constant.SUFFIX_ACTIVITY);
    }

    public static String getActivityTypeBaseName(String fullName) {
        return getTypeNameCutSuffix(getJavaSimpleName(fullName), Constant.SUFFIX_ACTIVITY);
    }

    public static String getAdapterTypeBaseName(String typeName) {
        return getTypeNameCutSuffix(typeName, Constant.SUFFIX_ADAPTER);
    }

    /**********************************************
     *
     * 获取Fragment的基础的类名
     *
     **********************************************/
    public static String getFragmentTypeBaseName(TypeElement element) {
        String typeName = simpleName(element);
        return typeName.substring(0, typeName.length() - Constant.SUFFIX_FRAGMENT.length());
    }


    public static String getFragmentTypeBaseName(String fullName) {
        return getTypeNameCutSuffix(getJavaSimpleName(fullName), Constant.SUFFIX_FRAGMENT);
    }

    public static String getActivityLayoutName(String layoutBaseName) {
        return Constant.LAYOUT_PREFIX_ACTIVITY + "_" + layoutBaseName;
    }

    public static String getFragmentLayoutName(String layoutBaseName) {
        return Constant.LAYOUT_PREFIX_FRAGMENT + "_" + layoutBaseName;
    }

    public static String getItemLayoutName(String layoutBaseName) {
        return Constant.LAYOUT_PREFIX_ITEM + "_" + layoutBaseName;
    }

    public static String fullName(TypeElement element) {
        return element.asType().toString();
    }

}
