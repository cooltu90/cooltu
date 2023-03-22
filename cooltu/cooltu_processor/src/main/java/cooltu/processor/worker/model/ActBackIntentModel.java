package cooltu.processor.worker.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

import cooltu.constant.Constant;
import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.tools.StringTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import cooltu.processor.annotation.ui.ActBack;
import cooltu.processor.lib.ls.EachType;
import cooltu.processor.lib.ls.TypeLs;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.lib.tools.ParamTools;
import cooltu.processor.modelinterface.ActBackIntentModelInterface;
import cooltu.processor.worker.model.base.SingleCoreToolsBaseModel;

public class ActBackIntentModel extends SingleCoreToolsBaseModel implements ActBackIntentModelInterface {

    public static final ActBackIntentModel model = new ActBackIntentModel();

    private List<ExecutableElement> ees = new ArrayList<>();
    private HashMap<String, String> names = new HashMap<>();

    public ActBackIntentModel() {
        super(Constant.NAME_ACT_BACK_INTENT);
    }


    public void add(ExecutableElement ee) {
        ees.add(ee);
    }


    @Override
    public void setTagFor_methods(StringBuilder sb) {
        Ts.ls(ees, new Each<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement ee) {
                List<? extends VariableElement> parameters = ee.getParameters();
                if (CountTool.isNull(parameters)) {
                    return false;
                }

                ActBack actBack = ee.getAnnotation(ActBack.class);
                String fullName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                    @Override
                    public Object get() {
                        return actBack.value();
                    }
                });
                JavaInfo javaInfo = NameTools.getJavaInfoByName(fullName);
                String methodName = NameTools.toMethodType(javaInfo.name.replace(Constant.SUFFIX_ACTIVITY, ""));
                String params = ParamTools.getDefaultParam(ee).getParams();
                String id = methodName + params;
                if (StringTool.isNotBlank(names.get(id))) {
                    return false;
                }

                names.put(id, id);

                addLnTag(sb, "    public static Intent [methodName]([params]) {", methodName, params);
                addLnTag(sb, "        Intent intent = new Intent();");

                TypeLs.ls(parameters, new EachType() {
                    @Override
                    public void each(int position, String type, String name) {
                        String line = null;
                        if (ClassTool.isBaseClass(type)) {
                            line = "        intent.putExtra(Pass.[staticName], [name]);";
                        } else {
                            line = "        intent.putExtra(Pass.[staticName], [name].toJson());";
                        }
                        addLnTag(sb, line, NameTools.toStaticType(name), name);
                    }
                });
                addLnTag(sb, "        return intent;");
                addLnTag(sb, "    }");

                return false;
            }
        });
    }

}
/* model_temp_start
package core.tools;

import android.content.Intent;

public class ActBackIntent {

[[methods]]

}

model_temp_end */