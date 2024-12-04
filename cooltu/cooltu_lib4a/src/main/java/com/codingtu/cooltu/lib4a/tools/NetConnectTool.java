package com.codingtu.cooltu.lib4a.tools;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4j.ts.StringTs;
import com.codingtu.cooltu.lib4j.ts.Ts;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

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

    public static StringTs getHostIPs() {
        StringTs strs = Ts.strs();
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        strs.add(ia.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            Logs.e(e);
        }
        return strs;
    }

}
