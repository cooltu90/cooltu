package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.bean.SubTag;
import com.codingtu.cooltu.processor.builder.base.BuilderBuilderBase;
import com.codingtu.cooltu.processor.constant.Tags;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.PathTools;
import com.codingtu.cooltu.processor.lib.tools.TagTools;
import com.codingtu.cooltu.processor.lib.tools.TempTools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuilderBuilder extends BuilderBuilderBase {
    private Map<String, String> nameMap = new HashMap<>();

    public BuilderBuilder(JavaInfo builderJavaInfo, JavaInfo info) {
        super(info);
        isForce = true;

        List<String> lines = TempTools.getTempLines(new File(builderJavaInfo.path));
        addTag(pkg, Pkg.PROCESSOR_BUILDER_BASE);
        addTag(name, info.name);
        addTag(base, FullName.PROCESS_CORE_BUILDER);
        addTag(JavaInfo, FullName.JAVA_INFO);

        int count = CountTool.count(lines);
        if (count > 0) {
            SubTag subTagStart = null;
            SubTag subTagEnd = null;
            List<String> subLines = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String line = lines.get(i);
                if (subTagEnd == null && line.startsWith(Tags.SUB_START)) {
                    subTagStart = SubTag.start(line);
                    subTagEnd = subTagStart.getEnd();
                    subLines.clear();
                } else if (subTagEnd != null && subTagEnd.full.equals(line)) {
                    dealSubLines(0, subTagStart, subLines);
                    subTagStart = null;
                    subTagEnd = null;
                } else if (subTagStart != null) {
                    subLines.add(line);
                } else {
                    List<String> tags = TagTools.getTags(Tags.DOUBLE_START, Tags.DOUBEL_END, line);
                    Ts.ls(tags, new BaseTs.EachTs<String>() {
                        @Override
                        public boolean each(int position, String tag) {
                            if (nameMap.get(tag) == null) {
                                nameMap.put(tag, tag);
                                addLnTag(fileds, "    protected StringBuilder [pkg];", tag);
                                addLnTag(getStringBuilder, "        [pkg] = map.get(\"[pkg]\");", tag, tag);
                            }
                            return false;
                        }
                    });
                    addLnTag(tempLines, "        lines.add(\"[line]\");", replaceLine(line));
                }

            }
        }

    }

    private void dealSubLines(int level, SubTag subTagStart, List<String> lines) {
        if ("for".equals(subTagStart.type)) {
            dealForSubLines(level, subTagStart.tag, lines);
        }
    }

    private void dealSubLines1(SubTag subTagStart, List<String> lines) {
        if ("for".equals(subTagStart.type)) {
            dealForSubLines1(subTagStart.tag, lines);
        }
    }

    private void dealForSubLines(int level, String tag, List<String> lines) {
        if (nameMap.get(tag) == null) {
            nameMap.put(tag, tag);
            addLnTag(fileds, "    private StringBuilder [pkg]Sb;", tag);
            addLnTag(fileds, "    private java.util.Map<String, Integer> [lines]Counts;", tag);

            addLnTag(fileds, "    private [ListValueMap]<String, String> [lines]Map;", FullName.LIST_VALUE_MAP, tag);

            addLnTag(getStringBuilder, "        [pkg]Sb = map.get(\"[pkg]\");", tag, tag);
            addLnTag(getStringBuilder, "        [lines]Counts = new java.util.HashMap<>();", tag);
            addLnTag(getStringBuilder, "        [lines]Map = new [ListValueMap]<>();", tag, FullName.LIST_VALUE_MAP);

            addLnTag(tempLines, "        lines.add(\"[[" + tag + "]]\");");

            addLnTag(addValuesMaps, "    protected void [lines](int i, String... ss) {", tag);
            addLnTag(addValuesMaps, "        addMapList([lines]Map, \"for\" + i, ss);", tag);
            addLnTag(addValuesMaps, "    }", tag);

            addLnTag(addValuesMaps, "    protected void [lines]Counts(int count) {", tag);
            addLnTag(addValuesMaps, "        [lines]Counts.put(\"for\", count);", tag);
            addLnTag(addValuesMaps, "    }");


            addLnTag(dealLinesInParent, "        for (int i[1] = 0; i[1] < [lines]Counts.get(\"for\"); i[1]++) {"
                    , level + 1, level + 1, tag, level + 1);
            addLnTag(dealLinesInParent, "            List<String> lines = [lines]Map.get(\"for\" + i[1]);", tag, level + 1);

            int count = CountTool.count(lines);
            if (count > 0) {
                SubTag subTagStart = null;
                SubTag subTagEnd = null;
                List<String> subLines = new ArrayList<>();
                int index = 0;
                for (int i = 0; i < count; i++) {
                    String line = lines.get(i);
                    if (subTagEnd == null && line.startsWith(Tags.SUB_START)) {
                        subTagStart = SubTag.start(line);
                        subTagEnd = subTagStart.getEnd();
                        subLines.clear();
                    } else if (subTagEnd != null && subTagEnd.full.equals(line)) {
                        dealSubLines1(subTagStart, subLines);
                        subTagStart = null;
                        subTagEnd = null;
                    } else if (subTagStart != null) {
                        subLines.add(line);
                    } else {
                        List<String> tags = TagTools.getTags(Tags.SINGLE_START, Tags.SINGLE_END, line);
                        StringBuilder sb = new StringBuilder();
                        for (int j = 0; j < CountTool.count(tags); j++) {
                            addTag(sb, ", lines.get([index])", index++);
                        }
                        addLnTag(dealLinesInParent, "            addLnTag([lines]Sb, \"[line]\"[params]);", tag, replaceLine(line), sb.toString());
                    }

                }
            }


            addLnTag(dealLinesInParent, "        }", tag);

        }


    }

    private void dealForSubLines1(String tag, List<String> lines) {
        if (nameMap.get(tag) == null) {
            nameMap.put(tag, tag);

            addLnTag(fileds, "    private java.util.Map<String, Integer> [lines]Counts;", tag);

            addLnTag(getStringBuilder, "        [lines]Counts = new java.util.HashMap<>();", tag);

        }
    }

    private String replaceLine(String line) {
        return line.replace("\"", "\\\"");
    }

    @Override
    protected void dealLines() {

    }

    @Override
    protected List<String> getTempLines() {
        return TempTools.getTempLines(new File(PathTools.getProcessorJavaInfo(this).path));
    }
}
/* model_temp_start
package [[pkg]];
import java.util.ArrayList;
import java.util.List;

public abstract class [[name]] extends [[base]] {
[[fileds]]
    public [[name]]([[JavaInfo]] info) {
        super(info);
[[getStringBuilder]]
    }
[[addValuesMaps]]
    @Override
    protected void dealLinesInParent() {
[[dealLinesInParent]]
    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
[[tempLines]]
        return lines;
    }
}
model_temp_end */