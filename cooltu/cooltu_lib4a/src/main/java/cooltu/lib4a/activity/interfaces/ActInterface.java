package cooltu.lib4a.activity.interfaces;

import android.app.Activity;

import cooltu.lib4a.destory.Destroys;

public interface ActInterface extends Destroys {

    Activity getAct();

    void toast(String msg);

}
