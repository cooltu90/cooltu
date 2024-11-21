package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.builder.base.MsMultiThreadBuilderBase;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.Map;
import java.util.Set;

import javax.lang.model.element.ExecutableElement;

public class MsMultiThreadBuilder extends MsMultiThreadBuilderBase {

    private final String interfaceNameStr;
    private final String typeNameStr;
    private Set<Integer> subThreadNumSet;
    private Map<String, ExecutableElement> mainMethodMap;

    public MsMultiThreadBuilder(JavaInfo info, String interfaceName, String typeName) {
        super(info);
        this.interfaceNameStr = interfaceName;
        this.typeNameStr = typeName;
    }

    public void addSubThreadNumSet(Set<Integer> subThreadNumSet) {
        this.subThreadNumSet = subThreadNumSet;
    }

    public void addMainMethodMap(Map<String, ExecutableElement> mainMethodMap) {
        this.mainMethodMap = mainMethodMap;
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_MSTHREAD);
        addTag(name, javaInfo.name);
        addTag(interfaceName, interfaceNameStr);
        addTag(typeName, typeNameStr);

        Ts.ts(subThreadNumSet).ls(new Ts.EachTs<Integer>() {
            @Override
            public boolean each(int position, Integer index) {

                addLnTag(subFields, "    private Handler subHandler[0];", index);

                addLnTag(createSubHandler, "        new Thread(new Runnable() {");
                addLnTag(createSubHandler, "            @Override");
                addLnTag(createSubHandler, "            public void run() {");
                addLnTag(createSubHandler, "                createSubHandler[0]();", index);
                addLnTag(createSubHandler, "            }");
                addLnTag(createSubHandler, "        }).start();");

                addLnTag(createSubHandlerMethods, "");
                addLnTag(createSubHandlerMethods, "    private void createSubHandler[0]() {", index);
                addLnTag(createSubHandlerMethods, "        Looper.prepare();");
                addLnTag(createSubHandlerMethods, "        subHandler[0] = new Handler(Looper.myLooper()) {", index);
                addLnTag(createSubHandlerMethods, "            @Override");
                addLnTag(createSubHandlerMethods, "            public void handleMessage(Message msg) {");
                addLnTag(createSubHandlerMethods, "                super.handleMessage(msg);");
                addLnTag(createSubHandlerMethods, "                handleMessageInThread[0](msg);", index);
                addLnTag(createSubHandlerMethods, "            }");
                addLnTag(createSubHandlerMethods, "        };");
                addLnTag(createSubHandlerMethods, "        sendMessage(subHandler[0], subThread[0]StartType());", index, index);
                addLnTag(createSubHandlerMethods, "        Looper.loop();");
                addLnTag(createSubHandlerMethods, "    }");

                addLnTag(checkThreadMethods, "");
                addLnTag(checkThreadMethods, "    protected boolean isSubThread[0]() {", index);
                addLnTag(checkThreadMethods, "        return Thread.currentThread() == subHandler[0].getLooper().getThread();", index);
                addLnTag(checkThreadMethods, "    }");


                addLnTag(subThreadMethods, "");
                addLnTag(subThreadMethods, "    ///////////////////////////////////////////////////////");
                addLnTag(subThreadMethods, "    //");
                addLnTag(subThreadMethods, "    // 线程[0]的消息处理", index);
                addLnTag(subThreadMethods, "    //");
                addLnTag(subThreadMethods, "    ///////////////////////////////////////////////////////");
                addLnTag(subThreadMethods, "    private int subThread[0]StartType() {", index);
                addLnTag(subThreadMethods, "        return 0;");
                addLnTag(subThreadMethods, "    }");
                addLnTag(subThreadMethods, "");
                addLnTag(subThreadMethods, "    private void handleMessageInThread[0](Message msg) {", index);
                addLnTag(subThreadMethods, "    }");

                return false;
            }
        });

        Ts.maps(mainMethodMap).ls(new Ts.MapEach<String, ExecutableElement>() {
            @Override
            public boolean each(String type, ExecutableElement element) {

                addLnTag(dealMainMessage, "        if (msg.what == type([SubThreadActivityMsThreadType].[DEAL_TOAST])) {", typeNameStr, type);

                String methodName = ElementTools.simpleName(element);
                Params params = ElementTools.getMethodParamKvs(element);
                if (params.count() == 0) {
                    addLnTag(dealMainMessage, "            dealer.[dealToast]();", methodName);
                } else if (params.count() == 1) {
                    KV<String, String> kv = params.getKvs().get(0);
                    addLnTag(dealMainMessage, "            dealer.[dealToast](([String]) msg.obj);", methodName, kv.k);
                } else {
                    addLnTag(dealMainMessage, "            Object[] objects = (Object[]) msg.obj;");
                    String param = params.getParam(new Params.Convert() {
                        @Override
                        public String convert(int index, KV<String, String> kv) {
                            return "(" + kv.k + ") objects[" + index + "]";
                        }
                    });
                    addLnTag(dealMainMessage, "            dealer.[dealCheckData]([params]);", methodName, param);

                }

                addLnTag(dealMainMessage, "            return;");
                addLnTag(dealMainMessage, "        }");

                addLnTag(sendMessageMethodsForMain, "");
                addLnTag(sendMessageMethodsForMain, "    public boolean sendMessageFor[DealToast]([String str]) {",
                        ConvertTool.toClassType(methodName), params.getMethodParams());
                addLnTag(sendMessageMethodsForMain, "        if (!isMainThread()) {");
                addLnTag(sendMessageMethodsForMain, "            sendMessage(mainHandler, type([FtpPlayActivityMSThreadType].[DEAL_TOAST])[, str]);"
                        , typeNameStr, type, params.getParams(true, false));
                addLnTag(sendMessageMethodsForMain, "            return true;");
                addLnTag(sendMessageMethodsForMain, "        }");
                addLnTag(sendMessageMethodsForMain, "        return false;");
                addLnTag(sendMessageMethodsForMain, "    }");

                return false;
            }
        });


    }

}
/* model_temp_start
package [[pkg]];

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.codingtu.cooltu.lib4a.msthread.CoreMultiMsThread;

public class [[name]] extends CoreMultiMsThread {

    ///////////////////////////////////////////////////////
    //
    // 创建方法
    //
    ///////////////////////////////////////////////////////
    private Handler mainHandler;
[[subFields]]
    public void start() {
        createMainHandler();
[[createSubHandler]]
    }

    private void createMainHandler() {
        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMessageInMain(msg);
            }
        };
    }
[[createSubHandlerMethods]]

    ///////////////////////////////////////////////////////
    //
    // 初始化方法
    //
    ///////////////////////////////////////////////////////
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
[[checkThreadMethods]]

    ///////////////////////////////////////////////////////
    //
    // 主线程的消息处理
    //
    ///////////////////////////////////////////////////////
    private void handleMessageInMain(Message msg) {
[[dealMainMessage]]
    }
[[sendMessageMethodsForMain]]
[[subThreadMethods]]
}

model_temp_end */