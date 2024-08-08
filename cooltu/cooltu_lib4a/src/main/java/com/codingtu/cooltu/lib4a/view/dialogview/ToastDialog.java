package com.codingtu.cooltu.lib4a.view.dialogview;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.view.layer.Layer;
import com.codingtu.cooltu.lib4a.view.layer.event.OnHiddenFinishedCallBack;
import com.codingtu.cooltu.lib4a.view.layer.event.OnShowFinishedCallBack;
import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4a.tools.InflateTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;

public final class ToastDialog implements OnDestroy {

    private Activity act;
    private Layer layer;
    private int layout;
    private View inflate;
    private View contentTv;

    public ToastDialog(Activity act) {
        this.act = act;
    }

    @Override
    public void destroy() {
        contentTv = null;
        inflate = null;
        ViewTool.removeFromAct(act, layer);
        if (layer != null)
            layer.destroy();
        layer = null;
        act = null;
    }

    public ToastDialog destroys(Destroys destroys) {
        if (destroys != null)
            destroys.add(this);
        return this;
    }

    public ToastDialog setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public ToastDialog build() {
        return build(false, false);
    }

    public ToastDialog build(boolean isHiddenWhenBackClick, boolean isHiddenWhenShadowClick) {
        layer = new Layer(act);
        layer.setHiddenWhenBackClick(isHiddenWhenBackClick);
        layer.setHiddenWhenShadowClick(isHiddenWhenShadowClick);
        ViewTool.addToAct(act, layer);
        ViewTool.gone(layer);
        inflate = InflateTool.inflate(act, layout);
        layer.addView(inflate, ViewTool.WRAP_CONTENT, ViewTool.WRAP_CONTENT);
        contentTv = inflate.findViewById(R.id.dialogContentTv);
        ViewTool.inRelativeCenter(inflate);
        return this;
    }

    public void setHiddenWhenBackClick(boolean isHiddenWhenBackClick) {
        layer.setHiddenWhenBackClick(isHiddenWhenBackClick);
    }

    public void setHiddenWhenShadowClick(boolean isHiddenWhenShadowClick) {
        layer.setHiddenWhenShadowClick(isHiddenWhenShadowClick);
    }


    public ToastDialog setContent(String text) {
        ViewTool.setText(contentTv, text);
        return this;
    }

    public String getContent() {
        return ((TextView) contentTv).getText().toString();
    }

    public boolean isShow() {
        return ViewTool.isVisible(layer);
    }


    public void show() {
        layer.show();
    }

    public void show(OnShowFinishedCallBack onShowFinishedCallBack) {
        layer.show(onShowFinishedCallBack);
    }

    public void hidden(OnHiddenFinishedCallBack callBack) {
        layer.hidden(callBack);
    }

    public void hidden() {
        hidden(null);
    }
}
