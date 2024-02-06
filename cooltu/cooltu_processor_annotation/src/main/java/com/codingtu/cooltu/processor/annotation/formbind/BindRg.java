package com.codingtu.cooltu.processor.annotation.formbind;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface BindRg {
    int id();

    Class onSetItem();

    int selected() default -1;

}
