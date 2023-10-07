package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.constant.Path;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;

/**************************************************
 *
 * ————获取java目录
 * 传入模块：{@link #getJavaDir(String)}
 * 当前模块：{@link #getJavaDir()}
 * processor模块：{@link #getProcessorJavaDir()}
 *
 * ————通过java目录和package获取目录
 * 传入目录：{@link #getPkgDir(String, String)}
 * 当前目录：{@link #getPkgDir(String)}
 * processor目录：{@link #getProcessorPkgDir(String)}
 *
 * ————获取.java文件的路径
 * 传入目录：{@link #getJavaPath(String, String)}
 * 当前目录：{@link #getCurrentJavaPath(String, String)}
 * processor目录：{@link #getProcessorJavaPath(String, String)}
 *
 * ————Builder的Impl目录
 * {@link #getBuilderImplDir()}
 *
 * ————BuilderBase的路径获取
 * {@link #getBuilderBase(String)}
 *
 * ————获取JavaInfo
 * 通过包名和简单类名：{@link #getJavaInfoByName(String, String)}
 * 通过全类名：{@link #getJavaInfoByName(String)}
 *
 *
 *
 **************************************************/
public class NameTools extends StringTool {

    /**************************************************
     *
     * 获取java目录
     *
     **************************************************/
    public static String getJavaDir(String module) {
        return FileTool.getProjectDir()
                + Constant.SEPARATOR
                + module
                + Path.SRC_MAIN_JAVA;
    }

    public static String getJavaDir() {
        return getJavaDir(Module.CURRENT);
    }

    public static String getProcessorJavaDir() {
        return getJavaDir(Module.CORE_PROCESSOR);
    }

    /**************************************************
     *
     * 通过java目录和package获取路径
     *
     **************************************************/

    public static String getPkgDir(String dir, String pkg) {
        return dir + ConvertTool.pkgToPath(pkg);
    }

    public static String getPkgDir(String pkg) {
        return getPkgDir(getJavaDir(), pkg);
    }

    public static String getProcessorPkgDir(String pkg) {
        return getPkgDir(getProcessorJavaDir(), pkg);
    }

    /**************************************************
     *
     * 获取.java文件的路径
     *
     **************************************************/

    public static String getJavaPath(String dir, String name) {
        return dir
                + Constant.SEPARATOR
                + name
                + FileType.d_JAVA;
    }

    public static String getCurrentJavaPath(String pkg, String name) {
        return getJavaPath(getPkgDir(pkg), name);
    }


    public static String getProcessorJavaPath(String pkg, String name) {
        return getJavaPath(getProcessorPkgDir(pkg), name);
    }

    /**************************************************
     *
     * Builder的Impl目录
     *
     **************************************************/

    public static String getBuilderImplDir() {
        return getProcessorPkgDir(Pkg.PROCESSOR_BUILDER_IMPL);
    }

    /**************************************************
     *
     * BuilderBase的路径获取
     *
     **************************************************/
    public static String getBuilderBase(String builderName) {
        return getProcessorJavaPath(Pkg.PROCESSOR_BUILDER_BASE,
                builderName + Suffix.PROCESS_BUILDER_BASE);
    }

    /**************************************************
     *
     * 获取JavaInfo
     *
     **************************************************/
    public static JavaInfo getJavaInfoByName(String packages, String typeName) {
        JavaInfo info = new JavaInfo();
        info.name = typeName;
        info.fullName = packages + "." + info.name;
        info.pkg = packages;
        info.path = getCurrentJavaPath(packages, info.name);
        return info;
    }

    public static JavaInfo getJavaInfoByName(String fullName) {
        int lastIndexOf = fullName.lastIndexOf(".");
        return getJavaInfoByName(fullName.substring(0, lastIndexOf), fullName.substring(lastIndexOf + 1));
    }


}
