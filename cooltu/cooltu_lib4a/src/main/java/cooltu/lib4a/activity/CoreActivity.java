package cooltu.lib4a.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cooltu.lib4a.tools.ActTool;
import cooltu.lib4a.tools.ScreenAdaptationTool;
import cooltu.lib4a.tools.StatusBarTool;

public class CoreActivity extends AppCompatActivity {

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

}
