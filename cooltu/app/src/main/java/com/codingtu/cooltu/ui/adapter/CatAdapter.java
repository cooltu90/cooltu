package com.codingtu.cooltu.ui.adapter;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.ui.adapter.CoreMoreListAdapter;
import com.codingtu.cooltu.processor.annotation.ui.VH;

import core.vh.CatVH;

@VH(layout = R.layout.item_cat, vh = CatVH.class)
public abstract class CatAdapter extends CoreMoreListAdapter<CatVH, String> {
    @Override
    protected void onBindVH(CatVH vh, int position, String s) {

    }
}
