package cooltu.processor.worker.deal;


import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import cooltu.processor.annotation.resource.ColorRes;
import cooltu.processor.annotation.resource.ColorStr;
import cooltu.processor.annotation.resource.Dimen;
import cooltu.processor.annotation.resource.Dp;
import cooltu.processor.annotation.ui.Adapter;
import cooltu.processor.annotation.ui.Res;
import cooltu.processor.lib.model.AdapterModels;
import cooltu.processor.lib.tools.ElementTools;
import cooltu.processor.lib.tools.IdTools;
import cooltu.processor.worker.deal.base.BaseDeal;
import cooltu.processor.worker.model.BaseParentModel;
import cooltu.processor.worker.model.base.BaseAdapterModel;

public class ResDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        Res res = element.getAnnotation(Res.class);
        String classFullName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return res.value();
            }
        });
        Ts.ls(element.getEnclosedElements(), new Each<Element>() {
            @Override
            public boolean each(int position, Element element) {
                Adapter adapter = element.getAnnotation(Adapter.class);
                if (adapter != null) {
                    dealAdapter(classFullName, (VariableElement) element, adapter);
                }

                ColorRes colorRes = element.getAnnotation(ColorRes.class);
                if (colorRes != null) {
                    dealColorRes(classFullName, (VariableElement) element, colorRes);
                }

                ColorStr colorStr = element.getAnnotation(ColorStr.class);
                if (colorStr != null) {
                    dealColorStr(classFullName, (VariableElement) element, colorStr);
                }

                Dp dp = element.getAnnotation(Dp.class);
                if (dp != null) {
                    dealDp(classFullName, (VariableElement) element, dp);
                }

                Dimen dimen = element.getAnnotation(Dimen.class);
                if (dimen != null) {
                    dealDimen(classFullName, (VariableElement) element, dimen);
                }


                return false;
            }
        });
    }

    private void dealDimen(String classFullName, VariableElement element, Dimen dimen) {
        BaseParentModel baseParentModel = getBaseParentModel(classFullName);
        IdTools.Id id = IdTools.elementToId(element, Dimen.class, dimen.value());
        if (baseParentModel != null) {
            baseParentModel.addDimen(ElementTools.simpleName(element), id);
        }
    }

    private void dealDp(String classFullName, VariableElement element, Dp dpAnno) {
        BaseParentModel baseParentModel = getBaseParentModel(classFullName);
        if (baseParentModel != null) {
            baseParentModel.addDp(ElementTools.getType(element), ElementTools.simpleName(element), dpAnno.value());
        }
    }

    private void dealColorStr(String classFullName, VariableElement element, ColorStr colorStr) {
        BaseParentModel baseParentModel = getBaseParentModel(classFullName);
        if (baseParentModel != null) {
            baseParentModel.addColorStr(ElementTools.simpleName(element), colorStr.value());
        }
    }

    private void dealColorRes(String classFullName, VariableElement element, ColorRes colorRes) {
        BaseParentModel baseParentModel = getBaseParentModel(classFullName);
        IdTools.Id id = IdTools.elementToId(element, ColorRes.class, colorRes.value());
        if (baseParentModel != null) {
            baseParentModel.addColorRes(ElementTools.simpleName(element), id);
        }
    }

    private void dealAdapter(String classFullName, VariableElement ve, Adapter adapter) {
        BaseParentModel baseModel = getBaseParentModel(classFullName);
        if (baseModel != null) {
            BaseAdapterModel adapterModel = AdapterModels.getAdapterModel(adapter.type());
            adapterModel.setAdapter(ElementTools.getType(ve));
            adapterModel.setRvName(adapter.rvName());
            adapterModel.setAdapterName(ElementTools.simpleName(ve));
            baseModel.setAdapter(adapterModel, "");
        }
    }

}
