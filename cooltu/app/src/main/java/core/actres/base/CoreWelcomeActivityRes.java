package core.actres.base;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codingtu.cooltu.processor.annotation.res.InBase;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.ui.base.CoreWelcomeActivity;

@ResFor(CoreWelcomeActivity.class)
public class CoreWelcomeActivityRes {

    @InBase
    RelativeLayout tv3;

}
