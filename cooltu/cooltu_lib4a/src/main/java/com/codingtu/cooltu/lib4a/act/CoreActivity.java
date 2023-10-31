package com.codingtu.cooltu.lib4a.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codingtu.cooltu.lib4a.act.ui.core.CoreActInterface;
import com.codingtu.cooltu.lib4a.act.ui.CoreUiBase;
import com.codingtu.cooltu.lib4a.bus.Bus;
import com.codingtu.cooltu.lib4a.bus.BusStation;
import com.codingtu.cooltu.lib4a.tools.ActTool;
import com.codingtu.cooltu.lib4a.tools.ScreenAdaptationTool;
import com.codingtu.cooltu.lib4a.tools.StatusBarTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

import java.util.ArrayList;
import java.util.List;

public class CoreActivity extends AppCompatActivity implements CoreActInterface {

    protected ViewGroup rootView;
    protected CoreUiBase base;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        base = new CoreUiBase();
        base.addPermissionBack(this);
        ScreenAdaptationTool.setCustomDensity(this);
        initStatusBar();
        ActivityManager.getInstance().add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getBase().destroyAll();
        removeBuses();
        ActivityManager.getInstance().remove(this);
    }

    protected void initStatusBar() {
        StatusBarTool.translucentAndDark(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        rootView = ViewTool.getRootViewGroup(this);
        rootView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        onViewInitComplete();
                        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
    }

    protected void onViewInitComplete() {

    }

    protected void onCreateComplete() {

    }

    protected void forbidKeyBack() {
        getBase().addWhenKeyDown(new WhenBackKeyDown() {
            @Override
            public boolean onBack(KeyEvent event) {
                return true;
            }
        });
    }

    protected boolean isLogin() {
        return false;
    }

    /********************************
     *
     * 对finish方法做扩展
     *
     ********************************/
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

    /************************************************
     *
     * setResultOk
     *
     ************************************************/
    protected void setResultOk() {
        setResult(RESULT_OK);
    }

    protected void setResultOk(Intent data) {
        setResult(RESULT_OK, data);
    }

    /**************************************************
     *
     *
     *
     **************************************************/
    protected List<Bus> busMap = new ArrayList<>();

    protected void addBus(Bus bus) {
        busMap.add(bus);
        BusStation.add(bus);
    }

    private void removeBuses() {
        Ts.ls(busMap, new BaseTs.EachTs<Bus>() {
            @Override
            public boolean each(int position, Bus bus) {
                BusStation.remove(bus);
                return false;
            }
        });
        busMap.clear();
        busMap = null;
    }

    /**************************************************
     *
     * 分割线
     *
     **************************************************/
    @Override
    public CoreUiBase getBase() {
        return this.base;
    }

    @Override
    public Activity getAct() {
        return this;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getBase().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getBase().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean b = getBase().onKeyDown(keyCode, event);
        return b ? b : super.onKeyDown(keyCode, event);
    }

    @Override
    public void toast(String str) {
        getBase().toast(str);
    }

    @Override
    public void add(OnDestroy onDestroy) {
        getBase().add(onDestroy);
    }

    @Override
    public void destroyAll() {
        getBase().destroyAll();
    }

}
