package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.bean.NetInfo;
import com.codingtu.cooltu.processor.builder.base.NetBuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.ExecutableElement;

public class NetBuilder extends NetBuilderBase {

    public static final NetBuilder BUILDER = new NetBuilder();

    private HashMap<String, String> nameMap = new HashMap<>();

    private List<NetInfo> infos = new ArrayList<>();

    public NetBuilder() {
        super(CurrentPath.javaInfo(FullName.NET));
    }


    public void addNetInfo(NetInfo info) {
        infos.add(info);
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
        //Logs.i(lines);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_NET);
        Ts.ls(infos, new BaseTs.EachTs<NetInfo>() {
            @Override
            public boolean each(int position, NetInfo netInfo) {
                return false;
            }
        });
        fieldCount(0);
    }

}
/* model_temp_start
package [[pkg]];

public class Net {
                                                                                                    [<sub>][for][field]
    private static final String [name] = "[value]";
                                                                                                    [<sub>][for][field]

}

model_temp_end */

