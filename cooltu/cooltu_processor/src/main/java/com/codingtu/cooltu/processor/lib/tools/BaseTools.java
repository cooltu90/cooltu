package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.bean.ActBaseInfo;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.deal.ActBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.util.ArrayList;
import java.util.List;

/**************************************************
 *
 *   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
 *  ┃   获取全部父类ActBaseInfo（包括自己）   ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #getActBaseInfoWithParents(ActBaseInfo, UiBaseBuilder, BaseTs.EachTs)}
 *
 *   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
 *  ┃   获取全部父类ActBaseBuilder（包括自己）   ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #getActBaseBuilderWithParents(String, BaseTs.EachTs)}
 *
 *   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
 *  ┃   获取全部子类ActBaseBuilder（包括自己）   ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #getActBaseBuilderWithChilds(String, BaseTs.EachTs)}
 *
 *
 **************************************************/
public class BaseTools {


    /**************************************************
     *
     * 获取全部父类ActBaseInfo（包括自己）
     *
     **************************************************/
    public static void getActBaseInfoWithParents(UiBaseBuilder uiBaseBuilder, BaseTs.EachTs<UiBaseBuilder> eachTs) {
        getActBaseInfoWithParents(uiBaseBuilder, new int[]{0}, eachTs);
    }

    private static void getActBaseInfoWithParents(UiBaseBuilder uiBaseBuilder, int[] indexs, BaseTs.EachTs<UiBaseBuilder> eachTs) {
        if (uiBaseBuilder != null) {
            eachTs.each(indexs[0]++, uiBaseBuilder);
            if (uiBaseBuilder.hasBaseClass()) {
                ActBaseBuilder builder = CurrentPath.actBaseBuilder(uiBaseBuilder.baseClass);
                if (builder != null) {
                    getActBaseInfoWithParents(builder.getUiBaseBuilder(), indexs, eachTs);
                }
            }
        }
    }

    /**************************************************
     *
     * 获取全部父类ActBaseBuilder（包括自己）
     *
     **************************************************/
    public static void getActBaseBuilderWithParents(String child, BaseTs.EachTs<ActBaseBuilder> eachTs) {
        getActBaseBuilderWithParents(child, new int[]{0}, eachTs);
    }

    private static void getActBaseBuilderWithParents(String child, int[] indexs, BaseTs.EachTs<ActBaseBuilder> eachTs) {
        ActBaseBuilder builder = CurrentPath.actBaseBuilder(child);
        if (builder != null) {
            eachTs.each(indexs[0]++, builder);
            UiBaseBuilder uiBaseBuilder = builder.getUiBaseBuilder();
            if (uiBaseBuilder != null && uiBaseBuilder.hasBaseClass()) {
                getActBaseBuilderWithParents(uiBaseBuilder.baseClass, indexs, eachTs);
            }
        }
    }

    /**************************************************
     *
     * 获取全部子类ActBaseBuilder（包括自己）
     *
     **************************************************/
    public static List<ActBaseBuilder> getActBaseBuilderWithChilds(String parent) {
        return getBaseBuilderWithChilds(parent, getActBaseBuilder());
    }


    public static void getActBaseBuilderWithChilds(String parent, BaseTs.EachTs<ActBaseBuilder> eachTs) {
        getBaseBuilderWithChilds(parent, new int[0], eachTs, getActBaseBuilder());
    }

    private static GetBaseBuilder<ActBaseBuilder> getActBaseBuilder() {
        return new GetBaseBuilder<ActBaseBuilder>() {
            @Override
            public ActBaseBuilder getBaseBuilder(String parent) {
                return CurrentPath.actBaseBuilder(parent);
            }
        };
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public static <B> List<B> getBaseBuilderWithChilds(String parent, GetBaseBuilder<B> getBaseBuilder) {
        ArrayList<B> list = new ArrayList<>();
        getBaseBuilderWithChilds(parent, new BaseTs.EachTs<B>() {
            @Override
            public boolean each(int position, B baseBuilder) {
                list.add(baseBuilder);
                return false;
            }
        }, getBaseBuilder);
        return list;
    }


    public static <B> void getBaseBuilderWithChilds(String parent, BaseTs.EachTs<B> eachTs, GetBaseBuilder<B> getBaseBuilder) {
        getBaseBuilderWithChilds(parent, new int[]{0}, eachTs, getBaseBuilder);
    }

    private static <B> void getBaseBuilderWithChilds(String parent, int[] indexs, BaseTs.EachTs<B> eachTs, GetBaseBuilder<B> getBaseBuilder) {
        B builder = getBaseBuilder.getBaseBuilder(parent);
        if (builder != null) {
            eachTs.each(indexs[0]++, builder);
            List<String> childs = ActBaseDeal.map.get(parent);
            int count = CountTool.count(childs);
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    String child = childs.get(i);
                    getBaseBuilderWithChilds(child, indexs, eachTs, getBaseBuilder);
                }
            }
        }
    }

    public static interface GetBaseBuilder<B> {
        public B getBaseBuilder(String parent);
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    public static <T> List<T> getThisWithChilds(String thisClass, GetThis<T> getThis) {
        ArrayList<T> list = new ArrayList<>();
        getThisWithChilds(thisClass, new BaseTs.EachTs<T>() {
            @Override
            public boolean each(int position, T t) {
                list.add(t);
                return false;
            }
        }, getThis);
        return list;
    }


    public static <T> void getThisWithChilds(String thisClass, BaseTs.EachTs<T> eachTs, GetThis<T> getThis) {
        getThisWithChilds(thisClass, new int[]{0}, eachTs, getThis);
    }


    private static <T> void getThisWithChilds(String thisClass, int[] indexs, BaseTs.EachTs<T> eachTs, GetThis<T> getThis) {
        T builder = getThis.getThis(thisClass);
        if (builder != null) {
            eachTs.each(indexs[0]++, builder);
            List<String> childs = getThis.getChilds(thisClass);
            int count = CountTool.count(childs);
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    String child = childs.get(i);
                    getThisWithChilds(child, indexs, eachTs, getThis);
                }
            }
        }
    }

    public static interface GetThis<T> {
        public T getThis(String thisClass);

        public List<String> getChilds(String thisClass);
    }

}
