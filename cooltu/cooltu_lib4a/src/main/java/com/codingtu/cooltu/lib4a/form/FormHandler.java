package com.codingtu.cooltu.lib4a.form;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

import java.util.HashMap;
import java.util.Map;

public class FormHandler extends Handler implements OnDestroy {
    private Map<Integer, Map<String, Object[]>> linkMap = new HashMap<>();
    private FormHandleCallBack callBack;

    public FormHandler(Destroys destroys, FormHandleCallBack callBack) {
        destroys.add(this);
        this.callBack = callBack;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        Map<String, Object[]> links = linkMap.get(msg.what);
        callBack.handleMessage(msg, links);
    }

    @Override
    public void destroy() {
        if (linkMap != null) {
            for (Integer index : linkMap.keySet()) {
                java.util.Map<String, Object[]> map = linkMap.get(index);
                for (String methodName :
                        map.keySet()) {
                    Object[] objects = map.get(methodName);
                    for (int i = 0; i < objects.length; i++) {
                        objects[i] = null;
                    }
                }
                map.clear();
            }
            linkMap.clear();
            linkMap = null;
        }
        callBack = null;
    }

    public void link(int handleId, String methodName, Object... linkViews) {
        Map<String, Object[]> map = linkMap.get(handleId);
        if (map == null) {
            map = new java.util.HashMap<>();
            linkMap.put(handleId, map);
        }
        map.put(methodName, linkViews);
    }
}
