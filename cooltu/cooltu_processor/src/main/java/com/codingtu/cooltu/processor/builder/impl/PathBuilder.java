package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.builder.base.PathBuilderBase;
import com.codingtu.cooltu.processor.deal.PathDeal;
import com.codingtu.cooltu.processor.lib.annotation.BuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.tools.PathTools;

@To(PathDeal.class)
public class PathBuilder extends PathBuilderBase {
    private String path;

    public PathBuilder(String baseName, String path) {
        super(PathTools.getCurrentJavaInfo(Pkg.CORE_PATH, baseName + Suffix.PATH));
        isForce = true;
        this.path = path;
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
    }

    private boolean isParam(String s) {
        return s.startsWith("{") && s.endsWith("}");
    }

    private String cutParam(String s) {
        return s.substring(1, s.length() - 1);
    }

    @Override
    protected void dealLines() {

    }
}
/* model_temp_start
package [[pkg]];

public class [[name]] extends [[basePath]] {
    public [[name]](String root) {
        super(root);
    }

    public static [[name]] root(String root) {
        return new [[name]](root);
    }
[sub[if[obtain
    public static [name] obtain([params]) {
        return root([SDCardTool].getSDCard()
[sub[for[addObtainRoot
                + addPrexSeparator([path])
]sub]for]addObtainRoot
        );
    }
]sub]if]obtain
}
model_temp_end */
