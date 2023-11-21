package core.fragmentres;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.processor.annotation.res.ColorRes;
import com.codingtu.cooltu.processor.annotation.res.ColorStr;
import com.codingtu.cooltu.processor.annotation.res.ResForFragment;
import com.codingtu.cooltu.ui.StepOneFragment;

@ResForFragment(StepOneFragment.class)
public class StepOneFragmentRes {

    @ColorStr("#ffffff")
    int textColor;

    @ColorRes(R.color.black)
    int tvColor;
}
