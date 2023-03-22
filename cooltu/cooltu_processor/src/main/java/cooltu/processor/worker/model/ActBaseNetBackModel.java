package cooltu.processor.worker.model;


import javax.lang.model.element.ExecutableElement;

import cooltu.constant.Constant;
import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.StringTool;
import cooltu.processor.annotation.net.NetBack;
import cooltu.processor.lib.ls.EachType;
import cooltu.processor.lib.ls.TypeLs;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.modelinterface.ActBaseNetBackModelInterface;
import cooltu.processor.worker.model.base.SubBaseModel;

public class ActBaseNetBackModel extends SubBaseModel implements ActBaseNetBackModelInterface {

    private boolean hasNetBack;
    private ExecutableElement netBackElement;
    private String methodName;
    private String methodBaseName;

    public ActBaseNetBackModel(boolean hasNetBack, ExecutableElement element) {
        this.hasNetBack = hasNetBack;
        netBackElement = element;
        methodName = NameTools.simpleName(netBackElement);
        methodBaseName = NameTools.getTypeNameCutSuffix(methodName, Constant.SUFFIX_NET_BACK);
        info = NameTools.getNetBackInfo(methodBaseName);
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    @Override
    public void setTagFor_backClass(StringBuilder sb) {
        addTag(sb, NameTools.getNetBackInfo(methodBaseName).fullName);
    }

    @Override
    public void setTagFor_backName(StringBuilder sb) {
        addTag(sb, methodName);
    }

    @Override
    public void setTagFor_params(StringBuilder sb) {
        NetBack netBack = netBackElement.getAnnotation(NetBack.class);
        boolean mock = netBack.mock();
        String mockName = Constant.PKG_MOCK + "." + NameTools.toClassType(methodBaseName) + Constant.SUFFIX_MOCK;

        //params
        TypeLs.ls(netBackElement.getParameters(), new EachType() {
            @Override
            public void each(int position, String type, String name) {
                if (position != 0) {
                    sb.append(", ");
                }
                //core.tools.net.params.GetUserByIdParams
                JavaInfo paramsInfo = NameTools.getSendParamsInfo(methodBaseName);

                if (type.equals(info.fullName)) {
                    sb.append("this");
                } else if (ClassTool.isType(type, String.class)) {
                    if (mock) {
                        addTag(sb, "new [name]().json", mockName);
                    } else {
                        sb.append("json");
                    }
                } else if (ClassTool.isList(type)) {
                    String beanType = StringTool.getSub(type, "List", "<", ">");
                    if (ClassTool.isObject(beanType)) {
                        sb.append("objs");
                    } else {
                        JavaInfo info1 = NameTools.getJavaInfoByName(beanType);

                        if (mock) {
                            addTag(sb, "new [name]().[user]s", mockName, NameTools.toMethodType(info1.name));
                        } else {
                            sb.append(NameTools.toMethodType(info1.name)).append("s");
                        }
                    }
                } else if (type.equals(paramsInfo.fullName)) {
                    addTag(sb, "([params]) params", type);
                } else {
                    JavaInfo info1 = NameTools.getJavaInfoByName(type);
                    if (mock) {
                        addTag(sb, "new [name]().[user]", mockName, NameTools.toMethodType(info1.name));
                    } else {
                        sb.append(NameTools.toMethodType(info1.name));
                    }
                }
            }
        });
    }

    @Override
    public void setTagFor_if(StringBuilder sb) {
        addIf(hasNetBack);
    }

}
/* model_temp_start
        [[if]] ("[[backName]]".equals(code)) {
            [[backClass]] back = new [[backClass]]() {
                @Override
                public void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs) {
                    super.accept(code, result, params, objs);
                    [[backName]]([[params]]);
                }
            };
            back.accept(code, result, params, objs);
        }
model_temp_end */