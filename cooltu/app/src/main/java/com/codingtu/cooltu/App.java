package com.codingtu.cooltu;

import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.lib4a.CoreApp;
import com.codingtu.cooltu.lib4a.CoreConfigs;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.annotation.create.CreateAct;

@ModuleInfo(
        module = Module.APP
)
@CreateAct(
        name = "welcome",
        packages = "com.codingtu.cooltu.ui"
)
public class App extends CoreApp {
    @Override
    public CoreConfigs createConfigs() {
        return null;
    }
}
