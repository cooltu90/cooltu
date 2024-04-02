package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.builder.base.CreateFormConfigBaseBuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;

import java.util.List;

public class CreateFormConfigBaseBuilder extends CreateFormConfigBaseBuilderBase {

    public CreateFormConfigBaseBuilder(JavaInfo bindConfigBaseJavaInfo) {
        super(bindConfigBaseJavaInfo);
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
    protected boolean isForce() {
        return false;
    }

    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        Logs.i(lines);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, javaInfo.pkg);
        addTag(baseClassName, javaInfo.name);
    }
}
/* model_temp_start
package [[pkg]];

public class [[baseClassName]] {
}
model_temp_end */