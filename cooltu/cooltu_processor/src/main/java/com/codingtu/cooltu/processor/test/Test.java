package com.codingtu.cooltu.processor.test;

import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

import java.util.ArrayList;
import java.util.List;

import jdk.internal.perf.PerfCounter;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();

        String first = "String name";
        String last = "";

        ArrayList<String> strings = new ArrayList<>();
        String params = test.getParams(strings, StringTool.isNotBlank(first), StringTool.isNotBlank(last));
        System.out.println(first + params + last);
    }

    protected String getParams(List<String> params, boolean hasPre, boolean hasNext) {
        if (params == null) {
            params = new ArrayList<>();
        }
        StringBuilder sb = new StringBuilder();
        int count = CountTool.count(params);

        if (hasPre && count != 0) {
            sb.append(", ");
        }
        Ts.ls(params, new BaseTs.EachTs<String>() {
            @Override
            public boolean each(int position, String param) {
                if (position != 0) {
                    sb.append(", ");
                }
                sb.append(param);
                return false;
            }
        });
        if (hasNext) {
            if (hasPre || count > 0) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }


}
