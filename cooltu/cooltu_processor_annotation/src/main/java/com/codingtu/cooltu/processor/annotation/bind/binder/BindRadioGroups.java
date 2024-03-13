package com.codingtu.cooltu.processor.annotation.bind.binder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface BindRadioGroups {
    int id() default -1;

    Class onSetItem() default Void.class;

    String onSetItemName() default "";

    String[] items() default {};

    boolean defulatItems() default false;

    int selected() default -1;

    int itemId() default -1;

    BindRadioGroup[] bindRadioGroups() default {};

}
