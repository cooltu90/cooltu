package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.net.Apis;
import com.codingtu.cooltu.processor.bean.NetInfo;
import com.codingtu.cooltu.processor.builder.impl.ApiServiceBuilder;
import com.codingtu.cooltu.processor.builder.impl.NetBuilder;
import com.codingtu.cooltu.processor.builder.impl.NetParamsBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public class NetDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {

        Apis apis = te.getAnnotation(Apis.class);
        String apiName = ElementTools.simpleName(te);
        JavaInfo javaInfo = CurrentPath.javaInfo(Pkg.CORE_NET_API + "." + apiName + Suffix.API_SERVICE);
        ApiServiceBuilder apiServiceBuilder = new ApiServiceBuilder(javaInfo);

        Ts.ls(te.getEnclosedElements(), (position, element) -> {
            if (element instanceof ExecutableElement) {
                ExecutableElement ee = (ExecutableElement) element;
                apiServiceBuilder.addMethod(ee);
                if (!CountTool.isNull(ee.getParameters())) {
                    new NetParamsBuilder(CurrentPath.javaInfo(
                            Pkg.CORE_NET_PARAMS
                                    + "."
                                    + ConvertTool.toClassType(ElementTools.simpleName(element))
                                    + Suffix.NET_PARAMS),ElementTools.getMethodParamKvs(ee));
                }

                NetInfo netInfo = new NetInfo();


                NetBuilder.BUILDER.addNetInfo(netInfo);

            }
            return false;
        });

    }
}
