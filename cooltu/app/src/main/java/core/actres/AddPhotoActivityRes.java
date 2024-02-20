package core.actres;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.view.dialogview.EditDialog;
import com.codingtu.cooltu.lib4a.view.dialogview.MenuDialog;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.MenuDialogItem;
import com.codingtu.cooltu.processor.annotation.ui.dialog.MenuDialogUse;
import com.codingtu.cooltu.ui.AddPhotoActivity;

@ResFor(AddPhotoActivity.class)
public class AddPhotoActivityRes {

    @EditDialogUse(
            title = "xxx",
            hint = "xxx"
    )
    EditDialog dialog;


    @MenuDialogUse(
            title = "操作1",
            items = {
                    @MenuDialogItem(id = R.id.reportTv, name = "导出工单"),
                    @MenuDialogItem(id = R.id.deleteItemBt, name = "删除"),
                    @MenuDialogItem(id = R.id.deleteItemBt1, name = "删除1"),
            },
            objType = String.class

    )
    MenuDialog menuDialog;
}
