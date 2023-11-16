package com.codingtu.cooltu.processor.annotation.form.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface BindMulti {
    int[] ids();

    Class link();
}
