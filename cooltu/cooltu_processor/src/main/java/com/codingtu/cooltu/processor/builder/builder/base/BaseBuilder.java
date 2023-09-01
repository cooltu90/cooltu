package com.codingtu.cooltu.processor.builder.builder.base;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;
import com.codingtu.cooltu.lib4j.data.symbol.Symbol;
import com.codingtu.cooltu.lib4j.file.read.FileReader;
import com.codingtu.cooltu.lib4j.file.read.ReadLine;
import com.codingtu.cooltu.processor.builder.BuilderType;
import com.codingtu.cooltu.processor.builder.builder.ActBaseBuilder;
import com.codingtu.cooltu.processor.lib.BuilderMap;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.lib.tools.TagTools;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBuilder implements Symbol {

    protected StringBuilderValueMap<String> map = new StringBuilderValueMap<>();

    public BaseBuilder() {
        BuilderMap.add(getBuilderType(), this);
        createStringBuilders();
    }

    protected abstract void createStringBuilders();

    protected BuilderType getBuilderType() {
        return BuilderType.DEFAULT;
    }

    public List<String> getTempLines(Class tempClass) {
        String path = NameTools.getProcessorJavaPath(tempClass);
        List<String> lines = new ArrayList<>();
        final boolean[] isStart = {false};
        FileReader.from(path).readLine(new ReadLine<String>() {
            @Override
            public void readLine(String s) {
                if (s.contains(Constant.SIGN_TEMP_START)) {
                    isStart[0] = true;
                } else if (s.contains(Constant.SIGN_TEMP_END)) {
                    isStart[0] = false;
                } else if (isStart[0]) {
                    lines.add(s);
                }
            }
        });
        return lines;
    }

    public void create() {
        deal();
        List<String> lines = TagTools.getLines(map, getTempLines(getClass().getSuperclass()));
        Logs.i(lines);
    }

    protected abstract void deal();


    @Override
    public String obtainSymbol() {
        return null;
    }

    /**************************************************
     *
     * 对tag的存入操作
     *
     **************************************************/

    public void addLnTag(StringBuilder tag, String line, Object... tags) {
        tag.append(dealLine(line, tags)).append("\r\n");
    }

    public void addTag(StringBuilder tag, String line, Object... tags) {
        tag.append(dealLine(line, tags));
    }

    private String dealLine(String line, Object... values) {
        if (values != null && values.length > 0) {
            line = TagTools.getLine(line, values);
        }
        return line;
    }
}
