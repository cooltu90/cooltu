package com.codingtu.cooltu.processor.builder.core;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;
import com.codingtu.cooltu.lib4j.data.symbol.Symbol;
import com.codingtu.cooltu.lib4j.file.write.FileWriter;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.lib.BuilderMap;
import com.codingtu.cooltu.processor.lib.tools.TagTools;

import java.io.File;
import java.util.List;

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
