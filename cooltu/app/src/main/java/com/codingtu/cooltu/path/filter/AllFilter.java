package com.codingtu.cooltu.path.filter;

import com.codingtu.cooltu.lib4a.path.BasePathFilter;
import com.codingtu.cooltu.processor.annotation.path.PathFilter;
import com.codingtu.cooltu.processor.annotation.path.PathFilterField;

import java.io.File;

@PathFilter
public class AllFilter extends BasePathFilter {

    @PathFilterField
    public String name;

    @Override
    public boolean check(File file) {
        return true;
    }
}
