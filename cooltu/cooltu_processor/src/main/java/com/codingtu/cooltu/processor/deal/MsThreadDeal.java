package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.pack.BoolValue;
import com.codingtu.cooltu.lib4j.ts.pack.TValue;
import com.codingtu.cooltu.processor.annotation.msthread.MainThread;
import com.codingtu.cooltu.processor.annotation.msthread.SubThread;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.MsThreadBuilder;
import com.codingtu.cooltu.processor.builder.impl.MsThreadInterfaceBuilder;
import com.codingtu.cooltu.processor.builder.impl.MsThreadTypeBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public class MsThreadDeal extends TypeBaseDeal {

    @Override
    protected void dealTypeElement(TypeElement te) {
        //com.codingtu.cooltu.ui.SubThreadActivity
        String objClassSimpleName = ElementTools.simpleName(te);

        BaseTs<ExecutableElement> mainThreadMethodTs = Ts.ts();
        BaseTs<ExecutableElement> subThreadMethodTs = Ts.ts();
        BaseTs<String> staticMethodNameTs = Ts.ts();

        BoolValue hasStart = BoolValue.obtain();
        TValue<String> startStaticSimpleName = TValue.obtain();
        ElementTools.ls(te.getEnclosedElements(), new Ts.EachTs<Element>() {
            @Override
            public boolean each(int position, Element element) {
                if (element instanceof ExecutableElement) {
                    ExecutableElement ee = (ExecutableElement) element;
                    MainThread mainThread = ee.getAnnotation(MainThread.class);
                    if (mainThread != null) {
                        mainThreadMethodTs.add(ee);
                        staticMethodNameTs.add(ElementTools.staticSimpleName(ee));
                    }

                    SubThread subThread = ee.getAnnotation(SubThread.class);
                    if (subThread != null) {
                        String staticSimpleName = ElementTools.staticSimpleName(ee);
                        if (subThread.isStart()) {
                            hasStart.value = true;
                            startStaticSimpleName.value = staticSimpleName;
                        }
                        subThreadMethodTs.add(ee);
                        staticMethodNameTs.add(staticSimpleName);
                    }

                }
                return false;
            }
        });

        if (!hasStart.value) {
            return;
        }
        //typeJavaInfo
        JavaInfo typeJavaInfo = CurrentPath.msThreadType(objClassSimpleName);
        MsThreadTypeBuilder msThreadTypeBuilder = new MsThreadTypeBuilder(typeJavaInfo);
        msThreadTypeBuilder.setStaticMethodNameTs(staticMethodNameTs);

        //interfaceJavaInfo
        JavaInfo interfaceJavaInfo = CurrentPath.msThreadInterface(objClassSimpleName);
        MsThreadInterfaceBuilder msThreadInterfaceBuilder = new MsThreadInterfaceBuilder(interfaceJavaInfo);
        msThreadInterfaceBuilder.addMethods(mainThreadMethodTs);
        msThreadInterfaceBuilder.addMethods(subThreadMethodTs);

        //msThreadJavaInfo
        JavaInfo msThreadJavaInfo = CurrentPath.msThread(objClassSimpleName);
        MsThreadBuilder msThreadBuilder = new MsThreadBuilder(msThreadJavaInfo, interfaceJavaInfo.name, typeJavaInfo.name);
        msThreadBuilder.setStartTypeStr(startStaticSimpleName.value);
        msThreadBuilder.setMainThreadMethodTs(mainThreadMethodTs);
        msThreadBuilder.setSubThreadMethodTs(subThreadMethodTs);

        ActBase actBase = te.getAnnotation(ActBase.class);
        if (actBase != null) {
            ActBaseBuilder builder = CurrentPath.actBaseBuilder(ElementTools.getType(te));
            builder.msThreadInterfaceFullName = interfaceJavaInfo.fullName;
            builder.msThreadFullName = msThreadJavaInfo.fullName;
            builder.msThreadFieldName = ConvertTool.toMethodType(msThreadJavaInfo.name);
            builder.msThreadMethodTs.add(mainThreadMethodTs);
            builder.msThreadMethodTs.add(subThreadMethodTs);
        }
    }

}
