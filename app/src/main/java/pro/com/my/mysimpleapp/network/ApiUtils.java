package pro.com.my.mysimpleapp.network;

public class ApiUtils {

    public static final String BASE_URL = "https://rbmq6lgs50.execute-api.us-east-1.amazonaws.com/";

    public static LoginService doLogin() {
        return RetrofitClient.getClient(BASE_URL).create(LoginService.class);
    }
}
