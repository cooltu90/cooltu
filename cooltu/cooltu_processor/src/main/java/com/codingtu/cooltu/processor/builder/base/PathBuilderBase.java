package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;
public abstract class PathBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("public class [[name]] extends [[basePath]] {");
        lines.add("");
        lines.add("}");
        return lines;
    }
}

