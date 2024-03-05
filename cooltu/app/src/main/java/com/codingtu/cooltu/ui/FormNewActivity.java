package com.codingtu.cooltu.ui;

import android.os.Bundle;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bind.FormBindConfig;
import com.codingtu.cooltu.bind.InfoBindConfig;
import com.codingtu.cooltu.processor.annotation.bind.Bind;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;

import core.actbase.FormNewActivityBase;
import core.actres.FormNewActivityRes;

@To(FormNewActivityRes.class)
@Bind({InfoBindConfig.class, FormBindConfig.class})
@ActBase(layout = R.layout.activity_form_new)
public class FormNewActivity extends FormNewActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @ClickView(value = R.id.saveBt1)
    public void saveBt1Click() {

    }
}

