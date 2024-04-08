package com.codingtu.cooltu.ui;

import android.os.Message;
import android.view.View;
import android.widget.EditText;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.FormDatas;
import com.codingtu.cooltu.form.DataFormConfig;
import com.codingtu.cooltu.form.TypeOnSetItem;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.combine.RadioGroup;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;

import java.util.Map;

import core.actbase.FormActivityBase;
import core.actres.FormActivityRes;

@To(FormActivityRes.class)
@ActBase(layout = R.layout.activity_form)
public class FormActivity extends FormActivityBase {


    @Override
    protected void initFormView() {
        numLl.setTag(com.codingtu.cooltu.lib4a.R.id.tag_0,
                RadioGroup.obtain(this)
                        .setBts(dataFormConfig.getNumViews(numLl))
                        .setOnSetItem(new TypeOnSetItem()));
        super.initFormView();
    }

    protected void echo(FormDatas.FormData formData) {
        ViewTool.setEditTextAndSelection(nameEt, formData.name);
        dataFormConfig.echoName(formData, nameEt, nicknameEt);
        ViewTool.setEditTextAndSelection(nicknameEt, formData.nickname);
    }

    @ClickView(R.id.saveBt)
    public void saveBtClick() {
        try {
            FormDatas.FormData formData = obtainFormData(null);
        } catch (Exception e) {
            toast(e.getMessage());
        }
    }

}

