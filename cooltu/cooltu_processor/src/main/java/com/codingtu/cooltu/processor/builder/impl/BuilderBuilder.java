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
import com.codingtu.cooltu.processor.lib.path.ProcessorPath;
import com.codingtu.cooltu.processor.lib.tools.TagTools;
import com.codingtu.cooltu.processor.lib.tools.TempTools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuilderBuilder extends BuilderBuilderBase {

    private String intTag = "i";
    private String addLnTagTemp = "            [space]addLnTag([lines]Sb, \"[line]\"[params]);";
    private String addTagTemp = "            [space]addTag([lines]Sb, \"[line]\"[params]);";
    private String[] lineTagSigns = {"if:", "for:"};
    private String[] lineTags = {"if", "for"};

    private Map<String, String> nameMap = new HashMap<>();

    public BuilderBuilder(JavaInfo builderJavaInfo, JavaInfo info) {
        super(info);

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
                if (subTagEnd == null && isSubStart(line)) {
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

    private void dealSubLines(int level, SubTag subTagStart, String line) {
        if ("for".equals(subTagStart.type)) {
            dealForSubLines(level, subTagStart, line);
        } else if ("if".equals(subTagStart.type)) {
            dealIfSubLines(level, subTagStart, line);
        }
    }

    private void dealIfSubLines(int level, SubTag lastSubTagStart, List<String> lines) {
        int levelsCount = CountTool.count(lastSubTagStart.forLevels);
        String ifKeyParams = getIfKeyParams(levelsCount);
        String space = getSpaces(level);
        String ifPutMethodParams = getPutMethodIntParams(levelsCount);
        boolean isFor = false;
        String tagTemp = addLnTagTemp;
        String dealLineTag = lastSubTagStart.parentTag;
        int[] listCounts = {0};
        StringBuilder strsParamSb = new StringBuilder();
        StringBuilder strsValueSb = new StringBuilder();
        List<String> subTags = new ArrayList<>();

        ifMethod(level, lastSubTagStart, ifPutMethodParams, ifKeyParams, space);
        int count = CountTool.count(lines);
        if (count > 0) {
            SubTag subTagStart = null;
            SubTag subTagEnd = null;
            List<String> subLines = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String line = lines.get(i);
                if (subTagEnd == null && isSubStart(line)) {
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
                    dealLine(isFor, level, lastSubTagStart, line, subTags, strsParamSb, strsValueSb, listCounts, tagTemp, dealLineTag, space);
                }

            }
        }
        ifMethod(lastSubTagStart, strsParamSb.toString(), strsValueSb.toString(), ifPutMethodParams, ifKeyParams, space);
    }

    private void dealIfSubLines(int level, SubTag lastSubTagStart, String line) {
        int levelsCount = CountTool.count(lastSubTagStart.forLevels);
        String ifKeyParams = getIfKeyParams(levelsCount);
        String space = getSpaces(level);
        String ifPutMethodParams = getPutMethodIntParams(levelsCount);
        boolean isFor = false;
        String tagTemp = addTagTemp;
        String dealLineTag = lastSubTagStart.tag;
        int[] listCounts = {0};
        StringBuilder strsParamSb = new StringBuilder();
        StringBuilder strsValueSb = new StringBuilder();
        List<String> subTags = new ArrayList<>();

        ifMethod(level, lastSubTagStart, ifPutMethodParams, ifKeyParams, space);
        dealLine(isFor, level, lastSubTagStart, line, subTags, strsParamSb, strsValueSb, listCounts, tagTemp, dealLineTag, space);
        ifMethod(lastSubTagStart, strsParamSb.toString(), strsValueSb.toString(), ifPutMethodParams, ifKeyParams, space);
    }

    private void ifMethod(int level, SubTag subTag, String ifPutMethodParams, String ifKeyParams, String space) {
        addLnTag(ifs, "    protected void [linesAdd1]If([params]boolean is) {", subTag.tag, ifPutMethodParams);
        addLnTag(ifs, "        [lines]Ifs.put(getIfKey(\"[linesAdd1]\"[params]), is);"
                , subTag.parentTag, subTag.tag, ifKeyParams);
        addLnTag(ifs, "    }");

        addLnTag(dealLinesInParent, "        [space]if ([lines]Ifs.get(getIfKey(\"[tag]\"[params]))) {"
                , space, subTag.parentTag, subTag.tag, ifKeyParams);
        addLnTag(dealLinesInParent, "            [space]List<String> [lines][2] = [lines].get(getIfKey(\"[tag]\"[params]));"
                , space, subTag.parentTag, level, subTag.parentTag, subTag.tag, ifKeyParams);
    }

    private void ifMethod(SubTag subTag, String strsParam, String strsValue, String ifPutMethodParams, String ifKeyParams, String space) {
        if (StringTool.isNotBlank(strsParam)) {
            addLnTag(ifs, "    protected void [lines]If([countSb][strings]) {"
                    , subTag.tag, ifPutMethodParams, strsParam);
            addLnTag(ifs, "        addForMap(this.[lines], getIfKey(\"[tag]\"[i0])[strsValue]);"
                    , subTag.parentTag, subTag.tag, ifKeyParams, strsValue);
            addLnTag(ifs, "    }");
        }
        addLnTag(dealLinesInParent, "        [space]}", space);
    }

    private void dealForSubLines(int level, SubTag lastSubTagStart, List<String> lines) {
        int levelsCount = CountTool.count(lastSubTagStart.forLevels);
        String forKeyParams1 = getForKeyParams(levelsCount + 1);
        String space = getSpaces(level);
        boolean isFor = true;
        String tagTemp = addLnTagTemp;
        String dealLineTag = lastSubTagStart.parentTag;
        List<String> subTags = new ArrayList<>();
        int[] listCounts = {0};
        StringBuilder strsParamSb = new StringBuilder();
        StringBuilder strsValueSb = new StringBuilder();

        forMethod(level, levelsCount, lastSubTagStart, space, getForKeyParams(levelsCount), forKeyParams1);
        int count = CountTool.count(lines);
        if (count > 0) {
            SubTag subTagStart = null;
            SubTag subTagEnd = null;
            List<String> subLines = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String line = lines.get(i);
                if (subTagEnd == null && isSubStart(line)) {
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
                    dealLine(isFor, level, lastSubTagStart, line, subTags, strsParamSb, strsValueSb, listCounts, tagTemp, dealLineTag, space);
                }

            }
        }
        forMethod(levelsCount, strsParamSb.toString(), strsValueSb.toString(), lastSubTagStart, forKeyParams1, space);
    }

    private void dealForSubLines(int level, SubTag lastSubTagStart, String line) {
        int levelsCount = CountTool.count(lastSubTagStart.forLevels);
        String forKeyParams1 = getForKeyParams(levelsCount + 1);
        String space = getSpaces(level);
        boolean isFor = true;
        String tagTemp = addTagTemp;
        String dealLineTag = lastSubTagStart.tag;
        List<String> subTags = new ArrayList<>();
        int[] listCounts = {0};
        StringBuilder strsParamSb = new StringBuilder();
        StringBuilder strsValueSb = new StringBuilder();

        forMethod(level, levelsCount, lastSubTagStart, space, getForKeyParams(levelsCount), forKeyParams1);
        dealLine(isFor, level, lastSubTagStart, line, subTags, strsParamSb, strsValueSb, listCounts, tagTemp, dealLineTag, space);
        forMethod(levelsCount, strsParamSb.toString(), strsValueSb.toString(), lastSubTagStart, forKeyParams1, space);

    }

    private void forMethod(int level, int levelsCount, SubTag subTag, String space, String forKeyParams0, String forKeyParams1) {
        addLnTag(forCounts, "    protected void [lines]Count([params]int count) {", subTag.tag, getPutMethodIntParams(levelsCount));
        addLnTag(forCounts, "        [lines]Counts.put(getForKey(\"[tag]\"[params]), count);", subTag.parentTag, subTag.tag, forKeyParams0);
        addLnTag(forCounts, "    }");


        addLnTag(dealLinesInParent, "        [space]for (int [i][0] = 0; [i][0] < [lines]Counts.get(getForKey(\"[tag]\"[params])); [i][0]++) {"
                , space, intTag, levelsCount, intTag, levelsCount, subTag.parentTag, subTag.tag, forKeyParams0, intTag, levelsCount);


        addLnTag(dealLinesInParent, "            [space]List<String> [lines][0] = [lines].get(getForKey(\"[tag]\"[params]));"
                , space, subTag.parentTag, level, subTag.parentTag, subTag.tag, forKeyParams1);
    }

    private void forMethod(int levelsCount, String strsParam, String strsValue, SubTag lastSubTagStart, String forKeyParams1, String space) {
        if (StringTool.isNotBlank(strsParam)) {
            addLnTag(fors, "    protected void [lines]([countSb][strings]) {"
                    , lastSubTagStart.tag, getPutMethodIntParams(levelsCount + 1), strsParam);
            addLnTag(fors, "        addForMap(this.[lines], getForKey(\"[tag]\"[params])[strsValue]);"
                    , lastSubTagStart.parentTag, lastSubTagStart.tag, forKeyParams1, strsValue);
            addLnTag(fors, "    }");
        }
        addLnTag(dealLinesInParent, "        [space]}", space);
    }

    private void dealLine(boolean isFor, int level, SubTag lastSubTag, String line,
                          List<String> subTags, StringBuilder strsParamSb, StringBuilder strsValueSb,
                          int[] listCounts, String writeLine, String sbName, String space) {
        int start = 0;
        StringBuilder lineSb = new StringBuilder();
        StringBuilder valuesSb = new StringBuilder();
        for (int j = 0; j < line.length(); j++) {
            char c = line.charAt(j);
            if (c == '[') {
                lineSb.append(line, start, j);
                start = j;
            } else if (c == ']') {
                String tag = line.substring(start + 1, j);
                for (int i = 0; i < CountTool.count(lineTagSigns); i++) {
                    String lineTagSign = lineTagSigns[i];
                    if (tag.startsWith(lineTagSign)) {
                        String realTag = tag.substring("if:".length());
                        addLnTag(dealLinesInParent, "            [space]StringBuilder [tag]Sb = new StringBuilder();"
                                , space, realTag);

                        tag = "[" + tag + "]";
                        int i1 = line.indexOf(tag, j);
                        String ifs = line.substring(start + tag.length(), i1);

                        SubTag subTag = new SubTag();
                        subTag.type = lineTags[i];
                        subTag.tag = realTag;
                        subTag.parentTag = lastSubTag.parentTag;
                        subTag.forLevels = copy(lastSubTag.forLevels);
                        if (isFor)
                            subTag.forLevels.add(level);
                        dealSubLines(level + 1, subTag, ifs);

                        start = i1 + tag.length();
                        j = start;
                        lineSb.append("[").append(realTag).append("]");

                        addTag(valuesSb, ", [tag]Sb.toString()", realTag);
                        continue;
                    }
                }
                if (!subTags.contains(tag)) {
                    if (!CountTool.isNull(subTags)) {
                        strsParamSb.append(", ");
                    }
                    strsParamSb.append("String ").append(tag);
                    subTags.add(tag);
                }
                strsValueSb.append(", ").append(tag);
                addTag(valuesSb, ", [lines][0].get([0])", lastSubTag.parentTag, level, listCounts[0]++);

                lineSb.append(line.substring(start, j + 1));
                start = j + 1;
            }
        }

        if (start != line.length()) {
            lineSb.append(line.substring(start));
        }

        addLnTag(dealLinesInParent, writeLine
                , space, sbName, replaceLine(lineSb.toString()), valuesSb.toString());
    }


    /**************************************************
     *
     *
     *
     **************************************************/
    private String getPutMethodIntParams(int count) {
        return getParams(false, true, count, "int ", intTag);
    }

    private String getIfKeyParams(int count) {
        return getParams(true, false, count, "", intTag);
    }

    private String getForKeyParams(int num) {
        return getParams(true, false, num, "", intTag);
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

    private boolean isSubStart(String line) {
        return line.contains(Tags.SUB);
    }

    @Override
    protected void dealLines() {

    }

    @Override
    protected List<String> getTempLines() {
        return TempTools.getTempLines(new File(ProcessorPath.javaInfo(this).path));
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