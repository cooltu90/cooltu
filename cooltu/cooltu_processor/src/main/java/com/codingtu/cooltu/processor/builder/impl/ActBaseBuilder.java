package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.annotation.form.view.BindEdieText;
import com.codingtu.cooltu.processor.annotation.form.FormBean;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.annotation.form.view.BindTextView;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.Permission;
import com.codingtu.cooltu.processor.bean.ActBaseInfo;
import com.codingtu.cooltu.processor.bean.BindInfo;
import com.codingtu.cooltu.processor.bean.ClickViewInfo;
import com.codingtu.cooltu.processor.bean.NetBackInfo;
import com.codingtu.cooltu.processor.builder.base.ActBaseBuilderBase;
import com.codingtu.cooltu.processor.deal.ActBaseDeal;
import com.codingtu.cooltu.processor.deal.FormBeanDeal;
import com.codingtu.cooltu.processor.deal.NetDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

@To(ActBaseDeal.class)
public class ActBaseBuilder extends ActBaseBuilderBase {


    private ActBaseInfo info;
    private List<KV<String, String>> inBases = new ArrayList<>();
    private HashMap<String, String> inBaseMap = new HashMap<>();

    public ActBaseBuilder(JavaInfo info) {
        super(info);
    }

    @Override
    protected BuilderType getBuilderType() {
        return BuilderType.actBase;
    }

    @Override
    public String obtainSymbol() {
        return javaInfo.fullName;
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected boolean isGetLines() {
        return true;
    }


    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        if (javaInfo.name.equals("FormActivityBase")) {
            Logs.i(lines);
        }
    }

    public void addInfos(ActBaseInfo actBaseInfo) {
        this.info = actBaseInfo;
    }

    public void addInBase(KV<String, String> fieldKv) {
        inBases.add(fieldKv);
    }

    public void removeInBase(KV<String, String> kv) {
        inBaseMap.put(kv.v, kv.v);
    }

    public ActBaseInfo getActBaseInfo() {
        return this.info;
    }

    @Override
    protected void dealLines() {
        addTag(pkg, javaInfo.pkg);
        addTag(name, javaInfo.name);
        addTag(baseClass, info.baseClass);
        addTag(netBackIFullName, FullName.NET_BACK_I);
        addTag(coreSendParamsFullName, FullName.CORE_SEND_PARAMS);

        layoutIf(info.layout != null);
        if (info.layout != null) {
            layoutIf(info.layout.toString());
        }

        final int[] fieldCount = {0};

        findViewCount(CountTool.count(info.viewInfos));
        Ts.ls(info.viewInfos, new BaseTs.EachTs<LayoutTools.ViewInfo>() {
            @Override
            public boolean each(int position, LayoutTools.ViewInfo viewInfo) {
                if (inBaseMap.get(viewInfo.fieldName) == null) {
                    field(fieldCount[0]++, Constant.SIGN_PROTECTED, viewInfo.tag, viewInfo.fieldName);
                }

                String parent = "";
                if (!viewInfo.fieldName.equals(viewInfo.id)) {
                    parent = viewInfo.parent.fieldName + ".";
                }
                findView(position, viewInfo.fieldName, parent, Pkg.R, viewInfo.id);
                return false;
            }
        });

        Ts.ls(inBases, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                field(fieldCount[0]++, Constant.SIGN_PROTECTED, kv.k, kv.v);
                return false;
            }
        });

        final int[] setOnClickCount = {0};

        int clickViewsCount = CountTool.count(info.clickViews);

        onClickMethodsCount(clickViewsCount);

        onClickSwithCount(clickViewsCount);

        Ts.ls(info.clickViews, new BaseTs.EachTs<ClickViewInfo>() {
            @Override
            public boolean each(int clickViewInfoIndex, ClickViewInfo info) {
                onClickMethods(clickViewInfoIndex, info.method, info.methodParams.getMethodParams());
                onClickSwith(clickViewInfoIndex, info.method);
                onClickCaseCount(clickViewInfoIndex, CountTool.count(info.ids));

                List<KV<String, String>> kvs = info.methodParams.getKvs();
                int kvCount = CountTool.count(kvs);

                onClickSwitchParamsIf(clickViewInfoIndex, false);
                onClickSwitchParamsCount(clickViewInfoIndex, 0);

                Ts.ls(kvs, new BaseTs.EachTs<KV<String, String>>() {

                    private int paramsIndex;

                    @Override
                    public boolean each(int kvIndex, KV<String, String> kv) {
                        String divider = (kvIndex != kvCount - 1) ? "," : "";
                        if (kvIndex == 0 && FullName.VIEW.equals(kv.k)) {
                            onClickSwitchParamsIf(clickViewInfoIndex, true);
                            onClickSwitchParamsIf(clickViewInfoIndex, divider);
                        } else {
                            onClickSwitchParams(clickViewInfoIndex, paramsIndex, kv.k, Pkg.LIB4A, paramsIndex + "", divider);
                            paramsIndex++;
                            onClickSwitchParamsCountAdd(clickViewInfoIndex);
                        }
                        return false;
                    }
                });

                Ts.ls(info.ids, new BaseTs.EachTs<IdTools.Id>() {
                    @Override
                    public boolean each(int idIndex, IdTools.Id id) {
                        onClickCase(clickViewInfoIndex, idIndex, id.toString());
                        if (info.inAct.get(clickViewInfoIndex)) {
                            BaseTools.getActBaseInfoWithParents(getActBaseInfo(), new BaseTs.EachTs<ActBaseInfo>() {
                                @Override
                                public boolean each(int position, ActBaseInfo actBaseInfo) {

                                    Ts.ts(actBaseInfo.viewInfos).convert(new BaseTs.Convert<LayoutTools.ViewInfo, LayoutTools.ViewInfo>() {
                                        @Override
                                        public LayoutTools.ViewInfo convert(int index, LayoutTools.ViewInfo viewInfo) {
                                            if (viewInfo.id.equals(id.rName)) {
                                                setOnClick(setOnClickCount[0], viewInfo.fieldName);
                                                setOnClickCount[0]++;
                                            }
                                            return null;
                                        }
                                    });
                                    return false;
                                }
                            });
                        }
                        return false;
                    }
                });
                return false;
            }
        });
        setOnClickCount(setOnClickCount[0]);


        //onclick继承
        superOnClickIf(info.hasBaseClass());

        //colorStr
        int count = CountTool.count(info.colorStrs);
        Ts.ls(info.colorStrs, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                if (inBaseMap.get(kv.k) == null) {
                    field(fieldCount[0]++, Constant.SIGN_PROTECTED, "int", kv.k);
                }
                colorStrInit(position, kv.k, kv.v);
                return false;
            }
        });
        colorStrInitCount(count);

        //colorRes
        int colorResCount = CountTool.count(info.colorReses);
        colorResInitCount(colorResCount);
        Ts.ls(info.colorReses, new BaseTs.EachTs<KV<String, IdTools.Id>>() {
            @Override
            public boolean each(int position, KV<String, IdTools.Id> kv) {
                if (inBaseMap.get(kv.k) == null) {
                    field(fieldCount[0]++, Constant.SIGN_PROTECTED, "int", kv.k);
                }
                colorResInit(position, kv.k, FullName.RESOURCE_TOOL, kv.v.toString());
                return false;
            }
        });

        //dp
        int dpCount = CountTool.count(info.dps);
        dpInitCount(dpCount);
        Ts.ls(info.dps, new BaseTs.EachTs<KV<String, Float>>() {
            @Override
            public boolean each(int position, KV<String, Float> kv) {
                if (inBaseMap.get(kv.k) == null) {
                    field(fieldCount[0]++, Constant.SIGN_PROTECTED, "int", kv.k);
                }
                dpInit(position, kv.k, FullName.MOBILE_TOOL, kv.v + "f");
                return false;
            }
        });

        //colorRes
        int dimenCount = CountTool.count(info.dimens);
        dimenInitCount(dimenCount);
        Ts.ls(info.dimens, new BaseTs.EachTs<KV<String, IdTools.Id>>() {
            @Override
            public boolean each(int position, KV<String, IdTools.Id> kv) {
                if (inBaseMap.get(kv.k) == null) {
                    field(fieldCount[0]++, Constant.SIGN_PROTECTED, "int", kv.k);
                }
                dimenInit(position, kv.k, FullName.RESOURCE_TOOL, kv.v.toString());
                return false;
            }
        });

        //startField
        int startCount = CountTool.count(info.starts);
        startInitCount(startCount);
        Ts.ls(info.starts, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                if (inBaseMap.get(kv.v) == null) {
                    field(fieldCount[0]++, Constant.SIGN_PROTECTED, kv.k, kv.v);
                }
                startInit(position, kv.v, FullName.PASS);
                return false;
            }
        });

        //accept
        superAcceptIf(info.hasBaseClass());
        acceptCount(0);
        acceptMethodCount(0);
        Ts.ls(info.netBacks, new BaseTs.EachTs<NetBackInfo>() {
            @Override
            public boolean each(int position, NetBackInfo netBackInfo) {

                String mockClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                    @Override
                    public Object get() {
                        return netBackInfo.netBack.mock();
                    }
                });

                String methodName = ElementTools.simpleName(netBackInfo.method);

                String methodBaseName = StringTool.cutSuffix(methodName, Suffix.NET_BACK);

                String netBackFullName = CurrentPath.netBackFullName(methodBaseName);
                String sendParamsFullName = CurrentPath.sendParamsFullName(methodBaseName);

                Params params = ElementTools.getMethodParamKvs(netBackInfo.method);
                String paramStr = params.getParam(new Params.Convert() {
                    @Override
                    public String convert(int index, KV<String, String> kv) {
                        if (ClassTool.isType(kv.k, netBackFullName)) {
                            return "this";
                        }

                        if (ClassTool.isType(kv.k, sendParamsFullName)) {
                            return "(" + sendParamsFullName + ")params";
                        }

                        String returnType = NetDeal.RETURN_TYPES.get(netBackFullName);
                        if (ClassTool.isType(kv.k, returnType)) {
                            String mock = ClassTool.isVoid(mockClass) ? "" : ("new " + mockClass + "().");
                            if (ClassTool.isList(returnType)) {
                                String beanType = StringTool.getSub(returnType, "List", "<", ">");
                                return mock + ConvertTool.toMethodType(CurrentPath.javaInfo(beanType).name) + "s";
                            } else {
                                return mock + ConvertTool.toMethodType(CurrentPath.javaInfo(returnType).name);
                            }
                        } else if (ClassTool.isList(kv.k)) {
                            return "objs";
                        }

                        return null;
                    }
                });

                accept(position, methodName, netBackFullName, FullName.CORE_SEND_PARAMS, paramStr);
                acceptCountAdd();


                String methodParamStr = params.getParam(new Params.Convert() {
                    @Override
                    public String convert(int index, KV<String, String> kv) {
                        return kv.k + " " + kv.v;
                    }
                });


                acceptMethod(position, methodName, methodParamStr);
                acceptMethodCountAdd();
                return false;
            }
        });

        actBackCount(0);
        actBackMethodCount(0);
        Ts.ls(info.actBacks, new BaseTs.EachTs<ActBack>() {
            @Override
            public boolean each(int actBackIndex, ActBack actBack) {
                ExecutableElement ee = info.actBackMethods.get(actBackIndex);
                String methodName = ElementTools.simpleName(ee);

                String fromClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                    @Override
                    public Object get() {
                        return actBack.value();
                    }
                });

                JavaInfo fromJavaInfo = CurrentPath.javaInfo(fromClass);

                actBack(actBackIndex, actBackIndex == 0 ? "if" : "else if", FullName.CODE_4_REQUEST, ConvertTool.toStaticType(fromJavaInfo.name), methodName);
                actBackCountAdd();

                actBackParamCount(actBackIndex, 0);
                Params params = ElementTools.getMethodParamKvs(ee);
                params.ls(new BaseTs.EachTs<KV<String, String>>() {
                    @Override
                    public boolean each(int paramIndex, KV<String, String> kv) {
                        actBackParamCountAdd(actBackIndex);
                        actBackParam(actBackIndex, paramIndex, FullName.PASS, kv.v);
                        actBackParamDividerIf(actBackIndex, paramIndex, paramIndex != (CountTool.count(ee.getParameters()) - 1));
                        return false;
                    }
                });

                actBackMethod(actBackIndex, methodName, params.getMethodParams());
                actBackMethodCountAdd();
                return false;
            }
        });

        permissionBackCount(0);
        permissionBackMethodCount(0);
        Ts.ls(info.permissions, new BaseTs.EachTs<Permission>() {
            @Override
            public boolean each(int permissionIndex, Permission permission) {
                ExecutableElement ee = info.permissionMethods.get(permissionIndex);

                String methodName = ElementTools.simpleName(ee);
                String methodNameStatic = ConvertTool.toStaticType(methodName);

                String actName = CurrentPath.javaInfo(ElementTools.getParentType(ee)).name;
                String actNameStatic = ConvertTool.toStaticType(actName);

                permissionBack(permissionIndex, permissionIndex == 0 ? "if" : "else if",
                        FullName.PERMISSIONS, methodNameStatic, actNameStatic, methodName);
                permissionBackCountAdd();

                boolean isParam = !CountTool.isNull(ee.getParameters());

                allowIf(permissionIndex, isParam);
                if (isParam) {
                    allowIf(permissionIndex, FullName.PERMISSION_TOOL);
                }


                permissionBackMethodCountAdd();
                permissionBackMethod(permissionIndex, methodName);

                allowParamIf(permissionIndex, isParam);

                return false;
            }
        });

        onCreateCompleteInitIf(!info.hasChild());

        //form
        if (info.form != null) {
            String formBeanClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return info.form.value();
                }
            });
            TypeElement formBeanTe = FormBeanDeal.MAP.get(formBeanClass);

            String name = formBeanTe.getAnnotation(FormBean.class).value();
            if (StringTool.isBlank(name)) {
                name = ConvertTool.toMethodType(CurrentPath.javaInfo(formBeanClass).name);
            }
            field(fieldCount[0]++, Constant.SIGN_PROTECTED, formBeanClass, name);
            field(fieldCount[0]++, Constant.SIGN_PROTECTED, "boolean", "initFormBean");
            field(fieldCount[0]++, Constant.SIGN_PUBLIC, "BindHandler", "bindHandler");

            bindHandlerIf(true);
            bindHandlerIf(formBeanClass, name);

            formInitIf(true);
            formInitIf(name, formBeanClass);

            dealFormBean(formBeanTe);


        } else {
            bindHandlerIf(false);
            formInitIf(false);
        }

        fieldCount(fieldCount[0]);

    }

    private void dealFormBean(TypeElement te) {

        HashMap<Integer, Integer> indexMap = new HashMap<>();

        Ts.ts(te.getEnclosedElements()).ls((position, element) -> {
            if (element instanceof VariableElement) {
                VariableElement ve = (VariableElement) element;
                BindEdieText bindEdieText = ve.getAnnotation(BindEdieText.class);
                if (bindEdieText != null) {
                    String viewName = bindEdieText.name();
                    if (StringTool.isBlank(viewName)) {
                        IdTools.Id id = IdTools.elementToId(ve, BindEdieText.class, bindEdieText.value());
                        viewName = id.rName;
                    }
                    Integer index = indexMap.get(FormType.EDIT_TEXT);

                }

            }
            return false;
        });
    }

}
/* model_temp_start
package [[pkg]];

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class [[name]] extends [[baseClass]] implements View.OnClickListener, [[netBackIFullName]] {
                                                                                                    [<sub>][for][field]
    [sign] [type] [name];
                                                                                                    [<sub>][for][field]

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                                                                                                    [<sub>][if][layout]
        setContentView([layout]);
                                                                                                    [<sub>][if][layout]
                                                                                                    [<sub>][for][findView]
        [fieldName] = [parent]findViewById([rPkg].R.id.[id]);
                                                                                                    [<sub>][for][findView]
                                                                                                    [<sub>][for][setOnClick]
        [fieldName].setOnClickListener(this);
                                                                                                    [<sub>][for][setOnClick]
                                                                                                    [<sub>][for][colorStrInit]
        [name] = android.graphics.Color.parseColor("[color]");
                                                                                                    [<sub>][for][colorStrInit]
                                                                                                    [<sub>][for][colorResInit]
        [name] = [resourceToolFullName].getColor([id]);
                                                                                                    [<sub>][for][colorResInit]
                                                                                                    [<sub>][for][dpInit]
        [name] = [mobileToolFullName].dpToPx([value]);
                                                                                                    [<sub>][for][dpInit]
                                                                                                    [<sub>][for][dimenInit]
        [name] = [resourceToolFullName].getDimen([id]);
                                                                                                    [<sub>][for][dimenInit]
                                                                                                    [<sub>][for][startInit]
        [name] = [passFullName].[name](getIntent());
                                                                                                    [<sub>][for][startInit]
                                                                                                    [<sub>][if][formInit]
        if ([name] == null) {
            [name] = new [type]();
            initFormBean = true;
        }
        bindHandler = new BindHandler([name]);
                                                                                                    [<sub>][if][formInit]
                                                                                                    [<sub>][if][onCreateCompleteInit]
        onCreateComplete();
                                                                                                    [<sub>][if][onCreateCompleteInit]
    }

    @Override
    public void onClick(View v) {
                                                                                                    [<sub>][if][superOnClick]
        super.onClick(v);
                                                                                                    [<sub>][if][superOnClick]
        switch (v.getId()) {
                                                                                                    [<sub>][for][onClickSwith]
                                                                                                    [<sub>][for][onClickCase]
            case [id]:
                                                                                                    [<sub>][for][onClickCase]
                [methodName](
                                                                                                    [<sub>][if][onClickSwitchParams]
                        v[divider]
                                                                                                    [<sub>][if][onClickSwitchParams]
                                                                                                    [<sub>][for][onClickSwitchParams]
                        ([type]) v.getTag([pkg].R.id.tag_[index])[divider]
                                                                                                    [<sub>][for][onClickSwitchParams]
                );
                break;
                                                                                                    [<sub>][for][onClickSwith]
        }
    }

                                                                                                    [<sub>][for][onClickMethods]
    protected void [methodName]([params]) {}
                                                                                                    [<sub>][for][onClickMethods]
    @Override
    public void accept(String code, Result<ResponseBody> result, [[coreSendParamsFullName]] params, List objs) {
                                                                                                    [<sub>][if][superAccept]
        super.accept(code, result, params, objs);
                                                                                                    [<sub>][if][superAccept]

                                                                                                    [<sub>][for][accept]
        if ("[methodName]".equals(code)) {
            new [netBackFullName]() {
                @Override
                public void accept(String code, Result<ResponseBody> result, [coreSendParamsFullName] params, List objs) {
                    super.accept(code, result, params, objs);
                    [methodName]([params]);
                }
            }.accept(code, result, params, objs);
        }
                                                                                                    [<sub>][for][accept]
    }
                                                                                                    [<sub>][for][acceptMethod]
    protected void [methodName]([params]) {}
                                                                                                    [<sub>][for][acceptMethod]
    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
                                                                                                    [<sub>][for][actBack]
            [ifSign] (requestCode == [code4RequestFullName].[code]) {
                [methodName]([for:actBackParam][passFullName].[name](data)[if:actBackParamDivider], [if:actBackParamDivider][for:actBackParam]);
            }
                                                                                                    [<sub>][for][actBack]
        }
    }
                                                                                                    [<sub>][for][actBackMethod]
    protected void [methodName]([params]) {}
                                                                                                    [<sub>][for][actBackMethod]
    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {
        super.back(requestCode, permissions, grantResults);
                                                                                                    [<sub>][for][permissionBack]
        [ifSign] (requestCode == [permissionsFullName].CODE_[methodNameStatic]_IN_[actStaticName]) {
            [methodName]([if:allow][permissionToolFullName].allow(grantResults)[if:allow]);
        }
                                                                                                    [<sub>][for][permissionBack]
    }
                                                                                                    [<sub>][for][permissionBackMethod]
    protected void [methodName]([if:allowParam]boolean isAllow[if:allowParam]) {}
                                                                                                    [<sub>][for][permissionBackMethod]
                                                                                                    [<sub>][if][bindHandler]
    public static class BindHandler extends android.os.Handler {
        private [beanType] [beanName];

        public BindHandler([beanType] [beanName]) {
            this.[beanName] = [beanName];
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
        }
    }
                                                                                                    [<sub>][if][bindHandler]
}

model_temp_end */