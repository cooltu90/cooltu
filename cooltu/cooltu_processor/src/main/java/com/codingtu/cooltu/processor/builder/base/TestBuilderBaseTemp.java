package com.codingtu.cooltu.processor.builder.base;

import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

import java.util.ArrayList;
import java.util.List;

public abstract class TestBuilderBaseTemp extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;

    public TestBuilderBaseTemp(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");

    }

    protected void linesCount(int count) {
        forCounts("for", count);
    }

    protected void linesAddCount(int i1, int count) {
        forCounts("for" + "-" + i1, count);
    }


    @Override
    protected void dealLinesInParent() {
        for (int i = 0; i < forCounts("for"); i++) {

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
        lines.add("    }");
        lines.add("");
        lines.add("}");

        return lines;
    }
}
