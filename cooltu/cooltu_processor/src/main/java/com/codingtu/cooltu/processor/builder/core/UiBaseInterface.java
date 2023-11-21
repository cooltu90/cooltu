package com.codingtu.cooltu.processor.builder.core;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;

public interface UiBaseInterface {
    void addTag(StringBuilder tag, String line, Object... tags);

    StringBuilderValueMap<String> getMap();

    JavaInfo getJavaInfo();

    String getDefulatViewParent();

    /**************************************************
     *
     *
     *
     **************************************************/
    void layoutIf(String inflateTool, String layout);

    void findView(int position, String fieldName, String parent, String r, String id);

    int fieldCount();

    void field(int fieldCount, String sign, String type, String name);

    void onClickMethods(int clickViewInfoIndex, String method, String methodParams);

    void onClickSwith(int clickViewInfoIndex, String method);

    void onClickSwitchParamsIf(int clickViewInfoIndex, String divider);

    void onClickSwitchParams(int clickViewInfoIndex, int paramsIndex, String k, String lib4a, String s, String divider);

    void onClickCase(int clickViewInfoIndex, int idIndex, String toString);

    void setOnClick(int i, String fieldName);

    void isSuperOnClick(boolean hasBaseClass);

    int setOnClickCount();

    void colorStrInit(int position, String k, String v);
}
