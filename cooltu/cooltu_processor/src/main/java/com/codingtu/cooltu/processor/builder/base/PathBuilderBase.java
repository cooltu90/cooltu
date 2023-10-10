package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class PathBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder basePath;
    private java.util.Map<String, Boolean> obtainIfs;
    private java.util.Map<String, Integer> obtainCounts;
    private StringBuilder obtainSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> obtain;

    public PathBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        basePath = map.get("basePath");
        obtainIfs = new java.util.HashMap<>();
        obtainCounts = new java.util.HashMap<>();
        obtainSb = map.get("obtain");
        obtain = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }
    protected void addObtainRootCount(int count) {
        obtainCounts.put(getForKey(), count);
    }

    protected void addObtainRoot(int i0, String s0) {
        addForMap(obtain, getForKey(i0), s0);
    }

    protected void obtainIf(boolean is) {
        obtainIfs.put(getIfKey("obtain"), is);
    }
    protected void obtain(String s0, String s1, String s2) {
        addForMap(obtain, getIfKey("obtain"), s0, s1, s2);
    }

    @Override
    protected void dealLinesInParent() {
        if (obtainIfs.get(getIfKey("obtain"))) {
            List<String> obtain0 = obtain.get(getIfKey("obtain"));
            addLnTag(obtainSb, "    public static [name] obtain([params]) {", obtain0.get(0), obtain0.get(1));
            addLnTag(obtainSb, "        return root([SDCardTool].getSDCard()", obtain0.get(2));
            for (int i0 = 0; i0 < obtainCounts.get(getForKey()); i0++) {
                List<String> obtain1 = obtain.get(getForKey(i0));
                addLnTag(obtainSb, "                + addPrexSeparator([path])", obtain1.get(0));
            }
            addLnTag(obtainSb, "        );");
            addLnTag(obtainSb, "    }");
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("public class [[name]] extends [[basePath]] {");
        lines.add("    public [[name]](String root) {");
        lines.add("        super(root);");
        lines.add("    }");
        lines.add("");
        lines.add("    public static [[name]] root(String root) {");
        lines.add("        return new [[name]](root);");
        lines.add("    }");
        lines.add("[[obtain]]");
        lines.add("}");

        return lines;
    }
}
