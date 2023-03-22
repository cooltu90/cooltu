package cooltu.processor.worker.deal;


import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import cooltu.constant.Constant;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.StringTool;
import cooltu.processor.annotation.ui.dialog.DialogUse;
import cooltu.processor.lib.bean.DialogInfo;
import cooltu.processor.lib.tools.ElementTools;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.worker.deal.base.BaseDeal;
import cooltu.processor.worker.model.BaseParentModel;

public class DialogUseDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        String classFullName = null;
        DialogUse use = element.getAnnotation(DialogUse.class);
        if (element instanceof TypeElement) {
            if (ElementTools.simpleName(element).endsWith(Constant.SUFFIX_ACTIVITY)) {
                //如果是activity上面的
                classFullName = ElementTools.getType(element);
            } else {
                classFullName = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                    @Override
                    public Object get() {
                        return use.base();
                    }
                });
            }
        } else if (element instanceof VariableElement) {
            classFullName = ElementTools.getType(element);
        }

        if (StringTool.isNotBlank(classFullName) && !ClassTool.isVoid(classFullName)) {
            deal(element, use, classFullName);
        }
    }

    private void deal(Element element, DialogUse use, String classFullName) {
        BaseParentModel baseModel = null;

        if (NameTools.isActivity(classFullName)) {
            baseModel = ActBaseDeal.getActBaseModel(classFullName);
        } else if (NameTools.isFragment(classFullName)) {
            baseModel = FragmentBaseDeal.getFragmentBaseModel(classFullName);
        }

        if (baseModel != null) {
            DialogInfo dialogInfo = new DialogInfo();
            dialogInfo.name = use.name();
            dialogInfo.title = use.title();
            dialogInfo.content = use.content();
            dialogInfo.objType = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return use.objType();
                }
            });
            baseModel.addDialog(dialogInfo);
        }
    }
}
