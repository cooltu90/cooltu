package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FileContentType;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.PathBeanType;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.bean.DirPathInfo;
import com.codingtu.cooltu.processor.bean.FilePathInfo;
import com.codingtu.cooltu.processor.bean.ObtainInfo;
import com.codingtu.cooltu.processor.builder.base.PathBuilderBase;
import com.codingtu.cooltu.processor.deal.PathDeal;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;

@To(PathDeal.class)
public class PathBuilder extends PathBuilderBase {
    private String path;
    private List<DirPathInfo> dirInfos;
    private List<FilePathInfo> fileInfos;
    private List<ObtainInfo> obtainList;

    public PathBuilder(String path, JavaInfo info) {
        super(info);
        this.path = path;
    }

    public void addObtain(ObtainInfo obtainInfo) {
        if (obtainList == null) {
            obtainList = new ArrayList<>();
        }
        obtainList.add(obtainInfo);
    }

    public void addDir(DirPathInfo dirInfo) {
        if (dirInfos == null) {
            dirInfos = new ArrayList<>();
        }
        dirInfos.add(dirInfo);
    }

    public void addFile(FilePathInfo info) {
        if (fileInfos == null) {
            fileInfos = new ArrayList<>();
        }
        fileInfos.add(info);
    }

    private boolean isParam(String s) {
        return s.startsWith("{") && s.endsWith("}");
    }

    private String cutParam(String s) {
        return s.substring(1, s.length() - 1);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_PATH);
        addTag(name, javaInfo.name);
        addTag(basePath, FullName.BASE_PATH);

        StringBuilder listMethodSb = new StringBuilder();

        boolean isRoot = StringTool.isNotBlank(this.path);

        if (isRoot) {
            String[] split = this.path.split("/");

            Params params = new Params();
            Ts.ls(split, new Ts.EachTs<String>() {
                @Override
                public boolean each(int position, String s) {
                    if (isParam(s)) {
                        s = cutParam(s);
                        params.add("String", s);
                    } else {
                        s = "\"" + s + "\"";
                    }
                    addObtainRoot(position, s);
                    return false;
                }
            });

            obtainIf(javaInfo.name, params.getMethodParams(), FullName.SDCARD_TOOL);

//            Ts.ls(obtainList, new Ts.EachTs<PathObtain>() {
//                @Override
//                public boolean each(int position, PathObtain pathObtain) {
//                    String methodName = pathObtain.name();
//                    if (StringTool.isBlank(methodName)) {
//                        methodName = "obtain";
//                    }
//
//                    List<String> classes = ClassTool.getAnnotationClasses(new ClassTool.AnnotationClassGetter() {
//                        @Override
//                        public Object get() {
//                            return pathObtain.value();
//                        }
//                    });
//
//                    int count = CountTool.count(classes);
//
//                    Params params1 = new Params();
//                    Params useParams = new Params();
//
//                    Ts.ls(params.getKvs(), new Ts.EachTs<KV<String, String>>() {
//                        @Override
//                        public boolean each(int position, KV<String, String> kv) {
//                            String className = Void.class.getCanonicalName();
//                            if (position < count) {
//                                className = classes.get(position);
//                            }
//                            if (ClassTool.isVoid(className)) {
//                                params1.add(kv);
//                                useParams.add("", kv.v);
//                            } else {
//                                useParams.add("", "new " + className + "().path()");
//                            }
//
//                            return false;
//                        }
//                    });
//
//                    //obtains(position, javaInfo.name, methodName, params1.getMethodParams(), useParams.getParams());
//
//                    return false;
//                }
//            });

            Ts.ls(obtainList, new Ts.EachTs<ObtainInfo>() {
                @Override
                public boolean each(int position, ObtainInfo obtainInfo) {
                    String methodName = obtainInfo.obtain.name();
                    if (StringTool.isBlank(methodName)) {
                        methodName = "obtain";
                    }

                    String[] methodNames = obtainInfo.obtain.methodNames();
                    int count = CountTool.count(methodNames);

                    Params params1 = new Params();
                    Params useParams = new Params();
                    Ts.ls(params.getKvs(), new Ts.EachTs<KV<String, String>>() {
                        @Override
                        public boolean each(int position, KV<String, String> kv) {
                            String methodName = "";
                            if (position < count) {
                                methodName = methodNames[position];
                            }

                            if (StringTool.isBlank(methodName)) {
                                params1.add(kv);
                                useParams.add("", kv.v);
                            } else {
                                useParams.add("", "configs." + methodName + "()");
                            }
                            return false;
                        }
                    });
                    obtains(position, javaInfo.name, methodName, params1.getMethodParams(), obtainInfo.configName, useParams.getParams());
                    return false;
                }
            });
        }

        final int[] nums = {0, 0, 0, 0, 0, 0};
        Ts.ls(dirInfos, new Ts.EachTs<DirPathInfo>() {
            @Override
            public boolean each(int position, DirPathInfo info) {
                if (!isParam(info.dirName)) {
                    fileds(nums[0], info.javaName, info.fieldName);
                    initDirs(nums[0], info.fieldName, info.javaName, info.dirName);
                    nums[0]++;
                } else {
                    String dirName = cutParam(info.dirName);
                    dirsMethod(nums[1], info.javaName, info.fieldName, dirName);
                    nums[1]++;
                }

                if (info.isList) {
                    ExecutableElement ee = info.listMethod;
                    if (ee != null) {
                        //有过滤方法
                        Params methodParamKvs = ElementTools.getMethodParamKvs(ee);
                        String param = methodParamKvs.getParam(new Params.Convert() {
                            @Override
                            public String convert(int index, KV<String, String> kv) {
                                if (index != 0) {
                                    return kv.k + " " + kv.v;
                                }
                                return null;
                            }
                        });
                        addLnTag(listMethodSb, "    public [BaseTs]<[DocumentStudentsStudentPath]> [student]List(", FullName.BASE_TS, info.javaName, info.fieldName);
                        addLnTag(listMethodSb, "            [param]", param);
                        addLnTag(listMethodSb, "    ) {");
                        addLnTag(listMethodSb, "        [Configs] configs = new [Configs]();", info.configName, info.configName);
                        addLnTag(listMethodSb, "        return [Ts].ts(rootFile().listFiles()).convert((index, file) -> {", FullName.TS);
                        addLnTag(listMethodSb, "            if (configs.[studentFilter]([param])) {", ElementTools.simpleName(ee), methodParamKvs.getParam((index, kv) -> kv.v));
                        addLnTag(listMethodSb, "                return [student](file.getName());", info.fieldName);
                        addLnTag(listMethodSb, "            }");
                        addLnTag(listMethodSb, "            return null;");
                        addLnTag(listMethodSb, "        });");
                        addLnTag(listMethodSb, "    }");
                    } else {
                        //没有过滤方法
                        addLnTag(listMethodSb, "    public [BaseTs]<[DocumentStudentsStudentPath]> [student]List(){", FullName.BASE_TS, info.javaName, info.fieldName);
                        addLnTag(listMethodSb, "        return [Ts].ts(rootFile().listFiles()).convert((index, file) -> {", FullName.TS);
                        addLnTag(listMethodSb, "            return [student](file.getName());", info.fieldName);
                        addLnTag(listMethodSb, "        });");
                        addLnTag(listMethodSb, "    }");
                    }
                }

                return false;
            }
        });

        Ts.ls(fileInfos, new Ts.EachTs<FilePathInfo>() {
            @Override
            public boolean each(int position, FilePathInfo info) {
                String filedType = null;
                boolean ifParam = false;

                if (info.fileContentType.equals(FileContentType.TXT)) {
                    if (info.isVoidBean) {
                        filedType = FullName.PATH_TEXT_FILE;
                    } else {
                        if (info.beanType == PathBeanType.BEAN) {
                            filedType = FullName.PATH_BEAN_FILE + "<" + info.beanClass + ">";
                        } else {
                            filedType = FullName.PATH_BEAN_LIST_FILE + "<" + info.beanClass + ">";
                        }
                        ifParam = true;

                    }
                } else if (info.fileContentType.equals(FileContentType.PIC)) {
                    filedType = FullName.PATH_IMAGE_FILE;
                }

                if (!isParam(info.fileName)) {
                    if (StringTool.isNotBlank(filedType)) {
                        fileFileds(nums[3], filedType, info.fieldFullName);
                        initFiles(nums[3], info.fieldFullName, filedType, info.fileName, info.file.fileType());
                        if (ifParam) {
                            initFilesParamIf(nums[3], info.beanClass + ".class");
                        }
                        nums[3]++;
                    }
                } else {
                    if (StringTool.isNotBlank(filedType)) {
                        if (ifParam) {
                            filesMethodParamIf(nums[4], info.beanClass + ".class");
                        }
                        filesMethod(nums[4], filedType, info.fieldFullName, cutParam(info.fileName), info.file.fileType());
                        nums[4]++;
                    }

                }

                if (info.isList) {
                    ExecutableElement ee = info.listMethod;
                    if (ee != null) {
                        //有过滤方法
                        Params methodParamKvs = ElementTools.getMethodParamKvs(ee);
                        String param = methodParamKvs.getParam(new Params.Convert() {
                            @Override
                            public String convert(int index, KV<String, String> kv) {
                                if (index != 0) {
                                    return kv.k + " " + kv.v;
                                }
                                return null;
                            }
                        });

                        addLnTag(listMethodSb, "    public [BaseTs]<[fieldType]> [score_txt]_list(",
                                FullName.BASE_TS, filedType, info.fieldFullName);
                        addLnTag(listMethodSb, "            [param]", param);
                        addLnTag(listMethodSb, "    ) {");
                        addLnTag(listMethodSb, "        [Configs] configs = new [Configs]();", info.configName, info.configName);
                        addLnTag(listMethodSb, "        return [Ts].ts(rootFile().listFiles()).convert((index, file) -> {", FullName.TS);
                        addLnTag(listMethodSb, "            if (configs.[studentFilter]([param])) {", ElementTools.simpleName(ee), methodParamKvs.getParam((index, kv) -> kv.v));
                        addLnTag(listMethodSb, "                return [student](file.getName());", info.fieldFullName);
                        addLnTag(listMethodSb, "            }");
                        addLnTag(listMethodSb, "            return null;");
                        addLnTag(listMethodSb, "        });");
                        addLnTag(listMethodSb, "    }");

                    } else {
                        //没有过滤方法
                        addLnTag(listMethodSb, "    public [BaseTs]<[fieldType]> [score_txt]_list() {",
                                FullName.BASE_TS, filedType, info.fieldFullName);
                        addLnTag(listMethodSb, "        return [Ts].ts(rootFile().listFiles()).convert((index, file) -> [score_txt](file.getName()));",
                                FullName.TS, info.fieldFullName);
                        addLnTag(listMethodSb, "    }");
                    }
                }
                return false;
            }
        });
        listMethodIf(listMethodSb.toString());
    }

}
/* model_temp_start
package [[pkg]];

public class [[name]] extends [[basePath]] {

                                                                                                    [<sub>][for][fileds]
    public [type] [name];
                                                                                                    [<sub>][for][fileds]

                                                                                                    [<sub>][for][fileFileds]
    public [type] [name];
                                                                                                    [<sub>][for][fileFileds]

                                                                                                    [<sub>][if][obtain]
                                                                                                    [<sub>][for][obtains]
    public static [className] [methodName]([params]) {
        [configs] configs = new [configs]();
        return obtain([useParams]);
    }
                                                                                                    [<sub>][for][obtains]

    public static [name] obtain([params]) {
        return root([sDCardToolFullName].getSDCard()
                                                                                                    [<sub>][for][addObtainRoot]
                + addPrexSeparator([path])
                                                                                                    [<sub>][for][addObtainRoot]
        );
    }
                                                                                                    [<sub>][if][obtain]

    public static [[name]] root(String root) {
        return new [[name]](root);
    }

    public [[name]](String root) {
        super(root);
                                                                                                    [<sub>][for][initDirs]
        this.[filedName] = [filedType].root(
                this.root
                        + addPrexSeparator("[dirName]")
        );
                                                                                                    [<sub>][for][initDirs]
                                                                                                    [<sub>][for][initFiles]
        this.[filedName] = new [filedType](
                this.root
                        + addPrexSeparator("[fileName]")
                , "[fileType]"
                                                                                                    [<sub>][if][initFilesParam]
                , [othersParam]
                                                                                                    [<sub>][if][initFilesParam]
        );
                                                                                                    [<sub>][for][initFiles]
    }

                                                                                                    [<sub>][for][dirsMethod]
    public [fieldType] [methodName](String [value]) {
        return new [fieldType](
                this.root
                        + addPrexSeparator([value])
        );
    }
                                                                                                    [<sub>][for][dirsMethod]

                                                                                                    [<sub>][for][dirList]
    public [tListTsFullName]<[fieldType]> [fieldName]List(
                                                                                                    [<sub>][for][dirListParam]
            [type] [name][divider]
                                                                                                    [<sub>][for][dirListParam]
    ) {
        [filterFullName] filter = new [filterFullName]();
                                                                                                    [<sub>][for][dirListFilter]
        filter.[type] = [type];
                                                                                                    [<sub>][for][dirListFilter]
        return [tsFullName].ts(new java.io.File(root()).listFiles()).convert((index, file) -> {
            if (filter.check(file)) {
                return [fieldName](file.getName());
            }
            return null;
        });
    }
                                                                                                    [<sub>][for][dirList]

                                                                                                    [<sub>][for][filesMethod]
    public [fieldType] [filedName](String [value]) {
        return new [fieldType](
                this.root
                        + addPrexSeparator([value] + ".txt")
                , "[fileType]"
                                                                                                    [<sub>][if][filesMethodParam]
                , [others]
                                                                                                    [<sub>][if][filesMethodParam]
        );
    }
                                                                                                    [<sub>][for][filesMethod]

                                                                                                    [<sub>][if][listMethod]
[listMethod]
                                                                                                    [<sub>][if][listMethod]
                                                                                                    [<sub>][for][fileList]
    public [tListTsFullName]<[fieldType]> [fieldName]_list(
                                                                                                    [<sub>][for][fileListParam]
            [type] [name][divider]
                                                                                                    [<sub>][for][fileListParam]
    ) {
        [filterFullName] filter = new [filterFullName]();
                                                                                                    [<sub>][for][filterParam]
        filter.[name] = [name];
                                                                                                    [<sub>][for][filterParam]
        return [tsFullName].ts(new java.io.File(root()).listFiles()).convert((index, file) -> {
            if (filter.check(file)) {
                return [fieldName](file.getName());
            }
            return null;
        });
    }
                                                                                                    [<sub>][for][fileList]
}
model_temp_end */
