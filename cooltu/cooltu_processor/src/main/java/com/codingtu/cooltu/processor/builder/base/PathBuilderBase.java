package com.codingtu.cooltu.processor.builder.base;

import com.codingtu.cooltu.processor.lib.tools.TagTools;

import java.util.ArrayList;
import java.util.List;

public abstract class PathBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder basePath;
    protected boolean isObtainMethod;
    private StringBuilder obtainMethodSb;
    private StringBuilder initParamsSb;
    protected com.codingtu.cooltu.processor.lib.param.Params initParams;
    protected StringBuilder SDCardTool;

    public PathBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        basePath = map.get("basePath");
        initParamsSb = map.get("initParamsSb");
        initParams = new com.codingtu.cooltu.processor.lib.param.Params();
        SDCardTool = map.get("SDCardTool");

    }

    @Override
    protected void dealLinesInParent() {
        initParamsSb.append(initParams.getMethodParams());
        if (isObtainMethod) {
            addLnTag(obtainMethodSb, "    public static [name] obtain([initParamsSb]) {", name.toString(), initParamsSb.toString());
            addLnTag(obtainMethodSb, "        return root([[SDCardTool]].getSDCard()", SDCardTool.toString());
        }
    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("public class [[name]] extends [[basePath]] {");
        lines.add("");
        lines.add("[sub[if[obtainMethod");
        lines.add("    public static [[name]] obtain([[initParamsSb]]) {");
        lines.add("        return root([[SDCardTool]].getSDCard()");
        lines.add("[sub[for[obtainMethodRoot");
        lines.add("                + addPrexSeparator([value])");
        lines.add("]sub]for]obtainMethodRoot");
        lines.add("        );");
        lines.add("    }");
        lines.add("]sub]if]obtainMethod");
        lines.add("");
        lines.add("    public static [[name]] root(String root) {");
        lines.add("        return new [[name]](root);");
        lines.add("    }");
        lines.add("");
        lines.add("    public [[name]](String root) {");
        lines.add("        super(root);");
        lines.add("    }");
        lines.add("}");

        return lines;
    }
}
