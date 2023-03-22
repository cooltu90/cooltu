package cooltu.processor.lib.bean;

import java.util.Map;

import cooltu.processor.lib.tools.IdTools;

public class FormItemInfo {
    public String beanField;
    public String prompt;
    public String parse;
    public String check;

    public IdTools.Id viewId;
    public String viewName;
    public String fieldName;
    public int fieldType;

    //link相关
    public Map<Integer, IdTools.Id> idMap;
    public int[] ids;
    public String linkClass;
}
