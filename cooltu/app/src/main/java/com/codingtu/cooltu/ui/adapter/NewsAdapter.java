package com.codingtu.cooltu.ui.adapter;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.ui.adapter.CoreMoreListAdapter;
import com.codingtu.cooltu.processor.annotation.ui.VH;

import core.vh.UserVH;

@VH(layout = R.layout.item_user, vh = UserVH.class)
public abstract class NewsAdapter extends CoreMoreListAdapter<UserVH, String> {
    @Override
    protected void onBindVH(UserVH vh, int position, String s) {

    }
}
