package com.codingtu.cooltu.processor.builder.builder;

import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.builder.BuilderType;
import com.codingtu.cooltu.processor.builder.builderbase.ActBaseBuilderBase;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;

@To(ActBaseBuilderBase.class)
public class ActBaseBuilder extends ActBaseBuilderBase {

    //com.codingtu.cooltu.WelcomeActivity
    private String actFullName;
    //com.codingtu.cooltu.R.layout.activity_welcome
    public IdTools.Id layout;

    @Override
    protected BuilderType getBuilderType() {
        return BuilderType.actBase;
    }

    public void setActFullName(String actFullName) {
        this.actFullName = actFullName;
        String actBaseFullName = NameTools.getActBaseFullNameByActFullName(this.actFullName);

    }


    @Override
    protected void deal() {
        //actName
        addTag(actName, NameTools.getJavaSimpleName(actFullName));
        //处理layout
        addTag(super.layout, layout.toString());
    }

}
