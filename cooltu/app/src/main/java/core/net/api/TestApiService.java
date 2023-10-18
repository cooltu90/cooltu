package core.net.api;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public interface TestApiService {

    @retrofit2.http.POST("/getObj/{ids}")
    Flowable<Result<ResponseBody>> getObj(
            @retrofit2.http.Header("token") java.lang.String token,
            @retrofit2.http.Path("ids") java.lang.String id,
            @retrofit2.http.Query(value = "order[desc]", encoded = true) java.lang.String order
    );

}
