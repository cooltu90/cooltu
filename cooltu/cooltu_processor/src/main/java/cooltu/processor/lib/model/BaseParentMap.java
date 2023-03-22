package cooltu.processor.lib.model;

import java.util.List;

import javax.lang.model.element.TypeElement;

import cooltu.lib4j.data.map.ListValueMap;
import cooltu.processor.lib.tools.NameTools;
import cooltu.processor.worker.deal.ActBaseDeal;
import cooltu.processor.worker.deal.FragmentBaseDeal;
import cooltu.processor.worker.model.BaseParentModel;

public class BaseParentMap {
    private ListValueMap<String, BaseParentModel> map = new ListValueMap<>();

    public void add(String key, TypeElement element) {
        BaseParentModel model = null;
        if (NameTools.isActivity(element)) {
            model = ActBaseDeal.getActBaseModel(element);
        } else if (NameTools.isFragment(element)) {
            model = FragmentBaseDeal.getFragmentBaseModel(element);
        }
        map.get(key).add(model);
    }

    public List<BaseParentModel> get(String key) {
        return map.get(key);
    }
}
