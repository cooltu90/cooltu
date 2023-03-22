package cooltu.processor.worker.deal;

import javax.lang.model.element.Element;

import cooltu.lib4j.tools.ClassTool;
import cooltu.processor.annotation.ui.Adapter;
import cooltu.processor.lib.model.AdapterModels;
import cooltu.processor.lib.tools.ElementTools;
import cooltu.processor.worker.deal.base.BaseDeal;
import cooltu.processor.worker.model.BaseParentModel;
import cooltu.processor.worker.model.base.BaseAdapterModel;


public class AdapterDeal extends BaseDeal {

    @Override
    public void deal(Element element) {
        Adapter adapter = element.getAnnotation(Adapter.class);
        String adapterFullName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return adapter.value();
            }
        });
        int type = adapter.type();
        String rvName = adapter.rvName();

        if (!ClassTool.isVoid(adapterFullName)) {
            String classFullName = ElementTools.getType(element);
            BaseParentModel baseModel = getBaseParentModel(classFullName);
            if (baseModel != null) {
                BaseAdapterModel adapterModel = AdapterModels.getAdapterModel(type);
                adapterModel.setAdapter(adapterFullName);
                adapterModel.setRvName(rvName);
                adapterModel.setAdapterName("adapter");
                baseModel.setAdapter(adapterModel, "");
            }
        }
    }
}
