package com.codingtu.cooltu.processor.annotation.form.link;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface LinkMethod {

    int value();

}
