package com.codingtu.cooltu.processor.annotation.dm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Cache {
    String tag() default "";

    String key() default "";

}
