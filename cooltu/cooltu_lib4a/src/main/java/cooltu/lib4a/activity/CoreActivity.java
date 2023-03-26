package cooltu.lib4a.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cooltu.lib4a.activity.interfaces.ActInterface;
import cooltu.lib4a.activity.interfaces.OnActBack;
import cooltu.lib4a.activity.whenkeydown.WhenKeyDown;
import cooltu.lib4a.destory.OnDestroy;
import cooltu.lib4a.permission.PermissionBack;
import cooltu.lib4a.tools.ActTool;
import cooltu.lib4a.tools.ScreenAdaptationTool;
import cooltu.lib4a.tools.StatusBarTool;
import cooltu.lib4a.tools.ToastTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;

public class CoreActivity extends AppCompatActivity implements ActInterface {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenAdaptationTool.setCustomDensity(this);
        initStatusBar();
        ActivityManager.getInstance().add(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyAll();
        ActivityManager.getInstance().remove(this);
    }

    protected void initStatusBar() {
        StatusBarTool.translucentAndDark(this);
    }

    /**************************************************
     *
     * 对finish方法做扩展
     *
     **************************************************/
    @Override
    public void finish() {
        beforeFinish();
        super.finish();
        setFinishAnimation();
        afterFinish();
    }

    protected void finishToNewPage() {
        beforeFinish();
        super.finish();
        afterFinish();
    }

    //finish之后调用
    protected void afterFinish() {
    }

    //设置finish动画
    protected void setFinishAnimation() {
        ActTool.actRightOut(this);
    }

    //finish之前调用
    protected void beforeFinish() {
    }

    /**************************************************
     *
     * setResultOk
     *
     **************************************************/
    protected void setResultOk() {
        setResult(RESULT_OK);
    }

    protected void setResultOk(Intent data) {
        setResult(RESULT_OK, data);
    }

    /**************************************************
     *
     * getThis
     *
     **************************************************/
    @Override
    public Activity getAct() {
        return this;
    }

    /**************************************************
     *
     * toast
     *
     **************************************************/
    public void toast(String msg) {
        ToastTool.toast(msg);
    }

    /**************************************************
     *
     * WhenKeyDown
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        final boolean[] b = {false};
        List<WhenKeyDown> whenKeyDowns = getWhenKeyDowns();
        Ts.ls(whenKeyDowns, new Each<WhenKeyDown>() {
            @Override
            public boolean each(int position, WhenKeyDown whenKeyDown) {
                if (whenKeyDown.onKeyDown(keyCode, event)) {
                    b[0] = true;
                }
                return false;
            }
        });
        return b[0] ? b[0] : super.onKeyDown(keyCode, event);
    }

    public void removeWhenKeyDown(WhenKeyDown whenKeyDown) {
        getWhenKeyDowns().remove(whenKeyDown);
    }

    /**************************************************
     *
     * onDestory
     *
     **************************************************/
    protected List<OnDestroy> onDestroys;

    public List<OnDestroy> getOnDestroys() {
        if (onDestroys == null)
            onDestroys = new ArrayList<OnDestroy>();
        return onDestroys;
    }

    @Override
    public void add(OnDestroy onDestroy) {
        getOnDestroys().add(onDestroy);
    }

    @Override
    public void destroyAll() {
        Ts.ls(getOnDestroys(), new Each<OnDestroy>() {
            @Override
            public boolean each(int position, OnDestroy onDestroy) {
                onDestroy.destroy();
                return false;
            }
        });
    }

    /**************************************************
     *
     * 权限回调
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        onRequestPermissionsResultInCore(requestCode, permissions, grantResults);
    }

    public void onRequestPermissionsResultInCore(int requestCode, String[] permissions,
                                                 int[] grantResults) {
        Ts.ls(getPermissionBacks(), new Each<PermissionBack>() {
            @Override
            public boolean each(int position, PermissionBack back) {
                back.back(requestCode, permissions, grantResults);
                return false;
            }
        });
    }

    /************************************************
     *
     *
     *
     ************************************************/
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Ts.ls(getOnActBacks(), new Each<OnActBack>() {
            @Override
            public boolean each(int position, OnActBack back) {
                back.onActivityResult(requestCode, resultCode, data);
                return false;
            }
        });
    }
}
