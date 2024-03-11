package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormNewActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.EditText age1Et;
    protected android.widget.LinearLayout numLl;
    protected android.widget.TextView saveBt1;
    protected android.widget.TextView saveBt2;
    protected android.widget.EditText nameEt;
    protected android.widget.EditText areaEt;
    protected android.widget.EditText ageEt;
    protected android.widget.TextView idTv;
    protected android.widget.SeekBar heightSb;
    protected android.widget.EditText id1Et;
    protected android.widget.SeekBar timeSb;
    protected android.widget.EditText nicknameEt;
    protected android.widget.LinearLayout num1Ll;
    protected android.widget.LinearLayout num2Ll;
    protected android.widget.EditText cityEt;
    protected android.widget.TextView passwordTv;
    protected android.widget.EditText provinceEt;
    protected android.widget.EditText idEt;
    protected com.codingtu.cooltu.bean.Info info;
    protected com.codingtu.cooltu.bind.InfoBindConfig infoBindConfig;
    protected boolean initInfo;
    protected InfoBindHandler infoBindHandler;
    protected com.codingtu.cooltu.lib4a.view.combine.RadioGroup numLlRg;
    protected com.codingtu.cooltu.lib4a.view.combine.RadioGroup num2LlRg;
    protected com.codingtu.cooltu.lib4a.view.combine.RadioGroup num1LlRg;
    protected com.codingtu.cooltu.bean.Form form;
    protected com.codingtu.cooltu.bind.FormBindConfig formBindConfig;
    protected boolean initForm;
    protected FormBindHandler formBindHandler;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_form_new);

        age1Et = findViewById(com.codingtu.cooltu.R.id.age1Et);
        numLl = findViewById(com.codingtu.cooltu.R.id.numLl);
        saveBt1 = findViewById(com.codingtu.cooltu.R.id.saveBt1);
        saveBt2 = findViewById(com.codingtu.cooltu.R.id.saveBt2);
        nameEt = findViewById(com.codingtu.cooltu.R.id.nameEt);
        areaEt = findViewById(com.codingtu.cooltu.R.id.areaEt);
        ageEt = findViewById(com.codingtu.cooltu.R.id.ageEt);
        idTv = findViewById(com.codingtu.cooltu.R.id.idTv);
        heightSb = findViewById(com.codingtu.cooltu.R.id.heightSb);
        id1Et = findViewById(com.codingtu.cooltu.R.id.id1Et);
        timeSb = findViewById(com.codingtu.cooltu.R.id.timeSb);
        nicknameEt = findViewById(com.codingtu.cooltu.R.id.nicknameEt);
        num1Ll = findViewById(com.codingtu.cooltu.R.id.num1Ll);
        num2Ll = findViewById(com.codingtu.cooltu.R.id.num2Ll);
        cityEt = findViewById(com.codingtu.cooltu.R.id.cityEt);
        passwordTv = findViewById(com.codingtu.cooltu.R.id.passwordTv);
        provinceEt = findViewById(com.codingtu.cooltu.R.id.provinceEt);
        idEt = findViewById(com.codingtu.cooltu.R.id.idEt);






        onCreateComplete();

    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();

        saveBt1.setOnClickListener(this);


        initBindView();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.saveBt1:
                saveBt1Click(
                );
                break;

        }
    }

    protected void saveBt1Click() {}


    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {

        }

        return false;

    }


    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == android.app.Activity.RESULT_OK) {

        }
    }

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {
        super.back(requestCode, permissions, grantResults);

    }








    protected void initBindView() {
        infoBindConfig = new com.codingtu.cooltu.bind.InfoBindConfig();
        com.codingtu.cooltu.form.TypeOnSetItem typeOnSetItem = new com.codingtu.cooltu.form.TypeOnSetItem();
        numLlRg = com.codingtu.cooltu.lib4a.view.combine.RadioGroup.obtain(this).setBts(com.codingtu.cooltu.lib4a.tools.ViewTool.getRadioGroupViews(com.codingtu.cooltu.R.id.rgItem, numLl)).setOnSetItem(typeOnSetItem).initItems().setOnClick(view -> infoBindConfig.onNumLlOnClick(info, view, numLl));
        numLl.setTag(com.codingtu.cooltu.lib4a.R.id.tag_0, numLlRg);
        num2LlRg = com.codingtu.cooltu.lib4a.view.combine.RadioGroup.obtain(this).setBts(com.codingtu.cooltu.lib4a.tools.ViewTool.getRadioGroupViews(com.codingtu.cooltu.R.id.rgItem, num2Ll)).setOnSetItem(typeOnSetItem).setItems("1", "2", "3");
        num2Ll.setTag(com.codingtu.cooltu.lib4a.R.id.tag_0, num2LlRg);
        num1LlRg = com.codingtu.cooltu.lib4a.view.combine.RadioGroup.obtain(this).setBts(num1Ll).setOnSetItem(typeOnSetItem);
        num1Ll.setTag(com.codingtu.cooltu.lib4a.R.id.tag_0, num1LlRg);
        formBindConfig = new com.codingtu.cooltu.bind.FormBindConfig();

        beforeInitBindView();
        if (info == null) {
            info = new com.codingtu.cooltu.bean.Info();
            initInfo = true;
        }
        infoBindHandler = new InfoBindHandler(info, infoBindConfig);
        idTv.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(this, infoBindHandler, com.codingtu.cooltu.R.id.idTv));
        link(infoBindHandler.linkMap, com.codingtu.cooltu.R.id.idTv, idEt, id1Et);
        idEt.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(this, infoBindHandler, com.codingtu.cooltu.R.id.idEt));
        link(infoBindHandler.linkMap, com.codingtu.cooltu.R.id.idEt, idEt, id1Et);
        id1Et.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(this, infoBindHandler, com.codingtu.cooltu.R.id.id1Et));
        link(infoBindHandler.linkMap, com.codingtu.cooltu.R.id.id1Et, idEt, id1Et);
        nameEt.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(this, infoBindHandler, com.codingtu.cooltu.R.id.nameEt));
        link(infoBindHandler.linkMap, com.codingtu.cooltu.R.id.nameEt, nicknameEt);
        nicknameEt.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(this, infoBindHandler, com.codingtu.cooltu.R.id.nicknameEt));
        infoBindConfig.bindAgeEt(this, ageEt, infoBindHandler);
        infoBindConfig.bindAgeEt(this, age1Et, infoBindHandler);
        provinceEt.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(this, infoBindHandler, com.codingtu.cooltu.R.id.provinceEt));
        cityEt.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(this, infoBindHandler, com.codingtu.cooltu.R.id.cityEt));
        areaEt.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(this, infoBindHandler, com.codingtu.cooltu.R.id.areaEt));
        numLlRg.addOnSelectChange(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSelectChange(this, infoBindHandler, com.codingtu.cooltu.R.id.numLl));
        link(infoBindHandler.linkMap, com.codingtu.cooltu.R.id.numLl, numLl);
        num2LlRg.addOnSelectChange(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSelectChange(this, infoBindHandler, com.codingtu.cooltu.R.id.num2Ll));
        link(infoBindHandler.linkMap, com.codingtu.cooltu.R.id.num2Ll, num2Ll);
        num1LlRg.addOnSelectChange(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSelectChange(this, infoBindHandler, com.codingtu.cooltu.R.id.num1Ll));
        link(infoBindHandler.linkMap, com.codingtu.cooltu.R.id.num1Ll, num1Ll);
        timeSb.setOnSeekBarChangeListener(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSeekBarChangeListener(this, infoBindHandler, com.codingtu.cooltu.R.id.timeSb));
        heightSb.setOnSeekBarChangeListener(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSeekBarChangeListener(this, infoBindHandler, com.codingtu.cooltu.R.id.heightSb));
        passwordTv.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(this, infoBindHandler, com.codingtu.cooltu.R.id.passwordTv));

        if (!initInfo) {
            infoBindConfig.idEcho(info, idEt, nameEt);
            com.codingtu.cooltu.lib4a.tools.ViewTool.setEditTextAndSelection(nameEt, info.name);
            com.codingtu.cooltu.lib4a.tools.ViewTool.setEditTextAndSelection(nicknameEt, info.nickname);
            infoBindConfig.ageEcho(info, ageEt);
            infoBindConfig.addressEcho(info, provinceEt, cityEt, areaEt);
            numLlRg.setSelected(numLlRg.getIndex(info.num));
            num2LlRg.setSelected(num2LlRg.getIndex(info.num));
            infoBindConfig.timeEcho(info, timeSb);
            heightSb.setProgress(info.height);
            com.codingtu.cooltu.lib4a.tools.ViewTool.setText(passwordTv, info.password);

        }
        numLlRg.setSelected(1);
        num2LlRg.setSelected(1);

        if (form == null) {
            form = new com.codingtu.cooltu.bean.Form();
            initForm = true;
        }
        formBindHandler = new FormBindHandler(form, formBindConfig);

        if (!initForm) {

        }


    }

    protected void beforeInitBindView() {}
    public static class InfoBindHandler extends android.os.Handler {
        private com.codingtu.cooltu.bean.Info info;
        private com.codingtu.cooltu.bind.InfoBindConfig infoBindConfig;
        private com.codingtu.cooltu.lib4j.data.map.ListValueMap<Integer, Object> linkMap = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

        public InfoBindHandler(com.codingtu.cooltu.bean.Info info, com.codingtu.cooltu.bind.InfoBindConfig infoBindConfig) {
            this.info = info;
            this.infoBindConfig = infoBindConfig;
        }
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            List<Object> linkObjs = linkMap.get(msg.what);
            switch (msg.what) {
                case com.codingtu.cooltu.R.id.idTv:
                    infoBindConfig.handleId(info, (android.widget.EditText) linkObjs.get(0), (android.widget.EditText) linkObjs.get(1));
                    break;
                case com.codingtu.cooltu.R.id.idEt:
                    infoBindConfig.handleId(info, (android.widget.EditText) linkObjs.get(0), (android.widget.EditText) linkObjs.get(1));
                    break;
                case com.codingtu.cooltu.R.id.id1Et:
                    infoBindConfig.handleId(info, (android.widget.EditText) linkObjs.get(0), (android.widget.EditText) linkObjs.get(1));
                    break;
                case com.codingtu.cooltu.R.id.nameEt:
                    infoBindConfig.handleView(info, (android.widget.EditText) linkObjs.get(0));
                    break;
                case com.codingtu.cooltu.R.id.nicknameEt:
                    info.nickname = (String) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.ageEt:
                    info.age = infoBindConfig.toAge(msg.obj);
                    break;
                case com.codingtu.cooltu.R.id.age1Et:
                    info.age = infoBindConfig.toAge(msg.obj);
                    break;
                case com.codingtu.cooltu.R.id.provinceEt:
                    infoBindConfig.handleProvince(info);
                    break;
                case com.codingtu.cooltu.R.id.cityEt:
                    infoBindConfig.handleProvince(info);
                    break;
                case com.codingtu.cooltu.R.id.areaEt:
                    infoBindConfig.handleProvince(info);
                    break;
                case com.codingtu.cooltu.R.id.numLl:
                    infoBindConfig.num = com.codingtu.cooltu.lib4a.tools.ViewTool.getRadioGroupItem((android.view.ViewGroup) linkObjs.get(0), (int) msg.obj);
                    break;
                case com.codingtu.cooltu.R.id.num2Ll:
                    infoBindConfig.num = com.codingtu.cooltu.lib4a.tools.ViewTool.getRadioGroupItem((android.view.ViewGroup) linkObjs.get(0), (int) msg.obj);
                    break;
                case com.codingtu.cooltu.R.id.num1Ll:
                    infoBindConfig.num1 = (int) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.timeSb:
                    info.time = infoBindConfig.toTime(msg.obj);
                    break;
                case com.codingtu.cooltu.R.id.heightSb:
                    infoBindConfig.height = (int) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.passwordTv:
                    info.password = (String) msg.obj;
                    break;

            }
        }
    }
    protected boolean checkInfo() {
        if (info.id < 0) {
            toast("请输入正确的id");
            return false;
        }
        if (!infoBindConfig.checkId(info, idEt)) {
            toast("嘻嘻嘻");
            return false;
        }
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isBlank(info.name)) {
            toast("请输入姓名");
            return false;
        }

        return true;
    }
    public static class FormBindHandler extends android.os.Handler {
        private com.codingtu.cooltu.bean.Form form;
        private com.codingtu.cooltu.bind.FormBindConfig formBindConfig;
        private com.codingtu.cooltu.lib4j.data.map.ListValueMap<Integer, Object> linkMap = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

        public FormBindHandler(com.codingtu.cooltu.bean.Form form, com.codingtu.cooltu.bind.FormBindConfig formBindConfig) {
            this.form = form;
            this.formBindConfig = formBindConfig;
        }
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            List<Object> linkObjs = linkMap.get(msg.what);
            switch (msg.what) {

            }
        }
    }
    protected boolean checkForm() {

        return true;
    }
    public void link(com.codingtu.cooltu.lib4j.data.map.ListValueMap<Integer, Object> linkMap, int handleId, Object... linkViews) {
        linkMap.get(handleId).addAll(com.codingtu.cooltu.lib4j.ts.Ts.ts(linkViews).toList());
    }




}

