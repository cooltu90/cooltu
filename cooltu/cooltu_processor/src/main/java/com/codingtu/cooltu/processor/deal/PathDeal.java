package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.FileContentType;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.Path;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.data.map.MapValueMap;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.path.DirPath;
import com.codingtu.cooltu.processor.annotation.path.FilePath;
import com.codingtu.cooltu.processor.annotation.path.PathList;
import com.codingtu.cooltu.processor.annotation.path.PathObtain;
import com.codingtu.cooltu.processor.annotation.path.Paths;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.bean.DirPathInfo;
import com.codingtu.cooltu.processor.bean.FilePathInfo;
import com.codingtu.cooltu.processor.builder.impl.PathBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

@To(PathBuilder.class)
public class PathDeal extends TypeBaseDeal {
    private HashMap<String, String> dirMap = new HashMap<>();
    private HashMap<String, PathBuilder> pathMap = new HashMap<>();
    private MapValueMap<String, String, ExecutableElement> pathListMap = new MapValueMap<>();

    @Override
    protected void dealTypeElement(TypeElement te) {
        Paths paths = te.getAnnotation(Paths.class);
        String baseName = paths.name();
        dirMap.put("root", baseName);

        PathBuilder pathBuilder = new PathBuilder(paths.path(), getJavaInfo(baseName));
        pathMap.put("root", pathBuilder);

        Ts.ls(te.getEnclosedElements(), (position, e) -> {
            if (e instanceof VariableElement) {
                VariableElement ve = (VariableElement) e;

                DirPath dir = ve.getAnnotation(DirPath.class);
                if (dir != null) {
                    dealDir(ve, dir);
                }

                FilePath file = ve.getAnnotation(FilePath.class);
                if (file != null) {
                    dealFile(ve, file);
                }

                PathObtain pathObtain = ve.getAnnotation(PathObtain.class);
                if (pathObtain != null)
                    pathBuilder.addObtain(pathObtain);

            }

            if (e instanceof ExecutableElement) {
                ExecutableElement ee = (ExecutableElement) e;
                PathList pathList = ee.getAnnotation(PathList.class);
                if (pathList != null) {
                    dealPathList(te, ee, pathList);
                }
            }

            return false;
        });
    }

    private void dealPathList(TypeElement te, ExecutableElement ee, PathList pathList) {
        String configName = ElementTools.simpleName(te);
        Map<String, ExecutableElement> map = pathListMap.get(configName);
        map.put(pathList.value(), ee);
    }


    private void dealDir(VariableElement ve, DirPath dir) {
        KV<String, String> kv = ElementTools.getFieldKv(ve);
        String baseName = dirMap.get(dir.parent());
        String fieldName = dir.fieldName();
        if (StringTool.isBlank(fieldName)) {
            fieldName = kv.v;
        }

        baseName = baseName + ConvertTool.toClassType(fieldName);
        dirMap.put(kv.v, baseName);

        JavaInfo basePathJavaInfo = getJavaInfo(baseName);
        PathBuilder pathModel = new PathBuilder(null, basePathJavaInfo);

        pathMap.put(kv.v, pathModel);

        PathBuilder parentModel = pathMap.get(dir.parent());

        DirPathInfo dirInfo = new DirPathInfo();
        dirInfo.javaName = basePathJavaInfo.name;
        dirInfo.fieldName = fieldName;
        dirInfo.dirName = StringTool.isBlank(dir.dirName()) ? kv.v : dir.dirName();
        dirInfo.filter = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return dir.filter();
            }
        });
        dirInfo.isFilter = !ClassTool.isVoid(dirInfo.filter);
        dirInfo.isList = dir.list();

        parentModel.addDir(dirInfo);

    }

    private void dealFile(VariableElement ve, FilePath file) {
        KV<String, String> kv = ElementTools.getFieldKv(ve);

        PathBuilder parentModel = pathMap.get(file.parent());
        FilePathInfo info = new FilePathInfo();
        info.file = file;

        info.fileName = info.fileFullName = StringTool.isBlank(file.fileName()) ? kv.v : file.fileName();
        info.fieldName = info.fieldFullName = StringTool.isBlank(file.fieldName()) ? kv.v : file.fieldName();
        info.fileContentType = file.fileContentType();
        if (!file.fileType().equals(FileType.NONE)) {
            if (info.fileContentType.equals(FileContentType.NONE)) {
                switch (file.fileType()) {
                    case FileType.TXT:
                    case FileType.JSON:
                        info.fileContentType = FileContentType.TXT;
                        break;
                    case FileType.JPG:
                    case FileType.PNG:
                    case FileType.PNC:
                        info.fileContentType = FileContentType.PIC;
                        break;
                }
            }
            info.fileType = "." + file.fileType();
            info.fieldType = "_" + file.fileType();
            info.fileFullName += info.fileType;
        } else {
            info.fieldType = "_";
        }
        info.fieldFullName += info.fieldType;

        info.beanClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return file.beanClass();
            }
        });
        info.beanType = file.beanType();
        info.isVoidBean = ClassTool.isVoid(info.beanClass);
        info.filter = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return file.filter();
            }
        });
        info.isFilter = !ClassTool.isVoid(info.filter);


        parentModel.addFile(info);
    }

    private JavaInfo getJavaInfo(String baseName) {
        return CurrentPath.javaInfo(Pkg.CORE_PATH, ConvertTool.toClassType(baseName) + Suffix.PATH);
    }
}
