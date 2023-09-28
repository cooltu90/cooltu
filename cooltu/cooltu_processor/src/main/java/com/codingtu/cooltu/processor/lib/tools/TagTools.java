package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;

public class TagTools {


    public static void addLnTag(StringBuilder tag, String line, Object... tags) {
        tag.append(dealLine(line, tags)).append("\r\n");
    }

    public static String dealLine(String line, Object... tags) {
        if (CountTool.isNull(tags)) {
            return line;
        }

        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = 0;
        int num = 0;
        while (true) {
            start = line.indexOf("[", end);
            if (start > 0) {
                sb.append(line.substring(end, start));
            } else if (start < 0) {
                sb.append(line.substring(end, line.length()));
                break;
            }
            end = line.indexOf("]", start);
            String tag = line.substring(start + 1, end);
            if (StringTool.isNotBlank(tag)) {
                Object tagValue = tags[num];
                if (tagValue != null) {
                    sb.append(StringTool.toString(tagValue));
                }
                num++;
            } else {
                sb.append("[]");
            }
            end += 1;
        }
        return sb.toString();
    }

}
