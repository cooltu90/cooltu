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
    private java.util.Map<String, Boolean> onCreateIfs;
    private java.util.Map<String, Integer> onCreateCounts;
    private StringBuilder onCreateSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> onCreate;

    public ActBaseBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        baseClass = map.get("baseClass");
        viewIfs = new java.util.HashMap<>();
        viewCounts = new java.util.HashMap<>();
        viewSb = map.get("view");
        view = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        onCreateIfs = new java.util.HashMap<>();
        onCreateCounts = new java.util.HashMap<>();
        onCreateSb = map.get("onCreate");
        onCreate = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }
    protected void viewCount(int count) {
        viewCounts.put(getForKey("view"), count);
    }

    protected void view(int i0, String type, String name) {
        addForMap(view, getForKey("view", i0), type, name);
    }

    protected void onCreateIf(boolean is) {
        onCreateIfs.put(getIfKey("onCreate"), is);
    }
    protected void onCreateIf(String layout) {
        addForMap(onCreate, getIfKey("onCreate"), layout);
    }

    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < viewCounts.get(getForKey("view")); i0++) {
            List<String> view0 = view.get(getForKey("view", i0));
            addLnTag(viewSb, "    protected [type] [name];", view0.get(0), view0.get(1));
        }
        if (onCreateIfs.get(getIfKey("onCreate"))) {
            List<String> onCreate0 = onCreate.get(getIfKey("onCreate"));
            addLnTag(onCreateSb, "    @Override");
            addLnTag(onCreateSb, "    protected void onCreate(android.os.Bundle savedInstanceState) {");
            addLnTag(onCreateSb, "        super.onCreate(savedInstanceState);");
            addLnTag(onCreateSb, "        setContentView([layout]);", onCreate0.get(0));
            addLnTag(onCreateSb, "    }");
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
        lines.add("");
        lines.add("[[onCreate]]");
        lines.add("");
        lines.add("    @Override");
        lines.add("    public void onClick(View v) {");
        lines.add("");
        lines.add("    }");
        lines.add("}");
        lines.add("");

        return lines;
    }
}
