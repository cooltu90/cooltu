package com.codingtu.cooltu.processor.annotation.formbind;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Check {
    String prompt();

    int type() default CheckType.NORMAL;
}
