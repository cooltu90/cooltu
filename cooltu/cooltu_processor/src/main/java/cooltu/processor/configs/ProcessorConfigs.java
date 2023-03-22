package cooltu.processor.configs;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

import cooltu.lib4j.config.LibConfigs;

public class ProcessorConfigs extends LibConfigs {

    private Messager messager;

    public ProcessorConfigs(Messager messager) {
        this.messager = messager;
    }

    @Override
    public boolean isLog() {
        return true;
    }

    @Override
    public String getDefaultLogTag() {
        return "defaultLogTag";
    }

    @Override
    public void baseLog(int level, String tag, String msg) {
        messager.printMessage(Diagnostic.Kind.NOTE, msg);
    }
}
