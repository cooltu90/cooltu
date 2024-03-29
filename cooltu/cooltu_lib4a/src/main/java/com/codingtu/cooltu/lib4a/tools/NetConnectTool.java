package com.codingtu.cooltu.lib4a.tools;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.codingtu.cooltu.lib4j.ts.Ts;

@SuppressLint("MissingPermission")
public class NetConnectTool {

    public static boolean isConnect() {
        ConnectivityManager manager = SystemTool.getConnectivityManager();
        NetworkInfo[] allNetworkInfo = manager.getAllNetworkInfo();

        return Ts.ts(allNetworkInfo).has(new Ts.IsThisOne<NetworkInfo>() {
            @Override
            public boolean isThisOne(int position, NetworkInfo networkInfo) {
                return networkInfo.getState() == NetworkInfo.State.CONNECTED;
            }
        });
    }

}
