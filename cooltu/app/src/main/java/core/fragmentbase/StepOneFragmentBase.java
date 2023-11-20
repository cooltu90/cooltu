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

public class StepOneFragmentBase extends com.codingtu.cooltu.lib4a.ui.fragment.CoreFragment implements View.OnClickListener, com.codingtu.cooltu.lib4a.net.netback.NetBackI {
    protected android.widget.TextView tv1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = com.codingtu.cooltu.lib4a.tools.InflateTool.inflate(inflater, com.codingtu.cooltu.R.layout.fragment_step_one, container);
        tv1 = view.findViewById(com.codingtu.cooltu.R.id.tv1);
        return view;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void accept(String code, Result<ResponseBody> result, com.codingtu.cooltu.lib4a.net.bean.CoreSendParams params, List objs) {

    }

}
