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

    void noticeDialogIf(String noticeDialog, String defaultNoticeDialogLayout);

    void editDialog(int position, String editDialog, String v, String edClassName, String title, String hint, String s, String defaultEditDialogLayout, String s1);

    void edShowParamIf(int position, String objClass, String objName);

    void edYesParamIf(int position, String objClass, String objName);

    void isEdUseYes(int position, boolean b);

    void edUseYesConvertIf(int position, String objClass);

    void setTextWatcherIf(int position, String edClassName);

    void setTextWatcherMethodIf(int position, String edTextWatcher, String edClassName);

    void isStopAnimation(int position, boolean b);

    void dialog(int position, String dialog, String v);

    void isShowDialogElse(int position, int i, boolean b);

    void showDialogUpdataContentIf(int position, int i, String v);

    void isShowDialogSetContent(int position, int i, boolean b);

    void isShowDialogSetContentStr(int position, int i, boolean b);

    void isShowDialogLeftObj(int position, int i, boolean b);

    void isShowDialogRightObj(int position, int i, boolean b);

    void showDialogLeftObjConvertIf(int position, int i, String objClass);

    void showDialogRightObjConvertIf(int position, int i, String objClass);

    void showDialog(int position, int i, String dialogClassName, String toString, String v, String dialog, String title, String leftBtText, String rightBtText, String defaultDialogLayout, String dialogOnBtClick, String s);

    void leftParamIf(int position, String objClass, String objName);

    void rightParamIf(int position, String objClass, String objName);

}
