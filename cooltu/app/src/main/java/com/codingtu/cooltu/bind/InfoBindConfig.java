package com.codingtu.cooltu.bind;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.Info;
import com.codingtu.cooltu.processor.annotation.bind.BindConfig;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindEt;

@BindConfig(Info.class)
public class InfoBindConfig {

    @BindEt(R.id.nameEt)
    public String name;

    @BindEt(R.id.provinceEt)
    public String province;

    @BindEt(R.id.cityEt)
    public String city;

    @BindEt(R.id.areaEt)
    public String area;


}
