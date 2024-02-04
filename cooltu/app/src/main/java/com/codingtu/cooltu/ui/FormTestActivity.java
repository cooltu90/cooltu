package com.codingtu.cooltu.ui;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.FormsObj;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.processor.annotation.formbind.FormBind;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;

import core.actbase.FormTestActivityBase;
import core.actres.FormTestActivityRes;

@To(FormTestActivityRes.class)
@FormBind(FormsObj.class)
@ActBase(layout = R.layout.activity_form_test, base = FormTestBaseActivity.class)
public class FormTestActivity extends FormTestActivityBase {

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
        ViewTool.setText(ymdEt, "2024-01-01");
    }

    @ClickView(R.id.testBt)
    public void testBtClick() {
        if (!checkForms()) {
            return;
        }

        Logs.i(forms.toJson());
    }

    @Override
    protected void initFormView() {
        super.initFormView();
        forms = new FormsObj();
        forms.name = "lisi";
    }
}

