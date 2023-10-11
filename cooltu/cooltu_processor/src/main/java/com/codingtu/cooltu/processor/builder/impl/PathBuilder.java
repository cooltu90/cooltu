package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FileContentType;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ts.impl.basic.TListTs;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.bean.DirPathInfo;
import com.codingtu.cooltu.processor.bean.FilePathInfo;
import com.codingtu.cooltu.processor.bean.PathFilterInfo;
import com.codingtu.cooltu.processor.builder.base.PathBuilderBase;
import com.codingtu.cooltu.processor.deal.PathDeal;
import com.codingtu.cooltu.processor.deal.PathFilterDeal;
import com.codingtu.cooltu.processor.lib.annotation.BuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.tools.PathTools;

import java.util.ArrayList;
import java.util.List;

@To(PathDeal.class)
public class PathBuilder extends PathBuilderBase {
    private String path;
    private List<DirPathInfo> dirInfos;
    private List<FilePathInfo> fileInfos;

    public PathBuilder(String path, JavaInfo info) {
        super(info);
        isForce = true;
        this.path = path;
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

        boolean isRoot = StringTool.isNotBlank(this.path);

        obtainIf(isRoot);
        if (isRoot) {
            String[] split = this.path.split("/");

            Params params = new Params();
            Ts.ls(split, new BaseTs.EachTs<String>() {
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

            obtain(javaInfo.name, params.getMethodParams(), FullName.SDCARD_TOOL);

            addObtainRootCount(CountTool.count(split));

        }

        final int[] nums = {0, 0, 0, 0};
        Ts.ls(dirInfos, new BaseTs.EachTs<DirPathInfo>() {
            @Override
            public boolean each(int position, DirPathInfo info) {
                if (!isParam(info.dirName)) {
                    fileds(nums[0], info.javaName, info.fieldName);
                    initDirs(nums[0], info.fieldName, info.javaName, info.dirName);
                    nums[0]++;
                } else {
                    String dirName = cutParam(info.dirName);
                    dirsMethod(nums[1], info.javaName, dirName, info.javaName, dirName);
                    nums[1]++;
                }


                if (info.isFilter) {
                    dirList(nums[2], FullName.T_LIST_TS, info.javaName, info.fieldName, info.filter, info.filter, FullName.TS, info.fieldName);
                    PathFilterInfo filterInfo = PathFilterDeal.map.get(info.filter);
                    int filterParamsCount = CountTool.count(filterInfo.params);
                    dirListParamCount(nums[2], filterParamsCount);
                    dirListFilterCount(nums[2], filterParamsCount);
                    Ts.ls(filterInfo.params, new BaseTs.EachTs<KV<String, String>>() {
                        @Override
                        public boolean each(int position, KV<String, String> kv) {
                            dirListParam(nums[2], position, kv.k, kv.v, position == (filterParamsCount - 1) ? "" : ",");
                            Logs.i(kv.k + " " + kv.v);
                            dirListFilter(nums[2], position, kv.v, kv.v);
                            return false;
                        }
                    });

                    nums[2]++;
                }

                return false;
            }
        });

        Ts.ls(fileInfos, new BaseTs.EachTs<FilePathInfo>() {
            @Override
            public boolean each(int position, FilePathInfo info) {
                if (!isParam(info.fileName)) {

                    String filedName = info.fieldFullName;
                    String filedType = null;
                    String fileName = info.fileName;
                    String fileType = info.file.fileType();
                    boolean ifParam = false;

                    if (info.fileContentType.equals(FileContentType.TXT)) {
                        if (info.isVoidBean) {
                            filedType = FullName.PATH_TEXT_FILE;
                        } else {
                            filedType = FullName.PATH_BEAN_FILE + "<" + info.beanClass + ">";
                            ifParam = true;

                        }
                    } else if (info.fileContentType.equals(FileContentType.PIC)) {
                        filedType = FullName.PATH_IMAGE_FILE;
                    }

                    if (StringTool.isNotBlank(filedType)) {
                        fileFileds(nums[3], filedType, filedName);
                        initFiles(nums[3], filedName, filedType, fileName, fileType);
                        initFilesParamIf(nums[3], ifParam);
                        if (ifParam) {
                            initFilesParam(nums[3], info.beanClass + ".class");
                        }
                        nums[3]++;
                    }
                }
                return false;
            }
        });

        filedsCount(nums[0]);
        initDirsCount(nums[0]);

        fileFiledsCount(nums[3]);
        initFilesCount(nums[3]);

        dirsMethodCount(nums[1]);
        dirListCount(nums[2]);

    }
}
/* model_temp_start
package [[pkg]];

public class [[name]] extends [[basePath]] {

[sub[for[fileds
    public [type] [name];
]sub]for]fileds
[sub[for[fileFileds
    public [type] [name];
]sub]for]fileFileds

[sub[if[obtain
    public static [name] obtain([params]) {
        return root([SDCardTool].getSDCard()
[sub[for[addObtainRoot
                + addPrexSeparator([path])
]sub]for]addObtainRoot
        );
    }
]sub]if]obtain

    public static [[name]] root(String root) {
        return new [[name]](root);
    }

    public [[name]](String root) {
        super(root);
[sub[for[initDirs
        this.[DeleteLabel] = [CheckDeleteLabelPath].root(
                this.root
                        + addPrexSeparator("[DeleteLabel]")
        );
]sub]for]initDirs
[sub[for[initFiles
        this.[name] = new [type](
                this.root
                        + addPrexSeparator("[filename]")
                , "[fileType]"
[sub[if[initFilesParam
                , [others]
]sub]if]initFilesParam
        );
]sub]for]initFiles
    }
[sub[for[dirsMethod
    public [CheckLabelPath] label(String [labelName]) {
        return new [CheckLabelPath](
                this.root
                        + addPrexSeparator([labelName])
        );
    }
]sub]for]dirsMethod

[sub[for[dirList
    public [TListTs]<[CheckLabelPath]> [label]List(
[sub[for[dirListParam
            [type] [name][divider]
]sub]for]dirListParam
    ) {
        [LabelFilter] filter = new [LabelFilter1]();
[sub[for[dirListFilter
        filter.[type] = [type1];
]sub]for]dirListFilter
        return [Ts].ts(new java.io.File(root()).listFiles()).convert((index, file) -> {
            if (filter.check(file)) {
                return [label](file.getName());
            }
            return null;
        });
    }
]sub]for]dirList
}
model_temp_end */
