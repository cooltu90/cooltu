package com.codingtu.cooltu.processor.builder.base;

import java.util.ArrayList;
import java.util.List;

public abstract class TestBuilderBaseTemp extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    private java.util.Map<String, Integer> linesCounts;
    private java.util.Map<String, Boolean> linesIfs;
    private StringBuilder linesSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> lines;


    public TestBuilderBaseTemp(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        linesCounts = new java.util.HashMap<>();
        linesSb = map.get("lines");
        lines = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }

    protected void linesCount(int count) {
        linesCounts.put(getForKey(), count);
    }

    protected void linesAddCount(int i0, int count) {
        linesCounts.put(getForKey(i0), count);
    }

    protected void lines(int i0, String s0, String s1) {
        addForMap(lines, getForKey(i0), s0, s1);
    }

    protected void linesAdd1If(int i0, int i1, boolean is) {
        linesIfs.put(getForKey(i0, i1), is);
    }

    protected void linesAdd1(int i0, int i1, String s1, String s2, String s3) {

    }

    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < linesCounts.get(getForKey()); i0++) {
            List<String> lines0 = lines.get(getForKey(i0));
            addLnTag(linesSb, "        ArrayList<String> [name] = new ArrayList<>();", lines0.get(0));
            for (int i1 = 0; i1 < linesCounts.get(getForKey(i0)); i1++) {
                List<String> lines1 = lines.get(getForKey(i0, i1));
                if (linesIfs.get(getForKey(i0, i1))) {
                    List<String> linesIf2 = lines.get(getIfKey("", i0, i1));
                    addLnTag(linesSb, "        [name].add(\"[value][v1]\");", linesIf2.get(0));
                }
            }
            addLnTag(linesSb, "        strs.add([name]);", lines0.get(1));
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
