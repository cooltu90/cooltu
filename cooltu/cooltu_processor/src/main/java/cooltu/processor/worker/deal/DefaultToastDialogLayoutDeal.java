package cooltu.processor.worker.deal;


import javax.lang.model.element.Element;

import cooltu.constant.Constant;
import cooltu.processor.annotation.ui.DefaultToastDialogLayout;
import cooltu.processor.lib.tools.IdTools;
import cooltu.processor.worker.deal.base.BaseDeal;

public class DefaultToastDialogLayoutDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        DefaultToastDialogLayout layout = element.getAnnotation(DefaultToastDialogLayout.class);
        Constant.DEFAULT_TOAST_DIALOG_LAYOUT = IdTools.elementToId(element, DefaultToastDialogLayout.class, layout.value()).toString();
    }
}
