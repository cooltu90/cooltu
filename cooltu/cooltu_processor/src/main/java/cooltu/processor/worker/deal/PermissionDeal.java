package cooltu.processor.worker.deal;


import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import cooltu.processor.worker.deal.base.BaseDeal;
import cooltu.processor.worker.model.PermissionModel;

public class PermissionDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        PermissionModel.model.elements.add((ExecutableElement) element);
        ActBaseDeal
                .getActBaseModel((TypeElement) element.getEnclosingElement())
                .addPermission((ExecutableElement) element);
    }
}
