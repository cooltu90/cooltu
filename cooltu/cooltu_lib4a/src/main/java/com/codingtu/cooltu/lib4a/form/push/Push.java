package com.codingtu.cooltu.lib4a.form.push;

import android.os.Handler;
import android.view.View;

import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public interface Push<THIS extends Push> extends OnDestroy {

    THIS destory(Destroys destroys);

    THIS addView(View view);

    THIS bindHandler(Handler handler);
}
