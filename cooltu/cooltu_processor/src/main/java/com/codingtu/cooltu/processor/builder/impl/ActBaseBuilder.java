package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.annotation.forms.FormConfig;
import com.codingtu.cooltu.processor.annotation.forms.check.CheckField;
import com.codingtu.cooltu.processor.annotation.forms.check.Checks;
import com.codingtu.cooltu.processor.annotation.forms.echo.Echo;
import com.codingtu.cooltu.processor.annotation.forms.echo.NoEcho;
import com.codingtu.cooltu.processor.annotation.forms.link.Link;
import com.codingtu.cooltu.processor.annotation.forms.link.Links;
import com.codingtu.cooltu.processor.annotation.forms.radiogroup.FormRadioGroupGetViews;
import com.codingtu.cooltu.processor.annotation.forms.radiogroup.FormRadioGroupItems;
import com.codingtu.cooltu.processor.annotation.forms.view.FormEditText;
import com.codingtu.cooltu.processor.annotation.forms.view.FormRadioGroup;
import com.codingtu.cooltu.processor.annotation.forms.view.FormTextView;
import com.codingtu.cooltu.processor.annotation.tools.Name;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.Permission;
import com.codingtu.cooltu.processor.bean.DealFormInfo;
import com.codingtu.cooltu.processor.builder.base.ActBaseBuilderBase;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.core.UiBaseInterface;
import com.codingtu.cooltu.processor.deal.ActBaseDeal;
import com.codingtu.cooltu.processor.deal.FormConfigDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;
import com.codingtu.cooltu.processor.lib.tools.BeanTools;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

@To(ActBaseDeal.class)
public class ActBaseBuilder extends ActBaseBuilderBase implements UiBaseInterface {


    /**************************************************
     *
     * 初始化
     *
     **************************************************/
    private final UiBaseBuilder uiBaseBuilder;
    public List<KV<String, String>> starts = new ArrayList<>();
    public List<Permission> permissions = new ArrayList<>();
    public List<ExecutableElement> permissionMethods = new ArrayList<>();
    private Map<String, LayoutTools.ViewInfo> parentViewMap;
    private StringBuilder otherLineSb = new StringBuilder();

    public ActBaseBuilder(JavaInfo info) {
        super(info);
        uiBaseBuilder = new UiBaseBuilder(this) {
            @Override
            protected BaseTools.GetThis<UiBaseBuilder> getChildGetter() {
                return BaseTools.getActBaseChildGetter();
            }

            @Override
            protected BaseTools.GetParent<UiBaseBuilder> getParentGetter() {
                return BaseTools.getActBaseParentGetter();
            }
        };
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
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        if (javaInfo.name.equals("StepOneActivityBase")) {
            //Logs.i(lines);
        }
    }

    public UiBaseBuilder getUiBaseBuilder() {
        return uiBaseBuilder;
    }

    @Override
    public StringBuilderValueMap<String> getMap() {
        return map;
    }

    @Override
    public JavaInfo getJavaInfo() {
        return javaInfo;
    }

    /**************************************************
     *
     * 设置数据
     *
     **************************************************/
    @Override
    protected void dealLines() {
        uiBaseBuilder.dealLines();
        //startField
        Ts.ls(starts, new Ts.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                addField(Constant.SIGN_PROTECTED, kv.k, kv.v);
                startInit(position, kv.v, FullName.PASS);
                return false;
            }
        });

        Ts.ls(permissions, new Ts.EachTs<Permission>() {
            @Override
            public boolean each(int permissionIndex, Permission permission) {
                ExecutableElement ee = permissionMethods.get(permissionIndex);

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

        isOnCreateCompleteInit(!uiBaseBuilder.hasChild());

        if (uiBaseBuilder.form != null) {
            parentViewMap = uiBaseBuilder.getParentViewMap();

            List<String> formConfigClassNames = ClassTool.getAnnotationClasses(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return uiBaseBuilder.form.value();
                }
            });

            Ts.strs(formConfigClassNames).ls(new Ts.EachTs<String>() {
                @Override
                public boolean each(int position, String formConfigClassName) {
                    try {
                        dealForm(formConfigClassName);
                    } catch (Exception e) {
                        LibLogs.i(e);
                    }

                    return false;
                }
            });

            addLnTag(otherLineSb, "    protected void initFormView() {");
            addLnTag(otherLineSb, initFormSb.toString());
            addLnTag(otherLineSb, "    }");

            addLnTag(otherLineSb, "    @Override");
            addLnTag(otherLineSb, "    public void handleMessage(android.os.Message msg, java.util.Map<String, Object[]> links) {");
            addLnTag(otherLineSb, "        Object[] objs;");
            addLnTag(otherLineSb, "        switch (msg.what) {");
            addLnTag(otherLineSb, handlerSb.toString());
            addLnTag(otherLineSb, "        }");
            addLnTag(otherLineSb, "    }");

            addLnTag(otherLineSb, obtainSb.toString());
            addLnTag(otherLineSb, echoSb.toString());

        }

        otherIf(otherLineSb.toString());
    }


    private StringBuilder echoSb = new StringBuilder();
    private StringBuilder obtainSb = new StringBuilder();
    private StringBuilder initFormSb = new StringBuilder();
    private StringBuilder handlerSb = new StringBuilder();

    private void dealForm(String formConfigClassName) {
        addTag(formHandlerCallBack, ",[formHandlerCallBack]", FullName.FORM_HANDLE_CALL_BACK);
        DealFormInfo info = new DealFormInfo();
        info.formConfigTe = FormConfigDeal.MAP.get(formConfigClassName);
        info.formConfigKv = BeanTools.getBeanKv(formConfigClassName, null);
        info.formHandlerKv = new KV<>(FullName.FORM_HANDLER, "formHandler");

        addField(Constant.SIGN_PROTECTED, info.formConfigKv.k, info.formConfigKv.v);
        addField(Constant.SIGN_PROTECTED, info.formHandlerKv.k, info.formHandlerKv.v);

        addLnTag(initFormSb, "        [formHandler] = new [FormHandler](this, this);", info.formHandlerKv.v, info.formHandlerKv.k);
        addLnTag(initFormSb, "        [dataFormConfig] = new [DataFormConfig]();", info.formConfigKv.v, info.formConfigKv.k);

        FormConfig formConfig = info.formConfigTe.getAnnotation(FormConfig.class);
        info.dataClassFullName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return formConfig.value();
            }
        });

        info.formBeanKv = BeanTools.getBeanKv(info.dataClassFullName, null);
        addField(Constant.SIGN_PROTECTED, info.formBeanKv.k, info.formBeanKv.v);

        addLnTag(initFormSb, "        if ([formData] == null)", info.formBeanKv.v);
        addLnTag(initFormSb, "            [formData] = new [FormData]();", info.formBeanKv.v, info.formBeanKv.k);

        addLnTag(echoSb, "    protected void echo() {");

        addLnTag(obtainSb, "    protected boolean check[FormData]() {", ConvertTool.toClassType(info.formBeanKv.v));
        addLnTag(obtainSb, "        try {");


        Map<String, ExecutableElement> methods = new HashMap<>();
        ElementTools.ls(info.formConfigTe.getEnclosedElements(), new Ts.EachTs<Element>() {
            @Override
            public boolean each(int position, Element element) {
                if (element instanceof ExecutableElement) {
                    ExecutableElement ee = (ExecutableElement) element;
                    String methodName = ElementTools.simpleName(ee);
                    Logs.i("methodName:" + methodName);
                    Name name = ee.getAnnotation(Name.class);
                    if (name != null) {
                        methodName = name.value();
                    }
                    methods.put(methodName, ee);
                }
                return false;
            }
        });


        ElementTools.ls(info.formConfigTe.getEnclosedElements(), new Ts.EachTs<Element>() {
            @Override
            public boolean each(int position, Element e) {
                if (e instanceof VariableElement) {
                    VariableElement ve = (VariableElement) e;
                    KV<String, String> veKv = ElementTools.getFieldKv(ve);
                    String veName = veKv.v;

                    FormEditText formEditText = ve.getAnnotation(FormEditText.class);
                    IdTools.Id editTextId = null;
                    String editTextFieldName = null;
                    if (formEditText != null) {
                        editTextId = IdTools.elementToId(ve, FormEditText.class, formEditText.value());
                        editTextFieldName = getViewFieldName(editTextId);
                    }
                    FormRadioGroup formRadioGroup = ve.getAnnotation(FormRadioGroup.class);
                    IdTools.Id radioGroupId = null;
                    String radioGroupFieldName = null;
                    if (formRadioGroup != null) {
                        radioGroupId = IdTools.elementToId(ve, FormRadioGroup.class, formRadioGroup.id());
                        radioGroupFieldName = getViewFieldName(radioGroupId);
                    }

                    FormTextView formTextView = ve.getAnnotation(FormTextView.class);
                    IdTools.Id textVeiwId = null;
                    String textVeiwFieldName = null;
                    if (formTextView != null) {
                        textVeiwId = IdTools.elementToId(ve, FormTextView.class, formTextView.value());
                        textVeiwFieldName = getViewFieldName(textVeiwId);
                    }


                    NoEcho noEcho = ve.getAnnotation(NoEcho.class);
                    if (noEcho == null) {
                        Echo echo = ve.getAnnotation(Echo.class);
                        if (echo != null) {
                            Map<Integer, IdTools.Id> idMap = IdTools.elementToIds(ve, Echo.class, echo.ids());

                            String param = Params.getParam(Ts.maps(idMap).toValueTs(), new Ts.Convert<IdTools.Id, String>() {
                                @Override
                                public String convert(int index, IdTools.Id id) {
                                    return getViewFieldName(id);
                                }
                            });
                            addLnTag(echoSb, "        [dataFormConfig].[echoName]([formData], [formData].[name], [params]);",
                                    info.formConfigKv.v, getMethodName(methods, echo.methodName()), info.formBeanKv.v, info.formBeanKv.v, veName, param);

                        } else if (formEditText != null) {
                            addLnTag(echoSb, "        [ViewTool].setEditTextAndSelection([nameEt], [formData].[name]);",
                                    FullName.VIEW_TOOL, editTextFieldName, info.formBeanKv.v, veName);
                        } else if (formTextView != null) {
                            addLnTag(echoSb, "        [ViewTool].setText([nameEt], [formData].[name]);",
                                    FullName.VIEW_TOOL, textVeiwFieldName, info.formBeanKv.v, veName);
                        } else if (formRadioGroup != null) {
                            if (isInt(veKv.k)) {
                                addLnTag(echoSb, "        [ViewTool].getRadioGroup([numLl]).setSelected([formData].[num]);",
                                        FullName.VIEW_TOOL, radioGroupFieldName, info.formBeanKv.v, veName);
                            } else if (ClassTool.isString(veKv.k)) {
                                addLnTag(echoSb, "        [RadioGroup] [numLl]Rg = [ViewTool].getRadioGroup([numLl]);",
                                        FullName.RADIO_GROUP, radioGroupFieldName, FullName.VIEW_TOOL, radioGroupFieldName);
                                addLnTag(echoSb, "        [numLl]Rg.setSelected([numLl]Rg.getIndex([formData].[num]));",
                                        radioGroupFieldName, radioGroupFieldName, info.formBeanKv.v, veName);
                            }
                        }
                    }

                    if (formRadioGroup != null) {
                        IdTools.Id id = IdTools.elementToId(ve, FormRadioGroup.class, formRadioGroup.id());

                        String viewFieldName = getViewFieldName(id);
                        addLnTag(initFormSb, "        [numLl].setTag([com.codingtu.cooltu.lib4a].R.id.tag_0,",
                                viewFieldName, Pkg.LIB4A);
                        addLnTag(initFormSb, "                [RadioGroup].obtain(this)", FullName.RADIO_GROUP);

                        FormRadioGroupGetViews getViews = ve.getAnnotation(FormRadioGroupGetViews.class);
                        if (getViews == null) {
                            addLnTag(initFormSb, "                        .setBts([numLl])", viewFieldName);
                        } else {
                            addLnTag(initFormSb, "                        .setBts([dataFormConfig].[getNumViews]([numLl]))",
                                    info.formConfigKv.v, getMethodName(methods, getViews.value()), viewFieldName);
                        }

                        FormRadioGroupItems items = ve.getAnnotation(FormRadioGroupItems.class);
                        if (items != null) {
                            String param = Params.getParam(items.value(), new Ts.Convert<String, String>() {
                                @Override
                                public String convert(int index, String s) {
                                    return "\"" + s + "\"";
                                }
                            });
                            if (StringTool.isBlank(param)) {
                                addLnTag(initFormSb, "                        .initItems()");
                            } else {
                                addLnTag(initFormSb, "                        .setItems([params])", param);
                            }
                        }

                        addLnTag(initFormSb, "                        .setOnSetItem(new [TypeOnSetItem]()));", ClassTool.getAnnotationClass(() -> formRadioGroup.onSetItem()));
                    }

                    Link[] linkArr = null;
                    Links links = ve.getAnnotation(Links.class);
                    if (links != null) {
                        linkArr = links.value();
                    }
                    Link link = ve.getAnnotation(Link.class);
                    if (link != null) {
                        linkArr = new Link[]{link};
                    }

                    if (linkArr != null) {
                        if (formEditText != null) {
                            addLnTag(initFormSb, "        [nameEt].addTextChangedListener(new [HandlerTextWatcher](this, [formHandler], [nameEt]));",
                                    editTextFieldName, FullName.HANDLER_TEXT_WATCHER, info.formHandlerKv.v, editTextFieldName);
                            extracted(methods, ve, editTextId, editTextFieldName, linkArr, info);
                        } else if (formTextView != null) {
                            addLnTag(initFormSb, "        [nameEt].addTextChangedListener(new [HandlerTextWatcher](this, [formHandler], [nameEt]));",
                                    textVeiwFieldName, FullName.HANDLER_TEXT_WATCHER, info.formHandlerKv.v, textVeiwFieldName);
                            extracted(methods, ve, textVeiwId, textVeiwFieldName, linkArr, info);
                        } else if (formRadioGroup != null) {
                            addLnTag(initFormSb,
                                    "        [ViewTool].getRadioGroup([numLl]).addOnSelectChange(new [HandlerOnSelectChange](this, [formHandler], [numLl].getId()));",
                                    FullName.VIEW_TOOL, radioGroupFieldName, FullName.HANDLER_ON_SELECT_CHANGE, info.formHandlerKv.v, radioGroupFieldName);
                            extracted(methods, ve, radioGroupId, radioGroupFieldName, linkArr, info);

                        }
                    }

                    CheckField[] checkFields = null;

                    Checks checks = ve.getAnnotation(Checks.class);
                    if (checks != null) {
                        checkFields = checks.value();
                    }

                    CheckField checkField = ve.getAnnotation(CheckField.class);
                    if (checkField != null) {
                        checkFields = new CheckField[]{checkField};
                    }

                    if (checkFields != null) {
                        for (int i = 0; i < checkFields.length; i++) {
                            checkField = checkFields[i];
                            String prompt = checkField.prompt();
                            String methodName = checkField.methodName();
                            if (StringTool.isNotBlank(methodName)) {
                                //有检测方法
                                Map<Integer, IdTools.Id> idMap = IdTools.elementToIds(ve, CheckField.class, checkField.ids());
                                BaseTs<IdTools.Id> idTs = Ts.maps(idMap).toValueTs();
                                String param = Params.getParam(idTs, new Ts.Convert<IdTools.Id, String>() {
                                    @Override
                                    public String convert(int index, IdTools.Id id) {
                                        return getViewFieldName(id);
                                    }
                                });

                                String p1 = "";
                                if (StringTool.isNotBlank(prompt)) {
                                    p1 = ", \"" + prompt + "\"";
                                }
                                addLnTag(obtainSb, "            [formData].[num] = [dataFormConfig].[checkName]([formData], [nameEt][prompt]);",
                                        info.formBeanKv.v, veName, info.formConfigKv.v, getMethodName(methods, methodName), info.formBeanKv.v, param, p1);
                            } else if (formEditText != null) {
                                addLnTag(obtainSb, "            [formData].[name] = [nameEt].getText().toString();",
                                        info.formBeanKv.v, veName, editTextFieldName);
                                if (StringTool.isNotBlank(prompt)) {
                                    addLnTag(obtainSb, "            if ([StringTool].isBlank([formData].[name])) {",
                                            FullName.STRING_TOOL, info.formBeanKv.v, veName);
                                    addLnTag(obtainSb, "                throw new java.lang.RuntimeException(\"[xxx]\");", prompt);
                                    addLnTag(obtainSb, "            }");
                                }
                            } else if (formTextView != null) {
                                addLnTag(obtainSb, "            [formData].[name] = [nameEt].getText().toString();",
                                        info.formBeanKv.v, veName, textVeiwFieldName);
                                if (StringTool.isNotBlank(prompt)) {
                                    addLnTag(obtainSb, "            if ([StringTool].isBlank([formData].[name])) {",
                                            FullName.STRING_TOOL, info.formBeanKv.v, veName);
                                    addLnTag(obtainSb, "                throw new java.lang.RuntimeException(\"[xxx]\");", prompt);
                                    addLnTag(obtainSb, "            }");
                                }
                            } else if (formRadioGroup != null) {
                                addLnTag(obtainSb, "            [RadioGroup] [numLl]Rg = [ViewTool].getRadioGroup([numLl]);",
                                        FullName.RADIO_GROUP, radioGroupFieldName, FullName.VIEW_TOOL, radioGroupFieldName);
                                if (isInt(veKv.k)) {
                                    addLnTag(obtainSb, "            [formData].[num] = [numLl]Rg.getSelected();", info.formBeanKv.v, veName, radioGroupFieldName);
                                    if (StringTool.isNotBlank(prompt)) {
                                        addLnTag(obtainSb, "            if ([formData].[num] < 0) {", info.formBeanKv.v, veName);
                                        addLnTag(obtainSb, "                throw new java.lang.RuntimeException(\"[xxx]\");", prompt);
                                        addLnTag(obtainSb, "            }");
                                    }
                                } else if (ClassTool.isString(veKv.k)) {
                                    addLnTag(obtainSb, "            [formData].[num] = [numLl]Rg.getCurrentItem();", info.formBeanKv.v, veName, radioGroupFieldName);
                                    if (StringTool.isNotBlank(prompt)) {
                                        addLnTag(obtainSb, "            if ([StringTool].isBlank([formData].[num])) {",
                                                FullName.STRING_TOOL, info.formBeanKv.v, veName);
                                        addLnTag(obtainSb, "                throw new java.lang.RuntimeException(\"[xxx]\");", prompt);
                                        addLnTag(obtainSb, "            }");
                                    }
                                }
                            }

                        }
                    }
                }
                return false;
            }
        });

        addLnTag(obtainSb, "            return true;");
        addLnTag(obtainSb, "        } catch (java.lang.Exception e) {");
        addLnTag(obtainSb, "            toast(e.getMessage());");
        addLnTag(obtainSb, "            return false;");
        addLnTag(obtainSb, "        }");
        addLnTag(obtainSb, "    }");
        addLnTag(echoSb, "    }");
    }

    private boolean isInt(String type) {
        return ClassTool.isInt(type) || ClassTool.isInteger(type);
    }

    private String getMethodName(Map<String, ExecutableElement> methods, String viewsMethodName) {
        return ElementTools.simpleName(methods.get(viewsMethodName));
    }

    private void extracted(Map<String, ExecutableElement> methods, VariableElement ve, IdTools.Id editTextId, String editTextFieldName, Link[] linkArr, DealFormInfo info) {
        addLnTag(handlerSb, "            case [R.id.nameEt]:", editTextId.toString());

        for (int i = 0; i < linkArr.length; i++) {
            Link link = linkArr[i];
            String methodName = link.methodName();

            Map<Integer, IdTools.Id> idMap = IdTools.elementToIds(ve, Link.class, link.ids());
            BaseTs<IdTools.Id> idTs = Ts.maps(idMap).toValueTs();


            String param = Params.getParam(idTs, new Ts.Convert<IdTools.Id, String>() {
                @Override
                public String convert(int index, IdTools.Id id) {
                    return getViewFieldName(id);
                }
            });

            String param1 = Params.getParam(idTs, new Ts.Convert<IdTools.Id, String>() {
                @Override
                public String convert(int index, IdTools.Id id) {
                    LayoutTools.ViewInfo viewInfo = parentViewMap.get(id.rName);
                    if (viewInfo != null) {
                        return "(" + viewInfo.tag + ") objs[" + index + "]";
                    } else {
                        return null;
                    }
                }
            });
            addLnTag(initFormSb, "        [formHandler].link([nameEt].getId(), \"[xxx]\", [nameEt, nicknameEt]);",
                    info.formHandlerKv.v, editTextFieldName, methodName, param);
            addLnTag(handlerSb, "                objs = links.get(\"[handleAverage]\");", methodName);
            addLnTag(handlerSb, "                [dataFormConfig].[handleName](msg, [param]);",
                    info.formConfigKv.v, getMethodName(methods, methodName), param1);
        }
        addLnTag(handlerSb, "                break;");

    }

    private String getViewFieldName(IdTools.Id id) {
        return getViewFieldName(id.rName);
    }

    private String getViewFieldName(String rName) {
        LayoutTools.ViewInfo viewInfo = parentViewMap.get(rName);
        if (viewInfo == null) {
            return "null";
        }
        return viewInfo.fieldName;
    }

    @Override
    public void layoutIf(String inflateTool, String layout) {
        layoutIf(layout);
    }

    @Override
    public String getDefulatViewParent() {
        return "";
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public boolean addField(String sign, String type, String name) {
        return uiBaseBuilder.addField(sign, type, name);
    }

    @Override
    public void isCheckForm(int index, boolean isCheckForm) {
    }

    @Override
    public void addOthers(String others) {
        otherLineSb.append(others);
    }


    /**************************************************
     *
     *   ┏━━━━━━━━━━━━━━━━━━━━━┓
     *  ┃   处理ListAdapter  ┃
     * ┗━━━━━━━━━━━━━━━━━━━━━━┛
     * {@link #dealListAdapter()}
     *
     **************************************************/
}
/* model_temp_start
package [[pkg]];

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class [[name]] extends [[baseClass]] implements View.OnClickListener, View.OnLongClickListener, [[netBackIFullName]][[formHandlerCallBack]]{
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
                                                                                                    [<sub>][if][onCreateCompleteInit]
        onCreateComplete();
                                                                                                    [<sub>][if][onCreateCompleteInit]
    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
                                                                                                    [<sub>][for][listAdapter]
                                                                                                    [<sub>][if][defaultListAdapter]
        // [adapterName]
        [adapterName] = new [adapterFullName]();
                                                                                                    [<sub>][if][defaultListAdapter]
                                                                                                    [<sub>][if][defaultListMoreAdapter]
        // [adapterName]
        [adapterName] = new [adapterFullName]() {
            @Override
            protected void loadMore(int page) {
                [adapterName]LoadMore(page);
            }
        };
                                                                                                    [<sub>][if][defaultListMoreAdapter]
        [adapterName].setVH([vhFullName].class);
        [adapterName].setClick(this);
        [rvName].setAdapter([adapterName]);
        new [configName]().config(getAct(), [rvName], () -> [rvName]Obj());
                                                                                                    [<sub>][for][listAdapter]
                                                                                                    [<sub>][for][setOnClick]
        [fieldName].setOnClickListener(this);
                                                                                                    [<sub>][for][setOnClick]
                                                                                                    [<sub>][for][setOnLongClick]
        [fieldName].setOnLongClickListener(this);
                                                                                                    [<sub>][for][setOnLongClick]
                                                                                                    [<sub>][if][onCreateCompleteOther]
[onCreateCompleteOther]
                                                                                                    [<sub>][if][onCreateCompleteOther]
    }
                                                                                                    [<sub>][for][adapterObjs]
    protected Object [rvName]Obj() {
        return null;
    }
                                                                                                    [<sub>][for][adapterObjs]

    @Override
    public void onClick(View v) {
                                                                                                    [<sub>][if][superOnClick]
        super.onClick(v);
                                                                                                    [<sub>][if][superOnClick]
        try {
            switch (v.getId()) {
                                                                                                    [<sub>][for][onClickSwith]
                                                                                                    [<sub>][for][onClickCase]
                case [id]:
                                                                                                    [<sub>][for][onClickCase]
                                                                                                    [<sub>][if][onClickCheckLogin]
                    if (!isLogin(getAct())) {
                        return;
                    }
                                                                                                    [<sub>][if][onClickCheckLogin]
                                                                                                    [<sub>][if][onClickCheckForm]
                    if (!check[formBean]()) {
                        return;
                    }
                                                                                                    [<sub>][if][onClickCheckForm]
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
        } catch (Exception e) {
            toast(e.getMessage());
            com.codingtu.cooltu.lib4a.log.Logs.e(e);
        }
    }

                                                                                                    [<sub>][for][onClickMethods]
    protected void [methodName]([params]) throws Exception {}
                                                                                                    [<sub>][for][onClickMethods]

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
                                                                                                    [<sub>][for][onLongClickSwith]
                                                                                                    [<sub>][for][onLongClickCase]
            case [id]:
                                                                                                    [<sub>][for][onLongClickCase]
                                                                                                    [<sub>][if][onLongClickCheckLogin]
                if (!isLogin(getAct())) {
                    return false;
                }
                                                                                                    [<sub>][if][onLongClickCheckLogin]
                                                                                                    [<sub>][if][onLongClickCheckForm]
                if (!check[formBean]()) {
                    return false;
                }
                                                                                                    [<sub>][if][onLongClickCheckForm]
                return [methodName](
                                                                                                    [<sub>][if][onLongClickSwitchParams]
                        v[divider]
                                                                                                    [<sub>][if][onLongClickSwitchParams]
                                                                                                    [<sub>][for][onLongClickSwitchParams]
                        ([type]) v.getTag([pkg].R.id.tag_[index])[divider]
                                                                                                    [<sub>][for][onLongClickSwitchParams]
                );
                                                                                                    [<sub>][for][onLongClickSwith]
        }
                                                                                                    [<sub>][if][superOnLongClick]
        return super.onLongClick(v);
                                                                                                    [<sub>][if][superOnLongClick]
                                                                                                    [<sub>][if][superOnLongClickFalse]
        return false;
                                                                                                    [<sub>][if][superOnLongClickFalse]
    }

                                                                                                    [<sub>][for][onLongClickMethods]
    protected boolean [methodName]([params]) {return false;}
                                                                                                    [<sub>][for][onLongClickMethods]
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
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == android.app.Activity.RESULT_OK) {
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
                                                                                                    [<sub>][for][loadMore]
    protected abstract void [adapterName]LoadMore(int page);
                                                                                                    [<sub>][for][loadMore]
                                                                                                    [<sub>][if][toastDialog]
    private [toastDialogFullName] toastDialog;

    protected [toastDialogFullName] getToastDialog() {
        if (toastDialog == null)
            toastDialog = new [toastDialogFullName](getAct())
                    .setLayout([layout])
                    .build();
        return toastDialog;
    }
    protected void toastShow(String msg) {
        [toastDialogFullName] td = getToastDialog();
        td.setContent(msg);
        if (!td.isShow()) {
            td.show();
        }
    }
    protected void toastShow(long time, String msg, [onHiddenFinishedFullName] onHiddenFinished) {
        toastShow(msg);
        [handlerToolFullName].getMainHandler().postDelayed(new java.lang.Runnable() {
            @Override
            public void run() {
                getToastDialog().hidden(onHiddenFinished);
            }
        }, time);
    }

    protected void toastShow(long time, String msg) {
        toastShow(time, msg, null);
    }

    protected void toastHidden(long time, String msg, [onHiddenFinishedFullName] onHiddenFinished) {
        getToastDialog().setContent(msg);
        [handlerToolFullName].getMainHandler().postDelayed(new java.lang.Runnable() {
            @Override
            public void run() {
                getToastDialog().hidden(onHiddenFinished);
            }
        }, time);
    }

    protected void toastHidden(long time, String msg) {
        toastHidden(time, msg, null);
    }
                                                                                                    [<sub>][if][toastDialog]
                                                                                                    [<sub>][if][noticeDialog]
    private [noticeDialogFullName] noticeDialog;

    protected void noticeShow(String msg) {
        if (noticeDialog == null)
            noticeDialog = new [noticeDialogFullName](getAct())
                    .setLayout([layout])
                    .build();
        noticeDialog.setContent(msg);
        noticeDialog.show();
    }
                                                                                                    [<sub>][if][noticeDialog]
                                                                                                    [<sub>][for][editDialog]
    private [editDialogFullName] [edName];

    protected void show[edClassName](String text[if:edShowParam], [type] [name][if:edShowParam]) {
        if ([edName] == null)
            [edName] = new [editDialogFullName].Builder(getAct())
                    .setTitle("[title]")
                    .setHint("[hint]")
                    .setInputType([inputType])
                    .setLayout([layout])
                                                                                                    [<sub>][if][setTextWatcher]
                    .setTextWatcher(get[edClassName]TextWatcher())
                                                                                                    [<sub>][if][setTextWatcher]
                                                                                                    [<sub>][if][stopAnimation]
                    .stopAnimation()
                                                                                                    [<sub>][if][stopAnimation]
                    .setYes(new [editDialogFullName].Yes() {
                        @Override
                        public boolean yes(String text, Object obj) {
                            return [edName]Yes(text[if:edUseYes], [if:edUseYesConvert]([type])[if:edUseYesConvert]obj[if:edUseYes]);
                        }
                    })
                    .build();
        [edName].setEditText(text);
        [edName].setObject([setObject]);
        [edName].show();
    }


    protected boolean [edName]Yes(String text[if:edYesParam], [type] [name][if:edYesParam]) {
        return false;
    }
                                                                                                    [<sub>][if][setTextWatcherMethod]
    protected [edTextWatcherFullName] get[edClassName]TextWatcher() {
        return null;
    }
                                                                                                    [<sub>][if][setTextWatcherMethod]
                                                                                                    [<sub>][for][editDialog]
                                                                                                    [<sub>][for][dialog]
    private [dialogFullName] [dialogName];
                                                                                                    [<sub>][for][showDialog]
    protected void show[dialogClassName]([showDialogParam]) {
        if ([dialogName] == null) {
            [dialogName] = new [dialogFullName](getAct())
                    .setTitle("[title]")
                                                                                                    [<sub>][if][showDialogSetContentStr]
                    .setContent("[content]")
                                                                                                    [<sub>][if][showDialogSetContentStr]
                                                                                                    [<sub>][if][showDialogSetContent]
                    .setContent(content)
                                                                                                    [<sub>][if][showDialogSetContent]
                    .setLeftBtText("[left]")
                    .setRighBtText("[right]")
                    .setLayout([layout])
                    .setOnBtClick(new [onBtClickFullName]() {
                        @Override
                        public void onLeftClick(Object obj) {
                            [dialogName]Left([if:showDialogLeftObj][if:showDialogLeftObjConvert]([type])[if:showDialogLeftObjConvert]obj[if:showDialogLeftObj]);
                        }

                        @Override
                        public void onRightClick(Object obj) {
                            [dialogName]Right([if:showDialogRightObj][if:showDialogRightObjConvert]([type])[if:showDialogRightObjConvert]obj[if:showDialogRightObj]);
                        }
                    })
                    .build();
        }[if:showDialogElse] else {[if:showDialogElse]
                                                                                                    [<sub>][if][showDialogUpdataContent]
            [dialogName].updateContent(content);
        }
                                                                                                    [<sub>][if][showDialogUpdataContent]
        [dialogName].setObject([obj]);
        [dialogName].show();
    }
                                                                                                    [<sub>][for][showDialog]
    protected void [dialogName]Left([if:leftParam][type] [name][if:leftParam]) { }
    protected void [dialogName]Right([if:rightParam][type] [name][if:rightParam]) { }
                                                                                                    [<sub>][for][dialog]
                                                                                                    [<sub>][for][initMethod]
    protected [typeFullName] [methodName]() {
        if ([field] == null) {
            [field] = new [typeFullName]();
                                                                                                    [<sub>][if][initAddDestory]
            [destoryToolFullName].onDestory(getAct(), [field]);
                                                                                                    [<sub>][if][initAddDestory]
            [initMethodName]([field]);
        }
        return [field];
    }

    protected void [initMethodName]([typeFullName] [field]) {}
                                                                                                    [<sub>][for][initMethod]
                                                                                                    [<sub>][if][useFormMethods]
[useFormMethods]
                                                                                                    [<sub>][if][useFormMethods]
                                                                                                    [<sub>][if][other]
[other]
                                                                                                    [<sub>][if][other]
}

model_temp_end */