package com.codingtu.cooltu.ui.adapter;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.ui.adapter.CoreMoreListAdapter;
import com.codingtu.cooltu.processor.annotation.ui.VH;

import core.vh.FoodVH;

@VH(R.layout.item_food)
public abstract class FoodAdapter extends CoreMoreListAdapter<FoodVH, String> {
    @Override
    protected void onBindVH(FoodVH vh, int position, String s) {

    }
}
