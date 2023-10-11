package core.path;

public class CheckPath extends com.codingtu.cooltu.lib4a.path.BasePath {

    public CheckDeleteLabelPath DeleteLabel;
    public CheckExtraInfoPath ExtraInfo;




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


    }

    public CheckLabelPath label(String labelName) {
        return new CheckLabelPath(
                this.root
                        + addPrexSeparator(labelName)
        );
    }


    public com.codingtu.cooltu.lib4j.ts.impl.basic.TListTs<CheckLabelPath> labelList(
    ) {
        com.codingtu.cooltu.path.filter.LabelFilter filter = new com.codingtu.cooltu.path.filter.LabelFilter();
        return com.codingtu.cooltu.lib4j.ts.Ts.ts(new java.io.File(root()).listFiles()).convert((index, file) -> {
            if (filter.check(file)) {
                return label(file.getName());
            }
            return null;
        });
    }

}
