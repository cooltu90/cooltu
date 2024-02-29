package com.codingtu.cooltu.processor.annotation.form1;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface FormConfig {
    //指定bean字段的名字
    String name() default "";

    Class bean() default Void.class;
}
