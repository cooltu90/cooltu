package com.codingtu.cooltu;

import android.os.Bundle;

import androidx.annotation.Nullable;

import cooltu.lib4a.activity.CoreActivity;

public class MainActiviyBase extends MainActivityRes {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.real_activity_main);
    }
}
