package com.codingtu.cooltu.processor.bean;

import com.codingtu.cooltu.processor.annotation.path.FilePath;

import javax.lang.model.element.ExecutableElement;

public class FilePathInfo {

    public FilePath file;
    //
    public String fileName;
    public String fileFullName;
    public String fileType;
    //
    public String fieldName;
    public String fieldFullName;
    public String fieldType;
    //
    public String fileContentType;
    public boolean isVoidBean;
    public String beanClass;
    public int beanType;
    //
    public boolean isList;
    public ExecutableElement listMethod;
    public String configName;
}
