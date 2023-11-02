package com.codingtu.cooltu.bean;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.processor.annotation.form.view.BindEdieText;
import com.codingtu.cooltu.processor.annotation.form.FormBean;

@FormBean("formmmm")
public class Forms {

    @BindEdieText(R.id.nameEt1)
    public String name1;

}


