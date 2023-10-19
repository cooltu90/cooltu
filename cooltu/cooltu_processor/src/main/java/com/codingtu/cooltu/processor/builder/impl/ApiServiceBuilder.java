package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.net.Param;
import com.codingtu.cooltu.processor.annotation.net.ParamType;
import com.codingtu.cooltu.processor.annotation.net.method.GET;
import com.codingtu.cooltu.processor.annotation.net.method.POST;
import com.codingtu.cooltu.processor.builder.base.ApiServiceBuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

public class ApiServiceBuilder extends ApiServiceBuilderBase {

    private List<ExecutableElement> methods = new ArrayList<>();

    public ApiServiceBuilder(JavaInfo info) {
        super(info);
    }

    public void addMethod(ExecutableElement ee) {
        methods.add(ee);
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
        addTag(pkg, Pkg.CORE_NET_API);
        addTag(name, javaInfo.name);

        int[] count = {0};
        Ts.ls(methods, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int methodIndex, ExecutableElement ee) {


                GET getMethod = ee.getAnnotation(GET.class);
                if (getMethod != null) {
                    method(count[0]++, FullName.RETROFIT_GET, getMethod.value(), ElementTools.simpleName(ee));

                    List<? extends VariableElement> parameters = ee.getParameters();
                    int paramCount = CountTool.count(parameters);
                    methodParamCount(methodIndex, paramCount);
                    Ts.ls(parameters, (paramIndex, element) -> {
                        KV<String, String> kv = ElementTools.getFieldKv(element);
                        Param param = element.getAnnotation(Param.class);
                        String anno = FullName.RETROFIT_QUERY;

                        switch (param.type()) {
                            case ParamType.PATH:
                                anno = FullName.RETROFIT_PATH;
                                break;
                            case ParamType.HEADER:
                                anno = FullName.RETROFIT_HEADER;
                                break;
                        }

                        methodParam(methodIndex, paramIndex, anno, kv.k, kv.v, paramIndex != paramCount - 1 ? "," : "");
                        return false;
                    });
                }

                POST postMethod = ee.getAnnotation(POST.class);
                if (postMethod != null) {
                    method(count[0]++, FullName.RETROFIT_POST, postMethod.value(), ElementTools.simpleName(ee));
                    methodParamCount(methodIndex, 0);
                }
                return false;
            }
        });
        methodCount(count[0]);
    }

}
/* model_temp_start
package [[pkg]];

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public interface [[name]] {
                                                                                                    [<sub>][for][method]
    @[netType]("[apiUrl]")
    Flowable<Result<ResponseBody>> [methodName](
                                                                                                    [<sub>][for][methodParam]
            @[anno] [type] [name][divider]
                                                                                                    [<sub>][for][methodParam]
    );
                                                                                                    [<sub>][for][method]

}
model_temp_end */
