package com.codingtu.cooltu.lib4a.form.push;

import android.os.Message;
import android.view.View;
import android.view.ViewGroup;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.view.combine.RadioGroup;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public class DefaultRadioGroupPush extends CorePush<DefaultRadioGroupPush> {

    private Destroys destroys;
    private RadioGroup rg;
    RadioGroup.OnSetItem onSetItem;
    private Integer selected;

    @Override
    public DefaultRadioGroupPush destory(Destroys destroys) {
        this.destroys = destroys;
        return super.destory(destroys);
    }

    @Override
    protected void destroyOthers() {
        destroys = null;
        rg = null;
        onSetItem = null;
    }

    public DefaultRadioGroupPush onSetItem(RadioGroup.OnSetItem onSetItem) {
        this.onSetItem = onSetItem;
        return this;
    }

    public DefaultRadioGroupPush selected(Integer selected) {
        this.selected = selected;
        return this;
    }

    @Override
    public DefaultRadioGroupPush addView(View view) {
        rg = RadioGroup.obtain(destroys).setBts((ViewGroup) view).addOnSelectChange(new RadioGroup.OnSelectChange() {
            @Override
            public void onChange(int selected) {
                Message msg = Message.obtain();
                msg.what = view.getId();
                msg.obj = selected;
                handler.sendMessage(msg);
            }
        }).setOnSetItem(onSetItem);
        if (selected != null) {
            rg.setSelected(selected);
        }
        view.setTag(R.id.tag_0, rg);
        return this;
    }
}
