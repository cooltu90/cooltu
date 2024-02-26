package core.path;

public class CheckLabelPath extends com.codingtu.cooltu.lib4a.path.BasePath {



    public com.codingtu.cooltu.lib4a.path.PathImageFile handle_jpg;
    public com.codingtu.cooltu.lib4a.path.PathImageFile handle_jpg_pnc;




    public static CheckLabelPath root(String root) {
        return new CheckLabelPath(root);
    }

    public CheckLabelPath(String root) {
        super(root);

        this.handle_jpg = new com.codingtu.cooltu.lib4a.path.PathImageFile(
                this.root
                        + addPrexSeparator("handle")
                , "jpg"
        );
        this.handle_jpg_pnc = new com.codingtu.cooltu.lib4a.path.PathImageFile(
                this.root
                        + addPrexSeparator("handle.jpg")
                , "pnc"
        );

    }





    public com.codingtu.cooltu.lib4a.path.PathBeanFile<com.codingtu.cooltu.bean.User> label_txt(String labelName) {
        return new com.codingtu.cooltu.lib4a.path.PathBeanFile<com.codingtu.cooltu.bean.User>(
                this.root
                        + addPrexSeparator(labelName + ".txt")
                , "txt"
                , com.codingtu.cooltu.bean.User.class
        );
    }


    public com.codingtu.cooltu.lib4j.ts.BaseTs<com.codingtu.cooltu.lib4a.path.PathBeanFile<com.codingtu.cooltu.bean.User>> label_txt_list() {
        return com.codingtu.cooltu.lib4j.ts.Ts.ts(rootFile().listFiles()).convert((index, file) -> label_txt(file.getName()));
    }



}
