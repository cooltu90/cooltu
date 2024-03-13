package com.codingtu.cooltu.bind;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.Data;
import com.codingtu.cooltu.form.TypeOnSetItem;
import com.codingtu.cooltu.processor.annotation.bind.BindConfig;
import com.codingtu.cooltu.processor.annotation.bind.BindField;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindEditText;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindRadioGroup;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindSeekBar;
import com.codingtu.cooltu.processor.annotation.bind.check.Check;
import com.codingtu.cooltu.processor.annotation.bind.check.CheckMethod;
import com.codingtu.cooltu.processor.annotation.bind.radiogroup.RadioGroupOnClickMethod;
import com.codingtu.cooltu.processor.annotation.ui.ViewId;

@BindConfig(Data.class)
public class DataBindConfig {

    @BindField
    @BindEditText(R.id.nameEt)
    @Check(prompt = "请输入名字")
    public String name;

    @CheckMethod(fields = "name", prompts = "请输入名字")
    public boolean checkName(Data data, @ViewId(R.id.nameEt) EditText nameEt) {
        return true;
    }

    @BindField
    @BindSeekBar(R.id.timeSeekBar)
    public int time;


    @BindField
    @BindRadioGroup(
            id = R.id.numLl,
            onSetItem = TypeOnSetItem.class,
            items = {"壹", "贰", "叁", "肆"},
            selected = 1
    )
    public String num;

    @RadioGroupOnClickMethod(R.id.numLl)
    public boolean onNumClick(Data data, int selectedId, View selectedView, @ViewId(R.id.numLl) LinearLayout numLl) {
        if (selectedId == 0) {
            return false;
        }
        return true;
    }

}
