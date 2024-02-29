package com.codingtu.cooltu.processor.annotation.form.parse;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface ToBeanMethod {

    int value();

}
