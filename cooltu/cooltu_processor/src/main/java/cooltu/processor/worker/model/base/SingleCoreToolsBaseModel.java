package cooltu.processor.worker.model.base;


import cooltu.processor.lib.tools.NameTools;

public class SingleCoreToolsBaseModel extends BaseModel {

    public SingleCoreToolsBaseModel(String typeName) {
        super(NameTools.getCoreToolsInfo(typeName));
        isForce = true;
    }
}
