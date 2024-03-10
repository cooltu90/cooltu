package com.codingtu.cooltu.processor.bean;

import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.json.base.JO;

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
    public Map<String, ExecutableElement> echoMethodMap = new HashMap<>();
    public Map<Integer, ExecutableElement> bindMethodMap = new HashMap<>();
    public Map<String, ExecutableElement> toBeanMethodMap = new HashMap<>();
    public Map<Integer, ExecutableElement> handleViewMethodMap = new HashMap<>();
    public Map<Integer, ExecutableElement> radioGroupViewsMethodMap = new HashMap<>();
    public Map<Integer, ExecutableElement> radioGroupItemsMethodMap = new HashMap<>();
    public StringBuilder bindSb;
    public StringBuilder echoSb;
    public StringBuilder handleSb;
    public StringBuilder checkSb;
    public Map<String, KV<String, String>> onSetItemMap = new HashMap<>();
    public ListValueMap<String, ExecutableElement> checkMethodMap1 = new ListValueMap<>();
    public ListValueMap<String, String> checkMethodMap2 = new ListValueMap<>();
    public StringBuilder setSelectedSb;
}
