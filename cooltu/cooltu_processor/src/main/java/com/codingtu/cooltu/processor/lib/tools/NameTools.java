package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.constant.Path;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.file.FileTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;

/**************************************************
 *
 * ————获取CoreProcessor的java目录
 * {@link #getCoreProcessorJavaDir()}
 *
 * ————Builder的Impl目录
 * {@link #getBuilderImplDir()}
 *
 * ————BuilderBase的路径获取
 * {@link #getBuilderBase(String)}
 *
 *
 **************************************************/
public class NameTools extends StringTool {


    public static String getCoreProcessorJavaDir() {
        return FileTool.getProjectDir()
                + Constant.SEPARATOR
                + Module.CORE_PROCESSOR
                + Path.SRC_MAIN_JAVA;
    }


    public static String getBuilderImplDir() {
        return getCoreProcessorJavaDir()
                + ConvertTool.pkgToPath(Pkg.PROCESSOR_BUILDER_IMPL);
    }

    public static String getBuilderBase(String builderName) {
        return getCoreProcessorJavaDir()
                + ConvertTool.pkgToPath(Pkg.PROCESSOR_BUILDER_BASE)
                + Constant.SEPARATOR
                + builderName
                + Suffix.PROCESS_BUILDER_BASE
                + FileType.d_JAVA;
    }


}
