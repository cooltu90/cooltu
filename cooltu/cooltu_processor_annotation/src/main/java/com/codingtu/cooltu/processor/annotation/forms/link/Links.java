package com.codingtu.cooltu.processor.annotation.forms.link;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Links {
    Link[] value();
}
