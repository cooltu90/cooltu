package core.actres;

import com.codingtu.cooltu.constant.AdapterType;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.Adapter;
import com.codingtu.cooltu.processor.annotation.ui.dialog.ToastDialogUse;
import com.codingtu.cooltu.ui.StepOneActivity;
import com.codingtu.cooltu.ui.adapter.CatAdapter;
import com.codingtu.cooltu.ui.adapter.DogAdapter;

@ToastDialogUse
@ResFor(StepOneActivity.class)
public class StepOneActivityRes {

    @Adapter
    CatAdapter catAdapter;
    @Adapter(type = AdapterType.DEFAULT_MORE_LIST)
    DogAdapter dogAdapter;

}
