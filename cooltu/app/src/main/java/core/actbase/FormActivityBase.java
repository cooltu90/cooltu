package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class FormActivityBase extends com.codingtu.cooltu.ui.base.BaseActivity implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.LinearLayout numLl;
    protected android.widget.EditText nicknameEt;
    protected android.widget.TextView saveBt;
    protected android.widget.EditText nameEt;
    protected FormHandler formHandler;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_form);

        numLl = findViewById(com.codingtu.cooltu.R.id.numLl);
        nicknameEt = findViewById(com.codingtu.cooltu.R.id.nicknameEt);
        saveBt = findViewById(com.codingtu.cooltu.R.id.saveBt);
        nameEt = findViewById(com.codingtu.cooltu.R.id.nameEt);






        onCreateComplete();

    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();

        saveBt.setOnClickListener(this);



        formHandler = new FormHandler(this, this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.saveBt:
                saveBtClick(
                );
                break;

        }
    }

    protected void saveBtClick() {}


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








    public static class FormHandler extends android.os.Handler implements com.codingtu.cooltu.lib4j.destory.OnDestroy {
        public java.util.Map<Integer, java.util.Map<String, Object[]>> linkMap = new java.util.HashMap<>();
        private FormActivityBase actBase;
        public FormHandler(com.codingtu.cooltu.lib4j.destory.Destroys destroys, FormActivityBase actBase) {
            destroys.add(this);
            this.actBase = actBase;
        }
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            java.util.Map<String, Object[]> links = linkMap.get(msg.what);
            actBase.handleMessage(msg, links);
        }
        @Override
        public void destroy() {
            if (linkMap != null) {
                for (Integer index : linkMap.keySet()) {
                    java.util.Map<String, Object[]> map = linkMap.get(index);
                    for (String methodName :
                            map.keySet()) {
                        Object[] objects = map.get(methodName);
                        for (int i = 0; i < objects.length; i++) {
                            objects[i] = null;
                        }
                    }
                    map.clear();
                }
                linkMap.clear();
                linkMap = null;
            }
            actBase = null;
        }
    }
    public void link(java.util.Map<Integer, java.util.Map<String, Object[]>> linkMap, int handleId, String methodName, Object... linkViews) {
        java.util.Map<String, Object[]> map = linkMap.get(handleId);
        if (map == null) {
            map = new java.util.HashMap<>();
            linkMap.put(handleId, map);
        }
        map.put(methodName, linkViews);
    }
    protected void handleMessage(android.os.Message msg, java.util.Map<String, Object[]> links) {
        switch (msg.what) {
        }
    }
    protected void linkEditText(String methodName, android.widget.EditText et, Object... views) {
        et.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(this, formHandler, et.getId()));
        link(formHandler.linkMap, et.getId(), methodName, views);
    }
    protected com.codingtu.cooltu.lib4a.view.combine.RadioGroup getRadioGroup(android.view.ViewGroup viewGroup) {
        com.codingtu.cooltu.lib4a.view.combine.RadioGroup rg = com.codingtu.cooltu.lib4a.view.combine.RadioGroup.obtain(this).setBts(viewGroup);
        viewGroup.setTag(com.codingtu.cooltu.lib4a.R.id.tag_0, rg);
        return rg;
    }



}

