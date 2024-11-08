package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.builder.base.MsThreadInterfaceBuilderBase;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import javax.lang.model.element.ExecutableElement;

public class MsThreadInterfaceBuilder extends MsThreadInterfaceBuilderBase {

    BaseTs<ExecutableElement> allMethodTs = Ts.ts();

    @Override
    protected boolean isBuild() {
        return true;
    }

    public MsThreadInterfaceBuilder(JavaInfo info) {
        super(info);
    }

    public void add(ExecutableElement element) {
        allMethodTs.add(element);
    }

    public void addMethods(BaseTs<ExecutableElement> methodTs) {
        allMethodTs.add(methodTs);
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_MSTHREAD);
        addTag(name, javaInfo.name);
        allMethodTs.ls(new Ts.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                addLnTag(methods, "");
                addLnTag(methods, "    void [dealCheckData]([String name, int age]);",
                        ElementTools.simpleName(element), ElementTools.getMethodParamKvs(element).getMethodParams());

                return false;
            }
        });
    }


}
/* model_temp_start
package [[pkg]];

public interface [[name]] {
[[methods]]
}
model_temp_end */