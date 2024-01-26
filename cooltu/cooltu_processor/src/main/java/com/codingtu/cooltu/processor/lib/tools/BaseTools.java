package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.builder.impl.FragmentBaseBuilder;
import com.codingtu.cooltu.processor.deal.ActBaseDeal;
import com.codingtu.cooltu.processor.deal.FragmentBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.util.ArrayList;
import java.util.List;

public class BaseTools {


    /**************************************************
     *
     * 获取全部父类（包括自己）
     *
     **************************************************/

    public static BaseTools.GetParent<UiBaseBuilder> getActBaseParentGetter() {
        return new BaseTools.GetParent<UiBaseBuilder>() {
            @Override
            public UiBaseBuilder getParent(UiBaseBuilder uiBaseBuilder) {
                ActBaseBuilder builder = CurrentPath.actBaseBuilder(uiBaseBuilder.baseClass);
                if (builder == null) {
                    return null;
                }
                return builder.getUiBaseBuilder();
            }
        };
    }

    public static BaseTools.GetParent<UiBaseBuilder> getFragBaseParentGetter() {
        return new BaseTools.GetParent<UiBaseBuilder>() {
            @Override
            public UiBaseBuilder getParent(UiBaseBuilder uiBaseBuilder) {
                FragmentBaseBuilder builder = CurrentPath.fragBaseBuilder(uiBaseBuilder.baseClass);
                if (builder == null) {
                    return null;
                }
                return builder.getUiBaseBuilder();
            }
        };
    }

    public static <T> List<T> getThisWithParents(T t, GetParent<T> getParent) {
        ArrayList<T> ts = new ArrayList<>();
        getThisWithParents(t, getParent, new Ts.EachTs<T>() {
            @Override
            public boolean each(int position, T t) {
                ts.add(t);
                return false;
            }
        });
        return ts;
    }

    public static <T> void getThisWithParents(T t, GetParent<T> getParent, Ts.EachTs<T> eachTs) {
        getThisWithParents(t, new int[]{0}, getParent, eachTs);
    }

    private static <T> void getThisWithParents(T t, int[] indexs, GetParent<T> getParent, Ts.EachTs<T> eachTs) {
        if (t != null) {
            eachTs.each(indexs[0]++, t);
            T parent = getParent.getParent(t);
            if (parent != null) {
                getThisWithParents(parent, indexs, getParent, eachTs);
            }
        }
    }

    public static interface GetParent<T> {
        T getParent(T t);
    }

    /**************************************************
     *
     * 获取全部子类（包括自己）
     *
     **************************************************/
    public static BaseTools.GetThis<UiBaseBuilder> getActBaseChildGetter() {
        return new BaseTools.GetThis<UiBaseBuilder>() {
            @Override
            public UiBaseBuilder getThis(String thisClass) {
                return CurrentPath.actBaseBuilder(thisClass).getUiBaseBuilder();
            }

            @Override
            public List<String> getChilds(String thisClass) {
                return ActBaseDeal.map.get(thisClass);
            }
        };
    }

    public static BaseTools.GetThis<UiBaseBuilder> getFragBaseChildGetter() {
        return new BaseTools.GetThis<UiBaseBuilder>() {
            @Override
            public UiBaseBuilder getThis(String thisClass) {
                return CurrentPath.fragBaseBuilder(thisClass).getUiBaseBuilder();
            }

            @Override
            public List<String> getChilds(String thisClass) {
                return FragmentBaseDeal.map.get(thisClass);
            }
        };
    }


    public static <T> List<T> getThisWithChilds(String thisClass, GetThis<T> getThis) {
        ArrayList<T> list = new ArrayList<>();
        getThisWithChilds(thisClass, new Ts.EachTs<T>() {
            @Override
            public boolean each(int position, T t) {
                list.add(t);
                return false;
            }
        }, getThis);
        return list;
    }


    public static <T> void getThisWithChilds(String thisClass, Ts.EachTs<T> eachTs, GetThis<T> getThis) {
        getThisWithChilds(thisClass, new int[]{0}, eachTs, getThis);
    }


    private static <T> void getThisWithChilds(String thisClass, int[] indexs, Ts.EachTs<T> eachTs, GetThis<T> getThis) {
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
