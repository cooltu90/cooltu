package com.codingtu.cooltu.ui;

import android.os.Message;
import android.widget.EditText;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.form.TypeOnSetItem;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.combine.RadioGroup;
import com.codingtu.cooltu.processor.annotation.form.HandleMethod;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;

import core.actbase.FormActivityBase;
import core.actres.FormActivityRes;

@To(FormActivityRes.class)
@ActBase(layout = R.layout.activity_form)
public class FormActivity extends FormActivityBase {

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
        linkEditText("handleName", nameEt, nameEt, nicknameEt);
        getRadioGroup(numLl).setOnSetItem(new TypeOnSetItem());
    }

    @HandleMethod({R.id.nameEt, R.id.nicknameEt})
    protected void handleName(Message msg, EditText nameEt, EditText nicknameEt) {
        ViewTool.setText(nicknameEt, nameEt.getText().toString());
    }

    @ClickView(R.id.saveBt)
    public void saveBtClick() {
        RadioGroup rg = ViewTool.getRadioGroup(numLl);
        int selected = rg.getSelected();
        Logs.i("selected:" + selected);
    }
}

