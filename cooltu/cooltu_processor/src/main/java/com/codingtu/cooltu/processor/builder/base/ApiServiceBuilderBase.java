package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class ApiServiceBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    private java.util.Map<String, Boolean> methodIfs;
    private java.util.Map<String, Integer> methodCounts;
    private StringBuilder methodSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> method;

    public ApiServiceBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        methodIfs = new java.util.HashMap<>();
        methodCounts = new java.util.HashMap<>();
        methodSb = map.get("method");
        method = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }
    protected void methodCount(int count) {
        methodCounts.put(getForKey("method"), count);
    }
    protected void methodParamCount(int i0, int count) {
        methodCounts.put(getForKey("methodParam", i0), count);
    }

    protected void methodParam(int i0, int i1, String anno, String type, String name, String divider) {
        addForMap(this.method, getForKey("methodParam", i0, i1), anno, type, name, divider);
    }
    protected void method(int i0, String netType, String apiUrl, String methodName) {
        addForMap(this.method, getForKey("method", i0), netType, apiUrl, methodName);
    }


    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < methodCounts.get(getForKey("method")); i0++) {
            List<String> method0 = method.get(getForKey("method", i0));
            addLnTag(methodSb, "    @[netType](\"[apiUrl]\")", method0.get(0), method0.get(1));
            addLnTag(methodSb, "    Flowable<Result<ResponseBody>> [methodName](", method0.get(2));
            for (int i1 = 0; i1 < methodCounts.get(getForKey("methodParam", i0)); i1++) {
                List<String> method1 = method.get(getForKey("methodParam", i0, i1));
                addLnTag(methodSb, "            @[anno] [type] [name][divider]", method1.get(0), method1.get(1), method1.get(2), method1.get(3));
            }
            addLnTag(methodSb, "    );");
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import io.reactivex.Flowable;");
        lines.add("import okhttp3.ResponseBody;");
        lines.add("import retrofit2.adapter.rxjava2.Result;");
        lines.add("");
        lines.add("public interface [[name]] {");
        lines.add("[[method]]");
        lines.add("");
        lines.add("}");

        return lines;
    }
}
