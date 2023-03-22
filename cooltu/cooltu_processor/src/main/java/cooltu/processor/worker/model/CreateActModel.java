package cooltu.processor.worker.model;

import cooltu.constant.Constant;
import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.processor.modelinterface.CreateActModelInterface;
import cooltu.processor.worker.model.base.BaseModel;

public class CreateActModel extends BaseModel implements CreateActModelInterface {
    private String layoutBaseName;
    private JavaInfo actBaseInfo;

    public CreateActModel(JavaInfo info, String layoutBaseName, JavaInfo actBaseInfo) {
        super(info);
        this.layoutBaseName = layoutBaseName;
        this.actBaseInfo = actBaseInfo;
    }

    /***************************************
     *
     *
     *
     ***************************************/

    @Override
    public void setTagFor_pkg(StringBuilder sb) {
        addTag(sb, info.pkg);
    }

    @Override
    public void setTagFor_rPkg(StringBuilder sb) {
        addTag(sb, Constant.PKG_DEFAULT_R);
    }

    @Override
    public void setTagFor_imports(StringBuilder sb) {
        addTag(sb, "import " + actBaseInfo.fullName + ";");
    }

    @Override
    public void setTagFor_baseName(StringBuilder sb) {
        addTag(sb, layoutBaseName);
    }

    @Override
    public void setTagFor_name(StringBuilder sb) {
        addTag(sb, info.name);
    }

    @Override
    public void setTagFor_base(StringBuilder sb) {
        addTag(sb, actBaseInfo.name);
    }

}
/* model_temp_start
package [[pkg]];

import android.os.Bundle;

import androidx.annotation.Nullable;

import [[rPkg]].R;

[[imports]]
import core.processor.annotation.ui.ActBase;
import core.processor.annotation.ui.Start;

@Start
@ActBase(layout = R.layout.activity_[[baseName]])
public class [[name]] extends [[base]] {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
model_temp_end */
