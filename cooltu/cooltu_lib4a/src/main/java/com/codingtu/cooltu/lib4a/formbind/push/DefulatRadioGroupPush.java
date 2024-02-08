package com.codingtu.cooltu.lib4a.formbind.push;

import android.os.Message;
import android.view.View;
import android.view.ViewGroup;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.view.combine.RadioGroup;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public class DefulatRadioGroupPush extends CorePush<DefulatRadioGroupPush> {

    private Destroys destroys;
    private RadioGroup rg;
    RadioGroup.OnSetItem onSetItem;
    private Integer selected;

    @Override
    public DefulatRadioGroupPush destory(Destroys destroys) {
        this.destroys = destroys;
        return super.destory(destroys);
    }

    @Override
    protected void destroyOthers() {
        destroys = null;
        rg = null;
        onSetItem = null;
    }

    public DefulatRadioGroupPush onSetItem(RadioGroup.OnSetItem onSetItem) {
        this.onSetItem = onSetItem;
        return this;
    }

    public DefulatRadioGroupPush selected(Integer selected) {
        this.selected = selected;
        return this;
    }

    @Override
    public DefulatRadioGroupPush addView(View view) {
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
