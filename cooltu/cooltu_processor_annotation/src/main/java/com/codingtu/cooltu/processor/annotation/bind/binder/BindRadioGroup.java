package com.codingtu.cooltu.processor.annotation.bind.binder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface BindRadioGroup {
    int id();

    Class onSetItem();

    String onSetItemName() default "";

    String[] items() default {};

    int selected() default -1;
}
