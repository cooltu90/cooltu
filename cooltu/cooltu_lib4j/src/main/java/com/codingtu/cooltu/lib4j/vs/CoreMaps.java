package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.json.JsonTool;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.tools.OtherTool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class CoreMaps<K, V, THIS extends CoreMaps, VS extends CoreVs> {

    ///////////////////////////////////////////////////////
    //
    // 数据
    //
    ///////////////////////////////////////////////////////
    protected Map<K, V> map;

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public CoreMaps() {
        this.map = new HashMap<>();
    }

    public CoreMaps(Map<K, V> map) {
        if (map == null) {
            this.map = new HashMap<>();
        } else {
            this.map = map;
        }
    }

    ///////////////////////////////////////////////////////
    //
    // put方法
    //
    ///////////////////////////////////////////////////////
    public THIS put(K k, V v) {
        this.map.put(k, v);
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // get方法
    //
    ///////////////////////////////////////////////////////
    public V get(K k) {
        return this.map.get(k);
    }

    public KV<K, V> getKV(K k) {
        V v = this.map.get(k);
        if (v != null) {
            return new KV<>(k, v);
        }
        return null;
    }

    ///////////////////////////////////////////////////////
    //
    // each
    //
    ///////////////////////////////////////////////////////
    public THIS ls(Vs.MapEach<K, V> mapEach) {
        Set<K> ks = this.map.keySet();
        for (K k : ks) {
            if (mapEach.each(k, map.get(k))) {
                return (THIS) this;
            }
        }
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // logs
    //
    ///////////////////////////////////////////////////////
    public THIS log() {
        ls(new Vs.MapEach<K, V>() {
            @Override
            public boolean each(K k, V v) {
                LibLogs.i("k:" + k + " v:" + JsonTool.toJson(v));
                return false;
            }
        });
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // delete
    //
    ///////////////////////////////////////////////////////
    public THIS delete(K k) {
        this.map.remove(k);
        return (THIS) this;
    }

    private VS createVs() {
        try {
            return (VS) OtherTool.getFanxing(this, 3).getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public VS getValueList() {
        VS vs = createVs();
        ls(new Vs.MapEach<K, V>() {
            @Override
            public boolean each(K k, V v) {
                vs.add(v);
                return false;
            }
        });
        return vs;
    }


}
