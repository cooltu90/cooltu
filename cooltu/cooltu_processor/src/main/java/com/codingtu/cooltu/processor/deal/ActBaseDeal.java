package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.net.NetBack;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.bean.ActBaseInfo;
import com.codingtu.cooltu.processor.bean.ClickViewInfo;
import com.codingtu.cooltu.processor.bean.NetBackInfo;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@To(ActBaseBuilder.class)
public class ActBaseDeal extends TypeBaseDeal {

    public static ListValueMap<String, String> map = new ListValueMap<>();

    @Override
    protected void dealTypeElement(TypeElement te) {
        ActBase actBase = te.getAnnotation(ActBase.class);

        ActBaseInfo actBaseInfo = new ActBaseInfo();
        actBaseInfo.act = ElementTools.getType(te);

        actBaseInfo.baseClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return actBase.base();
            }
        });

        if (ClassTool.isVoid(actBaseInfo.baseClass)) {
            actBaseInfo.baseClass = FullName.BASE_ACT;
        } else {
            map.get(actBaseInfo.baseClass).add(actBaseInfo.act);
        }

        if (actBase.layout() > 0) {
            actBaseInfo.layout = IdTools.elementToId(te, ActBase.class, actBase.layout());
            actBaseInfo.viewInfos = LayoutTools.convert(actBaseInfo.layout.rName);
        }

        Ts.ls(te.getEnclosedElements(), (position, element) -> {
            if (element instanceof ExecutableElement) {
                ExecutableElement ee = (ExecutableElement) element;
                ClickView clickView = ee.getAnnotation(ClickView.class);
                if (clickView != null) {
                    dealClickView(actBaseInfo, clickView, ee);
                }

                NetBack netBack = ee.getAnnotation(NetBack.class);
                if (netBack != null) {
                    dealNetBack(actBaseInfo, netBack, ee);
                }

            }

            return false;
        });

        JavaInfo actBaseJavaInfo = CurrentPath.actBaseJavaInfo(actBaseInfo.act);
        ActBaseBuilder actBaseBuilder = new ActBaseBuilder(actBaseJavaInfo);
        actBaseBuilder.addInfos(actBaseInfo);

    }

    private void dealNetBack(ActBaseInfo actBaseInfo, NetBack netBack, ExecutableElement ee) {
        NetBackInfo netBackInfo = new NetBackInfo();
        netBackInfo.netBack = netBack;
        netBackInfo.method = ee;
        actBaseInfo.netBacks.add(netBackInfo);
    }

    private void dealClickView(ActBaseInfo actBaseInfo, ClickView clickView, ExecutableElement ee) {
        ClickViewInfo clickViewInfo = new ClickViewInfo();
        clickViewInfo.ids = Ts.ts(IdTools.elementToIds(ee, ClickView.class, clickView.value())).toList().get();
        clickViewInfo.method = ElementTools.simpleName(ee);
        clickViewInfo.methodParams = ElementTools.getMethodParamKvs(ee);

        int inActCount = CountTool.count(clickView.inAct());
        Ts.ls(clickViewInfo.ids, new BaseTs.EachTs<IdTools.Id>() {
            @Override
            public boolean each(int position, IdTools.Id id) {
                boolean inAct = true;
                if (position < inActCount) {
                    inAct = clickView.inAct()[position];
                }
                clickViewInfo.inAct.add(inAct);
                return false;
            }
        });

        actBaseInfo.clickViews.add(clickViewInfo);
    }
}
