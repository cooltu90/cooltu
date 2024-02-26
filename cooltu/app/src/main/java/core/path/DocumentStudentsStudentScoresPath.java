package core.path;

public class DocumentStudentsStudentScoresPath extends com.codingtu.cooltu.lib4a.path.BasePath {







    public static DocumentStudentsStudentScoresPath root(String root) {
        return new DocumentStudentsStudentScoresPath(root);
    }

    public DocumentStudentsStudentScoresPath(String root) {
        super(root);


    }





    public com.codingtu.cooltu.lib4a.path.PathBeanFile<com.codingtu.cooltu.bean.Score> score_txt(String score) {
        return new com.codingtu.cooltu.lib4a.path.PathBeanFile<com.codingtu.cooltu.bean.Score>(
                this.root
                        + addPrexSeparator(score + ".txt")
                , "txt"
                , com.codingtu.cooltu.bean.Score.class
        );
    }


    public com.codingtu.cooltu.lib4j.ts.BaseTs<com.codingtu.cooltu.lib4a.path.PathBeanFile<com.codingtu.cooltu.bean.Score>> score_txt_list(
            java.lang.String prex
    ) {
        com.codingtu.cooltu.path.DocumentPathConfigs configs = new com.codingtu.cooltu.path.DocumentPathConfigs();
        return com.codingtu.cooltu.lib4j.ts.Ts.ts(rootFile().listFiles()).convert((index, file) -> {
            if (configs.scoreFilter(file, prex)) {
                return score_txt(file.getName());
            }
            return null;
        });
    }



}
