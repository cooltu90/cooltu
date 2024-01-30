package com.codingtu.cooltu.lib4a.ui.recyclerview;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DefaultConfig implements Config {

    @Override
    public void config(Context context, RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(context));
    }
}
