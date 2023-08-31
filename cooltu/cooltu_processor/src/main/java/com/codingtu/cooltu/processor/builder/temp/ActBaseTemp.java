package com.codingtu.cooltu.processor.builder.temp;

public class ActBaseTemp {

    public StringBuilder actName = new StringBuilder();
    public StringBuilder baseClass = new StringBuilder();
    public StringBuilder onClicks = new StringBuilder();

}
/* model_temp_start
package core.actbase;

import android.os.Bundle;

public abstract class [<[actName]>]Base extends [<[baseClass]>] {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
    }

    @Override
    public void onClick(View view) {
[<[onClicks]>]
    }

}
model_temp_end */
