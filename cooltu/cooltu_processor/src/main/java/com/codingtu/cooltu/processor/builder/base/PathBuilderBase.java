package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class PathBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder basePath;
    private java.util.Map<String, Boolean> filedsIfs;
    private java.util.Map<String, Integer> filedsCounts;
    private StringBuilder filedsSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> fileds;
    private java.util.Map<String, Boolean> fileFiledsIfs;
    private java.util.Map<String, Integer> fileFiledsCounts;
    private StringBuilder fileFiledsSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> fileFileds;
    private java.util.Map<String, Boolean> obtainIfs;
    private java.util.Map<String, Integer> obtainCounts;
    private StringBuilder obtainSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> obtain;
    private java.util.Map<String, Boolean> initDirsIfs;
    private java.util.Map<String, Integer> initDirsCounts;
    private StringBuilder initDirsSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> initDirs;
    private java.util.Map<String, Boolean> initFilesIfs;
    private java.util.Map<String, Integer> initFilesCounts;
    private StringBuilder initFilesSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> initFiles;
    private java.util.Map<String, Boolean> dirsMethodIfs;
    private java.util.Map<String, Integer> dirsMethodCounts;
    private StringBuilder dirsMethodSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> dirsMethod;
    private java.util.Map<String, Boolean> dirListIfs;
    private java.util.Map<String, Integer> dirListCounts;
    private StringBuilder dirListSb;
    private com.codingtu.cooltu.lib4j.data.map.ListValueMap<String, String> dirList;

    public PathBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        basePath = map.get("basePath");
        filedsIfs = new java.util.HashMap<>();
        filedsCounts = new java.util.HashMap<>();
        filedsSb = map.get("fileds");
        fileds = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        fileFiledsIfs = new java.util.HashMap<>();
        fileFiledsCounts = new java.util.HashMap<>();
        fileFiledsSb = map.get("fileFileds");
        fileFileds = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        obtainIfs = new java.util.HashMap<>();
        obtainCounts = new java.util.HashMap<>();
        obtainSb = map.get("obtain");
        obtain = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        initDirsIfs = new java.util.HashMap<>();
        initDirsCounts = new java.util.HashMap<>();
        initDirsSb = map.get("initDirs");
        initDirs = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        initFilesIfs = new java.util.HashMap<>();
        initFilesCounts = new java.util.HashMap<>();
        initFilesSb = map.get("initFiles");
        initFiles = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        dirsMethodIfs = new java.util.HashMap<>();
        dirsMethodCounts = new java.util.HashMap<>();
        dirsMethodSb = map.get("dirsMethod");
        dirsMethod = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
        dirListIfs = new java.util.HashMap<>();
        dirListCounts = new java.util.HashMap<>();
        dirListSb = map.get("dirList");
        dirList = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

    }
    protected void filedsCount(int count) {
        filedsCounts.put(getForKey("fileds"), count);
    }
    protected void fileFiledsCount(int count) {
        fileFiledsCounts.put(getForKey("fileFileds"), count);
    }
    protected void addObtainRootCount(int count) {
        obtainCounts.put(getForKey("addObtainRoot"), count);
    }
    protected void initDirsCount(int count) {
        initDirsCounts.put(getForKey("initDirs"), count);
    }
    protected void initFilesCount(int count) {
        initFilesCounts.put(getForKey("initFiles"), count);
    }
    protected void dirsMethodCount(int count) {
        dirsMethodCounts.put(getForKey("dirsMethod"), count);
    }
    protected void dirListCount(int count) {
        dirListCounts.put(getForKey("dirList"), count);
    }
    protected void dirListParamCount(int i0, int count) {
        dirListCounts.put(getForKey("dirListParam", i0), count);
    }
    protected void dirListFilterCount(int i0, int count) {
        dirListCounts.put(getForKey("dirListFilter", i0), count);
    }

    protected void fileds(int i0, String type, String name) {
        addForMap(fileds, getForKey("fileds", i0), type, name);
    }
    protected void fileFileds(int i0, String type, String name) {
        addForMap(fileFileds, getForKey("fileFileds", i0), type, name);
    }
    protected void addObtainRoot(int i0, String path) {
        addForMap(obtain, getForKey("addObtainRoot", i0), path);
    }
    protected void initDirs(int i0, String filedName, String filedType, String dirName) {
        addForMap(initDirs, getForKey("initDirs", i0), filedName, filedType, dirName);
    }
    protected void initFiles(int i0, String filedName, String filedType, String fileName, String fileType) {
        addForMap(initFiles, getForKey("initFiles", i0), filedName, filedType, fileName, fileType);
    }
    protected void dirsMethod(int i0, String fieldType, String methodName, String value) {
        addForMap(dirsMethod, getForKey("dirsMethod", i0), fieldType, methodName, value, fieldType, value);
    }
    protected void dirListParam(int i0, int i1, String type, String name, String divider) {
        addForMap(dirList, getForKey("dirListParam", i0, i1), type, name, divider);
    }
    protected void dirListFilter(int i0, int i1, String type) {
        addForMap(dirList, getForKey("dirListFilter", i0, i1), type, type);
    }
    protected void dirList(int i0, String tListTsFullName, String fieldType, String fieldName, String filterFullName, String tsFullName) {
        addForMap(dirList, getForKey("dirList", i0), tListTsFullName, fieldType, fieldName, filterFullName, filterFullName, tsFullName, fieldName);
    }

    protected void obtainIf(boolean is) {
        obtainIfs.put(getIfKey("obtain"), is);
    }
    protected void obtain(String name, String params, String sDCardToolFullName) {
        addForMap(obtain, getIfKey("obtain"), name, params, sDCardToolFullName);
    }
    protected void initFilesParamIf(int i0, boolean is) {
        initFilesIfs.put(getIfKey("initFilesParam", i0), is);
    }
    protected void initFilesParam(int i0, String othersParam) {
        addForMap(initFiles, getIfKey("initFilesParam", i0), othersParam);
    }

    @Override
    protected void dealLinesInParent() {
        for (int i0 = 0; i0 < filedsCounts.get(getForKey("fileds")); i0++) {
            List<String> fileds0 = fileds.get(getForKey("fileds", i0));
            addLnTag(filedsSb, "    public [type] [name];", fileds0.get(0), fileds0.get(1));
        }
        for (int i0 = 0; i0 < fileFiledsCounts.get(getForKey("fileFileds")); i0++) {
            List<String> fileFileds0 = fileFileds.get(getForKey("fileFileds", i0));
            addLnTag(fileFiledsSb, "    public [type] [name];", fileFileds0.get(0), fileFileds0.get(1));
        }
        if (obtainIfs.get(getIfKey("obtain"))) {
            List<String> obtain0 = obtain.get(getIfKey("obtain"));
            addLnTag(obtainSb, "    public static [name] obtain([params]) {", obtain0.get(0), obtain0.get(1));
            addLnTag(obtainSb, "        return root([sDCardToolFullName].getSDCard()", obtain0.get(2));
            for (int i0 = 0; i0 < obtainCounts.get(getForKey("addObtainRoot")); i0++) {
                List<String> obtain1 = obtain.get(getForKey("addObtainRoot", i0));
                addLnTag(obtainSb, "                + addPrexSeparator([path])", obtain1.get(0));
            }
            addLnTag(obtainSb, "        );");
            addLnTag(obtainSb, "    }");
        }
        for (int i0 = 0; i0 < initDirsCounts.get(getForKey("initDirs")); i0++) {
            List<String> initDirs0 = initDirs.get(getForKey("initDirs", i0));
            addLnTag(initDirsSb, "        this.[filedName] = [filedType].root(", initDirs0.get(0), initDirs0.get(1));
            addLnTag(initDirsSb, "                this.root");
            addLnTag(initDirsSb, "                        + addPrexSeparator(\"[dirName]\")", initDirs0.get(2));
            addLnTag(initDirsSb, "        );");
        }
        for (int i0 = 0; i0 < initFilesCounts.get(getForKey("initFiles")); i0++) {
            List<String> initFiles0 = initFiles.get(getForKey("initFiles", i0));
            addLnTag(initFilesSb, "        this.[filedName] = new [filedType](", initFiles0.get(0), initFiles0.get(1));
            addLnTag(initFilesSb, "                this.root");
            addLnTag(initFilesSb, "                        + addPrexSeparator(\"[fileName]\")", initFiles0.get(2));
            addLnTag(initFilesSb, "                , \"[fileType]\"", initFiles0.get(3));
            if (initFilesIfs.get(getIfKey("initFilesParam", i0))) {
                List<String> initFiles1 = initFiles.get(getIfKey("initFilesParam", i0));
                addLnTag(initFilesSb, "                , [othersParam]", initFiles1.get(0));
            }
            addLnTag(initFilesSb, "        );");
        }
        for (int i0 = 0; i0 < dirsMethodCounts.get(getForKey("dirsMethod")); i0++) {
            List<String> dirsMethod0 = dirsMethod.get(getForKey("dirsMethod", i0));
            addLnTag(dirsMethodSb, "    public [fieldType] [methodName](String [value]) {", dirsMethod0.get(0), dirsMethod0.get(1), dirsMethod0.get(2));
            addLnTag(dirsMethodSb, "        return new [fieldType](", dirsMethod0.get(3));
            addLnTag(dirsMethodSb, "                this.root");
            addLnTag(dirsMethodSb, "                        + addPrexSeparator([value])", dirsMethod0.get(4));
            addLnTag(dirsMethodSb, "        );");
            addLnTag(dirsMethodSb, "    }");
        }
        for (int i0 = 0; i0 < dirListCounts.get(getForKey("dirList")); i0++) {
            List<String> dirList0 = dirList.get(getForKey("dirList", i0));
            addLnTag(dirListSb, "    public [tListTsFullName]<[fieldType]> [fieldName]List(", dirList0.get(0), dirList0.get(1), dirList0.get(2));
            for (int i1 = 0; i1 < dirListCounts.get(getForKey("dirListParam", i0)); i1++) {
                List<String> dirList1 = dirList.get(getForKey("dirListParam", i0, i1));
                addLnTag(dirListSb, "            [type] [name][divider]", dirList1.get(0), dirList1.get(1), dirList1.get(2));
            }
            addLnTag(dirListSb, "    ) {");
            addLnTag(dirListSb, "        [filterFullName] filter = new [filterFullName]();", dirList0.get(3), dirList0.get(4));
            for (int i1 = 0; i1 < dirListCounts.get(getForKey("dirListFilter", i0)); i1++) {
                List<String> dirList1 = dirList.get(getForKey("dirListFilter", i0, i1));
                addLnTag(dirListSb, "        filter.[type] = [type];", dirList1.get(0), dirList1.get(1));
            }
            addLnTag(dirListSb, "        return [tsFullName].ts(new java.io.File(root()).listFiles()).convert((index, file) -> {", dirList0.get(5));
            addLnTag(dirListSb, "            if (filter.check(file)) {");
            addLnTag(dirListSb, "                return [fieldName](file.getName());", dirList0.get(6));
            addLnTag(dirListSb, "            }");
            addLnTag(dirListSb, "            return null;");
            addLnTag(dirListSb, "        });");
            addLnTag(dirListSb, "    }");
        }

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("public class [[name]] extends [[basePath]] {");
        lines.add("");
        lines.add("[[fileds]]");
        lines.add("[[fileFileds]]");
        lines.add("");
        lines.add("[[obtain]]");
        lines.add("");
        lines.add("    public static [[name]] root(String root) {");
        lines.add("        return new [[name]](root);");
        lines.add("    }");
        lines.add("");
        lines.add("    public [[name]](String root) {");
        lines.add("        super(root);");
        lines.add("[[initDirs]]");
        lines.add("[[initFiles]]");
        lines.add("    }");
        lines.add("[[dirsMethod]]");
        lines.add("");
        lines.add("[[dirList]]");
        lines.add("}");

        return lines;
    }
}
