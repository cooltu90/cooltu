package com.codingtu.cooltu.processor.builder;

import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.deal.ModuleInfoDeal;

/**************************************************
 *
 * 此处的顺序决定了读取顺序
 *
 **************************************************/
public class SupportTypes {

    public static Class[] types() {
        return new Class[]{
                ModuleInfo.class, ModuleInfoDeal.class,
//                Paths.class, PathBuilder.class,
//                ResFor.class, ResForDeal.class
        };
    }

}
