package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class PermissionBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    private java.util.Map<String, Boolean> fieldIfs;
    private java.util.Map<String, Integer> fieldCounts;
    private StringBuilder fieldSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> field;
    private java.util.Map<String, Boolean> methodIfs;
    private java.util.Map<String, Integer> methodCounts;
    private StringBuilder methodSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> method;

    public PermissionBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
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
        count(fieldCounts, getForKey("field"));
    }
    protected void methodCount(int count) {
        methodCounts.put(getForKey("method"), count);
    }
    protected void methodCountAdd() {
        count(methodCounts, getForKey("method"));
    }
    protected void permissionCount(int i0, int count) {
        methodCounts.put(getForKey("permission", i0), count);
    }
    protected void permissionCountAdd(int i0) {
        count(methodCounts, getForKey("permission", i0));
    }

    protected void field(int i0, String methodName, String act, String value) {
        addForMap(this.field, getForKey("field", i0), methodName, act, value);
    }
    protected void permission(int i0, int i1, String value) {
        addForMap(this.method, getForKey("permission", i0, i1), value);
    }
    protected void method(int i0, String methodName, String act, String permissionToolFullName, String methodNameStatic, String actStatic) {
        addForMap(this.method, getForKey("method", i0), methodName, act, permissionToolFullName, methodNameStatic, actStatic);
    }


    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < fieldCounts.get(getForKey("field")); i0++) {
            List<String> field0 = field.get(getForKey("field", i0));
            addLnTag(fieldSb, "    public static final int CODE_[methodName]_IN_[act] = [value];", field0.get(0), field0.get(1), field0.get(2));
        }
        for (int i0 = 0; i0 < methodCounts.get(getForKey("method")); i0++) {
            List<String> method0 = method.get(getForKey("method", i0));
            addLnTag(methodSb, "    public static void [methodName]In[act](Activity act) {", method0.get(0), method0.get(1));
            addLnTag(methodSb, "        [permissionToolFullName].check(act, CODE_[methodNameStatic]_IN_[actStatic]", method0.get(2), method0.get(3), method0.get(4));
            for (int i1 = 0; i1 < methodCounts.get(getForKey("permission", i0)); i1++) {
                List<String> method1 = method.get(getForKey("permission", i0, i1));
                addLnTag(methodSb, "                , \"[value]\"", method1.get(0));
            }
            addLnTag(methodSb, "        );");
            addLnTag(methodSb, "    }");
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import android.app.Activity;");
        lines.add("");
        lines.add("public class Permissions {");
        lines.add("[[field]]");
        lines.add("[[method]]");
        lines.add("}");

        return lines;
    }
}
