package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.builder.base.ApiServiceBuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;

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

        methodCount(0);

        Ts.ls(methods, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement ee) {

                


                return false;
            }
        });


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
