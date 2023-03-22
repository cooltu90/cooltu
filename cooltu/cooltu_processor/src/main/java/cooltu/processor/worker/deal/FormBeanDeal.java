package cooltu.processor.worker.deal;


import javax.lang.model.element.Element;

import cooltu.constant.FieldName;
import cooltu.constant.FullName;
import cooltu.lib4j.data.bean.KV;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import cooltu.processor.lib.tools.ElementTools;
import cooltu.processor.worker.deal.base.BaseDeal;
import cooltu.processor.worker.model.BaseParentModel;

public class FormBeanDeal extends BaseDeal {

    @Override
    public void deal(Element element) {
        KV<String, String> kv = ElementTools.getFormBeanName(element);
        Ts.ls(FormDeal.map.get(kv.k), new Each<BaseParentModel>() {
            @Override
            public boolean each(int position, BaseParentModel baseParentModel) {
                baseParentModel.addFormBean(kv.k, kv.v);
                baseParentModel.addField(FullName.BOOL, FieldName.INIT_FORM_BEAN);
                return false;
            }
        });

    }
}
