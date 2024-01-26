package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.bean.PathFilterInfo;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class PathFilterDeal extends TypeBaseDeal {

    public static Map<String, PathFilterInfo> map = new HashMap<>();

    @Override
    protected void dealTypeElement(TypeElement te) {
        String type = ElementTools.getType(te);
        PathFilterInfo info = new PathFilterInfo();
        info.params = Ts.ts(te.getEnclosedElements()).convert((index, e) -> {
            if (e instanceof VariableElement) {
                return ElementTools.getFieldKv((VariableElement) e);
            }
            return null;
        }).toList();
        map.put(type, info);
    }
}
