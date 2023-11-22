package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.file.copy.FileCopy;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.create.CreateFragment;
import com.codingtu.cooltu.processor.builder.impl.FragResBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import java.io.File;

import javax.lang.model.element.TypeElement;

public class CreateFragmentDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        CreateFragment createFragment = te.getAnnotation(CreateFragment.class);
        String packages = createFragment.packages();
        String name = createFragment.name();

        JavaInfo fragJavaInfo = CurrentPath.frag(packages, name);
        if (new File(fragJavaInfo.path).exists())
            return;


        //创建layout
        IdTools.Id layoutTempId = IdTools.elementToId(te, CreateFragment.class, createFragment.layoutTemp());
        String layoutName = "fragment_" + name;
        FileCopy
                .src(CurrentPath.layout(layoutTempId.rName))
                .to(new File(CurrentPath.layout(layoutName)));

        //创建ActRes
        JavaInfo fragmentResJavaInfo = CurrentPath.fragRes(packages, name);
        new FragResBuilder(fragmentResJavaInfo, fragJavaInfo);

        String baseClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return createFragment.baseClass();
            }
        });

        //创建ActBase
//        JavaInfo FragmentBaseJavaInfo = CurrentPath.fragBase(fragJavaInfo.fullName);
//        ActBaseInfo actBaseInfo = new ActBaseInfo();
//        actBaseInfo.act = ElementTools.getType(te);
//        actBaseInfo.baseClass = FullName.BASE_ACT;
//        actBaseInfo.layout = new IdTools.Id(Pkg.R, "layout", layoutName);
//        new ActBaseBuilder(FragmentBaseJavaInfo).addInfos(actBaseInfo);

    }
}
