package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.file.copy.FileCopy;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.processor.annotation.create.CreateAct;
import com.codingtu.cooltu.processor.builder.impl.ActResBuilder;
import com.codingtu.cooltu.processor.builder.impl.CreateActBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import java.io.File;

import javax.lang.model.element.TypeElement;

public class CreateActDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        CreateAct createAct = te.getAnnotation(CreateAct.class);
        String name = createAct.name();
        String packages = createAct.packages();
        String baseClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return createAct.baseClass();
            }
        });
        if (ClassTool.isVoid(baseClass)) {
            baseClass = FullName.BASE_ACT;
        }

        IdTools.Id layoutTempId = IdTools.elementToId(te, CreateAct.class, createAct.layoutTemp());

        Logs.i("createAct");
        Logs.i("name:" + name);
        Logs.i("packages:" + packages);
        Logs.i("baseClass:" + baseClass);

        String layoutName = "activity_" + name;
        String layoutPath = CurrentPath.layout(layoutName);
        File layoutFile = new File(layoutPath);
        if (!layoutFile.exists()) {
            //创建layout
            String layout = CurrentPath.layout(layoutTempId.rName);
            FileCopy.src(layout).to(layoutFile);
        }

        JavaInfo actJavaInfo = CurrentPath.act(packages, name);
        new ActResBuilder(CurrentPath.actRes(packages, name), actJavaInfo);

        new CreateActBuilder(actJavaInfo,layoutName);

    }
}
