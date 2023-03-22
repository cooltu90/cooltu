package cooltu.processor.deal.base;

import javax.lang.model.element.Element;

public abstract class BaseDeal {

    /**************************************************
     *
     * deal方法
     *
     **************************************************/
    public void dealElement(Element element) {
        deal(element);
    }

    public abstract void deal(Element element);
}
