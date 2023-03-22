package cooltu.processor.worker.deal;


import javax.lang.model.element.Element;

import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.worker.deal.base.BaseDeal;
import cooltu.processor.worker.model.VHModel;

public class VHDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        String typeBaseName = NameTools.getAdapterTypeBaseName(NameTools.simpleName(element));
        new VHModel(NameTools.getVHInfo(typeBaseName), typeBaseName, true);
    }
}
