package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class FragmentBaseBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder baseClass;
    protected StringBuilder netBackIFullName;
    protected java.util.Map<String, Boolean> fieldIfs;
    protected java.util.Map<String, Integer> fieldCounts;
    protected StringBuilder fieldSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> field;
    protected java.util.Map<String, Boolean> layoutIfs;
    protected java.util.Map<String, Integer> layoutCounts;
    protected StringBuilder layoutSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> layout;
    protected java.util.Map<String, Boolean> superOnClickIfs;
    protected java.util.Map<String, Integer> superOnClickCounts;
    protected StringBuilder superOnClickSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> superOnClick;
    protected java.util.Map<String, Boolean> onClickSwithIfs;
    protected java.util.Map<String, Integer> onClickSwithCounts;
    protected StringBuilder onClickSwithSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> onClickSwith;
    protected java.util.Map<String, Boolean> onClickMethodsIfs;
    protected java.util.Map<String, Integer> onClickMethodsCounts;
    protected StringBuilder onClickMethodsSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> onClickMethods;
    protected java.util.Map<String, Boolean> loadMoreIfs;
    protected java.util.Map<String, Integer> loadMoreCounts;
    protected StringBuilder loadMoreSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> loadMore;
    protected StringBuilder coreSendParamsFullName;
    protected java.util.Map<String, Boolean> superAcceptIfs;
    protected java.util.Map<String, Integer> superAcceptCounts;
    protected StringBuilder superAcceptSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> superAccept;
    protected java.util.Map<String, Boolean> acceptIfs;
    protected java.util.Map<String, Integer> acceptCounts;
    protected StringBuilder acceptSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> accept;
    protected java.util.Map<String, Boolean> acceptMethodIfs;
    protected java.util.Map<String, Integer> acceptMethodCounts;
    protected StringBuilder acceptMethodSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> acceptMethod;
    protected java.util.Map<String, Boolean> actBackIfs;
    protected java.util.Map<String, Integer> actBackCounts;
    protected StringBuilder actBackSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> actBack;
    protected java.util.Map<String, Boolean> actBackMethodIfs;
    protected java.util.Map<String, Integer> actBackMethodCounts;
    protected StringBuilder actBackMethodSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> actBackMethod;
    protected java.util.Map<String, Boolean> toastDialogIfs;
    protected java.util.Map<String, Integer> toastDialogCounts;
    protected StringBuilder toastDialogSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> toastDialog;

    public FragmentBaseBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
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
        loadMoreIfs = new java.util.HashMap<>();
        loadMoreCounts = new java.util.HashMap<>();
        loadMoreSb = map.get("loadMore");
        loadMore = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
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
        toastDialogIfs = new java.util.HashMap<>();
        toastDialogCounts = new java.util.HashMap<>();
        toastDialogSb = map.get("toastDialog");
        toastDialog = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }

    public int fieldCount() {
        return count(fieldCounts, getForKey("field"));
    }
    public void field(int i0, String sign, String type, String name) {
        addForMap(this.field, getForKey("field", i0), sign, type, name);
        countAdd(fieldCounts, getForKey("field"));
    }
    public int findViewCount() {
        return count(layoutCounts, getForKey("findView"));
    }
    public void findView(int i0, String fieldName, String parent, String rPkg, String id) {
        addForMap(this.layout, getForKey("findView", i0), fieldName, parent, rPkg, id);
        countAdd(layoutCounts, getForKey("findView"));
    }
    public int setOnClickCount() {
        return count(layoutCounts, getForKey("setOnClick"));
    }
    public void setOnClick(int i0, String fieldName) {
        addForMap(this.layout, getForKey("setOnClick", i0), fieldName);
        countAdd(layoutCounts, getForKey("setOnClick"));
    }
    public int colorStrInitCount() {
        return count(layoutCounts, getForKey("colorStrInit"));
    }
    public void colorStrInit(int i0, String name, String color) {
        addForMap(this.layout, getForKey("colorStrInit", i0), name, color);
        countAdd(layoutCounts, getForKey("colorStrInit"));
    }
    public int colorResInitCount() {
        return count(layoutCounts, getForKey("colorResInit"));
    }
    public void colorResInit(int i0, String name, String resourceToolFullName, String id) {
        addForMap(this.layout, getForKey("colorResInit", i0), name, resourceToolFullName, id);
        countAdd(layoutCounts, getForKey("colorResInit"));
    }
    public int dpInitCount() {
        return count(layoutCounts, getForKey("dpInit"));
    }
    public void dpInit(int i0, String name, String mobileToolFullName, String value) {
        addForMap(this.layout, getForKey("dpInit", i0), name, mobileToolFullName, value);
        countAdd(layoutCounts, getForKey("dpInit"));
    }
    public int dimenInitCount() {
        return count(layoutCounts, getForKey("dimenInit"));
    }
    public void dimenInit(int i0, String name, String resourceToolFullName, String id) {
        addForMap(this.layout, getForKey("dimenInit", i0), name, resourceToolFullName, id);
        countAdd(layoutCounts, getForKey("dimenInit"));
    }
    public int startInitCount() {
        return count(layoutCounts, getForKey("startInit"));
    }
    public void startInit(int i0, String name, String passFullName) {
        addForMap(this.layout, getForKey("startInit", i0), name, passFullName, name);
        countAdd(layoutCounts, getForKey("startInit"));
    }
    public int listAdapterCount() {
        return count(layoutCounts, getForKey("listAdapter"));
    }
    public void listAdapter(int i0, String adapterName, String vhFullName, String rvName) {
        addForMap(this.layout, getForKey("listAdapter", i0), adapterName, vhFullName, adapterName, rvName, adapterName, rvName);
        countAdd(layoutCounts, getForKey("listAdapter"));
    }
    public int onClickCaseCount(int i0) {
        return count(onClickSwithCounts, getForKey("onClickCase", i0));
    }
    public void onClickCase(int i0, int i1, String id) {
        addForMap(this.onClickSwith, getForKey("onClickCase", i0, i1), id);
        countAdd(onClickSwithCounts, getForKey("onClickCase", i0));
    }
    public int onClickSwitchParamsCount(int i0) {
        return count(onClickSwithCounts, getForKey("onClickSwitchParams", i0));
    }
    public void onClickSwitchParams(int i0, int i1, String type, String pkg, String index, String divider) {
        addForMap(this.onClickSwith, getForKey("onClickSwitchParams", i0, i1), type, pkg, index, divider);
        countAdd(onClickSwithCounts, getForKey("onClickSwitchParams", i0));
    }
    public int onClickSwithCount() {
        return count(onClickSwithCounts, getForKey("onClickSwith"));
    }
    public void onClickSwith(int i0, String methodName) {
        addForMap(this.onClickSwith, getForKey("onClickSwith", i0), methodName);
        countAdd(onClickSwithCounts, getForKey("onClickSwith"));
    }
    public int onClickMethodsCount() {
        return count(onClickMethodsCounts, getForKey("onClickMethods"));
    }
    public void onClickMethods(int i0, String methodName, String params) {
        addForMap(this.onClickMethods, getForKey("onClickMethods", i0), methodName, params);
        countAdd(onClickMethodsCounts, getForKey("onClickMethods"));
    }
    public int loadMoreCount() {
        return count(loadMoreCounts, getForKey("loadMore"));
    }
    public void loadMore(int i0, String adapterName) {
        addForMap(this.loadMore, getForKey("loadMore", i0), adapterName);
        countAdd(loadMoreCounts, getForKey("loadMore"));
    }
    public int acceptCount() {
        return count(acceptCounts, getForKey("accept"));
    }
    public void accept(int i0, String methodName, String netBackFullName, String coreSendParamsFullName, String params) {
        addForMap(this.accept, getForKey("accept", i0), methodName, netBackFullName, coreSendParamsFullName, methodName, params);
        countAdd(acceptCounts, getForKey("accept"));
    }
    public int acceptMethodCount() {
        return count(acceptMethodCounts, getForKey("acceptMethod"));
    }
    public void acceptMethod(int i0, String methodName, String params) {
        addForMap(this.acceptMethod, getForKey("acceptMethod", i0), methodName, params);
        countAdd(acceptMethodCounts, getForKey("acceptMethod"));
    }
    public int actBackParamCount(int i0) {
        return count(actBackCounts, getForKey("actBackParam", i0));
    }
    public void actBackParam(int i0, int i1, String passFullName, String name) {
        addForMap(this.actBack, getForKey("actBackParam", i0, i1), passFullName, name);
        countAdd(actBackCounts, getForKey("actBackParam", i0));
    }
    public int actBackCount() {
        return count(actBackCounts, getForKey("actBack"));
    }
    public void actBack(int i0, String ifSign, String code4RequestFullName, String code, String methodName) {
        addForMap(this.actBack, getForKey("actBack", i0), ifSign, code4RequestFullName, code, methodName);
        countAdd(actBackCounts, getForKey("actBack"));
    }
    public int actBackMethodCount() {
        return count(actBackMethodCounts, getForKey("actBackMethod"));
    }
    public void actBackMethod(int i0, String methodName, String params) {
        addForMap(this.actBackMethod, getForKey("actBackMethod", i0), methodName, params);
        countAdd(actBackMethodCounts, getForKey("actBackMethod"));
    }

    public void defaultListAdapterIf(int i0, String adapterName, String adapterFullName) {
        addForMap(this.layout, getIfKey("defaultListAdapter", i0), adapterName, adapterName, adapterFullName);
        layoutIfs.put(getIfKey("defaultListAdapter", i0), true);
    }
    public void defaultListMoreAdapterIf(int i0, String adapterName, String adapterFullName) {
        addForMap(this.layout, getIfKey("defaultListMoreAdapter", i0), adapterName, adapterName, adapterFullName, adapterName);
        layoutIfs.put(getIfKey("defaultListMoreAdapter", i0), true);
    }
    public void layoutIf(String inflateToolFullName, String layout) {
        addForMap(this.layout, getIfKey("layout"), inflateToolFullName, layout);
        layoutIfs.put(getIfKey("layout"), true);
    }
    public void isSuperOnClick(boolean is) {
        superOnClickIfs.put(getIfKey("superOnClick"), is);
    }
    public void onClickSwitchParamsIf(int i0, String divider) {
        addForMap(this.onClickSwith, getIfKey("onClickSwitchParams", i0), divider);
        onClickSwithIfs.put(getIfKey("onClickSwitchParams", i0), true);
    }
    public void isSuperAccept(boolean is) {
        superAcceptIfs.put(getIfKey("superAccept"), is);
    }
    public void isActBackParamDivider(int i0, int i1, boolean is) {
        actBackIfs.put(getIfKey("actBackParamDivider", i0, i1), is);
    }
    public void toastDialogIf(String toastDialogFullName, String layout, String onHiddenFinishedFullName, String handlerToolFullName) {
        addForMap(this.toastDialog, getIfKey("toastDialog"), toastDialogFullName, toastDialogFullName, toastDialogFullName, layout, toastDialogFullName, onHiddenFinishedFullName, handlerToolFullName, onHiddenFinishedFullName, handlerToolFullName);
        toastDialogIfs.put(getIfKey("toastDialog"), true);
    }

    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < count(fieldCounts, getForKey("field")); i0++) {
            List<String> field0 = field.get(getForKey("field", i0));
            addLnTag(fieldSb, "    [sign] [type] [name];", field0.get(0), field0.get(1), field0.get(2));
        }
        if (isIf(layoutIfs, getIfKey("layout"))) {
            List<String> layout0 = layout.get(getIfKey("layout"));
            addLnTag(layoutSb, "    @Nullable");
            addLnTag(layoutSb, "    @Override");
            addLnTag(layoutSb, "    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {");
            addLnTag(layoutSb, "        View view = [inflateToolFullName].inflate(inflater, [layout], container);", layout0.get(0), layout0.get(1));
            for (int i0 = 0; i0 < count(layoutCounts, getForKey("findView")); i0++) {
                List<String> layout1 = layout.get(getForKey("findView", i0));
                addLnTag(layoutSb, "        [fieldName] = [parent]findViewById([rPkg].R.id.[id]);", layout1.get(0), layout1.get(1), layout1.get(2), layout1.get(3));
            }
            for (int i0 = 0; i0 < count(layoutCounts, getForKey("setOnClick")); i0++) {
                List<String> layout1 = layout.get(getForKey("setOnClick", i0));
                addLnTag(layoutSb, "        [fieldName].setOnClickListener(this);", layout1.get(0));
            }
            for (int i0 = 0; i0 < count(layoutCounts, getForKey("colorStrInit")); i0++) {
                List<String> layout1 = layout.get(getForKey("colorStrInit", i0));
                addLnTag(layoutSb, "        [name] = android.graphics.Color.parseColor(\"[color]\");", layout1.get(0), layout1.get(1));
            }
            for (int i0 = 0; i0 < count(layoutCounts, getForKey("colorResInit")); i0++) {
                List<String> layout1 = layout.get(getForKey("colorResInit", i0));
                addLnTag(layoutSb, "        [name] = [resourceToolFullName].getColor([id]);", layout1.get(0), layout1.get(1), layout1.get(2));
            }
            for (int i0 = 0; i0 < count(layoutCounts, getForKey("dpInit")); i0++) {
                List<String> layout1 = layout.get(getForKey("dpInit", i0));
                addLnTag(layoutSb, "        [name] = [mobileToolFullName].dpToPx([value]);", layout1.get(0), layout1.get(1), layout1.get(2));
            }
            for (int i0 = 0; i0 < count(layoutCounts, getForKey("dimenInit")); i0++) {
                List<String> layout1 = layout.get(getForKey("dimenInit", i0));
                addLnTag(layoutSb, "        [name] = [resourceToolFullName].getDimen([id]);", layout1.get(0), layout1.get(1), layout1.get(2));
            }
            for (int i0 = 0; i0 < count(layoutCounts, getForKey("startInit")); i0++) {
                List<String> layout1 = layout.get(getForKey("startInit", i0));
                addLnTag(layoutSb, "        [name] = [passFullName].[name](getIntent());", layout1.get(0), layout1.get(1), layout1.get(2));
            }
            addLnTag(layoutSb, "");
            for (int i0 = 0; i0 < count(layoutCounts, getForKey("listAdapter")); i0++) {
                List<String> layout1 = layout.get(getForKey("listAdapter", i0));
                if (isIf(layoutIfs, getIfKey("defaultListAdapter", i0))) {
                    List<String> layout2 = layout.get(getIfKey("defaultListAdapter", i0));
                    addLnTag(layoutSb, "        // [adapterName]", layout2.get(0));
                    addLnTag(layoutSb, "        [adapterName] = new [adapterFullName]();", layout2.get(1), layout2.get(2));
                }
                if (isIf(layoutIfs, getIfKey("defaultListMoreAdapter", i0))) {
                    List<String> layout2 = layout.get(getIfKey("defaultListMoreAdapter", i0));
                    addLnTag(layoutSb, "        // [adapterName]", layout2.get(0));
                    addLnTag(layoutSb, "        [adapterName] = new [adapterFullName]() {", layout2.get(1), layout2.get(2));
                    addLnTag(layoutSb, "            @Override");
                    addLnTag(layoutSb, "            protected void loadMore(int page) {");
                    addLnTag(layoutSb, "                [adapterName]LoadMore(page);", layout2.get(3));
                    addLnTag(layoutSb, "            }");
                    addLnTag(layoutSb, "        };");
                }
                addLnTag(layoutSb, "        [adapterName].setVH([vhFullName].class);", layout1.get(0), layout1.get(1));
                addLnTag(layoutSb, "        [adapterName].setClick(this);", layout1.get(2));
                addLnTag(layoutSb, "        [rvName].setAdapter([adapterName]);", layout1.get(3), layout1.get(4));
                addLnTag(layoutSb, "        [rvName].setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getAct()));", layout1.get(5));
            }
            addLnTag(layoutSb, "        return view;");
            addLnTag(layoutSb, "    }");
        }
        if (isIf(superOnClickIfs, getIfKey("superOnClick"))) {
            List<String> superOnClick0 = superOnClick.get(getIfKey("superOnClick"));
            addLnTag(superOnClickSb, "        super.onClick(v);");
        }
        for (int i0 = 0; i0 < count(onClickSwithCounts, getForKey("onClickSwith")); i0++) {
            List<String> onClickSwith0 = onClickSwith.get(getForKey("onClickSwith", i0));
            for (int i1 = 0; i1 < count(onClickSwithCounts, getForKey("onClickCase", i0)); i1++) {
                List<String> onClickSwith1 = onClickSwith.get(getForKey("onClickCase", i0, i1));
                addLnTag(onClickSwithSb, "            case [id]:", onClickSwith1.get(0));
            }
            addLnTag(onClickSwithSb, "                [methodName](", onClickSwith0.get(0));
            if (isIf(onClickSwithIfs, getIfKey("onClickSwitchParams", i0))) {
                List<String> onClickSwith1 = onClickSwith.get(getIfKey("onClickSwitchParams", i0));
                addLnTag(onClickSwithSb, "                        v[divider]", onClickSwith1.get(0));
            }
            for (int i1 = 0; i1 < count(onClickSwithCounts, getForKey("onClickSwitchParams", i0)); i1++) {
                List<String> onClickSwith1 = onClickSwith.get(getForKey("onClickSwitchParams", i0, i1));
                addLnTag(onClickSwithSb, "                        ([type]) v.getTag([pkg].R.id.tag_[index])[divider]", onClickSwith1.get(0), onClickSwith1.get(1), onClickSwith1.get(2), onClickSwith1.get(3));
            }
            addLnTag(onClickSwithSb, "                );");
            addLnTag(onClickSwithSb, "                break;");
        }
        for (int i0 = 0; i0 < count(onClickMethodsCounts, getForKey("onClickMethods")); i0++) {
            List<String> onClickMethods0 = onClickMethods.get(getForKey("onClickMethods", i0));
            addLnTag(onClickMethodsSb, "    protected void [methodName]([params]) {}", onClickMethods0.get(0), onClickMethods0.get(1));
        }
        for (int i0 = 0; i0 < count(loadMoreCounts, getForKey("loadMore")); i0++) {
            List<String> loadMore0 = loadMore.get(getForKey("loadMore", i0));
            addLnTag(loadMoreSb, "    protected abstract void [adapterName]LoadMore(int page);", loadMore0.get(0));
        }
        if (isIf(superAcceptIfs, getIfKey("superAccept"))) {
            List<String> superAccept0 = superAccept.get(getIfKey("superAccept"));
            addLnTag(superAcceptSb, "        super.accept(code, result, params, objs);");
        }
        for (int i0 = 0; i0 < count(acceptCounts, getForKey("accept")); i0++) {
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
        for (int i0 = 0; i0 < count(acceptMethodCounts, getForKey("acceptMethod")); i0++) {
            List<String> acceptMethod0 = acceptMethod.get(getForKey("acceptMethod", i0));
            addLnTag(acceptMethodSb, "    protected void [methodName]([params]) {}", acceptMethod0.get(0), acceptMethod0.get(1));
        }
        for (int i0 = 0; i0 < count(actBackCounts, getForKey("actBack")); i0++) {
            List<String> actBack0 = actBack.get(getForKey("actBack", i0));
            addLnTag(actBackSb, "            [ifSign] (requestCode == [code4RequestFullName].[code]) {", actBack0.get(0), actBack0.get(1), actBack0.get(2));
            StringBuilder actBackParamSb = new StringBuilder();
            for (int i1 = 0; i1 < count(actBackCounts, getForKey("actBackParam", i0)); i1++) {
                List<String> actBack1 = actBack.get(getForKey("actBackParam", i0, i1));
                StringBuilder actBackParamDividerSb = new StringBuilder();
                if (isIf(actBackIfs, getIfKey("actBackParamDivider", i0, i1))) {
                    List<String> actBack2 = actBack.get(getIfKey("actBackParamDivider", i0, i1));
                    addTag(actBackParamDividerSb, ", ");
                }
                addTag(actBackParamSb, "[passFullName].[name](data)[actBackParamDivider]", actBack1.get(0), actBack1.get(1), actBackParamDividerSb.toString());
            }
            addLnTag(actBackSb, "                [methodName]([actBackParam]);", actBack0.get(3), actBackParamSb.toString());
            addLnTag(actBackSb, "            }");
        }
        for (int i0 = 0; i0 < count(actBackMethodCounts, getForKey("actBackMethod")); i0++) {
            List<String> actBackMethod0 = actBackMethod.get(getForKey("actBackMethod", i0));
            addLnTag(actBackMethodSb, "    protected void [methodName]([params]) {}", actBackMethod0.get(0), actBackMethod0.get(1));
        }
        if (isIf(toastDialogIfs, getIfKey("toastDialog"))) {
            List<String> toastDialog0 = toastDialog.get(getIfKey("toastDialog"));
            addLnTag(toastDialogSb, "    private [toastDialogFullName] toastDialog;", toastDialog0.get(0));
            addLnTag(toastDialogSb, "");
            addLnTag(toastDialogSb, "    protected [toastDialogFullName] getToastDialog() {", toastDialog0.get(1));
            addLnTag(toastDialogSb, "        if (toastDialog == null)");
            addLnTag(toastDialogSb, "            toastDialog = new [toastDialogFullName](getAct())", toastDialog0.get(2));
            addLnTag(toastDialogSb, "                    .setLayout([layout])", toastDialog0.get(3));
            addLnTag(toastDialogSb, "                    .build();");
            addLnTag(toastDialogSb, "        return toastDialog;");
            addLnTag(toastDialogSb, "    }");
            addLnTag(toastDialogSb, "    protected void toastShow(String msg) {");
            addLnTag(toastDialogSb, "        [toastDialogFullName] td = getToastDialog();", toastDialog0.get(4));
            addLnTag(toastDialogSb, "        td.setContent(msg);");
            addLnTag(toastDialogSb, "        if (!td.isShow()) {");
            addLnTag(toastDialogSb, "            td.show();");
            addLnTag(toastDialogSb, "        }");
            addLnTag(toastDialogSb, "    }");
            addLnTag(toastDialogSb, "    protected void toastShow(long time, String msg, [onHiddenFinishedFullName] onHiddenFinished) {", toastDialog0.get(5));
            addLnTag(toastDialogSb, "        toastShow(msg);");
            addLnTag(toastDialogSb, "        [handlerToolFullName].getMainHandler().postDelayed(new java.lang.Runnable() {", toastDialog0.get(6));
            addLnTag(toastDialogSb, "            @Override");
            addLnTag(toastDialogSb, "            public void run() {");
            addLnTag(toastDialogSb, "                getToastDialog().hidden(onHiddenFinished);");
            addLnTag(toastDialogSb, "            }");
            addLnTag(toastDialogSb, "        }, time);");
            addLnTag(toastDialogSb, "    }");
            addLnTag(toastDialogSb, "");
            addLnTag(toastDialogSb, "    protected void toastShow(long time, String msg) {");
            addLnTag(toastDialogSb, "        toastShow(time, msg, null);");
            addLnTag(toastDialogSb, "    }");
            addLnTag(toastDialogSb, "");
            addLnTag(toastDialogSb, "    protected void toastHidden(long time, String msg, [onHiddenFinishedFullName] onHiddenFinished) {", toastDialog0.get(7));
            addLnTag(toastDialogSb, "        getToastDialog().setContent(msg);");
            addLnTag(toastDialogSb, "        [handlerToolFullName].getMainHandler().postDelayed(new java.lang.Runnable() {", toastDialog0.get(8));
            addLnTag(toastDialogSb, "            @Override");
            addLnTag(toastDialogSb, "            public void run() {");
            addLnTag(toastDialogSb, "                getToastDialog().hidden(onHiddenFinished);");
            addLnTag(toastDialogSb, "            }");
            addLnTag(toastDialogSb, "        }, time);");
            addLnTag(toastDialogSb, "    }");
            addLnTag(toastDialogSb, "");
            addLnTag(toastDialogSb, "    protected void toastHidden(long time, String msg) {");
            addLnTag(toastDialogSb, "        toastHidden(time, msg, null);");
            addLnTag(toastDialogSb, "    }");
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import android.os.Bundle;");
        lines.add("import android.view.LayoutInflater;");
        lines.add("import android.view.View;");
        lines.add("import android.view.ViewGroup;");
        lines.add("");
        lines.add("import androidx.annotation.NonNull;");
        lines.add("import androidx.annotation.Nullable;");
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
        lines.add("    public void onCreate(@Nullable Bundle savedInstanceState) {");
        lines.add("        super.onCreate(savedInstanceState);");
        lines.add("");
        lines.add("    }");
        lines.add("[[layout]]");
        lines.add("");
        lines.add("    @Override");
        lines.add("    public void onClick(View v) {");
        lines.add("[[superOnClick]]");
        lines.add("        switch (v.getId()) {");
        lines.add("[[onClickSwith]]");
        lines.add("        }");
        lines.add("    }");
        lines.add("[[onClickMethods]]");
        lines.add("[[loadMore]]");
        lines.add("    @Override");
        lines.add("    public void accept(String code, Result<ResponseBody> result, [[coreSendParamsFullName]] params, List objs) {");
        lines.add("[[superAccept]]");
        lines.add("");
        lines.add("[[accept]]");
        lines.add("    }");
        lines.add("[[acceptMethod]]");
        lines.add("    @Override");
        lines.add("    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {");
        lines.add("        super.onActivityResult(requestCode, resultCode, data);");
        lines.add("        if (resultCode == android.app.Activity.RESULT_OK) {");
        lines.add("[[actBack]]");
        lines.add("        }");
        lines.add("    }");
        lines.add("[[actBackMethod]]");
        lines.add("[[toastDialog]]");
        lines.add("}");

        return lines;
    }
}
