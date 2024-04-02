package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.builder.base.CreateFormConfigBuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.util.List;

public class CreateFormConfigBuilder extends CreateFormConfigBuilderBase {
    private final JavaInfo bindConfigBaseJavaInfo;
    private final String beanClassFullName;

    public CreateFormConfigBuilder(JavaInfo bindConfigJavaInfo, JavaInfo bindConfigBaseJavaInfo, String beanClassFullName) {
        super(bindConfigJavaInfo);
        this.bindConfigBaseJavaInfo = bindConfigBaseJavaInfo;
        this.beanClassFullName = beanClassFullName;
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
        addTag(bindConfigClassName, javaInfo.name);
        addTag(baseFullName, bindConfigBaseJavaInfo.fullName);
        addTag(baseClassName, bindConfigBaseJavaInfo.name);

        addTag(bindFullName, FullName.FORM_CONFIG);
        addTag(beanFullName, beanClassFullName);

        addTag(bindName, FullName.FORM_CONFIG_SHORT_NAME);
        addTag(beanName, CurrentPath.javaInfo(beanClassFullName).name);

    }
}
/* model_temp_start
package [[pkg]];

import [[beanFullName]];
import [[bindFullName]];

import [[baseFullName]];

@[[bindName]]([[beanName]].class)
public class [[bindConfigClassName]] extends [[baseClassName]] {
}
model_temp_end */