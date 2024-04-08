package com.codingtu.cooltu.processor.annotation.forms;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface UseForm {
    Class[] value();
}
