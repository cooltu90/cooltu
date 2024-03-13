package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class BindActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.LinearLayout numLl;
    protected android.widget.EditText nicknameEt;
    protected android.widget.EditText nameEt;
    protected android.widget.TextView submitBt;
    protected android.widget.SeekBar timeSeekBar;
    protected com.codingtu.cooltu.bean.Data data;
    protected com.codingtu.cooltu.bind.DataBindConfig dataBindConfig;
    protected boolean initData;
    protected DataBindHandler dataBindHandler;
    protected com.codingtu.cooltu.lib4a.view.combine.RadioGroup numLlRg;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_bind);

        numLl = findViewById(com.codingtu.cooltu.R.id.numLl);
        nicknameEt = findViewById(com.codingtu.cooltu.R.id.nicknameEt);
        nameEt = findViewById(com.codingtu.cooltu.R.id.nameEt);
        submitBt = findViewById(com.codingtu.cooltu.R.id.submitBt);
        timeSeekBar = findViewById(com.codingtu.cooltu.R.id.timeSeekBar);






        onCreateComplete();

    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();



        initBindView();

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
        dataBindConfig = new com.codingtu.cooltu.bind.DataBindConfig();
        com.codingtu.cooltu.form.TypeOnSetItem typeOnSetItem = new com.codingtu.cooltu.form.TypeOnSetItem();
        numLlRg = com.codingtu.cooltu.lib4a.view.combine.RadioGroup.obtain(this).setBts(numLl).setOnSetItem(typeOnSetItem).setItems("壹", "贰", "叁", "肆").setOnClick((index, view) -> dataBindConfig.onNumClick(data, index, view, numLl));
        numLl.setTag(com.codingtu.cooltu.lib4a.R.id.tag_0, numLlRg);

        beforeInitBindView();
        if (data == null) {
            data = new com.codingtu.cooltu.bean.Data();
            initData = true;
        }
        dataBindHandler = new DataBindHandler(data, dataBindConfig);
        nameEt.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(this, dataBindHandler, com.codingtu.cooltu.R.id.nameEt));
        timeSeekBar.setOnSeekBarChangeListener(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSeekBarChangeListener(this, dataBindHandler, com.codingtu.cooltu.R.id.timeSeekBar));
        numLlRg.addOnSelectChange(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSelectChange(this, dataBindHandler, com.codingtu.cooltu.R.id.numLl));
        link(dataBindHandler.linkMap, com.codingtu.cooltu.R.id.numLl, numLl);

        if (!initData) {
            echoData();
        }
        numLlRg.setSelected(1);


    }

    protected void beforeInitBindView() {}
    protected void echoData() {
        com.codingtu.cooltu.lib4a.tools.ViewTool.setEditTextAndSelection(nameEt, data.name);
        timeSeekBar.setProgress(data.time);
        numLlRg.setSelected(numLlRg.getIndex(data.num));

    }
    public static class DataBindHandler extends android.os.Handler {
        private com.codingtu.cooltu.bean.Data data;
        private com.codingtu.cooltu.bind.DataBindConfig dataBindConfig;
        private com.codingtu.cooltu.lib4j.data.map.ListValueMap<Integer, Object> linkMap = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();

        public DataBindHandler(com.codingtu.cooltu.bean.Data data, com.codingtu.cooltu.bind.DataBindConfig dataBindConfig) {
            this.data = data;
            this.dataBindConfig = dataBindConfig;
        }
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            List<Object> linkObjs = linkMap.get(msg.what);
            switch (msg.what) {
                case com.codingtu.cooltu.R.id.nameEt:
                    data.name = (String) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.timeSeekBar:
                    dataBindConfig.time = (int) msg.obj;
                    break;
                case com.codingtu.cooltu.R.id.numLl:
                    data.num = com.codingtu.cooltu.lib4a.tools.ViewTool.getRadioGroupItem((android.view.ViewGroup) linkObjs.get(0), (int) msg.obj);
                    break;

            }
        }
    }
    protected boolean checkData() {
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isBlank(data.name)) {
            toast("请输入名字");
            return false;
        }
        if (!dataBindConfig.checkName(data, nameEt)) {
            toast("请输入名字");
            return false;
        }

        return true;
    }
    public void link(com.codingtu.cooltu.lib4j.data.map.ListValueMap<Integer, Object> linkMap, int handleId, Object... linkViews) {
        linkMap.get(handleId).addAll(com.codingtu.cooltu.lib4j.ts.Ts.ts(linkViews).toList());
    }




}

