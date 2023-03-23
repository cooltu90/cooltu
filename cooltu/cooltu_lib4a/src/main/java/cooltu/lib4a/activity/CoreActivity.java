package cooltu.lib4a.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cooltu.lib4a.tools.ScreenAdaptationTool;
import cooltu.lib4a.tools.StatusBarTool;

public class CoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenAdaptationTool.setCustomDensity(this);
        initStatusBar();
    }

    protected void initStatusBar() {
        StatusBarTool.translucentAndDark(this);
    }
}
