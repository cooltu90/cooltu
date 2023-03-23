package cooltu.lib4a.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;


public class ActivityManager {

    private List<Activity> acts;

    private ActivityManager() {
    }

    private static final class SingleInstance {
        public static final ActivityManager INSTANCE = new ActivityManager();
    }

    public static ActivityManager getInstance() {
        return SingleInstance.INSTANCE;
    }

    public void add(Activity act) {
        if (acts == null)
            acts = new ArrayList<Activity>();
        acts.add(act);
    }

    public void remove(Activity act) {
        if (acts != null) {
            acts.remove(act);
        }
    }

    public void finishAll() {
        if (acts != null) {
            Ts.ls(acts, new Each<Activity>() {
                @Override
                public boolean each(int position, Activity activity) {
                    activity.finish();
                    return false;
                }
            });
            acts.clear();
        }
    }

    public Activity getLastOne() {
        return acts.get(acts.size() - 1);
    }

}