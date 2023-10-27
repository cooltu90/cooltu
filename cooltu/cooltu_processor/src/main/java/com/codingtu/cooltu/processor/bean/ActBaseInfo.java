package com.codingtu.cooltu.processor.bean;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

import java.util.ArrayList;
import java.util.List;

public class ActBaseInfo {
    public IdTools.Id layout;
    public String baseClass;
    public List<LayoutTools.ViewInfo> viewInfos;
    public List<ClickViewInfo> clickViews = new ArrayList<>();
    public List<NetBackInfo> netBacks = new ArrayList<>();
    public String act;
    public List<KV<String, String>> colorStrs = new ArrayList<>();
    public List<KV<String, IdTools.Id>> colorReses = new ArrayList<>();
    public List<KV<String, Float>> dps = new ArrayList<>();
    public List<KV<String, IdTools.Id>> dimens = new ArrayList<>();
    public List<KV<String, String>> starts = new ArrayList<>();


    public boolean hasBaseClass() {
        return !FullName.BASE_ACT.equals(baseClass);
    }

}
