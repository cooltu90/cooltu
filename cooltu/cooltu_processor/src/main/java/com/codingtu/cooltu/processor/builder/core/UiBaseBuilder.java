package com.codingtu.cooltu.processor.builder.core;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.bean.ClickViewInfo;
import com.codingtu.cooltu.processor.builder.impl.ActBackIntentBuilder;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UiBaseBuilder {
    private final UiBaseInterface uiBase;
    public String uiFullName;
    public String baseClass;
    public IdTools.Id layout;
    public List<KV<String, String>> inBases = new ArrayList<>();
    public HashMap<String, String> inBaseMap = new HashMap<>();
    public HashMap<String, String> fieldMap = new HashMap<>();
    public List<ClickViewInfo> clickViews = new ArrayList<>();
    public List<LayoutTools.ViewInfo> viewInfos;
    public List<KV<String, String>> colorStrs = new ArrayList<>();

    public String finalBaseClass;


    public UiBaseBuilder(UiBaseInterface uiBase) {
        this.uiBase = uiBase;
    }

    private StringBuilder getStringBuilder(String key) {
        return uiBase.getMap().get(key);
    }

    private JavaInfo javaInfo() {
        return this.uiBase.getJavaInfo();
    }

    public boolean hasChild() {
        return CountTool.count(BaseTools.getActBaseBuilderWithChilds(uiFullName)) > 1;
    }

    public boolean hasBaseClass() {
        return !finalBaseClass.equals(baseClass);
    }

    public void addInBase(KV<String, String> fieldKv) {
        inBases.add(fieldKv);
    }

    public void removeInBase(KV<String, String> kv) {
        inBaseMap.put(kv.v, kv.v);
    }


    public void dealLines() {
        uiBase.addTag(getStringBuilder("pkg"), javaInfo().pkg);
        uiBase.addTag(getStringBuilder("name"), javaInfo().name);
        uiBase.addTag(getStringBuilder("baseClass"), baseClass);
        uiBase.addTag(getStringBuilder("netBackIFullName"), FullName.NET_BACK_I);
        uiBase.addTag(getStringBuilder("coreSendParamsFullName"), FullName.CORE_SEND_PARAMS);
        //设置在基础类中的字段
        setBaseField();
        //设置布局layout
        setLayout();
        //
        findView();
        //
        onClick();
        //
        colorStr();
    }

    private void setBaseField() {
        Ts.ls(inBases, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                addField(Constant.SIGN_PROTECTED, kv.k, kv.v);
                return false;
            }
        });
    }

    private void setLayout() {
        if (layout != null) {
            uiBase.layoutIf(FullName.INFLATE_TOOL, layout.toString());
        }
    }

    private void findView() {
        Ts.ls(viewInfos, new BaseTs.EachTs<LayoutTools.ViewInfo>() {
            @Override
            public boolean each(int position, LayoutTools.ViewInfo viewInfo) {
                addField(Constant.SIGN_PROTECTED, viewInfo.tag, viewInfo.fieldName);

                String parent = uiBase.getDefulatViewParent();
                if (!viewInfo.fieldName.equals(viewInfo.id)) {
                    parent = viewInfo.parent.fieldName + ".";
                }
                uiBase.findView(position, viewInfo.fieldName, parent, Pkg.R, viewInfo.id);
                return false;
            }
        });
    }

    private void onClick() {
        Ts.ls(clickViews, new BaseTs.EachTs<ClickViewInfo>() {
            @Override
            public boolean each(int clickViewInfoIndex, ClickViewInfo info) {
                uiBase.onClickMethods(clickViewInfoIndex, info.method, info.methodParams.getMethodParams());
                uiBase.onClickSwith(clickViewInfoIndex, info.method);

                List<KV<String, String>> kvs = info.methodParams.getKvs();
                int kvCount = CountTool.count(kvs);

                Ts.ls(kvs, new BaseTs.EachTs<KV<String, String>>() {
                    private int paramsIndex;

                    @Override
                    public boolean each(int kvIndex, KV<String, String> kv) {
                        String divider = (kvIndex != kvCount - 1) ? "," : "";
                        if (kvIndex == 0 && FullName.VIEW.equals(kv.k)) {
                            uiBase.onClickSwitchParamsIf(clickViewInfoIndex, divider);
                        } else {
                            uiBase.onClickSwitchParams(clickViewInfoIndex, paramsIndex, kv.k, Pkg.LIB4A, paramsIndex + "", divider);
                            paramsIndex++;
                        }
                        return false;
                    }
                });

                Ts.ls(info.ids, new BaseTs.EachTs<IdTools.Id>() {
                    @Override
                    public boolean each(int idIndex, IdTools.Id id) {
                        uiBase.onClickCase(clickViewInfoIndex, idIndex, id.toString());
                        if (info.inAct.get(clickViewInfoIndex)) {
                            BaseTools.getActBaseInfoWithParents(UiBaseBuilder.this, new BaseTs.EachTs<UiBaseBuilder>() {
                                @Override
                                public boolean each(int position, UiBaseBuilder uiBaseBuilder) {

                                    Ts.ts(uiBaseBuilder.viewInfos).convert(new BaseTs.Convert<LayoutTools.ViewInfo, LayoutTools.ViewInfo>() {
                                        @Override
                                        public LayoutTools.ViewInfo convert(int index, LayoutTools.ViewInfo viewInfo) {
                                            if (viewInfo.id.equals(id.rName)) {
                                                uiBase.setOnClick(uiBase.setOnClickCount(), viewInfo.fieldName);
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

        //onclick继承
        uiBase.isSuperOnClick(hasBaseClass());
    }


    private void colorStr() {
        //colorStr
        Ts.ls(colorStrs, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                addField(Constant.SIGN_PROTECTED, "int", kv.k);
                uiBase.colorStrInit(position, kv.k, kv.v);
                return false;
            }
        });
    }

    private boolean addField(String sign, String type, String name) {
        if (inBaseMap.get(name) == null && fieldMap.get(name) == null) {
            fieldMap.put(name, name);
            uiBase.field(uiBase.fieldCount(), sign, type, name);
            return true;
        }
        return false;
    }

    /**************************************************
     *
     *   ┏━━━━━━━━━━━━━━━━━━┓
     *  ┃   setBaseField  ┃
     * ┗━━━━━━━━━━━━━━━━━━━┛
     * {@link #setBaseField()}
     *   ┏━━━━━━━━━━━━┓
     *  ┃   设置布局  ┃
     * ┗━━━━━━━━━━━━━┛
     * {@link #setLayout()}
     *   ┏━━━━━━━━━━━━━━┓
     *  ┃   findView  ┃
     * ┗━━━━━━━━━━━━━━━┛
     * {@link #findView()}
     *
     *
     **************************************************/

}
