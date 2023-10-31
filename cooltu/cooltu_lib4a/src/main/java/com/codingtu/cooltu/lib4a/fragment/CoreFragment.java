package com.codingtu.cooltu.lib4a.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codingtu.cooltu.lib4a.act.ui.CoreUiBase;
import com.codingtu.cooltu.lib4a.act.ui.core.CoreUiInterface;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.bus.Bus;
import com.codingtu.cooltu.lib4a.bus.BusStation;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

import java.util.ArrayList;
import java.util.List;

public class CoreFragment extends Fragment implements CoreUiInterface {

    private CoreUiBase base;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        base = new CoreUiBase();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeBuses();
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
     *
     *
     **************************************************/
    @Override
    public CoreUiBase getBase() {
        return base;
    }

    @Override
    public void toast(String msg) {
        getBase().toast(msg);
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }

    @Override
    public void add(OnDestroy onDestroy) {
        getBase().add(onDestroy);
    }

    @Override
    public void destroyAll() {
        getBase().destroyAll();
    }

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {

    }
}
