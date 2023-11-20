package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.builder.base.FragmentBaseBuilderBase;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.core.UiBaseInterface;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

import java.util.List;

public class FragmentBaseBuilder extends FragmentBaseBuilderBase implements UiBaseInterface {

    /**************************************************
     *
     * 初始化
     *
     **************************************************/
    private final UiBaseBuilder uiBaseBuilder;

    public FragmentBaseBuilder(JavaInfo info) {
        super(info);
        uiBaseBuilder = new UiBaseBuilder(this);
    }

    @Override
    protected BuilderType getBuilderType() {
        return BuilderType.fragment;
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        Logs.i(lines);
    }

    @Override
    public String obtainSymbol() {
        return javaInfo.fullName;
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
    }

    @Override
    public String getDefulatViewParent() {
        return "view.";
    }
}
/* model_temp_start
package [[pkg]];

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public class [[name]] extends [[baseClass]] implements View.OnClickListener, [[netBackIFullName]] {
                                                                                                    [<sub>][for][field]
    [sign] [type] [name];
                                                                                                    [<sub>][for][field]
                                                                                                    [<sub>][if][layout]
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = [inflateToolFullName].inflate(inflater, [layout], container);
                                                                                                    [<sub>][for][findView]
        [fieldName] = [parent]findViewById([rPkg].R.id.[id]);
                                                                                                    [<sub>][for][findView]
        return view;
    }
                                                                                                    [<sub>][if][layout]

    @Override
    public void onClick(View v) {

    }

    @Override
    public void accept(String code, Result<ResponseBody> result, [[coreSendParamsFullName]] params, List objs) {

    }

}
model_temp_end */
