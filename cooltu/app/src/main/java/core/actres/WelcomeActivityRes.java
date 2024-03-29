package core.actres;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.processor.annotation.res.ColorRes;
import com.codingtu.cooltu.processor.annotation.res.ColorStr;
import com.codingtu.cooltu.processor.annotation.res.Dimen;
import com.codingtu.cooltu.processor.annotation.res.Dp;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.ui.WelcomeActivity;

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

}
