package com.codingtu.cooltu.processor.bean;

import com.codingtu.cooltu.lib4j.data.kv.KV;

import javax.lang.model.element.TypeElement;

public class DealFormInfo {
    public TypeElement formConfigTe;
    public String dataClassFullName;
    public KV<String, String> formConfigKv;
    public KV<String, String> formBeanKv;
    public KV<String, String> formHandlerKv;
}
