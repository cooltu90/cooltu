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
import com.codingtu.cooltu.lib4j.ts.Maps;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.annotation.form.Form;
import com.codingtu.cooltu.processor.annotation.form.FormBean;
import com.codingtu.cooltu.processor.annotation.form.bind.BindEditText;
import com.codingtu.cooltu.processor.annotation.form.bind.BindRadioGroup;
import com.codingtu.cooltu.processor.annotation.form.bind.BindSeekBar;
import com.codingtu.cooltu.processor.annotation.form.bind.BindView;
import com.codingtu.cooltu.processor.annotation.form.check.Check;
import com.codingtu.cooltu.processor.annotation.form.check.CheckMethod;
import com.codingtu.cooltu.processor.annotation.form.check.CheckType;
import com.codingtu.cooltu.processor.annotation.form.echo.Echo;
import com.codingtu.cooltu.processor.annotation.form.echo.EchoMethod;
import com.codingtu.cooltu.processor.annotation.form.echo.EchoType;
import com.codingtu.cooltu.processor.annotation.form.link.LinkMethod;
import com.codingtu.cooltu.processor.annotation.form.link.LinkView;
import com.codingtu.cooltu.processor.annotation.form1.FormConfig;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.Permission;
import com.codingtu.cooltu.processor.builder.base.ActBaseBuilderBase;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.core.UiBaseInterface;
import com.codingtu.cooltu.processor.deal.ActBaseDeal;
import com.codingtu.cooltu.processor.deal.FormBeanDeal;
import com.codingtu.cooltu.processor.deal.FormConfigDeal;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;
import com.codingtu.cooltu.processor.lib.tools.TagTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
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
    public Form form;
    public com.codingtu.cooltu.processor.annotation.form1.Form formConfigs;
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

        if (formConfigs != null) {
            String formConfigClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return formConfigs.value();
                }
            });
            TypeElement formConfigTe = FormConfigDeal.MAP.get(formConfigClass);

            FormConfig formConfig = formConfigTe.getAnnotation(FormConfig.class);
            String formBeanClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return formConfig.bean();
                }
            });

            String formBeanSimpleName = CurrentPath.javaInfo(formBeanClass).name;
            String name = formConfig.name();
            if (StringTool.isBlank(name)) {
                name = ConvertTool.toMethodType(formBeanSimpleName);
            }
            String beanName = name;

            ElementTools.ls(formConfigTe.getEnclosedElements(), new Ts.EachTs<Element>() {
                @Override
                public boolean each(int position, Element element) {
                    return false;
                }
            });

            addField(Constant.SIGN_PROTECTED, formBeanClass, beanName);
            addField(Constant.SIGN_PROTECTED, "boolean", "initFormBean");
            addField(Constant.SIGN_PUBLIC, "BindHandler", "bindHandler");
            useFormInitIf("        initFormView();");
            addLnTag(otherLineSb, "    private void initFormView() {");
            addLnTag(otherLineSb, "        beforInitFormView();");
            addLnTag(otherLineSb, "        if ([form] == null) {", beanName);
            addLnTag(otherLineSb, "            [form] = new [beanClass]();", beanName, formBeanClass);
            addLnTag(otherLineSb, "            initFormBean = true;");
            addLnTag(otherLineSb, "        }");
            addLnTag(otherLineSb, "        bindHandler = new BindHandler([forms]);", beanName);


            addLnTag(otherLineSb, "        if (!initFormBean) {");
            addLnTag(otherLineSb, "        }");
            addLnTag(otherLineSb, "    }");
            addLnTag(otherLineSb, "    protected void beforInitFormView() {}");
            addLnTag(otherLineSb, "");
            addLnTag(otherLineSb, "    public static class BindHandler extends android.os.Handler {");
            addLnTag(otherLineSb, "        private [FormObj2] [form];", formBeanClass, beanName);
            addLnTag(otherLineSb, "        public BindHandler([FormObj2] [form]) {", formBeanClass, beanName);
            addLnTag(otherLineSb, "            this.[form] = [form];", beanName, beanName);
            addLnTag(otherLineSb, "        }");
            addLnTag(otherLineSb, "        @Override");
            addLnTag(otherLineSb, "        public void handleMessage(android.os.Message msg) {");
            addLnTag(otherLineSb, "            super.handleMessage(msg);");
            addLnTag(otherLineSb, "            switch (msg.what){");
            addLnTag(otherLineSb, "            }");
            addLnTag(otherLineSb, "        }");
            addLnTag(otherLineSb, "    }");


        }


        if (form != null) {
            String formBeanClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return form.value();
                }
            });
            TypeElement formBeanTe = FormBeanDeal.MAP.get(formBeanClass);

            String formBeanSimpleName = CurrentPath.javaInfo(formBeanClass).name;

            String name = formBeanTe.getAnnotation(FormBean.class).value();
            if (StringTool.isBlank(name)) {
                name = ConvertTool.toMethodType(formBeanSimpleName);
            }

            String beanName = name;

            addField(Constant.SIGN_PROTECTED, formBeanClass, beanName);
            addField(Constant.SIGN_PROTECTED, "boolean", "initFormBean");
            addField(Constant.SIGN_PUBLIC, "BindHandler", "bindHandler");


            parentViewMap = uiBaseBuilder.getParentViewMap();

            BaseTs<VariableElement> ves = Ts.ts(VariableElement.class);

            Maps<Integer, ExecutableElement> echoMethodMap = Maps.map(new HashMap<>());
            Maps<Integer, ExecutableElement> linkMethodMap = Maps.map(new HashMap<>());
            Maps<Integer, ExecutableElement> checkMethodMap = Maps.map(new HashMap<>());

            ElementTools.ls(formBeanTe.getEnclosedElements(), new Ts.EachTs<Element>() {
                @Override
                public boolean each(int position, Element element) {
                    if (element instanceof VariableElement) {
                        ves.add((VariableElement) element);
                        return false;
                    }

                    if (element instanceof ExecutableElement) {
                        EchoMethod echoMethod = element.getAnnotation(EchoMethod.class);
                        if (echoMethod != null) {
                            Ts.ints(echoMethod.value()).ls(new Ts.EachTs<Integer>() {
                                @Override
                                public boolean each(int position, Integer integer) {
                                    echoMethodMap.put(integer, (ExecutableElement) element);
                                    return false;
                                }
                            });
                            return false;
                        }

                        LinkMethod linkMethod = element.getAnnotation(LinkMethod.class);
                        if (linkMethod != null) {
                            linkMethodMap.put(linkMethod.value(), (ExecutableElement) element);
                            return false;
                        }

                        CheckMethod checkMethod = element.getAnnotation(CheckMethod.class);
                        if (checkMethod != null) {
                            Ts.ints(checkMethod.value()).ls(new Ts.EachTs<Integer>() {
                                @Override
                                public boolean each(int position, Integer integer) {
                                    checkMethodMap.put(integer, (ExecutableElement) element);
                                    return false;
                                }
                            });
                            return false;
                        }

                    }

                    return false;
                }
            });


            //bindHandler
            StringBuilder initSb = new StringBuilder();
            StringBuilder methodsSb = new StringBuilder();

            addLnTag(initSb, "        initFormView();");
            addLnTag(initSb, "        if ([forms] == null) {", beanName);
            addLnTag(initSb, "            [forms] = new [beanClass]();", beanName, formBeanClass);
            addLnTag(initSb, "            initFormBean = true;");
            addLnTag(initSb, "        }");
            addLnTag(initSb, "        bindHandler = new BindHandler([name]);", beanName);

            HashMap<String, String> onSetItemMap = new HashMap<>();
            //通知方法绑定

            ves.ls(new Ts.EachTs<VariableElement>() {

                private void pushMethods(VariableElement ve, Class annoClass, int viewId, String pushClass) {
                    IdTools.Id id = IdTools.elementToId(ve, annoClass, viewId);
                    addLnTag(initSb, "        new [pushClass]().destory(this).bindHandler(bindHandler).addView([view]);",
                            pushClass, getViewFieldName(id));
                }

                @Override
                public boolean each(int position, VariableElement ve) {
                    BindView bindView = ve.getAnnotation(BindView.class);
                    if (bindView != null) {
                        pushMethods(ve, BindView.class, bindView.id(), ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                            @Override
                            public Object get() {
                                return bindView.push();
                            }
                        }));
                        return false;
                    }

                    BindEditText bindEt = ve.getAnnotation(BindEditText.class);
                    if (bindEt != null) {
                        pushMethods(ve, BindEditText.class, bindEt.value(), FullName.DEFAULT_EDIT_TEXT_PUSH);
                        return false;
                    }

                    BindRadioGroup bindRg = ve.getAnnotation(BindRadioGroup.class);
                    if (bindRg != null) {
                        IdTools.Id id = IdTools.elementToId(ve, BindRadioGroup.class, bindRg.id());
                        String onSetItemClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                            @Override
                            public Object get() {
                                return bindRg.onSetItem();
                            }
                        });

                        JavaInfo javaInfo = new JavaInfo(onSetItemClass);
                        String onSetItemName = ConvertTool.toMethodType(javaInfo.name);
                        if (!onSetItemMap.containsKey(onSetItemClass)) {
                            addLnTag(initSb, "        [type] [name] = new [type]();", onSetItemClass, onSetItemName, onSetItemClass);
                            onSetItemMap.put(onSetItemClass, onSetItemClass);
                        }

                        int selected = bindRg.selected();
                        String selectedStr = selected + "";

                        addLnTag(initSb, "        new [DefulatRadioGroupPush]()", FullName.DEFAULT_RADIO_GROUP_PUSH);
                        addLnTag(initSb, "                .destory(this).bindHandler(bindHandler)");
                        addLnTag(initSb, "                .onSetItem([onSetItem]).selected([selectedStr]).addView([view]);", onSetItemName, selectedStr, getViewFieldName(id));
                        return false;
                    }

                    BindSeekBar bindSeekbar = ve.getAnnotation(BindSeekBar.class);
                    if (bindSeekbar != null) {
                        pushMethods(ve, BindSeekBar.class, bindSeekbar.value(), FullName.DEFAULT_SEEK_BAR_PUSH);
                        return false;
                    }

                    return false;
                }
            });

            linkMethodMap.ls(new Ts.MapEach<Integer, ExecutableElement>() {
                @Override
                public boolean each(Integer integer, ExecutableElement ee) {
                    LinkMethod linkMethod = ee.getAnnotation(LinkMethod.class);
                    IdTools.Id handleId = IdTools.elementToId(ee, LinkMethod.class, linkMethod.value());
                    String param = Params.getParam(ElementTools.getVariableElements(ee), new Ts.Convert<VariableElement, String>() {
                        @Override
                        public String convert(int index, VariableElement ve) {
                            LinkView linkView = ve.getAnnotation(LinkView.class);
                            IdTools.Id id = IdTools.elementToId(ve, LinkView.class, linkView.value());

                            KV<String, String> kv = ElementTools.getFieldKv(ve);
                            String viewFieldName = getViewFieldName(id);
                            if (FullName.RADIO_GROUP.equals(kv.k)) {
                                return TagTools.dealLine("getRadioGroup([floorsLl])", viewFieldName);
                            }
                            return viewFieldName;
                        }
                    });


                    addLnTag(initSb, "        bindHandler.link([handlId], [name2Et]);", handleId.toString(), param);
                    return false;
                }
            });

            addLnTag(initSb, "        if (!initFormBean) {");

            //回显
            ves.ls(new Ts.EachTs<VariableElement>() {
                @Override
                public boolean each(int position, VariableElement ve) {
                    KV<String, String> kv = ElementTools.getFieldKv(ve);

                    Echo echo = ve.getAnnotation(Echo.class);
                    int echoType = EchoType.NORMAL;
                    if (echo != null) {
                        echoType = echo.value();
                    }

                    ExecutableElement ee = null;
                    IdTools.Id id = null;

                    BindView bindView = ve.getAnnotation(BindView.class);
                    if (bindView != null) {
                        ee = echoMethodMap.get(bindView.id());
                        id = IdTools.elementToId(ve, BindView.class, bindView.id());
                    }

                    BindEditText bindEt = ve.getAnnotation(BindEditText.class);
                    if (bindEt != null) {
                        id = IdTools.elementToId(ve, BindEditText.class, bindEt.value());
                        if (echoType == EchoType.NORMAL) {
                            addLnTag(initSb, "            [ViewTool].setText([ageEt], [forms].[age]);",
                                    FullName.VIEW_TOOL, getViewFieldName(id), beanName, kv.v);
                            return false;
                        } else if (echoType == EchoType.METHOD) {
                            ee = echoMethodMap.get(bindEt.value());
                        }

                    }

                    BindRadioGroup bindRg = ve.getAnnotation(BindRadioGroup.class);
                    if (bindRg != null) {
                        id = IdTools.elementToId(ve, BindRadioGroup.class, bindRg.id());
                        if (echoType == EchoType.NORMAL) {
                            addLnTag(initSb, "            getRadioGroup([view]).setSelected([forms].[classIndex]);",
                                    getViewFieldName(id), beanName, kv.v);
                            return false;
                        } else if (echoType == EchoType.METHOD) {
                            ee = echoMethodMap.get(bindRg.id());
                        }

                    }

                    BindSeekBar bindSeekbar = ve.getAnnotation(BindSeekBar.class);
                    if (bindSeekbar != null) {
                        id = IdTools.elementToId(ve, BindSeekBar.class, bindSeekbar.value());
                        if (echoType == EchoType.NORMAL) {
                            addLnTag(initSb, "            [timeSb].setProgress([forms].[seekBar]);",
                                    getViewFieldName(id), beanName, kv.v);
                            return false;
                        } else if (echoType == EchoType.METHOD) {
                            ee = echoMethodMap.get(bindSeekbar.value());
                        }
                    }

                    if (ee != null && id != null) {
                        addLnTag(initSb, "            [forms].[nameEcho]([nameEt],[forms].[field]);",
                                beanName, ElementTools.simpleName(ee), getViewFieldName(id), beanName, kv.v);
                    }

                    return false;
                }
            });

            addLnTag(initSb, "        }");


            addLnTag(methodsSb, "    protected void initFormView() {}");
            addLnTag(methodsSb, "");
            addLnTag(methodsSb, "    private boolean check[Photo]() {", CurrentPath.javaInfo(formBeanClass).name);


            ves.ls(new Ts.EachTs<VariableElement>() {
                @Override
                public boolean each(int position, VariableElement ve) {
                    KV<String, String> kv = ElementTools.getFieldKv(ve);
                    Check check = ve.getAnnotation(Check.class);
                    if (check != null) {
                        String prompt = check.prompt();
                        int type = check.type();

                        ExecutableElement ee = null;

                        BindEditText bindEt = ve.getAnnotation(BindEditText.class);
                        if (bindEt != null) {
                            if (type == CheckType.NORMAL) {
                                addLnTag(methodsSb, "        if ([StringTool].isBlank([photo].[label])) {", FullName.STRING_TOOL, beanName, kv.v);
                                addLnTag(methodsSb, "            toast(\"[prompt]\");", prompt);
                                addLnTag(methodsSb, "            return false;");
                                addLnTag(methodsSb, "        }");
                                return false;
                            } else if (type == CheckType.METHOD) {
                                ee = checkMethodMap.get(bindEt.value());
                            }
                        }

                        BindRadioGroup bindRg = ve.getAnnotation(BindRadioGroup.class);
                        if (bindRg != null) {
                            if (type == CheckType.NORMAL) {
                                addLnTag(methodsSb, "        if ([photo].[classType] < 0) {", beanName, kv.v);
                                addLnTag(methodsSb, "            toast(\"[prompt]\");", prompt);
                                addLnTag(methodsSb, "            return false;");
                                addLnTag(methodsSb, "        }");
                                return false;
                            } else if (type == CheckType.METHOD) {
                                ee = checkMethodMap.get(bindRg.id());
                            }
                        }

                        BindView bindView = ve.getAnnotation(BindView.class);
                        if (bindView != null) {
                            ee = checkMethodMap.get(bindView.id());
                        }

                        BindSeekBar bindSeekbar = ve.getAnnotation(BindSeekBar.class);
                        if (bindSeekbar != null && type == CheckType.METHOD) {
                            ee = checkMethodMap.get(bindSeekbar.value());
                        }


                        if (ee != null) {
                            addLnTag(methodsSb, "        if (![photo].[checkLabel]([photo].[param])) {", beanName, ElementTools.simpleName(ee), beanName, kv.v);
                            addLnTag(methodsSb, "            toast(\"[prompt]\");", prompt);
                            addLnTag(methodsSb, "            return false;");
                            addLnTag(methodsSb, "        }");
                        }

                    }
                    return false;
                }
            });


            addLnTag(methodsSb, "        return true;");
            addLnTag(methodsSb, "    }");

            addLnTag(methodsSb, "    public static class BindHandler extends android.os.Handler {");
            addLnTag(methodsSb, "");
            addLnTag(methodsSb, "        private [beanClass] [name];", formBeanClass, name);
            addLnTag(methodsSb, "        private [ListValueMap]<Integer, Object> linkMap = new [ListValueMap]<>();", FullName.LIST_VALUE_MAP, FullName.LIST_VALUE_MAP);
            addLnTag(methodsSb, "");
            addLnTag(methodsSb, "        public BindHandler([beanClass] [name]) {", formBeanClass, name);
            addLnTag(methodsSb, "            this.[name] = [name];", name, name);
            addLnTag(methodsSb, "        }");
            addLnTag(methodsSb, "");
            addLnTag(methodsSb, "        @Override");
            addLnTag(methodsSb, "        public void handleMessage(android.os.Message msg) {");
            addLnTag(methodsSb, "            super.handleMessage(msg);");
            addLnTag(methodsSb, "            List views = linkMap.get(msg.what);");
            addLnTag(methodsSb, "            switch (msg.what) {");


            ves.ls(new Ts.EachTs<VariableElement>() {
                @Override
                public boolean each(int position, VariableElement ve) {

                    KV<String, String> kv = ElementTools.getFieldKv(ve);

                    IdTools.Id id = null;
                    int resId = 0;

                    BindView bindView = ve.getAnnotation(BindView.class);
                    if (bindView != null) {
                        resId = bindView.id();
                        id = IdTools.elementToId(ve, BindView.class, resId);
                    }

                    BindEditText bindEt = ve.getAnnotation(BindEditText.class);
                    if (bindEt != null) {
                        resId = bindEt.value();
                        id = IdTools.elementToId(ve, BindEditText.class, resId);
                    }

                    BindRadioGroup bindRg = ve.getAnnotation(BindRadioGroup.class);
                    if (bindRg != null) {
                        resId = bindRg.id();
                        id = IdTools.elementToId(ve, BindRadioGroup.class, resId);
                    }
                    BindSeekBar bindSeekbar = ve.getAnnotation(BindSeekBar.class);
                    if (bindSeekbar != null) {
                        resId = bindSeekbar.value();
                        id = IdTools.elementToId(ve, BindSeekBar.class, resId);
                    }

                    if (id != null) {
                        addLnTag(methodsSb, "                case [id]:", id.toString());
                        addLnTag(methodsSb, "                    [forms].[name] = ([String]) msg.obj;", beanName, kv.v, kv.k);

                        ExecutableElement ee = linkMethodMap.get(resId);
                        if (ee != null) {
                            LinkMethod linkMethod = ee.getAnnotation(LinkMethod.class);
                            String methodName = ee.getSimpleName().toString();
                            String param = ElementTools.getMethodParamKvs(ee).getParam(new Params.Convert() {
                                @Override
                                public String convert(int index, KV<String, String> kv) {
                                    return TagTools.dealLine("([View]) views.get([index])", kv.k, index);
                                }
                            });
                            addLnTag(methodsSb, "                    [photo].[methodName]([params]);", beanName, methodName, param);
                        }

                        addLnTag(methodsSb, "                    break;");
                    }

                    return false;
                }
            });

            addLnTag(methodsSb, "            }");
            addLnTag(methodsSb, "        }");
            addLnTag(methodsSb, "        public void link(int handleId, Object... linkViews) {");
            addLnTag(methodsSb, "            linkMap.get(handleId).addAll([ts].ts(linkViews).toList());", FullName.TS);
            addLnTag(methodsSb, "        }");

            addLnTag(methodsSb, "    }");

            addLnTag(methodsSb, "    private [RadioGroup] getRadioGroup([ViewGroup] viewGroup) {", FullName.RADIO_GROUP, FullName.VIEW_GROUP);
            addLnTag(methodsSb, "        return (([RadioGroup]) viewGroup.getTag([rPkg].R.id.tag_0));", FullName.RADIO_GROUP, Pkg.LIB4A);
            addLnTag(methodsSb, "    }");

            useFormInitIf(initSb.toString());
            useFormMethodsIf(methodsSb.toString());
        }


        otherIf(otherLineSb.toString());
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
        if (form != null && isCheckForm) {
            String formBeanClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return form.value();
                }
            });
            String formBeanSimpleName = CurrentPath.javaInfo(formBeanClass).name;
            onClickCheckFormIf(index, formBeanSimpleName);
        }
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

public abstract class [[name]] extends [[baseClass]] implements View.OnClickListener, View.OnLongClickListener, [[netBackIFullName]]{
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
                                                                                                    [<sub>][if][useFormInit]
[userFormInit]
                                                                                                    [<sub>][if][useFormInit]
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
        new [configName]().config(getAct(), [rvName]);
                                                                                                    [<sub>][for][listAdapter]
                                                                                                    [<sub>][for][setOnClick]
        [fieldName].setOnClickListener(this);
                                                                                                    [<sub>][for][setOnClick]
                                                                                                    [<sub>][for][setOnLongClick]
        [fieldName].setOnLongClickListener(this);
                                                                                                    [<sub>][for][setOnLongClick]
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
    }

                                                                                                    [<sub>][for][onClickMethods]
    protected void [methodName]([params]) {}
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