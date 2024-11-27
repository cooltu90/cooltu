package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.builder.base.MsThreadBaseBuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.List;

import javax.lang.model.element.ExecutableElement;

public class MsThreadBaseBuilder extends MsThreadBaseBuilderBase {
    private final String interfaceNameStr;
    private final String msThreadTypeStr;
    private final String msThreadFieldName;
    public BaseTs<ExecutableElement> msThreadMethodTs = Ts.ts();

    public MsThreadBaseBuilder(JavaInfo info, String interfaceName, String typeName) {
        super(info);
        this.interfaceNameStr = interfaceName;
        this.msThreadTypeStr = typeName;
        this.msThreadFieldName = ConvertTool.toMethodType(msThreadTypeStr);
    }

//    @Override
//    protected boolean isBuild() {
//        return false;
//    }
//
//    @Override
//    protected void beforeBuild(List<String> lines) {
//        super.beforeBuild(lines);
//        Logs.i(lines);
//    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_MSTHREAD);
        addTag(name, javaInfo.name);
        addTag(interfaceType, interfaceNameStr);
        addTag(msThreadType, msThreadTypeStr);
        addTag(msThreadName, ConvertTool.toMethodType(msThreadTypeStr));

        msThreadMethodTs.ls(new Ts.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                Params params = ElementTools.getMethodParamKvs(element);

                String simpleName = ElementTools.simpleName(element);
                String methodParams = params.getMethodParams();
                String sendMethodName = ConvertTool.toClassType(simpleName);

                addLnTag(methods, "    @Override");
                addLnTag(methods, "    public void [dealDataStart]([params]) {",
                        simpleName, methodParams);
                addLnTag(methods, "    }");
                addLnTag(methods, "");

                addLnTag(methods, "    protected boolean sendMessageFor[DealToast]([String str]) {",
                        sendMethodName, methodParams);
                addLnTag(methods, "        return [ftpPlayActivityMSThread].sendMessageFor[DealToast]([str]);",
                        msThreadFieldName, sendMethodName, params.getParams());
                addLnTag(methods, "    }");

                return false;
            }
        });


    }
}
/* model_temp_start
package [[pkg]];

public class [[name]] implements [[interfaceType]] {

    protected [[msThreadType]] [[msThreadName]];

    public void start() {
        [[msThreadName]] = [[msThreadType]].obtain().dealer(this);
        [[msThreadName]].start();
    }

[[methods]]
}
model_temp_end */