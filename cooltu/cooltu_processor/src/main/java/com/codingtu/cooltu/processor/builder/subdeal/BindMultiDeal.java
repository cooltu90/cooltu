package com.codingtu.cooltu.processor.builder.subdeal;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Suffix;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.ts.Maps;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.annotation.form.view.BindMulti;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;

import java.util.Map;

import javax.lang.model.element.VariableElement;

public class BindMultiDeal {
    public static class ViewIndex {
        public int typeIndex;
        public int handleIndex;

        public ViewIndex(int typeIndex, int handleIndex) {
            this.typeIndex = typeIndex;
            this.handleIndex = handleIndex;
        }
    }


    public static void deal(ActBaseBuilder builder, String beanName,
                            Map<Integer, Integer> indexMap, Map<Integer, Integer> typeIndexMap,
                            Map<String, LayoutTools.ViewInfo> viewMap, Map<Integer, BindMultiDeal.ViewIndex> viewIndexMap,
                            VariableElement ve, BindMulti bindMulti) {
        Map<Integer, IdTools.Id> idMap = IdTools.elementToIds(ve, BindMulti.class, bindMulti.ids());
        String linkClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return bindMulti.link();
            }
        });

        KV<String, String> fieldKv = ElementTools.getFieldKv(ve);

        String param = Params.getParam(Ts.ints(bindMulti.ids()), new Ts.Convert<Integer, String>() {
            @Override
            public String convert(int index, Integer rid) {
                IdTools.Id id = idMap.get(rid);
                LayoutTools.ViewInfo viewInfo = viewMap.get(id.rName);
                if (viewInfo == null) {
                    return null;
                }
                String fieldName = viewInfo.fieldName;
                Logs.i("fieldName:" + fieldName);
                return fieldName;
            }
        });

        int bindMultiCount = builder.bindMultiCount();
        String linkName = fieldKv.v + Suffix.LINK;
        builder.bindMulti(bindMultiCount, FullName.FORM_LINK, fieldKv.v + Suffix.LINK, linkClass, beanName, param);
        builder.linkEcho(builder.linkEchoCount(), linkName);

        Maps.map(idMap).ls(new Ts.MapEach<Integer, IdTools.Id>() {
            @Override
            public boolean each(Integer viewId, IdTools.Id id) {
                builder.addLink(bindMultiCount, builder.addLinkCount(bindMultiCount), id.toString(), linkName);
                ViewIndex viewIndex = viewIndexMap.get(viewId);
                if (viewIndex != null)
                    builder.handlerItemLinkIf(viewIndex.typeIndex, viewIndex.handleIndex, id.toString());
                return false;
            }
        });

    }
}
