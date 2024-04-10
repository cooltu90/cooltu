package com.codingtu.cooltu.processor.annotation.forms.radiogroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface FormRadioGroupItems {
    String[] value() default {};
}
