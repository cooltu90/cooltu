package core.path;

public class DocumentPath extends com.codingtu.cooltu.lib4a.path.BasePath {

    public DocumentInfosPath infos;
    public DocumentClassGoodsPath classGoods;
    public DocumentStudentsPath students;




    public static DocumentPath obtain(String className) {
        com.codingtu.cooltu.path.DocumentPathConfigs configs = new com.codingtu.cooltu.path.DocumentPathConfigs();
        return obtain(configs.gradePath(), className);
    }

    public static DocumentPath obtain(String grade, String className) {
        return root(com.codingtu.cooltu.lib4a.tools.SDCardTool.getSDCard()
                + addPrexSeparator("AppData")
                + addPrexSeparator("document")
                + addPrexSeparator(grade)
                + addPrexSeparator(className)
        );
    }


    public static DocumentPath root(String root) {
        return new DocumentPath(root);
    }

    public DocumentPath(String root) {
        super(root);
        this.infos = DocumentInfosPath.root(
                this.root
                        + addPrexSeparator("infos")
        );
        this.classGoods = DocumentClassGoodsPath.root(
                this.root
                        + addPrexSeparator("物品")
        );
        this.students = DocumentStudentsPath.root(
                this.root
                        + addPrexSeparator("students")
        );


    }










}
