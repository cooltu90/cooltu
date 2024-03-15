package com.codingtu.cooltu.lib4a.view.dialogview;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.tools.InflateTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.layer.Layer;
import com.codingtu.cooltu.lib4a.view.layer.event.OnHiddenFinishedCallBack;
import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public final class NoticeDialog implements OnDestroy, View.OnClickListener {

    private Activity act;
    private Layer rlv;
    private int layout;
    private View inflate;
    private View contentTv;

    public NoticeDialog(Activity act) {
        this.act = act;
        if (act instanceof Destroys) {
            ((Destroys) act).add(this);
        }
    }

    @Override
    public void destroy() {
        contentTv = null;
        inflate = null;
        rlv = null;
        act = null;
    }

    public NoticeDialog setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public NoticeDialog build() {
        rlv = new Layer(act);
        rlv.setHiddenWhenBackClick(false);
        rlv.setHiddenWhenShadowClick(false);
        ViewTool.addToAct(act, rlv);
        ViewTool.gone(rlv);
        inflate = InflateTool.inflate(act, layout);
        rlv.addView(inflate, ViewTool.WRAP_CONTENT, ViewTool.WRAP_CONTENT);
        contentTv = inflate.findViewById(R.id.dialogContentTv);
        inflate.findViewById(R.id.noticeDialogYesBt).setOnClickListener(this);

        ViewTool.inRelativeCenter(inflate);
        return this;
    }

    public NoticeDialog setContent(String text) {
        ViewTool.setText(contentTv, text);
        return this;
    }

    public String getContent() {
        return ((TextView) contentTv).getText().toString();
    }

    public void show() {
        rlv.show();
    }

    public void hidden(OnHiddenFinishedCallBack callBack) {
        rlv.hidden(callBack);
    }

    public void hidden() {
        hidden(null);
    }

    @Override
    public void onClick(View v) {
        hidden();
    }

}
