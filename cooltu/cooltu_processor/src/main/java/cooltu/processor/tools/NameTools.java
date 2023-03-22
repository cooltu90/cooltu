package cooltu.processor.tools;

import java.io.File;

import cooltu.constant.FileType;
import cooltu.constant.Module;
import cooltu.constant.Path;
import cooltu.constant.Suffix;
import cooltu.lib4j.file.FileTool;
import cooltu.processor.model.base.BasicModel;
import cooltu.processor.tools.base.StringTools;

public class NameTools {

    /**************************************************
     *
     * names
     *
     **************************************************/
    public static String getModelTypeBaseName(Class modelClass) {
        return StringTools.cutSuffix(modelClass, Suffix.MODEL);
    }

    /**************************************************
     *
     * path
     *
     **************************************************/
    public static String getModelPath(BasicModel basicModel) {
        return FileTool.getProjectDir()
                + File.separator
                + Module.COOLTU_PROCESSOR
                + Path.SRC_MAIN_JAVA
                + StringTools.pkgToPath(basicModel.getClass().getCanonicalName())
                + FileType.JAVA;
    }

}
