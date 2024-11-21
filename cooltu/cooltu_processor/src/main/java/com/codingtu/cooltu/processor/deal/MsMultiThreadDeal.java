package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.pack.BoolValue;
import com.codingtu.cooltu.processor.annotation.msthread.MainThread;
import com.codingtu.cooltu.processor.annotation.msthread.SubThread;
import com.codingtu.cooltu.processor.builder.impl.MsThreadBuilder;
import com.codingtu.cooltu.processor.builder.impl.MsThreadInterfaceBuilder;
import com.codingtu.cooltu.processor.builder.impl.MsThreadTypeBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public class MsMultiThreadDeal extends TypeBaseDeal {

    @Override
    protected void dealTypeElement(TypeElement te) {
        String objClassSimpleName = ElementTools.simpleName(te);
        BoolValue hasStart = BoolValue.obtain();

        Map<String, Integer> staticMethodNameMap = new HashMap<>();
        BaseTs<String> staticMethodNameTs = Ts.ts();
        BaseTs<ExecutableElement> interfaceMethodTs = Ts.ts();

        ElementTools.ls(te.getEnclosedElements(), new Ts.EachTs<Element>() {
            @Override
            public boolean each(int position, Element element) {
                if (element instanceof ExecutableElement) {
                    ExecutableElement ee = (ExecutableElement) element;
                    MainThread mainThread = ee.getAnnotation(MainThread.class);
                    if (mainThread != null) {
                        String staticSimpleName = ElementTools.staticSimpleName(ee);
                        Integer num = staticMethodNameMap.get(staticSimpleName);
                        if (num == null) {
                            num = 0;
                        }
                        String typeName = staticSimpleName + "_" + num;
                        staticMethodNameMap.put(staticSimpleName, num + 1);
                        staticMethodNameTs.add(typeName);

                        interfaceMethodTs.add(ee);

                    }

                    SubThread subThread = ee.getAnnotation(SubThread.class);
                    if (subThread != null) {
                        String staticSimpleName = ElementTools.staticSimpleName(ee);
                        Integer num = staticMethodNameMap.get(staticSimpleName);
                        if (num == null) {
                            num = 0;
                        }
                        String typeName = staticSimpleName + "_" + num;
                        staticMethodNameMap.put(staticSimpleName, num + 1);
                        staticMethodNameTs.add(typeName);

                        interfaceMethodTs.add(ee);

                    }
                }
                return false;
            }
        });

        staticMethodNameTs.log();

        //typeJavaInfo
        JavaInfo typeJavaInfo = CurrentPath.msThreadType(objClassSimpleName);
        MsThreadTypeBuilder msThreadTypeBuilder = new MsThreadTypeBuilder(typeJavaInfo);
        msThreadTypeBuilder.setStaticMethodNameTs(staticMethodNameTs);

        //interfaceJavaInfo
        JavaInfo interfaceJavaInfo = CurrentPath.msThreadInterface(objClassSimpleName);
        MsThreadInterfaceBuilder msThreadInterfaceBuilder = new MsThreadInterfaceBuilder(interfaceJavaInfo);
        msThreadInterfaceBuilder.addMethods(interfaceMethodTs);

    }

}
