package cooltu.processor.lib.model;

import cooltu.constant.AdapterType;
import cooltu.processor.worker.model.AdapterDefaultModel;
import cooltu.processor.worker.model.AdapterDefaultMoreModel;
import cooltu.processor.worker.model.base.BaseAdapterModel;

public class AdapterModels {

    public static BaseAdapterModel getAdapterModel(int type) {
        switch (type) {
            case AdapterType.DEFAULT_LIST:
                return new AdapterDefaultModel();
            case AdapterType.DEFAULT_MORE_LIST:
                return new AdapterDefaultMoreModel();
        }
        return null;
    }
}
