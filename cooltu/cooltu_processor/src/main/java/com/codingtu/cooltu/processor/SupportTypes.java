package com.codingtu.cooltu.processor;

import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.annotation.create.CreateAct;
import com.codingtu.cooltu.processor.annotation.create.CreateAdapter;
import com.codingtu.cooltu.processor.annotation.form.FormBean;
import com.codingtu.cooltu.processor.annotation.net.Apis;
import com.codingtu.cooltu.processor.annotation.path.PathFilter;
import com.codingtu.cooltu.processor.annotation.path.Paths;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.deal.ActBaseDeal;
import com.codingtu.cooltu.processor.deal.CreateActDeal;
import com.codingtu.cooltu.processor.deal.CreateAdapterDeal;
import com.codingtu.cooltu.processor.deal.FormBeanDeal;
import com.codingtu.cooltu.processor.deal.ModuleInfoDeal;
import com.codingtu.cooltu.processor.deal.NetDeal;
import com.codingtu.cooltu.processor.deal.PathDeal;
import com.codingtu.cooltu.processor.deal.PathFilterDeal;
import com.codingtu.cooltu.processor.deal.ResForDeal;

/**************************************************
 *
 * 此处的顺序决定了读取顺序
 *
 **************************************************/
public class SupportTypes {

    public static Class[] types() {
        return new Class[]{
                ModuleInfo.class, ModuleInfoDeal.class,
                ActBase.class, ActBaseDeal.class,
                PathFilter.class, PathFilterDeal.class,
                Paths.class, PathDeal.class,
                ResFor.class, ResForDeal.class,
                Apis.class, NetDeal.class,
                CreateAct.class, CreateActDeal.class,
                FormBean.class, FormBeanDeal.class,
                CreateAdapter.class, CreateAdapterDeal.class,
        };
    }

}
