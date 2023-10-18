package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.builder.base.NetBuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.util.List;

public class NetBuilder extends NetBuilderBase {

    public static final NetBuilder BUILDER = new NetBuilder();

    public NetBuilder() {
        super(CurrentPath.javaInfo(FullName.NET));
    }

    @Override
    protected boolean isBuild() {
        return false;
    }

    @Override
    protected boolean isGetLines() {
        return true;
    }

    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        Logs.i(lines);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_NET);
    }
}
/* model_temp_start
package [[pkg]];

public class Net {
}

model_temp_end */

