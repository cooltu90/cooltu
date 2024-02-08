package com.codingtu.cooltu.lib4a.form.push;

import android.os.Handler;
import android.view.View;

import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public interface Push<THIS extends Push> extends OnDestroy {

    default THIS destory(Destroys destroys) {
        destroys.add(this);
        return (THIS) this;
    }

    THIS addView(View view);

    THIS bindHandler(Handler handler);
}
