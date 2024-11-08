package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.builder.base.MsThreadBuilderBase;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import javax.lang.model.element.ExecutableElement;

public class MsThreadBuilder extends MsThreadBuilderBase {

    private final String interfaceNameStr;
    private final String typeNameStr;
    private String startTypeStr;
    private BaseTs<ExecutableElement> mainThreadMethodTs;
    private BaseTs<ExecutableElement> subThreadMethodTs;

    public MsThreadBuilder(JavaInfo info, String interfaceName, String typeName) {
        super(info);
        this.interfaceNameStr = interfaceName;
        this.typeNameStr = typeName;
    }

    public void setStartTypeStr(String startTypeStr) {
        this.startTypeStr = startTypeStr;
    }


    public void setMainThreadMethodTs(BaseTs<ExecutableElement> mainThreadMethodTs) {
        this.mainThreadMethodTs = mainThreadMethodTs;
    }

    public void setSubThreadMethodTs(BaseTs<ExecutableElement> subThreadMethodTs) {
        this.subThreadMethodTs = subThreadMethodTs;
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_MSTHREAD);
        addTag(name, javaInfo.name);
        addTag(interfaceName, interfaceNameStr);
        addTag(typeName, typeNameStr);
        addTag(startType, startTypeStr);

        //sendMessageMethods
        dealMethod(true, mainThreadMethodTs, mainThreadDeal);
        dealMethod(false, subThreadMethodTs, subThreadDeal);

    }

    private void dealMethod(boolean isMain, BaseTs<ExecutableElement> methodTs, StringBuilder stringBuilder) {
        methodTs.ls(new Ts.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                String methodStaticName = ElementTools.staticSimpleName(element);
                String methodName = ElementTools.simpleName(element);
                addLnTag(stringBuilder, "        if (msg.what == type([FtpPlayActivityMSThreadType].[DEAL_TOAST])) {", typeName, methodStaticName);

                Params params = ElementTools.getMethodParamKvs(element);
                if (params.count() == 0) {
                    addLnTag(stringBuilder, "            dealer.[dealToast]();", methodName);
                } else if (params.count() == 1) {
                    KV<String, String> kv = params.getKvs().get(0);
                    addLnTag(stringBuilder, "            dealer.[dealToast](([String]) msg.obj);", methodName, kv.k);
                } else {
                    addLnTag(stringBuilder, "            Object[] objects = (Object[]) msg.obj;");
                    String param = params.getParam(new Params.Convert() {
                        @Override
                        public String convert(int index, KV<String, String> kv) {
                            return "(" + kv.k + ") objects[" + index + "]";
                        }
                    });
                    addLnTag(stringBuilder, "            dealer.[dealCheckData]([params]);", methodName, param);

                }
                addLnTag(stringBuilder, "            return;");
                addLnTag(stringBuilder, "        }");


                addLnTag(sendMessageMethods, "    public boolean sendMessageFor[DealToast]([String str]) {",
                        ConvertTool.toClassType(methodName), params.getMethodParams());
                addLnTag(sendMessageMethods, "        if ([isSubThread]()) {", isMain ? "isSubThread" : "isMainThread");
                addLnTag(sendMessageMethods, "            [sendMainMessage](type([FtpPlayActivityMSThreadType].[DEAL_TOAST])[, str]);"
                        , isMain ? "sendMainMessage" : "sendSubMessage", typeNameStr, methodStaticName, params.getParams(true, false));
                addLnTag(sendMessageMethods, "            return true;");
                addLnTag(sendMessageMethods, "        }");
                addLnTag(sendMessageMethods, "        return false;");
                addLnTag(sendMessageMethods, "    }");

                return false;
            }
        });
    }

}
/* model_temp_start
package [[pkg]];

import android.os.Message;

import com.codingtu.cooltu.lib4a.msthread.CoreMSThread;

public class [[name]] extends CoreMSThread {

    private [[interfaceName]] dealer;

    public static [[name]] obtain() {
        return new [[name]]();
    }

    public [[name]] dealer([[interfaceName]] dealer) {
        this.dealer = dealer;
        return this;
    }

    private int type([[typeName]] type) {
        return type.ordinal();
    }

    @Override
    protected int subThreadStartType() {
        return type([[typeName]].[[startType]]);
    }


    @Override
    protected void handleMessageInThread(Message msg) {
[[subThreadDeal]]
    }

    @Override
    protected void handleMessageInMain(Message msg) {
[[mainThreadDeal]]
    }

[[sendMessageMethods]]
}

model_temp_end */