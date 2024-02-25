package core.path;

public class CheckExtraInfoDeleteLabelPath extends com.codingtu.cooltu.lib4a.path.BasePath {







    public static CheckExtraInfoDeleteLabelPath root(String root) {
        return new CheckExtraInfoDeleteLabelPath(root);
    }

    public CheckExtraInfoDeleteLabelPath(String root) {
        super(root);


    }

    public CheckExtraInfoDeleteLabelLabelPath label(String labelName) {
        return new CheckExtraInfoDeleteLabelLabelPath(
                this.root
                        + addPrexSeparator(labelName)
        );
    }






    public com.codingtu.cooltu.lib4j.ts.BaseTs<CheckExtraInfoDeleteLabelLabelPath> labelList(
            
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
