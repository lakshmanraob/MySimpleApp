package pro.com.my.mysimpleapp.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import pro.com.my.mysimpleapp.R;
import pro.com.my.mysimpleapp.models.LoginResponse;
import pro.com.my.mysimpleapp.models.User;
import pro.com.my.mysimpleapp.models.event.ScreenChangeEvent;
import pro.com.my.mysimpleapp.network.ApiUtils;
import pro.com.my.mysimpleapp.network.LoginService;
import pro.com.my.mysimpleapp.utils.BaseFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static pro.com.my.mysimpleapp.utils.Constants.FRAGMENT_FORGOT;
import static pro.com.my.mysimpleapp.utils.Constants.FRAGMENT_OUTLET_MAP;
import static pro.com.my.mysimpleapp.utils.Constants.FRAGMENT_SIGN_UP;
import static pro.com.my.mysimpleapp.utils.Constants.OPERATION_REPLACE;


public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private static LoginFragment loginFragment = null;
    private LoginView loginView = null;

    private LoginService mService;
    User user = null;

    public static LoginFragment getInstance() {
        if (loginFragment == null) {
            loginFragment = new LoginFragment();
        }
        return loginFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.login_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginView = new LoginView(view);
        initViews();
        mService = ApiUtils.doLogin();
    }

    private void initViews() {
        loginView.login_txt.setOnClickListener(this);
        loginView.forgot_pin.setOnClickListener(this);
        loginView.signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ScreenChangeEvent event = new ScreenChangeEvent();
        switch (v.getId()) {
            case R.id.login_txt:
                user = new User(loginView.edit_login_phone_number.getText().toString(),
                        loginView.edit_login_pin_number.getText().toString());
                authenticate(user);
                break;
            case R.id.forgot_pin:
                event.setOperatoionIndex(OPERATION_REPLACE);
                event.setFragmentIndex(FRAGMENT_FORGOT);
                EventBus.getDefault().post(event);
                break;
            case R.id.signup:
                event.setOperatoionIndex(OPERATION_REPLACE);
                event.setFragmentIndex(FRAGMENT_SIGN_UP);
                EventBus.getDefault().post(event);
                break;
        }

    }

    public void authenticate(User user) {

        mService.doLogin(user).enqueue(new Callback<List<LoginResponse>>() {
            @Override
            public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {

                if (response.isSuccessful()) {
//                    mAdapter.updateAnswers(response.body().getItems());
                    Log.d("MainActivity", "posts loaded from API");
                    ScreenChangeEvent event = new ScreenChangeEvent();
                    event.setOperatoionIndex(OPERATION_REPLACE);
                    event.setFragmentIndex(FRAGMENT_OUTLET_MAP);
                    EventBus.getDefault().post(event);
                } else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "posts loaded from API " + statusCode);
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<List<LoginResponse>> call, Throwable t) {

                Log.d("MainActivity", "error loading from API");

            }
        });
    }

    public class LoginView {

        private final TextInputLayout login_phone_number;
        private final TextInputLayout login_pin_number;

        private final EditText edit_login_phone_number;
        private final EditText edit_login_pin_number;

        private final TextView forgot_pin;
        private final TextView signup;

        private final TextView login_txt;


        public LoginView(View view) {

            login_phone_number = view.findViewById(R.id.login_phone_layout);
            edit_login_phone_number = view.findViewById(R.id.phone_number_entry);
            login_pin_number = view.findViewById(R.id.login_pin_layout);
            edit_login_pin_number = view.findViewById(R.id.pin_entry);

            forgot_pin = view.findViewById(R.id.forgot_pin);
            signup = view.findViewById(R.id.signup);

            login_txt = view.findViewById(R.id.login_txt);

        }
    }
}
