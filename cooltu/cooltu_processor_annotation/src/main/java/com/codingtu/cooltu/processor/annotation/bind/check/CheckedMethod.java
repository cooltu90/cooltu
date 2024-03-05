package com.codingtu.cooltu.processor.annotation.bind.check;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface CheckedMethod {
    String[] value();
}
