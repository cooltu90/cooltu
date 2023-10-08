package com.codingtu.cooltu.processor.bean;

import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.processor.lib.tools.TagTools;

import java.util.ArrayList;
import java.util.List;

public class SubBuilder {
    private List<String> tempLines;
    public StringBuilderValueMap<String> map = new StringBuilderValueMap<>();

    public void initTempLines(List<String> tempLines) {
        if (tempLines == null)
            throw new RuntimeException("模板不能为空");
        this.tempLines = tempLines;
    }

    public void initTempLines() {
        int count = CountTool.count(tempLines);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String line = tempLines.get(i);

            }
        }
    }

    public void addTempLines(String line) {
        if (tempLines == null) {
            tempLines = new ArrayList<>();
        }
        tempLines.add(line);
    }

    public List getLines() {
        return TagTools.dealLines(map, tempLines);
    }
}
