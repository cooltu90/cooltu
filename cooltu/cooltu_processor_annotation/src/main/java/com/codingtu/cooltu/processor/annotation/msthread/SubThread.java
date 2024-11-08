package com.codingtu.cooltu.processor.annotation.msthread;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface SubThread {
    boolean isStart() default false;

}
