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
 * 当前模块：{@link #getCurrentJavaDir()}
 * processor模块：{@link #getProcessorJavaDir()}
 *
 * ————通过java目录和package获取目录
 * 传入目录：{@link #getPkgDir(String, String)}
 * 当前目录：{@link #getCurrentPkgDir(String)}
 * processor目录：{@link #getProcessorPkgDir(String)}
 *
 * ————获取.java文件的路径
 * 传入目录：{@link #getJavaPath(String, String, String)}
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
 * 传入模块：
 * {@link #getJavaInfo(String, JavaInfo)}
 * {@link #getJavaInfo(String, String, String)}
 * {@link #getJavaInfo(String, String)}
 * 当前模块：
 * {@link #getCurrentJavaInfo(String, String)}
 * {@link #getCurrentJavaInfo(String)}
 * processor模块：
 * {@link #getProcessorJavaInfo(String, String)}
 * {@link #getProcessorJavaInfo(String)}
 *
 *
 **************************************************/
public class PathTools extends StringTool {

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

    public static String getCurrentJavaDir() {
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

    public static String getPkgDir(String module, String pkg) {
        return getJavaDir(module) + ConvertTool.pkgToPath(pkg);
    }

    public static String getCurrentPkgDir(String pkg) {
        return getPkgDir(Module.CURRENT, pkg);
    }

    public static String getProcessorPkgDir(String pkg) {
        return getPkgDir(Module.CORE_PROCESSOR, pkg);
    }

    /**************************************************
     *
     * 获取.java文件的路径
     *
     **************************************************/
    public static String getJavaPath(String module, String pkg, String name) {
        return getPkgDir(module, pkg)
                + Constant.SEPARATOR
                + name
                + FileType.d_JAVA;
    }

    public static String getCurrentJavaPath(String pkg, String name) {
        return getJavaPath(Module.CURRENT, pkg, name);
    }

    public static String getProcessorJavaPath(String pkg, String name) {
        return getJavaPath(Module.CORE_PROCESSOR, pkg, name);
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
    private static JavaInfo getJavaInfo(String module, JavaInfo info) {
        info.path = getJavaPath(module, info.pkg, info.name);
        return info;
    }

    public static JavaInfo getJavaInfo(String module, String pkg, String typeName) {
        return getJavaInfo(module, new JavaInfo(pkg, typeName));
    }

    public static JavaInfo getJavaInfo(String module, String fullName) {
        return getJavaInfo(module, new JavaInfo(fullName));
    }

    public static JavaInfo getCurrentJavaInfo(String pkg, String typeName) {
        return getJavaInfo(Module.CURRENT, pkg, typeName);
    }

    public static JavaInfo getCurrentJavaInfo(String fullName) {
        return getJavaInfo(Module.CURRENT, fullName);
    }

    public static JavaInfo getProcessorJavaInfo(String fullName) {
        return getJavaInfo(Module.CORE_PROCESSOR, fullName);
    }

    public static JavaInfo getProcessorJavaInfo(Object obj) {
        return getJavaInfo(Module.CORE_PROCESSOR, obj.getClass().getCanonicalName());
    }

    public static JavaInfo getProcessorJavaInfo(String pkg, String typeName) {
        return getJavaInfo(Module.CORE_PROCESSOR, pkg, typeName);
    }

}
