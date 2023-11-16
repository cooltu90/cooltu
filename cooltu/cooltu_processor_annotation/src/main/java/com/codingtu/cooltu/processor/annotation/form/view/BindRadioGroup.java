package com.codingtu.cooltu.processor.annotation.form.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface BindRadioGroup {
    int value() default -1;

    String name() default "";

    boolean echo() default true;

    Class onSetItem();

    String onSetItemName() default "";

    String[] strItems() default {};

    int defualtItem() default -1;

}
