package com.codingtu.cooltu.processor.annotation.forms.check;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface CheckField {
    String prompt() default "";

    String methodName() default "";

    int[] ids() default {};
}
