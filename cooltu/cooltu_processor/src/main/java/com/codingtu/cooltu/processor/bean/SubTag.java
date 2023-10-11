package com.codingtu.cooltu.processor.bean;

import com.codingtu.cooltu.processor.constant.Tags;
import com.codingtu.cooltu.processor.lib.tools.TagTools;

import java.util.List;

public class SubTag {

    public String type;
    public String tag;
    public String full;
    public List<Integer> forLevels;
    public String parentTag;

    public static SubTag start(String line) {
        SubTag subTag = new SubTag();
        if (line.startsWith(Tags.SUB_FOR)) {
            subTag.type = "for";
        } else if (line.startsWith(Tags.SUB_IF)) {
            subTag.type = "if";
        }
        subTag.full = line;
        subTag.tag = TagTools.getTags(Tags.SINGLE_START, Tags.SINGLE_END, line).get(0);
        return subTag;
    }

    public SubTag getEnd() {
        SubTag subTag = new SubTag();
        subTag.tag = this.tag;
        subTag.type = this.type;
        subTag.full = this.full;
        return subTag;
    }
}
