package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.path.DirPath;
import com.codingtu.cooltu.processor.annotation.path.FilePath;
import com.codingtu.cooltu.processor.annotation.path.Paths;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.bean.DirPathInfo;
import com.codingtu.cooltu.processor.builder.impl.PathBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;

import java.util.HashMap;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

@To(PathBuilder.class)
public class PathDeal extends TypeBaseDeal {
    private HashMap<String, String> dirMap = new HashMap<>();
    private HashMap<String, PathBuilder> pathMap = new HashMap<>();

    @Override
    protected void dealTypeElement(TypeElement te) {
        Paths paths = te.getAnnotation(Paths.class);
        String baseName = paths.name();
        dirMap.put("root", baseName);

        PathBuilder pathBuilder = new PathBuilder(ConvertTool.toClassType(baseName), paths.path());
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

            }
            return false;
        });
    }

    private void dealDir(VariableElement ve, DirPath dir) {
        KV<String, String> kv = ElementTools.getFiledKv(ve);
        String baseName = dirMap.get(dir.parent());
        String fieldName = dir.fieldName();
        if (StringTool.isBlank(fieldName)) {
            fieldName = kv.v;
        }

        baseName = baseName + ConvertTool.toClassType(fieldName);
        dirMap.put(kv.v, baseName);

        PathBuilder pathModel = new PathBuilder(null, baseName);
        pathMap.put(kv.v, pathModel);

        PathBuilder parentModel = pathMap.get(dir.parent());

//        DirPathInfo dirInfo = new DirPathInfo();
//        dirInfo.javaName = basePathJavaInfo.name;
//        dirInfo.fieldName = fieldName;
//        dirInfo.dirName = StringTool.isBlank(dir.dirName()) ? kv.v : dir.dirName();
//        dirInfo.filter = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
//            @Override
//            public Object get() {
//                return dir.filter();
//            }
//        });
//        dirInfo.isFilter = !ClassTool.isVoid(dirInfo.filter);
//
//        parentModel.addDir(dirInfo);

    }

    private void dealFile(VariableElement ve, FilePath file) {

    }
}
