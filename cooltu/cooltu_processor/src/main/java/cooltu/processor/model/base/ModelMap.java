package cooltu.processor.model.base;

import java.util.List;
import java.util.Map;

import cooltu.lib4j.data.map.ListValueMap;
import cooltu.lib4j.ls.Ts;
import cooltu.lib4j.ls.each.Each;
import cooltu.lib4j.ls.getter.Getter;
import cooltu.processor.ModelType;

public class ModelMap {

    public static final Map<Integer, List<BasicModel>> MAP = new ListValueMap<>();

    /**************************************************
     *
     * 添加方法
     *
     **************************************************/

    public static void add(BasicModel model) {
        add(ModelType.DEFAULT, model);
    }

    public static void add(ModelType type, BasicModel model) {
        MAP.get(type.ordinal()).add(model);
    }

    /**************************************************
     *
     * 查找
     *
     **************************************************/
    public static <T> T find(ModelType type, String id) {
        return (T) Ts.get(MAP.get(type.ordinal()), new Getter<Integer, BasicModel>() {
            @Override
            public boolean get(Integer key, BasicModel baseModel) {
                return baseModel.id.equals(id);
            }
        });
    }

    /**************************************************
     *
     * 创建
     *
     **************************************************/
    public static void create() {
        Ts.ls(ModelType.values(), new Each<ModelType>() {
            @Override
            public boolean each(int position, ModelType modelType) {
                create(modelType.ordinal());
                return false;
            }
        });
        MAP.clear();
    }

    private static void create(int type) {
        List<BasicModel> models = MAP.get(type);
        Ts.ls(models, new Each<BasicModel>() {
            @Override
            public boolean each(int position, BasicModel model) {
                model.create();
                return false;
            }
        });
    }

}
