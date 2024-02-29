package com.codingtu.cooltu.lib4a.bind;

import android.os.Handler;
import android.widget.EditText;

import com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public class BindTool {
    public static void bindEt(Destroys destroys, EditText et, Handler handler) {
        et.addTextChangedListener(new HandlerTextWatcher(destroys, handler, et.getId()));
    }
}
