package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.bean.SubTag;
import com.codingtu.cooltu.processor.builder.base.BuilderBuilderBase;
import com.codingtu.cooltu.processor.constant.Tags;
import com.codingtu.cooltu.processor.lib.tools.PathTools;
import com.codingtu.cooltu.processor.lib.tools.TagTools;
import com.codingtu.cooltu.processor.lib.tools.TempTools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuilderBuilder extends BuilderBuilderBase {

    private String intTag = "i";
    private String strTag = "s";

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
//        Logs.i(lines);
        if (level == 0) {
            if (nameMap.get(subTagStart.tag) == null) {
                nameMap.put(subTagStart.tag, subTagStart.tag);
                addLnTag(fileds, "    private java.util.Map<String, Boolean> [lines]Ifs;", subTagStart.tag);
                addLnTag(inits, "        [lines]Ifs = new java.util.HashMap<>();", subTagStart.tag);
                addLnTag(fileds, "    private java.util.Map<String, Integer> [lines]Counts;", subTagStart.tag);
                addLnTag(inits, "        [lines]Counts = new java.util.HashMap<>();", subTagStart.tag);
                addLnTag(fileds, "    private StringBuilder [pkg]Sb;", subTagStart.tag);
                addLnTag(inits, "        [pkg]Sb = map.get(\"[pkg]\");", subTagStart.tag, subTagStart.tag);
                addLnTag(fileds, "    private [ListValueMap]<String, String> [lines];", FullName.LIST_VALUE_MAP, subTagStart.tag);
                addLnTag(inits, "        [lines] = new [ListValueMap]<>();", subTagStart.tag, FullName.LIST_VALUE_MAP);
                addLnTag(tempLines, "        lines.add(\"[[" + subTagStart.tag + "]]\");");
            }
            subTagStart.parentTag = subTagStart.tag;
        }
        if ("for".equals(subTagStart.type)) {
            dealForSubLines(level, subTagStart, lines);
        } else if ("if".equals(subTagStart.type)) {
            dealIfSubLines(level, subTagStart, lines);
        }
    }

    private void dealIfSubLines(int level, SubTag lastSubTagStart, List<String> lines) {
        int levelsCount = CountTool.count(lastSubTagStart.forLevels);
        String ifKeyParams = getIfKeyParams(levelsCount);
        String space = getSpaces(level);
        String ifPutMethodParams = getPutMethodIntParams(levelsCount);

        addLnTag(ifs, "    protected void [linesAdd1]If([params]boolean is) {", lastSubTagStart.tag, ifPutMethodParams);
        addLnTag(ifs, "        [lines]Ifs.put(getIfKey(\"[linesAdd1]\"[params]), is);"
                , lastSubTagStart.parentTag, lastSubTagStart.tag, ifKeyParams);
        addLnTag(ifs, "    }");

        addLnTag(dealLinesInParent, "        [space]if ([lines]Ifs.get(getIfKey(\"[tag]\"[params]))) {"
                , space, lastSubTagStart.parentTag, lastSubTagStart.tag, ifKeyParams);
        addLnTag(dealLinesInParent, "            [space]List<String> [lines][2] = [lines].get(getIfKey(\"[tag]\"[params]));"
                , space, lastSubTagStart.parentTag, level, lastSubTagStart.parentTag, lastSubTagStart.tag, ifKeyParams);


        int count = CountTool.count(lines);
        int[] index = {0};
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
                    subTagStart.parentTag = lastSubTagStart.parentTag;
                    dealSubLines(level + 1, subTagStart, subLines);
                    subTagStart = null;
                    subTagEnd = null;
                } else if (subTagStart != null) {
                    subLines.add(line);
                } else {
                    List<String> tags = TagTools.getTags(Tags.SINGLE_START, Tags.SINGLE_END, line);
                    StringBuilder sb = new StringBuilder();
                    Ts.ls(tags, new BaseTs.EachTs<String>() {
                        @Override
                        public boolean each(int position, String s) {
                            addTag(sb, ", [lines][0].get([0])", lastSubTagStart.parentTag, level, index[0]++);
                            return false;
                        }
                    });
                    addLnTag(dealLinesInParent, "            [space]addLnTag([lines]Sb, \"[line]\"[params]);"
                            , space, lastSubTagStart.parentTag, replaceLine(line), sb.toString());
                }

            }
        }

        String strsParam = getPutMethodStrParams(index[0]);

        if (StringTool.isNotBlank(strsParam)) {
            addLnTag(ifs, "    protected void [lines]([countSb][strings]) {"
                    , lastSubTagStart.tag, ifPutMethodParams, strsParam);
            addLnTag(ifs, "        addForMap([lines], getIfKey(\"[tag]\"[i0])[strsValue]);"
                    , lastSubTagStart.parentTag, lastSubTagStart.tag, ifKeyParams, getAddForMapStrValue(index[0]));
            addLnTag(ifs, "    }");
        }

        addLnTag(dealLinesInParent, "        [space]}", space);


    }

    private void dealForSubLines(int level, SubTag lastSubTagStart, List<String> lines) {
        int levelsCount = CountTool.count(lastSubTagStart.forLevels);

        String forKeyParams0 = getForKeyParams(levelsCount);
        String forKeyParams1 = getForKeyParams(levelsCount + 1);
        String space = getSpaces(level);


        addLnTag(forCounts, "    protected void [lines]Count([params]int count) {", lastSubTagStart.tag, getPutMethodIntParams(levelsCount));
        addLnTag(forCounts, "        [lines]Counts.put(getForKey(\"[tag]\"[params]), count);", lastSubTagStart.parentTag, lastSubTagStart.tag, forKeyParams0);
        addLnTag(forCounts, "    }");


        addLnTag(dealLinesInParent, "        [space]for (int [i][0] = 0; [i][0] < [lines]Counts.get(getForKey(\"[tag]\"[params])); [i][0]++) {"
                , space, intTag, levelsCount, intTag, levelsCount, lastSubTagStart.parentTag, lastSubTagStart.tag, forKeyParams0, intTag, levelsCount);


        addLnTag(dealLinesInParent, "            [space]List<String> [lines][0] = [lines].get(getForKey(\"[tag]\"[params]));"
                , space, lastSubTagStart.parentTag, level, lastSubTagStart.parentTag, lastSubTagStart.tag, forKeyParams1);


        int count = CountTool.count(lines);
        int[] index = {0};
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
                    subTagStart.parentTag = lastSubTagStart.parentTag;
                    dealSubLines(level + 1, subTagStart, subLines);
                    subTagStart = null;
                    subTagEnd = null;
                } else if (subTagStart != null) {
                    subLines.add(line);
                } else {
                    List<String> tags = TagTools.getTags(Tags.SINGLE_START, Tags.SINGLE_END, line);
                    StringBuilder sb = new StringBuilder();
                    Ts.ls(tags, new BaseTs.EachTs<String>() {
                        @Override
                        public boolean each(int position, String s) {
                            addTag(sb, ", [lines][0].get([0])", lastSubTagStart.parentTag, level, index[0]++);
                            return false;
                        }
                    });
                    addLnTag(dealLinesInParent, "            [space]addLnTag([lines]Sb, \"[line]\"[params]);"
                            , space, lastSubTagStart.parentTag, replaceLine(line), sb.toString());
                }

            }
        }

        String strsParam = getPutMethodStrParams(index[0]);

        if (StringTool.isNotBlank(strsParam)) {
            addLnTag(fors, "    protected void [lines]([countSb][strings]) {"
                    , lastSubTagStart.tag, getPutMethodIntParams(levelsCount + 1), strsParam);
            addLnTag(fors, "        addForMap([lines], getForKey(\"[tag]\"[params])[strsValue]);", lastSubTagStart.parentTag, lastSubTagStart.tag, forKeyParams1, getAddForMapStrValue(index[0]));
            addLnTag(fors, "    }");
        }

        addLnTag(dealLinesInParent, "        [space]}", space);

    }


    private String getPutMethodIntParams(int count) {
        return getParams(false, true, count, "int ", intTag);
    }

    private String getPutMethodStrParams(int count) {
        return getParams(false, false, count, "String ", strTag);
    }

    private String getIfKeyParams(int count) {
        return getParams(true, false, count, "", intTag);
    }

    private String getForKeyParams(int num) {
        return getParams(true, false, num, "", intTag);
    }

    private String getAddForMapStrValue(int num) {
        return getParams(true, false, num, "", strTag);
    }

    private String getParams(boolean isFirst, boolean isLast, int num, String type, String tag) {
        StringBuilder sb = new StringBuilder();
        if (isFirst && num > 0) {
            sb.append(", ");
        }
        for (int i = 0; i < num; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(type).append(tag).append(i);
        }
        if (isLast && num > 0) {
            sb.append(", ");
        }
        return sb.toString();
    }

    private String getSpaces(int times) {
        return StringTool.repeatString(times, "    ");
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
[[fors]]
[[ifs]]
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