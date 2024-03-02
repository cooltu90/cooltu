package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormNewActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.TextView saveBt1;
    protected android.widget.EditText nicknameEt;
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
    protected com.codingtu.cooltu.bean.Form form;
    protected com.codingtu.cooltu.bind.FormBindConfig formBindConfig;
    protected boolean initForm;
    protected FormBindHandler formBindHandler;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_form_new);

        saveBt1 = findViewById(com.codingtu.cooltu.R.id.saveBt1);
        nicknameEt = findViewById(com.codingtu.cooltu.R.id.nicknameEt);
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
        beforeInitBindView();
        if (info == null) {
            info = new com.codingtu.cooltu.bean.Info();
            infoBindConfig = new com.codingtu.cooltu.bind.InfoBindConfig();
            initInfo = true;
        }
        infoBindHandler = new InfoBindHandler(info, infoBindConfig);
        com.codingtu.cooltu.lib4a.bind.BindTool.bindEt(this, idEt, infoBindHandler);
        com.codingtu.cooltu.lib4a.bind.BindTool.bindEt(this, nameEt, infoBindHandler);
        com.codingtu.cooltu.lib4a.bind.BindTool.bindEt(this, nicknameEt, infoBindHandler);
        infoBindConfig.bindAgeEt(this, ageEt, infoBindHandler);
        com.codingtu.cooltu.lib4a.bind.BindTool.bindEt(this, provinceEt, infoBindHandler);
        com.codingtu.cooltu.lib4a.bind.BindTool.bindEt(this, cityEt, infoBindHandler);
        com.codingtu.cooltu.lib4a.bind.BindTool.bindEt(this, areaEt, infoBindHandler);

        if (!initInfo) {

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
            switch (msg.what) {

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
            switch (msg.what) {

            }
        }
    }
    public void link(com.codingtu.cooltu.lib4j.data.map.ListValueMap<Integer, Object> linkMap, int handleId, Object... linkViews) {
        linkMap.get(handleId).addAll(com.codingtu.cooltu.lib4j.ts.Ts.ts(linkViews).toList());
    }



}

