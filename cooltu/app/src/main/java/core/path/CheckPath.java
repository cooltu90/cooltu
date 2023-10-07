package core.path;

public class CheckPath extends com.codingtu.cooltu.lib4a.path.BasePath {

    public static CheckPath obtain(String company, String taskName) {
        return root(com.codingtu.cooltu.lib4a.tools.SDCardTool.getSDCard()
                + addPrexSeparator("EnvCheckData")
                + addPrexSeparator("tasks")
                + addPrexSeparator(company)
                + addPrexSeparator(taskName)

        );
    }

    public static CheckPath root(String root) {
        return new CheckPath(root);
    }

    public CheckPath(String root) {
        super(root);
    }
}
