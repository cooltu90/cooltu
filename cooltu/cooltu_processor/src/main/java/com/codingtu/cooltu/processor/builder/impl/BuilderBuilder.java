package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.bean.SubTag;
import com.codingtu.cooltu.processor.builder.base.BuilderBuilderBase;
import com.codingtu.cooltu.processor.constant.Tags;
import com.codingtu.cooltu.processor.lib.log.Logs;
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
                addLnTag(fileds, "    protected java.util.Map<String, Boolean> [lines]Ifs;", subTagStart.tag);
                addLnTag(inits, "        [lines]Ifs = new java.util.HashMap<>();", subTagStart.tag);
                addLnTag(fileds, "    protected java.util.Map<String, Integer> [lines]Counts;", subTagStart.tag);
                addLnTag(inits, "        [lines]Counts = new java.util.HashMap<>();", subTagStart.tag);
                addLnTag(fileds, "    protected StringBuilder [pkg]Sb;", subTagStart.tag);
                addLnTag(inits, "        [pkg]Sb = map.get(\"[pkg]\");", subTagStart.tag, subTagStart.tag);
                addLnTag(fileds, "    protected [ListValueMap]<String, String> [lines];", FullName.LIST_VALUE_MAP, subTagStart.tag);
                addLnTag(inits, "        [lines] = new [ListValueMap]<>();", subTagStart.tag, FullName.LIST_VALUE_MAP);
                addLnTag(tempLines, "        lines.add(\"[[" + subTagStart.tag + "]]\");");
            }
            subTagStart.parentTag = subTagStart.tag;
        }
        dealSubLines(subTagStart, level, addLnTagTemp, subTagStart.parentTag, getLinesDeal(lines));
    }

    private void dealSubLines(SubTag subTagStart, int level, String addTemp, String tag, Deal deal) {
        int levelsCount = CountTool.count(subTagStart.forLevels);
        boolean isFor = "for".equals(subTagStart.type);
        MethodDeal methodDeal = isFor ? getForMethodDeal() : getIfMethodDeal();
        StringBuilder tagSb = isFor ? fors : ifs;
        String tag0 = isFor ? "For" : "If";
        String space = getSpaces(level);
        List<String> methodParams = isFor ? getMethodIntParams(levelsCount + 1) : getMethodIntParams(levelsCount);
        List<String> useMethodStrParams = new ArrayList<>();
        List<String> useIntParam0 = getUseMethodIntParams(levelsCount);
        List<String> useIntParam1 = isFor ? getUseMethodIntParams(levelsCount + 1) : null;
        List<String> useIntParam2 = isFor ? useIntParam1 : useIntParam0;

        int[] listCounts = {0};
        List<String> subTags = new ArrayList<>();
        methodDeal.deal(level, levelsCount, subTagStart, useIntParam0, useIntParam1, space);
        deal.deal(isFor, level, subTagStart, subTags, methodParams, useMethodStrParams, listCounts, addTemp, tag, space);
        method(levelsCount, tagSb, subTagStart, tag0, methodParams, useMethodStrParams, useIntParam0, useIntParam2, space);
    }

    private MethodDeal getIfMethodDeal() {
        return new MethodDeal() {
            @Override
            public void deal(int level, int levelsCount, SubTag subTag, List<String> useIntParam0, List<String> useIntParam1, String space) {
                String keyParams0 = getParams(useIntParam0, true, false);
                //String ifPutMethodParams = getParams(getMethodIntParams(levelsCount), false, true);

//                addLnTag(ifs, "    protected void is[linesAdd1]([params]boolean is) {"
//                        , ConvertTool.toClassType(subTag.tag), ifPutMethodParams);
//                addLnTag(ifs, "        [lines]Ifs.put(getIfKey(\"[linesAdd1]\"[params]), is);"
//                        , subTag.parentTag, subTag.tag, keyParams0);
//                addLnTag(ifs, "    }");

                addLnTag(dealLinesInParent, "        [space]if (isIf([lines]Ifs, getIfKey(\"[tag]\"[params]))) {"
                        , space, subTag.parentTag, subTag.tag, keyParams0);
                addLnTag(dealLinesInParent, "            [space]List<String> [lines][2] = [lines].get(getIfKey(\"[tag]\"[params]));"
                        , space, subTag.parentTag, level, subTag.parentTag, subTag.tag, keyParams0);
            }
        };
    }

    private MethodDeal getForMethodDeal() {
        return new MethodDeal() {
            @Override
            public void deal(int level, int levelsCount, SubTag subTag, List<String> useIntParam0, List<String> useIntParam1, String space) {
                String keyParams0 = getParams(useIntParam0, true, false);
                String keyParams1 = getParams(useIntParam1, true, false);
//                String params = getParams(getMethodIntParams(levelsCount));

//                addLnTag(forCounts, "    protected int [lines]Count([params]) {"
//                        , subTag.tag, params);
//                addLnTag(forCounts, "        return count([lines]Counts, getForKey(\"[tag]\"[params]));"
//                        , subTag.parentTag, subTag.tag, keyParams0);
//                addLnTag(forCounts, "    }");


                addLnTag(dealLinesInParent, "        [space]for (int [i][0] = 0; [i][0] < count([lines]Counts, getForKey(\"[tag]\"[params])); [i][0]++) {"
                        , space, intTag, levelsCount, intTag, levelsCount, subTag.parentTag, subTag.tag, keyParams0, intTag, levelsCount);


                addLnTag(dealLinesInParent, "            [space]List<String> [lines][0] = [lines].get(getForKey(\"[tag]\"[params]));"
                        , space, subTag.parentTag, level, subTag.parentTag, subTag.tag, keyParams1);
            }
        };
    }

    private void method(int levelsCount, StringBuilder sb, SubTag subTag, String tag0, List<String> methodParams, List<String> useMethodStrParams,
                        List<String> useIntParam0, List<String> useIntParam2, String space) {
        Logs.i(useIntParam2);
        String keyParams0 = getParams(useIntParam0, true, false);
        String keyParams = getParams(useIntParam2, true, false);

        if ("If".equals(tag0)) {
            String ifPutMethodParams = getParams(getMethodIntParams(levelsCount), false, true);

            if (CountTool.isNull(useMethodStrParams)) {
                addLnTag(sb, "    protected void is[linesAdd1]([params]boolean is) {"
                        , ConvertTool.toClassType(subTag.tag), ifPutMethodParams);
                addLnTag(sb, "        [lines]Ifs.put(getIfKey(\"[linesAdd1]\"[params]), is);"
                        , subTag.parentTag, subTag.tag, keyParams0);
                addLnTag(sb, "    }");
            }else{
                addLnTag(sb, "    protected void [lines]If([params]) {"
                        , subTag.tag, getParams(methodParams));
                addLnTag(sb, "        addForMap(this.[lines], getIfKey(\"[tag]\"[params])[strsValue]);"
                        , subTag.parentTag, subTag.tag, keyParams, getParams(useMethodStrParams, true, false));
                addLnTag(sb, "        [lines]Ifs.put(getIfKey(\"[tag]\"[params]), true);"
                        , subTag.parentTag, subTag.tag, keyParams);
                addLnTag(sb, "    }");
            }
        } else {

            String params = getParams(getMethodIntParams(levelsCount));

            addLnTag(sb, "    protected int [lines]Count([params]) {"
                    , subTag.tag, params);
            addLnTag(sb, "        return count([lines]Counts, getForKey(\"[tag]\"[params]));"
                    , subTag.parentTag, subTag.tag, keyParams0);
            addLnTag(sb, "    }");


            addLnTag(sb, "    protected void [lines]([params]) {"
                    , subTag.tag, getParams(methodParams));
            addLnTag(sb, "        addForMap(this.[lines], getForKey(\"[tag]\"[params])[strsValue]);"
                    , subTag.parentTag, subTag.tag, keyParams, getParams(useMethodStrParams, true, false));
            addLnTag(sb, "        countAdd([lines]Counts, getForKey(\"[tag]\"[params]));"
                    , subTag.parentTag, subTag.tag, keyParams0);
            addLnTag(sb, "    }");
        }
        addLnTag(dealLinesInParent, "        [space]}", space);

    }

    private Deal getLinesDeal(List<String> lines) {
        return new Deal() {
            @Override
            public void deal(boolean isFor, int level, SubTag lastSubTagStart, List<String> subTags,
                             List<String> methodParams, List<String> useMethodStrParams,
                             int[] listCounts, String writeLine, String sbName, String space) {
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
                            if (isFor)
                                subTagStart.forLevels.add(level);
                            subTagStart.parentTag = lastSubTagStart.parentTag;
                            dealSubLines(level + 1, subTagStart, subLines);
                            subTagStart = null;
                            subTagEnd = null;
                        } else if (subTagStart != null) {
                            subLines.add(line);
                        } else {
                            getLineDeal(line).deal(isFor, level, lastSubTagStart, subTags, methodParams, useMethodStrParams, listCounts, writeLine, sbName, space);
                        }

                    }
                }
            }
        };
    }

    private Deal getLineDeal(String line) {
        return new Deal() {
            @Override
            public void deal(boolean isFor, int level, SubTag lastSubTag, List<String> subTags,
                             List<String> methodParams, List<String> useMethodStrParams,
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
                        boolean isDeal = false;
                        for (int i = 0; i < CountTool.count(lineTagSigns); i++) {
                            String lineTagSign = lineTagSigns[i];
                            if (tag.startsWith(lineTagSign)) {
                                String realTag = tag.substring(lineTagSign.length());
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
                                //dealSubLines(level + 1, subTag, ifs);
                                dealSubLines(subTag, level + 1, addTagTemp, subTag.tag, getLineDeal(ifs));

                                start = i1 + tag.length();
                                j = start;
                                lineSb.append("[").append(realTag).append("]");

                                addTag(valuesSb, ", [tag]Sb.toString()", realTag);
                                isDeal = true;
                                continue;
                            }
                        }
                        if (isDeal) {
                            continue;
                        }
                        if (!subTags.contains(tag)) {
                            methodParams.add("String " + tag);
                            subTags.add(tag);
                        }
                        useMethodStrParams.add(tag);
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
        };
    }

    private static interface Deal {
        public void deal(boolean isFor, int level, SubTag lastSubTag,
                         List<String> subTags, List<String> methodParams, List<String> useMethodStrParams,
                         int[] listCounts, String writeLine, String sbName, String space);
    }

    private static interface MethodDeal {
        public void deal(int level, int levelsCount, SubTag subTag, List<String> useIntParam0, List<String> useIntParam1, String space);
    }

    private String getPutMethodIntParams(int count) {
        return getParams(false, true, count, "int ", intTag);
    }

    private String getPutMethodIntParams1(int count) {
        return getParams(false, false, count, "int ", intTag);
    }

    private String getKeyParams(int num) {
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