package com.codingtu.cooltu;

import com.codingtu.cooltu.constant.Module;
import com.codingtu.cooltu.lib4a.CoreApp;
import com.codingtu.cooltu.lib4a.CoreConfigs;
import com.codingtu.cooltu.lib4a.ui.act.CoreActivity;
import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.annotation.create.CreateAct;

@ModuleInfo(
        module = Module.APP,
        actPkg = "com.codingtu.cooltu.ui",
        baseAct = CoreActivity.class,
        rPkg = "com.codingtu.cooltu"
)
@CreateAct(
        name = "step_one",
        packages = "com.codingtu.cooltu.ui",
        baseClass = CoreActivity.class,
        layoutTemp = R.layout.layout_temp
)
public class App extends CoreApp {
    @Override
    public CoreConfigs createConfigs() {
        return new Configs();
    }
}
