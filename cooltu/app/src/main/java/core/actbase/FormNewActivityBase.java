package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormNewActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.LinearLayout numLl;
    protected android.widget.TextView saveBt1;
    protected android.widget.EditText nicknameEt;
    protected android.widget.LinearLayout num1Ll;
    protected android.widget.TextView saveBt2;
    protected android.widget.EditText nameEt;
    protected android.widget.EditText cityEt;
    protected android.widget.EditText areaEt;
    protected android.widget.EditText ageEt;
    protected android.widget.EditText provinceEt;
    protected android.widget.EditText idEt;
    protected com.codingtu.cooltu.bean.Info info;
    protected com.codingtu.cooltu.bind.InfoBindConfig infoBindConfig;
    protected boolean initInfo;
    protected InfoBindHandler infoBindHandler;
    protected com.codingtu.cooltu.lib4a.view.combine.RadioGroup numRg;
    protected com.codingtu.cooltu.lib4a.view.combine.RadioGroup num1Rg;
    protected com.codingtu.cooltu.bean.Form form;
    protected com.codingtu.cooltu.bind.FormBindConfig formBindConfig;
    protected boolean initForm;
    protected FormBindHandler formBindHandler;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_form_new);

        numLl = findViewById(com.codingtu.cooltu.R.id.numLl);
        saveBt1 = findViewById(com.codingtu.cooltu.R.id.saveBt1);
        nicknameEt = findViewById(com.codingtu.cooltu.R.id.nicknameEt);
        num1Ll = findViewById(com.codingtu.cooltu.R.id.num1Ll);
        saveBt2 = findViewById(com.codingtu.cooltu.R.id.saveBt2);
        nameEt = findViewById(com.codingtu.cooltu.R.id.nameEt);
        cityEt = findViewById(com.codingtu.cooltu.R.id.cityEt);
        areaEt = findViewById(com.codingtu.cooltu.R.id.areaEt);
        ageEt = findViewById(com.codingtu.cooltu.R.id.ageEt);
        provinceEt = findViewById(com.codingtu.cooltu.R.id.provinceEt);
        idEt = findViewById(com.codingtu.cooltu.R.id.idEt);






        initBindView();

        onCreateComplete();

    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        }
    }



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
        com.codingtu.cooltu.form.TypeOnSetItem typeOnSetItem = new com.codingtu.cooltu.form.TypeOnSetItem();
        numRg = com.codingtu.cooltu.lib4a.view.combine.RadioGroup.obtain(this).setBts(numLl).setOnSetItem(typeOnSetItem).setItems("1", "2", "3");
        numLl.setTag(com.codingtu.cooltu.lib4a.R.id.tag_0, numRg);
        num1Rg = com.codingtu.cooltu.lib4a.view.combine.RadioGroup.obtain(this).setBts(num1Ll).setOnSetItem(typeOnSetItem);
        num1Ll.setTag(com.codingtu.cooltu.lib4a.R.id.tag_0, num1Rg);

        beforeInitBindView();
        if (info == null) {
            info = new com.codingtu.cooltu.bean.Info();
            infoBindConfig = new com.codingtu.cooltu.bind.InfoBindConfig();
            initInfo = true;
        }
        infoBindHandler = new InfoBindHandler(info, infoBindConfig);
        com.codingtu.cooltu.lib4a.bind.BindTool.bindEt(this, idEt, infoBindHandler);
        com.codingtu.cooltu.lib4a.bind.BindTool.bindEt(this, nameEt, infoBindHandler);
        link(infoBindHandler.linkMap, com.codingtu.cooltu.R.id.nameEt, nicknameEt);
        com.codingtu.cooltu.lib4a.bind.BindTool.bindEt(this, nicknameEt, infoBindHandler);
        infoBindConfig.bindAgeEt(this, ageEt, infoBindHandler);
        com.codingtu.cooltu.lib4a.bind.BindTool.bindEt(this, provinceEt, infoBindHandler);
        com.codingtu.cooltu.lib4a.bind.BindTool.bindEt(this, cityEt, infoBindHandler);
        com.codingtu.cooltu.lib4a.bind.BindTool.bindEt(this, areaEt, infoBindHandler);
        numRg.addOnSelectChange(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSelectChange(infoBindHandler, com.codingtu.cooltu.R.id.numLl));
        num1Rg.addOnSelectChange(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSelectChange(infoBindHandler, com.codingtu.cooltu.R.id.num1Ll));

        if (!initInfo) {
            infoBindConfig.idEcho(info.id, idEt);
            com.codingtu.cooltu.lib4a.tools.ViewTool.setEditTextAndSelection(nameEt, info.name);
            com.codingtu.cooltu.lib4a.tools.ViewTool.setEditTextAndSelection(nicknameEt, info.nickname);
            infoBindConfig.ageEcho(info.age, ageEt);
            infoBindConfig.addressEcho(info.address, provinceEt, cityEt, areaEt);
            numRg.setSelected(numRg.getIndex(info.num));
            num1Rg.setSelected(info.num1);

        }
        if (form == null) {
            form = new com.codingtu.cooltu.bean.Form();
            formBindConfig = new com.codingtu.cooltu.bind.FormBindConfig();
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
                case com.codingtu.cooltu.R.id.idEt:
                    infoBindConfig.id = infoBindConfig.parseLong(msg.obj);
                    info.id = infoBindConfig.id;
                    break;
                case com.codingtu.cooltu.R.id.nameEt:
                    infoBindConfig.name = (String) msg.obj;
                    info.name = infoBindConfig.name;
                    infoBindConfig.handleView(info, (android.widget.EditText) linkObjs.get(0));
                    break;
                case com.codingtu.cooltu.R.id.nicknameEt:
                    infoBindConfig.nickname = (String) msg.obj;
                    info.nickname = infoBindConfig.nickname;
                    break;
                case com.codingtu.cooltu.R.id.ageEt:
                    infoBindConfig.age = infoBindConfig.toAge(msg.obj);
                    info.age = infoBindConfig.age;
                    break;
                case com.codingtu.cooltu.R.id.provinceEt:
                    infoBindConfig.province = (String) msg.obj;
                    infoBindConfig.handleProvince(info);
                    break;
                case com.codingtu.cooltu.R.id.cityEt:
                    infoBindConfig.city = (String) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.areaEt:
                    infoBindConfig.area = (String) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.numLl:
                    info.num = infoBindConfig.num;
                    break;
                case com.codingtu.cooltu.R.id.num1Ll:
                    info.num1 = infoBindConfig.num1;
                    break;

            }
        }
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
    public void link(com.codingtu.cooltu.lib4j.data.map.ListValueMap<Integer, Object> linkMap, int handleId, Object... linkViews) {
        linkMap.get(handleId).addAll(com.codingtu.cooltu.lib4j.ts.Ts.ts(linkViews).toList());
    }



}

