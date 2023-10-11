package com.codingtu.cooltu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import core.path.CheckDeleteLabelPath;
import core.path.CheckPath;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckPath checkPath = CheckPath.obtain("xx", "xxx");
        checkPath.DeleteLabel.label("L-xxxx");

    }
}