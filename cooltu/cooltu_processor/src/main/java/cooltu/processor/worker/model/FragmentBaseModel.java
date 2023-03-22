package cooltu.processor.worker.model;

import cooltu.constant.FullName;
import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.StringTool;
import cooltu.processor.modelinterface.FragmentBaseModelInterface;
import cooltu.processor.worker.ModelType;

public class FragmentBaseModel extends BaseParentModel implements FragmentBaseModelInterface {

    public FragmentBaseModel(JavaInfo info) {
        super(info, false);
    }

    @Override
    public ModelType getModelType() {
        return ModelType.fragmentBase;
    }

    @Override
    protected String getFindViewByIdParent(String viParent) {
        return StringTool.isNotBlank(viParent) ? (viParent + ".") : "view.";
    }

    /***************************************
     *
     *
     *
     ***************************************/

    @Override
    public void setTagFor_InflateTool(StringBuilder sb) {
        addTag("InflateTool", FullName.INFLATE_TOOL);
    }

}
/* model_temp_start
package core.fragmentbase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import core.lib4a.net.bean.CoreSendParams;
import core.lib4a.net.netback.NetBackI;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class [[name]] extends [[base]] implements View.OnClickListener, NetBackI [[longClickListener]]{
[[field]]

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = [[InflateTool]].inflate(inflater, [[rPkg]].R.layout.[[layoutName]], container);
[[findView]]
[[setClick]]
[[setLongClick]]
[[onCreates]]
[[binds]]
[[resources]]
        onCreateView(view);
        return view;
    }

    public void onCreateView(View view) {}

    @Override
    public void onDestroy() {
        super.onDestroy();
[[onDestroys]]
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
[[onclicks]]
        }
    }
[[clickMethods]]

    public android.view.View.OnClickListener getOnClick() {
        return this;
    }

    @Override
    public void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs) {
[[netMethos]]
    }

[[netBackMethods]]

[[bindHandler]]

[[bindCheck]]

[[others]]

[[dialogs]]

[[toastDialog]]

[[editDialog]]

[[onLongClick]]

[[onLongClickMethods]]

}
model_temp_end */
