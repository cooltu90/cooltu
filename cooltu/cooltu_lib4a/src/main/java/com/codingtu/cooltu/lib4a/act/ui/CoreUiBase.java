package com.codingtu.cooltu.lib4a.act.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;

import com.codingtu.cooltu.lib4a.act.OnActBack;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.act.WhenKeyDown;
import com.codingtu.cooltu.lib4a.act.ui.core.CoreUiInterface;
import com.codingtu.cooltu.lib4a.permission.PermissionBack;
import com.codingtu.cooltu.lib4a.tools.ToastTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

import java.util.ArrayList;
import java.util.List;

public class CoreUiBase implements CoreUiInterface {

    @Override
    public CoreUiBase getBase() {
        return this;
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    protected List<PermissionBack> permissionBacks;

    public List<PermissionBack> getPermissionBacks() {
        if (permissionBacks == null)
            permissionBacks = new ArrayList<PermissionBack>();
        return permissionBacks;
    }

    public void addPermissionBack(PermissionBack back) {
        getPermissionBacks().add(back);

    }

    public void removePermissionBack(PermissionBack back) {
        getPermissionBacks().remove(back);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        Ts.ls(getPermissionBacks(), new BaseTs.EachTs<PermissionBack>() {
            @Override
            public boolean each(int position, PermissionBack back) {
                back.back(requestCode, permissions, grantResults);
                return false;
            }
        });
    }

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {

    }

    /**************************************************
     *
     *
     *
     **************************************************/
    protected List<OnActBack> onActBacks;

    public List<OnActBack> getOnActBacks() {
        if (onActBacks == null)
            onActBacks = new ArrayList<OnActBack>();
        return onActBacks;
    }

    public void addOnActBack(OnActBack onActBack) {
        getOnActBacks().add(onActBack);
    }

    public void removeOnActBack(OnActBack onActBack) {
        getOnActBacks().remove(onActBack);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Ts.ls(getOnActBacks(), new BaseTs.EachTs<OnActBack>() {
            @Override
            public boolean each(int position, OnActBack back) {
                back.onActivityResult(requestCode, resultCode, data);
                return false;
            }
        });
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    protected List<WhenKeyDown> whenKeyDowns;

    protected List<WhenKeyDown> getWhenKeyDowns() {
        if (whenKeyDowns == null)
            whenKeyDowns = new ArrayList<WhenKeyDown>();
        return whenKeyDowns;
    }

    public void addWhenKeyDown(WhenKeyDown whenKeyDown) {
        getWhenKeyDowns().add(whenKeyDown);
    }

    public void removeWhenKeyDown(WhenKeyDown whenKeyDown) {
        getWhenKeyDowns().remove(whenKeyDown);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        final boolean[] b = {false};
        List<WhenKeyDown> whenKeyDowns = getWhenKeyDowns();
        Ts.ls(whenKeyDowns, new BaseTs.EachTs<WhenKeyDown>() {
            @Override
            public boolean each(int position, WhenKeyDown whenKeyDown) {
                if (whenKeyDown.onKeyDown(keyCode, event)) {
                    b[0] = true;
                }
                return false;
            }
        });
        return b[0];
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    protected List<OnDestroy> onDestroys;

    public List<OnDestroy> getOnDestroys() {
        if (onDestroys == null)
            onDestroys = new ArrayList<OnDestroy>();
        return onDestroys;
    }

    public void add(OnDestroy onDestroy) {
        getOnDestroys().add(onDestroy);
    }

    public void destroyAll() {
        Ts.ls(getOnDestroys(), new BaseTs.EachTs<OnDestroy>() {
            @Override
            public boolean each(int position, OnDestroy onDestroy) {
                onDestroy.destroy();
                return false;
            }
        });
    }


    /**************************************************
     *
     *
     *
     **************************************************/
    public void toast(String str) {
        ToastTool.toast(str);
    }

    @Override
    public Activity getAct() {
        return null;
    }

}
