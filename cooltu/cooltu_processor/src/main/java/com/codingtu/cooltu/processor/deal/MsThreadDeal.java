package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.map.ValueMap;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.pack.BoolValue;
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public class MsThreadDeal extends TypeBaseDeal {

    @Override
    protected void dealTypeElement(TypeElement te) {
        String objClassSimpleName = ElementTools.simpleName(te);

        Map<String, Integer> staticMethodNameMap = new HashMap<>();
        BaseTs<String> staticMethodNameTs = Ts.ts();
        BaseTs<ExecutableElement> interfaceMethodTs = Ts.ts();
        Set<Integer> subThreadNumSet = new HashSet<>();
        Map<String, ExecutableElement> mainMethodMap = new HashMap<>();
        ValueMap<Integer, Map<String, ExecutableElement>> subMethodMap = new ValueMap<Integer, Map<String, ExecutableElement>>() {
            @Override
            protected Map<String, ExecutableElement> newValue() {
                return new HashMap<>();
            }
        };

        Map<Integer, Integer> hasStartMap = new HashMap<>();
        Map<Integer, String> startTypeMap = new HashMap<>();

        BaseTs<ExecutableElement> allMethodTs = Ts.ts();




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

                        mainMethodMap.put(typeName, ee);

                        allMethodTs.add(ee);
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

                        subThreadNumSet.add(subThread.value());

                        Integer startNum = hasStartMap.get(subThread.value());
                        if (startNum == null) {
                            startNum = 0;
                        }
                        if (subThread.isStart()) {
                            startTypeMap.put(subThread.value(), typeName);
                            startNum++;
                        }
                        hasStartMap.put(subThread.value(), startNum);

                        Map<String, ExecutableElement> subMethodMap1 = subMethodMap.get(subThread.value());
                        subMethodMap1.put(typeName, ee);

                        allMethodTs.add(ee);

                    }
                }
                return false;
            }
        });

        BoolValue hasStart = BoolValue.obtain(true);
        Ts.maps(hasStartMap).ls(new Ts.MapEach<Integer, Integer>() {
            @Override
            public boolean each(Integer thread, Integer startNum) {
                if (startNum != 1) {
                    hasStart.value = false;
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
        msThreadInterfaceBuilder.addMethods(interfaceMethodTs);

        //msThreadJavaInfo
        JavaInfo msThreadJavaInfo = CurrentPath.msThread(objClassSimpleName);
        MsThreadBuilder msThreadBuilder = new MsThreadBuilder(msThreadJavaInfo, interfaceJavaInfo.name, typeJavaInfo.name);
        msThreadBuilder.addSubThreadNumSet(subThreadNumSet);
        msThreadBuilder.addMainMethodMap(mainMethodMap);
        msThreadBuilder.addSubMethodMap(subMethodMap);
        msThreadBuilder.addStartTypeMap(startTypeMap);

        ActBase actBase = te.getAnnotation(ActBase.class);
        if (actBase != null) {
            ActBaseBuilder builder = CurrentPath.actBaseBuilder(ElementTools.getType(te));
            builder.msThreadInterfaceFullName = interfaceJavaInfo.fullName;
            builder.msThreadFullName = msThreadJavaInfo.fullName;
            builder.msThreadFieldName = ConvertTool.toMethodType(msThreadJavaInfo.name);
            builder.msThreadMethodTs.add(allMethodTs);
        }


    }

}
