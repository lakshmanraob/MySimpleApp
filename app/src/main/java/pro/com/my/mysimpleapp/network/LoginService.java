package pro.com.my.mysimpleapp.network;

import java.util.List;

import pro.com.my.mysimpleapp.models.LoginResponse;
import pro.com.my.mysimpleapp.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("dev/login")
    Call<List<LoginResponse>> doLogin(@Body User user);

}
