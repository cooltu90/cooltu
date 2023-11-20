package com.codingtu.cooltu.processor.builder.core;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
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
    public List<LayoutTools.ViewInfo> viewInfos;

    public List<KV<String, String>> inBases = new ArrayList<>();
    public HashMap<String, String> inBaseMap = new HashMap<>();
    public HashMap<String, String> fieldMap = new HashMap<>();


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
        return !FullName.BASE_ACT.equals(baseClass);
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

        if (layout != null) {
            uiBase.layoutIf(FullName.INFLATE_TOOL, layout.toString());
        }

//        Ts.ls(uiBaseBuilder.viewInfos, new BaseTs.EachTs<LayoutTools.ViewInfo>() {
//            @Override
//            public boolean each(int position, LayoutTools.ViewInfo viewInfo) {
//                addField(Constant.SIGN_PROTECTED, viewInfo.tag, viewInfo.fieldName);
//
//                String parent = "";
//                if (!viewInfo.fieldName.equals(viewInfo.id)) {
//                    parent = viewInfo.parent.fieldName + ".";
//                }
//                findView(position, viewInfo.fieldName, parent, Pkg.R, viewInfo.id);
//                return false;
//            }
//        });


    }

}
