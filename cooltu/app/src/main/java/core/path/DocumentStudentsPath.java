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






    public com.codingtu.cooltu.lib4j.ts.BaseTs<DocumentStudentsStudentPath> studentList(
            java.lang.String prex
    ) {
        com.codingtu.cooltu.path.DocumentPathConfigs configs = new com.codingtu.cooltu.path.DocumentPathConfigs();
        return com.codingtu.cooltu.lib4j.ts.Ts.ts(rootFile().listFiles()).convert((index, file) -> {
            if (configs.studentFilter(file, prex)) {
                return student(file.getName());
            }
            return null;
        });
    }



}
