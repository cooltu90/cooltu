package com.codingtu.cooltu.lib4a.formbind.push;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
