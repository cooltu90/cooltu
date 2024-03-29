package com.codingtu.cooltu.lib4a.connect;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.connect.device.ConnectDevice;
import com.codingtu.cooltu.lib4j.ts.Maps;
import com.codingtu.cooltu.lib4j.ts.Ts;

public class ConnectService extends Service {
    /**************************************************
     *
     * 可以忽略的地方
     *
     **************************************************/
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**************************************************
     *
     * 创建
     *
     **************************************************/
    @Override
    public void onCreate() {
        super.onCreate();
        ConnectTool.SERVICE = this;
        Maps.map(ConnectTool.preparedDevices()).ls(new Ts.MapEach<Integer, ConnectDevice>() {
            @Override
            public boolean each(Integer connectType, ConnectDevice connectDevice) {
                run(connectDevice);
                return false;
            }
        });
        ConnectTool.preparedDevices().clear();
    }

    public void run(ConnectDevice connectDevice) {
        connectDevice.connect();
    }
}
