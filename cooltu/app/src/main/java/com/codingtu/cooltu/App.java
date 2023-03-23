package com.codingtu.cooltu;

import cooltu.lib4a.CoreApp;
import cooltu.lib4j.config.LibConfigs;

public class App extends CoreApp {
    @Override
    protected LibConfigs createConfigs() {
        return new Configs();
    }
}
