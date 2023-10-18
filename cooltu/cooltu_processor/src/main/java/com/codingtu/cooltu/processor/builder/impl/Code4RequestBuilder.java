package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.builder.base.Code4RequestBuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code4RequestBuilder extends Code4RequestBuilderBase {

    public static final Code4RequestBuilder BUILDER = new Code4RequestBuilder();

    public Map<String, String> fullNames = new HashMap<>();

    public Code4RequestBuilder() {
        super(CurrentPath.javaInfo(FullName.CODE_4_REQUEST));
    }


    public void add(String classFullName) {
        fullNames.put(classFullName, classFullName);
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected boolean isGetLines() {
        return true;
    }

    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_TOOLS);
        int count = CountTool.count(fullNames);
        fieldCount(count);
        Ts.ts(fullNames.keySet()).ls(new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int position, String s) {
                field(position, CurrentPath.actStaticName(s), position + "");
                return false;
            }
        });
    }

}
/* model_temp_start
package [[pkg]];

public class Code4Request {
                                                                                                    [<sub>][for][field]
    public static final int [name] = [value];
                                                                                                    [<sub>][for][field]

}
model_temp_end */

