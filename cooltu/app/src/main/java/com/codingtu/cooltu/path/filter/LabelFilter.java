package com.codingtu.cooltu.path.filter;

import com.codingtu.cooltu.lib4a.path.BasePathFilter;
import com.codingtu.cooltu.lib4j.ts.impl.basic.TListTs;
import com.codingtu.cooltu.processor.annotation.path.PathFilter;

import java.io.File;

@PathFilter
public class LabelFilter extends BasePathFilter {

    @Override
    public boolean check(File file) {
        return file.getName().startsWith("L-");
    }
}
