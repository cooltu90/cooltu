package cooltu.processor.worker.deal;

import java.io.File;

import javax.lang.model.element.Element;

import cooltu.constant.Constant;
import cooltu.constant.FullName;
import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ClassTool;
import cooltu.processor.annotation.create.CreateAct;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.worker.deal.base.BaseDeal;
import cooltu.processor.worker.model.ActBaseModel;
import cooltu.processor.worker.model.CreateActModel;
import cooltu.processor.worker.model.LayoutModel;
import cooltu.processor.worker.model.ManifestModel;
import cooltu.processor.worker.model.StartModel;

public class CreateActDeal extends BaseDeal {

    @Override
    public void deal(Element element) {
        //获取基本信息
        final CreateAct createAct = element.getAnnotation(CreateAct.class);
        String layoutBaseName = createAct.name();
        String packages = createAct.packages();
        JavaInfo actInfo = NameTools.getActInfo(packages, layoutBaseName);
        File file = new File(actInfo.path);
        if (file.exists()) {
            return;
        }

        String base = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return createAct.baseClass();
            }
        });
        if (ClassTool.isVoid(base)) {
            base = FullName.BASE_ACT;
        }

        //创建layout
        JavaInfo layoutInfo = new JavaInfo();
        layoutInfo.path = NameTools.getActivityLayoutPath(layoutBaseName);
        new LayoutModel(layoutInfo);

        JavaInfo actBaseInfo = NameTools.getActBaseInfo(layoutBaseName);
        //创建ActBase
        ActBaseModel actBaseModel = new ActBaseModel(actBaseInfo);
        actBaseModel.setBaseClass(base);
        actBaseModel.setLayout(Constant.PKG_DEFAULT_R, NameTools.getActivityLayoutName(layoutBaseName));

        //创建activity
        new CreateActModel(actInfo, layoutBaseName, actBaseInfo);

        //添加Manifest
        JavaInfo manifestInfo = new JavaInfo();
        manifestInfo.path = NameTools.getManifestPath();
        new ManifestModel(manifestInfo).setAct(actInfo.fullName,createAct.screenOrientation());

        //添加ActStart
        StartModel.model.addStart(actInfo.fullName, NameTools.toStaticType(actInfo.name),null);
    }

}
