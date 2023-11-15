package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormActivityBase extends com.codingtu.cooltu.lib4a.ui.act.CoreActivity implements View.OnClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
    protected android.widget.SeekBar timeSb;
    protected android.widget.TextView nameTv;
    protected android.widget.Button bt;
    protected android.widget.EditText nameEt1;
    protected android.widget.LinearLayout radios;
    protected android.widget.LinearLayout radios1;
    protected android.widget.EditText nameEt2;
    protected android.widget.EditText nameEt3;
    protected android.widget.SeekBar timeSb1;
    protected java.lang.String fromAct;
    protected com.codingtu.cooltu.bean.Forms forms;
    protected boolean initFormBean;
    public BindHandler bindHandler;
    protected com.codingtu.cooltu.form.TypeOnSetItem typeOnSetItem;
    protected com.codingtu.cooltu.lib4a.view.combine.RadioGroup radiosRg;
    protected com.codingtu.cooltu.lib4a.view.combine.RadioGroup radios1Rg;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_form);

        timeSb = findViewById(com.codingtu.cooltu.R.id.timeSb);
        nameTv = findViewById(com.codingtu.cooltu.R.id.nameTv);
        bt = findViewById(com.codingtu.cooltu.R.id.bt);
        nameEt1 = findViewById(com.codingtu.cooltu.R.id.nameEt1);
        radios = findViewById(com.codingtu.cooltu.R.id.radios);
        radios1 = findViewById(com.codingtu.cooltu.R.id.radios1);
        nameEt2 = findViewById(com.codingtu.cooltu.R.id.nameEt2);
        nameEt3 = findViewById(com.codingtu.cooltu.R.id.nameEt3);
        timeSb1 = findViewById(com.codingtu.cooltu.R.id.timeSb1);

        bt.setOnClickListener(this);


        fromAct = core.tools.Pass.fromAct(getIntent());
        forms = core.tools.Pass.forms(getIntent());

        //radiosRg
        typeOnSetItem = new com.codingtu.cooltu.form.TypeOnSetItem();
        radiosRg = com.codingtu.cooltu.lib4a.view.combine.RadioGroup.obtain(this).setBts(radios).setOnSetItem(typeOnSetItem);
        radios.setTag(com.codingtu.cooltu.lib4a.R.id.tag_0, radiosRg);
        //radios1Rg
        radios1Rg = com.codingtu.cooltu.lib4a.view.combine.RadioGroup.obtain(this).setBts(radios1).setOnSetItem(typeOnSetItem);
        radios1.setTag(com.codingtu.cooltu.lib4a.R.id.tag_0, radios1Rg);
        //forms
        if (forms == null) {
            forms = new com.codingtu.cooltu.bean.Forms();
            initFormBean = true;
        }
        bindHandler = new BindHandler(forms);
        nameEt1.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(bindHandler, com.codingtu.cooltu.processor.annotation.form.FormType.EDIT_TEXT, 0));
        nameEt2.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(bindHandler, com.codingtu.cooltu.processor.annotation.form.FormType.EDIT_TEXT, 1));
        nameEt3.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(bindHandler, com.codingtu.cooltu.processor.annotation.form.FormType.EDIT_TEXT, 2));
        nameTv.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(bindHandler, com.codingtu.cooltu.processor.annotation.form.FormType.TEXT_VIEW, 0));
        radiosRg.addOnSelectChange(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSelectChange(bindHandler, com.codingtu.cooltu.processor.annotation.form.FormType.RADIO_GROUP, 0));
        radios1Rg.addOnSelectChange(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSelectChange(bindHandler, com.codingtu.cooltu.processor.annotation.form.FormType.RADIO_GROUP, 1));
        timeSb.setOnSeekBarChangeListener(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSeekBarChangeListener(bindHandler, com.codingtu.cooltu.processor.annotation.form.FormType.SEEK_BAR, 0));
        timeSb1.setOnSeekBarChangeListener(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSeekBarChangeListener(bindHandler, com.codingtu.cooltu.processor.annotation.form.FormType.SEEK_BAR, 1));
        if (!initFormBean) {
            com.codingtu.cooltu.lib4a.tools.ViewTool.setText(nameEt1, new com.codingtu.cooltu.form.Name1Parse().toView(forms.name1));
            com.codingtu.cooltu.lib4a.tools.ViewTool.setText(nameEt3, forms.name3);
            com.codingtu.cooltu.lib4a.tools.ViewTool.setText(nameTv, forms.name4);
            radiosRg.setSelected(new com.codingtu.cooltu.lib4a.form.DefaultRadioGroupToString("r1", "r2", "r3").toView(forms.type));
            radios1Rg.setSelected(new com.codingtu.cooltu.form.TypeParse().toView(forms.type1));
            timeSb1.setProgress(forms.time1);
            timeSb.setProgress(new com.codingtu.cooltu.form.TypeParse().toView(forms.time));
        }

        onCreateComplete();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.bt:
                btClick(
                );
                break;

        }
    }

    protected void btClick() {
    }

    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

        }
    }

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {
        super.back(requestCode, permissions, grantResults);

    }

    public static class BindHandler extends android.os.Handler {
        private com.codingtu.cooltu.bean.Forms forms;

        public BindHandler(com.codingtu.cooltu.bean.Forms forms) {
            this.forms = forms;
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            if (msg.what == com.codingtu.cooltu.processor.annotation.form.FormType.EDIT_TEXT) {
                switch (msg.arg1) {
                    case 1:
                        forms.name2 = (java.lang.String) msg.obj;
                        break;
                    case 2:
                        forms.name3 = (java.lang.String) msg.obj;
                        break;
                    case 0:
                        forms.name1 = new com.codingtu.cooltu.form.Name1Parse().toBean(msg.obj);
                        break;
                }
            }
            if (msg.what == com.codingtu.cooltu.processor.annotation.form.FormType.TEXT_VIEW) {
                switch (msg.arg1) {
                    case 0:
                        forms.name4 = (java.lang.String) msg.obj;
                        break;
                }
            }
            if (msg.what == com.codingtu.cooltu.processor.annotation.form.FormType.RADIO_GROUP) {
                switch (msg.arg1) {
                    case 0:
                        forms.type = new com.codingtu.cooltu.lib4a.form.DefaultRadioGroupToString("r1", "r2", "r3").toBean(msg.obj);
                        break;
                    case 1:
                        forms.type1 = new com.codingtu.cooltu.form.TypeParse().toBean(msg.obj);
                        break;
                }
            }
            if (msg.what == com.codingtu.cooltu.processor.annotation.form.FormType.SEEK_BAR) {
                switch (msg.arg1) {
                    case 1:
                        forms.time1 = (int) msg.obj;
                        break;
                    case 0:
                        forms.time = new com.codingtu.cooltu.form.TypeParse().toBean(msg.obj);
                        break;
                }
            }


        }
    }

    protected boolean checkForms() {
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isBlank(forms.name1)) {
            toast("请输入name1");
            return false;
        }
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isBlank(forms.name2)) {
            toast("请输入name2");
            return false;
        }
        if (new com.codingtu.cooltu.form.Name1FormCheck().check(forms, forms.name3)) {
            toast("请输入name3");
            return false;
        }
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isBlank(forms.name4)) {
            toast("请输入name4");
            return false;
        }
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isBlank(forms.type)) {
            toast("请选择类型");
            return false;
        }
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isBlank(forms.type1)) {
            toast("请选择类型1");
            return false;
        }
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isBlank(forms.time)) {
            toast("请选择时间");
            return false;
        }
        return true;
    }

}

