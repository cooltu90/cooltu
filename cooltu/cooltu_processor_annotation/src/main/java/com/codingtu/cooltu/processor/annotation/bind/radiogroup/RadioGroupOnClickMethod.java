package com.codingtu.cooltu.processor.annotation.bind.radiogroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface RadioGroupOnClickMethod {
    int[] value();
}
