package com.codingtu.cooltu.processor.deal;

import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.processor.annotation.path.Paths;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.builder.impl.PathBuilder;
import com.codingtu.cooltu.processor.deal.base.TypeBaseDeal;

import javax.lang.model.element.TypeElement;

@To(PathBuilder.class)
public class PathDeal extends TypeBaseDeal {

    @Override
    protected void dealTypeElement(TypeElement te) {
        Paths paths = te.getAnnotation(Paths.class);
        String name = paths.name();
        String path = paths.path();
        PathBuilder pathBuilder = new PathBuilder(ConvertTool.toClassType(name));
        pathBuilder.setPath(path);
    }
}
