package core.path;

public class DocumentClassGoodsPath extends com.codingtu.cooltu.lib4a.path.BasePath {

    public DocumentClassGoodsGoodsInfosPath goodsInfos;






    public static DocumentClassGoodsPath root(String root) {
        return new DocumentClassGoodsPath(root);
    }

    public DocumentClassGoodsPath(String root) {
        super(root);
        this.goodsInfos = DocumentClassGoodsGoodsInfosPath.root(
                this.root
                        + addPrexSeparator("info")
        );


    }










}
