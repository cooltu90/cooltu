package core.path;

public class CheckPath extends com.codingtu.cooltu.lib4a.path.BasePath {

    public static CheckPath obtain(String type) {
        return root(com.codingtu.cooltu.lib4a.tools.SDCardTool.getSDCard()
                + addPrexSeparator("Xxx")
                + addPrexSeparator("xxx")
                + addPrexSeparator(type)
                + addPrexSeparator("0")

        );
    }

    public static CheckPath root(String root) {
        return new CheckPath(root);
    }

    public CheckPath(String root) {
        super(root);
    }
}
