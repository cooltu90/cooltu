package com.codingtu.cooltu.processor.bean;

import javax.lang.model.element.ExecutableElement;

public class DirPathInfo {
    public String javaName;
    public String fieldName;
    public String dirName;
    public String configName;
    public boolean isList;
    public ExecutableElement listMethod;
}
