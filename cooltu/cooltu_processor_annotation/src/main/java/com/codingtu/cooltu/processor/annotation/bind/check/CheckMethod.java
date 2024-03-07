package com.codingtu.cooltu.processor.annotation.bind.check;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface CheckMethod {
    String[] fields();

    String[] prompts() default {};
}
