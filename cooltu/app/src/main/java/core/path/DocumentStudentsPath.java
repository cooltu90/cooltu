package core.path;

public class DocumentStudentsPath extends com.codingtu.cooltu.lib4a.path.BasePath {







    public static DocumentStudentsPath root(String root) {
        return new DocumentStudentsPath(root);
    }

    public DocumentStudentsPath(String root) {
        super(root);


    }

    public DocumentStudentsStudentPath student(String student) {
        return new DocumentStudentsStudentPath(
                this.root
                        + addPrexSeparator(student)
        );
    }









}
