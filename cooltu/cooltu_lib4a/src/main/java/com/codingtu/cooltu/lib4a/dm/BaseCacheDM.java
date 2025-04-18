package com.codingtu.cooltu.lib4a.dm;

import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

import java.util.HashMap;
import java.util.Map;

public class BaseCacheDM {

    public static Map<String, Object> map;


    public static void cache(Destroys destroys, String key, Object obj) {
        CacheDestroy cacheDestroy = CacheDestroy.obtain(key);
        destroys.add(cacheDestroy);
        getMap().put(key, obj);
    }

    public static <T> T getCache(String key) {
        return (T) getMap().get(key);
    }

    private static Map<String, Object> getMap() {
        if (map == null) {
            map = new HashMap<>();
        }
        return map;
    }

    public static class CacheDestroy implements OnDestroy {

        public String key;

        public static CacheDestroy obtain(String key) {
            CacheDestroy cacheDestroy = new CacheDestroy();
            cacheDestroy.key = key;
            return cacheDestroy;
        }

        @Override
        public void destroy() {
            BaseCacheDM.getMap().remove(key);
        }
    }


}
