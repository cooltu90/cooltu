package com.codingtu.cooltu.processor.builder.core;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;
import com.codingtu.cooltu.lib4j.data.symbol.Symbol;
import com.codingtu.cooltu.lib4j.file.write.FileWriter;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.lib.BuilderMap;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.TagTools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CoreBuilder implements Symbol {
    public boolean isForce;
    protected JavaInfo javaInfo;

    protected StringBuilderValueMap<String> map = new StringBuilderValueMap();


    public CoreBuilder(JavaInfo info) {
        this.javaInfo = info;
        BuilderMap.add(getBuilderType(), this);
    }

    private BuilderType getBuilderType() {
        return BuilderType.DEFAULT;
    }

    @Override
    public String obtainSymbol() {
        return getClass().getCanonicalName();
    }

    public void create() {
        if (javaInfo == null) {
            return;
        }
        File file = new File(javaInfo.path);
        if (isForce || !file.exists()) {
            List<String> lines = getLines();
            if (!CountTool.isNull(lines)) {
                FileWriter.to(file).cover().write(lines);
            }
        }
    }

    private List<String> getLines() {
        dealLines();
        dealLinesInParent();
        return TagTools.dealLines(map, getTempLines());
    }

    protected void dealLinesInParent() {

    }

    protected abstract void dealLines();

    protected abstract List<String> getTempLines();

    /**************************************************
     *
     *
     *
     **************************************************/

    protected void addValuesMap(List<String> strs, String... ss) {
        Ts.ls(ss, new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int position, String s) {
                strs.add(s);
                return false;
            }
        });
    }

    protected static List<String> getMapList(Map map, int... is) {
        for (int i = 0; i < CountTool.count(is); i++) {
            int index = is[i];
            if (i != CountTool.count(is) - 1) {
                Map map1 = (Map) map.get(index);
                if (map1 == null) {
                    map.put(index, (map1 = new HashMap()));
                }
                map = map1;
            } else {
                List<String> lines = (List<String>) map.get(index);
                if (lines == null) {
                    map.put(index, (lines = new ArrayList<>()));
                }
                return lines;
            }
        }
        return null;
    }

    protected void addMapList(ListValueMap<String, String> map, String key, String... ss) {
        List<String> strings = map.get(key);
        Ts.ls(ss, new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int position, String s) {
                strings.add(s);
                return false;
            }
        });
    }

    public void addLnTag(StringBuilder tag, String line, Object... tags) {
        tag.append(dealLine(line, tags)).append("\r\n");
    }

    public void addTag(StringBuilder tag, String line, Object... tags) {
        tag.append(dealLine(line, tags));
    }

    private String dealLine(String line, Object... values) {
        return TagTools.dealLine(line, values);
    }

}
