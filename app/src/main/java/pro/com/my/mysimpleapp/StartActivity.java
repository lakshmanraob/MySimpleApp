package pro.com.my.mysimpleapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import pro.com.my.mysimpleapp.fragments.WelcomeFragment;
import pro.com.my.mysimpleapp.models.event.BackBtnEvent;
import pro.com.my.mysimpleapp.models.event.ScreenChangeEvent;
import pro.com.my.mysimpleapp.utils.BaseActivity;
import pro.com.my.mysimpleapp.utils.BaseFragment;
import pro.com.my.mysimpleapp.utils.UiUtils;

import static pro.com.my.mysimpleapp.utils.Constants.FRAGMENT_LOGIN;
import static pro.com.my.mysimpleapp.utils.Constants.OPERATION_ADD;
import static pro.com.my.mysimpleapp.utils.Constants.OPERATION_DELETE;
import static pro.com.my.mysimpleapp.utils.Constants.OPERATION_REPLACE;

public class StartActivity extends BaseActivity {

    final Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WelcomeFragment welcomeFragment = WelcomeFragment.getInstance();
        addFragment(welcomeFragment);

        handler.postDelayed(runnable, 5000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            BaseFragment loginFragment = UiUtils.getFragment(FRAGMENT_LOGIN);
            replaceFragment(loginFragment);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventReceived(ScreenChangeEvent event) {
        int action = event.getOperatoionIndex();
        int fragmentIndex = event.getFragmentIndex();
        BaseFragment fragment = UiUtils.getFragment(fragmentIndex);
        switch (action) {
            case OPERATION_ADD:
                addFragment(fragment);
                break;
            case OPERATION_REPLACE:
                replaceFragment(fragment);
                break;
            case OPERATION_DELETE:
                break;
            default:
                replaceFragment(fragment);
                break;
        }
    }

    @Subscribe
    public void handleBackBtn(BackBtnEvent event) {
        onBackPressed();
    }

}