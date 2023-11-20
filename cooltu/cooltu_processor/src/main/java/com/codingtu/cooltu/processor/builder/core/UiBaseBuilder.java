package com.codingtu.cooltu.processor.builder.core;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;

public class UiBaseBuilder {
    private final UiBaseInterface uiBase;

    public UiBaseBuilder(UiBaseInterface uiBase) {
        this.uiBase = uiBase;
    }

    private StringBuilder getStringBuilder(String key) {
        return uiBase.getMap().get(key);
    }

    private JavaInfo javaInfo() {
        return this.uiBase.getJavaInfo();
    }

    public void dealLines() {
        uiBase.addTag(getStringBuilder("pkg"), javaInfo().pkg);
        uiBase.addTag(getStringBuilder("name"), javaInfo().name);
    }

}
