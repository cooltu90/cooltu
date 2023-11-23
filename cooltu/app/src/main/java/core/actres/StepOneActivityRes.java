package core.actres;

import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.constant.AdapterType;
import com.codingtu.cooltu.lib4a.constant.InputType;
import com.codingtu.cooltu.lib4a.view.dialogview.EditDialog;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.Adapter;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.NoticeDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.ToastDialogUse;
import com.codingtu.cooltu.ui.StepOneActivity;
import com.codingtu.cooltu.ui.adapter.CatAdapter;
import com.codingtu.cooltu.ui.adapter.DogAdapter;

@NoticeDialogUse
@ToastDialogUse
@ResFor(StepOneActivity.class)
public class StepOneActivityRes {

    @Adapter
    CatAdapter catAdapter;
    @Adapter(type = AdapterType.DEFAULT_MORE_LIST)
    DogAdapter dogAdapter;

    @EditDialogUse(
            title = "xxx",
            hint = "xxx",
            inputType = InputType.INT,
            objType = User.class,
            isUseTextWatcher = true,
            stopAnimation = true
    )
    EditDialog ed;

}
