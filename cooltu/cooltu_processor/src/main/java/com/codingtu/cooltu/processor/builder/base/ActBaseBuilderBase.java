package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class ActBaseBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder baseClass;
    protected StringBuilder netBackIFullName;
    private java.util.Map<String, Boolean> fieldIfs;
    private java.util.Map<String, Integer> fieldCounts;
    private StringBuilder fieldSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> field;
    private java.util.Map<String, Boolean> layoutIfs;
    private java.util.Map<String, Integer> layoutCounts;
    private StringBuilder layoutSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> layout;
    private java.util.Map<String, Boolean> findViewIfs;
    private java.util.Map<String, Integer> findViewCounts;
    private StringBuilder findViewSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> findView;
    private java.util.Map<String, Boolean> setOnClickIfs;
    private java.util.Map<String, Integer> setOnClickCounts;
    private StringBuilder setOnClickSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> setOnClick;
    private java.util.Map<String, Boolean> colorStrInitIfs;
    private java.util.Map<String, Integer> colorStrInitCounts;
    private StringBuilder colorStrInitSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> colorStrInit;
    private java.util.Map<String, Boolean> colorResInitIfs;
    private java.util.Map<String, Integer> colorResInitCounts;
    private StringBuilder colorResInitSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> colorResInit;
    private java.util.Map<String, Boolean> dpInitIfs;
    private java.util.Map<String, Integer> dpInitCounts;
    private StringBuilder dpInitSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> dpInit;
    private java.util.Map<String, Boolean> dimenInitIfs;
    private java.util.Map<String, Integer> dimenInitCounts;
    private StringBuilder dimenInitSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> dimenInit;
    private java.util.Map<String, Boolean> startInitIfs;
    private java.util.Map<String, Integer> startInitCounts;
    private StringBuilder startInitSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> startInit;
    private java.util.Map<String, Boolean> superOnClickIfs;
    private java.util.Map<String, Integer> superOnClickCounts;
    private StringBuilder superOnClickSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> superOnClick;
    private java.util.Map<String, Boolean> onClickSwithIfs;
    private java.util.Map<String, Integer> onClickSwithCounts;
    private StringBuilder onClickSwithSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> onClickSwith;
    private java.util.Map<String, Boolean> onClickMethodsIfs;
    private java.util.Map<String, Integer> onClickMethodsCounts;
    private StringBuilder onClickMethodsSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> onClickMethods;
    protected StringBuilder coreSendParamsFullName;
    private java.util.Map<String, Boolean> acceptIfs;
    private java.util.Map<String, Integer> acceptCounts;
    private StringBuilder acceptSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> accept;
    private java.util.Map<String, Boolean> acceptMethodIfs;
    private java.util.Map<String, Integer> acceptMethodCounts;
    private StringBuilder acceptMethodSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> acceptMethod;

    public ActBaseBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        baseClass = map.get("baseClass");
        netBackIFullName = map.get("netBackIFullName");
        fieldIfs = new java.util.HashMap<>();
        fieldCounts = new java.util.HashMap<>();
        fieldSb = map.get("field");
        field = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        layoutIfs = new java.util.HashMap<>();
        layoutCounts = new java.util.HashMap<>();
        layoutSb = map.get("layout");
        layout = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        findViewIfs = new java.util.HashMap<>();
        findViewCounts = new java.util.HashMap<>();
        findViewSb = map.get("findView");
        findView = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        setOnClickIfs = new java.util.HashMap<>();
        setOnClickCounts = new java.util.HashMap<>();
        setOnClickSb = map.get("setOnClick");
        setOnClick = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        colorStrInitIfs = new java.util.HashMap<>();
        colorStrInitCounts = new java.util.HashMap<>();
        colorStrInitSb = map.get("colorStrInit");
        colorStrInit = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        colorResInitIfs = new java.util.HashMap<>();
        colorResInitCounts = new java.util.HashMap<>();
        colorResInitSb = map.get("colorResInit");
        colorResInit = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        dpInitIfs = new java.util.HashMap<>();
        dpInitCounts = new java.util.HashMap<>();
        dpInitSb = map.get("dpInit");
        dpInit = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        dimenInitIfs = new java.util.HashMap<>();
        dimenInitCounts = new java.util.HashMap<>();
        dimenInitSb = map.get("dimenInit");
        dimenInit = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        startInitIfs = new java.util.HashMap<>();
        startInitCounts = new java.util.HashMap<>();
        startInitSb = map.get("startInit");
        startInit = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        superOnClickIfs = new java.util.HashMap<>();
        superOnClickCounts = new java.util.HashMap<>();
        superOnClickSb = map.get("superOnClick");
        superOnClick = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        onClickSwithIfs = new java.util.HashMap<>();
        onClickSwithCounts = new java.util.HashMap<>();
        onClickSwithSb = map.get("onClickSwith");
        onClickSwith = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        onClickMethodsIfs = new java.util.HashMap<>();
        onClickMethodsCounts = new java.util.HashMap<>();
        onClickMethodsSb = map.get("onClickMethods");
        onClickMethods = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        coreSendParamsFullName = map.get("coreSendParamsFullName");
        acceptIfs = new java.util.HashMap<>();
        acceptCounts = new java.util.HashMap<>();
        acceptSb = map.get("accept");
        accept = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        acceptMethodIfs = new java.util.HashMap<>();
        acceptMethodCounts = new java.util.HashMap<>();
        acceptMethodSb = map.get("acceptMethod");
        acceptMethod = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }
    protected void fieldCount(int count) {
        fieldCounts.put(getForKey("field"), count);
    }
    protected void fieldCountAdd() {
        count(fieldCounts, getForKey("field"));
    }
    protected void findViewCount(int count) {
        findViewCounts.put(getForKey("findView"), count);
    }
    protected void findViewCountAdd() {
        count(findViewCounts, getForKey("findView"));
    }
    protected void setOnClickCount(int count) {
        setOnClickCounts.put(getForKey("setOnClick"), count);
    }
    protected void setOnClickCountAdd() {
        count(setOnClickCounts, getForKey("setOnClick"));
    }
    protected void colorStrInitCount(int count) {
        colorStrInitCounts.put(getForKey("colorStrInit"), count);
    }
    protected void colorStrInitCountAdd() {
        count(colorStrInitCounts, getForKey("colorStrInit"));
    }
    protected void colorResInitCount(int count) {
        colorResInitCounts.put(getForKey("colorResInit"), count);
    }
    protected void colorResInitCountAdd() {
        count(colorResInitCounts, getForKey("colorResInit"));
    }
    protected void dpInitCount(int count) {
        dpInitCounts.put(getForKey("dpInit"), count);
    }
    protected void dpInitCountAdd() {
        count(dpInitCounts, getForKey("dpInit"));
    }
    protected void dimenInitCount(int count) {
        dimenInitCounts.put(getForKey("dimenInit"), count);
    }
    protected void dimenInitCountAdd() {
        count(dimenInitCounts, getForKey("dimenInit"));
    }
    protected void startInitCount(int count) {
        startInitCounts.put(getForKey("startInit"), count);
    }
    protected void startInitCountAdd() {
        count(startInitCounts, getForKey("startInit"));
    }
    protected void onClickSwithCount(int count) {
        onClickSwithCounts.put(getForKey("onClickSwith"), count);
    }
    protected void onClickSwithCountAdd() {
        count(onClickSwithCounts, getForKey("onClickSwith"));
    }
    protected void onClickCaseCount(int i0, int count) {
        onClickSwithCounts.put(getForKey("onClickCase", i0), count);
    }
    protected void onClickCaseCountAdd(int i0) {
        count(onClickSwithCounts, getForKey("onClickCase", i0));
    }
    protected void onClickSwitchParamsCount(int i0, int count) {
        onClickSwithCounts.put(getForKey("onClickSwitchParams", i0), count);
    }
    protected void onClickSwitchParamsCountAdd(int i0) {
        count(onClickSwithCounts, getForKey("onClickSwitchParams", i0));
    }
    protected void onClickMethodsCount(int count) {
        onClickMethodsCounts.put(getForKey("onClickMethods"), count);
    }
    protected void onClickMethodsCountAdd() {
        count(onClickMethodsCounts, getForKey("onClickMethods"));
    }
    protected void acceptCount(int count) {
        acceptCounts.put(getForKey("accept"), count);
    }
    protected void acceptCountAdd() {
        count(acceptCounts, getForKey("accept"));
    }
    protected void acceptMethodCount(int count) {
        acceptMethodCounts.put(getForKey("acceptMethod"), count);
    }
    protected void acceptMethodCountAdd() {
        count(acceptMethodCounts, getForKey("acceptMethod"));
    }

    protected void field(int i0, String type, String name) {
        addForMap(this.field, getForKey("field", i0), type, name);
    }
    protected void findView(int i0, String fieldName, String parent, String rPkg, String id) {
        addForMap(this.findView, getForKey("findView", i0), fieldName, parent, rPkg, id);
    }
    protected void setOnClick(int i0, String fieldName) {
        addForMap(this.setOnClick, getForKey("setOnClick", i0), fieldName);
    }
    protected void colorStrInit(int i0, String name, String color) {
        addForMap(this.colorStrInit, getForKey("colorStrInit", i0), name, color);
    }
    protected void colorResInit(int i0, String name, String resourceToolFullName, String id) {
        addForMap(this.colorResInit, getForKey("colorResInit", i0), name, resourceToolFullName, id);
    }
    protected void dpInit(int i0, String name, String mobileToolFullName, String value) {
        addForMap(this.dpInit, getForKey("dpInit", i0), name, mobileToolFullName, value);
    }
    protected void dimenInit(int i0, String name, String resourceToolFullName, String id) {
        addForMap(this.dimenInit, getForKey("dimenInit", i0), name, resourceToolFullName, id);
    }
    protected void startInit(int i0, String name, String passFullName) {
        addForMap(this.startInit, getForKey("startInit", i0), name, passFullName, name);
    }
    protected void onClickCase(int i0, int i1, String id) {
        addForMap(this.onClickSwith, getForKey("onClickCase", i0, i1), id);
    }
    protected void onClickSwitchParams(int i0, int i1, String type, String pkg, String index, String divider) {
        addForMap(this.onClickSwith, getForKey("onClickSwitchParams", i0, i1), type, pkg, index, divider);
    }
    protected void onClickSwith(int i0, String methodName) {
        addForMap(this.onClickSwith, getForKey("onClickSwith", i0), methodName);
    }
    protected void onClickMethods(int i0, String methodName, String params) {
        addForMap(this.onClickMethods, getForKey("onClickMethods", i0), methodName, params);
    }
    protected void accept(int i0, String methodName, String netBackFullName, String coreSendParamsFullName, String params) {
        addForMap(this.accept, getForKey("accept", i0), methodName, netBackFullName, coreSendParamsFullName, methodName, params);
    }
    protected void acceptMethod(int i0, String methodName, String params) {
        addForMap(this.acceptMethod, getForKey("acceptMethod", i0), methodName, params);
    }

    protected void layoutIf(boolean is) {
        layoutIfs.put(getIfKey("layout"), is);
    }
    protected void layoutIf(String layout) {
        addForMap(this.layout, getIfKey("layout"), layout);
    }
    protected void superOnClickIf(boolean is) {
        superOnClickIfs.put(getIfKey("superOnClick"), is);
    }
    protected void onClickSwitchParamsIf(int i0, boolean is) {
        onClickSwithIfs.put(getIfKey("onClickSwitchParams", i0), is);
    }
    protected void onClickSwitchParamsIf(int i0, String divider) {
        addForMap(this.onClickSwith, getIfKey("onClickSwitchParams", i0), divider);
    }

    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < fieldCounts.get(getForKey("field")); i0++) {
            List<String> field0 = field.get(getForKey("field", i0));
            addLnTag(fieldSb, "    protected [type] [name];", field0.get(0), field0.get(1));
        }
        if (layoutIfs.get(getIfKey("layout"))) {
            List<String> layout0 = layout.get(getIfKey("layout"));
            addLnTag(layoutSb, "        setContentView([layout]);", layout0.get(0));
        }
        for (int i0 = 0; i0 < findViewCounts.get(getForKey("findView")); i0++) {
            List<String> findView0 = findView.get(getForKey("findView", i0));
            addLnTag(findViewSb, "        [fieldName] = [parent]findViewById([rPkg].R.id.[id]);", findView0.get(0), findView0.get(1), findView0.get(2), findView0.get(3));
        }
        for (int i0 = 0; i0 < setOnClickCounts.get(getForKey("setOnClick")); i0++) {
            List<String> setOnClick0 = setOnClick.get(getForKey("setOnClick", i0));
            addLnTag(setOnClickSb, "        [fieldName].setOnClickListener(this);", setOnClick0.get(0));
        }
        for (int i0 = 0; i0 < colorStrInitCounts.get(getForKey("colorStrInit")); i0++) {
            List<String> colorStrInit0 = colorStrInit.get(getForKey("colorStrInit", i0));
            addLnTag(colorStrInitSb, "        [name] = android.graphics.Color.parseColor(\"[color]\");", colorStrInit0.get(0), colorStrInit0.get(1));
        }
        for (int i0 = 0; i0 < colorResInitCounts.get(getForKey("colorResInit")); i0++) {
            List<String> colorResInit0 = colorResInit.get(getForKey("colorResInit", i0));
            addLnTag(colorResInitSb, "        [name] = [resourceToolFullName].getColor([id]);", colorResInit0.get(0), colorResInit0.get(1), colorResInit0.get(2));
        }
        for (int i0 = 0; i0 < dpInitCounts.get(getForKey("dpInit")); i0++) {
            List<String> dpInit0 = dpInit.get(getForKey("dpInit", i0));
            addLnTag(dpInitSb, "        [name] = [mobileToolFullName].dpToPx([value]);", dpInit0.get(0), dpInit0.get(1), dpInit0.get(2));
        }
        for (int i0 = 0; i0 < dimenInitCounts.get(getForKey("dimenInit")); i0++) {
            List<String> dimenInit0 = dimenInit.get(getForKey("dimenInit", i0));
            addLnTag(dimenInitSb, "        [name] = [resourceToolFullName].getDimen([id]);", dimenInit0.get(0), dimenInit0.get(1), dimenInit0.get(2));
        }
        for (int i0 = 0; i0 < startInitCounts.get(getForKey("startInit")); i0++) {
            List<String> startInit0 = startInit.get(getForKey("startInit", i0));
            addLnTag(startInitSb, "        [name] = [passFullName].[name](getIntent());", startInit0.get(0), startInit0.get(1), startInit0.get(2));
        }
        if (superOnClickIfs.get(getIfKey("superOnClick"))) {
            List<String> superOnClick0 = superOnClick.get(getIfKey("superOnClick"));
            addLnTag(superOnClickSb, "        super.onClick(v);");
        }
        for (int i0 = 0; i0 < onClickSwithCounts.get(getForKey("onClickSwith")); i0++) {
            List<String> onClickSwith0 = onClickSwith.get(getForKey("onClickSwith", i0));
            for (int i1 = 0; i1 < onClickSwithCounts.get(getForKey("onClickCase", i0)); i1++) {
                List<String> onClickSwith1 = onClickSwith.get(getForKey("onClickCase", i0, i1));
                addLnTag(onClickSwithSb, "            case [id]:", onClickSwith1.get(0));
            }
            addLnTag(onClickSwithSb, "                [methodName](", onClickSwith0.get(0));
            if (onClickSwithIfs.get(getIfKey("onClickSwitchParams", i0))) {
                List<String> onClickSwith1 = onClickSwith.get(getIfKey("onClickSwitchParams", i0));
                addLnTag(onClickSwithSb, "                        v[divider]", onClickSwith1.get(0));
            }
            for (int i1 = 0; i1 < onClickSwithCounts.get(getForKey("onClickSwitchParams", i0)); i1++) {
                List<String> onClickSwith1 = onClickSwith.get(getForKey("onClickSwitchParams", i0, i1));
                addLnTag(onClickSwithSb, "                        ([type]) v.getTag([pkg].R.id.tag_[index])[divider]", onClickSwith1.get(0), onClickSwith1.get(1), onClickSwith1.get(2), onClickSwith1.get(3));
            }
            addLnTag(onClickSwithSb, "                );");
            addLnTag(onClickSwithSb, "                break;");
        }
        for (int i0 = 0; i0 < onClickMethodsCounts.get(getForKey("onClickMethods")); i0++) {
            List<String> onClickMethods0 = onClickMethods.get(getForKey("onClickMethods", i0));
            addLnTag(onClickMethodsSb, "    protected void [methodName]([params]) {}", onClickMethods0.get(0), onClickMethods0.get(1));
        }
        for (int i0 = 0; i0 < acceptCounts.get(getForKey("accept")); i0++) {
            List<String> accept0 = accept.get(getForKey("accept", i0));
            addLnTag(acceptSb, "        if (\"[methodName]\".equals(code)) {", accept0.get(0));
            addLnTag(acceptSb, "            new [netBackFullName]() {", accept0.get(1));
            addLnTag(acceptSb, "                @Override");
            addLnTag(acceptSb, "                public void accept(String code, Result<ResponseBody> result, [coreSendParamsFullName] params, List objs) {", accept0.get(2));
            addLnTag(acceptSb, "                    super.accept(code, result, params, objs);");
            addLnTag(acceptSb, "                    [methodName]([params]);", accept0.get(3), accept0.get(4));
            addLnTag(acceptSb, "                }");
            addLnTag(acceptSb, "            }.accept(code, result, params, objs);");
            addLnTag(acceptSb, "        }");
        }
        for (int i0 = 0; i0 < acceptMethodCounts.get(getForKey("acceptMethod")); i0++) {
            List<String> acceptMethod0 = acceptMethod.get(getForKey("acceptMethod", i0));
            addLnTag(acceptMethodSb, "    protected void [methodName]([params]) {}", acceptMethod0.get(0), acceptMethod0.get(1));
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import android.view.View;");
        lines.add("");
        lines.add("import java.util.List;");
        lines.add("");
        lines.add("import okhttp3.ResponseBody;");
        lines.add("import retrofit2.adapter.rxjava2.Result;");
        lines.add("");
        lines.add("public abstract class [[name]] extends [[baseClass]] implements View.OnClickListener, [[netBackIFullName]] {");
        lines.add("[[field]]");
        lines.add("");
        lines.add("    @Override");
        lines.add("    protected void onCreate(android.os.Bundle savedInstanceState) {");
        lines.add("        super.onCreate(savedInstanceState);");
        lines.add("[[layout]]");
        lines.add("[[findView]]");
        lines.add("[[setOnClick]]");
        lines.add("[[colorStrInit]]");
        lines.add("[[colorResInit]]");
        lines.add("[[dpInit]]");
        lines.add("[[dimenInit]]");
        lines.add("[[startInit]]");
        lines.add("");
        lines.add("    }");
        lines.add("    @Override");
        lines.add("    public void onClick(View v) {");
        lines.add("[[superOnClick]]");
        lines.add("        switch (v.getId()) {");
        lines.add("[[onClickSwith]]");
        lines.add("        }");
        lines.add("    }");
        lines.add("");
        lines.add("[[onClickMethods]]");
        lines.add("    @Override");
        lines.add("    public void accept(String code, Result<ResponseBody> result, [[coreSendParamsFullName]] params, List objs) {");
        lines.add("[[accept]]");
        lines.add("    }");
        lines.add("[[acceptMethod]]");
        lines.add("    @Override");
        lines.add("    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {");
        lines.add("        super.onActivityResult(requestCode, resultCode, data);");
        lines.add("        if (resultCode == RESULT_OK) {");
        lines.add("");
        lines.add("        }");
        lines.add("    }");
        lines.add("}");
        lines.add("");

        return lines;
    }
}
