package cooltu.processor.worker.model;


import cooltu.constant.Constant;
import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.processor.modelinterface.CreateFragmentModelInterface;
import cooltu.processor.worker.model.base.BaseModel;

public class CreateFragmentModel extends BaseModel implements CreateFragmentModelInterface {
    private final String layoutBaseName;
    private final JavaInfo fragmentBaseInfo;

    public CreateFragmentModel(JavaInfo info, String layoutBaseName, JavaInfo fragmentBaseInfo) {
        super(info);
        this.layoutBaseName = layoutBaseName;
        this.fragmentBaseInfo = fragmentBaseInfo;
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
        addTag(sb, "import " + fragmentBaseInfo.fullName + ";");
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
        addTag(sb, fragmentBaseInfo.name);
    }
}
/* model_temp_start
package [[pkg]];

import [[rPkg]].R;
[[imports]]

import core.processor.annotation.ui.FragmentBase;

@FragmentBase(layout = R.layout.fragment_[[baseName]])
public class [[name]] extends [[base]] {

}
model_temp_end */