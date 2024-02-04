package com.codingtu.cooltu.bean;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.formbind.CountParse;
import com.codingtu.cooltu.formbind.NameCheck;
import com.codingtu.cooltu.formbind.NameParse;
import com.codingtu.cooltu.formbind.advice.EditTextAdvice;
import com.codingtu.cooltu.formbind.binder.AddressBinder;
import com.codingtu.cooltu.formbind.binder.DayBinder;
import com.codingtu.cooltu.formbind.binder.MonthBinder;
import com.codingtu.cooltu.formbind.binder.YearBinder;
import com.codingtu.cooltu.lib4j.data.bean.CoreBean;
import com.codingtu.cooltu.processor.annotation.form.EchoType;
import com.codingtu.cooltu.processor.annotation.formbind.Advice;
import com.codingtu.cooltu.processor.annotation.formbind.BindETs;
import com.codingtu.cooltu.processor.annotation.formbind.Binder;
import com.codingtu.cooltu.processor.annotation.formbind.Check;
import com.codingtu.cooltu.processor.annotation.formbind.Echo;
import com.codingtu.cooltu.processor.annotation.formbind.FormObject;
import com.codingtu.cooltu.processor.annotation.formbind.Parse;

@FormObject("forms")
public class FormsObj extends CoreBean {

    @BindETs(R.id.nameEt)
    @Echo(EchoType.NORMAL)
    @Check(prompt = "请输入姓名", checkClass = NameCheck.class)
    @Parse(NameParse.class)
    public String name;

    @BindETs(R.id.provinceEt)
    public String province;

    @BindETs({R.id.cityEt, R.id.provinceEt, R.id.areaEt})
    @Binder(AddressBinder.class)
    public String address;

    @BindETs(R.id.ymdEt)
    @Binder(YearBinder.class)
    public int year;

    @BindETs(R.id.ymdEt)
    @Binder(MonthBinder.class)
    public int month;

    @BindETs(R.id.ymdEt)
    @Binder(DayBinder.class)
    public int day;

    @BindETs(R.id.countEt)
    @Advice(EditTextAdvice.class)
    @Parse(CountParse.class)
    public int count;


}
