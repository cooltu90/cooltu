package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.net.method.GET;
import com.codingtu.cooltu.processor.annotation.net.method.POST;
import com.codingtu.cooltu.processor.builder.base.ApiServiceBuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;

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
            public boolean each(int position, ExecutableElement ee) {

                GET getMethod = ee.getAnnotation(GET.class);
                POST postMethod = ee.getAnnotation(POST.class);

                String netType = null;
                String apiUrl = null;
                if (getMethod != null) {
                    netType = FullName.RETROFIT_GET;
                    apiUrl = getMethod.value();
                } else if (postMethod != null) {
                    netType = FullName.RETROFIT_POST;
                    apiUrl = postMethod.value();
                }

                if (StringTool.isNotBlank(netType)) {
                    method(count[0]++, netType, apiUrl, ElementTools.simpleName(ee));
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
    );
                                                                                                    [<sub>][for][method]

}
model_temp_end */
