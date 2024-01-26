package com.codingtu.cooltu.lib4j.ts;

import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.data.symbol.Symbol;
import com.codingtu.cooltu.lib4j.log.LibLogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maps<K, V> {

    protected Map<K, V> map;

    public static <K, V> Maps<K, V> map(Map<K, V> map) {
        Maps<K, V> maps = new Maps<>();
        if (map == null) {
            maps.map = new HashMap<>();
        } else {
            maps.map = map;
        }
        return maps;
    }

    public static <SYMBOL extends Symbol> SymbolMaps<SYMBOL> symbolMaps(Map<String, SYMBOL> map) {
        SymbolMaps<SYMBOL> maps = new SymbolMaps<>();
        if (map == null) {
            maps.map = new HashMap<>();
        } else {
            maps.map = map;
        }
        return maps;
    }

    public void ls(Ts.MapEach<K, V> mapEach) {
        Ts.ts(map.keySet()).ls(new Ts.EachTs<K>() {
            @Override
            public boolean each(int position, K k) {
                mapEach.each(k, map.get(k));
                return false;
            }
        });
    }

    public Maps<K, V> log() {
        ls(new Ts.MapEach<K, V>() {
            @Override
            public boolean each(K k, V v) {
                LibLogs.i("k:" + v + " v:" + v);
                return false;
            }
        });
        return this;
    }


    public V get(K k) {
        return map.get(k);
    }

    public KV<K, V> getKV(K k) {
        V v = map.get(k);
        if (v != null) {
            return new KV<>(k, v);
        }
        return null;
    }

    public Maps<K, V> put(K k, V v) {
        map.put(k, v);
        return this;
    }

    public Maps<K, V> delete(K k) {
        map.remove(k);
        return this;
    }

    public List<V> getValueList() {
        ArrayList<V> list = new ArrayList<>();
        ls(new Ts.MapEach<K, V>() {
            @Override
            public boolean each(K k, V v) {
                list.add(v);
                return false;
            }
        });
        return list;
    }

    public BaseTs<V> toValueTs() {
        List<V> list = getValueList();
        return Ts.ts(list);
    }


}
