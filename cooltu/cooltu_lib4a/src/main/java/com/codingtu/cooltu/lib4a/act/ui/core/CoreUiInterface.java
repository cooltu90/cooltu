package com.codingtu.cooltu.lib4a.act.ui.core;

import android.app.Activity;

import com.codingtu.cooltu.lib4a.act.Destroys;
import com.codingtu.cooltu.lib4a.act.ui.CoreUiBase;
import com.codingtu.cooltu.lib4a.permission.PermissionBack;

public interface CoreUiInterface extends Destroys, PermissionBack {

    public CoreUiBase getBase();

    public void toast(String msg);

    public Activity getAct();
}
