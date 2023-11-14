package com.codingtu.cooltu.processor.annotation.form;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface FormCheck {
    Class checkClass() default Void.class;

    String prompt();
}
