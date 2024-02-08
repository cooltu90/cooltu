package com.codingtu.cooltu.ui;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.FormObj;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.processor.annotation.formbind.FormBind;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;

import core.actbase.FormTestActivityBase;
import core.actres.FormTestActivityRes;

@To(FormTestActivityRes.class)
@FormBind(FormObj.class)
@ActBase(layout = R.layout.activity_form_test, base = FormTestBaseActivity.class)
public class FormTestActivity extends FormTestActivityBase {

    @Override
    protected void initFormView() {
        super.initFormView();
        forms = new FormObj();
        forms.name = "李四";
        forms.age = "12";
        forms.province = "黑龙江";
        forms.city = "哈尔滨";
        forms.area = "地铁站";
        forms.classIndex = 2;
    }

    @ClickView(R.id.submitBt)
    public void submitBtClick() {
        Logs.i(forms);
    }

}

