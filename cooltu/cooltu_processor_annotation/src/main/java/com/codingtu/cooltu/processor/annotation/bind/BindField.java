package com.codingtu.cooltu.processor.annotation.bind;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface BindField {
    String value() default "";
}