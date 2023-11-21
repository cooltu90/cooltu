package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.form.Form;
import com.codingtu.cooltu.processor.annotation.net.NetBack;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.Permission;
import com.codingtu.cooltu.processor.bean.ActBaseInfo;
import com.codingtu.cooltu.processor.bean.ClickViewInfo;
import com.codingtu.cooltu.processor.bean.NetBackInfo;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActBackIntentBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.PassBuilder;
import com.codingtu.cooltu.processor.builder.impl.PermissionBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
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
        ActBase baseAnno = te.getAnnotation(ActBase.class);
        String uiFullName = ElementTools.getType(te);
        JavaInfo baseJavaInfo = CurrentPath.actBase(uiFullName);
        ActBaseBuilder baseBuilder = new ActBaseBuilder(baseJavaInfo);
        UiBaseBuilder uiBaseBuilder = baseBuilder.getUiBaseBuilder();
        uiBaseBuilder.uiFullName = uiFullName;
        uiBaseBuilder.baseClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return baseAnno.base();
            }
        });

        if (ClassTool.isVoid(uiBaseBuilder.baseClass)) {
            uiBaseBuilder.baseClass = FullName.BASE_ACT;
        } else {
            map.get(uiBaseBuilder.baseClass).add(uiBaseBuilder.uiFullName);
        }

        if (baseAnno.layout() > 0) {
            uiBaseBuilder.layout = IdTools.elementToId(te, ActBase.class, baseAnno.layout());
            uiBaseBuilder.viewInfos = LayoutTools.convert(uiBaseBuilder.layout.rName);
        }

        /**************************************************
         *
         *
         *
         **************************************************/

        ActBaseInfo actBaseInfo = new ActBaseInfo();

        Ts.ls(te.getEnclosedElements(), (position, element) -> {
            if (element instanceof ExecutableElement) {
                ExecutableElement ee = (ExecutableElement) element;
                ClickView clickView = ee.getAnnotation(ClickView.class);
                if (clickView != null) {
                    dealClickView(uiBaseBuilder, clickView, ee);
                }

                NetBack netBack = ee.getAnnotation(NetBack.class);
                if (netBack != null) {
                    dealNetBack(actBaseInfo, netBack, ee);
                }

                ActBack actBack = ee.getAnnotation(ActBack.class);
                if (actBack != null) {
                    dealActBack(actBaseInfo, actBack, ee);
                }

                Permission permission = ee.getAnnotation(Permission.class);
                if (permission != null) {
                    dealPermissionBack(actBaseInfo, permission, ee);
                }

            }

            return false;
        });

        actBaseInfo.form = te.getAnnotation(Form.class);

        baseBuilder.addInfos(actBaseInfo);

    }

    private void dealPermissionBack(ActBaseInfo actBaseInfo, Permission permission, ExecutableElement ee) {
        PermissionBuilder.BUILDER.add(permission, ee);
        actBaseInfo.permissions.add(permission);
        actBaseInfo.permissionMethods.add(ee);
    }

    private void dealActBack(ActBaseInfo actBaseInfo, ActBack actBack, ExecutableElement ee) {
        actBaseInfo.actBacks.add(actBack);
        actBaseInfo.actBackMethods.add(ee);

        ElementTools.getMethodParamKvs(ee).ls(new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                PassBuilder.BUILDER.add(kv);
                return false;
            }
        });

        ActBackIntentBuilder.BUILDER.add(actBack, ee);
    }

    private void dealNetBack(ActBaseInfo actBaseInfo, NetBack netBack, ExecutableElement ee) {
        NetBackInfo netBackInfo = new NetBackInfo();
        netBackInfo.netBack = netBack;
        netBackInfo.method = ee;
        actBaseInfo.netBacks.add(netBackInfo);
    }

    private void dealClickView(UiBaseBuilder uiBaseBuilder, ClickView clickView, ExecutableElement ee) {
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

        uiBaseBuilder.clickViews.add(clickViewInfo);
    }
}
