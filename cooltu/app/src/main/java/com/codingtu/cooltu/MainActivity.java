package com.codingtu.cooltu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.log.Logs;

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