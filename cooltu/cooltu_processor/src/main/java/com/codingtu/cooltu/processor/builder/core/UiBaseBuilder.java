package com.codingtu.cooltu.processor.builder.core;

import com.codingtu.cooltu.constant.AdapterType;
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
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.lib4j.ts.StringTs;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.Adapter;
import com.codingtu.cooltu.processor.annotation.ui.Init;
import com.codingtu.cooltu.processor.annotation.ui.dialog.DialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.MenuDialogItem;
import com.codingtu.cooltu.processor.annotation.ui.dialog.MenuDialogUse;
import com.codingtu.cooltu.processor.bean.ClickViewInfo;
import com.codingtu.cooltu.processor.bean.NetBackInfo;
import com.codingtu.cooltu.processor.builder.impl.ActBackIntentBuilder;
import com.codingtu.cooltu.processor.deal.NetDeal;
import com.codingtu.cooltu.processor.deal.VHDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;
import com.codingtu.cooltu.processor.lib.tools.TagTools;
import com.sun.tools.javac.tree.JCTree;

import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

public abstract class UiBaseBuilder {
    private final UiBaseInterface uiBase;
    public String uiFullName;
    public String baseClass;
    public IdTools.Id layout;
    public List<KV<String, String>> inBases = new ArrayList<>();
    public HashMap<String, String> inBaseMap = new HashMap<>();
    public HashMap<String, String> fieldMap = new HashMap<>();
    public List<ClickViewInfo> clickViews = new ArrayList<>();
    public List<ClickViewInfo> longClickViews = new ArrayList<>();
    public List<LayoutTools.ViewInfo> viewInfos;
    public List<KV<String, String>> colorStrs = new ArrayList<>();
    public List<KV<String, IdTools.Id>> colorReses = new ArrayList<>();
    public List<KV<String, Float>> dps = new ArrayList<>();
    public List<KV<String, IdTools.Id>> dimens = new ArrayList<>();
    public List<VariableElement> adapters = new ArrayList<>();
    public List<NetBackInfo> netBacks = new ArrayList<>();
    public List<ActBack> actBacks = new ArrayList<>();
    public List<ExecutableElement> actBackMethods = new ArrayList<>();
    public List<VariableElement> editDialogUses = new ArrayList<>();
    public List<VariableElement> menuDialogUses = new ArrayList<>();
    public List<VariableElement> dialogUses = new ArrayList<>();
    public List<VariableElement> inits = new ArrayList<>();

    public boolean isToastDialog;
    public boolean isNoticeDialog;
    private List<String> inBaseInParent;
    private Map<String, LayoutTools.ViewInfo> childViewMap;
    private Map<String, LayoutTools.ViewInfo> allViewMap;


    public UiBaseBuilder(UiBaseInterface uiBase) {
        this.uiBase = uiBase;
    }

    private StringBuilder getStringBuilder(String key) {
        return uiBase.getMap().get(key);
    }

    private JavaInfo javaInfo() {
        return this.uiBase.getJavaInfo();
    }

    public boolean hasChild() {
        return CountTool.count(BaseTools.getThisWithChilds(uiFullName, getChildGetter())) > 1;
    }

    protected abstract BaseTools.GetThis<UiBaseBuilder> getChildGetter();

    protected abstract BaseTools.GetParent<UiBaseBuilder> getParentGetter();

    public boolean hasBaseClass() {
        return CountTool.count(BaseTools.getThisWithParents(this, getParentGetter())) > 1;
    }

    public void addInBase(KV<String, String> fieldKv) {
        inBases.add(fieldKv);
    }

    public void addInits(VariableElement ve) {
        inits.add(ve);
    }


    public void dealLines() {
        inBaseInParent = getInBaseInParent();
        childViewMap = getChildViewMap();
        allViewMap = getAllViewMap();

        uiBase.addTag(getStringBuilder("pkg"), javaInfo().pkg);
        uiBase.addTag(getStringBuilder("name"), javaInfo().name);
        uiBase.addTag(getStringBuilder("baseClass"), baseClass);
        uiBase.addTag(getStringBuilder("netBackIFullName"), FullName.NET_BACK_I);
        uiBase.addTag(getStringBuilder("coreSendParamsFullName"), FullName.CORE_SEND_PARAMS);

        //设置在基础类中的字段
        setBaseField();
        //设置布局layout
        setLayout();
        //
        findView();
        //
        onClick();

        onLongClick();
        //
        colorStr();
        //colorRes
        colorReses();
        //dp
        dps();
        //dimens
        dimens();
        //dealListAdapter
        dealListAdapter();

        //accept
        nets();

        actBacks();

        toastDialog();

        noticeDialog();

        editDialog();

        menuDialog();


        inits();

        Ts.ls(dialogUses, new Ts.EachTs<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {
                KV<String, String> kv = ElementTools.getFieldKv(ve);
                DialogUse dialogUse = ve.getAnnotation(DialogUse.class);
                uiBase.dialog(position, FullName.DIALOG, kv.v);
                String dialogClassName = ConvertTool.toClassType(kv.v);

                String objClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                    @Override
                    public Object get() {
                        return dialogUse.objType();
                    }
                });

                boolean isVoid = ClassTool.isVoid(objClass);

                JavaInfo objJavaInfo = CurrentPath.javaInfo(objClass);

                String objName = ConvertTool.toMethodType(objJavaInfo.name);


                for (int i = 0; i < 2; i++) {
                    StringBuilder showDialogParamSb = new StringBuilder();
                    if (i != 0) {
                        showDialogParamSb.append("String content");
                        uiBase.isShowDialogElse(position, i, true);
                        uiBase.showDialogUpdataContentIf(position, i, kv.v);
                        uiBase.isShowDialogSetContent(position, 1, true);
                    } else {
                        uiBase.showDialogSetContentStrIf(position, 0, dialogUse.content());
                    }
                    if (!isVoid) {
                        if (i != 0) {
                            showDialogParamSb.append(", ");
                        }
                        showDialogParamSb.append(objClass).append(" ").append(objName);
                        uiBase.isShowDialogLeftObj(position, i, true);
                        uiBase.isShowDialogRightObj(position, i, true);
                        boolean isObj = ClassTool.isObject(objClass);
                        if (!isObj) {
                            uiBase.showDialogLeftObjConvertIf(position, i, objClass);
                            uiBase.showDialogRightObjConvertIf(position, i, objClass);
                        }
                    }

                    uiBase.showDialog(position, i, dialogClassName, showDialogParamSb.toString(), kv.v,
                            FullName.DIALOG, dialogUse.title(), dialogUse.leftBtText(), dialogUse.rightBtText(),
                            Constant.DEFAULT_DIALOG_LAYOUT, FullName.DIALOG_ON_BT_CLICK, isVoid ? "null" : objName);
                }

                if (!isVoid) {
                    uiBase.leftParamIf(position, objClass, objName);
                    uiBase.rightParamIf(position, objClass, objName);
                }

                return false;
            }
        });


    }

    private void inits() {
        Ts.ls(inits, new Ts.EachTs<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {
                KV<String, String> kv = ElementTools.getFieldKv(ve);
                Init init = ve.getAnnotation(Init.class);
                String methodName = init.value();
                if (StringTool.isBlank(methodName)) {
                    methodName = kv.v;
                }
                uiBase.initMethod(position, kv.k, methodName, kv.v, methodName);


                boolean destory = init.isDestory();
                if (destory) {
                    uiBase.initAddDestoryIf(position, FullName.DESTORY_TOOL, kv.v);
                }


                return false;
            }
        });
    }


    public boolean addField(String sign, String type, String name) {
        if (fieldMap.get(name) == null) {
            fieldMap.put(name, name);
            if (!inBaseInParent.contains(name)) {
                uiBase.field(uiBase.fieldCount(), sign, type, name);
            }
            return true;
        }
        return false;
    }

    private void setBaseField() {
        Ts.ls(inBases, new Ts.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                addField(Constant.SIGN_PROTECTED, kv.k, kv.v);
                return false;
            }
        });
    }

    private void setLayout() {
        if (layout != null) {
            uiBase.layoutIf(FullName.INFLATE_TOOL, layout.toString());
        }
    }

    private void findView() {
        Ts.ls(viewInfos, new Ts.EachTs<LayoutTools.ViewInfo>() {
            @Override
            public boolean each(int position, LayoutTools.ViewInfo viewInfo) {
                addField(Constant.SIGN_PROTECTED, viewInfo.tag, viewInfo.fieldName);

                String parent = uiBase.getDefulatViewParent();
                if (!viewInfo.fieldName.equals(viewInfo.id)) {
                    parent = viewInfo.parent.fieldName + ".";
                }

                boolean isCoreR = viewInfo.id.startsWith("core_");
                uiBase.findView(position, viewInfo.fieldName, parent, isCoreR ? Pkg.LIB4A : Pkg.R, viewInfo.id);
                return false;
            }
        });
    }


    private void onLongClick() {
        Ts.ls(longClickViews, new Ts.EachTs<ClickViewInfo>() {
            @Override
            public boolean each(int clickViewInfoIndex, ClickViewInfo info) {
                uiBase.isOnLongClickCheckLogin(clickViewInfoIndex, info.isCheckLogin);
                uiBase.isCheckForm(clickViewInfoIndex, false);
                uiBase.onLongClickMethods(clickViewInfoIndex, info.method, info.methodParams.getMethodParams());
                uiBase.onLongClickSwith(clickViewInfoIndex, info.method);

                List<KV<String, String>> kvs = info.methodParams.getKvs();
                int kvCount = CountTool.count(kvs);

                Ts.ls(kvs, new Ts.EachTs<KV<String, String>>() {
                    private int paramsIndex;

                    @Override
                    public boolean each(int kvIndex, KV<String, String> kv) {
                        String divider = (kvIndex != kvCount - 1) ? "," : "";
                        if (kvIndex == 0 && FullName.VIEW.equals(kv.k)) {
                            uiBase.onLongClickSwitchParamsIf(clickViewInfoIndex, divider);
                        } else {
                            uiBase.onLongClickSwitchParams(clickViewInfoIndex, paramsIndex, kv.k, Pkg.LIB4A, paramsIndex + "", divider);
                            paramsIndex++;
                        }
                        return false;
                    }
                });

                Ts.ls(info.ids, new Ts.EachTs<IdTools.Id>() {
                    @Override
                    public boolean each(int idIndex, IdTools.Id id) {
                        uiBase.onLongClickCase(clickViewInfoIndex, idIndex, id.toString());
                        if (info.inAct.get(idIndex)) {
                            LayoutTools.ViewInfo viewInfo = childViewMap.get(id.rName);
                            if (viewInfo != null) {
                                addField(Constant.SIGN_PROTECTED, viewInfo.tag, viewInfo.fieldName);
                            }

                            viewInfo = allViewMap.get(id.rName);
                            if (viewInfo != null) {
                                uiBase.setOnLongClick(uiBase.setOnLongClickCount(), viewInfo.fieldName);
                            }
                        }
                        return false;
                    }
                });
                return false;
            }
        });

        //onclick继承
        if (hasBaseClass()) {
            uiBase.isSuperOnLongClick(true);
        } else {
            uiBase.isSuperOnLongClickFalse(true);
        }
    }


    private void onClick() {
        Ts.ls(clickViews, new Ts.EachTs<ClickViewInfo>() {
            @Override
            public boolean each(int clickViewInfoIndex, ClickViewInfo info) {
                uiBase.isOnClickCheckLogin(clickViewInfoIndex, info.isCheckLogin);
                uiBase.isCheckForm(clickViewInfoIndex, false);
                uiBase.onClickMethods(clickViewInfoIndex, info.method, info.methodParams.getMethodParams());
                uiBase.onClickSwith(clickViewInfoIndex, info.method);

                List<KV<String, String>> kvs = info.methodParams.getKvs();
                int kvCount = CountTool.count(kvs);

                Ts.ls(kvs, new Ts.EachTs<KV<String, String>>() {
                    private int paramsIndex;

                    @Override
                    public boolean each(int kvIndex, KV<String, String> kv) {
                        String divider = (kvIndex != kvCount - 1) ? "," : "";
                        if (kvIndex == 0 && FullName.VIEW.equals(kv.k)) {
                            uiBase.onClickSwitchParamsIf(clickViewInfoIndex, divider);
                        } else {
                            uiBase.onClickSwitchParams(clickViewInfoIndex, paramsIndex, kv.k, Pkg.LIB4A, paramsIndex + "", divider);
                            paramsIndex++;
                        }
                        return false;
                    }
                });

                Ts.ls(info.ids, new Ts.EachTs<IdTools.Id>() {
                    @Override
                    public boolean each(int idIndex, IdTools.Id id) {
                        uiBase.onClickCase(clickViewInfoIndex, idIndex, id.toString());
                        if (info.inAct.get(idIndex)) {
                            LayoutTools.ViewInfo viewInfo = childViewMap.get(id.rName);
                            if (viewInfo != null) {
                                addField(Constant.SIGN_PROTECTED, viewInfo.tag, viewInfo.fieldName);
                            }

                            viewInfo = allViewMap.get(id.rName);
                            if (viewInfo != null) {
                                uiBase.setOnClick(uiBase.setOnClickCount(), viewInfo.fieldName);
                            }
                        }
                        return false;
                    }
                });
                return false;
            }
        });

        //onclick继承
        uiBase.isSuperOnClick(hasBaseClass());
    }

    private void colorStr() {
        //colorStr
        Ts.ls(colorStrs, new Ts.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                addField(Constant.SIGN_PROTECTED, "int", kv.k);
                uiBase.colorStrInit(position, kv.k, kv.v);
                return false;
            }
        });
    }

    private void colorReses() {
        Ts.ls(colorReses, new Ts.EachTs<KV<String, IdTools.Id>>() {
            @Override
            public boolean each(int position, KV<String, IdTools.Id> kv) {
                addField(Constant.SIGN_PROTECTED, "int", kv.k);
                uiBase.colorResInit(position, kv.k, FullName.RESOURCE_TOOL, kv.v.toString());
                return false;
            }
        });
    }


    private void dps() {
        Ts.ls(dps, new Ts.EachTs<KV<String, Float>>() {
            @Override
            public boolean each(int position, KV<String, Float> kv) {
                addField(Constant.SIGN_PROTECTED, "int", kv.k);
                uiBase.dpInit(position, kv.k, FullName.MOBILE_TOOL, kv.v + "f");
                return false;
            }
        });
    }


    private void dimens() {
        Ts.ls(dimens, new Ts.EachTs<KV<String, IdTools.Id>>() {
            @Override
            public boolean each(int position, KV<String, IdTools.Id> kv) {
                addField(Constant.SIGN_PROTECTED, "int", kv.k);
                uiBase.dimenInit(position, kv.k, FullName.RESOURCE_TOOL, kv.v.toString());
                return false;
            }
        });
    }


    private void dealListAdapter() {
        Ts.ls(adapters, new Ts.EachTs<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {
                Adapter adapter = ve.getAnnotation(Adapter.class);

                String configClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                    @Override
                    public Object get() {
                        return adapter.rvConfig();
                    }
                });

                KV<String, String> kv = ElementTools.getFieldKv(ve);
                //添加字段
                addField(Constant.SIGN_PROTECTED, kv.k, kv.v);
                addField(Constant.SIGN_PROTECTED, FullName.RECYCLER_VIEW, adapter.rvName());
                String vh = VHDeal.vhMap.get(kv.k);
                int adapterIndex = uiBase.listAdapterCount();

                if (ClassTool.isVoid(configClass)) {
                    configClass = FullName.RECYCLER_VIEW_DEFAULT_CONFIG;
                }

                uiBase.listAdapter(adapterIndex, kv.v, vh, adapter.rvName(), configClass);
                if (adapter.type() == AdapterType.DEFAULT_MORE_LIST) {
                    uiBase.loadMore(uiBase.loadMoreCount(), kv.v);
                    uiBase.defaultListMoreAdapterIf(adapterIndex, kv.v, kv.k);
                } else if (adapter.type() == AdapterType.DEFAULT_LIST) {
                    uiBase.defaultListAdapterIf(adapterIndex, kv.v, kv.k);
                }
                return false;
            }
        });
    }

    private void nets() {
        uiBase.isSuperAccept(hasBaseClass());
        Ts.ls(netBacks, new Ts.EachTs<NetBackInfo>() {
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

                uiBase.accept(position, methodName, netBackFullName, FullName.CORE_SEND_PARAMS, paramStr);

                String methodParamStr = params.getParam(new Params.Convert() {
                    @Override
                    public String convert(int index, KV<String, String> kv) {
                        return kv.k + " " + kv.v;
                    }
                });


                uiBase.acceptMethod(position, methodName, methodParamStr);
                return false;
            }
        });
    }

    private void actBacks() {
        Ts.ls(actBacks, new Ts.EachTs<ActBack>() {
            @Override
            public boolean each(int actBackIndex, ActBack actBack) {
                ExecutableElement ee = actBackMethods.get(actBackIndex);
                String methodName = ElementTools.simpleName(ee);

                String fromClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                    @Override
                    public Object get() {
                        return actBack.value();
                    }
                });

                JavaInfo fromJavaInfo = CurrentPath.javaInfo(fromClass);

                uiBase.actBack(actBackIndex, actBackIndex == 0 ? "if" : "else if", FullName.CODE_4_REQUEST, ConvertTool.toStaticType(fromJavaInfo.name), methodName);

                Params params = ElementTools.getMethodParamKvs(ee);
                params.ls(new Ts.EachTs<KV<String, String>>() {
                    @Override
                    public boolean each(int paramIndex, KV<String, String> kv) {
                        uiBase.actBackParam(actBackIndex, paramIndex, FullName.PASS, kv.v);
                        uiBase.isActBackParamDivider(actBackIndex, paramIndex, paramIndex != (CountTool.count(ee.getParameters()) - 1));
                        return false;
                    }
                });

                uiBase.actBackMethod(actBackIndex, methodName, params.getMethodParams());
                return false;
            }
        });
    }

    private void toastDialog() {
        if (isToastDialog) {
            uiBase.toastDialogIf(FullName.TOAST_DIALOG, Constant.DEFAULT_TOAST_DIALOG_LAYOUT, FullName.ON_HIDDEN_FINISHED_CALLBACK, FullName.HANDLER_TOOL);
        }
    }

    private void noticeDialog() {
        if (isNoticeDialog) {
            uiBase.noticeDialogIf(FullName.NOTICE_DIALOG, Constant.DEFAULT_NOTICE_DIALOG_LAYOUT);
        }
    }

    private void editDialog() {
        Ts.ls(editDialogUses, new Ts.EachTs<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {
                EditDialogUse editDialogUse = ve.getAnnotation(EditDialogUse.class);
                KV<String, String> kv = ElementTools.getFieldKv(ve);

                String objClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                    @Override
                    public Object get() {
                        return editDialogUse.objType();
                    }
                });
                boolean isVoid = ClassTool.isVoid(objClass);

                JavaInfo objJavaInfo = CurrentPath.javaInfo(objClass);

                String objName = ConvertTool.toMethodType(objJavaInfo.name);

                String edClassName = ConvertTool.toClassType(kv.v);

                uiBase.editDialog(position, FullName.EDIT_DIALOG, kv.v, edClassName,
                        editDialogUse.title(),
                        editDialogUse.hint(), editDialogUse.inputType() + "",
                        Constant.DEFAULT_EDIT_DIALOG_LAYOUT, isVoid ? "null" : objName);

                if (!isVoid) {
                    uiBase.edShowParamIf(position, objClass, objName);
                    uiBase.edYesParamIf(position, objClass, objName);
                    uiBase.isEdUseYes(position, true);
                    boolean isObject = ClassTool.isObject(objClass);
                    if (!isObject) {
                        uiBase.edUseYesConvertIf(position, objClass);
                    }
                }

                if (editDialogUse.isUseTextWatcher()) {
                    uiBase.setTextWatcherIf(position, edClassName);
                    uiBase.setTextWatcherMethodIf(position, FullName.ED_TEXT_WATCHER, edClassName);
                }

                if (editDialogUse.stopAnimation()) {
                    uiBase.isStopAnimation(position, true);
                }

                return false;
            }
        });
    }

    private void menuDialog() {
        StringBuilder sb = new StringBuilder();
        Ts.ls(menuDialogUses, new Ts.EachTs<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {
                MenuDialogUse menuDialogUse = ve.getAnnotation(MenuDialogUse.class);
                String objType = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                    @Override
                    public Object get() {
                        return menuDialogUse.objType();
                    }
                });

                KV<String, String> kv = ElementTools.getFieldKv(ve);
                TagTools.addLnTag(sb, "    /**************************************************\n" +
                        "     *\n" +
                        "     *  [menuDialog]\n" +
                        "     *\n" +
                        "     **************************************************/", kv.v);
                TagTools.addLnTag(sb, "    protected [MenuDialog] [menuDialog];\n", kv.k, kv.v);

                String param = "";
                if (!ClassTool.isVoid(objType)) {
                    param = objType + " obj";
                }

                TagTools.addLnTag(sb, "    protected void show[MenuDialog]([String obj]) {", ConvertTool.toClassType(kv.v), param);
                TagTools.addLnTag(sb, "        if ([menuDialog] == null) {", kv.v);
                TagTools.addLnTag(sb, "            [menuDialog] = new [MenuDialog](getAct())", kv.v, kv.k);
                TagTools.addLnTag(sb, "                    .setLayout([layout])", Constant.DEFAULT_MENU_DIALOG_LAYOUT);
                TagTools.addLnTag(sb, "                    .setItemLayout([item])", Constant.DEFAULT_MENU_DIALOG_ITEM_LAYOUT);

                MenuDialogItem[] items = menuDialogUse.items();
                BaseTs<IdTools.Id> idTs = Ts.ts(IdTools.Id.class);
                StringTs strTs = Ts.strs();
                Ts.ls(items, new Ts.EachTs<MenuDialogItem>() {
                    @Override
                    public boolean each(int position, MenuDialogItem menuDialogItem) {
                        IdTools.Id itemId = IdTools.elementToId(ve, MenuDialogItem.class, menuDialogItem.id());
                        idTs.add(itemId);
                        String name = menuDialogItem.name();
                        strTs.add(name);
                        TagTools.addLnTag(sb, "                    .setItem([R.id.reportTv], \"[导出维修工单]\")", itemId.toString(), name);
                        return false;
                    }
                });

                TagTools.addLnTag(sb, "                    .setOnClickListener(this)");
                TagTools.addLnTag(sb, "                    .setShowItem(new [MenuDialog].ShowItem() {", kv.k);
                TagTools.addLnTag(sb, "                        @Override");
                TagTools.addLnTag(sb, "                        public boolean showItem(int viewId, Object obj) {");
                TagTools.addLnTag(sb, "                            switch (viewId) {");

                idTs.ls(new Ts.EachTs<IdTools.Id>() {
                    @Override
                    public boolean each(int position, IdTools.Id id) {
                        TagTools.addLnTag(sb, "                                case [R.id.reportTv]:", id.toString());

                        if (ClassTool.isVoid(objType)) {
                            TagTools.addLnTag(sb, "                                    return show[ReportTv]();", ConvertTool.toClassType(id.rName));
                        } else if (ClassTool.isObject(objType)) {
                            TagTools.addLnTag(sb, "                                    return show[ReportTv](obj);"
                                    , ConvertTool.toClassType(id.rName));
                        } else {
                            TagTools.addLnTag(sb, "                                    return show[ReportTv](([String]) obj);"
                                    , ConvertTool.toClassType(id.rName), objType);
                        }

                        return false;
                    }
                });


                TagTools.addLnTag(sb, "                            }");
                TagTools.addLnTag(sb, "                            return false;");
                TagTools.addLnTag(sb, "                        }");
                TagTools.addLnTag(sb, "                    })");
                TagTools.addLnTag(sb, "                    .setTitle(\"[操作]\")", menuDialogUse.title());
                TagTools.addLnTag(sb, "                    .build();");
                TagTools.addLnTag(sb, "        }");
                if (ClassTool.isVoid(objType)) {
                    TagTools.addLnTag(sb, "        menuDialog.setObj();");
                } else {
                    TagTools.addLnTag(sb, "        menuDialog.setObj(obj);");
                }
                TagTools.addLnTag(sb, "        menuDialog.show();");
                TagTools.addLnTag(sb, "    }");

                idTs.ls(new Ts.EachTs<IdTools.Id>() {
                    @Override
                    public boolean each(int position, IdTools.Id id) {

                        if (ClassTool.isVoid(objType)) {
                            TagTools.addLnTag(sb, "    protected boolean show[ReportTv]() {", ConvertTool.toClassType(id.rName));
                        } else if (ClassTool.isObject(objType)) {
                            TagTools.addLnTag(sb, "    protected boolean show[ReportTv](Object obj) {"
                                    , ConvertTool.toClassType(id.rName));
                        } else {
                            TagTools.addLnTag(sb, "    protected boolean show[ReportTv]([String] str) {"
                                    , ConvertTool.toClassType(id.rName), objType);
                        }

                        TagTools.addLnTag(sb, "        return true;");
                        TagTools.addLnTag(sb, "    }");
                        return false;
                    }
                });

                return false;
            }
        });
        uiBase.addOthers(sb.toString());
    }


    private List<String> getInBaseInParent() {
        return Ts.ts(getParents()).convertList(new Ts.Convert<UiBaseBuilder, List<String>>() {
            @Override
            public List<String> convert(int index, UiBaseBuilder uiBaseBuilder) {
                if (index != 0) {
                    return uiBaseBuilder.getInBaseInThis();
                }
                return null;
            }
        }).toList();
    }

    private List<String> getInBaseInThis() {
        List<String> inBaseList = Ts.ts(inBases).convert(new Ts.Convert<KV<String, String>, String>() {
            @Override
            public String convert(int index, KV<String, String> kv) {
                return kv.v;
            }
        }).toList();

        Map<String, LayoutTools.ViewInfo> viewMap = getChildViewMap();
        inBaseList.addAll(getIds(viewMap, clickViews));
        inBaseList.addAll(getIds(viewMap, longClickViews));

        inBaseList.addAll(Ts.ts(this.adapters).convert(new Ts.Convert<VariableElement, String>() {
            @Override
            public String convert(int index, VariableElement ve) {
                Adapter adapter = ve.getAnnotation(Adapter.class);
                return adapter.rvName();
            }
        }).toList());

        return inBaseList;
    }

    private List<String> getIds(Map<String, LayoutTools.ViewInfo> viewMap, List<ClickViewInfo> clickViews) {
        return Ts.ts(clickViews).convertList(new Ts.Convert<ClickViewInfo, List<String>>() {
            @Override
            public List<String> convert(int index, ClickViewInfo clickViewInfo) {
                return Ts.ts(clickViewInfo.ids).convert(new Ts.Convert<IdTools.Id, String>() {
                    @Override
                    public String convert(int index, IdTools.Id id) {
                        Boolean aBoolean = clickViewInfo.inAct.get(index);
                        if (aBoolean != null && aBoolean) {
                            LayoutTools.ViewInfo viewInfo = viewMap.get(id.rName);
                            if (viewInfo != null) {
                                return viewInfo.fieldName;
                            }
                        }
                        return null;
                    }
                }).toList();
            }
        }).toList();
    }


    private Map<String, LayoutTools.ViewInfo> getChildViewMap() {
        HashMap<String, LayoutTools.ViewInfo> map = new HashMap<>();
        addViewMap(map, getChilds());
        return map;
    }

    public Map<String, LayoutTools.ViewInfo> getParentViewMap() {
        HashMap<String, LayoutTools.ViewInfo> map = new HashMap<>();
        addViewMap(map, getParents());
        return map;
    }

    private Map<String, LayoutTools.ViewInfo> getAllViewMap() {
        HashMap<String, LayoutTools.ViewInfo> map = new HashMap<>();
        addViewMap(map, getParents());
        addViewMap(map, getChilds());
        return map;
    }

    private void addViewMap(HashMap<String, LayoutTools.ViewInfo> map, List<UiBaseBuilder> uiBaseBuilders) {
        Ts.ts(uiBaseBuilders).ls(new Ts.EachTs<UiBaseBuilder>() {
            @Override
            public boolean each(int position, UiBaseBuilder uiBaseBuilder) {
                Ts.ts(uiBaseBuilder.viewInfos).ls(new Ts.EachTs<LayoutTools.ViewInfo>() {
                    @Override
                    public boolean each(int position, LayoutTools.ViewInfo viewInfo) {
                        map.put(viewInfo.id, viewInfo);
                        return false;
                    }
                });
                return false;
            }
        });
    }

    private List<UiBaseBuilder> getChilds() {
        return BaseTools.getThisWithChilds(uiFullName, getChildGetter());
    }

    private List<UiBaseBuilder> getParents() {
        return BaseTools.getThisWithParents(this, getParentGetter());
    }


    /**************************************************
     *
     *   ┏━━━━━━━━━━━━━━━━━━┓
     *  ┃   setBaseField  ┃
     * ┗━━━━━━━━━━━━━━━━━━━┛
     * {@link #setBaseField()}
     *   ┏━━━━━━━━━━━━┓
     *  ┃   设置布局  ┃
     * ┗━━━━━━━━━━━━━┛
     * {@link #setLayout()}
     *   ┏━━━━━━━━━━━━━━┓
     *  ┃   findView  ┃
     * ┗━━━━━━━━━━━━━━━┛
     * {@link #findView()}
     *
     *
     **************************************************/

}
