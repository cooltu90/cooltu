package core.actres;

import com.codingtu.cooltu.bean.Forms;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.ui.FormActivity;

@ResFor(FormActivity.class)
public class FormActivityRes {

    @StartGroup
    Forms forms;

}
