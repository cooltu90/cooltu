package com.codingtu.cooltu.bind;

import android.os.Handler;
import android.widget.EditText;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.Info;
import com.codingtu.cooltu.lib4a.bind.BindTool;
import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.processor.annotation.bind.BindConfig;
import com.codingtu.cooltu.processor.annotation.bind.BindField;
import com.codingtu.cooltu.processor.annotation.bind.BindMethod;
import com.codingtu.cooltu.processor.annotation.bind.ViewId;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindEt;
import com.codingtu.cooltu.processor.annotation.bind.binder.ViewBinder;
import com.codingtu.cooltu.processor.annotation.bind.echo.EchoFunc;

@BindConfig(Info.class)
public class InfoBindConfig {

    /**************************************************
     *
     * id
     *
     **************************************************/
    @BindField
    @BindEt(R.id.idEt)
    public long id;

    @EchoFunc("id")
    public void idEcho(long id, @ViewId(R.id.idEt) EditText idEt) {

    }

    /**************************************************
     *
     * name
     *
     **************************************************/
    @BindField
    @BindEt(R.id.nameEt)
    public String name;

    /**************************************************
     *
     * nickname
     *
     **************************************************/
    @BindField
    @BindEt(R.id.nicknameEt)
    public String nickname;

    /**************************************************
     *
     * age
     *
     **************************************************/
    @BindField
    @ViewBinder(R.id.ageEt)
    public int age;

    @BindMethod(R.id.ageEt)
    public void bindAgeEt(Destroys destroys, EditText ageEt, Handler handler) {
        BindTool.bindEt(destroys, ageEt, handler);
    }

    @EchoFunc("age")
    public void ageEcho(int age, @ViewId(R.id.ageEt) EditText ageEt) {

    }

    /**************************************************
     *
     * address
     *
     **************************************************/
    @BindField
    public String address;

    @BindEt(R.id.provinceEt)
    public String province;

    @BindEt(R.id.cityEt)
    public String city;

    @BindEt(R.id.areaEt)
    public String area;

    @EchoFunc("address")
    public void addressEcho(String address
            , @ViewId(R.id.provinceEt) EditText provinceEt
            , @ViewId(R.id.cityEt) EditText cityEt
            , @ViewId(R.id.areaEt) EditText areaEt
    ) {

    }

}
