package com.codingtu.cooltu.bean;

import com.codingtu.cooltu.processor.annotation.bean.ConvertItem;
import com.codingtu.cooltu.processor.annotation.bean.ConvertTo;

@ConvertTo(Bean2.class)
public class Bean1 {

    @ConvertItem("name2")
    public String name1;
    public String age;

}
