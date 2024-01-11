package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.annotation.form.Form;
import com.codingtu.cooltu.processor.annotation.form.FormBean;
import com.codingtu.cooltu.processor.annotation.form.view.BindEditText;
import com.codingtu.cooltu.processor.annotation.form.view.BindMulti;
import com.codingtu.cooltu.processor.annotation.form.view.BindRadioGroup;
import com.codingtu.cooltu.processor.annotation.form.view.BindSeekBar;
import com.codingtu.cooltu.processor.annotation.form.view.BindTextView;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.Permission;
import com.codingtu.cooltu.processor.annotation.ui.dialog.DialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.processor.builder.base.ActBaseBuilderBase;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.core.UiBaseInterface;
import com.codingtu.cooltu.processor.builder.subdeal.BindEditTextDeal;
import com.codingtu.cooltu.processor.builder.subdeal.BindMultiDeal;
import com.codingtu.cooltu.processor.builder.subdeal.BindRadioGroupDeal;
import com.codingtu.cooltu.processor.builder.subdeal.BindSeekBarDeal;
import com.codingtu.cooltu.processor.builder.subdeal.BindTextViewDeal;
import com.codingtu.cooltu.processor.deal.ActBaseDeal;
import com.codingtu.cooltu.processor.deal.FormBeanDeal;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Ts.ls(starts, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                addField(Constant.SIGN_PROTECTED, kv.k, kv.v);
                startInit(position, kv.v, FullName.PASS);
                return false;
            }
        });

        Ts.ls(permissions, new BaseTs.EachTs<Permission>() {
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

        //form
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
            addField(Constant.SIGN_PROTECTED, formBeanClass, name);
            addField(Constant.SIGN_PROTECTED, "boolean", "initFormBean");
            addField(Constant.SIGN_PUBLIC, "BindHandler", "bindHandler");
            bindHandlerIf(formBeanClass, name, FullName.FORM_LINK, FullName.LIST_VALUE_MAP, FullName.TS);
            formInitIf(name, formBeanClass);
            checkFormsIf(formBeanSimpleName);
            dealFormBean(formBeanTe, name);
        }

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
        if (uiBaseBuilder.inBaseMap.get(name) == null && uiBaseBuilder.fieldMap.get(name) == null) {
            uiBaseBuilder.fieldMap.put(name, name);
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
                                                                                                    [<sub>][for][setOnLongClick]
        [fieldName].setOnLongClickListener(this);
                                                                                                    [<sub>][for][setOnLongClick]
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
        [rvName].setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));
                                                                                                    [<sub>][for][listAdapter]

                                                                                                    [<sub>][if][formInit]
                                                                                                    [<sub>][for][rgInit]
        //[viewName]Rg
                                                                                                    [<sub>][if][rgOnSetItemInit]
        [name] = new [type]();
                                                                                                    [<sub>][if][rgOnSetItemInit]
        [viewName]Rg = [radioGroupFullName].obtain(this).setBts([viewName]).setOnSetItem([onSetItem]);
                                                                                                    [<sub>][if][rgDefaultItem]
        [viewName]Rg.setSelected([index]);
                                                                                                    [<sub>][if][rgDefaultItem]
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
                                                                                                    [<sub>][for][echos]
[echos]
                                                                                                    [<sub>][for][echos]
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
    public void onCreateComplete() {
        super.onCreateComplete();
                                                                                                    [<sub>][for][setOnClick]
        [fieldName].setOnClickListener(this);
                                                                                                    [<sub>][for][setOnClick]
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

}

model_temp_end */