package com.codingtu.cooltu.processor.annotation.forms.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface FormEditText {
    int value();
}
