package com.codingtu.cooltu.ui;

import android.widget.EditText;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.Forms;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.HandlerTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.processor.annotation.form.Form;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;

import core.actbase.FormActivityBase;
import core.actres.FormActivityRes;

@To(FormActivityRes.class)
@Form(Forms.class)
@ActBase(layout = R.layout.activity_form)
public class FormActivity extends FormActivityBase {


    @ClickView(R.id.bt)
    public void btClick() {
        ViewTool.setText(nameTv, "sdfsdf");
        HandlerTool.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                Logs.i(forms);
            }
        });
    }

}

