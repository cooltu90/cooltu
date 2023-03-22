package cooltu.processor.worker.model.net_retrofit;

import javax.lang.model.element.ExecutableElement;

import cooltu.constant.FullName;
import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.StringTool;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.modelinterface.NetBackModelInterface;
import cooltu.processor.worker.model.base.BaseModel;

public class NetBackModel extends BaseModel implements NetBackModelInterface {
    private ExecutableElement element;

    public NetBackModel(JavaInfo info, ExecutableElement element) {
        super(info);
        isForce = true;
        this.element = element;
    }

    /***************************************
     *
     *
     *
     ***************************************/
    @Override
    public void setTagFor_name(StringBuilder sb) {
        addTag(sb, info.name);
    }

    @Override
    public void setTagFor_returnTag(StringBuilder sb) {
        String typeName = element.getReturnType().toString();

        //back
        if (ClassTool.isType(typeName, void.class, Void.class)) {

        } else if (ClassTool.isList(typeName)) {
            String beanType = StringTool.getSub(typeName, "List", "<", ">");
            JavaInfo info = NameTools.getJavaInfoByName(beanType);
            String fieldName = NameTools.toMethodType(info.name) + "s";
            addTag(sb, "    public [type] [name];", typeName, fieldName);

        } else if (ClassTool.isBaseClass(typeName)) {

        } else {
            JavaInfo info = NameTools.getJavaInfoByName(typeName);
            String fieldName = NameTools.toMethodType(info.name);
            addTag(sb, "    public [type] [name];", typeName, fieldName);
        }
    }

    @Override
    public void setTagFor_back(StringBuilder backSb) {
        String typeName = element.getReturnType().toString();

        //back
        if (ClassTool.isType(typeName, void.class, Void.class)) {

        } else if (ClassTool.isList(typeName)) {
            String beanType = StringTool.getSub(typeName, "List", "<", ">");
            JavaInfo info = NameTools.getJavaInfoByName(beanType);
            String fieldName = NameTools.toMethodType(info.name) + "s";

            addLnTag(backSb, "        if ([StringTool].isNotBlank(json)) {", FullName.STRING_TOOL);
            addLnTag(backSb, "            [name] = [JsonTools].toBeanList([type].class, json);", fieldName, FullName.JSON_TOOL, beanType);
            addLnTag(backSb, "        }");

        } else if (ClassTool.isBaseClass(typeName)) {

        } else {
            JavaInfo info = NameTools.getJavaInfoByName(typeName);
            String fieldName = NameTools.toMethodType(info.name);

            addLnTag(backSb, "        if ([StringTool].isNotBlank(json)) {", FullName.STRING_TOOL);
            addLnTag(backSb, "            [name] = [JsonTools].toBean([type].class, json);", fieldName, FullName.JSON_TOOL, typeName);
            addLnTag(backSb, "        }");
        }
    }
}
/* model_temp_start
package core.tools.net.back;

import java.util.List;

import core.lib4a.net.bean.CoreSendParams;
import core.lib4a.net.netback.NetBack;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class [[name]] extends NetBack {
[[returnTag]]
    @Override
    public void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs) {
        super.accept(code, result, params, objs);
[[back]]
    }
}
model_temp_end */
