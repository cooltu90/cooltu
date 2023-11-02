package com.codingtu.cooltu.processor.annotation.form;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface FormBean {
    //指定bean字段的名字
    String value() default "";
}
