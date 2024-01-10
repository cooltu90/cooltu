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
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.Adapter;
import com.codingtu.cooltu.processor.annotation.ui.dialog.DialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.processor.bean.ClickViewInfo;
import com.codingtu.cooltu.processor.bean.NetBackInfo;
import com.codingtu.cooltu.processor.deal.NetDeal;
import com.codingtu.cooltu.processor.deal.VHDeal;
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
    public List<VariableElement> dialogUses = new ArrayList<>();

    public boolean isToastDialog;
    public boolean isNoticeDialog;


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

    public void removeInBase(KV<String, String> kv) {
        inBaseMap.put(kv.v, kv.v);
    }

    public void dealLines() {
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

        Ts.ls(dialogUses, new BaseTs.EachTs<VariableElement>() {
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
                        uiBase.isShowDialogSetContentStr(position, 0, true);
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

                if(!isVoid){
                    uiBase.leftParamIf(position, objClass, objName);
                    uiBase.rightParamIf(position, objClass, objName);
                }

                return false;
            }
        });


    }


    private boolean addField(String sign, String type, String name) {
        if (inBaseMap.get(name) == null && fieldMap.get(name) == null) {
            fieldMap.put(name, name);
            uiBase.field(uiBase.fieldCount(), sign, type, name);
            return true;
        }
        return false;
    }

    private void setBaseField() {
        Ts.ls(inBases, new BaseTs.EachTs<KV<String, String>>() {
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
        Ts.ls(viewInfos, new BaseTs.EachTs<LayoutTools.ViewInfo>() {
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
        Ts.ls(longClickViews, new BaseTs.EachTs<ClickViewInfo>() {
            @Override
            public boolean each(int clickViewInfoIndex, ClickViewInfo info) {
                uiBase.isOnLongClickCheckLogin(clickViewInfoIndex, info.isCheckLogin);
                uiBase.isCheckForm(clickViewInfoIndex, info.isCheckForm);
                uiBase.onLongClickMethods(clickViewInfoIndex, info.method, info.methodParams.getMethodParams());
                uiBase.onLongClickSwith(clickViewInfoIndex, info.method);

                List<KV<String, String>> kvs = info.methodParams.getKvs();
                int kvCount = CountTool.count(kvs);

                Ts.ls(kvs, new BaseTs.EachTs<KV<String, String>>() {
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

                Ts.ls(info.ids, new BaseTs.EachTs<IdTools.Id>() {
                    @Override
                    public boolean each(int idIndex, IdTools.Id id) {
                        uiBase.onLongClickCase(clickViewInfoIndex, idIndex, id.toString());
                        if (info.inAct.get(idIndex)) {
                            BaseTools.getThisWithParents(UiBaseBuilder.this, getParentGetter(), new BaseTs.EachTs<UiBaseBuilder>() {
                                @Override
                                public boolean each(int position, UiBaseBuilder uiBaseBuilder) {
                                    Ts.ts(uiBaseBuilder.viewInfos).convert(new BaseTs.Convert<LayoutTools.ViewInfo, LayoutTools.ViewInfo>() {
                                        @Override
                                        public LayoutTools.ViewInfo convert(int index, LayoutTools.ViewInfo viewInfo) {
                                            if (viewInfo.id.equals(id.rName)) {
                                                uiBase.setOnLongClick(uiBase.setOnLongClickCount(), viewInfo.fieldName);
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
        if (hasBaseClass()) {
            uiBase.isSuperOnLongClick(true);
        } else {
            uiBase.isSuperOnLongClickFalse(true);
        }
    }


    private void onClick() {
        Ts.ls(clickViews, new BaseTs.EachTs<ClickViewInfo>() {
            @Override
            public boolean each(int clickViewInfoIndex, ClickViewInfo info) {
                uiBase.isOnClickCheckLogin(clickViewInfoIndex, info.isCheckLogin);
                uiBase.isCheckForm(clickViewInfoIndex, info.isCheckForm);
                uiBase.onClickMethods(clickViewInfoIndex, info.method, info.methodParams.getMethodParams());
                uiBase.onClickSwith(clickViewInfoIndex, info.method);

                List<KV<String, String>> kvs = info.methodParams.getKvs();
                int kvCount = CountTool.count(kvs);

                Ts.ls(kvs, new BaseTs.EachTs<KV<String, String>>() {
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

                Ts.ls(info.ids, new BaseTs.EachTs<IdTools.Id>() {
                    @Override
                    public boolean each(int idIndex, IdTools.Id id) {
                        uiBase.onClickCase(clickViewInfoIndex, idIndex, id.toString());
                        if (info.inAct.get(idIndex)) {
                            BaseTools.getThisWithParents(UiBaseBuilder.this, getParentGetter(), new BaseTs.EachTs<UiBaseBuilder>() {
                                @Override
                                public boolean each(int position, UiBaseBuilder uiBaseBuilder) {
                                    Ts.ts(uiBaseBuilder.viewInfos).convert(new BaseTs.Convert<LayoutTools.ViewInfo, LayoutTools.ViewInfo>() {
                                        @Override
                                        public LayoutTools.ViewInfo convert(int index, LayoutTools.ViewInfo viewInfo) {
                                            if (viewInfo.id.equals(id.rName)) {
                                                uiBase.setOnClick(uiBase.setOnClickCount(), viewInfo.fieldName);
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
        uiBase.isSuperOnClick(hasBaseClass());
    }

    private void colorStr() {
        //colorStr
        Ts.ls(colorStrs, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                addField(Constant.SIGN_PROTECTED, "int", kv.k);
                uiBase.colorStrInit(position, kv.k, kv.v);
                return false;
            }
        });
    }

    private void colorReses() {
        Ts.ls(colorReses, new BaseTs.EachTs<KV<String, IdTools.Id>>() {
            @Override
            public boolean each(int position, KV<String, IdTools.Id> kv) {
                addField(Constant.SIGN_PROTECTED, "int", kv.k);
                uiBase.colorResInit(position, kv.k, FullName.RESOURCE_TOOL, kv.v.toString());
                return false;
            }
        });
    }


    private void dps() {
        Ts.ls(dps, new BaseTs.EachTs<KV<String, Float>>() {
            @Override
            public boolean each(int position, KV<String, Float> kv) {
                addField(Constant.SIGN_PROTECTED, "int", kv.k);
                uiBase.dpInit(position, kv.k, FullName.MOBILE_TOOL, kv.v + "f");
                return false;
            }
        });
    }


    private void dimens() {
        Ts.ls(dimens, new BaseTs.EachTs<KV<String, IdTools.Id>>() {
            @Override
            public boolean each(int position, KV<String, IdTools.Id> kv) {
                addField(Constant.SIGN_PROTECTED, "int", kv.k);
                uiBase.dimenInit(position, kv.k, FullName.RESOURCE_TOOL, kv.v.toString());
                return false;
            }
        });
    }


    private void dealListAdapter() {
        Ts.ls(adapters, new BaseTs.EachTs<VariableElement>() {
            @Override
            public boolean each(int position, VariableElement ve) {
                Adapter adapter = ve.getAnnotation(Adapter.class);
                KV<String, String> kv = ElementTools.getFieldKv(ve);
                //添加字段
                addField(Constant.SIGN_PROTECTED, kv.k, kv.v);
                String vh = VHDeal.vhMap.get(kv.k);
                int adapterIndex = uiBase.listAdapterCount();
                uiBase.listAdapter(adapterIndex, kv.v, vh, adapter.rvName());
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
        Ts.ls(netBacks, new BaseTs.EachTs<NetBackInfo>() {
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
        Ts.ls(actBacks, new BaseTs.EachTs<ActBack>() {
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
                params.ls(new BaseTs.EachTs<KV<String, String>>() {
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
            uiBase.toastDialogIf(FullName.TOAST_DIALOG, Constant.DEFAULT_TOAST_DIALOG_LAYOUT, FullName.ON_HIDDEN_FINISHED, FullName.HANDLER_TOOL);
        }
    }

    private void noticeDialog() {
        if (isNoticeDialog) {
            uiBase.noticeDialogIf(FullName.NOTICE_DIALOG, Constant.DEFAULT_NOTICE_DIALOG_LAYOUT);
        }
    }

    private void editDialog() {
        Ts.ls(editDialogUses, new BaseTs.EachTs<VariableElement>() {
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
