package core.tools;

import com.codingtu.cooltu.lib4a.dm.BaseCacheDM;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public class CacheDM {

    public static void cacheUser(Destroys destroys, String userId, com.codingtu.cooltu.bean.User user) {
        BaseCacheDM.cache(destroys, "user" + userId, user);
    }

    public static com.codingtu.cooltu.bean.User getUser(String userId) {
        return BaseCacheDM.getCache("user" + userId);
    }
    public static void cacheWeatherTs(Destroys destroys, String id, com.codingtu.cooltu.lib4j.ts.BaseTs<com.codingtu.cooltu.bean.Weather> weatherTs) {
        BaseCacheDM.cache(destroys, "weatherTs" + id, weatherTs);
    }

    public static com.codingtu.cooltu.lib4j.ts.BaseTs<com.codingtu.cooltu.bean.Weather> getWeatherTs(String id) {
        return BaseCacheDM.getCache("weatherTs" + id);
    }

}
