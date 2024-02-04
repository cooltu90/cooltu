package core.actres;

import android.widget.EditText;

import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.ui.FormTestBaseActivity;

@ResFor(FormTestBaseActivity.class)
public class FormTestBaseActivityRes {

    @InBase
    EditText nameEt;
    @InBase
    EditText provinceEt;
    @InBase
    EditText cityEt;
    @InBase
    EditText areaEt;
    @InBase
    EditText ymdEt;
    @InBase
    EditText countEt;

}
