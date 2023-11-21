package com.codingtu.cooltu.processor.bean;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.form.Form;
import com.codingtu.cooltu.processor.annotation.ui.ActBack;
import com.codingtu.cooltu.processor.annotation.ui.Permission;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

public class ActBaseInfo {
    public List<KV<String, String>> starts = new ArrayList<>();
    public List<ActBack> actBacks = new ArrayList<>();
    public List<ExecutableElement> actBackMethods = new ArrayList<>();
    public List<Permission> permissions = new ArrayList<>();
    public List<ExecutableElement> permissionMethods = new ArrayList<>();
    public Form form;
}
