package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class FragmentBaseBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder baseClass;
    protected StringBuilder netBackIFullName;
    protected java.util.Map<String, Boolean> fieldIfs;
    protected java.util.Map<String, Integer> fieldCounts;
    protected StringBuilder fieldSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> field;
    protected java.util.Map<String, Boolean> layoutIfs;
    protected java.util.Map<String, Integer> layoutCounts;
    protected StringBuilder layoutSb;
    protected com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> layout;
    protected StringBuilder coreSendParamsFullName;

    public FragmentBaseBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        baseClass = map.get("baseClass");
        netBackIFullName = map.get("netBackIFullName");
        fieldIfs = new java.util.HashMap<>();
        fieldCounts = new java.util.HashMap<>();
        fieldSb = map.get("field");
        field = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        layoutIfs = new java.util.HashMap<>();
        layoutCounts = new java.util.HashMap<>();
        layoutSb = map.get("layout");
        layout = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        coreSendParamsFullName = map.get("coreSendParamsFullName");

    }

    public int fieldCount() {
        return count(fieldCounts, getForKey("field"));
    }
    public void field(int i0, String sign, String type, String name) {
        addForMap(this.field, getForKey("field", i0), sign, type, name);
        countAdd(fieldCounts, getForKey("field"));
    }
    public int findViewCount() {
        return count(layoutCounts, getForKey("findView"));
    }
    public void findView(int i0, String fieldName, String parent, String rPkg, String id) {
        addForMap(this.layout, getForKey("findView", i0), fieldName, parent, rPkg, id);
        countAdd(layoutCounts, getForKey("findView"));
    }

    public void layoutIf(String inflateToolFullName, String layout) {
        addForMap(this.layout, getIfKey("layout"), inflateToolFullName, layout);
        layoutIfs.put(getIfKey("layout"), true);
    }

    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < count(fieldCounts, getForKey("field")); i0++) {
            List<String> field0 = field.get(getForKey("field", i0));
            addLnTag(fieldSb, "    [sign] [type] [name];", field0.get(0), field0.get(1), field0.get(2));
        }
        if (isIf(layoutIfs, getIfKey("layout"))) {
            List<String> layout0 = layout.get(getIfKey("layout"));
            addLnTag(layoutSb, "    @Nullable");
            addLnTag(layoutSb, "    @Override");
            addLnTag(layoutSb, "    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {");
            addLnTag(layoutSb, "        View view = [inflateToolFullName].inflate(inflater, [layout], container);", layout0.get(0), layout0.get(1));
            for (int i0 = 0; i0 < count(layoutCounts, getForKey("findView")); i0++) {
                List<String> layout1 = layout.get(getForKey("findView", i0));
                addLnTag(layoutSb, "        [fieldName] = [parent]findViewById([rPkg].R.id.[id]);", layout1.get(0), layout1.get(1), layout1.get(2), layout1.get(3));
            }
            addLnTag(layoutSb, "        return view;");
            addLnTag(layoutSb, "    }");
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import android.os.Bundle;");
        lines.add("import android.view.LayoutInflater;");
        lines.add("import android.view.View;");
        lines.add("import android.view.ViewGroup;");
        lines.add("");
        lines.add("import androidx.annotation.NonNull;");
        lines.add("import androidx.annotation.Nullable;");
        lines.add("");
        lines.add("import java.util.List;");
        lines.add("");
        lines.add("import okhttp3.ResponseBody;");
        lines.add("import retrofit2.adapter.rxjava2.Result;");
        lines.add("");
        lines.add("public class [[name]] extends [[baseClass]] implements View.OnClickListener, [[netBackIFullName]] {");
        lines.add("[[field]]");
        lines.add("[[layout]]");
        lines.add("");
        lines.add("    @Override");
        lines.add("    public void onClick(View v) {");
        lines.add("");
        lines.add("    }");
        lines.add("");
        lines.add("    @Override");
        lines.add("    public void accept(String code, Result<ResponseBody> result, [[coreSendParamsFullName]] params, List objs) {");
        lines.add("");
        lines.add("    }");
        lines.add("");
        lines.add("}");

        return lines;
    }
}
