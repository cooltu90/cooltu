package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.builder.base.PathBuilderBase;
import com.codingtu.cooltu.processor.deal.PathDeal;
import com.codingtu.cooltu.processor.lib.annotation.BuilderBase;
import com.codingtu.cooltu.processor.lib.tools.PathTools;

@To(PathDeal.class)
public class PathBuilder extends PathBuilderBase {
    private String path;

    public PathBuilder(String baseName, String path) {
        super(PathTools.getCurrentJavaInfo(Pkg.CORE_PATH, baseName + Suffix.PATH));
        isForce = true;
        this.path = path;
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_PATH);
        addTag(name, javaInfo.name);
        addTag(basePath, FullName.BASE_PATH);
        isObtainMethod = StringTool.isNotBlank(path);
    }

}
/* model_temp_start
package [[pkg]];

public class [[name]] extends [[basePath]] {

[sub[if[obtainMethod
    public static [[name]] obtain([mparam[initParams]mparam]) {
        return root([[SDCardTool]].getSDCard()
[sub[for[obtainMethodRoot
                + addPrexSeparator([value])
]sub]for]obtainMethodRoot
        );
    }
]sub]if]obtainMethod

    public static [[name]] root(String root) {
        return new [[name]](root);
    }

    public [[name]](String root) {
        super(root);
    }
}
model_temp_end */
