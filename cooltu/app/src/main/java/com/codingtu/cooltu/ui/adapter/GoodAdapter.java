package com.codingtu.cooltu.ui.adapter;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.ui.adapter.CoreListAdapter;
import com.codingtu.cooltu.processor.annotation.ui.VH;

import core.vh.GoodVH;

@VH(R.layout.item_good)
public class GoodAdapter extends CoreListAdapter<GoodVH, String> {
    @Override
    protected void onBindVH(@NonNull GoodVH vh, int position, String s) {

    }
}
