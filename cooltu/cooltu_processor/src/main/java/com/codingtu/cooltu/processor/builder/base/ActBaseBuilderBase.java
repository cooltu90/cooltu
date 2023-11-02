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
    private java.util.Map<String, Boolean> formInitIfs;
    private java.util.Map<String, Integer> formInitCounts;
    private StringBuilder formInitSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> formInit;
    private java.util.Map<String, Boolean> onCreateCompleteInitIfs;
    private java.util.Map<String, Integer> onCreateCompleteInitCounts;
    private StringBuilder onCreateCompleteInitSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> onCreateCompleteInit;
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
    private java.util.Map<String, Boolean> superAcceptIfs;
    private java.util.Map<String, Integer> superAcceptCounts;
    private StringBuilder superAcceptSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> superAccept;
    private java.util.Map<String, Boolean> acceptIfs;
    private java.util.Map<String, Integer> acceptCounts;
    private StringBuilder acceptSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> accept;
    private java.util.Map<String, Boolean> acceptMethodIfs;
    private java.util.Map<String, Integer> acceptMethodCounts;
    private StringBuilder acceptMethodSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> acceptMethod;
    private java.util.Map<String, Boolean> actBackIfs;
    private java.util.Map<String, Integer> actBackCounts;
    private StringBuilder actBackSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> actBack;
    private java.util.Map<String, Boolean> actBackMethodIfs;
    private java.util.Map<String, Integer> actBackMethodCounts;
    private StringBuilder actBackMethodSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> actBackMethod;
    private java.util.Map<String, Boolean> permissionBackIfs;
    private java.util.Map<String, Integer> permissionBackCounts;
    private StringBuilder permissionBackSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> permissionBack;
    private java.util.Map<String, Boolean> permissionBackMethodIfs;
    private java.util.Map<String, Integer> permissionBackMethodCounts;
    private StringBuilder permissionBackMethodSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> permissionBackMethod;
    private java.util.Map<String, Boolean> bindHandlerIfs;
    private java.util.Map<String, Integer> bindHandlerCounts;
    private StringBuilder bindHandlerSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> bindHandler;

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
        formInitIfs = new java.util.HashMap<>();
        formInitCounts = new java.util.HashMap<>();
        formInitSb = map.get("formInit");
        formInit = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        onCreateCompleteInitIfs = new java.util.HashMap<>();
        onCreateCompleteInitCounts = new java.util.HashMap<>();
        onCreateCompleteInitSb = map.get("onCreateCompleteInit");
        onCreateCompleteInit = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
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
        superAcceptIfs = new java.util.HashMap<>();
        superAcceptCounts = new java.util.HashMap<>();
        superAcceptSb = map.get("superAccept");
        superAccept = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        acceptIfs = new java.util.HashMap<>();
        acceptCounts = new java.util.HashMap<>();
        acceptSb = map.get("accept");
        accept = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        acceptMethodIfs = new java.util.HashMap<>();
        acceptMethodCounts = new java.util.HashMap<>();
        acceptMethodSb = map.get("acceptMethod");
        acceptMethod = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        actBackIfs = new java.util.HashMap<>();
        actBackCounts = new java.util.HashMap<>();
        actBackSb = map.get("actBack");
        actBack = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        actBackMethodIfs = new java.util.HashMap<>();
        actBackMethodCounts = new java.util.HashMap<>();
        actBackMethodSb = map.get("actBackMethod");
        actBackMethod = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        permissionBackIfs = new java.util.HashMap<>();
        permissionBackCounts = new java.util.HashMap<>();
        permissionBackSb = map.get("permissionBack");
        permissionBack = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        permissionBackMethodIfs = new java.util.HashMap<>();
        permissionBackMethodCounts = new java.util.HashMap<>();
        permissionBackMethodSb = map.get("permissionBackMethod");
        permissionBackMethod = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        bindHandlerIfs = new java.util.HashMap<>();
        bindHandlerCounts = new java.util.HashMap<>();
        bindHandlerSb = map.get("bindHandler");
        bindHandler = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

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
    protected void actBackCount(int count) {
        actBackCounts.put(getForKey("actBack"), count);
    }
    protected void actBackCountAdd() {
        count(actBackCounts, getForKey("actBack"));
    }
    protected void actBackParamCount(int i0, int count) {
        actBackCounts.put(getForKey("actBackParam", i0), count);
    }
    protected void actBackParamCountAdd(int i0) {
        count(actBackCounts, getForKey("actBackParam", i0));
    }
    protected void actBackMethodCount(int count) {
        actBackMethodCounts.put(getForKey("actBackMethod"), count);
    }
    protected void actBackMethodCountAdd() {
        count(actBackMethodCounts, getForKey("actBackMethod"));
    }
    protected void permissionBackCount(int count) {
        permissionBackCounts.put(getForKey("permissionBack"), count);
    }
    protected void permissionBackCountAdd() {
        count(permissionBackCounts, getForKey("permissionBack"));
    }
    protected void permissionBackMethodCount(int count) {
        permissionBackMethodCounts.put(getForKey("permissionBackMethod"), count);
    }
    protected void permissionBackMethodCountAdd() {
        count(permissionBackMethodCounts, getForKey("permissionBackMethod"));
    }

    protected void field(int i0, String sign, String type, String name) {
        addForMap(this.field, getForKey("field", i0), sign, type, name);
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
    protected void actBackParam(int i0, int i1, String passFullName, String name) {
        addForMap(this.actBack, getForKey("actBackParam", i0, i1), passFullName, name);
    }
    protected void actBack(int i0, String ifSign, String code4RequestFullName, String code, String methodName) {
        addForMap(this.actBack, getForKey("actBack", i0), ifSign, code4RequestFullName, code, methodName);
    }
    protected void actBackMethod(int i0, String methodName, String params) {
        addForMap(this.actBackMethod, getForKey("actBackMethod", i0), methodName, params);
    }
    protected void permissionBack(int i0, String ifSign, String permissionsFullName, String methodNameStatic, String actStaticName, String methodName) {
        addForMap(this.permissionBack, getForKey("permissionBack", i0), ifSign, permissionsFullName, methodNameStatic, actStaticName, methodName);
    }
    protected void permissionBackMethod(int i0, String methodName) {
        addForMap(this.permissionBackMethod, getForKey("permissionBackMethod", i0), methodName);
    }

    protected void layoutIf(boolean is) {
        layoutIfs.put(getIfKey("layout"), is);
    }
    protected void layoutIf(String layout) {
        addForMap(this.layout, getIfKey("layout"), layout);
    }
    protected void formInitIf(boolean is) {
        formInitIfs.put(getIfKey("formInit"), is);
    }
    protected void formInitIf(String name, String type) {
        addForMap(this.formInit, getIfKey("formInit"), name, name, type, name);
    }
    protected void onCreateCompleteInitIf(boolean is) {
        onCreateCompleteInitIfs.put(getIfKey("onCreateCompleteInit"), is);
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
    protected void superAcceptIf(boolean is) {
        superAcceptIfs.put(getIfKey("superAccept"), is);
    }
    protected void actBackParamDividerIf(int i0, int i1, boolean is) {
        actBackIfs.put(getIfKey("actBackParamDivider", i0, i1), is);
    }
    protected void allowIf(int i0, boolean is) {
        permissionBackIfs.put(getIfKey("allow", i0), is);
    }
    protected void allowIf(int i0, String permissionToolFullName) {
        addForMap(this.permissionBack, getIfKey("allow", i0), permissionToolFullName);
    }
    protected void allowParamIf(int i0, boolean is) {
        permissionBackMethodIfs.put(getIfKey("allowParam", i0), is);
    }
    protected void bindHandlerIf(boolean is) {
        bindHandlerIfs.put(getIfKey("bindHandler"), is);
    }
    protected void bindHandlerIf(String beanType, String beanName) {
        addForMap(this.bindHandler, getIfKey("bindHandler"), beanType, beanName, beanType, beanName, beanName, beanName);
    }

    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < fieldCounts.get(getForKey("field")); i0++) {
            List<String> field0 = field.get(getForKey("field", i0));
            addLnTag(fieldSb, "    [sign] [type] [name];", field0.get(0), field0.get(1), field0.get(2));
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
        if (formInitIfs.get(getIfKey("formInit"))) {
            List<String> formInit0 = formInit.get(getIfKey("formInit"));
            addLnTag(formInitSb, "        if ([name] == null) {", formInit0.get(0));
            addLnTag(formInitSb, "            [name] = new [type]();", formInit0.get(1), formInit0.get(2));
            addLnTag(formInitSb, "            initFormBean = true;");
            addLnTag(formInitSb, "        }");
            addLnTag(formInitSb, "        bindHandler = new BindHandler([name]);", formInit0.get(3));
        }
        if (onCreateCompleteInitIfs.get(getIfKey("onCreateCompleteInit"))) {
            List<String> onCreateCompleteInit0 = onCreateCompleteInit.get(getIfKey("onCreateCompleteInit"));
            addLnTag(onCreateCompleteInitSb, "        onCreateComplete();");
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
        if (superAcceptIfs.get(getIfKey("superAccept"))) {
            List<String> superAccept0 = superAccept.get(getIfKey("superAccept"));
            addLnTag(superAcceptSb, "        super.accept(code, result, params, objs);");
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
        for (int i0 = 0; i0 < actBackCounts.get(getForKey("actBack")); i0++) {
            List<String> actBack0 = actBack.get(getForKey("actBack", i0));
            addLnTag(actBackSb, "            [ifSign] (requestCode == [code4RequestFullName].[code]) {", actBack0.get(0), actBack0.get(1), actBack0.get(2));
            StringBuilder actBackParamSb = new StringBuilder();
            for (int i1 = 0; i1 < actBackCounts.get(getForKey("actBackParam", i0)); i1++) {
                List<String> actBack1 = actBack.get(getForKey("actBackParam", i0, i1));
                StringBuilder actBackParamDividerSb = new StringBuilder();
                if (actBackIfs.get(getIfKey("actBackParamDivider", i0, i1))) {
                    List<String> actBack2 = actBack.get(getIfKey("actBackParamDivider", i0, i1));
                    addTag(actBackParamDividerSb, ", ");
                }
                addTag(actBackParamSb, "[passFullName].[name](data)[actBackParamDivider]", actBack1.get(0), actBack1.get(1), actBackParamDividerSb.toString());
            }
            addLnTag(actBackSb, "                [methodName]([actBackParam]);", actBack0.get(3), actBackParamSb.toString());
            addLnTag(actBackSb, "            }");
        }
        for (int i0 = 0; i0 < actBackMethodCounts.get(getForKey("actBackMethod")); i0++) {
            List<String> actBackMethod0 = actBackMethod.get(getForKey("actBackMethod", i0));
            addLnTag(actBackMethodSb, "    protected void [methodName]([params]) {}", actBackMethod0.get(0), actBackMethod0.get(1));
        }
        for (int i0 = 0; i0 < permissionBackCounts.get(getForKey("permissionBack")); i0++) {
            List<String> permissionBack0 = permissionBack.get(getForKey("permissionBack", i0));
            addLnTag(permissionBackSb, "        [ifSign] (requestCode == [permissionsFullName].CODE_[methodNameStatic]_IN_[actStaticName]) {", permissionBack0.get(0), permissionBack0.get(1), permissionBack0.get(2), permissionBack0.get(3));
            StringBuilder allowSb = new StringBuilder();
            if (permissionBackIfs.get(getIfKey("allow", i0))) {
                List<String> permissionBack1 = permissionBack.get(getIfKey("allow", i0));
                addTag(allowSb, "[permissionToolFullName].allow(grantResults)", permissionBack1.get(0));
            }
            addLnTag(permissionBackSb, "            [methodName]([allow]);", permissionBack0.get(4), allowSb.toString());
            addLnTag(permissionBackSb, "        }");
        }
        for (int i0 = 0; i0 < permissionBackMethodCounts.get(getForKey("permissionBackMethod")); i0++) {
            List<String> permissionBackMethod0 = permissionBackMethod.get(getForKey("permissionBackMethod", i0));
            StringBuilder allowParamSb = new StringBuilder();
            if (permissionBackMethodIfs.get(getIfKey("allowParam", i0))) {
                List<String> permissionBackMethod1 = permissionBackMethod.get(getIfKey("allowParam", i0));
                addTag(allowParamSb, "boolean isAllow");
            }
            addLnTag(permissionBackMethodSb, "    protected void [methodName]([allowParam]) {}", permissionBackMethod0.get(0), allowParamSb.toString());
        }
        if (bindHandlerIfs.get(getIfKey("bindHandler"))) {
            List<String> bindHandler0 = bindHandler.get(getIfKey("bindHandler"));
            addLnTag(bindHandlerSb, "    public static class BindHandler extends android.os.Handler {");
            addLnTag(bindHandlerSb, "        private [beanType] [beanName];", bindHandler0.get(0), bindHandler0.get(1));
            addLnTag(bindHandlerSb, "");
            addLnTag(bindHandlerSb, "        public BindHandler([beanType] [beanName]) {", bindHandler0.get(2), bindHandler0.get(3));
            addLnTag(bindHandlerSb, "            this.[beanName] = [beanName];", bindHandler0.get(4), bindHandler0.get(5));
            addLnTag(bindHandlerSb, "        }");
            addLnTag(bindHandlerSb, "");
            addLnTag(bindHandlerSb, "        @Override");
            addLnTag(bindHandlerSb, "        public void handleMessage(android.os.Message msg) {");
            addLnTag(bindHandlerSb, "            super.handleMessage(msg);");
            addLnTag(bindHandlerSb, "        }");
            addLnTag(bindHandlerSb, "    }");
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
        lines.add("[[formInit]]");
        lines.add("[[onCreateCompleteInit]]");
        lines.add("    }");
        lines.add("");
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
        lines.add("[[superAccept]]");
        lines.add("");
        lines.add("[[accept]]");
        lines.add("    }");
        lines.add("[[acceptMethod]]");
        lines.add("    @Override");
        lines.add("    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {");
        lines.add("        super.onActivityResult(requestCode, resultCode, data);");
        lines.add("        if (resultCode == RESULT_OK) {");
        lines.add("[[actBack]]");
        lines.add("        }");
        lines.add("    }");
        lines.add("[[actBackMethod]]");
        lines.add("    @Override");
        lines.add("    public void back(int requestCode, String[] permissions, int[] grantResults) {");
        lines.add("        super.back(requestCode, permissions, grantResults);");
        lines.add("[[permissionBack]]");
        lines.add("    }");
        lines.add("[[permissionBackMethod]]");
        lines.add("[[bindHandler]]");
        lines.add("}");
        lines.add("");

        return lines;
    }
}
