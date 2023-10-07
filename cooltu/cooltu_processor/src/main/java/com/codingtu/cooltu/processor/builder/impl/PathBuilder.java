package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.builder.base.PathBuilderBase;
import com.codingtu.cooltu.processor.deal.PathDeal;
import com.codingtu.cooltu.processor.lib.annotation.BuilderBase;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.tools.NameTools;

@To(PathDeal.class)
@BuilderBase
public class PathBuilder extends PathBuilderBase {
    private String path;

    public PathBuilder(String baseName, String path) {
        super(NameTools.getJavaInfoByName(Pkg.CORE_PATH, baseName + Suffix.PATH));
        isForce = true;
        this.path = path;
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_PATH);
        addTag(name, javaInfo.name);
        addTag(basePath, FullName.BASE_PATH);
        addTag(SDCardTool, FullName.SDCARD_TOOL);

        String[] pathParts = this.path.split("/");
        Ts.ls(pathParts, new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int position, String s) {
                if (s.startsWith("{") && s.endsWith("}")) {
                    s = s.substring(1, s.length() - 1);
                    initParams.add("String", s);
                    obtainMethod.get(position).add(s);
                } else {
                    obtainMethod.get(position).add("\"" + s + "\"");
                }
                return false;
            }
        });
    }

}
/* model_temp_start
package [[pkg]];

public class [[name]] extends [[basePath]] {

    public static [[name]] obtain([mparam[initParams]mparam]) {
        return root([[SDCardTool]].getSDCard()
[for[obtainMethod
                + addPrexSeparator([value])
]for]
        );
    }
    public static [[name]] root(String root) {
        return new [[name]](root);
    }

    public [[name]](String root) {
        super(root);
    }
}
model_temp_end */
