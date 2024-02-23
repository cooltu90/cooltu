package com.codingtu.cooltu.path.filter;

import com.codingtu.cooltu.lib4a.path.BasePathFilter;
import com.codingtu.cooltu.processor.annotation.path.PathFilter;

import java.io.File;

@PathFilter
public class NameFilter extends BasePathFilter {
    @Override
    public boolean check(File file) {
        return !file.getName().equals("classInfos");
    }
}
