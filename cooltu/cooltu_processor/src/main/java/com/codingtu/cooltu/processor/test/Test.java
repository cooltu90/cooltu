package com.codingtu.cooltu.processor.test;

import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        ArrayList<String> strings = new ArrayList<>();

        String params = test.getParams(strings, true, true);
        System.out.println(params);
    }

    protected String getParams(List<String> params, boolean hasPre, boolean hasNext) {
        if (params == null) {
            params = new ArrayList<>();
        }
        if (hasPre) {
            params.add(0, "");
        }
        if (hasNext) {
            params.add("");
        }
        StringBuilder sb = new StringBuilder();
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
        return sb.toString();
    }


}
