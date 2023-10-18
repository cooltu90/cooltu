package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.bean.ActBaseInfo;
import com.codingtu.cooltu.processor.bean.ClickViewInfo;
import com.codingtu.cooltu.processor.builder.base.ActBaseBuilderBase;
import com.codingtu.cooltu.processor.deal.ActBaseDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
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

    @Override
    protected boolean isGetLines() {
        return true;
    }


    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        //Logs.i(lines);
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

        final int[] fieldCount = {0};

        findViewCount(CountTool.count(info.viewInfos));
        Ts.ls(info.viewInfos, new BaseTs.EachTs<LayoutTools.ViewInfo>() {
            @Override
            public boolean each(int position, LayoutTools.ViewInfo viewInfo) {
                if (inBaseMap.get(viewInfo.fieldName) == null) {
                    field(fieldCount[0]++, viewInfo.tag, viewInfo.fieldName);
                }

                String parent = "";
                if (!viewInfo.fieldName.equals(viewInfo.id)) {
                    parent = viewInfo.parent.fieldName + ".";
                }
                findView(position, viewInfo.fieldName, parent, Pkg.R, viewInfo.id);
                return false;
            }
        });

        Ts.ls(inBases, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                field(fieldCount[0]++, kv.k, kv.v);
                return false;
            }
        });

        final int[] setOnClickCount = {0};

        int clickViewsCount = CountTool.count(info.clickViews);

        onClickMethodsCount(clickViewsCount);

        onClickSwithCount(clickViewsCount);

        Ts.ls(info.clickViews, new BaseTs.EachTs<ClickViewInfo>() {
            @Override
            public boolean each(int clickViewInfoIndex, ClickViewInfo info) {
                onClickMethods(clickViewInfoIndex, info.method, info.methodParams.getMethodParams());
                onClickSwith(clickViewInfoIndex, info.method);
                onClickCaseCount(clickViewInfoIndex, CountTool.count(info.ids));

                List<KV<String, String>> kvs = info.methodParams.getKvs();
                int kvCount = CountTool.count(kvs);

                int[] paramsIndex = {0};
                onClickSwitchParamsIf(clickViewInfoIndex, false);
                Ts.ls(kvs, new BaseTs.EachTs<KV<String, String>>() {
                    @Override
                    public boolean each(int kvIndex, KV<String, String> kv) {
                        String divider = (kvIndex != kvCount - 1) ? "," : "";
                        if (kvIndex == 0 && FullName.VIEW.equals(kv.k)) {
                            onClickSwitchParamsIf(clickViewInfoIndex, true);
                            onClickSwitchParamsIf(clickViewInfoIndex, divider);
                        } else {
                            onClickSwitchParams(clickViewInfoIndex, paramsIndex[0], kv.k, Pkg.LIB4A, paramsIndex[0] + "", divider);
                            paramsIndex[0]++;
                        }
                        return false;
                    }
                });

                onClickSwitchParamsCount(clickViewInfoIndex, paramsIndex[0]);


                Ts.ls(info.ids, new BaseTs.EachTs<IdTools.Id>() {
                    @Override
                    public boolean each(int idIndex, IdTools.Id id) {
                        onClickCase(clickViewInfoIndex, idIndex, id.toString());
                        if (info.inAct.get(clickViewInfoIndex)) {
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


        //onclick继承
        superOnClickIf(info.hasBaseClass());

        //colorStr
        int count = CountTool.count(info.colorStrs);
        Ts.ls(info.colorStrs, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                if (inBaseMap.get(kv.k) == null) {
                    field(fieldCount[0]++, "int", kv.k);
                }
                colorStrInit(position, kv.k, kv.v);
                return false;
            }
        });
        colorStrInitCount(count);

        //colorRes
        int colorResCount = CountTool.count(info.colorReses);
        colorResInitCount(colorResCount);
        Ts.ls(info.colorReses, new BaseTs.EachTs<KV<String, IdTools.Id>>() {
            @Override
            public boolean each(int position, KV<String, IdTools.Id> kv) {
                if (inBaseMap.get(kv.k) == null) {
                    field(fieldCount[0]++, "int", kv.k);
                }
                colorResInit(position, kv.k, FullName.RESOURCE_TOOL, kv.v.toString());
                return false;
            }
        });

        //dp
        int dpCount = CountTool.count(info.dps);
        dpInitCount(dpCount);
        Ts.ls(info.dps, new BaseTs.EachTs<KV<String, Float>>() {
            @Override
            public boolean each(int position, KV<String, Float> kv) {
                if (inBaseMap.get(kv.k) == null) {
                    field(fieldCount[0]++, "int", kv.k);
                }
                dpInit(position, kv.k, FullName.MOBILE_TOOL, kv.v + "f");
                return false;
            }
        });

        //colorRes
        int dimenCount = CountTool.count(info.dimens);
        dimenInitCount(dimenCount);
        Ts.ls(info.dimens, new BaseTs.EachTs<KV<String, IdTools.Id>>() {
            @Override
            public boolean each(int position, KV<String, IdTools.Id> kv) {
                if (inBaseMap.get(kv.k) == null) {
                    field(fieldCount[0]++, "int", kv.k);
                }
                dimenInit(position, kv.k, FullName.RESOURCE_TOOL, kv.v.toString());
                return false;
            }
        });

        //startField
        int startCount = CountTool.count(info.starts);
        startInitCount(startCount);
        Ts.ls(info.starts, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                if (inBaseMap.get(kv.v) == null) {
                    field(fieldCount[0]++, kv.k, kv.v);
                }
                startInit(position, kv.v, FullName.PASS);
                return false;
            }
        });

        fieldCount(fieldCount[0]);
    }

}
/* model_temp_start
package [[pkg]];

import android.view.View;

public abstract class [[name]] extends [[baseClass]] implements View.OnClickListener {
                                                                                                    [<sub>][for][field]
    protected [type] [name];
                                                                                                    [<sub>][for][field]

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
                                                                                                    [<sub>][for][colorStrInit]
        [name] = android.graphics.Color.parseColor("[color]");
                                                                                                    [<sub>][for][colorStrInit]
                                                                                                    [<sub>][for][colorResInit]
        [name] = [resourceToolFullName].getColor([id]);
                                                                                                    [<sub>][for][colorResInit]
                                                                                                    [<sub>][for][dpInit]
        [name] = [mobileToolFullName].dpToPx([value]);
                                                                                                    [<sub>][for][dpInit]
                                                                                                    [<sub>][for][dimenInit]
        [name] = [resourceToolFullName].getDimen([id]);
                                                                                                    [<sub>][for][dimenInit]
                                                                                                    [<sub>][for][startInit]
        [name] = [passFullName].[name](getIntent());
                                                                                                    [<sub>][for][startInit]

    }
    @Override
    public void onClick(View v) {
                                                                                                    [<sub>][if][superOnClick]
        super.onClick(v);
                                                                                                    [<sub>][if][superOnClick]
        switch (v.getId()) {
                                                                                                    [<sub>][for][onClickSwith]
                                                                                                    [<sub>][for][onClickCase]
            case [id]:
                                                                                                    [<sub>][for][onClickCase]
                [methodName](
                                                                                                    [<sub>][if][onClickSwitchParams]
                        v[divider]
                                                                                                    [<sub>][if][onClickSwitchParams]
                                                                                                    [<sub>][for][onClickSwitchParams]
                        ([type]) v.getTag([pkg].R.id.tag_[index])[divider]
                                                                                                    [<sub>][for][onClickSwitchParams]
                );
                break;
                                                                                                    [<sub>][for][onClickSwith]
        }
    }

                                                                                                    [<sub>][for][onClickMethods]
    protected void [methodName]([params]) {}
                                                                                                    [<sub>][for][onClickMethods]
}

model_temp_end */