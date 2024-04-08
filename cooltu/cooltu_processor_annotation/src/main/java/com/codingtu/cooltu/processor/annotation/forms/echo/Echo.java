package com.codingtu.cooltu.processor.annotation.forms.echo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Echo {
    String methodName();

    int[] ids();
}
