package com.codingtu.cooltu.form.config;

import com.codingtu.cooltu.bean.Doc;
import com.codingtu.cooltu.processor.annotation.form.FormConfig;

import core.form.DocFormConfigBase;

@FormConfig(Doc.class)
public class DocFormConfig extends DocFormConfigBase {

    public String name;
}
