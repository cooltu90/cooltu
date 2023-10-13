package com.codingtu.cooltu.processor.bean;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.processor.annotation.res.ClickView;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

import java.util.ArrayList;
import java.util.List;

public class ActBaseInfo {
    public IdTools.Id layout;
    public String baseClass;
    public List<LayoutTools.ViewInfo> viewInfos;
    public List<ClickViewInfo> clickViews = new ArrayList<>();


    public boolean hasBaseClass() {
        return !FullName.BASE_ACT.equals(baseClass);
    }

}
