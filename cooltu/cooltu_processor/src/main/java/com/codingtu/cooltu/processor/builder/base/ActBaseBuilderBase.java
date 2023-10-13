package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class ActBaseBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder baseClass;
    private java.util.Map<String, Boolean> viewIfs;
    private java.util.Map<String, Integer> viewCounts;
    private StringBuilder viewSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> view;
    private java.util.Map<String, Boolean> inBaseIfs;
    private java.util.Map<String, Integer> inBaseCounts;
    private StringBuilder inBaseSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> inBase;
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

    public ActBaseBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        baseClass = map.get("baseClass");
        viewIfs = new java.util.HashMap<>();
        viewCounts = new java.util.HashMap<>();
        viewSb = map.get("view");
        view = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        inBaseIfs = new java.util.HashMap<>();
        inBaseCounts = new java.util.HashMap<>();
        inBaseSb = map.get("inBase");
        inBase = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
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

    }
    protected void viewCount(int count) {
        viewCounts.put(getForKey("view"), count);
    }
    protected void inBaseCount(int count) {
        inBaseCounts.put(getForKey("inBase"), count);
    }
    protected void findViewCount(int count) {
        findViewCounts.put(getForKey("findView"), count);
    }
    protected void setOnClickCount(int count) {
        setOnClickCounts.put(getForKey("setOnClick"), count);
    }

    protected void view(int i0, String type, String name) {
        addForMap(this.view, getForKey("view", i0), type, name);
    }
    protected void inBase(int i0, String type, String name) {
        addForMap(this.inBase, getForKey("inBase", i0), type, name);
    }
    protected void findView(int i0, String fieldName, String parent, String rPkg, String id) {
        addForMap(this.findView, getForKey("findView", i0), fieldName, parent, rPkg, id);
    }
    protected void setOnClick(int i0, String fieldName) {
        addForMap(this.setOnClick, getForKey("setOnClick", i0), fieldName);
    }

    protected void layoutIf(boolean is) {
        layoutIfs.put(getIfKey("layout"), is);
    }
    protected void layoutIf(String layout) {
        addForMap(this.layout, getIfKey("layout"), layout);
    }

    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < viewCounts.get(getForKey("view")); i0++) {
            List<String> view0 = view.get(getForKey("view", i0));
            addLnTag(viewSb, "    protected [type] [name];", view0.get(0), view0.get(1));
        }
        for (int i0 = 0; i0 < inBaseCounts.get(getForKey("inBase")); i0++) {
            List<String> inBase0 = inBase.get(getForKey("inBase", i0));
            addLnTag(inBaseSb, "    protected [type] [name];", inBase0.get(0), inBase0.get(1));
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

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import android.view.View;");
        lines.add("");
        lines.add("public abstract class [[name]] extends [[baseClass]] implements View.OnClickListener {");
        lines.add("");
        lines.add("[[view]]");
        lines.add("[[inBase]]");
        lines.add("");
        lines.add("    @Override");
        lines.add("    protected void onCreate(android.os.Bundle savedInstanceState) {");
        lines.add("        super.onCreate(savedInstanceState);");
        lines.add("[[layout]]");
        lines.add("[[findView]]");
        lines.add("[[setOnClick]]");
        lines.add("    }");
        lines.add("    @Override");
        lines.add("    public void onClick(View v) {");
        lines.add("");
        lines.add("    }");
        lines.add("}");
        lines.add("");

        return lines;
    }
}
