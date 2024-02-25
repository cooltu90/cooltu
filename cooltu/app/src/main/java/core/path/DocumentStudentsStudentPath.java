package core.path;

public class DocumentStudentsStudentPath extends com.codingtu.cooltu.lib4a.path.BasePath {



    public com.codingtu.cooltu.lib4a.path.PathBeanFile<com.codingtu.cooltu.bean.User> json_txt;




    public static DocumentStudentsStudentPath root(String root) {
        return new DocumentStudentsStudentPath(root);
    }

    public DocumentStudentsStudentPath(String root) {
        super(root);

        this.json_txt = new com.codingtu.cooltu.lib4a.path.PathBeanFile<com.codingtu.cooltu.bean.User>(
                this.root
                        + addPrexSeparator("json.txt")
                , "txt"
                , com.codingtu.cooltu.bean.User.class
        );

    }










}
