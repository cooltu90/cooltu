package cooltu.processor.worker.deal;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import cooltu.processor.annotation.net.Apis;
import cooltu.processor.annotation.tools.To;
import cooltu.processor.lib.tools.ElementTools;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.worker.deal.base.BaseDeal;
import cooltu.processor.worker.model.net.SendParamsModel;
import cooltu.processor.worker.model.net_retrofit.ApiServiceMethodModel;
import cooltu.processor.worker.model.net_retrofit.ApiServiceModel;
import cooltu.processor.worker.model.net_retrofit.NetBackModel;
import cooltu.processor.worker.model.net_retrofit.NetMethodModel;
import cooltu.processor.worker.model.net_retrofit.NetModel;

/**************************************************
 *
 * 相关文件:
 * XxxxApiService
 * Net
 * XxxxNetBack
 * XxxxSendParams
 *
 **************************************************/
@To({ApiServiceMethodModel.class, NetModel.class, NetMethodModel.class})
public class ApisDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        forRetrofit(element);
    }

    private void forRetrofit(Element element) {
        Apis apis = element.getAnnotation(Apis.class);
        String baseUrl = apis.baseUrl();

        String apiTypeName = NameTools.simpleName(element);
        JavaInfo info = NameTools.getApiServiceInfo(apiTypeName);
        ApiServiceModel apiServiceModel = new ApiServiceModel(info);

        Ts.ls(element.getEnclosedElements(), new Each<Element>() {
            @Override
            public boolean each(int position, Element element) {
                ExecutableElement ee = (ExecutableElement) element;
                //XxxxApiService
                apiServiceModel.addMethod(ee);
                //Net
                NetModel.model.add(ee, baseUrl, apiTypeName);

                String classTypeName = NameTools.toClassType(ElementTools.simpleName(ee));
                //XxxxSendParams
                if (!CountTool.isNull(ee.getParameters())) {
                    new SendParamsModel(NameTools.getSendParamsInfo(classTypeName), ee);
                }
                //XxxxNetBack
                new NetBackModel(NameTools.getNetBackInfo(classTypeName), ee);

                return false;
            }
        });
    }
}
