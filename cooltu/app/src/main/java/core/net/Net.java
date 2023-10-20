package core.net;

import com.codingtu.cooltu.lib4a.net.NetTool;
import com.codingtu.cooltu.lib4a.net.api.API;
import com.codingtu.cooltu.lib4a.net.api.CreateApi;
import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;

public class Net {

    private static final String GET_OBJ = "getObjBack";

    private static final String BASE_URL_TEST_API = "https://www.xxxxxxxxx.com";

    public static API getObj(java.lang.String id, java.lang.String order) {
        core.net.params.GetObjParams params = new core.net.params.GetObjParams();
        params.token = "stDXFdsdff";
        params.id = id;
        params.order = order;

        return NetTool.api(new CreateApi() {
            @Override
            public Flowable<Result<ResponseBody>>
            create(Retrofit retrofit, CoreSendParams ps) {
                core.net.params.GetObjParams params = (core.net.params.GetObjParams) ps;

                return retrofit.create(core.net.api.TestApiService.class).getObj(
                        params.token,
                        params.id,
                        params.order
                );
            }
        }, GET_OBJ, BASE_URL_TEST_API, params);
    }

}
