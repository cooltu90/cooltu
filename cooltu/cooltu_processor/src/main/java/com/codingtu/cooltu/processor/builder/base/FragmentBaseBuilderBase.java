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
    protected StringBuilder coreSendParamsFullName;

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
        coreSendParamsFullName = map.get("coreSendParamsFullName");

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
        lines.add("public class [[name]] extends [[baseClass]] implements View.OnClickListener, [[netBackIFullName]] {");
        lines.add("[[field]]");
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
        lines.add("");
        lines.add("    @Override");
        lines.add("    public void accept(String code, Result<ResponseBody> result, [[coreSendParamsFullName]] params, List objs) {");
        lines.add("");
        lines.add("    }");
        lines.add("");
        lines.add("}");

        return lines;
    }
}
