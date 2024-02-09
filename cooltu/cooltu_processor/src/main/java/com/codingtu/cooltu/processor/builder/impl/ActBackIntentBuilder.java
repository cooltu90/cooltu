package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.builder.base.ActBackIntentBuilderBase;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;

public class ActBackIntentBuilder extends ActBackIntentBuilderBase {

    public static ActBackIntentBuilder BUILDER = new ActBackIntentBuilder();
    private ListValueMap<String, String> CHECK_MAP = new ListValueMap<>();

    private List<String> methodNames = new ArrayList<>();
    private List<Params> methodParams = new ArrayList<>();


    public ActBackIntentBuilder() {
        super(CurrentPath.javaInfo(FullName.ACT_BACK_INTENT));
    }

    public void add(ActBack actBack, ExecutableElement ee) {
        String actBackClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return actBack.value();
            }
        });


        JavaInfo actBackJavaInfo = CurrentPath.javaInfo(actBackClass);
        String methodName = StringTool.cutSuffix(ConvertTool.toMethodType(actBackJavaInfo.name), Suffix.ACTIVITY);

        Params params = ElementTools.getMethodParamKvs(ee);

        String kParam = params.getParam(new Params.Convert() {
            @Override
            public String convert(int index, KV<String, String> kv) {
                return kv.k;
            }
        });

        List<String> list = CHECK_MAP.get(actBackClass);

        boolean has = Ts.ts(list).has(new Ts.IsThisOne<String>() {
            @Override
            public boolean isThisOne(int position, String s) {
                return s.equals(kParam);
            }
        });
        if (!has) {
            list.add(kParam);
            methodNames.add(methodName);
            methodParams.add(params);
        }
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
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        //Logs.i(lines);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_TOOLS);

        Ts.ls(methodNames, new Ts.EachTs<String>() {
            @Override
            public boolean each(int methodIndex, String methodName) {
                Params params = methodParams.get(methodIndex);
                LibLogs.i("===ActBackIntent=======================");
                LibLogs.i("methodIndex:" + methodIndex);
                LibLogs.i("methodName:" + methodName);
                LibLogs.i("params:" + params.getMethodParams());


                method(methodIndex, methodName, params.getMethodParams());
                params.ls(new Ts.EachTs<KV<String, String>>() {
                    @Override
                    public boolean each(int paramIndex, KV<String, String> kv) {
                        if (ClassTool.isBaseClass(kv.k)) {
                            methodParamOtherIf(methodIndex, paramIndex, FullName.PASS, ConvertTool.toStaticType(kv.v), kv.v);
                        } else {
                            methodParamBeanIf(methodIndex, paramIndex, FullName.PASS, ConvertTool.toStaticType(kv.v), FullName.JSON_TOOL, kv.v);
                        }
                        methodParam(methodIndex, paramIndex);
                        return false;
                    }
                });
                return false;
            }
        });

    }

    @Override
    protected void dealLinesInParent() {
        int method = count(methodCounts, getForKey("method"));
        LibLogs.i("methodCount:" + method);
        super.dealLinesInParent();
    }
}
/* model_temp_start
package [[pkg]];

import android.content.Intent;

public class ActBackIntent {
                                                                                                    [<sub>][for][method]
    public static Intent [methodName]([params]) {
        Intent intent = new Intent();
                                                                                                    [<sub>][for][methodParam]
                                                                                                    [<sub>][if][methodParamOther]
        intent.putExtra([passFullName].[field], [param]);
                                                                                                    [<sub>][if][methodParamOther]
                                                                                                    [<sub>][if][methodParamBean]
        intent.putExtra([passFullName].[field], [jsonToolFullName].toJson([param]));
                                                                                                    [<sub>][if][methodParamBean]
                                                                                                    [<sub>][for][methodParam]
        return intent;
    }
                                                                                                    [<sub>][for][method]
}
model_temp_end */