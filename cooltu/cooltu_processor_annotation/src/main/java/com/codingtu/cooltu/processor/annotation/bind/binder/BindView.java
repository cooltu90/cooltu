package com.codingtu.cooltu.processor.annotation.bind.binder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface BindView {
    int value();
}
