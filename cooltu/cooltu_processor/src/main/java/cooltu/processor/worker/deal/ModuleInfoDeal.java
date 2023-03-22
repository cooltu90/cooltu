package cooltu.processor.worker.deal;


import javax.lang.model.element.Element;

import cooltu.constant.Constant;
import cooltu.constant.FullName;
import cooltu.lib4j.tools.ClassTool;
import cooltu.processor.annotation.ModuleInfo;
import cooltu.processor.worker.deal.base.BaseDeal;
import cooltu.processor.worker.model.ActBackIntentModel;
import cooltu.processor.worker.model.Code4RequestModel;
import cooltu.processor.worker.model.PassModel;
import cooltu.processor.worker.model.PermissionModel;
import cooltu.processor.worker.model.StartModel;
import cooltu.processor.worker.model.mock.MockModel;
import cooltu.processor.worker.model.net_retrofit.NetModel;

public class ModuleInfoDeal extends BaseDeal {
    @Override
    public void deal(Element element) {
        final ModuleInfo info = element.getAnnotation(ModuleInfo.class);
        Constant.CURRENT_MODULE = info.module();
        FullName.BASE_ACT = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return info.baseAct();
            }
        });
        FullName.BASE_FRAGMENT = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return info.baseFragment();
            }
        });
        Constant.PKG_DEFAULT_R = info.defaultRPackage();
        Constant.PKG_MOCK = Constant.PKG_DEFAULT_R + info.mockPackage();
        Constant.DEFAULT_TEMP_LAYOUT = info.defaultLayout();

        PassModel passModel = PassModel.model;
        StartModel startModel = StartModel.model;
        Code4RequestModel code4RequestModel = Code4RequestModel.model;
        NetModel netModel1 = NetModel.model;
        PermissionModel permissionModel = PermissionModel.model;
        MockModel mockModel = MockModel.model;
        ActBackIntentModel actBackIntentModel = ActBackIntentModel.model;
    }
}
