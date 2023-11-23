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

    void colorResInit(int position, String k, String resourceTool, String toString);

    void dpInit(int position, String k, String mobileTool, String s);

    void dimenInit(int position, String k, String resourceTool, String toString);

    int listAdapterCount();

    void listAdapter(int adapterIndex, String v, String vh, String rvName);

    int loadMoreCount();

    void loadMore(int loadMoreCount, String v);

    void defaultListMoreAdapterIf(int adapterIndex, String v, String k);

    void defaultListAdapterIf(int adapterIndex, String v, String k);

    void isSuperAccept(boolean hasBaseClass);

    void accept(int position, String methodName, String netBackFullName, String coreSendParams, String paramStr);

    void acceptMethod(int position, String methodName, String methodParamStr);

    void actBack(int actBackIndex, String s, String code4Request, String toStaticType, String methodName);

    void actBackParam(int actBackIndex, int paramIndex, String pass, String v);

    void isActBackParamDivider(int actBackIndex, int paramIndex, boolean b);

    void actBackMethod(int actBackIndex, String methodName, String methodParams);

    void toastDialogIf(String toastDialogFullName, String layout, String onHiddenFinishedFullName, String handlerToolFullName);
}
