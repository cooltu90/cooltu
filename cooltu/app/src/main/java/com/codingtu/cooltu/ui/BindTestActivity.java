package com.codingtu.cooltu.ui;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.Doc;
import com.codingtu.cooltu.form.config.DocFormConfig;
import com.codingtu.cooltu.processor.annotation.form.Form;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;

import core.actbase.BindTestActivityBase;
import core.actres.BindTestActivityRes;

@To(BindTestActivityRes.class)
@Form(DocFormConfig.class)
@ActBase(layout = R.layout.activity_bind_test)
public class BindTestActivity extends BindTestActivityBase {
    protected Doc doc;

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();

    }

    @ClickView(R.id.saveBt)
    public void saveBtClick() {

    }

}

