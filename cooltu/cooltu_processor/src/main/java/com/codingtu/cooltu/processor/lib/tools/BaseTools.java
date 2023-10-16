package com.codingtu.cooltu.processor.lib.tools;

import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.bean.ActBaseInfo;
import com.codingtu.cooltu.processor.builder.impl.ActBaseBuilder;
import com.codingtu.cooltu.processor.deal.ActBaseDeal;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;

import java.util.List;

/**************************************************
 *
 *   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
 *  ┃   获取全部父类ActBaseInfo（包括自己）   ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #getActBaseInfoWithParents(ActBaseInfo, BaseTs.EachTs)} }
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
    public static void getActBaseInfoWithParents(ActBaseInfo info, BaseTs.EachTs<ActBaseInfo> eachTs) {
        getActBaseInfoWithParents(info, new int[]{0}, eachTs);
    }

    private static void getActBaseInfoWithParents(ActBaseInfo info, int[] indexs, BaseTs.EachTs<ActBaseInfo> eachTs) {
        if (info != null) {
            eachTs.each(indexs[0]++, info);
            if (info.hasBaseClass()) {
                ActBaseBuilder builder = CurrentPath.actBaseBuilder(info.baseClass);
                if (builder != null) {
                    getActBaseInfoWithParents(builder.getActBaseInfo(), indexs, eachTs);
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
            ActBaseInfo actBaseInfo = builder.getActBaseInfo();
            if (actBaseInfo != null && actBaseInfo.hasBaseClass()) {
                getActBaseBuilderWithParents(actBaseInfo.baseClass, indexs, eachTs);
            }
        }
    }

    /**************************************************
     *
     * 获取全部子类ActBaseBuilder（包括自己）
     *
     **************************************************/

    public static void getActBaseBuilderWithChilds(String parent, BaseTs.EachTs<ActBaseBuilder> eachTs) {
        getActBaseBuilderWithChilds(parent, new int[]{0}, eachTs);
    }

    private static void getActBaseBuilderWithChilds(String parent, int[] indexs, BaseTs.EachTs<ActBaseBuilder> eachTs) {
        ActBaseBuilder builder = CurrentPath.actBaseBuilder(parent);
        if (builder != null) {
            eachTs.each(indexs[0]++, builder);
            List<String> childs = ActBaseDeal.map.get(parent);
            int count = CountTool.count(childs);
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    String child = childs.get(i);
                    getActBaseBuilderWithChilds(child, indexs, eachTs);
                }
            }
        }
    }


}
