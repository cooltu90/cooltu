package core.actres;

import com.codingtu.cooltu.lib4a.view.dialogview.EditDialog;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.ui.AddPhotoActivity;

@ResFor(AddPhotoActivity.class)
public class AddPhotoActivityRes {

    @EditDialogUse(
            title = "xxx",
            hint = "xxx"
    )
    EditDialog dialog;

}
