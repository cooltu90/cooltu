package core.path;

public class CheckPath extends com.codingtu.cooltu.lib4a.path.BasePath {

    public static CheckPath root(String root) {
        return new CheckPath(root);
    }

    public CheckPath(String root) {
        super(root);
    }
}
