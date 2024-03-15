package core.actres;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.view.dialogview.Dialog;
import com.codingtu.cooltu.lib4a.view.dialogview.EditDialog;
import com.codingtu.cooltu.processor.annotation.res.ColorRes;
import com.codingtu.cooltu.processor.annotation.res.ColorStr;
import com.codingtu.cooltu.processor.annotation.res.Dimen;
import com.codingtu.cooltu.processor.annotation.res.Dp;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.processor.annotation.ui.dialog.DialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.NoticeDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.ToastDialogUse;
import com.codingtu.cooltu.ui.WelcomeActivity;

@ToastDialogUse
@NoticeDialogUse
@ResFor(WelcomeActivity.class)
public class WelcomeActivityRes {

    @StartGroup({1, 2})
    String name;
    @StartGroup({1})
    int age;
    @StartGroup({1})
    boolean isTest;


    @ColorStr("#ffffff")
    int textColor;

    @ColorRes(R.color.black)
    int tvColor;

    @Dp(12.5f)
    int dp;

    @Dimen(R.dimen.xxx)
    int dp1;

    @DialogUse(
            title = "提示",
            content = "胜多负少的"
    )
    Dialog dialog;

    @EditDialogUse(
            title = "提示",
            hint = "请输入文字"
    )
    EditDialog editDialog;

}
