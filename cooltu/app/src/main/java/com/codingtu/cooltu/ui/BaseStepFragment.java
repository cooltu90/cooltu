package com.codingtu.cooltu.ui;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.processor.annotation.tools.To;
import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.FragmentBase;

import core.fragmentbase.BaseStepFragmentBase;
import core.fragmentres.BaseStepFragmentRes;

@To(BaseStepFragmentRes.class)
@FragmentBase
public class BaseStepFragment extends BaseStepFragmentBase {

    @ClickView(R.id.tv2)
    public void tv2Click() {

    }

}
