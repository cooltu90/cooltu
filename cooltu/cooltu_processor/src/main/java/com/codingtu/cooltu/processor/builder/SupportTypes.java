package com.codingtu.cooltu.processor.builder;

import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.annotation.create.CreateAct;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.builder.deal.ModuleInfoDeal;
import com.codingtu.cooltu.processor.builder.deal.ResForDeal;

/**************************************************
 *
 * 此处的顺序决定了读取顺序
 *
 **************************************************/
public class SupportTypes {

    public static Class[] types() {
        return new Class[]{
                ModuleInfo.class, ModuleInfoDeal.class,
                ResFor.class, ResForDeal.class
        };
    }

}
