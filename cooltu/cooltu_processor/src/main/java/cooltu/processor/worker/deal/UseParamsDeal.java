package cooltu.processor.worker.deal;


import javax.lang.model.element.Element;

import cooltu.processor.annotation.UseParams;
import cooltu.processor.worker.deal.base.BaseDeal;

public class UseParamsDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        UseParams useParams = element.getAnnotation(UseParams.class);
        switch (useParams.type()) {
        }
    }
}
