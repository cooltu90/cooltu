package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Maps;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.form.Form;
import com.codingtu.cooltu.processor.annotation.formbind.FormBind;
import com.codingtu.cooltu.processor.annotation.net.NetBack;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.LongClickView;
import com.codingtu.cooltu.processor.annotation.ui.Permission;
import com.codingtu.cooltu.processor.bean.ClickViewInfo;
import com.codingtu.cooltu.processor.bean.NetBackInfo;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActBackIntentBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.PassBuilder;
import com.codingtu.cooltu.processor.builder.impl.PermissionBuilder;
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

        Ts.ls(te.getEnclosedElements(), (position, element) -> {
            if (element instanceof ExecutableElement) {
                ExecutableElement ee = (ExecutableElement) element;
                ClickView clickView = ee.getAnnotation(ClickView.class);
                if (clickView != null) {
                    dealClickView(uiBaseBuilder, clickView, ee);
                }
                LongClickView longClickView = ee.getAnnotation(LongClickView.class);
                if (longClickView != null) {
                    dealLongClickView(uiBaseBuilder, longClickView, ee);
                }

                NetBack netBack = ee.getAnnotation(NetBack.class);
                if (netBack != null) {
                    dealNetBack(uiBaseBuilder, netBack, ee);
                }

                ActBack actBack = ee.getAnnotation(ActBack.class);
                if (actBack != null) {
                    dealActBack(uiBaseBuilder, actBack, ee);
                }

                Permission permission = ee.getAnnotation(Permission.class);
                if (permission != null) {
                    dealPermissionBack(baseBuilder, permission, ee);
                }

            }

            return false;
        });

        baseBuilder.form = te.getAnnotation(Form.class);
        baseBuilder.formBind = te.getAnnotation(FormBind.class);
    }

    private void dealLongClickView(UiBaseBuilder uiBaseBuilder, LongClickView clickView, ExecutableElement ee) {
        ClickViewInfo clickViewInfo = new ClickViewInfo();
        clickViewInfo.ids = Maps.map(IdTools.elementToIds(ee, LongClickView.class, clickView.value())).toValueTs().toList();
        clickViewInfo.method = ElementTools.simpleName(ee);
        clickViewInfo.methodParams = ElementTools.getMethodParamKvs(ee);
        clickViewInfo.isCheckLogin = clickView.checkLogin();
        clickViewInfo.isCheckForm = clickView.check();

        int inActCount = CountTool.count(clickView.inAct());
        Ts.ls(clickViewInfo.ids, new Ts.EachTs<IdTools.Id>() {
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

        uiBaseBuilder.longClickViews.add(clickViewInfo);
    }

    private void dealClickView(UiBaseBuilder uiBaseBuilder, ClickView clickView, ExecutableElement ee) {
        ClickViewInfo clickViewInfo = new ClickViewInfo();
        clickViewInfo.ids = Maps.map(IdTools.elementToIds(ee, ClickView.class, clickView.value())).toValueTs().toList();
        clickViewInfo.method = ElementTools.simpleName(ee);
        clickViewInfo.methodParams = ElementTools.getMethodParamKvs(ee);
        clickViewInfo.isCheckLogin = clickView.checkLogin();
        clickViewInfo.isCheckForm = clickView.check();

        int inActCount = CountTool.count(clickView.inAct());
        Ts.ls(clickViewInfo.ids, new Ts.EachTs<IdTools.Id>() {
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

    private void dealNetBack(UiBaseBuilder uiBaseBuilder, NetBack netBack, ExecutableElement ee) {
        NetBackInfo netBackInfo = new NetBackInfo();
        netBackInfo.netBack = netBack;
        netBackInfo.method = ee;
        uiBaseBuilder.netBacks.add(netBackInfo);
    }

    private void dealActBack(UiBaseBuilder uiBaseBuilder, ActBack actBack, ExecutableElement ee) {
        uiBaseBuilder.actBacks.add(actBack);
        uiBaseBuilder.actBackMethods.add(ee);

        ElementTools.getMethodParamKvs(ee).ls(new Ts.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                PassBuilder.BUILDER.add(kv);
                return false;
            }
        });

        ActBackIntentBuilder.BUILDER.add(actBack, ee);
    }


    private void dealPermissionBack(ActBaseBuilder baseBuilder, Permission permission, ExecutableElement ee) {
        PermissionBuilder.BUILDER.add(permission, ee);
        baseBuilder.permissions.add(permission);
        baseBuilder.permissionMethods.add(ee);
    }


}
