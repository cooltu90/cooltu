package com.codingtu.cooltu.processor.annotation.ui;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface DefaultNoticeDialogLayout {
    int value();
}