package com.codingtu.cooltu.lib4a.form;

import android.view.ViewGroup;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.view.combine.RadioGroup;
import com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public class FormTool {

    public static void linkTextView(Destroys destroys, FormHandler handler, String methodName, TextView tv, Object... views) {
        tv.addTextChangedListener(new HandlerTextWatcher(destroys, handler, tv.getId()));
        handler.link(tv.getId(), methodName, views);
    }

    public static RadioGroup obtainRadioGroup(Destroys destroys, ViewGroup viewGroup) {
        RadioGroup rg = RadioGroup.obtain(destroys).setBts(viewGroup);
        viewGroup.setTag(R.id.tag_0, rg);
        return rg;
    }

}
