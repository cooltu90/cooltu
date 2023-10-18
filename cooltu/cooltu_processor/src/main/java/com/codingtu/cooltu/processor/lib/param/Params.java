package com.codingtu.cooltu.processor.lib.param;

import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

import java.util.ArrayList;
import java.util.List;

public class Params {
    private List<KV<String, String>> kvs;


    public static Params obtain(List<KV<String, String>> kvs) {
        Params params = new Params();
        params.kvs = kvs;
        return params;
    }

    public List<KV<String, String>> getKvs() {
        return kvs;
    }

    public void add(String type, String name) {
        if (kvs == null) {
            kvs = new ArrayList<>();
        }
        KV<String, String> kv = new KV<>();
        kv.k = type;
        kv.v = name;
        kvs.add(kv);
    }

    public String getMethodParams() {
        StringBuilder sb = new StringBuilder();
        Ts.ls(kvs, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                if (position != 0) {
                    sb.append(", ");
                }
                sb.append(kv.k).append(" ").append(kv.v);
                return false;
            }
        });
        return sb.toString();
    }

    public String getMethodParams(boolean hasFirst, boolean hasNext) {
        StringBuilder sb = new StringBuilder();
        int count = CountTool.count(kvs);
        if (count > 0 && hasFirst) {
            sb.append(", ");
        }
        Ts.ls(kvs, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                if (position != 0) {
                    sb.append(", ");
                }
                sb.append(kv.k).append(" ").append(kv.v);
                return false;
            }
        });

        if (count > 0 && hasNext) {
            sb.append(", ");
        }

        return sb.toString();
    }

    public int count() {
        return CountTool.count(kvs);
    }
}
