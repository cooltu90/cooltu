package com.codingtu.cooltu.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.codingtu.cooltu.R;
import com.codingtu.cooltu.form.AddressLink;
import com.codingtu.cooltu.form.Name1FormCheck;
import com.codingtu.cooltu.form.Name1Parse;
import com.codingtu.cooltu.form.TypeOnSetItem;
import com.codingtu.cooltu.form.TypeParse;
import com.codingtu.cooltu.lib4j.data.bean.CoreBean;
import com.codingtu.cooltu.processor.annotation.form.EchoType;
import com.codingtu.cooltu.processor.annotation.form.FormCheck;
import com.codingtu.cooltu.processor.annotation.form.FormEcho;
import com.codingtu.cooltu.processor.annotation.form.FormParse;
import com.codingtu.cooltu.processor.annotation.form.view.BindEditText;
import com.codingtu.cooltu.processor.annotation.form.FormBean;
import com.codingtu.cooltu.processor.annotation.form.view.BindMulti;
import com.codingtu.cooltu.processor.annotation.form.view.BindRadioGroup;
import com.codingtu.cooltu.processor.annotation.form.view.BindSeekBar;
import com.codingtu.cooltu.processor.annotation.form.view.BindTextView;

@FormBean
public class Forms extends CoreBean {

    @BindEditText(value = R.id.nameEt1)
    @FormParse(Name1Parse.class)
    @FormCheck(prompt = "请输入name1")
    public String name1;

    @BindEditText(value = R.id.nameEt2)
    @FormCheck(prompt = "请输入name2")
    @FormEcho(EchoType.NOT_ECHO)
    public String name2;

    @BindEditText(R.id.nameEt3)
    @FormCheck(prompt = "请输入name3", checkClass = Name1FormCheck.class)
    public String name3;

    @BindTextView(R.id.nameTv)
    @FormCheck(prompt = "请输入name4")
    public String name4;

    @BindRadioGroup(value = R.id.radios, onSetItem = TypeOnSetItem.class, strItems = {"r1", "r2", "r3"})
    @FormCheck(prompt = "请选择类型")
    public String type;

    @BindRadioGroup(value = R.id.radios1, onSetItem = TypeOnSetItem.class, strItems = {"r1", "r2", "r3"}, defualtItem = 1)
    @FormCheck(prompt = "请选择类型1")
    public String type1;

    @BindSeekBar(R.id.timeSb)
    @FormParse(TypeParse.class)
    @FormCheck(prompt = "请选择时间")
    public String time;

    @BindSeekBar(R.id.timeSb1)
//    @FormCheck(prompt = "请选择时间")
    public int time1;

    @BindEditText(R.id.provinceEt)
    @FormCheck(prompt = "请输入省份")
    @JSONField(serialize = false)
    public String province;

    @BindEditText(R.id.cityEt)
    @FormCheck(prompt = "请输入城市")
    @JSONField(serialize = false)
    public String city;

    @BindEditText(R.id.areaEt)
    @FormCheck(prompt = "请输入区域")
    @JSONField(serialize = false)
    public String area;

    @BindMulti(ids = {R.id.provinceEt, R.id.cityEt, R.id.areaEt}, link = AddressLink.class)
//    @BindEditText(R.id.addressEt)
    @FormCheck(prompt = "请输入地址")
    public String address;

}


