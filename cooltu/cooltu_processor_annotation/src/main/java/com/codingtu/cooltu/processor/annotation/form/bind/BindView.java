package com.codingtu.cooltu.processor.annotation.form.bind;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface BindView {
    int id();

    Class push();
}
