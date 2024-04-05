package com.codingtu.cooltu.lib4a.form;

import android.os.Message;

import java.util.Map;

public interface FormHandleCallBack {
    void handleMessage(Message msg, Map<String, Object[]> linkMap);
}
