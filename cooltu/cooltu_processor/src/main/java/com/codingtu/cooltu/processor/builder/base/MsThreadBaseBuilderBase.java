package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class MsThreadBaseBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder interfaceType;
    protected StringBuilder msThreadType;
    protected StringBuilder msThreadName;
    protected StringBuilder methods;

    public MsThreadBaseBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        interfaceType = map.get("interfaceType");
        msThreadType = map.get("msThreadType");
        msThreadName = map.get("msThreadName");
        methods = map.get("methods");

    }



    @Override
    protected void dealLinesInParent() {

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("public class [[name]] implements [[interfaceType]] {");
        lines.add("");
        lines.add("    protected [[msThreadType]] [[msThreadName]];");
        lines.add("");
        lines.add("    public void start() {");
        lines.add("        [[msThreadName]] = [[msThreadType]].obtain().dealer(this);");
        lines.add("        [[msThreadName]].start();");
        lines.add("    }");
        lines.add("");
        lines.add("[[methods]]");
        lines.add("}");

        return lines;
    }
}
