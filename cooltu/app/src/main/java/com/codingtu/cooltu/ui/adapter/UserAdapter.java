package com.codingtu.cooltu.ui.adapter;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.R;
import com.codingtu.cooltu.lib4a.ui.adapter.CoreListAdapter;
import com.codingtu.cooltu.processor.annotation.ui.VH;

import core.vh.UserVH;

@VH(R.layout.item_user)
public class UserAdapter extends CoreListAdapter<UserVH, String> {
    @Override
    protected void onBindVH(@NonNull UserVH vh, int position, String s) {

    }
}
