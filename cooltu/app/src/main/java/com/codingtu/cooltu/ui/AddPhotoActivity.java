package com.codingtu.cooltu.ui;

import android.os.Bundle;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.Photo;
import com.codingtu.cooltu.processor.annotation.form.Form;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;

import core.actbase.AddPhotoActivityBase;
import core.actres.AddPhotoActivityRes;

@To(AddPhotoActivityRes.class)
@Form(Photo.class)
@ActBase(layout = R.layout.activity_add_photo)
public class AddPhotoActivity extends AddPhotoActivityBase {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showMenuDialog("年后");
    }

    @Override
    protected boolean showDeleteItemBt1(String str) {
        return true;
    }

    @ClickView(value = R.id.reportTv, inAct = false)
    public void reportTvClick(String str) {
        toast("点击工单");
    }

    @ClickView(value = R.id.deleteItemBt, inAct = false)
    public void deleteItemBtClick(String str) {
        toast("点击删除：" + str);
    }

    @ClickView(value = R.id.deleteItemBt1,inAct = false)
    public void deleteItemBt1Click() {
        toast("删除1");
    }

}

