package core.fragmentres;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.constant.AdapterType;
import com.codingtu.cooltu.processor.annotation.res.ColorRes;
import com.codingtu.cooltu.processor.annotation.res.ColorStr;
import com.codingtu.cooltu.processor.annotation.res.Dimen;
import com.codingtu.cooltu.processor.annotation.res.Dp;
import com.codingtu.cooltu.processor.annotation.res.ResForFragment;
import com.codingtu.cooltu.processor.annotation.ui.Adapter;
import com.codingtu.cooltu.ui.StepOneFragment;
import com.codingtu.cooltu.ui.adapter.CatAdapter;
import com.codingtu.cooltu.ui.adapter.DogAdapter;

@ResForFragment(StepOneFragment.class)
public class StepOneFragmentRes {

    @ColorStr("#ffffff")
    int textColor;

    @ColorRes(R.color.black)
    int tvColor;

    @Dp(12.5f)
    int dp;

    @Dimen(R.dimen.xxx)
    int dp1;

    @Adapter
    CatAdapter catAdapter;
    @Adapter(type = AdapterType.DEFAULT_MORE_LIST)
    DogAdapter dogAdapter;
}
