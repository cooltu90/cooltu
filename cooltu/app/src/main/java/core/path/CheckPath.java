package core.path;

public class CheckPath extends com.codingtu.cooltu.lib4a.path.BasePath {

    public CheckDeleteLabelPath DeleteLabel;
    public CheckExtraInfoPath ExtraInfo;


    public com.codingtu.cooltu.lib4a.path.PathBeanListFile<com.codingtu.cooltu.bean.User> list_txt;


    public static CheckPath obtain1(String company, String taskName) {
        com.codingtu.cooltu.path.CheckPathConfigs configs = new com.codingtu.cooltu.path.CheckPathConfigs();
        return obtain(company, taskName);
    }

    public static CheckPath obtain(String company, String taskName) {
        return root(com.codingtu.cooltu.lib4a.tools.SDCardTool.getSDCard()
                + addPrexSeparator("EnvCheckData")
                + addPrexSeparator("tasks")
                + addPrexSeparator(company)
                + addPrexSeparator(taskName)
        );
    }


    public static CheckPath root(String root) {
        return new CheckPath(root);
    }

    public CheckPath(String root) {
        super(root);
        this.DeleteLabel = CheckDeleteLabelPath.root(
                this.root
                        + addPrexSeparator("DeleteLabel")
        );
        this.ExtraInfo = CheckExtraInfoPath.root(
                this.root
                        + addPrexSeparator("ExtraInfo")
        );

        this.list_txt = new com.codingtu.cooltu.lib4a.path.PathBeanListFile<com.codingtu.cooltu.bean.User>(
                this.root
                        + addPrexSeparator("1.txt")
                , "txt"
                , com.codingtu.cooltu.bean.User.class
        );

    }

    public CheckLabelPath label(String labelName) {
        return new CheckLabelPath(
                this.root
                        + addPrexSeparator(labelName)
        );
    }






    public com.codingtu.cooltu.lib4j.ts.BaseTs<CheckLabelPath> labelList(
            
    ) {
        com.codingtu.cooltu.path.CheckPathConfigs configs = new com.codingtu.cooltu.path.CheckPathConfigs();
        return com.codingtu.cooltu.lib4j.ts.Ts.ts(rootFile().listFiles()).convert((index, file) -> {
            if (configs.labelFilter(file)) {
                return label(file.getName());
            }
            return null;
        });
    }



}
