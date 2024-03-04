package com.codingtu.cooltu.lib4a.bind;

import android.os.Handler;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.codingtu.cooltu.lib4a.view.combine.HandlerOnSelectChange;
import com.codingtu.cooltu.lib4a.view.combine.RadioGroup;
import com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public class BindTool {
    public static void bindEt(Destroys destroys, EditText et, Handler handler) {
        et.addTextChangedListener(new HandlerTextWatcher(destroys, handler, et.getId()));
    }
}
