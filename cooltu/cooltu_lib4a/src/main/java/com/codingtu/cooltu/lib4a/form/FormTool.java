package com.codingtu.cooltu.lib4a.form;

import android.view.ViewGroup;
import android.widget.EditText;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.view.combine.RadioGroup;
import com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public class FormTool {

    public static void linkEditText(Destroys destroys, FormHandler handler, String methodName, EditText et, Object... views) {
        et.addTextChangedListener(new HandlerTextWatcher(destroys, handler, et.getId()));
        handler.link(et.getId(), methodName, views);
    }

    public static RadioGroup obtainRadioGroup(Destroys destroys, ViewGroup viewGroup) {
        RadioGroup rg = RadioGroup.obtain(destroys).setBts(viewGroup);
        viewGroup.setTag(R.id.tag_0, rg);
        return rg;
    }

}
