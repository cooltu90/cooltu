package com.codingtu.cooltu.processor.builder.builderbase;

import com.codingtu.cooltu.processor.builder.builder.base.BaseBuilder;

public abstract class ActBaseBuilderBase extends BaseBuilder {
    public StringBuilder actName;
    public StringBuilder baseClass;
    public StringBuilder layout;
    @Override
    protected void createStringBuilders() {
        actName = map.get("actName");
        baseClass = map.get("baseClass");
        layout = map.get("layout");
    }
}
/* model_temp_start
package core.actbase;

import android.os.Bundle;

public abstract class <[[actName]]>Base extends <[[baseClass]]> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(<[[layout]]>);
    }

    @Override
    public void onClick(View view) {
    }
}
model_temp_end */
