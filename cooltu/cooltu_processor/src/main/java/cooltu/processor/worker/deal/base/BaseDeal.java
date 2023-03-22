package cooltu.processor.worker.deal.base;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import cooltu.constant.Constant;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.worker.deal.ActBaseDeal;
import cooltu.processor.worker.deal.FragmentBaseDeal;
import cooltu.processor.worker.model.BaseParentModel;


public abstract class BaseDeal {

    /**************************************************
     *
     * deal方法
     *
     **************************************************/
    public void dealElement(Element element) {
        if (element instanceof TypeElement) {
            String fullName = NameTools.fullName((TypeElement) element);
            if (Constant.DELETE_ACTS!=null&&Constant.DELETE_ACTS.contains(fullName)) {
                return;
            }
        }
        deal(element);
    }

    public abstract void deal(Element element);

    protected TypeElement getTypeElement(Element element) {
        if (element instanceof ExecutableElement) {
            return (TypeElement) element.getEnclosingElement();
        } else if (element instanceof TypeElement) {
            return (TypeElement) element;
        } else {
            return null;
        }
    }


    protected BaseParentModel getBaseParentModel(String classFullName) {
        BaseParentModel baseModel = null;
        if (NameTools.isActivity(classFullName)) {
            baseModel = ActBaseDeal.getActBaseModel(classFullName);
        } else if (NameTools.isFragment(classFullName)) {
            baseModel = FragmentBaseDeal.getFragmentBaseModel(classFullName);
        }
        return baseModel;
    }

}
