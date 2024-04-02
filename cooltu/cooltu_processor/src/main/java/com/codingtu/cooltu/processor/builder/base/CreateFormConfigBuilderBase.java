package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class CreateFormConfigBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder beanFullName;
    protected StringBuilder bindFullName;
    protected StringBuilder baseFullName;
    protected StringBuilder bindName;
    protected StringBuilder beanName;
    protected StringBuilder bindConfigClassName;
    protected StringBuilder baseClassName;

    public CreateFormConfigBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        beanFullName = map.get("beanFullName");
        bindFullName = map.get("bindFullName");
        baseFullName = map.get("baseFullName");
        bindName = map.get("bindName");
        beanName = map.get("beanName");
        bindConfigClassName = map.get("bindConfigClassName");
        baseClassName = map.get("baseClassName");

    }



    @Override
    protected void dealLinesInParent() {

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import [[beanFullName]];");
        lines.add("import [[bindFullName]];");
        lines.add("");
        lines.add("import [[baseFullName]];");
        lines.add("");
        lines.add("@[[bindName]]([[beanName]].class)");
        lines.add("public class [[bindConfigClassName]] extends [[baseClassName]] {");
        lines.add("}");

        return lines;
    }
}
