package core.fragmentbase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class StepOneFragmentBase extends com.codingtu.cooltu.lib4a.ui.fragment.CoreFragment implements View.OnClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
    protected androidx.recyclerview.widget.RecyclerView rv;
    protected android.widget.TextView tv1;
    protected int textColor;
    protected int tvColor;
    protected int dp;
    protected int dp1;
    protected com.codingtu.cooltu.ui.adapter.CatAdapter catAdapter;
    protected com.codingtu.cooltu.ui.adapter.DogAdapter dogAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = com.codingtu.cooltu.lib4a.tools.InflateTool.inflate(inflater, com.codingtu.cooltu.R.layout.fragment_step_one, container);
        rv = view.findViewById(com.codingtu.cooltu.R.id.rv);
        tv1 = view.findViewById(com.codingtu.cooltu.R.id.tv1);
        tv1.setOnClickListener(this);
        textColor = android.graphics.Color.parseColor("#ffffff");
        tvColor = com.codingtu.cooltu.lib4a.tools.ResourceTool.getColor(com.codingtu.cooltu.R.color.black);
        dp = com.codingtu.cooltu.lib4a.tools.MobileTool.dpToPx(12.5f);
        dp1 = com.codingtu.cooltu.lib4a.tools.ResourceTool.getDimen(com.codingtu.cooltu.R.dimen.xxx);

        // catAdapter
        catAdapter = new com.codingtu.cooltu.ui.adapter.CatAdapter();
        catAdapter.setVH(core.vh.CatVH.class);
        catAdapter.setClick(this);
        rv.setAdapter(catAdapter);
        rv.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getAct()));
        // dogAdapter
        dogAdapter = new com.codingtu.cooltu.ui.adapter.DogAdapter() {
            @Override
            protected void loadMore(int page) {
                dogAdapterLoadMore(page);
            }
        };
        dogAdapter.setVH(core.vh.DogVH.class);
        dogAdapter.setClick(this);
        rv.setAdapter(dogAdapter);
        rv.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getAct()));
        return view;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.tv1:
                tv1Click(
                        (com.codingtu.cooltu.bean.User) v.getTag(com.codingtu.cooltu.lib4a.R.id.tag_0)
                );
                break;

        }
    }
    protected void tv1Click(com.codingtu.cooltu.bean.User user) {}

    protected abstract void dogAdapterLoadMore(int page);

    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {


        if ("getObjBack".equals(code)) {
            new core.net.back.GetObjBack() {
                @Override
                public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {
                    super.accept(code, result, params, objs);
                    getObjBack(user);
                }
            }.accept(code, result, params, objs);
        }

    }
    protected void getObjBack(com.codingtu.cooltu.bean.User user) {}

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == android.app.Activity.RESULT_OK) {

        }
    }


}
