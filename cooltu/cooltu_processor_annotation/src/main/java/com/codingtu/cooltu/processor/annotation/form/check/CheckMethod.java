package com.codingtu.cooltu.processor.annotation.form.check;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface CheckMethod {
    int[] value();
}
