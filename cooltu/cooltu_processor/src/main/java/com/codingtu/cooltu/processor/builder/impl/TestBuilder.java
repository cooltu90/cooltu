package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.processor.builder.base.TestBuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;

public class TestBuilder extends TestBuilderBase {

    public TestBuilder(JavaInfo info) {
        super(info);
        isForce = true;
        addTag(pkg, "com.codingtu.cooltu.processor.test");
        addTag(name, "Test");

//        linesCount(4);
//        for (int i = 0; i < 4; i++) {
//            int num = Fake.nextInt(1, 8);
//            linesAddCount(i, num);
//            lines(i, "lines" + i, "lines" + i);
//            for (int j = 0; j < num; j++) {
//                linesAdd1If(i, j, true);
//                linesAdd1(i, j, "lines" + i, Fake.name(), Fake.name());
//            }
//        }

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
--------------------------------------------------------------------------------[lines]
        ArrayList<String> [name] = new ArrayList<>();
--------------------------------------------------------------------------------[linesAdd]
================================================================================[linesAdd1]
        [name].add("[value][v1]");
================================================================================[linesAdd1]
--------------------------------------------------------------------------------[linesAdd]
        strs.add([name]);
--------------------------------------------------------------------------------[lines]
    }

}
model_temp_end */
