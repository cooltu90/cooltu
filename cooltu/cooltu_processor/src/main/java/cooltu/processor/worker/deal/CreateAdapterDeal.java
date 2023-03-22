package cooltu.processor.worker.deal;

import javax.lang.model.element.Element;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import cooltu.processor.annotation.create.CreateAdapter;
import cooltu.processor.lib.model.AdapterModels;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.worker.deal.base.BaseDeal;
import cooltu.processor.worker.model.LayoutModel;
import cooltu.processor.worker.model.VHModel;
import cooltu.processor.worker.model.base.BaseAdapterModel;

public class CreateAdapterDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        CreateAdapter createAdapter = element.getAnnotation(CreateAdapter.class);
        String packages = createAdapter.packages();
        String typeBaseName = NameTools.toClassType(createAdapter.name());
        //创建layout
        JavaInfo layoutInfo = new JavaInfo();
        layoutInfo.path = NameTools.getItemLayoutPath(NameTools.toLayoutType(typeBaseName));
        new LayoutModel(layoutInfo);
        //生成VH
        new VHModel(NameTools.getVHInfo(typeBaseName), typeBaseName, false);

        JavaInfo adapterInfo = NameTools.getAdapterInfo(packages, typeBaseName);
        BaseAdapterModel adapterModel = AdapterModels.getAdapterModel(createAdapter.type());
        adapterModel.setInfo(adapterInfo);
        adapterModel.setBeans(ClassTool.getAnnotationClasses(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return createAdapter.beanClasses();
            }
        }));
    }
}
