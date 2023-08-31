package com.codingtu.cooltu.processor.lib;

import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.builder.BuilderType;
import com.codingtu.cooltu.processor.builder.builder.base.BaseBuilder;

import java.util.List;
import java.util.Map;

public class BuilderMap {
    public static final Map<Integer, List<BaseBuilder>> MAP = new ListValueMap<>();

    /**************************************************
     *
     * 添加方法
     *
     **************************************************/

    public static void add(BaseBuilder builder) {
        add(BuilderType.DEFAULT, builder);
    }

    public static void add(BuilderType type, BaseBuilder builder) {
        MAP.get(type.ordinal()).add(builder);
    }

    /**************************************************
     *
     * 查找
     *
     **************************************************/
    public static <T> T find(BuilderType type, String id) {
        return (T) Ts.ts(MAP.get(type.ordinal())).symbol().get(id);
    }

    /**************************************************
     *
     * 创建
     *
     **************************************************/
    public static void create() {
        Ts.ls(BuilderType.values(), new BaseTs.EachTs<BuilderType>() {
            @Override
            public boolean each(int position, BuilderType modelType) {
                create(modelType.ordinal());
                return false;
            }
        });
        MAP.clear();
    }

    private static void create(int type) {
        List<BaseBuilder> models = MAP.get(type);
        Ts.ls(models, new BaseTs.EachTs<BaseBuilder>() {
            @Override
            public boolean each(int position, BaseBuilder model) {
                model.create();
                return false;
            }
        });
    }
}
