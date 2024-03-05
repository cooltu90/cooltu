package com.codingtu.cooltu.bind;

import android.os.Handler;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.Info;
import com.codingtu.cooltu.form.TypeOnSetItem;
import com.codingtu.cooltu.lib4a.bind.BindTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.processor.annotation.bind.BindConfig;
import com.codingtu.cooltu.processor.annotation.bind.BindField;
import com.codingtu.cooltu.processor.annotation.bind.BindMethod;
import com.codingtu.cooltu.processor.annotation.bind.ViewId;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindEt;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindRg;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindSeekbar;
import com.codingtu.cooltu.processor.annotation.bind.binder.ViewBinder;
import com.codingtu.cooltu.processor.annotation.bind.echo.EchoFunc;
import com.codingtu.cooltu.processor.annotation.bind.parse.HandleView;
import com.codingtu.cooltu.processor.annotation.bind.parse.ToBean;

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

    @ToBean("id")
    public long parseLong(Object obj) {
        return Long.parseLong((String) obj);
    }

    /**************************************************
     *
     * name
     *
     **************************************************/
    @BindField
    @BindEt(R.id.nameEt)
    public String name;

    @HandleView(R.id.nameEt)
    public void handleView(Info info, @ViewId(R.id.nicknameEt) EditText nicknameEt) {
        ViewTool.setEditTextAndSelection(nicknameEt, name);
    }

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

    @ToBean("age")
    public int toAge(Object obj) {
        return (int) obj;
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

    @HandleView(R.id.provinceEt)
    public void handleProvince(Info info) {

    }

    /**************************************************
     *
     *
     *
     **************************************************/
    @BindField
    @BindRg(id = R.id.numLl, onSetItem = TypeOnSetItem.class, items = {"1", "2", "3"})
    public String num;

    @BindField
    @BindRg(id = R.id.num1Ll, onSetItem = TypeOnSetItem.class)
    public int num1;

    /**************************************************
     *
     *
     *
     **************************************************/
    @BindField
    @BindSeekbar(R.id.timeSb)
    public float time;

    @ToBean("time")
    public float toTime(Object obj) {
        return (int) obj / 60f;
    }

    @EchoFunc("time")
    public void timeEcho(float time, @ViewId(R.id.timeSb) SeekBar timeSeekBar) {

    }

    @BindField
    @BindSeekbar(R.id.heightSb)
    public int height;
}
