package com.codingtu.cooltu.processor.builder.builder.base;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.lib4j.data.symbol.Symbol;
import com.codingtu.cooltu.lib4j.file.read.FileReader;
import com.codingtu.cooltu.lib4j.file.read.ReadLine;
import com.codingtu.cooltu.processor.builder.BuilderType;
import com.codingtu.cooltu.processor.lib.BuilderMap;
import com.codingtu.cooltu.processor.lib.tools.NameTools;

import java.util.ArrayList;
import java.util.List;

public class BaseBuilder implements Symbol {

    public BaseBuilder() {
        BuilderMap.add(getBuilderType(), this);
    }

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

    }

    @Override
    public String obtainSymbol() {
        return null;
    }
}
