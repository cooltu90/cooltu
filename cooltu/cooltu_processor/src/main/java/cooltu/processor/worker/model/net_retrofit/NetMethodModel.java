package cooltu.processor.worker.model.net_retrofit;


import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

import cooltu.constant.Constant;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.tools.StringTool;
import cooltu.processor.annotation.net.Default;
import cooltu.processor.annotation.net.Param;
import cooltu.processor.lib.bean.NetMethodDeal;
import cooltu.processor.lib.ls.TypeLs;
import cooltu.processor.lib.tools.ElementTools;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.modelinterface.NetMethodModelInterface;
import cooltu.processor.worker.model.base.SubBaseModel;

public class NetMethodModel extends SubBaseModel implements NetMethodModelInterface {
    private final ExecutableElement ee;
    private final String baseUrl;
    private final String apiTypeName;
    private final String methodName;
    private final List<? extends VariableElement> parameters;
    private final int parametersCount;
    private final NetMethodDeal netMethodDeal;

    public NetMethodModel(ExecutableElement ee, String baseUrl, String apiTypeName) {
        this.ee = ee;
        this.methodName = ElementTools.simpleName(ee);
        this.baseUrl = baseUrl;
        this.apiTypeName = apiTypeName;
        parameters = ee.getParameters();
        parametersCount = CountTool.count(parameters);
        netMethodDeal = new NetMethodDeal(ee);
    }

    @Override
    public void setTagFor_methodName(StringBuilder sb) {
        addTag(sb, methodName);
    }

    @Override
    public void setTagFor_params(StringBuilder sb) {
        TypeLs.ls(parameters, new TypeLs.EachTypePlus() {
            private int index = 0;

            @Override
            public void each(int position, VariableElement ve, String type, String name) {
                Default aDefault = ve.getAnnotation(Default.class);
                if (aDefault == null) {
                    if (index != 0) {
                        addTag(sb, " ,");
                    }
                    addTag(sb, "[type] [name]", type, name);
                    index++;
                }
            }
        });
    }

    @Override
    public void setTagFor_serviceName(StringBuilder sb) {
        addTag(sb, "core.tools.net.api." + apiTypeName + Constant.SUFFIX_API_SERVICE);
    }

    @Override
    public void setTagFor_methodBackName(StringBuilder sb) {
        addTag(sb, NameTools.toStaticType(methodName));
    }

    @Override
    public void setTagFor_baseUrl(StringBuilder sb) {
        if (StringTool.isNotBlank(netMethodDeal.getMethodBaseUrl())) {
            addTag(sb, "BASE_URL_[api]_[method]", NameTools.toStaticType(apiTypeName), NameTools.toStaticType(methodName));
        } else {
            if (StringTool.isBlank(baseUrl)) {
                addTag(sb, "core.lib4a.CoreConfigs.configs().getBaseUrl()");
            } else {
                addTag(sb, "BASE_URL_" + NameTools.toStaticType(apiTypeName));
            }
        }
    }

    @Override
    public void setTagFor_sendParamField(StringBuilder sb) {
        if (parametersCount > 0) {
            addTag(sb, "params");
        } else {
            addTag(sb, "null");
        }
    }

    @Override
    public void setTagFor_invokeParams(StringBuilder sb) {
        boolean isJsonBody = netMethodDeal.isJsonBody();
        TypeLs.ls(parameters, new TypeLs.EachTypePlus() {
            @Override
            public void each(int position, VariableElement ve, String type, String name) {
                Param param = ve.getAnnotation(Param.class);
                if (param == null && isJsonBody) {
                    return;
                }
                if (position == parametersCount - 1 && !isJsonBody) {
                    addTag(sb, "                        params.[id]", name);
                } else {
                    addLnTag(sb, "                        params.[id],", name);
                }
            }
        });
        if (isJsonBody) {
            addTag(sb, "                        NetTool.toJsonBody(jo.toJson())");
        }
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    @Override
    public void setTagFor_beforeReturn(StringBuilder sb) {
        if (parametersCount > 0) {
            String sendParamName = NameTools.toClassType(methodName) + "Params";
            addLnTag(sb, "        core.tools.net.params.[sendParamName] params = new core.tools.net.params.[sendParamName]();",
                    sendParamName, sendParamName);
        }


        TypeLs.ls(parameters, new TypeLs.EachTypePlus() {
            @Override
            public void each(int position, VariableElement ve, String type, String name) {
                Default aDefault = ve.getAnnotation(Default.class);
                if (aDefault != null) {
                    addLnTag(sb, "        params.[name] = [name];", name, xxx(aDefault, type));
                } else {
                    addLnTag(sb, "        params.[name] = [name];", name, name);
                }
            }
        });
    }

    private String xxx(Default aDefault, String type) {
        if (ClassTool.isString(type)) {
            return "\"" + aDefault.value() + "\"";
        }
        return aDefault.value();
    }

    @Override
    public void setTagFor_beforeCreate(StringBuilder sb) {

        if (parametersCount > 0) {
            String sendParamName = NameTools.toClassType(methodName) + "Params";
            addLnTag(sb, "                core.tools.net.params.[sendParamName] params = (core.tools.net.params.[sendParamName]) ps;",
                    sendParamName, sendParamName);
        }

        if (netMethodDeal.isJsonBody()) {
            addLnTag(sb, "                core.lib4a.json.base.JO jo = core.lib4a.json.JsonTool.createJO();");
            TypeLs.ls(ee.getParameters(), new TypeLs.EachTypePlus() {
                @Override
                public void each(int position, VariableElement ve, String type, String name) {
                    Param param = ve.getAnnotation(Param.class);
                    if (param == null) {
                        addLnTag(sb, "                jo.put(\"[taskName]\", params.[taskName]);", name, name);
                    }
                }
            });
        }
    }


}
/* model_temp_start

    public static API [[methodName]]([[params]]) {
[[beforeReturn]]
        return NetTool.api(new CreateApi() {
            @Override
            public Flowable<Result<ResponseBody>>
            create(Retrofit retrofit, CoreSendParams ps) {
[[beforeCreate]]
                return retrofit.create([[serviceName]].class).[[methodName]](
[[invokeParams]]
                );
            }
        }, [[methodBackName]], [[baseUrl]], [[sendParamField]]);
    }
model_temp_end */