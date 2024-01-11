package com.codingtu.cooltu.lib4a.ui.fragment;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.codingtu.cooltu.lib4a.tools.ToastTool;
import com.codingtu.cooltu.lib4a.uicore.CoreFragmentInterface;

public class CoreFragment extends Fragment implements CoreFragmentInterface {

    @Override
    public void toast(String msg) {
        ToastTool.toast(msg);
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }

    @Override
    public void onCreateComplete() {

    }

}
