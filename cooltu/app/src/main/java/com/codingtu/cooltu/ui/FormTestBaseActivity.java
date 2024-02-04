package com.codingtu.cooltu.ui;

import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.bean.FormsObj;
import com.codingtu.cooltu.formbind.CountParse;
import com.codingtu.cooltu.formbind.NameCheck;
import com.codingtu.cooltu.formbind.binder.AddressBinder;
import com.codingtu.cooltu.formbind.binder.DayBinder;
import com.codingtu.cooltu.formbind.binder.MonthBinder;
import com.codingtu.cooltu.formbind.binder.YearBinder;
import com.codingtu.cooltu.lib4a.form.advice.DefaultEditTextAdvice;
import com.codingtu.cooltu.lib4a.formbind.binder.Binder;
import com.codingtu.cooltu.lib4a.formbind.binder.EditTextBinder;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4j.data.map.ValueMap;
import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;

import java.util.HashMap;
import java.util.Map;

import core.actbase.FormTestBaseActivityBase;
import core.actres.FormTestBaseActivityRes;

@To(FormTestBaseActivityRes.class)
@ActBase
public class FormTestBaseActivity extends FormTestBaseActivityBase {

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();

        initFormView();
        if (forms == null) {
            forms = new FormsObj();
            initFormBean = true;
        }
        bindHandler = new BindHandler(this, forms);

        new DefaultEditTextAdvice().addHandler(bindHandler).addViews(nameEt).start();
        bindHandler.addBinder(R.id.nameEt, 0, new EditTextBinder().addViews(nameEt));

        new DefaultEditTextAdvice().addHandler(bindHandler).addViews(provinceEt).start();
        bindHandler.addBinder(R.id.provinceEt, 0, new EditTextBinder().addViews(provinceEt));


        new DefaultEditTextAdvice().addHandler(bindHandler).addViews(cityEt).start();
        new DefaultEditTextAdvice().addHandler(bindHandler).addViews(areaEt).start();

        AddressBinder addressBinder = new AddressBinder().addViews(provinceEt, cityEt, areaEt);
        bindHandler.addBinder(R.id.provinceEt, 1, addressBinder);
        bindHandler.addBinder(R.id.cityEt, 0, addressBinder);
        bindHandler.addBinder(R.id.areaEt, 0, addressBinder);

        new DefaultEditTextAdvice().addHandler(bindHandler).addViews(ymdEt).start();
        bindHandler.addBinder(R.id.ymdEt, 0, new YearBinder().addViews(ymdEt));
        bindHandler.addBinder(R.id.ymdEt, 1, new MonthBinder().addViews(ymdEt));
        bindHandler.addBinder(R.id.ymdEt, 2, new DayBinder().addViews(ymdEt));

        new DefaultEditTextAdvice().addHandler(bindHandler).addViews(countEt).start();
        bindHandler.addBinder(R.id.countEt, 0, new EditTextBinder().addViews(countEt));


        if (!initFormBean) {
            ViewTool.setText(nameEt, forms.name);
            ViewTool.setText(countEt, new CountParse().toView(forms.count));
        }


    }

    protected void initFormView() {}

    /**************************************************
     *
     *
     *
     **************************************************/

    protected FormsObj forms;
    protected boolean initFormBean;
    private BindHandler bindHandler;

    public static class BindHandler extends android.os.Handler implements OnDestroy {
        private FormsObj forms;
        private ValueMap<Integer, Map<Integer, Binder>> binders = new ValueMap<Integer, Map<Integer, Binder>>() {
            @Override
            protected Map<Integer, Binder> newValue() {
                return new HashMap<>();
            }
        };

        public BindHandler(Destroys destroys, FormsObj forms) {
            destroys.add(this);
            this.forms = forms;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Map<Integer, Binder> map = binders.get(msg.what);

            switch (msg.what) {
                case R.id.nameEt:
                    forms.name = (String) map.get(0).value();
                    break;
                case R.id.provinceEt:
                    forms.province = (String) map.get(0).value();
                    forms.address = (String) map.get(1).value();
                    break;
                case R.id.cityEt:
                    forms.address = (String) map.get(0).value();
                    break;
                case R.id.areaEt:
                    forms.address = (String) map.get(0).value();
                    break;
                case R.id.ymdEt:
                    forms.year = (int) map.get(0).value();
                    forms.month = (int) map.get(1).value();
                    forms.day = (int) map.get(2).value();
                    break;
                case R.id.countEt:
                    forms.count = new CountParse().toBean(map.get(0).value());
                    break;
            }

        }

        @Override
        public void destroy() {
            binders = null;
            forms = null;
        }

        public void addBinder(int viewId, int index, Binder binder) {
            binders.get(viewId).put(index, binder);
        }
    }

    protected boolean checkForms() {
        if (!new NameCheck().check(forms, forms.name)) {
            toast("请输入姓名");
            return false;
        }
        return true;
    }
}

