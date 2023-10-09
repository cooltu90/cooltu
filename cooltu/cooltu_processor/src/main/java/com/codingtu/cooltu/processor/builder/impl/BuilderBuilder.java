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

    private String forListsName = "forLists";

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
                                addLnTag(inits, "        [pkg] = map.get(\"[pkg]\");", tag, tag);
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
            dealForSubLines(level, subTagStart, lines);
        }
    }

    private void dealForSubLines(int level, SubTag lastSubTagStart, List<String> lines) {
        //forCounts
        StringBuilder countSb = new StringBuilder();
        StringBuilder countSb1 = new StringBuilder();
        for (int i = 0; i < CountTool.count(lastSubTagStart.forLevels); i++) {
            addTag(countSb, "int i[1], ", i);
            addTag(countSb1, " + \"-\" + i[1]", i);
        }
        addLnTag(forCounts, "    protected void [lines]Count([params]int count) {", lastSubTagStart.tag, countSb.toString());
        addLnTag(forCounts, "        forCounts(\"for\"[params], count);", countSb1.toString());
        addLnTag(forCounts, "    }");

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
                    subTagStart.forLevels = copy(lastSubTagStart.forLevels);
                    subTagStart.forLevels.add(level);
                    dealSubLines(level + 1, subTagStart, subLines);
                    subTagStart = null;
                    subTagEnd = null;
                } else if (subTagStart != null) {
                    subLines.add(line);
                } else {

                }

            }
        }


    }

    private List<Integer> copy(List<Integer> levels) {
        return Ts.ts(levels).convert(new BaseTs.Convert<Integer, Integer>() {
            @Override
            public Integer convert(int index, Integer integer) {
                return integer;
            }
        }).get();
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
[[inits]]
    }
[[forCounts]]
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