package pro.com.my.mysimpleapp.utils;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import pro.com.my.mysimpleapp.fragments.ForgotPinFragment;
import pro.com.my.mysimpleapp.fragments.LoginFragment;
import pro.com.my.mysimpleapp.fragments.OutletMapFragment;
import pro.com.my.mysimpleapp.fragments.SignupFragment;
import pro.com.my.mysimpleapp.fragments.WelcomeFragment;

import static pro.com.my.mysimpleapp.utils.Constants.FRAGMENT_FORGOT;
import static pro.com.my.mysimpleapp.utils.Constants.FRAGMENT_LOGIN;
import static pro.com.my.mysimpleapp.utils.Constants.FRAGMENT_OUTLET_MAP;
import static pro.com.my.mysimpleapp.utils.Constants.FRAGMENT_SIGN_UP;
import static pro.com.my.mysimpleapp.utils.Constants.FRAGMENT_WELCOME;

public class UiUtils {

    /**
     * Changes the System Bar Theme.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void setSystemBarTheme(final Activity activity, final boolean isDark, int coloInt) {
        // Fetch the current flags.
        final int flags = activity.getWindow().getDecorView().getSystemUiVisibility();
        // Update the SystemUiVisibility depending on whether we want a Light or Dark theme.
        activity.getWindow().getDecorView().setSystemUiVisibility(isDark ? (flags & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) : (flags | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR));

        activity.getWindow().setStatusBarColor(coloInt);
    }

    /**
     * Returns the available fragments basing on the fragment Index
     *
     * @param fragmentIndex
     * @return
     */
    public static BaseFragment getFragment(int fragmentIndex) {
        BaseFragment fragment = WelcomeFragment.getInstance();
        switch (fragmentIndex) {
            case FRAGMENT_WELCOME:
                fragment = WelcomeFragment.getInstance();
                break;
            case FRAGMENT_LOGIN:
                fragment = LoginFragment.getInstance();
                break;
            case FRAGMENT_SIGN_UP:
                fragment = SignupFragment.getInstance();
                break;
            case FRAGMENT_FORGOT:
                fragment = ForgotPinFragment.getInstance();
                break;
            case FRAGMENT_OUTLET_MAP:
                fragment = OutletMapFragment.getInstance();
                break;
            default:
                fragment = WelcomeFragment.getInstance();
                break;
        }
        return fragment;
    }

}
