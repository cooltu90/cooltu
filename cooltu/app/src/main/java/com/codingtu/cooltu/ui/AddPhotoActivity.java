package com.codingtu.cooltu.ui;

import android.os.Bundle;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.Photo;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.textview.PrefixFixedSizeTextWather;
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

    @ClickView(value = R.id.submitBt, check = true)
    public void submitBtClick() {
        Logs.i(photo.toJson());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        labelEt.addTextChangedListener(new PrefixFixedSizeTextWather(this, labelEt, 5, "L-"));
    }

    @Override
    protected void initFormView() {
        super.initFormView();
        photo = new Photo();
        photo.label = "L-00001";
        photo.classType = 1;
    }

    @ClickView(R.id.otherBt)
    public void otherBtClick() {
        showDialog("");
    }

    @Override
    protected boolean dialogYes(String text) {
        ViewTool.setEditTextAndSelection(otherEt, text);
        return true;
    }
}

