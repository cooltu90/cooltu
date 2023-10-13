package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.bean.ActBaseInfo;
import com.codingtu.cooltu.processor.builder.base.ActBaseBuilderBase;
import com.codingtu.cooltu.processor.deal.ActBaseDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

@To(ActBaseDeal.class)
public class ActBaseBuilder extends ActBaseBuilderBase {

    private ActBaseInfo info;
    private int idCount;

    public ActBaseBuilder(JavaInfo info) {
        super(info);
    }

    @Override
    protected BuilderType getBuilderType() {
        return BuilderType.actBase;
    }

    @Override
    public String obtainSymbol() {
        return javaInfo.fullName;
    }

    @Override
    protected boolean isBuild() {
        return true;
    }


    public void addInfos(ActBaseInfo actBaseInfo) {
        this.info = actBaseInfo;
    }

    @Override
    protected void dealLines() {
        addTag(pkg, javaInfo.pkg);
        addTag(name, javaInfo.name);
        addTag(baseClass, info.baseClass);

        onCreateIf(info.layout != null);
        if (info.layout != null) {
            onCreateIf(info.layout.toString());
        }

        dealViewInfo(info.viewInfo);

        viewCount(idCount);
    }

    private void dealViewInfo(LayoutTools.ViewInfo viewInfo) {
        if (viewInfo != null) {
            if (StringTool.isNotBlank(viewInfo.id)) {
                view(idCount, viewInfo.tag, viewInfo.id);
                idCount++;
            }
            Ts.ls(viewInfo.childs, new BaseTs.EachTs<LayoutTools.ViewInfo>() {
                @Override
                public boolean each(int position, LayoutTools.ViewInfo viewInfo) {
                    dealViewInfo(viewInfo);
                    return false;
                }
            });
        }
    }

}
/* model_temp_start
package [[pkg]];

import android.view.View;

public abstract class [[name]] extends [[baseClass]] implements View.OnClickListener {

                                                                                                    [<sub>][for][view]
    protected [type] [name];
                                                                                                    [<sub>][for][view]

                                                                                                    [<sub>][if][onCreate]
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView([layout]);
    }
                                                                                                    [<sub>][if][onCreate]

    @Override
    public void onClick(View v) {

    }
}

model_temp_end */