package com.codingtu.cooltu.bean;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.form.Name1FormCheck;
import com.codingtu.cooltu.form.Name1Parse;
import com.codingtu.cooltu.lib4j.data.bean.CoreBean;
import com.codingtu.cooltu.processor.annotation.form.FormCheck;
import com.codingtu.cooltu.processor.annotation.form.FormParse;
import com.codingtu.cooltu.processor.annotation.form.view.BindEditText;
import com.codingtu.cooltu.processor.annotation.form.FormBean;
import com.codingtu.cooltu.processor.annotation.form.view.BindTextView;

@FormBean
public class Forms extends CoreBean {

    @BindEditText(value = R.id.nameEt1)
    @FormParse(Name1Parse.class)
    @FormCheck(prompt = "请输入name1")
    public String name1;

    @BindEditText(value = R.id.nameEt2, echo = false)
    @FormCheck(prompt = "请输入name2")
    public String name2;

    @BindEditText(R.id.nameEt3)
    @FormCheck(prompt = "请输入name3", checkClass = Name1FormCheck.class)
    public String name3;

    @BindTextView(R.id.nameTv)
    @FormCheck(prompt = "请输入name4")
    public String name4;

}


