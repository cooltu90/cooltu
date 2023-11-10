package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class NetBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected java.util.Map<String, Boolean> fieldIfs;
    protected java.util.Map<String, Integer> fieldCounts;
    protected StringBuilder fieldSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> field;
    protected java.util.Map<String, Boolean> methodIfs;
    protected java.util.Map<String, Integer> methodCounts;
    protected StringBuilder methodSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> method;

    public NetBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        fieldIfs = new java.util.HashMap<>();
        fieldCounts = new java.util.HashMap<>();
        fieldSb = map.get("field");
        field = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        methodIfs = new java.util.HashMap<>();
        methodCounts = new java.util.HashMap<>();
        methodSb = map.get("method");
        method = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }
    protected void fieldCount(int count) {
        fieldCounts.put(getForKey("field"), count);
    }
    protected void fieldCountAdd() {
        countAdd(fieldCounts, getForKey("field"));
    }
    protected void methodCount(int count) {
        methodCounts.put(getForKey("method"), count);
    }
    protected void methodCountAdd() {
        countAdd(methodCounts, getForKey("method"));
    }
    protected void sendParamsSetCount(int i0, int count) {
        methodCounts.put(getForKey("sendParamsSet", i0), count);
    }
    protected void sendParamsSetCountAdd(int i0) {
        countAdd(methodCounts, getForKey("sendParamsSet", i0));
    }
    protected void postJsonBodySetCount(int i0, int count) {
        methodCounts.put(getForKey("postJsonBodySet", i0), count);
    }
    protected void postJsonBodySetCountAdd(int i0) {
        countAdd(methodCounts, getForKey("postJsonBodySet", i0));
    }
    protected void methodParamsCount(int i0, int count) {
        methodCounts.put(getForKey("methodParams", i0), count);
    }
    protected void methodParamsCountAdd(int i0) {
        countAdd(methodCounts, getForKey("methodParams", i0));
    }

    protected void field(int i0, String name, String value) {
        addForMap(this.field, getForKey("field", i0), name, value);
    }
    protected void sendParamsSet(int i0, int i1, String name, String value) {
        addForMap(this.method, getForKey("sendParamsSet", i0, i1), name, value);
    }
    protected void postJsonBodySet(int i0, int i1, String key, String name) {
        addForMap(this.method, getForKey("postJsonBodySet", i0, i1), key, name);
    }
    protected void methodParams(int i0, int i1, String name, String divider) {
        addForMap(this.method, getForKey("methodParams", i0, i1), name, divider);
    }
    protected void method(int i0, String apiFullName, String methodName, String methodParams, String netToolFullName, String serviceFullName, String methodTag, String baseUrl) {
        addForMap(this.method, getForKey("method", i0), apiFullName, methodName, methodParams, netToolFullName, serviceFullName, methodName, methodTag, baseUrl);
    }

    protected void sendParamsIf(int i0, boolean is) {
        methodIfs.put(getIfKey("sendParams", i0), is);
    }
    protected void sendParamsIf(int i0, String sendParamFullName) {
        addForMap(this.method, getIfKey("sendParams", i0), sendParamFullName, sendParamFullName);
    }
    protected void sendParamsGetIf(int i0, boolean is) {
        methodIfs.put(getIfKey("sendParamsGet", i0), is);
    }
    protected void sendParamsGetIf(int i0, String sendParamFullName) {
        addForMap(this.method, getIfKey("sendParamsGet", i0), sendParamFullName, sendParamFullName);
    }
    protected void postJsonBodyIf(int i0, boolean is) {
        methodIfs.put(getIfKey("postJsonBody", i0), is);
    }
    protected void postJsonBodyIf(int i0, String joFullName, String jsonToolFullName) {
        addForMap(this.method, getIfKey("postJsonBody", i0), joFullName, jsonToolFullName);
    }

    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < count(fieldCounts, getForKey("field")); i0++) {
            List<String> field0 = field.get(getForKey("field", i0));
            addLnTag(fieldSb, "    private static final String [name] = \"[value]\";", field0.get(0), field0.get(1));
        }
        for (int i0 = 0; i0 < count(methodCounts, getForKey("method")); i0++) {
            List<String> method0 = method.get(getForKey("method", i0));
            addLnTag(methodSb, "    public static [apiFullName] [methodName]([methodParams]) {", method0.get(0), method0.get(1), method0.get(2));
            if (methodIfs.get(getIfKey("sendParams", i0))) {
                List<String> method1 = method.get(getIfKey("sendParams", i0));
                addLnTag(methodSb, "        [sendParamFullName] params = new [sendParamFullName]();", method1.get(0), method1.get(1));
                for (int i1 = 0; i1 < count(methodCounts, getForKey("sendParamsSet", i0)); i1++) {
                    List<String> method2 = method.get(getForKey("sendParamsSet", i0, i1));
                    addLnTag(methodSb, "        params.[name] = [value];", method2.get(0), method2.get(1));
                }
            }
            addLnTag(methodSb, "        return [netToolFullName].api((retrofit, ps) -> {", method0.get(3));
            if (methodIfs.get(getIfKey("sendParamsGet", i0))) {
                List<String> method1 = method.get(getIfKey("sendParamsGet", i0));
                addLnTag(methodSb, "            [sendParamFullName] paramsGet = ([sendParamFullName]) ps;", method1.get(0), method1.get(1));
            }
            if (methodIfs.get(getIfKey("postJsonBody", i0))) {
                List<String> method1 = method.get(getIfKey("postJsonBody", i0));
                addLnTag(methodSb, "            [joFullName] jo = [jsonToolFullName].createJO();", method1.get(0), method1.get(1));
                for (int i1 = 0; i1 < count(methodCounts, getForKey("postJsonBodySet", i0)); i1++) {
                    List<String> method2 = method.get(getForKey("postJsonBodySet", i0, i1));
                    addLnTag(methodSb, "            jo.put(\"[key]\", paramsGet.[name]);", method2.get(0), method2.get(1));
                }
            }
            addLnTag(methodSb, "");
            addLnTag(methodSb, "            return retrofit.create([serviceFullName].class).[methodName](", method0.get(4), method0.get(5));
            for (int i1 = 0; i1 < count(methodCounts, getForKey("methodParams", i0)); i1++) {
                List<String> method1 = method.get(getForKey("methodParams", i0, i1));
                addLnTag(methodSb, "                    [name][divider]", method1.get(0), method1.get(1));
            }
            addLnTag(methodSb, "            );");
            addLnTag(methodSb, "        }, [methodTag], [baseUrl], params);", method0.get(6), method0.get(7));
            addLnTag(methodSb, "    }");
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("public class Net {");
        lines.add("[[field]]");
        lines.add("[[method]]");
        lines.add("");
        lines.add("}");
        lines.add("");

        return lines;
    }
}
