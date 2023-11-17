package core.actbase;

import android.view.View;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class StepOneActivityBase extends com.codingtu.cooltu.lib4a.ui.act.CoreActivity implements View.OnClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
    protected androidx.recyclerview.widget.RecyclerView rv;
    protected com.codingtu.cooltu.ui.adapter.CatAdapter catAdapter;
    protected com.codingtu.cooltu.ui.adapter.DogAdapter dogAdapter;


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.codingtu.cooltu.R.layout.activity_step_one);

        rv = findViewById(com.codingtu.cooltu.R.id.rv);








        catAdapter = new com.codingtu.cooltu.ui.adapter.CatAdapter();
        catAdapter.setVH(core.vh.CatVH.class);
        catAdapter.setClick(this);
        rv.setAdapter(catAdapter);
        rv.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));



        onCreateComplete();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        }
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


}

