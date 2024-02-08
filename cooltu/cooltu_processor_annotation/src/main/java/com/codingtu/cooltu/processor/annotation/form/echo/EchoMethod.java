package com.codingtu.cooltu.processor.annotation.form.echo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface EchoMethod {

    int[] value();

}
