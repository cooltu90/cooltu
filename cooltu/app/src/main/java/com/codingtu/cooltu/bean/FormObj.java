package com.codingtu.cooltu.bean;

import android.widget.EditText;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.form.TypeOnSetItem;
import com.codingtu.cooltu.lib4a.form.push.DefaultEditTextPush;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4j.data.bean.CoreBean;
import com.codingtu.cooltu.processor.annotation.form.bind.BindView;
import com.codingtu.cooltu.processor.annotation.form.bind.BindEditText;
import com.codingtu.cooltu.processor.annotation.form.bind.BindRadioGroup;
import com.codingtu.cooltu.processor.annotation.form.bind.BindSeekBar;
import com.codingtu.cooltu.processor.annotation.form.echo.Echo;
import com.codingtu.cooltu.processor.annotation.form.echo.EchoMethod;
import com.codingtu.cooltu.processor.annotation.form.echo.EchoType;
import com.codingtu.cooltu.processor.annotation.form.FormBean;

@FormBean("forms")
public class FormObj extends CoreBean {

    @BindView(id = R.id.nameEt, push = DefaultEditTextPush.class)
    public String name;

    @BindEditText(R.id.ageEt)
    @Echo(EchoType.METHOD)
    public String age;

    @BindEditText(R.id.provinceEt)
    public String province;

    @BindEditText(R.id.cityEt)
    public String city;

    @BindEditText(R.id.areaEt)
    public String area;

    @BindRadioGroup(id = R.id.classLl, onSetItem = TypeOnSetItem.class)
    public int classIndex;

    @BindSeekBar(R.id.timeSb)
    public int seekBar;


    @EchoMethod({R.id.nameEt, R.id.ageEt})
    public void nameEcho(EditText nameEt, String name) {
        ViewTool.setText(nameEt, name);
    }

}
