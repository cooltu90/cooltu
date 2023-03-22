package cooltu.processor.worker.deal;


import javax.lang.model.element.Element;

import cooltu.constant.Constant;
import cooltu.processor.annotation.ui.DefaultDialogLayout;
import cooltu.processor.lib.tools.IdTools;
import cooltu.processor.worker.deal.base.BaseDeal;

public class DefaultDialogLayoutDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        DefaultDialogLayout layout = element.getAnnotation(DefaultDialogLayout.class);
        Constant.DEFAULT_DIALOG_LAYOUT = IdTools.elementToId(element, DefaultDialogLayout.class, layout.value()).toString();
    }
}
