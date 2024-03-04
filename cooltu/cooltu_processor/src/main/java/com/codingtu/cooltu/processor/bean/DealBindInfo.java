package com.codingtu.cooltu.processor.bean;

import com.codingtu.cooltu.lib4j.data.kv.KV;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public class DealBindInfo {
    public TypeElement bindConfigTe;
    public KV<String, String> bindBeanKv;
    public KV<String, String> bindConfigKv;
    public KV<String, String> initKv;
    public KV<String, String> handlerKv;
    public Map<String, ExecutableElement> echoMethodMap;
    public Map<Integer, ExecutableElement> bindMethodMap;
    public Map<String, ExecutableElement> toBeanMethodMap;
    public Map<Integer, ExecutableElement> handleViewMethodMap;
    public StringBuilder bindSb;
    public StringBuilder echoSb;
    public StringBuilder handleSb;
    public Map<String, KV<String, String>> onSetItemMap;
}
