package com.codingtu.cooltu.form;

import android.view.View;
import android.widget.EditText;

import com.codingtu.cooltu.bean.Forms;
import com.codingtu.cooltu.lib4a.form.CoreFormLink;
import com.codingtu.cooltu.lib4a.form.FormLink;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public class AddressLink extends CoreFormLink<Forms> {
    private EditText provinceEt;
    private EditText cityEt;
    private EditText areaEt;

    public AddressLink(Destroys destroys) {
        super(destroys);
    }

    @Override
    public FormLink setViews(View... views) {
        provinceEt = (EditText) views[0];
        cityEt = (EditText) views[1];
        areaEt = (EditText) views[2];
        return this;
    }

    @Override
    public void link(int id) {
        String province = provinceEt.getText().toString();
        String city = cityEt.getText().toString();
        String area = areaEt.getText().toString();
        bean.address = province + "-" + city + "-" + area;
    }

    @Override
    public void echo() {
        String[] split = bean.address.split("-");
        ViewTool.setEditTextAndSelection(provinceEt, split[0]);
        ViewTool.setEditTextAndSelection(cityEt, split[1]);
        ViewTool.setEditTextAndSelection(areaEt, split[2]);
    }

    @Override
    public void destroy() {
        super.destroy();
        provinceEt = null;
        cityEt = null;
        areaEt = null;
    }
}
