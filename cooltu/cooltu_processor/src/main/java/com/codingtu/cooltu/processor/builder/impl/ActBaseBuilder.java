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
import com.codingtu.cooltu.processor.annotation.form.FormBean;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.annotation.form.view.BindEditText;
import com.codingtu.cooltu.processor.annotation.form.view.BindMulti;
import com.codingtu.cooltu.processor.annotation.form.view.BindRadioGroup;
import com.codingtu.cooltu.processor.annotation.form.view.BindSeekBar;
import com.codingtu.cooltu.processor.annotation.form.view.BindTextView;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.Permission;
import com.codingtu.cooltu.processor.bean.ActBaseInfo;
import com.codingtu.cooltu.processor.bean.ClickViewInfo;
import com.codingtu.cooltu.processor.bean.NetBackInfo;
import com.codingtu.cooltu.processor.builder.base.ActBaseBuilderBase;
import com.codingtu.cooltu.processor.builder.subdeal.BindEditTextDeal;
import com.codingtu.cooltu.processor.builder.subdeal.BindMultiDeal;
import com.codingtu.cooltu.processor.builder.subdeal.BindRadioGroupDeal;
import com.codingtu.cooltu.processor.builder.subdeal.BindSeekBarDeal;
import com.codingtu.cooltu.processor.builder.subdeal.BindTextViewDeal;
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
import java.util.Map;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

@To(ActBaseDeal.class)
public class ActBaseBuilder extends ActBaseBuilderBase {


    private ActBaseInfo info;
    private List<KV<String, String>> inBases = new ArrayList<>();
    private HashMap<String, String> inBaseMap = new HashMap<>();
    private HashMap<String, String> fieldMap = new HashMap<>();

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

        if (info.layout != null) {
            layoutIf(info.layout.toString());
        }

        Ts.ls(info.viewInfos, new BaseTs.EachTs<LayoutTools.ViewInfo>() {
            @Override
            public boolean each(int position, LayoutTools.ViewInfo viewInfo) {
                addField(Constant.SIGN_PROTECTED, viewInfo.tag, viewInfo.fieldName);

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
                addField(Constant.SIGN_PROTECTED, kv.k, kv.v);
                return false;
            }
        });

        final int[] setOnClickCount = {0};
        Ts.ls(info.clickViews, new BaseTs.EachTs<ClickViewInfo>() {
            @Override
            public boolean each(int clickViewInfoIndex, ClickViewInfo info) {
                onClickMethods(clickViewInfoIndex, info.method, info.methodParams.getMethodParams());
                onClickSwith(clickViewInfoIndex, info.method);

                List<KV<String, String>> kvs = info.methodParams.getKvs();
                int kvCount = CountTool.count(kvs);

                Ts.ls(kvs, new BaseTs.EachTs<KV<String, String>>() {
                    private int paramsIndex;

                    @Override
                    public boolean each(int kvIndex, KV<String, String> kv) {
                        String divider = (kvIndex != kvCount - 1) ? "," : "";
                        if (kvIndex == 0 && FullName.VIEW.equals(kv.k)) {
                            onClickSwitchParamsIf(clickViewInfoIndex, divider);
                        } else {
                            onClickSwitchParams(clickViewInfoIndex, paramsIndex, kv.k, Pkg.LIB4A, paramsIndex + "", divider);
                            paramsIndex++;
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


        //onclick继承
        isSuperOnClick(info.hasBaseClass());

        //colorStr
        Ts.ls(info.colorStrs, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                addField(Constant.SIGN_PROTECTED, "int", kv.k);
                colorStrInit(position, kv.k, kv.v);
                return false;
            }
        });

        //colorRes
        Ts.ls(info.colorReses, new BaseTs.EachTs<KV<String, IdTools.Id>>() {
            @Override
            public boolean each(int position, KV<String, IdTools.Id> kv) {
                addField(Constant.SIGN_PROTECTED, "int", kv.k);
                colorResInit(position, kv.k, FullName.RESOURCE_TOOL, kv.v.toString());
                return false;
            }
        });

        //dp
        Ts.ls(info.dps, new BaseTs.EachTs<KV<String, Float>>() {
            @Override
            public boolean each(int position, KV<String, Float> kv) {
                addField(Constant.SIGN_PROTECTED, "int", kv.k);
                dpInit(position, kv.k, FullName.MOBILE_TOOL, kv.v + "f");
                return false;
            }
        });

        //colorRes
        Ts.ls(info.dimens, new BaseTs.EachTs<KV<String, IdTools.Id>>() {
            @Override
            public boolean each(int position, KV<String, IdTools.Id> kv) {
                addField(Constant.SIGN_PROTECTED, "int", kv.k);
                dimenInit(position, kv.k, FullName.RESOURCE_TOOL, kv.v.toString());
                return false;
            }
        });

        //startField
        Ts.ls(info.starts, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                addField(Constant.SIGN_PROTECTED, kv.k, kv.v);
                startInit(position, kv.v, FullName.PASS);
                return false;
            }
        });

        //accept
        isSuperAccept(info.hasBaseClass());
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


                String methodParamStr = params.getParam(new Params.Convert() {
                    @Override
                    public String convert(int index, KV<String, String> kv) {
                        return kv.k + " " + kv.v;
                    }
                });


                acceptMethod(position, methodName, methodParamStr);
                return false;
            }
        });

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

                Params params = ElementTools.getMethodParamKvs(ee);
                params.ls(new BaseTs.EachTs<KV<String, String>>() {
                    @Override
                    public boolean each(int paramIndex, KV<String, String> kv) {
                        actBackParam(actBackIndex, paramIndex, FullName.PASS, kv.v);
                        isActBackParamDivider(actBackIndex, paramIndex, paramIndex != (CountTool.count(ee.getParameters()) - 1));
                        return false;
                    }
                });

                actBackMethod(actBackIndex, methodName, params.getMethodParams());
                return false;
            }
        });

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

                boolean isParam = !CountTool.isNull(ee.getParameters());
                if (isParam) {
                    allowIf(permissionIndex, FullName.PERMISSION_TOOL);
                }

                permissionBackMethod(permissionIndex, methodName);

                isAllowParam(permissionIndex, isParam);

                return false;
            }
        });

        isOnCreateCompleteInit(!info.hasChild());

        //form
        if (info.form != null) {
            String formBeanClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return info.form.value();
                }
            });
            TypeElement formBeanTe = FormBeanDeal.MAP.get(formBeanClass);

            String formBeanSimpleName = CurrentPath.javaInfo(formBeanClass).name;

            String name = formBeanTe.getAnnotation(FormBean.class).value();
            if (StringTool.isBlank(name)) {
                name = ConvertTool.toMethodType(formBeanSimpleName);
            }
            addField(Constant.SIGN_PROTECTED, formBeanClass, name);
            addField(Constant.SIGN_PROTECTED, "boolean", "initFormBean");
            addField(Constant.SIGN_PUBLIC, "BindHandler", "bindHandler");
            bindHandlerIf(formBeanClass, name, FullName.FORM_LINK, FullName.LIST_VALUE_MAP, FullName.TS);
            formInitIf(name, formBeanClass);
            checkFormsIf(formBeanSimpleName);
            dealFormBean(formBeanTe, name);
        }
    }

    public boolean addField(String sign, String type, String name) {
        if (inBaseMap.get(name) == null && fieldMap.get(name) == null) {
            fieldMap.put(name, name);
            field(fieldCount(), sign, type, name);
            return true;
        }
        return false;
    }

    private void dealFormBean(TypeElement te, String beanName) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        Map<Integer, Integer> typeIndexMap = new HashMap<>();
        Map<Integer, String> viewMap = new HashMap<>();
        Map<Integer, BindMultiDeal.ViewIndex> viewIndexMap = new HashMap<>();

        Ts.ts(te.getEnclosedElements()).ls((position, element) -> {
            if (element instanceof VariableElement) {
                VariableElement ve = (VariableElement) element;

                BindEditText bindEditText = ve.getAnnotation(BindEditText.class);
                if (bindEditText != null) {
                    BindEditTextDeal.deal(ActBaseBuilder.this, beanName, indexMap, typeIndexMap, viewMap, viewIndexMap, ve, bindEditText);
                }

                BindTextView bindTextView = ve.getAnnotation(BindTextView.class);
                if (bindTextView != null) {
                    BindTextViewDeal.deal(ActBaseBuilder.this, beanName, indexMap, typeIndexMap, viewMap, viewIndexMap, ve, bindTextView);

                }

                BindRadioGroup bindRadioGroup = ve.getAnnotation(BindRadioGroup.class);
                if (bindRadioGroup != null) {
                    BindRadioGroupDeal.deal(ActBaseBuilder.this, beanName, indexMap, typeIndexMap, viewMap, viewIndexMap, ve, bindRadioGroup);
                }

                BindSeekBar bindSeekBar = ve.getAnnotation(BindSeekBar.class);
                if (bindSeekBar != null) {
                    BindSeekBarDeal.deal(ActBaseBuilder.this, beanName, indexMap, typeIndexMap, viewMap, viewIndexMap, ve, bindSeekBar);
                }

                BindMulti bindMulti = ve.getAnnotation(BindMulti.class);
                if (bindMulti != null) {
                    BindMultiDeal.deal(ActBaseBuilder.this, beanName, indexMap, typeIndexMap, viewMap, viewIndexMap, ve, bindMulti);
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
                                                                                                    [<sub>][for][rgInit]
        //[viewName]Rg
                                                                                                    [<sub>][if][rgOnSetItemInit]
        [name] = new [type]();
                                                                                                    [<sub>][if][rgOnSetItemInit]
        [viewName]Rg = [radioGroupFullName].obtain(this).setBts([viewName]).setOnSetItem([onSetItem]);
        [viewName].setTag([rPkg].R.id.tag_0, [viewName]Rg);
                                                                                                    [<sub>][for][rgInit]
        //[name]
        if ([name] == null) {
            [name] = new [type]();
            initFormBean = true;
        }
        bindHandler = new BindHandler([name]);
                                                                                                    [<sub>][for][editTextInit]
        [name].addTextChangedListener(new [handlerTextWatcherFullName](bindHandler, [formTypeFullName].[type], [index]));
                                                                                                    [<sub>][for][editTextInit]
                                                                                                    [<sub>][for][textViewInit]
        [name].addTextChangedListener(new [handlerTextWatcherFullName](bindHandler, [formTypeFullName].[type], [index]));
                                                                                                    [<sub>][for][textViewInit]
                                                                                                    [<sub>][for][rgBind]
        [viewName]Rg.addOnSelectChange(new [handlerOnSelectChangeFullName](bindHandler, [formTypeFullName].[type], [index]));
                                                                                                    [<sub>][for][rgBind]
                                                                                                    [<sub>][for][seekBarBind]
        [name].setOnSeekBarChangeListener(new [handlerOnSeekBarChangeListenerFullName](bindHandler, [formTypeFullName].[type], [index]));
                                                                                                    [<sub>][for][seekBarBind]
                                                                                                    [<sub>][for][bindMulti]
        [formLinkFullName] [linkName] = new [linkType](this)
                .setBean([beanName])
                .setViews([views]);
                                                                                                    [<sub>][for][addLink]
        bindHandler.addLink([viewId], [linkName]);
                                                                                                    [<sub>][for][addLink]
                                                                                                    [<sub>][for][bindMulti]
        if (!initFormBean) {
                                                                                                    [<sub>][for][etEchoWithParse]
            [viewToolFullName].setText([view], new [parse]().toView([bean].[field]));
                                                                                                    [<sub>][for][etEchoWithParse]
                                                                                                    [<sub>][for][etEcho]
            [viewToolFullName].setText([view], [bean].[field]);
                                                                                                    [<sub>][for][etEcho]
                                                                                                    [<sub>][for][tvEchoWithParse]
            [viewToolFullName].setText([view], new [parse]().toView([bean].[field]));
                                                                                                    [<sub>][for][tvEchoWithParse]
                                                                                                    [<sub>][for][tvEcho]
            [viewToolFullName].setText([view], [bean].[field]);
                                                                                                    [<sub>][for][tvEcho]
                                                                                                    [<sub>][for][rgEcho]
            [viewName]Rg.setSelected(new [defaultRadioGroupToStringFullName]([items]).toView([bean].[field]));
                                                                                                    [<sub>][for][rgEcho]
                                                                                                    [<sub>][for][rgEchoWithParse]
            [viewName]Rg.setSelected(new [parse]().toView([bean].[field]));
                                                                                                    [<sub>][for][rgEchoWithParse]
                                                                                                    [<sub>][for][seekBarEcho]
            [viewName].setProgress([bean].[field]);
                                                                                                    [<sub>][for][seekBarEcho]
                                                                                                    [<sub>][for][seekBarEchoWithParse]
            [viewName].setProgress(new [parse]().toView([bean].[field]));
                                                                                                    [<sub>][for][seekBarEchoWithParse]
                                                                                                    [<sub>][for][linkEcho]
            [lineName].echo();
                                                                                                    [<sub>][for][linkEcho]
        }
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
                                                                                                    [<sub>][for][handler]
            if (msg.what == [formTypeFullName].[type]) {
                switch (msg.arg1) {
                                                                                                    [<sub>][for][handlerItem]
                    case [index]:
                                                                                                    [<sub>][if][handlerItemString]
                        [beanName].[field] = (java.lang.String) msg.obj;
                                                                                                    [<sub>][if][handlerItemString]
                                                                                                    [<sub>][if][handlerItemRg]
                        [beanName].[field] = new [defaultRadioGroupToStringFullName]([items]).toBean(msg.obj);
                                                                                                    [<sub>][if][handlerItemRg]
                                                                                                    [<sub>][if][handlerItemInt]
                        [beanName].[field] = (int) msg.obj;
                                                                                                    [<sub>][if][handlerItemInt]
                                                                                                    [<sub>][if][handlerItemParse]
                        [beanName].[field] = new [parse]().toBean(msg.obj);
                                                                                                    [<sub>][if][handlerItemParse]
                                                                                                    [<sub>][if][handlerItemLink]
                        link([viewId]);
                                                                                                    [<sub>][if][handlerItemLink]
                        break;
                                                                                                    [<sub>][for][handlerItem]
                }
            }
                                                                                                    [<sub>][for][handler]
        }

        public void addLink(int viewId, [formLinkFullName] link) {
            getLinks().get(viewId).add(link);
        }

        private [listValueMapFullName]<Integer, [formLinkFullName]> links;

        private [listValueMapFullName]<Integer, [formLinkFullName]> getLinks() {
            if (links == null) {
                links = new [listValueMapFullName]<>();
            }
            return links;
        }
        private void link(int id) {
            [tsFullName].ls(getLinks().get(id), (position, formLink) -> {
                formLink.link();
                return false;
            });
        }
    }
                                                                                                    [<sub>][if][bindHandler]
                                                                                                    [<sub>][if][checkForms]
    protected boolean check[bean]() {
                                                                                                    [<sub>][for][check]
                                                                                                    [<sub>][if][checkString]
        if ([stringToolFullName].isBlank([bean].[field])) {
            toast("[promp]");
            return false;
        }
                                                                                                    [<sub>][if][checkString]
                                                                                                    [<sub>][if][checkWithDeal]
        if (new [checkClass]().check([bean], [bean].[field])) {
            toast("[promp]");
            return false;
        }
                                                                                                    [<sub>][if][checkWithDeal]
                                                                                                    [<sub>][if][checkRg]
        if (new [defaultRadioGroupFormCheckFullName]().check([bean], [viewName]Rg.getSelected())) {
            toast("[promp]");
            return false;
        }
                                                                                                    [<sub>][if][checkRg]
                                                                                                    [<sub>][for][check]
        return true;
    }
                                                                                                    [<sub>][if][checkForms]
}

model_temp_end */