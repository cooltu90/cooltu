package core.path;

public class CheckDeleteLabelLabelPath extends com.codingtu.cooltu.lib4a.path.BasePath {







    public static CheckDeleteLabelLabelPath root(String root) {
        return new CheckDeleteLabelLabelPath(root);
    }

    public CheckDeleteLabelLabelPath(String root) {
        super(root);


    }





    public com.codingtu.cooltu.lib4a.path.PathBeanFile<com.codingtu.cooltu.bean.User> label_txt(String labelName) {
        return new com.codingtu.cooltu.lib4a.path.PathBeanFile<com.codingtu.cooltu.bean.User>(
                this.root
                        + addPrexSeparator(labelName + ".txt")
                , "txt"
                , com.codingtu.cooltu.bean.User.class
        );
    }



}
