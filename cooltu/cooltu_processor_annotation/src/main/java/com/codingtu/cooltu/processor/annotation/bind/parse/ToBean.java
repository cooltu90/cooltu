package com.codingtu.cooltu.processor.annotation.bind.parse;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface ToBean {

    String value();

}
