package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.bean.ActBaseInfo;
import com.codingtu.cooltu.processor.bean.ClickViewInfo;
import com.codingtu.cooltu.processor.builder.base.ActBaseBuilderBase;
import com.codingtu.cooltu.processor.deal.ActBaseDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@To(ActBaseDeal.class)
public class ActBaseBuilder extends ActBaseBuilderBase {

    private ActBaseInfo info;
    private List<KV<String, String>> inBases = new ArrayList<>();
    private HashMap<String, String> inBaseMap = new HashMap<>();

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

    public void addInBase(KV<String, String> fieldKv) {
        inBases.add(fieldKv);
    }

    public void removeInBase(KV<String, String> kv) {
        inBaseMap.put(kv.v, kv.v);
    }

    public ActBaseInfo getActBaseInfo() {
        return this.info;
    }

    @Override
    protected void dealLines() {
        addTag(pkg, javaInfo.pkg);
        addTag(name, javaInfo.name);
        addTag(baseClass, info.baseClass);

        layoutIf(info.layout != null);
        if (info.layout != null) {
            layoutIf(info.layout.toString());
        }

        final int[] viewCount = {0};
        findViewCount(CountTool.count(info.viewInfos));
        Ts.ls(info.viewInfos, new BaseTs.EachTs<LayoutTools.ViewInfo>() {
            @Override
            public boolean each(int position, LayoutTools.ViewInfo viewInfo) {
                if (inBaseMap.get(viewInfo.fieldName) == null) {
                    view(viewCount[0]++, viewInfo.tag, viewInfo.fieldName);
                }

                String parent = "";
                if (!viewInfo.fieldName.equals(viewInfo.id)) {
                    parent = viewInfo.parent.fieldName + ".";
                }
                findView(position, viewInfo.fieldName, parent, Pkg.R, viewInfo.id);
                return false;
            }
        });

        viewCount(viewCount[0]);

        inBaseCount(CountTool.count(inBases));
        Ts.ls(inBases, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                inBase(position, kv.k, kv.v);
                return false;
            }
        });

        final int[] setOnClickCount = {0};
        Ts.ls(info.clickViews, new BaseTs.EachTs<ClickViewInfo>() {
            @Override
            public boolean each(int position, ClickViewInfo info) {
                Ts.ls(info.ids, new BaseTs.EachTs<IdTools.Id>() {
                    @Override
                    public boolean each(int position, IdTools.Id id) {
                        if (info.inAct.get(position)) {
                            BaseTools.getActBaseInfoWithParents(getActBaseInfo(), new BaseTs.EachTs<ActBaseInfo>() {
                                @Override
                                public boolean each(int position, ActBaseInfo actBaseInfo) {

                                    Ts.ts(actBaseInfo.viewInfos).convert(new BaseTs.Convert<LayoutTools.ViewInfo, LayoutTools.ViewInfo>() {
                                        @Override
                                        public LayoutTools.ViewInfo convert(int index, LayoutTools.ViewInfo viewInfo) {
                                            if (viewInfo.id.equals(id.rName)) {
                                                setOnClick(setOnClickCount[0], viewInfo.fieldName);
                                                setOnClickCount[0]++;
                                            }
                                            return null;
                                        }
                                    });
                                    return false;
                                }
                            });
                        }
                        return false;
                    }
                });
                return false;
            }
        });
        setOnClickCount(setOnClickCount[0]);
    }

}
/* model_temp_start
package [[pkg]];

import android.view.View;

public abstract class [[name]] extends [[baseClass]] implements View.OnClickListener {

                                                                                                    [<sub>][for][view]
    protected [type] [name];
                                                                                                    [<sub>][for][view]
                                                                                                    [<sub>][for][inBase]
    protected [type] [name];
                                                                                                    [<sub>][for][inBase]

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                                                                                                    [<sub>][if][layout]
        setContentView([layout]);
                                                                                                    [<sub>][if][layout]
                                                                                                    [<sub>][for][findView]
        [fieldName] = [parent]findViewById([rPkg].R.id.[id]);
                                                                                                    [<sub>][for][findView]
                                                                                                    [<sub>][for][setOnClick]
        [fieldName].setOnClickListener(this);
                                                                                                    [<sub>][for][setOnClick]
    }
    @Override
    public void onClick(View v) {

    }
}

model_temp_end */