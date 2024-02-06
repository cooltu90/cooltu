package com.codingtu.cooltu.bean;

import android.widget.EditText;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.form.TypeOnSetItem;
import com.codingtu.cooltu.lib4a.formbind.push.DefaultEditTextPush;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4j.data.bean.CoreBean;
import com.codingtu.cooltu.processor.annotation.formbind.Bind;
import com.codingtu.cooltu.processor.annotation.formbind.BindEt;
import com.codingtu.cooltu.processor.annotation.formbind.BindRg;
import com.codingtu.cooltu.processor.annotation.formbind.Echo;
import com.codingtu.cooltu.processor.annotation.formbind.EchoMethod;
import com.codingtu.cooltu.processor.annotation.formbind.EchoType;
import com.codingtu.cooltu.processor.annotation.formbind.FormObject;

@FormObject("forms")
public class FormObj extends CoreBean {

    @Bind(id = R.id.nameEt, push = DefaultEditTextPush.class)
    public String name;

    @BindEt(R.id.ageEt)
    @Echo(EchoType.METHOD)
    public String age;

    @BindEt(R.id.provinceEt)
    public String province;

    @BindEt(R.id.cityEt)
    public String city;

    @BindEt(R.id.areaEt)
    public String area;

    @BindRg(id = R.id.classLl, onSetItem = TypeOnSetItem.class, selected = 0)
    public int classIndex;


    @EchoMethod(R.id.nameEt)
    public void nameEcho(EditText nameEt) {
        ViewTool.setText(nameEt, name);
    }

    @EchoMethod(R.id.ageEt)
    public void ageEcho(EditText ageEt) {
        ViewTool.setText(ageEt, age);
    }

}
