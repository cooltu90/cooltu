package com.codingtu.cooltu.processor.bean;

import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindEt;
import com.codingtu.cooltu.processor.annotation.bind.echo.NoEcho;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import java.lang.annotation.Annotation;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

public class DealBindVeInfo {
    public VariableElement ve;
    public IdTools.Id id;
    public String viewFieldName;
    public Class<? extends Annotation> annoClass;
    public int annoValue;
    public KV<String, String> fieldOriKv;
    public KV<String, String> fieldKv;
    public ExecutableElement echoMethodEe;
    public NoEcho noEcho;
}
