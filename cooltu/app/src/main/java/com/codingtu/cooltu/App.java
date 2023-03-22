package com.codingtu.cooltu;

import android.app.Application;

import cooltu.processor.annotation.ModuleInfo;

@ModuleInfo(
        module = "app",
        baseAct = BaseActivity.class,
        baseFragment = BaseFragment.class,
        defaultRPackage = "com.codingtu.cooltu",
        mockPackage = "",
        defaultLayout = "")
public class App extends Application {
}
