package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.builder.base.FragmentBaseBuilderBase;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.core.UiBaseInterface;
import com.codingtu.cooltu.processor.lib.log.Logs;

import java.util.List;

public class FragmentBaseBuilder extends FragmentBaseBuilderBase implements UiBaseInterface {

    /**************************************************
     *
     * 初始化
     *
     **************************************************/
    private final UiBaseBuilder uiBaseBuilder;

    public FragmentBaseBuilder(JavaInfo info) {
        super(info);
        uiBaseBuilder = new UiBaseBuilder(this);
    }

    @Override
    protected BuilderType getBuilderType() {
        return BuilderType.fragment;
    }

    @Override
    protected boolean isBuild() {
        return false;
    }

    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        Logs.i(lines);
    }

    @Override
    public String obtainSymbol() {
        return javaInfo.fullName;
    }

    public UiBaseBuilder getUiBaseBuilder() {
        return uiBaseBuilder;
    }

    @Override
    public StringBuilderValueMap<String> getMap() {
        return map;
    }

    @Override
    public JavaInfo getJavaInfo() {
        return javaInfo;
    }

    /**************************************************
     *
     * 设置数据
     *
     **************************************************/
    @Override
    protected void dealLines() {
        uiBaseBuilder.dealLines();
    }

}
/* model_temp_start
package [[pkg]];

public class [[name]] extends [[baseClass]] {

}
model_temp_end */
