package com.codingtu.cooltu.processor.annotation.path;

import com.codingtu.cooltu.constant.FileContentType;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.constant.PathBeanType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface FilePath {
    String parent() default "root";

    String fileName() default "";

    String fileType() default FileType.NONE;

    String fileContentType() default FileContentType.NONE;

    String fieldName() default "";

    Class beanClass() default Void.class;

    int beanType() default PathBeanType.BEAN;

    boolean list() default false;
}
