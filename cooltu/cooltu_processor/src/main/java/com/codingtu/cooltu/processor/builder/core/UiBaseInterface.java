package com.codingtu.cooltu.processor.builder.core;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;

public interface UiBaseInterface {
    void addTag(StringBuilder tag, String line, Object... tags);

    StringBuilderValueMap<String> getMap();

    JavaInfo getJavaInfo();

    void layoutIf(String inflateTool, String layout);

    void findView(int position, String fieldName, String parent, String r, String id);

    String getDefulatViewParent();

    int fieldCount();

    void field(int fieldCount, String sign, String type, String name);
}
