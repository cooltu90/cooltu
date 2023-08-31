package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.constant.Path;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.lib.log.Logs;

import java.io.File;

public class NameTools extends StringTool {

    /**********************************************
     *
     * 获取java类的类名信息
     *
     **********************************************/
    //通过文件路径和java路径获取
    public static JavaInfo getJavaInfo(String filePath, String javaPath) {
        JavaInfo info = new JavaInfo();
        int lastIndexOf = filePath.lastIndexOf(File.separator) + 1;
        info.name = filePath.substring(lastIndexOf, filePath.length() - FileType.dJAVA.length());
        String subPath = getSubStrictMode(filePath, null, javaPath, info.name);
        info.pkg = ConvertTool.pathToPkg(subPath);
        if (isNotBlank(info.pkg)) {
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
        String pkgPath = ConvertTool.pkgToPath(packages);
        info.path = javaPath + pkgPath + Constant.SEPARATOR + info.name + FileType.dJAVA;
        return info;
    }

    //根据全类名获取
    public static JavaInfo getJavaInfoByName(String fullName) {
        int lastIndexOf = fullName.lastIndexOf(".");
        return getJavaInfoByName(fullName.substring(0, lastIndexOf), fullName.substring(lastIndexOf + 1));
    }

    //根据包名+基础名+后缀获取,基础名（test，user_name,UserName等类型，除去后缀的名字）
    public static JavaInfo getJavaInfoByName(String packages, String allBaseName, String suffix) {
        return getJavaInfoByName(packages, ConvertTool.toClassType(allBaseName) + suffix);
    }

    public static String getJavaSimpleName(String fullName) {
        int lastIndexOf = fullName.lastIndexOf(".");
        return fullName.substring(lastIndexOf + 1);
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    public static String getProcessorJavaPath(Class aClass) {
        String canonicalName = aClass.getCanonicalName();
        String s = ConvertTool.pkgToPath(canonicalName);
        return getJavaDir(Module.CORE_PROCESSOR)
                + s
                + FileType.dJAVA;
    }

    /**********************************************
     *
     * 获取各种dir
     *
     **********************************************/
    //获取当前模块中的java路径
    public static String getJavaDir() {
        return getJavaDir(Module.CURRENT);
    }

    //通过模块名获取java路径
    public static String getJavaDir(String module) {
        return FileTool.getProjectDir() + Constant.SEPARATOR + module + Path.SRC_MAIN_JAVA;
    }

}
