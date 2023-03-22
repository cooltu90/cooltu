package cooltu.processor.worker.deal;


import javax.lang.model.element.Element;

import cooltu.constant.Constant;
import cooltu.processor.annotation.ui.DefaultEditDialogLayout;
import cooltu.processor.lib.tools.IdTools;
import cooltu.processor.worker.deal.base.BaseDeal;

public class DefaultEditDialogLayoutDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        DefaultEditDialogLayout layout = element.getAnnotation(DefaultEditDialogLayout.class);
        Constant.DEFAULT_EDIT_DIALOG_LAYOUT = IdTools.elementToId(element, DefaultEditDialogLayout.class, layout.value()).toString();
    }
}
