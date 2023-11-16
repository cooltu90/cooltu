package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.processor.annotation.ui.VH;
import com.codingtu.cooltu.processor.builder.impl.VhBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import javax.lang.model.element.TypeElement;

public class VHDeal extends TypeBaseDeal {
    @Override
    protected void dealTypeElement(TypeElement te) {
        VH vh = te.getAnnotation(VH.class);
        IdTools.Id id = IdTools.elementToId(te, VH.class, vh.layout());
        String vhClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return vh.vh();
            }
        });
        JavaInfo vhJavaInfo = CurrentPath.javaInfo(vhClass);

        new VhBuilder(vhJavaInfo,id);
    }
}
