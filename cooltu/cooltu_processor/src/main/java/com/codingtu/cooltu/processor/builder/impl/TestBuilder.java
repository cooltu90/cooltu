package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.builder.base.TestBuilderBase;
import com.codingtu.cooltu.processor.builder.base.TestBuilderBaseTemp;
import com.codingtu.cooltu.processor.lib.log.Logs;

public class TestBuilder extends TestBuilderBase {

    public TestBuilder(JavaInfo info) {
        super(info);
        isForce = true;
        addTag(pkg, "com.codingtu.cooltu.processor.test");
        addTag(name, "Test");

        linesCount(4);
        for (int i = 0; i < 4; i++) {
            linesAddCount(i, 5);
        }

    }

    @Override
    protected void dealLines() {

    }

    @Override
    public void create() {
        super.create();
        Logs.i("test create");
    }
}
/* model_temp_start
package [[pkg]];

import java.util.ArrayList;
import java.util.List;

public class [[name]] {

    private void add() {
        List<List<String>> strs = new ArrayList<>();
[sub[for[lines
        ArrayList<String> [name] = new ArrayList<>();
[sub[for[linesAdd
        [name].add("[value]");
]sub]for]linesAdd
        strs.add([name]);
]sub]for]lines
    }

}
model_temp_end */
