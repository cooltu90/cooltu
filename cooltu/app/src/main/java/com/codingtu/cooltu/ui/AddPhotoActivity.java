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

}

