package cooltu.processor.worker.model;


import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;

import cooltu.constant.Constant;
import cooltu.constant.FullName;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import cooltu.processor.annotation.permission.Permission;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.modelinterface.PermissionModelInterface;
import cooltu.processor.worker.model.base.SingleCoreToolsBaseModel;

public class PermissionModel extends SingleCoreToolsBaseModel implements PermissionModelInterface {

    public static final PermissionModel model = new PermissionModel();

    private int code = 0;

    public List<ExecutableElement> elements = new ArrayList<>();

    public PermissionModel() {
        super(Constant.NAME_PERMISSION);
    }

    @Override
    public void setTagFor_fields(StringBuilder fieldsSb) {
        Ts.ls(elements, new Each<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                addLnTag(fieldsSb,
                        "    public static final int CODE_[name] = [num];",
                        NameTools.toStaticType(NameTools.simpleName(element)),
                        code++);
                return false;
            }
        });
    }

    @Override
    public void setTagFor_methods(StringBuilder methodsSb) {
        Ts.ls(elements, new Each<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                String simpleName = NameTools.simpleName(element);
                addLnTag(methodsSb, "    public static void [name](Activity act) {", simpleName);
                Permission permission = element.getAnnotation(Permission.class);
                String[] value = permission.value();
                StringBuilder sb = new StringBuilder();
                Ts.ls(value, new Each<String>() {
                    @Override
                    public boolean each(int position, String s) {
                        sb.append(", \"").append(s).append("\"");
                        return false;
                    }
                });
                addLnTag(methodsSb, "        [tool].check(act, CODE_[name] [params]);", FullName.PERMISSION_TOOL, NameTools.toStaticType(simpleName), sb.toString());
                addLnTag(methodsSb, "    }");

                return false;
            }
        });
    }
}
/* model_temp_start
package core.tools;

import android.Manifest;
import android.app.Activity;

public class Permissions {

[[fields]]

[[methods]]

}
model_temp_end */
