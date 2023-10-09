package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class TestBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    private StringBuilder linesSb;
    private java.util.Map<String, Integer> linesCounts;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> linesMap;
    private java.util.Map<String, Integer> linesAddCounts;

    public TestBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        linesSb = map.get("lines");
        linesCounts = new java.util.HashMap<>();
        linesMap = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        linesAddCounts = new java.util.HashMap<>();

    }
    protected void lines(int i, String... ss) {
        addMapList(linesMap, "for" + i, ss);
    }
    protected void linesCounts(int count) {
        linesCounts.put("for", count);
    }

    @Override
    protected void dealLinesInParent() {
        for (int i1 = 0; i1 < linesCounts.get("for"); i1++) {
            List<String> lines = linesMap.get("for" + i1);
            addLnTag(linesSb, "        ArrayList<String> [name] = new ArrayList<>();", lines.get(0));
            addLnTag(linesSb, "        strs.add([name]);", lines.get(1));
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import java.util.ArrayList;");
        lines.add("import java.util.List;");
        lines.add("");
        lines.add("public class [[name]] {");
        lines.add("");
        lines.add("    private void add() {");
        lines.add("        List<List<String>> strs = new ArrayList<>();");
        lines.add("[[lines]]");
        lines.add("    }");
        lines.add("");
        lines.add("}");

        return lines;
    }
}
