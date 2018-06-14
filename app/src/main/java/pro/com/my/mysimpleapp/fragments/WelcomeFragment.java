package pro.com.my.mysimpleapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import pro.com.my.mysimpleapp.R;
import pro.com.my.mysimpleapp.utils.BaseFragment;

public class WelcomeFragment extends BaseFragment {

    public static WelcomeFragment welcomeFragment = null;

    @Override
    protected int getLayoutResource() {
        return R.layout.welcome_fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public static WelcomeFragment getInstance() {
        if (welcomeFragment == null) {
            welcomeFragment = new WelcomeFragment();
        }
        return welcomeFragment;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
