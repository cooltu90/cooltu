package com.codingtu.cooltu.dm;

import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.bean.Weather;
import com.codingtu.cooltu.lib4j.ts.BaseTs;
import com.codingtu.cooltu.processor.annotation.dm.Cache;

public class CacheDMConfigs {

    @Cache(key = "userId")
    public User user;

    @Cache(key = "id")
    public BaseTs<Weather> weatherTs;

    @Cache
    public Weather weather;

}
