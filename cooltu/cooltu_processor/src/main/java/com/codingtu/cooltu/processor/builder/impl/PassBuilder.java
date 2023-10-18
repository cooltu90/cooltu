package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.builder.base.PassBuilderBase;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PassBuilder extends PassBuilderBase {

    public static PassBuilder BUILDER = new PassBuilder();

    private List<KV<String, String>> kvs = new ArrayList<>();

    private HashMap<String, String> names = new HashMap<>();


    public PassBuilder() {
        super(CurrentPath.javaInfo(FullName.PASS));
        add(new KV<>(FullName.STRING, Constant.FROM_ACT));
    }

    public void add(KV<String, String> kv) {
        kvs.add(kv);
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
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_TOOLS);

        int[] count = {0};
        Ts.ls(kvs, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                if (names.get(kv.v) == null) {
                    names.put(kv.v, kv.v);
                    String fieldName = ConvertTool.toStaticType(kv.v);
                    field(count[0], fieldName, kv.v);
                    if (ClassTool.isInt(kv.k)) {
                        method(count[0], "int", kv.v, "Int", fieldName, ", -1");
                    } else if (ClassTool.isString(kv.k)) {
                        method(count[0], "String", kv.v, "String", fieldName, "");
                    } else if (ClassTool.isLong(kv.k)) {
                        method(count[0], "long", kv.v, "Long", fieldName, ", -1");
                    } else if (ClassTool.isDouble(kv.k)) {
                        method(count[0], "double", kv.v, "Double", fieldName, ", -1");
                    } else if (ClassTool.isFloat(kv.k)) {
                        method(count[0], "float", kv.v, "Float", fieldName, ", -1");
                    } else if (ClassTool.isBoolean(kv.k)) {
                        method(count[0], "boolean", kv.v, "Boolean", fieldName, ", false");
                    }
                    count[0]++;
                }
                return false;
            }
        });
        fieldCount(count[0]);
        methodCount(count[0]);

    }

    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        //Logs.i(lines);
    }
}
/* model_temp_start
package [[pkg]];

import android.content.Intent;

public class Pass {
                                                                                                    [<sub>][for][field]
    public static final String [name] = "[value]";
                                                                                                    [<sub>][for][field]
                                                                                                    [<sub>][for][method]
    public static final [type] [methodName](Intent data) {
        return data.get[methodType]Extra([fieldName][defaultValue]);
    }
                                                                                                    [<sub>][for][method]

}

model_temp_end */