package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class MsThreadBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder name;
    protected StringBuilder interfaceName;
    protected StringBuilder typeName;
    protected StringBuilder startType;
    protected StringBuilder subThreadDeal;
    protected StringBuilder mainThreadDeal;
    protected StringBuilder sendMessageMethods;

    public MsThreadBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        name = map.get("name");
        interfaceName = map.get("interfaceName");
        typeName = map.get("typeName");
        startType = map.get("startType");
        subThreadDeal = map.get("subThreadDeal");
        mainThreadDeal = map.get("mainThreadDeal");
        sendMessageMethods = map.get("sendMessageMethods");

    }



    @Override
    protected void dealLinesInParent() {

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import android.os.Message;");
        lines.add("");
        lines.add("import com.codingtu.cooltu.lib4a.msthread.CoreMSThread;");
        lines.add("");
        lines.add("public class [[name]] extends CoreMSThread {");
        lines.add("");
        lines.add("    private [[interfaceName]] dealer;");
        lines.add("");
        lines.add("    public static [[name]] obtain() {");
        lines.add("        return new [[name]]();");
        lines.add("    }");
        lines.add("");
        lines.add("    public [[name]] dealer([[interfaceName]] dealer) {");
        lines.add("        this.dealer = dealer;");
        lines.add("        return this;");
        lines.add("    }");
        lines.add("");
        lines.add("    private int type([[typeName]] type) {");
        lines.add("        return type.ordinal();");
        lines.add("    }");
        lines.add("");
        lines.add("    @Override");
        lines.add("    protected int subThreadStartType() {");
        lines.add("        return type([[typeName]].[[startType]]);");
        lines.add("    }");
        lines.add("");
        lines.add("");
        lines.add("    @Override");
        lines.add("    protected void handleMessageInThread(Message msg) {");
        lines.add("[[subThreadDeal]]");
        lines.add("    }");
        lines.add("");
        lines.add("    @Override");
        lines.add("    protected void handleMessageInMain(Message msg) {");
        lines.add("[[mainThreadDeal]]");
        lines.add("    }");
        lines.add("");
        lines.add("[[sendMessageMethods]]");
        lines.add("}");
        lines.add("");

        return lines;
    }
}
