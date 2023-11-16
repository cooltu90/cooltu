package com.codingtu.cooltu.processor.builder.subdeal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ts.impl.MapTs;
import com.codingtu.cooltu.lib4j.ts.impl.TsInterface;
import com.codingtu.cooltu.lib4j.ts.impl.basic.TListTs;
import com.codingtu.cooltu.processor.annotation.form.view.BindMulti;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.VariableElement;

public class BindMultiDeal {
    public static void deal(ActBaseBuilder builder, String beanName,
                            Map<Integer, Integer> indexMap, Map<Integer, Integer> typeIndexMap,
                            Map<Integer, String> viewMap,
                            VariableElement ve, BindMulti bindMulti) {
        Map<Integer, IdTools.Id> idMap = IdTools.elementToIds(ve, BindMulti.class, bindMulti.ids());
        String linkClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return bindMulti.link();
            }
        });

        KV<String, String> fieldKv = ElementTools.getFieldKv(ve);


        String param = Params.getParam(Ts.ts(bindMulti.ids()), new BaseTs.Convert<Integer, String>() {
            @Override
            public String convert(int index, Integer id) {
                return viewMap.get(id);
            }
        });

        int bindMultiCount = builder.bindMultiCount();
        String linkName = fieldKv.v + Suffix.LINK;
        builder.bindMulti(bindMultiCount, FullName.FORM_LINK, fieldKv.v + Suffix.LINK, linkClass, beanName, param);

        Ts.ts(idMap).ls(new MapTs.MapEach<Integer, IdTools.Id>() {
            @Override
            public boolean each(Integer integer, IdTools.Id id) {
                builder.addLink(bindMultiCount, builder.addLinkCount(bindMultiCount), id.toString(), linkName);
                return false;
            }
        });
    }
}
