package com.codingtu.cooltu.processor.annotation.form.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface BindSeekBar {
    int value() default -1;

    String name() default "";

    boolean echo() default true;

}
