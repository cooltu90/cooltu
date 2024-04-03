package com.codingtu.cooltu.ui;

import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.form.TypeOnSetItem;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.combine.RadioGroup;
import com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher;
import com.codingtu.cooltu.processor.annotation.form.Form;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;

import java.util.List;
import java.util.Objects;

import core.actbase.FormActivityBase;
import core.actres.FormActivityRes;

@To(FormActivityRes.class)
@ActBase(layout = R.layout.activity_form)
public class FormActivity extends FormActivityBase {

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
        linkEditText(R.id.nameEt, nameEt, nameEt, nicknameEt);
        getRadioGroup(numLl).setOnSetItem(new TypeOnSetItem());
    }

    protected void handleMessage(Message msg, List<Object> linkObjs) {
        switch (msg.what) {
            case R.id.nameEt:
                handleName(linkObjs);
                break;
        }
    }

    private void handleName(List<Object> linkObjs) {
        EditText nameEt = (EditText) linkObjs.get(0);
        EditText nicknameEt = (EditText) linkObjs.get(1);
        ViewTool.setText(nicknameEt, nameEt.getText().toString());
    }

    @ClickView(R.id.saveBt)
    public void saveBtClick() {
        RadioGroup rg = ViewTool.getRadioGroup(numLl);
        int selected = rg.getSelected();
        Logs.i("selected:" + selected);
    }
}

