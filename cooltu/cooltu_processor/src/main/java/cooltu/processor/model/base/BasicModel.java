package cooltu.processor.model.base;

import java.io.File;
import java.util.List;
import java.util.Map;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.data.map.StringBuilderValueMap;
import cooltu.lib4j.file.read.FileReader;
import cooltu.lib4j.file.write.FileWriter;
import cooltu.lib4j.tools.CountTool;
import cooltu.processor.ModelType;
import cooltu.processor.tools.NameTools;
import cooltu.processor.tools.TagTool;

public class BasicModel {
    public boolean isForce;
    public String id;
    protected JavaInfo info;

    public BasicModel(JavaInfo info) {
        this.info = info;
        if (info != null)
            this.id = info.fullName;
        else
            this.id = NameTools.getModelTypeBaseName(getClass());
        addToMap();
    }

    protected void addToMap() {
        ModelMap.add(getModelType(), this);
    }

    public void create() {
        if (info == null)
            return;
        beforCreate();
        File file = new File(info.path);
        if (isForce || !file.exists()) {
            List<String> lines = getLines();
            if (!CountTool.isNull(lines)) {
                FileWriter.to(file).cover().write(lines);
            }
        }
    }

    protected void beforCreate() {

    }

    /**************************************************
     *
     * 获取整个处理好的内容
     *
     **************************************************/
    public List<String> getLines() {
        return TagTool.getLines(map, getTempLines());
    }

    /**************************************************
     *
     * 获取模板
     *
     **************************************************/
    public List<String> getTempLines() {
        try {
            List<String> lines = FileReader.from(new File(NameTools.getModelPath(this))).readLine();
            int start = 0;
            int end = 0;
            for (int i = 0; i < CountTool.count(lines); i++) {
                String line = lines.get(i);
                if ("/* model_temp_start".equals(line.trim())) {
                    start = i + 1;
                } else if ("model_temp_end */".equals(line.trim())) {
                    end = i;
                }
            }
            if (start != end) {
                List<String> strings = lines.subList(start, end);
                return strings;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**************************************************
     *
     * 获取类型
     *
     **************************************************/
    public ModelType getModelType() {
        return ModelType.DEFAULT;
    }


    /**************************************************
     *
     * 对tag的存入操作
     *
     **************************************************/
    protected Map<String, StringBuilder> map = new StringBuilderValueMap<>();

    public StringBuilder getTag(String tag) {
        return map.get(tag);
    }

    public void addTag(String tag, String line, Object... values) {
        addTag(map.get(tag), line, values);
    }

    public void addLnTag(String tag, String line, Object... values) {
        addLnTag(map.get(tag), line, values);
    }

    public void addLnTag(StringBuilder tag, String line, Object... tags) {
        tag.append(dealLine(line, tags)).append("\r\n");
    }

    public void addTag(StringBuilder tag, String line, Object... tags) {
        tag.append(dealLine(line, tags));
    }

    private String dealLine(String line, Object... values) {
        if (values != null && values.length > 0) {
            line = TagTool.getLine(line, values);
        }
        return line;
    }
}
