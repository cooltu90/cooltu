package com.codingtu.cooltu.bean;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.processor.annotation.form1.FormConfig;
import com.codingtu.cooltu.processor.annotation.form1.bind.BindEt;

@FormConfig(bean = FormObj2.class, name = "form")
public class FormConfigs {

    @BindEt(R.id.nameEt)
    public String name;




}
