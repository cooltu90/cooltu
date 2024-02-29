package com.codingtu.cooltu.processor.annotation.form1.bind;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface BindEt {
    int value();
}
