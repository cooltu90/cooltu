package com.codingtu.cooltu.bean;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4j.data.bean.CoreBean;
import com.codingtu.cooltu.processor.annotation.form.view.BindEdieText;
import com.codingtu.cooltu.processor.annotation.form.FormBean;
import com.codingtu.cooltu.processor.annotation.form.view.BindTextView;

@FormBean("formmmm")
public class Forms extends CoreBean {

    @BindEdieText(R.id.nameEt1)
    public String name1;
    @BindEdieText(R.id.nameEt2)
    public String name2;
    @BindEdieText(R.id.nameEt3)
    public String name3;
    @BindTextView(R.id.nameTv)
    public String name4;

}


