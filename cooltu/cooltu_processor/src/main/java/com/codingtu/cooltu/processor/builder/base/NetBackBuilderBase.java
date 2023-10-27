package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class NetBackBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder name;
    protected StringBuilder netBackFullName;
    private java.util.Map<String, Boolean> fieldIfs;
    private java.util.Map<String, Integer> fieldCounts;
    private StringBuilder fieldSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> field;
    protected StringBuilder coreSendParamsFullName;
    private java.util.Map<String, Boolean> acceptIfs;
    private java.util.Map<String, Integer> acceptCounts;
    private StringBuilder acceptSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> accept;

    public NetBackBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        name = map.get("name");
        netBackFullName = map.get("netBackFullName");
        fieldIfs = new java.util.HashMap<>();
        fieldCounts = new java.util.HashMap<>();
        fieldSb = map.get("field");
        field = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        coreSendParamsFullName = map.get("coreSendParamsFullName");
        acceptIfs = new java.util.HashMap<>();
        acceptCounts = new java.util.HashMap<>();
        acceptSb = map.get("accept");
        accept = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }


    protected void fieldIf(boolean is) {
        fieldIfs.put(getIfKey("field"), is);
    }
    protected void fieldIf(String type, String name) {
        addForMap(this.field, getIfKey("field"), type, name);
    }
    protected void acceptIf(boolean is) {
        acceptIfs.put(getIfKey("accept"), is);
    }
    protected void acceptIf(String stringToolFullName, String name, String jsonToolFullName, String method, String type) {
        addForMap(this.accept, getIfKey("accept"), stringToolFullName, name, jsonToolFullName, method, type);
    }

    @Override
    protected void dealLinesInParent() {
        if (fieldIfs.get(getIfKey("field"))) {
            List<String> field0 = field.get(getIfKey("field"));
            addLnTag(fieldSb, "    public [type] [name];", field0.get(0), field0.get(1));
        }
        if (acceptIfs.get(getIfKey("accept"))) {
            List<String> accept0 = accept.get(getIfKey("accept"));
            addLnTag(acceptSb, "        if ([stringToolFullName].isNotBlank(json)) {", accept0.get(0));
            addLnTag(acceptSb, "            [name] = [jsonToolFullName].[method]([type].class, json);", accept0.get(1), accept0.get(2), accept0.get(3), accept0.get(4));
            addLnTag(acceptSb, "        }");
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package core.net.back;");
        lines.add("");
        lines.add("import java.util.List;");
        lines.add("import okhttp3.ResponseBody;");
        lines.add("import retrofit2.adapter.rxjava2.Result;");
        lines.add("");
        lines.add("public class [[name]] extends [[netBackFullName]] {");
        lines.add("[[field]]");
        lines.add("    @Override");
        lines.add("    public void accept(String code, Result<ResponseBody> result, [[coreSendParamsFullName]] params, List objs) {");
        lines.add("        super.accept(code, result, params, objs);");
        lines.add("[[accept]]");
        lines.add("");
        lines.add("    }");
        lines.add("");
        lines.add("}");

        return lines;
    }
}
