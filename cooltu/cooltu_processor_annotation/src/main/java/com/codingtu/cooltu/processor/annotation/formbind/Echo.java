package com.codingtu.cooltu.processor.annotation.formbind;

import com.codingtu.cooltu.processor.annotation.form.EchoType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Echo {
    int value();
}
