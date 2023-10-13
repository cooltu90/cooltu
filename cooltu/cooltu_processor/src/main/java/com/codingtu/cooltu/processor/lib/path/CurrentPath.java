package com.codingtu.cooltu.processor.lib.path;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.constant.Path;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.lib.BuilderMap;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

/**************************************************
 *
 *   ┏━━━━━━━━━━━━━━┓
 *  ┃   java目录   ┃
 * ┗━━━━━━━━━━━━━━━┛
 * {@link #javaDir()}
 *
 *   ┏━━━━━━━━━━━━━┓
 *  ┃   pkg目录   ┃
 * ┗━━━━━━━━━━━━━━┛
 * {@link #pkgDir(String)}
 *
 *   ┏━━━━━━━━━━━━━━━━━┓
 *  ┃   layout目录   ┃
 * ┗━━━━━━━━━━━━━━━━━┛
 * {@link #layoutDir()}
 *
 *   ┏━━━━━━━━━━━━━━━┓
 *  ┃   .java路径   ┃
 * ┗━━━━━━━━━━━━━━━━┛
 * {@link #javaPath(String, String)}
 *
 *   ┏━━━━━━━━━━━━━━━┓
 *  ┃   JavaInfo   ┃
 * ┗━━━━━━━━━━━━━━━━┛
 * 包名、类名：{@link #javaInfo(String, String)}
 * 全类名：{@link #javaInfo(String)}
 *
 *   ┏━━━━━━━━━━━━━━━━━━━━━━━┓
 *  ┃   ActBaseJavaInfo   ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #actBaseJavaInfo(String)}
 *
 *   ┏━━━━━━━━━━━━━━━━━━┓
 *  ┃   layout的路径   ┃
 * ┗━━━━━━━━━━━━━━━━━━━┛
 * {@link #layout(String)}
 *
 *
 *   ┏━━━━━━━━━━━━━━━━━━━━━━┓
 *  ┃   ActBaseBuilder   ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #actBaseBuilder(String)}
 *
 **************************************************/
public class CurrentPath {

    private static JavaInfo javaInfo;

    public static String javaDir() {
        return PathTools.javaDir(Module.CURRENT);
    }

    public static String pkgDir(String pkg) {
        return PathTools.pkgDir(Module.CURRENT, pkg);
    }

    public static String layoutDir() {
        return FileTool.getProjectDir()
                + Constant.SEPARATOR
                + Module.CURRENT
                + Path.SRC_MAIN_RES_LAYOUT;
    }

    public static String javaPath(String pkg, String name) {
        return PathTools.javaPath(Module.CURRENT, pkg, name);
    }

    public static JavaInfo javaInfo(String pkg, String typeName) {
        return PathTools.javaInfo(Module.CURRENT, pkg, typeName);
    }

    public static JavaInfo javaInfo(String fullName) {
        return PathTools.javaInfo(Module.CURRENT, fullName);
    }

    public static JavaInfo actBaseJavaInfo(String actFullName) {
        JavaInfo actJavaInfo = javaInfo(actFullName);
        String pkg = Pkg.ACT_BASE + actJavaInfo.pkg.substring(Pkg.ACT.length());
        return javaInfo(pkg, actJavaInfo.name + Suffix.ACT_BASE);
    }

    public static String layout(String layoutName) {
        return layoutDir()
                + Constant.SEPARATOR
                + layoutName
                + FileType.d_XML;
    }

    public static ActBaseBuilder actBaseBuilder(String actFullName) {
        JavaInfo javaInfo = actBaseJavaInfo(actFullName);
        return BuilderMap.find(BuilderType.actBase, javaInfo.fullName);
    }
}
