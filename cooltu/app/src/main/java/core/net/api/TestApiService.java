package core.net.api;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public interface TestApiService {
    @retrofit2.http.GET("/getObj/{ids}")
    Flowable<Result<ResponseBody>> getObj(
            java.lang.String token,
            java.lang.String id,
            java.lang.String order
    );
    @retrofit2.http.POST("/addObj")
    Flowable<Result<ResponseBody>> addObj(
            java.lang.String name,
            int age
    );
    @retrofit2.http.POST("/addObj")
    Flowable<Result<ResponseBody>> addObj(
            com.codingtu.cooltu.bean.User user
    );


}
