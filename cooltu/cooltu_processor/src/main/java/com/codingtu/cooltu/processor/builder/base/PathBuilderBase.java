package com.codingtu.cooltu.processor.builder.base;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import java.util.ArrayList;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import java.util.List;
public abstract class PathBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder basePath;
    private StringBuilder initParamsSb;
    protected Params initParams;
    protected StringBuilder SDCardTool;
    private StringBuilder obtainMethodSb;
    protected ListValueMap<Integer, String> obtainMethod;
    public PathBuilderBase(JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        basePath = map.get("basePath");
        initParamsSb = map.get("initParamsSb");
        initParams = new Params();
        SDCardTool = map.get("SDCardTool");
        obtainMethodSb = map.get("obtainMethodSb");
        obtainMethod = new ListValueMap<>();

    }
    @Override
    protected void dealLinesInParent() {
        initParamsSb.append(initParams.getMethodParams());
        for (int i = 0; i < CountTool.count(obtainMethod); i++) {
            List<String> lines = obtainMethod.get(i);
            addLnTag(obtainMethodSb, "                + addPrexSeparator([value])", lines.get(0));
        }

    }
    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("public class [[name]] extends [[basePath]] {");
        lines.add("");
        lines.add("    public static [[name]] obtain([[initParamsSb]]) {");
        lines.add("        return root([[SDCardTool]].getSDCard()");
        lines.add("[[obtainMethodSb]]");
        lines.add("        );");
        lines.add("    }");
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

