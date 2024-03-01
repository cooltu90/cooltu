package com.codingtu.cooltu.processor.annotation.bind;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface BindConfig {
    String configName() default "";

    Class value() default Void.class;

    String bindBeanName() default "";
}