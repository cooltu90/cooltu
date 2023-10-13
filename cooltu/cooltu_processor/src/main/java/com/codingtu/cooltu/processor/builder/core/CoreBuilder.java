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

    protected BuilderType getBuilderType() {
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
        if (isBuild() && (isForce() || !file.exists())) {
            List<String> lines = getLines();
            if (!CountTool.isNull(lines)) {
                FileWriter.to(file).cover().write(lines);
            }
        }
    }

    protected boolean isBuild() {
        return true;
    }

    protected boolean isForce() {
        return true;
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

    protected String getForKey(String tag, int... ii) {
        return getKey("for-" + tag, ii);
    }

    protected String getIfKey(String tag, int... ii) {
        return getKey("if-" + tag, ii);
    }

    private String getKey(String tag, int... ii) {
        StringBuilder sb = new StringBuilder();
        sb.append(tag);
        Ts.ts(ii).ls(new BaseTs.EachTs<Integer>() {
            @Override
            public boolean each(int position, Integer integer) {
                sb.append("-").append(integer);
                return false;
            }
        });
        return sb.toString();
    }


    protected void addForMap(ListValueMap<String, String> map, String key, String... strs) {
        List<String> list = map.get(key);
        Ts.ls(strs, new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int position, String s) {
                list.add(s);
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
