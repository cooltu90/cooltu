package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.builder.base.VhBuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;

import java.util.List;

public class VhBuilder extends VhBuilderBase {

    private final String layoutSimpleName;

    public VhBuilder(JavaInfo info, String name) {
        super(info);
        this.layoutSimpleName = name;
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        Logs.i(lines);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_VH);
        addTag(name, javaInfo.name);
        addTag(coreAdapterVHFullName, FullName.CORE_ADAPTER_VH);
        addTag(rPkg, Pkg.R);
        addTag(layoutName, layoutSimpleName);
    }
}
/* model_temp_start
package [[pkg]];

import android.view.ViewGroup;

public class [[name]] extends [[coreAdapterVHFullName]] {
                                                                                                    [<sub>][for][field]
    public [type] [name];
                                                                                                    [<sub>][for][field]


    public [[name]](ViewGroup parent) {
        super([[rPkg]].R.layout.item_[[layoutName]], parent);
                                                                                                    [<sub>][for][findView]
        [name] = itemView.findViewById([viewId]);
                                                                                                    [<sub>][for][findView]
    }
}
model_temp_end */
