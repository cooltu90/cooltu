package cooltu.processor.worker.deal;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import cooltu.constant.FullName;
import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import cooltu.processor.annotation.ui.FragmentBase;
import cooltu.processor.lib.model.ModelMap;
import cooltu.processor.lib.tools.IdTools;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.worker.ModelType;
import cooltu.processor.worker.deal.base.BaseDeal;
import cooltu.processor.worker.model.FragmentBaseModel;


public class FragmentBaseDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        FragmentBase fragmentBase = element.getAnnotation(FragmentBase.class);
        String baseClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return fragmentBase.baseClass();
            }
        });
        if (ClassTool.isVoid(baseClass)) {
            baseClass = FullName.BASE_FRAGMENT;
        }

        IdTools.Id id = IdTools.elementToId(element, FragmentBase.class, fragmentBase.layout());

        FragmentBaseModel fragmentBaseModel = new FragmentBaseModel(getFragmentBaseInfo((TypeElement) element));
        fragmentBaseModel.setBaseClass(baseClass);
        fragmentBaseModel.setLayout(id.rPackage, id.rName);
    }

    private static JavaInfo getFragmentBaseInfo(TypeElement element) {
        return NameTools.getFragmentBaseInfo(NameTools.getFragmentTypeBaseName(element));
    }

    public static FragmentBaseModel getFragmentBaseModel(TypeElement element) {
        return ModelMap.find(ModelType.fragmentBase, getFragmentBaseInfo(element).fullName);
    }

    public static FragmentBaseModel getFragmentBaseModel(String fullName) {
        return ModelMap.find(ModelType.fragmentBase, NameTools.getFragmentBaseInfoByFragmentFullName(fullName).fullName);
    }
}
