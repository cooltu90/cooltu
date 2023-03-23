package cooltu.lib4a;

import android.app.Application;

import androidx.annotation.NonNull;

import cooltu.lib4a.logs.Logs;
import cooltu.lib4j.config.LibApp;
import cooltu.lib4j.config.LibConfigs;

public abstract class CoreApp extends Application implements Thread.UncaughtExceptionHandler {

    public static CoreApp APP;

    @Override
    public void onCreate() {
        super.onCreate();
        APP = this;
        LibApp.APP = new LibApp() {
            @Override
            protected LibConfigs createConfigs() {
                return CoreApp.this.createConfigs();
            }
        };
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    protected abstract LibConfigs createConfigs();


    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Logs.w(e);
    }
}
