package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.file.copy.FileCopy;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.create.CreateAct;
import com.codingtu.cooltu.processor.annotation.net.NetBack;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.bean.ActBaseInfo;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActResBuilder;
import com.codingtu.cooltu.processor.builder.impl.CreateActBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

import java.io.File;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public class CreateActDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        CreateAct createAct = te.getAnnotation(CreateAct.class);
        String name = createAct.name();
        String packages = createAct.packages();

        IdTools.Id layoutTempId = IdTools.elementToId(te, CreateAct.class, createAct.layoutTemp());

        String layoutName = "activity_" + name;
        String layoutPath = CurrentPath.layout(layoutName);
        File layoutFile = new File(layoutPath);
        if (!layoutFile.exists()) {
            //创建layout
            String layout = CurrentPath.layout(layoutTempId.rName);
            FileCopy.src(layout).to(layoutFile);
        }

        JavaInfo actJavaInfo = CurrentPath.act(packages, name);
        JavaInfo actResJavaInfo = CurrentPath.actRes(packages, name);
        JavaInfo actBaseJavaInfo = CurrentPath.actBaseJavaInfo(actJavaInfo.fullName);

        new ActResBuilder(actResJavaInfo, actJavaInfo);
        new CreateActBuilder(actJavaInfo, layoutName, actResJavaInfo, actBaseJavaInfo);

        if (!new File(actJavaInfo.path).exists()) {

            ActBaseInfo actBaseInfo = new ActBaseInfo();
            actBaseInfo.act = ElementTools.getType(te);
            actBaseInfo.baseClass = FullName.BASE_ACT;

            //String rPackage, String rType, String rName
            actBaseInfo.layout = new IdTools.Id(Pkg.R,"layout",layoutName);

            ActBaseBuilder actBaseBuilder = new ActBaseBuilder(actBaseJavaInfo);
            actBaseBuilder.addInfos(actBaseInfo);

        }

    }
}
