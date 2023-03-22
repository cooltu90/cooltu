package cooltu.processor.worker.deal;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import cooltu.constant.FullName;
import cooltu.processor.lib.ls.EachType;
import cooltu.processor.lib.ls.TypeLs;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.worker.deal.base.BaseDeal;
import cooltu.processor.worker.model.ActBackIntentModel;
import cooltu.processor.worker.model.PassModel;


public class ActBackDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        TypeElement typeElement = getTypeElement(element);
        if (NameTools.isActivity(typeElement)) {
            List<? extends VariableElement> parameters = ((ExecutableElement) element).getParameters();
            TypeLs.ls(parameters, new EachType() {
                @Override
                public void each(int position, String type, String name) {
                    if (!FullName.INTENT.equals(type)) {
                        PassModel.model.add(type, name);
                    }
                }
            });
            //获取所在的Activity
            ActBaseDeal
                    .getActBaseModel(typeElement)
                    .addActBack((ExecutableElement) element);

            ActBackIntentModel.model.add((ExecutableElement) element);
        }
    }
}
