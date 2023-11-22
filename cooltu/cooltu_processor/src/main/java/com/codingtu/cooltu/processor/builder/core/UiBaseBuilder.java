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
    public List<LayoutTools.ViewInfo> viewInfos;
    public List<KV<String, String>> colorStrs = new ArrayList<>();
    public List<KV<String, IdTools.Id>> colorReses = new ArrayList<>();
    public List<KV<String, Float>> dps = new ArrayList<>();
    public List<KV<String, IdTools.Id>> dimens = new ArrayList<>();
    public List<VariableElement> adapters = new ArrayList<>();
    public List<NetBackInfo> netBacks = new ArrayList<>();
    public List<ActBack> actBacks = new ArrayList<>();
    public List<ExecutableElement> actBackMethods = new ArrayList<>();

    public String finalBaseClass;


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
        return !finalBaseClass.equals(baseClass);
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
                uiBase.findView(position, viewInfo.fieldName, parent, Pkg.R, viewInfo.id);
                return false;
            }
        });
    }

    private void onClick() {
        Ts.ls(clickViews, new BaseTs.EachTs<ClickViewInfo>() {
            @Override
            public boolean each(int clickViewInfoIndex, ClickViewInfo info) {
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
                        if (info.inAct.get(clickViewInfoIndex)) {
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
