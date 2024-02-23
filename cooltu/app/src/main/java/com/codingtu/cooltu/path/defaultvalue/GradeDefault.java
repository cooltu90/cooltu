package com.codingtu.cooltu.path.defaultvalue;

import com.codingtu.cooltu.lib4a.path.BaseDefaultPath;

public class GradeDefault implements BaseDefaultPath {
    @Override
    public String path() {
        //根据当前登录用户获取所在年级
        return "一年级";
//        return CurrentUser.getGrade();
    }
}
