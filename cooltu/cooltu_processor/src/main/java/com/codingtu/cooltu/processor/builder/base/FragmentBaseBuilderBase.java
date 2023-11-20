package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class FragmentBaseBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder baseClass;

    public FragmentBaseBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        baseClass = map.get("baseClass");

    }



    @Override
    protected void dealLinesInParent() {

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("public class [[name]] extends [[baseClass]] {");
        lines.add("");
        lines.add("}");

        return lines;
    }
}
