package cooltu.processor.worker.deal;


import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import cooltu.constant.Constant;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.StringTool;
import cooltu.processor.annotation.ui.dialog.DialogUseForEdit;
import cooltu.processor.lib.bean.EditDialogInfo;
import cooltu.processor.lib.tools.ElementTools;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.worker.deal.base.BaseDeal;
import cooltu.processor.worker.model.BaseParentModel;

public class DialogUseForEditDeal extends BaseDeal {


    @Override
    public void deal(Element element) {
        String classFullName = null;
        DialogUseForEdit use = element.getAnnotation(DialogUseForEdit.class);
        if (element instanceof TypeElement) {
            String simpleName = ElementTools.simpleName(element);

            if (simpleName.endsWith(Constant.SUFFIX_ACTIVITY) || simpleName.endsWith(Constant.SUFFIX_FRAGMENT)) {
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

    private void deal(Element element, DialogUseForEdit use, String classFullName) {
        EditDialogInfo info = new EditDialogInfo();
        info.name = use.name();
        info.title = use.title();
        info.hint = use.hint();
        info.inputType = use.inputType();
        info.stopAnimation = use.stopAnimation();
        info.objType = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return use.objType();
            }
        });
        info.reserve = use.reserve();

        BaseParentModel baseModel = null;

        if (NameTools.isActivity(classFullName)) {
            baseModel = ActBaseDeal.getActBaseModel(classFullName);
        } else if (NameTools.isFragment(classFullName)) {
            baseModel = FragmentBaseDeal.getFragmentBaseModel(classFullName);
        }

        if (baseModel != null)
            baseModel.addEditDialog(info);

    }

}
