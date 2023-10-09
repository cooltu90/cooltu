package com.codingtu.cooltu.processor.bean;

import java.util.List;

public class SubTag {

    public String type;
    public String tag;
    public String full;
    public List<Integer>  forLevels;

    public static SubTag start(String line) {
        SubTag subTag = new SubTag();
        init(subTag, line, "\\[");
        return subTag;
    }

    public static SubTag end(String line) {
        SubTag subTag = new SubTag();
        init(subTag, line, "\\]");
        return subTag;
    }

    private static void init(SubTag subTag, String line, String regex) {
        String[] parts = line.split(regex);
        subTag.full = line;
        subTag.type = parts[2];
        subTag.tag = parts[3];
    }

    public SubTag getEnd() {
        SubTag subTag = new SubTag();
        subTag.tag = this.tag;
        subTag.type = this.type;
        subTag.full = "]sub]" + subTag.type + "]" + subTag.tag;
        return subTag;
    }
}
