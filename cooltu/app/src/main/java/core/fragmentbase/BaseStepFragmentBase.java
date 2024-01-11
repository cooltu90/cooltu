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

public abstract class BaseStepFragmentBase extends com.codingtu.cooltu.lib4a.ui.fragment.CoreFragment implements View.OnClickListener, View.OnLongClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI{
    protected android.widget.TextView tv2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
        tv2.setOnClickListener(this);

        tv2.setOnLongClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.tv2:
                tv2Click(
                );
                break;

        }
    }
    protected void tv2Click() {}


    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case com.codingtu.cooltu.R.id.tv2:
                return tv2LongClick(
                );

        }

        return false;


    }
    protected boolean tv2LongClick() {return false;}




    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == android.app.Activity.RESULT_OK) {

        }
    }





}
