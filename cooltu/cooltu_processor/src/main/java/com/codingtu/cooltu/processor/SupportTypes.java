package com.codingtu.cooltu.processor;

import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.annotation.path.Paths;
import com.codingtu.cooltu.processor.deal.ModuleInfoDeal;
import com.codingtu.cooltu.processor.deal.PathDeal;

/**************************************************
 *
 * 此处的顺序决定了读取顺序
 *
 **************************************************/
public class SupportTypes {

    public static Class[] types() {
        return new Class[]{
                ModuleInfo.class, ModuleInfoDeal.class,
                Paths.class, PathDeal.class
//                ResFor.class, ResForDeal.class
        };
    }

}
