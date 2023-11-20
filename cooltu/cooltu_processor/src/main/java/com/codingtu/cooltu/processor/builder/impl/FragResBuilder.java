package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.builder.base.FragResBuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;

import java.util.List;

public class FragResBuilder extends FragResBuilderBase {
    private final JavaInfo fragJavaInfo;

    public FragResBuilder(JavaInfo info, JavaInfo fragJavaInfo) {
        super(info);
        this.fragJavaInfo = fragJavaInfo;
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        Logs.i(lines);
    }

    @Override
    protected void dealLines() {

    }
}
/* model_temp_start
package [[pkg]];

import [[resForFullName]];
import [[fragFullName]];

@[[ResForFragmentName]]([[fragName]].class)
public class [[fragName]]Res {
}
model_temp_end */
