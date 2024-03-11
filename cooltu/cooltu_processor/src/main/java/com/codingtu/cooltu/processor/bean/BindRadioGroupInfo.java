package com.codingtu.cooltu.processor.bean;

import com.codingtu.cooltu.lib4j.data.kv.KV;

public class BindRadioGroupInfo {
    public int id;
    public String onSetItem;
    public String onSetItemName;
    public String[] items;
    public boolean defulatItems;
    public int selected;
    public int itemId;
    public KV<String, String> rgKv;
}
