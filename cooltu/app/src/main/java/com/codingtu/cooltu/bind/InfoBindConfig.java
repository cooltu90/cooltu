package com.codingtu.cooltu.bind;

import android.os.Handler;
import android.widget.EditText;
import android.widget.SeekBar;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.Info;
import com.codingtu.cooltu.form.TypeOnSetItem;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.processor.annotation.bind.BindConfig;
import com.codingtu.cooltu.processor.annotation.bind.BindField;
import com.codingtu.cooltu.processor.annotation.bind.BindMethod;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindTextView;
import com.codingtu.cooltu.processor.annotation.ui.ViewId;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindEditText;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindRadioGroup;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindSeekBar;
import com.codingtu.cooltu.processor.annotation.bind.binder.BindView;
import com.codingtu.cooltu.processor.annotation.bind.check.Check;
import com.codingtu.cooltu.processor.annotation.bind.check.CheckMethod;
import com.codingtu.cooltu.processor.annotation.bind.echo.EchoMethod;
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
    @BindEditText(R.id.idEt)
    @Check(prompt = "请输入正确的id")
    public long id;

    @CheckMethod(fields = {"id"}, prompts = {"嘻嘻嘻"})
    public boolean checkId(long id) {
        return false;
    }

    @EchoMethod("id")
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
    @BindEditText(R.id.nameEt)
    @Check(prompt = "请输入姓名")
    public String name;

    @HandleView(R.id.nameEt)
    public void handleView(Info info, @ViewId(R.id.nicknameEt) EditText nnEt) {
        ViewTool.setEditTextAndSelection(nnEt, name);
    }

    /**************************************************
     *
     * nickname
     *
     **************************************************/
    @BindField
    @BindEditText(R.id.nicknameEt)
    public String nickname;


    /**************************************************
     *
     * age
     *
     **************************************************/
    @BindField
    @BindView(R.id.ageEt)
    public int age;

    @BindMethod(R.id.ageEt)
    public void bindAgeEt(Destroys destroys, EditText ageEt, Handler handler) {
//        BindTool.bindEt(destroys, ageEt, handler);
    }

    @EchoMethod("age")
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

    @BindEditText(R.id.provinceEt)
    public String province;

    @BindEditText(R.id.cityEt)
    public String city;

    @BindEditText(R.id.areaEt)
    public String area;

    @EchoMethod("address")
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
    @BindRadioGroup(id = R.id.numLl, onSetItem = TypeOnSetItem.class, items = {"1", "2", "3"})
    public String num;

    @BindRadioGroup(id = R.id.num1Ll, onSetItem = TypeOnSetItem.class)
    public int num1;


    /**************************************************
     *
     *
     *
     **************************************************/
    @BindField
    @BindSeekBar(R.id.timeSb)
    public float time;

    @ToBean("time")
    public float toTime(Object obj) {
        return (int) obj / 60f;
    }

    @EchoMethod("time")
    public void timeEcho(float time, @ViewId(R.id.timeSb) SeekBar timeSeekBar) {

    }

    @BindField
    @BindSeekBar(R.id.heightSb)
    public int height;

    /**************************************************
     *
     *
     *
     **************************************************/
    @BindField
    @BindTextView(R.id.passwordTv)
    public String password;
}
