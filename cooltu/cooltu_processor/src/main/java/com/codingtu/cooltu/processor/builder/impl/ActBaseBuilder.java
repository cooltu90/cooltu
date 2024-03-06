package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.annotation.bind.Bind;
import com.codingtu.cooltu.processor.annotation.bind.BindConfig;
import com.codingtu.cooltu.processor.annotation.bind.BindField;
import com.codingtu.cooltu.processor.annotation.bind.BindMethod;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindTextView;
import com.codingtu.cooltu.processor.annotation.ui.ViewId;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindEditText;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindRadioGroup;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindSeekBar;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindView;
import com.codingtu.cooltu.processor.annotation.bind.check.Check;
import com.codingtu.cooltu.processor.annotation.bind.check.CheckMethod;
import com.codingtu.cooltu.processor.annotation.bind.echo.EchoMethod;
import com.codingtu.cooltu.processor.annotation.bind.echo.NoEcho;
import com.codingtu.cooltu.processor.annotation.bind.parse.HandleView;
import com.codingtu.cooltu.processor.annotation.bind.parse.ToBean;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.Permission;
import com.codingtu.cooltu.processor.bean.DealBindInfo;
import com.codingtu.cooltu.processor.bean.DealBindVeInfo;
import com.codingtu.cooltu.processor.builder.base.ActBaseBuilderBase;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.core.UiBaseInterface;
import com.codingtu.cooltu.processor.deal.ActBaseDeal;
import com.codingtu.cooltu.processor.deal.BindConfigDeal;
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
    public Bind bind;
    private Map<String, LayoutTools.ViewInfo> parentViewMap;
    private StringBuilder otherLineSb = new StringBuilder();
    private StringBuilder initBindViewSb = new StringBuilder();
    private StringBuilder beforeBindViewSb = new StringBuilder();
    private StringBuilder bindHandlerSb = new StringBuilder();
    private StringBuilder checkBindSb = new StringBuilder();

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

        if (bind != null) {

            parentViewMap = uiBaseBuilder.getParentViewMap();

            useFormInitIf("        initBindView();");

            List<String> bindConfigClassNames = ClassTool.getAnnotationClasses(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return bind.value();
                }
            });
            Ts.ts(bindConfigClassNames).ls(new Ts.EachTs<String>() {
                @Override
                public boolean each(int position, String bindConfigClassName) {
                    dealBind(bindConfigClassName);
                    return false;
                }
            });

            addLnTag(bindHandlerSb, "    public void link([ListValueMap]<Integer, Object> linkMap, int handleId, Object... linkViews) {", FullName.LIST_VALUE_MAP);
            addLnTag(bindHandlerSb, "        linkMap.get(handleId).addAll([Ts].ts(linkViews).toList());", FullName.TS);
            addLnTag(bindHandlerSb, "    }");

            addLnTag(otherLineSb, "    protected void initBindView() {");
            addLnTag(otherLineSb, beforeBindViewSb.toString());
            addLnTag(otherLineSb, "        beforeInitBindView();");
            addLnTag(otherLineSb, initBindViewSb.toString());
            addLnTag(otherLineSb, "    }");
            addLnTag(otherLineSb, "");
            addLnTag(otherLineSb, "    protected void beforeInitBindView() {}");
            addLnTag(otherLineSb, bindHandlerSb.toString());
            addLnTag(otherLineSb, checkBindSb.toString());
        }

        otherIf(otherLineSb.toString());
    }

    private void dealBind(String bindConfigClassName) {
        DealBindInfo info = new DealBindInfo();
        info.bindConfigTe = BindConfigDeal.MAP.get(bindConfigClassName);
        BindConfig bindConfig = info.bindConfigTe.getAnnotation(BindConfig.class);
        String bindBeanClassName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return bindConfig.value();
            }
        });

        if (ClassTool.isVoid(bindBeanClassName)) {
            return;
        }

        info.bindBeanKv = BeanTools.getBeanKv(bindBeanClassName, bindConfig.bindBeanName());
        info.bindConfigKv = BeanTools.getBeanKv(bindConfigClassName, bindConfig.configName());
        info.initKv = new KV<>("boolean", "init" + ConvertTool.toClassType(info.bindBeanKv.v));
        //BindHandler
        String bindHandlerName = info.bindBeanKv.v + "BindHandler";
        info.handlerKv = new KV<>(ConvertTool.toClassType(bindHandlerName), bindHandlerName);
        addField(Constant.SIGN_PROTECTED, info.bindBeanKv.k, info.bindBeanKv.v);
        addField(Constant.SIGN_PROTECTED, info.bindConfigKv.k, info.bindConfigKv.v);
        addField(Constant.SIGN_PROTECTED, info.initKv.k, info.initKv.v);
        addField(Constant.SIGN_PROTECTED, info.handlerKv.k, info.handlerKv.v);

        info.echoMethodMap = new HashMap<>();
        info.bindMethodMap = new HashMap<>();
        info.toBeanMethodMap = new HashMap<>();
        info.handleViewMethodMap = new HashMap<>();
        info.checkMethodMap = new HashMap<>();
        ElementTools.ls(info.bindConfigTe.getEnclosedElements(), new Ts.EachTs<Element>() {
            @Override
            public boolean each(int position, Element element) {
                if (element instanceof ExecutableElement) {
                    ExecutableElement ee = (ExecutableElement) element;
                    BindMethod bindMethod = ee.getAnnotation(BindMethod.class);
                    if (bindMethod != null) {
                        info.bindMethodMap.put(bindMethod.value(), ee);
                        return false;
                    }

                    EchoMethod echoMethod = ee.getAnnotation(EchoMethod.class);
                    if (echoMethod != null) {
                        info.echoMethodMap.put(echoMethod.value(), ee);
                        return false;
                    }

                    ToBean toBean = ee.getAnnotation(ToBean.class);
                    if (toBean != null) {
                        Ts.strs(toBean.value()).ls(new Ts.EachTs<String>() {
                            @Override
                            public boolean each(int position, String s) {
                                info.toBeanMethodMap.put(s, ee);
                                return false;
                            }
                        });
                        return false;
                    }

                    HandleView handleView = ee.getAnnotation(HandleView.class);
                    if (handleView != null) {
                        info.handleViewMethodMap.put(handleView.value(), ee);
                        return false;
                    }

                    CheckMethod checkMethod = ee.getAnnotation(CheckMethod.class);
                    if (checkMethod != null) {
                        Ts.strs(checkMethod.value()).ls(new Ts.EachTs<String>() {
                            @Override
                            public boolean each(int position, String s) {
                                info.checkMethodMap.put(s, ee);
                                return false;
                            }
                        });
                        return false;
                    }
                }
                return false;
            }
        });

        info.bindSb = new StringBuilder();
        info.echoSb = new StringBuilder();
        info.handleSb = new StringBuilder();
        info.checkSb = new StringBuilder();
        info.onSetItemMap = new HashMap<String, KV<String, String>>();
        ElementTools.ls(info.bindConfigTe.getEnclosedElements(), new Ts.EachTs<Element>() {
            @Override
            public boolean each(int position, Element element) {
                if (element instanceof VariableElement) {
                    DealBindVeInfo veInfo = new DealBindVeInfo();
                    veInfo.ve = (VariableElement) element;
                    BindField bindField = veInfo.ve.getAnnotation(BindField.class);
                    veInfo.noEcho = veInfo.ve.getAnnotation(NoEcho.class);
                    veInfo.fieldOriKv = null;
                    veInfo.fieldKv = null;
                    veInfo.echoMethodEe = null;
                    if (bindField != null) {
                        veInfo.fieldOriKv = ElementTools.getFieldKv(veInfo.ve);
                        veInfo.fieldKv = BeanTools.getBeanKv(veInfo.ve, bindField.value());
                        veInfo.echoMethodEe = info.echoMethodMap.get(veInfo.fieldOriKv.v);
                        Check check = veInfo.ve.getAnnotation(Check.class);
                        if (check != null) {
                            //有check
                            ExecutableElement checkMethodEe = info.checkMethodMap.get(veInfo.fieldOriKv.v);
                            if (checkMethodEe != null) {
                                //有checkMethod
                                addLnTag(info.checkSb, "        if (![infoBindConfig].[checkEcho]([info].[id])) {",
                                        info.bindConfigKv.v, ElementTools.simpleName(checkMethodEe), info.bindBeanKv.v, veInfo.fieldKv.v);
                            } else {
                                if (isLong(veInfo) || isInt(veInfo)) {
                                    addLnTag(info.checkSb, "        if ([info].[id] < 0) {", info.bindBeanKv.v, veInfo.fieldKv.v);
                                } else if (isString(veInfo)) {
                                    addLnTag(info.checkSb, "        if ([StringTool].isBlank([info].[name])) {",
                                            FullName.STRING_TOOL, info.bindBeanKv.v, veInfo.fieldKv.v);
                                }
                            }
                            addLnTag(info.checkSb, "            toast(\"[prompt]\");", check.prompt());
                            addLnTag(info.checkSb, "            return false;");
                            addLnTag(info.checkSb, "        }");
                        }
                    }

                    if (veInfo.fieldOriKv != null && veInfo.noEcho == null && veInfo.echoMethodEe != null) {
                        List<VariableElement> ves = ElementTools.getVariableElements(veInfo.echoMethodEe);
                        String param = Params.getParam(ves, new Ts.Convert<VariableElement, String>() {
                            @Override
                            public String convert(int index, VariableElement ve) {
                                if (index == 0) {
                                    return null;
                                }
                                return getViewFieldName(ve);
                            }
                        });

                        addLnTag(info.echoSb, "            [infoBindConfig].[idEcho]([info].[id], [idEt]);",
                                info.bindConfigKv.v, ElementTools.simpleName(veInfo.echoMethodEe), info.bindBeanKv.v, veInfo.fieldKv.v, param);
                    }

                    BindSeekBar bindSeekbar = veInfo.ve.getAnnotation(BindSeekBar.class);
                    if (bindSeekbar != null) {
                        veInfo.annoClass = BindSeekBar.class;
                        veInfo.annoValue = bindSeekbar.value();

                        dealBind(info, veInfo, new DealBind() {
                            @Override
                            public void dealBind() {
                                addLnTag(info.bindSb, "        [timeSb].setOnSeekBarChangeListener(new [HandlerOnSeekBarChangeListener](this, [infoBindHandler], [rPkg].R.id.[timeSb]));",
                                        veInfo.viewFieldName, FullName.HANDLER_ON_SEEK_BAR_CHANGE_LISTENER, info.handlerKv.v, Pkg.R, veInfo.id.rName);
                            }

                            @Override
                            public void dealEcho() {
                                if (ClassTool.isInt(veInfo.fieldOriKv.k) || ClassTool.isInteger(veInfo.fieldOriKv.k)) {
                                    addLnTag(info.echoSb, "            [timeSb].setProgress([info].[time]);",
                                            veInfo.viewFieldName, info.bindBeanKv.v, veInfo.fieldKv.v);
                                }
                            }

                            @Override
                            public void dealToBean() {
                                if (ClassTool.isInt(veInfo.fieldOriKv.k) || ClassTool.isInteger(veInfo.fieldOriKv.k)) {
                                    addLnTag(info.handleSb, "                    [infoBindConfig].[height] = (int) msg.obj;",
                                            info.bindConfigKv.v, veInfo.fieldOriKv.v);
                                }
                            }
                        });
                    }

                    BindRadioGroup bindRg = veInfo.ve.getAnnotation(BindRadioGroup.class);
                    if (bindRg != null) {
                        veInfo.annoClass = BindRadioGroup.class;
                        veInfo.annoValue = bindRg.id();

                        dealBind(info, veInfo, 1, new DealBind() {
                            @Override
                            public void dealBind() {
                                addLnTag(info.bindSb, "        [num1]Rg.addOnSelectChange(new [HandlerOnSelectChange](this, [infoBindHandler], [rPkg].R.id.[numLl]));",
                                        veInfo.fieldOriKv.v, FullName.HANDLER_ON_SELECT_CHANGE, info.handlerKv.v, Pkg.R, veInfo.id.rName);
                                addLnTag(info.bindSb, "        link([infoBindHandler].linkMap, [rPkg].R.id.[numLl], [numLl]);",
                                        info.handlerKv.v, Pkg.R, veInfo.id.rName, veInfo.viewFieldName);
                            }

                            @Override
                            public void dealEcho() {
                                if (ClassTool.isString(veInfo.fieldOriKv.k)) {
                                    addLnTag(info.echoSb, "            [num]Rg.setSelected([num]Rg.getIndex([info].[num]));",
                                            veInfo.fieldOriKv.v, veInfo.fieldOriKv.v, info.bindBeanKv.v, veInfo.fieldKv.v);
                                } else if (ClassTool.isInt(veInfo.fieldOriKv.k) || ClassTool.isInteger(veInfo.fieldOriKv.k)) {
                                    addLnTag(info.echoSb, "            [num1]Rg.setSelected([info].[num1]);",
                                            veInfo.fieldOriKv.v, info.bindBeanKv.v, veInfo.fieldKv.v);
                                }
                            }

                            @Override
                            public void dealToBean() {
                                if (ClassTool.isString(veInfo.fieldOriKv.k)) {
                                    addLnTag(info.handleSb,
                                            "                    [infoBindConfig].[num] = [ViewTool].getRadioGroupItem(([ViewGroup]) linkObjs.get(0), (int) msg.obj);",
                                            info.bindConfigKv.v, veInfo.fieldOriKv.v, FullName.VIEW_TOOL, FullName.VIEW_GROUP);
                                } else if (ClassTool.isInteger(veInfo.fieldOriKv.k) || ClassTool.isInt(veInfo.fieldOriKv.k)) {
                                    addLnTag(info.handleSb, "                    [infoBindConfig].[num1] = (int) msg.obj;",
                                            info.bindConfigKv.v, veInfo.fieldOriKv.v);
                                }
                            }
                        });

                        KV<String, String> onSetItemKv = BeanTools.getBeanKv(ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                            @Override
                            public Object get() {
                                return bindRg.onSetItem();
                            }
                        }), bindRg.onSetItemName());

                        KV<String, String> kv = info.onSetItemMap.get(onSetItemKv.v);
                        if (kv == null) {
                            addLnTag(beforeBindViewSb, "        [TypeOnSetItem] [typeOnSetItem] = new [TypeOnSetItem]();",
                                    onSetItemKv.k, onSetItemKv.v, onSetItemKv.k);
                            info.onSetItemMap.put(onSetItemKv.v, onSetItemKv);
                        }

                        addField(Constant.SIGN_PROTECTED, FullName.RADIO_GROUP, veInfo.fieldOriKv.v + "Rg");

                        String setItems = "";
                        String param = Params.getParam(bindRg.items(), new Ts.Convert<String, String>() {
                            @Override
                            public String convert(int index, String s) {
                                return "\"" + s + "\"";
                            }
                        });
                        if (StringTool.isNotBlank(param)) {
                            setItems = ".setItems(" + param + ")";
                        }


                        addLnTag(beforeBindViewSb, "        [num]Rg = [RadioGroup].obtain(this).setBts([numLl]).setOnSetItem([typeOnSetItem])[setItems];",
                                veInfo.fieldOriKv.v, FullName.RADIO_GROUP, veInfo.viewFieldName, onSetItemKv.v, setItems);
                        addLnTag(beforeBindViewSb, "        [numLl].setTag([lib4aPkg].R.id.tag_0, [num]Rg);",
                                veInfo.viewFieldName, Pkg.LIB4A, veInfo.fieldOriKv.v);

                        return false;
                    }

                    BindTextView bindTv = veInfo.ve.getAnnotation(BindTextView.class);
                    if (bindTv != null) {
                        veInfo.annoClass = BindTextView.class;
                        veInfo.annoValue = bindTv.value();
                        dealBind(info, veInfo, new DealBind() {
                            @Override
                            public void dealBind() {
                                addLnTag(info.bindSb, "        [idEt].addTextChangedListener(new [HandlerTextWatcher](this, [infoBindHandler], [rPkg].R.id.[idEt]));",
                                        veInfo.viewFieldName, FullName.HANDLER_TEXT_WATCHER, info.handlerKv.v, Pkg.R, veInfo.id.rName);
                            }

                            @Override
                            public void dealEcho() {
                                addLnTag(info.echoSb, "            [ViewTool].setText([passwordTv], [info].[password]);",
                                        FullName.VIEW_TOOL, veInfo.viewFieldName, info.bindBeanKv.v, veInfo.fieldKv.v);
                            }

                            @Override
                            public void dealToBean() {
                                if (isString(veInfo)) {
                                    addLnTag(info.handleSb, "                    [infoBindConfig].[name] = (String) msg.obj;",
                                            info.bindConfigKv.v, veInfo.fieldOriKv.v);
                                } else if (isInt(veInfo)) {
                                    addLnTag(info.handleSb, "                    [infoBindConfig].[age] = Integer.parseInt((String) msg.obj);",
                                            info.bindConfigKv.v, veInfo.fieldOriKv.v);
                                } else if (isLong(veInfo)) {
                                    addLnTag(info.handleSb, "                    [infoBindConfig].[age] = Long.parseLong((String) msg.obj);",
                                            info.bindConfigKv.v, veInfo.fieldOriKv.v);
                                }
                            }
                        });

                    }

                    BindEditText bindEt = veInfo.ve.getAnnotation(BindEditText.class);
                    if (bindEt != null) {
                        veInfo.annoClass = BindEditText.class;
                        veInfo.annoValue = bindEt.value();
                        dealBind(info, veInfo, new DealBind() {
                            @Override
                            public void dealBind() {
                                addLnTag(info.bindSb, "        [idEt].addTextChangedListener(new [HandlerTextWatcher](this, [infoBindHandler], [rPkg].R.id.[idEt]));",
                                        veInfo.viewFieldName, FullName.HANDLER_TEXT_WATCHER, info.handlerKv.v, Pkg.R, veInfo.id.rName);
                            }

                            @Override
                            public void dealEcho() {
                                addLnTag(info.echoSb, "            [ViewTool].setEditTextAndSelection([nameEt], [info].[name]);",
                                        FullName.VIEW_TOOL, veInfo.viewFieldName, info.bindBeanKv.v, veInfo.fieldKv.v);
                            }

                            @Override
                            public void dealToBean() {
                                if (isString(veInfo)) {
                                    addLnTag(info.handleSb, "                    [infoBindConfig].[name] = (String) msg.obj;",
                                            info.bindConfigKv.v, veInfo.fieldOriKv.v);
                                } else if (isInt(veInfo)) {
                                    addLnTag(info.handleSb, "                    [infoBindConfig].[age] = Integer.parseInt((String) msg.obj);",
                                            info.bindConfigKv.v, veInfo.fieldOriKv.v);
                                } else if (isLong(veInfo)) {
                                    addLnTag(info.handleSb, "                    [infoBindConfig].[age] = Long.parseLong((String) msg.obj);",
                                            info.bindConfigKv.v, veInfo.fieldOriKv.v);
                                }
                            }
                        });
                        return false;
                    }
                    BindView bindView = veInfo.ve.getAnnotation(BindView.class);
                    if (bindView != null) {
                        veInfo.annoClass = BindView.class;
                        veInfo.annoValue = bindView.value();
                        dealBind(info, veInfo, new DealBind() {
                            @Override
                            public void dealBind() {
                                addLnTag(info.bindSb, "        [infoBindConfig].[bindAgeEt](this, [ageEt], [infoBindHandler]);",
                                        info.bindConfigKv.v, ElementTools.simpleName(info.bindMethodMap.get(bindView.value())),
                                        veInfo.viewFieldName, info.handlerKv.v);
                            }

                            @Override
                            public void dealEcho() {

                            }

                            @Override
                            public void dealToBean() {
                                addLnTag(info.handleSb, "                    [infoBindConfig].[age] = ([int]) msg.obj;",
                                        info.bindConfigKv.v, veInfo.fieldOriKv.v, veInfo.fieldOriKv.k);
                            }
                        });
                        return false;
                    }
                }
                return false;
            }
        });


        addLnTag(initBindViewSb, "        if ([info] == null) {", info.bindBeanKv.v);
        addLnTag(initBindViewSb, "            [info] = new [Info]();", info.bindBeanKv.v, info.bindBeanKv.k);
        addLnTag(initBindViewSb, "            [infoBindConfig] = new [InfoBindConfig]();", info.bindConfigKv.v, info.bindConfigKv.k);
        addLnTag(initBindViewSb, "            [initName] = true;", info.initKv.v);
        addLnTag(initBindViewSb, "        }");
        addLnTag(initBindViewSb, "        [infoBindHandler] = new [InfoBindHandler]([info], [infoBindConfig]);",
                info.handlerKv.v, info.handlerKv.k, info.bindBeanKv.v, info.bindConfigKv.v);
        //绑定事件
        addLnTag(initBindViewSb, info.bindSb.toString());
        addLnTag(initBindViewSb, "        if (![initInfo]) {", info.initKv.v);
        //回显事件
        addLnTag(initBindViewSb, info.echoSb.toString());
        addLnTag(initBindViewSb, "        }");
        //bindHandlerSb
        addLnTag(bindHandlerSb, "    public static class [InfoBindHandler] extends android.os.Handler {", info.handlerKv.k);
        addLnTag(bindHandlerSb, "        private [Info] [info];", info.bindBeanKv.k, info.bindBeanKv.v);
        addLnTag(bindHandlerSb, "        private [InfoBindConfig] [infoBindConfig];", info.bindConfigKv.k, info.bindConfigKv.v);
        addLnTag(bindHandlerSb, "        private [ListValueMap]<Integer, Object> linkMap = new [ListValueMap]<>();",
                FullName.LIST_VALUE_MAP, FullName.LIST_VALUE_MAP);
        addLnTag(bindHandlerSb, "");
        addLnTag(bindHandlerSb, "        public [InfoBindHandler]([Info] [info], [InfoBindConfig] [infoBindConfig]) {",
                info.handlerKv.k, info.bindBeanKv.k, info.bindBeanKv.v, info.bindConfigKv.k, info.bindConfigKv.v);
        addLnTag(bindHandlerSb, "            this.[info] = [info];", info.bindBeanKv.v, info.bindBeanKv.v);
        addLnTag(bindHandlerSb, "            this.[infoBindConfig] = [infoBindConfig];", info.bindConfigKv.v, info.bindConfigKv.v);
        addLnTag(bindHandlerSb, "        }");
        addLnTag(bindHandlerSb, "        @Override");
        addLnTag(bindHandlerSb, "        public void handleMessage(android.os.Message msg) {");
        addLnTag(bindHandlerSb, "            super.handleMessage(msg);");
        addLnTag(bindHandlerSb, "            List<Object> linkObjs = linkMap.get(msg.what);");
        addLnTag(bindHandlerSb, "            switch (msg.what) {");
        addLnTag(bindHandlerSb, info.handleSb.toString());
        addLnTag(bindHandlerSb, "            }");
        addLnTag(bindHandlerSb, "        }");
        addLnTag(bindHandlerSb, "    }");

        addLnTag(bindHandlerSb, "    protected boolean check[Info]() {", ConvertTool.toClassType(info.bindBeanKv.v));
        addLnTag(bindHandlerSb, info.checkSb.toString());
        addLnTag(bindHandlerSb, "        return true;");
        addLnTag(bindHandlerSb, "    }");


    }

    private boolean isInt(DealBindVeInfo veInfo) {
        return ClassTool.isInt(veInfo.fieldOriKv.k) || ClassTool.isInteger(veInfo.fieldOriKv.k);
    }

    private boolean isLong(DealBindVeInfo veInfo) {
        return ClassTool.isLong(veInfo.fieldOriKv.k) || ClassTool.isLONG(veInfo.fieldOriKv.k);
    }

    private boolean isString(DealBindVeInfo veInfo) {
        return ClassTool.isString(veInfo.fieldOriKv.k);
    }

    private static interface DealBind {
        void dealBind();

        void dealEcho();

        void dealToBean();
    }

    private void dealBind(DealBindInfo info, DealBindVeInfo veInfo, DealBind dealBind) {
        dealBind(info, veInfo, 0, dealBind);
    }

    private void dealBind(DealBindInfo info, DealBindVeInfo veInfo, int handleViewSkip, DealBind dealBind) {
        veInfo.id = IdTools.elementToId(veInfo.ve, veInfo.annoClass, veInfo.annoValue);
        veInfo.viewFieldName = getViewFieldName(veInfo.id);
        dealBind.dealBind();
        if (veInfo.fieldOriKv != null && veInfo.noEcho == null && veInfo.echoMethodEe == null) {
            dealBind.dealEcho();
        }
        addLnTag(info.handleSb, "                case [rPkg].R.id.[nameEt]:", Pkg.R, veInfo.viewFieldName);
        if (veInfo.fieldOriKv == null) {
            veInfo.fieldOriKv = ElementTools.getFieldKv(veInfo.ve);
        }
        ExecutableElement toBeanEe = info.toBeanMethodMap.get(veInfo.fieldOriKv.v);
        if (toBeanEe != null) {
            addLnTag(info.handleSb, "                    [infoBindConfig].[id] = [infoBindConfig].[parseLong](msg.obj);",
                    info.bindConfigKv.v, veInfo.fieldOriKv.v, info.bindConfigKv.v, ElementTools.simpleName(toBeanEe));
        } else {
            dealBind.dealToBean();
        }

        if (veInfo.fieldKv != null) {
            addLnTag(info.handleSb, "                    [info].[id] = [infoBindConfig].[id];",
                    info.bindBeanKv.v, veInfo.fieldKv.v, info.bindConfigKv.v, veInfo.fieldOriKv.v);
        }

        ExecutableElement handleViewEe = info.handleViewMethodMap.get(veInfo.annoValue);
        if (handleViewEe != null) {
            List<VariableElement> ves = ElementTools.getVariableElements(handleViewEe);
            String param = Params.getParam(ves, new Ts.Convert<VariableElement, String>() {
                @Override
                public String convert(int index, VariableElement ve) {
                    if (index == 0) {
                        return null;
                    }
                    KV<String, String> kv = ElementTools.getFieldKv(ve);
                    return "(" + kv.k + ") linkObjs.get(" + (index - 1 + handleViewSkip) + ")";
                }
            });
            if (StringTool.isNotBlank(param)) {
                param = ", " + param;
            } else {
                param = "";
            }
            addLnTag(info.handleSb, "                    [infoBindConfig].[handleProvince]([info][params]);",
                    info.bindConfigKv.v, ElementTools.simpleName(handleViewEe), info.bindBeanKv.v, param);
            if (CountTool.count(ves) > 1) {
                String param1 = Params.getParam(ves, new Ts.Convert<VariableElement, String>() {
                    @Override
                    public String convert(int index, VariableElement ve) {
                        if (index == 0)
                            return null;
                        return getViewFieldName(ve);
                    }
                });
                addLnTag(info.bindSb, "        link([infoBindHandler].linkMap, [rPkg].R.id.[nameEt], [nicknameEt]);",
                        info.handlerKv.v, Pkg.R, veInfo.id.rName, param1);
            }

        }


        addLnTag(info.handleSb, "                    break;");

    }

    private String getViewFieldName(VariableElement ve) {
        ViewId viewId = ve.getAnnotation(ViewId.class);
        IdTools.Id id = IdTools.elementToId(ve, ViewId.class, viewId.value());
        return getViewFieldName(id);
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
//        if (bind != null && isCheckForm) {
//            String formBeanClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
//                @Override
//                public Object get() {
//                    return bind.value();
//                }
//            });
//            String formBeanSimpleName = CurrentPath.javaInfo(formBeanClass).name;
//            onClickCheckFormIf(index, formBeanSimpleName);
//        }
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