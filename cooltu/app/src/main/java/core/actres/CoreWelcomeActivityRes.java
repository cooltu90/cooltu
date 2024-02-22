package core.actres;

import android.widget.RelativeLayout;

import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.NoStart;
import com.codingtu.cooltu.ui.base.CoreWelcomeActivity;

@NoStart
@ResFor(CoreWelcomeActivity.class)
public class CoreWelcomeActivityRes {

    @InBase
    RelativeLayout tv3;

    @InBase
    boolean isTest;

}
